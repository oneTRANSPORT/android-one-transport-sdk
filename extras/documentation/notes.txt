SDK docs outline
----------------

oneTransport Android SDK: What data is available?

Real time: Car parks, roadworks, traffic flow/queue/scoot/speed/travel time, VMS.
           Some of Bucks, Herts, Northants and Oxon.
Historical: Silverstone, Clearview car parks and BitCarrier traffic flow.

Server URLs?  Maybe not needed.  Appendix, troubleshooting, OkHttp logs.

Adding libraries: from Git repo?

eg. compile 'com.interdigital.android:bucks-debug:1.0.0@aar'
    compile 'com.interdigital.android:herts-debug:1.0.0@aar'
    compile 'com.interdigital.android:northants-debug:1.0.0@aar'
    compile 'com.interdigital.android:oxon-debug:1.0.0@aar'
    compile 'com.interdigital.android:clearviewsilverstone-debug:1.0.0@aar'
    compile 'com.interdigital.android:bitcarriersilverstone-debug:1.0.0@aar'
    compile 'com.interdigital.android:common-debug:1.0.0@aar'
    compile 'com.interdigital.android:dougal-debug:1.0.0@aar'
    compile 'com.google.code.gson:gson:2.5'
    compile 'com.squareup.retrofit2:retrofit:2.0.1'
    compile 'com.squareup.retrofit2:converter-gson:2.0.1'
    compile 'com.squareup.okhttp3:logging-interceptor:3.2.0'
(Also add local lib folder.)

Resources: strings.xml

Authentication: AE, token.

Adding historical data.

Retriever API:

eg. CarParkRetriever(Context context)

Sync API

eg. refresh(...)

Content helper

eg. BucksContentHelper(...)

  Cursor methods
  Object methods

Content provider

Resolver.
URIs.
Tables.
C-r-u-d.
Cursor change notification.
SQL queries

Lower level:

Dougal

AE, containers, content instances.
?rcn=6, 

Troubleshooting.


Sample Map App
--------------

...
