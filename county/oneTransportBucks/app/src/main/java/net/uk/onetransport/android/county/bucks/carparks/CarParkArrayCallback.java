package net.uk.onetransport.android.county.bucks.carparks;

public interface CarParkArrayCallback {

    void onCarParkListReady(int id, CarParkArray carParkArray);

    void onCarParkListError(int id,Throwable throwable);

}
