package net.uk.onetransport.android.county.bucks.carparks;

import android.content.Context;

import net.uk.onetransport.android.county.bucks.generic.Retriever;

public class CarParkRetriever extends Retriever<CarPark> implements CarParkParams {

    public CarParkRetriever(Context context) {
        super(context);
    }

    @Override
    protected String getRetrievePrefix() {
        return RETRIEVE_PREFIX;
    }

    @Override // TODO    Needs Unique index title, cinId?
    protected CarPark[] fromJson(String content, String cinId, Long creationTime) {
        CarPark[] carParks = GSON.fromJson(content, CarPark[].class);
        for (CarPark carPark : carParks) {
            carPark.setCinId(cinId);
            carPark.setCreationTime(creationTime);
        }
        return carParks;
    }
}
