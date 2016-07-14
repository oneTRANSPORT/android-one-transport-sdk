package net.uk.onetransport.android.modules.bitcarriersilverstone.data.vector;

public interface VectorStatusArrayCallback {

    void onVectorStatusArrayReady(int id, VectorStatusArray vectorStatusArray);

    void onVectorStatusArrayError(int id, Throwable throwable);

}
