Sample Map Data app
===================

There is an example app available that uses the oneTRANSPORT Android SDK
to fetch data from the CSE and plot it on a Google Map.  The source code is
here:

> [`https://github.com/oneTRANSPORT/android-sample-map-data-app`](https://github.com/oneTRANSPORT/android-sample-map-data-app)

Hackathon attendees might want to borrow some code from this app to get going
quickly, so here is a description of the main functional parts.

## Overview

There is only one activity, `MapActivity`, that contains:

* Toolbar with a refresh button
* Navigation drawer with a list of layers and checkboxes
* SupportMapFragment

Each of the feeds corresponds to a layer of items on the map, such as car
parks or traffic queues.  Items like these will cluster when zooming out and
split up when zooming in.

For performance reasons, we only display the first ten items of each layer.
This is enough to show how things work and still keep everything fast.  If we
added every item in all feeds to the map, it would be very slow to redraw.

There are also polyline elements on the map that show roads around Silverstone
as part of the BitCarrier Sketch feed.

The data for most of the layers is stored in a SQLite database wrapped by a
content provider.  On pressing the refresh button,a sync adapter is started
which downloads new data from the CSE and sends it to the content provider.

Once the downloads have finished, the map can be refreshed.  The exception
is the layer for FastPrk demo kit sensors that has the sensor configuration
hard-coded into the layer class and makes its own oneM2M requests every few
seconds to update the sensor icon status.

## User interface

These are the possible interactions:

> ### Toolbar
> **Refresh button**: tapping starts a sync adapter that downloads the latest
> data from the CSE.  An indeterminate progress bar is displayed at the top of
> the screen and when downloads are complete, the map is updated.

> **Hamburger icon**: opens up a left-hand-side navigation drawer containing
> layer information.

> ### Navigation drawer
> Opened either by the hamburger icon or a right swipe
> from the left edge of the display and is closed by swiping left.

> The drawer contains a vertical-scrolling list of layer labels, each having a
> checkbox to determine its visibility.  If the checkbox is tapped to
> deselect, then items in the corresponding layer are removed from the map.
> If selected, a layer will reappear.

> ### Google map view
> Can be dragged in any direction and pinch gestures zoom in and out.  Items
> appear on the map either individually or in clusters.  Tapping on a cluster
> splits it into smaller clusters or individual items and zooms into the
> surrounding region.

> Tapping on an item that is not in a cluster causes an info window pop-up to
> be displayed above that item.  This will show data retrieved from the CSE
> pertaining to traffic, parking, roadworks etc.

## Initialisation

There is a callback method named `onMapReady` that is invoked when the
map fragment has finished its initialisation and is ready for content to be
added.

This is a convenient place to set up the credentials for each SDK module so
that access to the CSE will be granted in subsequent retrieve requests.
Modules have a credential helper class that allows the client to inject their
AE id and access key from the developer portal:

        CredentialHelper.initialiseCredentials(context, AE_ID, ACCESS_KEY, INSTALLATION_ID);

For now, the installation id is not used, so any non-empty string will do
for this parameter.

Having set up the credentials for a module, any subsequent retrieve requests
will pass without authorisation errors.  This only has to be done once per
session.

Also in `onMapReady`, the layer classes are instantiated and added to an
array of `BaseLayer` for easy access.

## Layers

### `BaseLayer`

The `BaseLayer` class is the ancestor of all layers on the map.

* Stores `Context` and `GoogleMap` objects and provides accessors
* Provides abstract lifecycle methods `initialise()`, `load()`, `addToMap()`
and `setVisible(boolean)` that must be implemented by extending classes
* Has a helper method for testing if a date string of an item is recent
enough for that item to be included in a layer

### `MarkerBaseLayer`

Layers that contain simple map markers should extend this base class.

* Manages loading markers initially
* Correctly routes clicks to the handler for the right marker
* Shows the info window of the clicked marker

Only `BitCarrierSilverstoneNodes` extends `MarkerBaseLayer`.  There are so
few nodes that clustering functionality is not required.

### `ClusterBaseLayer`

All layers containing items so numerous that they cluster together on
zooming out, extend `ClusterBaseLayer`.

* Implements the `BaseLayer` lifecycle methods to control the loading and
display of items and clusters
* Provides abstract methods to allow extending classes to inject
`ClusterManager` and `ClusterRenderer` objects

All local authority layers extend `ClusterBaseLayer`.

### `PolylineBaseLayer`

Rather than map markers, sometimes we need to show polyline data on the map.
Since polylines normally need to be clicked to show data relating to their
route, there is a circular marker in the middle that can accept clicks.  This
is easier than clicking on a line that overlaps other lines as the markers are
usually not coincident.

There is no change in configuration as the user zooms in or out.

* Manages adding new polylines to the map
* Routes clicks to the correct marker
* Shows info windows of clicked markers

Only `BitCarrierSilverstone` extends this class.

### `FastPrk`

The FastPrk layer is different to the rest as it requires no configuration
data from the CSE and is not kept in the content provider.  There are only
three active sensors currently, corresponding to one of the FastPrk portable
demo kits.

The latitude and longitude of the sensors (made up) are stored in the
layer class, `FastPrk`.  We have put them around Aylesbury.

When the app starts up, a 15-second timer based on a handler is started.  When
this ends, connections are made to the CSE to determine the state of the
FastPrk demo parking sensors.  The map icons are updated and a new timer is
started.

## Clusters

To implement a layer of clustered markers, there needs to be an item class,
a manager and a renderer.  Every item class implements Google's `ClusterItem`
interface that requires a `getPosition()` method and returns a `LatLng`
object.

As well as a position member field, item classes also contain a data object
retrieved from a oneTRANSPORT feed that is used to populate the info window
pop-up for the corresponding map marker.

Add getters and setters, and that's all there is to cluster items.

The manager and renderer classes are more complicated but logic can be shared
between layers by encapsulating functionality in abstract base classes.

### `BaseClusterManager`

The logic for Google's clusters is encapsulated in `BaseCusterManager` to the
extent that subclasses are very simple to implement, the only method required
in each case being a constructor.

* Manages clicks on clusters to expand along with zoom in
* Injects renderer into superclass

### `BaseClusterRenderer`

Google provides the `DefaultClusterRenderer` superclass for this class to
extend.

* Adds marker icons for clustered and non-clustered states
* Sets visibility for markers
* Allows icons and y-anchor value to be injected by an extending class

Along with passing icons to the cluster renderer, extending classes must
implement `getInfoContents(Marker)` if they want to display an info window
when tapped.  This method returns the view that will appear in the pop-up.

## Markers

### `BaseMarker`

Google's `Marker` class is unhelpfully final, but `BaseMarker` wraps `Marker`
so that it can be used as a superclass.

* Simple data class for a marker and its configuration options

In fact, since most layers use an implementation of `ClusterItem`, only
`BitCarrierSilverstoneNodeMarker` extends this class.

## Polylines

Like `BaseMarker`, `BasePolyline` wraps Google's `Polyline` class but also
includes a marker that is used as a clickable centrepoint.

* Data class for a polyline and its options
* Add points to form a polyline
* Sets the location of a clickable centrepoint and adds a marker to the map

The only layer to use polylines is `BitCarrierSilverstone` with the
`BitCarrierSketchPolyline` extended class.

## Downloading new data

The activity contains a method called `refreshCache` that invokes the
`refresh` methods of each module.  This causes the sync adapter to run and
download the latest data from the CSE.

There are six modules so therefore six runs of the sync adapter.  Every time
a sync adapter finishes its download, it updates a table in the content
provider with the URI `LAST_UPDATED_URI`.

In order to find out when the refresh has finished, the activity registers an
`ItemObserver` in its `onResume` method (it is deregistered in `onPause`).
When the item observer detects the sixth update to the last updated table, it
triggers a refresh of the map view.

## Adding content to the map

All of the markers and polylines displayed on the map view come from the
content provider, so anything that doesn't go in the database doesn't get
rendered (with the exception of the FastPrk demo kit layer described earlier).

Since content is read out from the database, that has to happen off the main
application thread.  The app creates a `LoadMarkerTask` for this purpose that
extends Android's `AsyncTask`.

There is a complication that changes to the map view must occur on the main
thread.  So `LoadMarkerTask` does some initialisation firstly on the main
thread, then switches to the background to load data, then returns to the main
thread to install content on the map.

> ### Initialisation
> 
> When the map is refreshed, any markers and polylines that are already on the
> map need to be removed so that duplicates are not accidentally overlaid.  Each
> layer loops through its list of map content and removes each item.
> 
> Any manager and renderer classes are instantiated here ready to be used later.

> ### Content provider access
> 
> On the background thread, each layer gets a chance to make its
> `*ContentHelper.getLatest*` calls to the helper classes and instantiate its
> own map items.

> ### Post execute
> 
> Back on the main thread, layers now get to add their new map items to the
> Google map view.  If this is the first time that the map view is being shown,
> we move the camera to the middle of Aylesbury where we know there is some
> data from FastPrk.
