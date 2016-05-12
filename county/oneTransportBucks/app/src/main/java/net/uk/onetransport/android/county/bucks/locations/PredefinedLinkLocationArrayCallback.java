package net.uk.onetransport.android.county.bucks.locations;

public interface PredefinedLinkLocationArrayCallback {

    void onPredefinedLinkLocationArrayReady(int id,
                                            PredefinedLinkLocationArray predefinedLinkLocationArray);

    void onPredefinedLinkLocationArrayError(int id, Throwable throwable);

}
