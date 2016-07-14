package net.uk.onetransport.android.modules.bitcarriersilverstone.data.sketch;

public interface SketchArrayCallback {

    void onSketchArrayReady(int id, SketchArray sketchArray);

    void onSketchArrayError(int id, Throwable throwable);

}
