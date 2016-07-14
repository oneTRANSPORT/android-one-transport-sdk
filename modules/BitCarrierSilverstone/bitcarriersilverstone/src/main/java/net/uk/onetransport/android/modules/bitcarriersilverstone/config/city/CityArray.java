package net.uk.onetransport.android.modules.bitcarriersilverstone.config.city;

import android.content.Context;

import com.interdigital.android.dougal.resource.Container;
import com.interdigital.android.dougal.resource.ContentInstance;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.BaseArray;
import net.uk.onetransport.android.modules.bitcarriersilverstone.R;
import net.uk.onetransport.android.modules.bitcarriersilverstone.authentication.CredentialHelper;

public class CityArray extends BaseArray implements DougalCallback {

    public static final String AE_NAME = "Worldsensing";
    private static final String RETRIEVE_PREFIX = AE_NAME
            + "/BitCarrier/v1.0/InterdigitalDemo/installationtest/silverstone/config/cities/c21";

    private City city;
    private CityArrayCallback cityArrayCallback;
    private int id; // TODO    Move to superclass?

    private CityArray() {
    }

    public CityArray(City city) {
        this.city = city;
    }

    public static CityArray getCityArray(Context context) throws Exception {
        String aeId = "C-" + CredentialHelper.getAeId(context);
        String userName = CredentialHelper.getAeId(context);
        String password = CredentialHelper.getSessionToken(context);
        String cseBaseUrl = context.getString(R.string.bitcarrier_cse_base_url);
        City newCity;
        ContentInstance contentInstance = Container.retrieveLatest(aeId, cseBaseUrl,
                RETRIEVE_PREFIX, userName, password);
        String content = contentInstance.getContent();
        newCity = GSON.fromJson(content, City.class);
        return new CityArray(newCity);
    }

    public static void getCityArrayAsync(Context context,
                                          CityArrayCallback cityArrayCallback, int id) {
        CityArray cityArray = new CityArray();
        cityArray.cityArrayCallback = cityArrayCallback;
        cityArray.id = id;
        String aeId = "C-" + CredentialHelper.getAeId(context);
        String userName = CredentialHelper.getAeId(context);
        String password = CredentialHelper.getSessionToken(context);
        String cseBaseUrl = context.getString(R.string.bitcarrier_cse_base_url);
        Container.retrieveLatestAsync(aeId, cseBaseUrl,
                RETRIEVE_PREFIX, userName, password, cityArray);
    }

    @Override
    public void getResponse(Resource resource, Throwable throwable) {
        if (throwable != null) {
            cityArrayCallback.onCityArrayError(id, throwable);
        } else {
            try {
                String content = ((ContentInstance) resource).getContent();
                city = GSON.fromJson(content, City.class);
                cityArrayCallback.onCityArrayReady(id, this);
            } catch (Exception e) {
                cityArrayCallback.onCityArrayError(id, e);
            }
        }
    }

    public City getCity() {
        return city;
    }
}
