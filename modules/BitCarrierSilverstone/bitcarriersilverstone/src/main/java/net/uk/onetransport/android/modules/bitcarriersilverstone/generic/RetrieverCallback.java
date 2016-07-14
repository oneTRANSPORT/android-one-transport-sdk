package net.uk.onetransport.android.modules.bitcarriersilverstone.generic;


public interface RetrieverCallback<B extends Retriever> {

    void onArrayReady(int id, B b);

    void onArrayError(int id, Throwable throwable);

}
