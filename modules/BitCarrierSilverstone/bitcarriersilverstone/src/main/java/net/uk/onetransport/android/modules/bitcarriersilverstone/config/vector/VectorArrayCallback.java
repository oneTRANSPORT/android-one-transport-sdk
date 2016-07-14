package net.uk.onetransport.android.modules.bitcarriersilverstone.config.vector;

public interface VectorArrayCallback {

    void onVectorArrayReady(int id, VectorArray vectorArray);

    void onVectorArrayError(int id, Throwable throwable);

}
