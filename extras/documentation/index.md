oneTRANSPORT Android SDK
========================

## [What data is available?](available_data.html)
This SDK gives your app access to real-time road transport data from four
counties in England: Buckinghamshire, Hertfordshire, Northamptonshire and
Oxfordshire.

In addition, there are historical data sets from race weekends at Silverstone
circuit during the Formula One Grand Prix and Moto GP races in 2016.

## [Adding the SDK to your app](adding_sdk_to_your_app.html)
Whether you build with Android Studio or another IDE, you'll need to add
several AAR files to your projects and also third party libraries like
[Gson](https://github.com/google/gson) and
[Retrofit](https://github.com/square/retrofit).

## [Retriever API](retrievers.html)
For the latest road traffic data we provide a simple, no-frills retriever
API that returns oneTRANSPORT resources as arrays of objects, either
synchronously or in the background.

## [Local caching API](local_cache.html)
To provide a better user experience, we also have a caching API that
uses a SQLite database, content provider and sync adapter to store and
update oneTRANSPORT resources locally.  This is what you will use to access
historical data sets, but can also be used to build up a store of the
latest road traffic resources.

A content helper class means you don't have to deal with cursors if you
would prefer to work with pojos instead.

## [SQL queries and cursors](queries_and_cursors.html)
If you run the content provider, then direct SQL queries through a content
resolver are possible of course.  Our content helper also returns cursors
if you would prefer to inject those into adapter views.

<!-- ## [Dougal---a oneM2M SDK](dougal.html)
oneTRANSPORT is based on an implementation of the oneM2M standard called
Dougal.  Lower level routines to access individual containers and content
instances are available here. -->

## [Troubleshooting](troubleshooting.html)
Is your app not working?  We have error logging for you.
