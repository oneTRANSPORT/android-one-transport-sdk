package net.uk.onetransport.android.modules.bitcarriersilverstone.config.city;

import android.content.Context;

import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.RetrieverLoader;

public class CityRetrieverLoader extends RetrieverLoader<City> implements CityParams {

    public CityRetrieverLoader(Context context) {
        super(context, new CityRetriever(context));
    }

}
