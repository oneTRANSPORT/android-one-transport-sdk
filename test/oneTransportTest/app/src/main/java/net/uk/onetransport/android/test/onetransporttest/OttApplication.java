package net.uk.onetransport.android.test.onetransporttest;

import android.app.Application;

import net.uk.onetransport.android.county.bucks.provider.BucksProvider;

public class OttApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        BucksProvider.initialise(getApplicationContext());
    }
}
