package net.uk.onetransport.android.county.bucks.carparks;

import android.content.Context;

import net.uk.onetransport.android.county.bucks.generic.RetrieverLoader;

public class CarParkRetrieverLoader extends RetrieverLoader<CarPark> {

    public CarParkRetrieverLoader(Context context) {
        super(context, new CarParkRetriever(context));
    }
}
