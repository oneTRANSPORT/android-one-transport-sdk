package net.uk.onetransport.android.modules.bitcarriersilverstone.vector;

public interface VectorArrayCallback {

    void onVectorArrayReady(int id, VectorArray vectorArray);

    void onVectorArrayError(int id, Throwable throwable);

}
