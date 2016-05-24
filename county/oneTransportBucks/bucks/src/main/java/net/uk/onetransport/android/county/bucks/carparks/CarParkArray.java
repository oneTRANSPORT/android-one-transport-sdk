package net.uk.onetransport.android.county.bucks.carparks;

import android.content.Context;

import com.interdigital.android.dougal.resource.Container;
import com.interdigital.android.dougal.resource.ContentInstance;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.BaseArray;
import net.uk.onetransport.android.county.bucks.R;

public class CarParkArray extends BaseArray implements DougalCallback {

    private static final String RETRIEVE_PATH1 = "BCCCarParkFeedImport/All";
    private static final String RETRIEVE_PATH2 = "BCCCarPark2FeedImport/All";

    private CarPark[] carParks;
    private CarParkArrayCallback carParkArrayCallback;
    private int id;

    private CarParkArray() {
    }

    public CarParkArray(CarPark[] carParks) {
        this.carParks = carParks;
    }

    public static CarParkArray getCarParkArray(Context context) throws Exception {
        String aeId = getAeId(context);
        String cseBaseUrl = context.getString(R.string.bucks_cse_base_url);
        String userName = context.getString(R.string.one_transport_user_name);
        String password = context.getString(R.string.one_transport_password);
        ContentInstance contentInstance = Container.retrieveLatest(aeId, cseBaseUrl, RETRIEVE_PATH1,
                userName, password);
        String content = contentInstance.getContent();
        CarParkArray carParkArray1 = new CarParkArray(GSON.fromJson(content, CarPark[].class));
        contentInstance = Container.retrieveLatest(aeId, cseBaseUrl, RETRIEVE_PATH2,
                userName, password);
        content = contentInstance.getContent();
        CarParkArray carParkArray2 = new CarParkArray(GSON.fromJson(content, CarPark[].class));
        return merge(carParkArray1, carParkArray2);
    }

    public static void getCarParkArrayAsync(Context context,
                                            CarParkArrayCallback carParkArrayCallback, int id) {
        CarParkArray carParkArray = new CarParkArray();
        carParkArray.carParkArrayCallback = carParkArrayCallback;
        carParkArray.id = id;
        String aeId = getAeId(context);
        String cseBaseUrl = context.getString(R.string.bucks_cse_base_url);
        String userName = context.getString(R.string.one_transport_user_name);
        String password = context.getString(R.string.one_transport_password);
        Container.retrieveLatestAsync(aeId, cseBaseUrl, RETRIEVE_PATH1, userName, password,
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

    private static CarParkArray merge(CarParkArray cpa1, CarParkArray cpa2) {
        for (CarPark cp1 : cpa1.getCarParks()) {
            for (CarPark cp2 : cpa2.getCarParks()) {
                if (cp1.getCarParkIdentity().equals(cp2.getCarParkIdentity())) {
                    if (cp1.getExitRate() == null) {
                        cp1.setExitRate(cp2.getExitRate());
                    }
                    if (cp1.getFillRate() == null) {
                        cp1.setFillRate(cp2.getFillRate());
                    }
                    if (cp1.getCarParkIdentity() == null) {
                        cp1.setCarParkIdentity(cp2.getCarParkIdentity());
                    }
                    if (cp1.getTotalParkingCapacity() == null) {
                        cp1.setTotalParkingCapacity(cp2.getTotalParkingCapacity());
                    }
                    if (cp1.getAlmostFullDecreasing() == null) {
                        cp1.setAlmostFullDecreasing(cp2.getAlmostFullDecreasing());
                    }
                    if (cp1.getAlmostFullIncreasing() == null) {
                        cp1.setAlmostFullIncreasing(cp2.getAlmostFullIncreasing());
                    }
                    if (cp1.getFullDecreasing() == null) {
                        cp1.setFullDecreasing(cp2.getFullDecreasing());
                    }
                    if (cp1.getFullIncreasing() == null) {
                        cp1.setFullIncreasing(cp2.getFullIncreasing());
                    }
                    if (cp1.getEntranceFull() == null) {
                        cp1.setEntranceFull(cp2.getEntranceFull());
                    }
                    if (cp1.getLatitude() == null) {
                        cp1.setLatitude(cp2.getLatitude());
                    }
                    if (cp1.getLongitude() == null) {
                        cp1.setLongitude(cp2.getLongitude());
                    }
                }
            }
        }
        return cpa1;
    }
}
