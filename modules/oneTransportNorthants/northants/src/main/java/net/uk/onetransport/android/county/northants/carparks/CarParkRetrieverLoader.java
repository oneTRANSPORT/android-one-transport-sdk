package net.uk.onetransport.android.county.northants.carparks;

import android.content.Context;

import net.uk.onetransport.android.county.northants.generic.RetrieverLoader;

public class CarParkRetrieverLoader extends RetrieverLoader<CarPark> {

    public CarParkRetrieverLoader(Context context) {
        super(context, new CarParkRetriever(context));
    }
}
