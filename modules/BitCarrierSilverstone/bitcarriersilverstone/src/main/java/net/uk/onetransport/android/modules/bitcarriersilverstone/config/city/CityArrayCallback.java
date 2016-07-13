package net.uk.onetransport.android.modules.bitcarriersilverstone.config.city;

public interface CityArrayCallback {

    void onCityArrayReady(int id, CityArray cityArray);

    void onCityArrayError(int id, Throwable throwable);

}
