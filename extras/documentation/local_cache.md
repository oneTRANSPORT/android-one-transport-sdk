Local caching API
=================

The oneTRANSPORT SDK provides a way for clients to acquire and store data
from the CSE in a structured format suitable for running local queries.  A
typical Android pattern is implemented where a SQLite database is wrapped by
a Content Provider, using a Sync Adapter to refresh the content when needed.

There is also a class of static helper routines which can return resource
objects if you would prefer not to work with content provider URLs and
database cursors.

## Content helper classes

Each oneTRANSPORT module contains its own helper class for accessing SQL
tables relating to that module.  The Common module manages a content provider
that allows the other modules to inject tables and to insert, select, update
and delete data.

There's no need to instantiate these classes as they contain only static
methods.  The local authority content helpers are:

        net.uk.onetransport.android.county.bucks.provider.BucksContentHelper.java
        net.uk.onetransport.android.county.herts.provider.HertsContentHelper.java
        net.uk.onetransport.android.county.Northants.provider.NorthantsContentHelper.java
        net.uk.onetransport.android.county.oxon.provider.OxonContentHelper.java

There are also helper classes for the BitCarrier and Clearview resources
at Silverstone:

        net.uk.onetransport.android.modules.bitcarriersilverstone.provider.BcsContentHelper.java
        net.uk.onetransport.android.modules.clearviewsilverstone.provider.CvsContentHelper.java

## Refreshing the local cache

Each content helper provides a `refresh` method that takes an Android
context and then several boolean parameters which should be true if a
particular resource should be retrieved from the CSE and inserted into the
local database.  For example, here is how to download car parks and roadworks
from Northamptonshire, but not traffic flows, travel times or variable message
signs:

        NorthantsProviderModule.refresh(context, true, true, false, false, false);

This method forces a sync adapter update of the content provider.  You could
start this in a background thread every few minutes if you wanted to store
frequent updates to CSE resources.  However, note that unless you poll very
often (eg. every minute), it is possible that you may miss some updates.

This is because the refresh process asks the CSE for the latest copy of each
resource and does not go back for all the updates that may have occurred since
the last time you polled the server.  Getting absolutely every change is more
difficult and for that you will need to use Dougal, the oneM2M Android API,
directly.

If you need to know when the update has completed, you can create a content
observer for `LastUpdatedProviderModule.LAST_UPDATED_URI`.  For example:

        public class AdapterObserver extends ContentObserver {
        
            private OneTransportTest oneTransportTest;
        
            public AdapterObserver(Handler handler) {
                super(handler);
            }
        
            @Override
            public void onChange(boolean selfChange) {
                super.onChange(selfChange);
                // Update has finished.  Do something.
            }
        
            @Override
            public void onChange(boolean selfChange, Uri uri) {
                super.onChange(selfChange, uri);
                // Update has finished.  Do something.
            }
        }

This is activated by registering:

        AdapterObserver adapterObserver = new AdapterObserver(null, this);
        context.getContentResolver().registerContentObserver(
            LastUpdatedProviderModule.LAST_UPDATED_URI, true, adapterObserver);

And when you no longer need to receive notification of an update, you should
deregister the observer:

        context.getContentResolver().unregisterContentObserver(adapterObserver);

You can of course observe any URI from the content provider, but the point
about `LAST_UPDATED_URI` is that it will only be refreshed when all of the
downloads in a module have occurred.

## Retrieving resources from the provider

Each helper has three kinds of getter method.  One takes only a context and
returns a list of objects representing the entire table of resources that
were requested.  For example,

        TrafficQueue[] trafficQueues = OxonContentHelper.getTrafficQueues(context);

The size of `trafficQueues` will be the same as the number of rows in the
`traffic_queue` SQL table.

The second getter method takes a time interval and returns all resources
that were inserted during that time.  So, to retrieve the last 24 hours
of traffic queues around Oxford, we could use:

        long now = System.currentTimeMillis() / 1000L;
        long hours24 = 60L * 60L * 24L;
        TrafficQueue[] trafficQueues = OxonContentHelper.getTrafficQueues(context, now - hours24, now); 

Lastly, it is possible to retrieve only the latest resources from the cache:

        TrafficQueue[] trafficQueues = OxonContentHelper.getLatestTrafficQueues(context);

In this case, all the resources that were delivered in the latest content
instance will be returned.

## Deleting resources from the cache

There are a couple of options for removing resources from local storage.
Each module provides methods for deleting all records of a particular type,
or all records of a particular type before a particular time.  The latter
can be useful for pruning back in time when you only want to keep recent
resources.

Removing all traffic queue records:

        OxonContentHelper.deleteFromProvider(context, OxonContentHelper.DATA_TYPE_TRAFFIC_QUEUE);

Remove traffic queues older than 24 hours:

        OxonContentHelper.deleteFromProviderBeforeTime(context,
                    OxonContentHelper.DATA_TYPE_TRAFFIC_QUEUE,
                    System.currentTimeMillis() / 1000L - 24L * 60L * 60L);
