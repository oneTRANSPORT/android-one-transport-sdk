package net.uk.onetransport.android.county.bucks.locations;

public interface PredefinedLocationArrayCallback {

    void onPredefinedLocationArrayReady(int id, PredefinedLocationArray predefinedLocationArray);

    void onPredefinedLocationArrayError(int id, Throwable throwable);

}
