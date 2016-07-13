package net.uk.onetransport.android.modules.bitcarriersilverstone.config.node;

public interface NodeArrayCallback {

    void onNodeArrayReady(int id, NodeArray nodeArray);

    void onNodeArrayError(int id, Throwable throwable);

}
