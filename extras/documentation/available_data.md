What data is available?
=======================

## Near-real-time local authority feeds

Data sources are provided by county and by data type, but note that there
is a fair amount of overlap between the regions and councils may control
items outside their boundaries.  For example, Northamptonshire has variable
message signs near Luton and Leicester.  We suggest consuming all the feeds
for the items in which you are interested and then filtering geographically
if needed.

Also, not every county provides every type of data.  These feeds are
available:

> ### Buckinghamshire
> > Car parks, events, traffic flow, traffic queue, traffic scoot, traffic
> > speed, traffic travel time, roadworks, variable message signs

> ### Hertfordshire
> > Car parks, events, traffic flow, traffic scoot, traffic
> > speed, traffic travel time, roadworks, variable message signs

> ### Northamptonshire
> > Car parks, traffic flow, traffic travel time, roadworks,
> > variable message signs

> ### Oxfordshire
> > Car parks, events, traffic flow, traffic queue, traffic scoot, traffic
> > speed, traffic travel time, roadworks, variable message signs

The data types in each feed are common across counties, so a car park object
from Bucks will be the same class as one from Northants.  Objects retrieved
from a feed are generally indicative of the current situation, so if an
average speed reading across a link is 40kph, then that was true when the last
reading was observed a few minutes ago.  If an event occurs in a feed, then
that event is happening now.

Here are some examples to show the package structure for data classes:

> `net.uk.onetransport.android.county.bucks.carparks.CarPark.java`<br/>
> `net.uk.onetransport.android.county.herts.roadworks.Roadworks.java`<br/>
> `net.uk.onetransport.android.county.northants.trafficflow.TrafficFlow.java`<br/>
> `net.uk.onetransport.android.county.oxon.variablemessagesigns.VariableMessageSign.java`

The classes are:

> ### `CarPark`
> > **`String carParkIdentity`** unique identifier
> >
> > **`Double latitude`** latitude of this car park
> >
> > **`Double longitude`** longitude of this car park
> >
> > **`Double occupancy`**
> >
> > **`String occupancyTrend`**
> >
> > **`Double totalParkingCapacity`** number of parking spaces
> >
> > **`Double fillRate`** cars entering per hour
> >
> > **`Double exitRate`** cars leaving per hour
> >
> > **`Double almostFullIncreasing`** number of spaces above which 'spaces
> > available' becomes 'almost full' as cars enter
> >
> > **`Double almostFullDecreasing`** number of spaces below which 'almost full'
> > becomes 'spaces available' as cars leave
> >
> > **`Double fullIncreasing`** number of spaces above which 'almost full' becomes
> > 'full' as cars enter
> >
> > **`Double fullDecreasing`** number of spaces below which 'full' becomes
> > 'almost full' as cars leave
> >
> > **`String status`**
> >
> > **`String statusTime`**
> >
> > **`Double queuingTime`** current queuing time for car park entry
> >
> > **`String parkingAreaName`** name of this car park
> >
> > **`Double entranceFull`** number of spaces above which the car park is
> > considered full (so the entrance sign lights up 'FULL')

> ### `Event`
> > **`String id`** a unique identifier
> > 
> > **`String startOfPeriod`** time the event begins
> > 
> > **`String endOfPeriod`** time the event ends
> > 
> > **`String overallStartTime`** seems to be the same as **`startOfPeriod`**
> > 
> > **`String overallEndTime`** seems to be the same as **`endOfPeriod`**
> > 
> > **`Double latitude`** latitude of the event
> >
> > **`Double longitude`** longitude of the event
> > 
> > **`String description`** what the event actually is
> > 
> > **`String impactOnTraffic`** how traffic flow in the region of the event
> > is affected
> > 
> > **`String validityStatus`**

> ### `Roadworks`
> > **`String id`** unique identifier
> >
> > **`String effectOnRoadLayout`** how the traffic flow changes, if at all
> >
> > **`String roadMaintenanceType`**
> >
> > **`String comment`** description of the works to be carried out
> >
> > **`String impactOnTraffic`**
> >
> > **`Double latitude`** latitude of the roadworks
> >
> > **`Double longitude`** longitude of the roadworks
> >
> > **`String validityStatus`**
> >
> > **`String overallStartTime`** when the roadworks were started
> >
> > **`String overallEndTime`** scheduled end time
> >
> > **`String startOfPeriod`** seems to be the same as **`overallStartTime`**
> >
> > **`String endOfPeriod`** seems to be the same as **`overallEndTime`**

> ### `TrafficFlow`
> > **`String id`** unique identifier
> > 
> > **`String tpegDirection`**
> > 
> > **`String fromType`**
> > 
> > **`String fromDescriptor`** name of the start of the link
> > 
> > **`Double fromLatitude`** latitude of the start of the link
> > 
> > **`Double fromLongitude`** longitude of the start of the link
> > 
> > **`String toType`**
> > 
> > **`String toDescriptor`** name of the end of the link
> > 
> > **`Double toLatitude`** latitude of the end of the link
> > 
> > **`Double toLongitude`** longitude of the end of the link
> > 
> > **`String time`** timestamp of this item
> > 
> > **`Double vehicleFlow`** number of vehicles along the link per hour

> ### `TrafficQueue`
> > **`String id`** unique identifier
> > 
> > **`String tpegDirection`**
> > 
> > **`String fromType`**
> > 
> > **`String fromDescriptor`** name of the start of the link
> > 
> > **`Double fromLatitude`** latitude of the start of the link
> > 
> > **`Double fromLongitude`** longitude of the start of the link
> > 
> > **`String toType`**
> > 
> > **`String toDescriptor`** name of the end of the link
> > 
> > **`Double toLatitude`** latitude of the end of the link
> > 
> > **`Double toLongitude`** longitude of the end of the link
> > 
> > **`String time`** timestamp of this item
> > 
> > **`Severity`**
> >
> > **`Present`** 'Y' if a queue exists, 'N' otherwise

> ### `TrafficScoot`
> > **`String id`** unique identifier
> > 
> > **`String tpegDirection`**
> > 
> > **`String fromType`**
> > 
> > **`String fromDescriptor`** name of the start of the link
> > 
> > **`Double fromLatitude`** latitude of the start of the link
> > 
> > **`Double fromLongitude`** longitude of the start of the link
> > 
> > **`String toType`**
> > 
> > **`String toDescriptor`** name of the end of the link
> > 
> > **`Double toLatitude`** latitude of the end of the link
> > 
> > **`Double toLongitude`** longitude of the end of the link
> > 
> > **`String time`** timestamp of this item
> >
> > **`Double currentFlow`** vehicles per hour travelling along this link
> >
> > **`Double averageSpeed`** average speed of vehicles
> >
> > **`Double linkStatusType`**
> >
> > **`Double linkStatus`**
> >
> > **`Double linkTravelTime`**
> >
> > **`Double congestionPercent`** level of congestion on this link

> ### `TrafficSpeed`
> > **`String id`** unique identifier
> > 
> > **`String tpegDirection`**
> > 
> > **`String fromType`**
> > 
> > **`String fromDescriptor`** name of the start of the link
> > 
> > **`Double fromLatitude`** latitude of the start of the link
> > 
> > **`Double fromLongitude`** longitude of the start of the link
> > 
> > **`String toType`**
> > 
> > **`String toDescriptor`** name of the end of the link
> > 
> > **`Double toLatitude`** latitude of the end of the link
> > 
> > **`Double toLongitude`** longitude of the end of the link
> > 
> > **`String time`** timestamp of this item
> > 
> > **`Double averageVehicleSpeed`** average speed along this link in km/h

> ### `TrafficTravelTime`
> > **`String id`** unique identifier
> > 
> > **`String tpegDirection`**
> > 
> > **`String fromType`**
> > 
> > **`String fromDescriptor`** name of the start of the link
> > 
> > **`Double fromLatitude`** latitude of the start of the link
> > 
> > **`Double fromLongitude`** longitude of the start of the link
> > 
> > **`String toType`**
> > 
> > **`String toDescriptor`** name of the end of the link
> > 
> > **`Double toLatitude`** latitude of the end of the link
> > 
> > **`Double toLongitude`** longitude of the end of the link
> > 
> > **`String time`** timestamp of this item
> > 
> > **`Double travelTime`** actual time taken to traverse this link
> >
> > **`Double freeFlowTravelTime`** best possible time given minimal congestion
> >
> > **`Double freeFlowSpeed`** best possible speed given minimal congestion

> ### `VariableMessageSign`
> > **`String locationId`** unique identifier
> >
> > **`String description`** name of this sign
> >
> > **`String vmsType`**
> >
> > **`Double latitude`** latitude of this sign
> >
> > **`Double longitude`** longitude of this sign
> >
> > **`Double numberOfCharacters`** width of the matrix
> >
> > **`Double numberOfRows`** height of the matrix
> >
> > **`String[] legend`** lines of text currently displayed by this sign

## Silverstone near-real-time and historical data

We have two groups of feeds supplied by Clearview and BitCarrier, for the
car parks and roads at Silverstone circuit.

Clearview has parking sensors located at the entrances to car parks at the
venue and BitCarrier has Bluetooth sensors on road junctions that can match
Bluetooth ids from in-car entertainment systems or passengers' mobile phones.

Both providers supply relatively static feeds describing the configuration
of devices, that only need to be consumed once, and dynamic feeds about the
current state of car parks and road networks that can be read repeatedly to
build up a continuous picture of traffic information.

In addition, we can supply a SQLite database that contains historical data
for the weekends of the Formula One Grand Prix and Moto GP races in 2016.
You can add this database to your app and continue to add to it with current
feed data if you like.

Whether your data comes direct from a feed or from the database, the format
is the same except in the case of Clearview data where the database output
is slightly different to the direct feed class:

> `net.uk.onetransport.android.modules.clearviewsilverstone.device.Device.java`
> `net.uk.onetransport.android.modules.clearviewsilverstone.traffic.Traffic.java`
> `net.uk.onetransport.android.modules.clearviewsilverstone.traffic.TrafficItem.java`
> `net.uk.onetransport.android.modules.clearviewsilverstone.traffic.TrafficGroup.java`
> `net.uk.onetransport.android.modules.bitcarriersilverstone.config.node.Node.java`
> `net.uk.onetransport.android.modules.bitcarriersilverstone.config.sketch.Position.java`
> `net.uk.onetransport.android.modules.bitcarriersilverstone.config.sketch.Sketch.java`
> `net.uk.onetransport.android.modules.bitcarriersilverstone.config.vector.Vector.java`
> `net.uk.onetransport.android.modules.bitcarriersilverstone.travelsummary.Stat.java`
> `net.uk.onetransport.android.modules.bitcarriersilverstone.travelsummary.Details.java`
> `net.uk.onetransport.android.modules.bitcarriersilverstone.travelsummary.TravelTime.java`
> `net.uk.onetransport.android.modules.bitcarriersilverstone.travelsummary.TravelSummary.java`

> ### `Device`
> > **`int sensorId`** unique integer identifier for this car park sensor
> >
> > **`String title`** sensor name
> >
> > **`String description`** describes the sensor location
> >
> > **`String type`** sensor part code
> >
> > **`Double latitude`** latitude of this sensor
> >
> > **`Double longitude`** longitude of this sensor
> >
> > **`String changed`** time the sensor was last changed

> ### `Traffic`
> > **`String time`** time of this observation
> >
> > **`Integer lane`** lane in which the vehicle was detected
> >
> > **`Boolean direction`** false for entering, true for leaving

> ### `TrafficItem`
> > **`Integer sensorId`** unique identifier
> >
> > **`String time`** time of this observation
> >
> > **`Integer lane`** lane in which the vehicle was detected
> >
> > **`Boolean direction`** false for entering, true for leaving

> ### `TrafficGroup`
> > **`Integer sensorId`** unique identifier
> >
> > **`Traffic[] traffic`** list of observations

> ### `Node`
> > **`Integer id`** unique identifier
> >
> > **`String customerName`**  unique identifier assigned by the implementer,
> > hopefully containing a description of the location
> >
> > **`Double latitude`** latitude of this sensor
> >
> > **`Double longitude`** longitude of this sensor

> ### `Position`
> > **`Double latitude`**
> >
> > **`Double longitude`**

> ### `Sketch`
> > **`Integer sketchId`** unique identifier for this sketch
> > **`Integer vectorId`** corresponding vector identifier
> > **`Boolean visible`** always true
> > **`String copyrights`** attribution for this data
> > **`String coordinates`** JSON string of point data, can be ignored
> > **`Position[] positions`** use this array of positions instead of
> > `coordinates`

> ### `Vector`
> > **`Integer id`** unique identifier
> >
> > **`String name`** appears to be not working at present
> >
> > **`String customerName`** location of this vector prefixed by a unique
> > identifier
> >
> > **`Integer from`** start node of this vector
> >
> > **`Integer to`** end node of this vector
> >
> > **`Integer distance`** length of this link in kilometres
> >
> > **`Integer sId`** identifier of the sketch pertaining to this vector

> ### `Stat`
> > **`Double speed**` average speed in km/h along a link
> >
> > **`Double elapsed**` time taken to traverse this link
> >
> > **`Double trend**` 
> >
> > **`Integer readings**` how many readings contributed to this observation

> ### `Details`
> > **`Integer score`** 
> >
> > **`Stat publish`** values suitable for publication (eg. no speeding
> > drivers)
> >
> > **`Stat calculated`** actual values for this link

> ### `TravelTime`
> > **`Integer tId`** unique identifier
> >
> > **`Integer offset`** 
> >
> > **`String from`** start node
> >
> > **`String to`** end node

> ### `TravelSummary`
> > **`Integer rId`** unique identifier for this route
> >
> > **`String time`** timestamp for these observations
> >
> > **`TravelTime[] travelTimes`** only ever contains one element
> >
> > **`Details details`** average statistics for this route
> >
> > **`Details last`** last observed statistics
> >
> > **`String levelOfService`** red, yellow or green, with red being the most
> > congested and green being the least

### Historical data

An archive of SQL files may be found in the oneTRANSPORT repo here:

        /extras/cse_hoover/historical_import/silverstone_historical.tar

To import these into your app database, you should first run your app so that
it creates a database for its content provider in `/sdcard/oneTransport`.

For example, the oneTRANSPORT test app uses the following database file:

        /sdcard/oneTransport/net.uk.onetransport.android.test.onetransporttest.provider/one-transport-db

The path will depend on your own app package name, of course.  Close your app,
the copy the one-transport-db file down to your laptop with `adb`:

        adb pull /sdcard/oneTransport/net.uk.onetransport.android.test.onetransporttest.provider/one-transport-db

Unpack the historical archive in the current directory with:

        tar xf silverstone_historical.tar

Then insert the data files into your database with these SQLite commands
(note these will delete any existing rows in BitCarrier and Clearview tables
but local authority data will be preserved):

        bzcat config_vectors.sql.bz2 | sqlite3 one-transport-db
        bzcat data_vectors.sql.bz2 | sqlite3 one-transport-db
        bzcat devices.sql.bz2 | sqlite3 one-transport-db
        bzcat nodes.sql.bz2 | sqlite3 one-transport-db
        bzcat sketches.sql.bz2 | sqlite3 one-transport-db
        bzcat traffic.sql.bz2 | sqlite3 one-transport-db
        bzcat travel_times.sql.bz2 | sqlite3 one-transport-db

Finally, copy the updated database back to your Android device with:

        adb push one-transport-db /sdcard/oneTransport/<your-package-name>.provider/one-transport-db

Alternatively, if you do not have any existing local authority data you wish
to keep, just uncompress the SQLite binary file in this archive and copy that
into place:

        bunzip2 one-transport-db.bz2
        adb push one-transport-db /sdcard/oneTransport/<your-package-name>.provider/one-transport-db

Now you should be able to restart your app and historical data from BitCarrier
and Clearview at Silverstone over the F1 Grand Prix and Moto GP races will be
available.
