package net.uk.onetransport.android.county.bucks.locations;

public interface PredefinedSectionLocationArrayCallback {

    void onPredefinedSectionLocationArrayReady(int id,
                                            PredefinedSectionLocationArray predefinedSectionLocationArray);

    void onPredefinedSectionLocationArrayError(int id, Throwable throwable);

}
