package net.uk.onetransport.android.county.bucks.carparks;

import android.content.Context;

import com.interdigital.android.dougal.resource.Container;
import com.interdigital.android.dougal.resource.ContentInstance;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.BaseArray;
import net.uk.onetransport.android.county.bucks.R;

public class CarParkArray extends BaseArray implements DougalCallback {

    private static final String RETRIEVE_PATH = "BCCCarPark2FeedImport/All";

    private CarPark[] carParks;
    private CarParkArrayCallback carParkArrayCallback;
    private int id;

    private CarParkArray() {
    }

    public CarParkArray(CarPark[] carParks) {
        this.carParks = carParks;
    }

    public static CarParkArray getCarParkArray(Context context) throws Exception {
        String aeId = maybeCreateAe(context);
        if (aeId == null) { // TODO Error reporting?
            return null;
        }
        String cseBaseUrl = context.getString(R.string.bucks_cse_base_url);
        String userName = context.getString(R.string.one_transport_user_name);
        String password = context.getString(R.string.one_transport_password);
        ContentInstance contentInstance = Container.retrieveLatest(aeId, cseBaseUrl, RETRIEVE_PATH,
                userName, password);
        String content = contentInstance.getContent();
        return new CarParkArray(GSON.fromJson(content, CarPark[].class));
    }

    public static void getCarParkArrayAsync(Context context, CarParkArrayCallback carParkArrayCallback,
                                            int id) {
        CarParkArray carParkArray = new CarParkArray();
        carParkArray.carParkArrayCallback = carParkArrayCallback;
        carParkArray.id = id;
        String aeId = maybeCreateAe(context);
        if (aeId == null) { // TODO Error reporting?
            return;
        }
        String cseBaseUrl = context.getString(R.string.bucks_cse_base_url);
        String userName = context.getString(R.string.one_transport_user_name);
        String password = context.getString(R.string.one_transport_password);
        Container.retrieveLatestAsync(aeId, cseBaseUrl, RETRIEVE_PATH, userName, password,
                carParkArray);
    }

    @Override
    public void getResponse(Resource resource, Throwable throwable) {
        if (throwable != null) {
            carParkArrayCallback.onCarParkArrayError(id, throwable);
        } else {
            try {
                String content = ((ContentInstance) resource).getContent();
                carParks = GSON.fromJson(content, CarPark[].class);
                carParkArrayCallback.onCarParkArrayReady(id, this);
            } catch (Exception e) {
                carParkArrayCallback.onCarParkArrayError(id, e);
            }
        }
    }

    public CarPark[] getCarParks() {
        return carParks;
    }
}
