package net.uk.onetransport.android.county.herts.carparks;

import android.content.Context;

import net.uk.onetransport.android.county.herts.generic.RetrieverLoader;

public class CarParkRetrieverLoader extends RetrieverLoader<CarPark> {

    public CarParkRetrieverLoader(Context context) {
        super(context, new CarParkRetriever(context));
    }
}
