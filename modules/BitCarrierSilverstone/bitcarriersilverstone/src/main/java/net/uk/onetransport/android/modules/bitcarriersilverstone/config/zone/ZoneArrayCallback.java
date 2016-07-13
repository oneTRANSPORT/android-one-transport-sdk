package net.uk.onetransport.android.modules.bitcarriersilverstone.config.zone;

public interface ZoneArrayCallback {

    void onZoneArrayReady(int id, ZoneArray zoneArray);

    void onZoneArrayError(int id, Throwable throwable);

}
