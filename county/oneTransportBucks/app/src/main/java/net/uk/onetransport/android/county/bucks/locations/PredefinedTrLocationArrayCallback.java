package net.uk.onetransport.android.county.bucks.locations;

public interface PredefinedTrLocationArrayCallback {

    void onPredefinedTrLocationArrayReady(int id, PredefinedTrLocationArray predefinedTrLocationArray);

    void onPredefinedTrLocationArrayError(int id, Throwable throwable);

}
