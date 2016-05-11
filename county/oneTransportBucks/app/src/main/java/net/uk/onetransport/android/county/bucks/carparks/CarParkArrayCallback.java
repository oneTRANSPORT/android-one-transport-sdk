package net.uk.onetransport.android.county.bucks.carparks;

public interface CarParkArrayCallback {

    void onCarParkArrayReady(int id, CarParkArray carParkArray);

    void onCarParkArrayError(int id, Throwable throwable);

}
