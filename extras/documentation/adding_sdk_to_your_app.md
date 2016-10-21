Adding the SDK to your app
==========================

## Configure your Gradle build file

The oneTRANSPORT SDK for Android is currently distributed as a set of AAR
libraries that you can add to your `app/libs` directory.

In the `build.gradle` in your app module, add these lines to allow including
libraries from the `libs` directory:

        repositories {
            flatDir {
                dirs 'libs'
            }
        }

As well as including the oneTRANSPORT libraries, you will also need a few
third-party packages.  Add these to your list of dependencies:

        compile 'com.interdigital.android:bucks-debug:1.0.0@aar'
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

**Note:** the order of dependencies is important as resources from the local
authority libraries need to override those in Common and Dougal.

## Set up string resources

To allow the SDK to create a content provider, the app must supply an
authority string for the provider to use.  A common way to create this
string is to add '`.provider`' to the app package name.  So for example,
the Sample Map App defines this authority:

        <string name="provider_authority">com.interdigital.android.samplemapdataapp.provider</string>

The SDK also provides a sync adapter implementation and if you want to use
this, you must supply a unique account identifier.  The sync adapter never
asks for account credentials, but this string must exist anyway.  Your
package name in reverse will be fine, for example:

        <string name="sync_account_type">samplemapdataapp.android.interdigital.com</string>


