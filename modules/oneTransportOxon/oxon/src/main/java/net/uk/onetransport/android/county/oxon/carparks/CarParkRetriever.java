package net.uk.onetransport.android.county.oxon.carparks;

import android.content.Context;

import net.uk.onetransport.android.county.oxon.generic.Retriever;

public class CarParkRetriever extends Retriever<CarPark> implements CarParkParams {

    public CarParkRetriever(Context context) {
        super(context);
    }

    @Override
    protected String getRetrievePrefix() {
        return RETRIEVE_PREFIX;
    }

    @Override
    protected CarPark[] fromJson(String content, String cinId, Long creationTime) {
        CarPark[] carParks = GSON.fromJson(content, CarPark[].class);
        for (CarPark carPark : carParks) {
            carPark.setCinId(cinId);
            carPark.setCreationTime(creationTime);
        }
        return carParks;
    }
}
