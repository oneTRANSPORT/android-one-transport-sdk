package net.uk.onetransport.android.modules.clearviewsilverstone.device;

public interface DeviceArrayCallback {

    void onDeviceArrayReady(int id, DeviceArray deviceArray);

    void onDeviceArrayError(int id, Throwable throwable);

}
