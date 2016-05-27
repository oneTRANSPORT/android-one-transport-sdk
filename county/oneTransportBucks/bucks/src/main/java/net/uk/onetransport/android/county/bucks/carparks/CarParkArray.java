package net.uk.onetransport.android.county.bucks.carparks;

import android.content.Context;

import com.interdigital.android.dougal.resource.Container;
import com.interdigital.android.dougal.resource.ContentInstance;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.BaseArray;
import net.uk.onetransport.android.county.bucks.R;
import net.uk.onetransport.android.county.bucks.authentication.CredentialHelper;

public class CarParkArray extends BaseArray implements DougalCallback {

    private static final String RETRIEVE_PATH1 = "BCCCarParkFeedImport/All";
    private static final String RETRIEVE_PATH2 = "BCCCarPark2FeedImport/All";

    private CarPark[] carParks;
    private CarParkArrayCallback carParkArrayCallback;
    private int id;
    private static int completed = 0;

    private CarParkArray() {
    }

    public CarParkArray(CarPark[] carParks) {
        this.carParks = carParks;
    }

    public static CarParkArray getCarParkArray(Context context) throws Exception {
        String aeId = "C-" + CredentialHelper.getAeId(context);
        String userName = CredentialHelper.getAeId(context);
        String password = CredentialHelper.getSessionToken(context);
        String cseBaseUrl = context.getString(R.string.bucks_cse_base_url);
        ContentInstance contentInstance = Container.retrieveLatest(aeId, cseBaseUrl, RETRIEVE_PATH1,
                userName, password);
        String content = contentInstance.getContent();
        CarParkArray carParkArray1 = new CarParkArray(GSON.fromJson(content, CarPark[].class));
        contentInstance = Container.retrieveLatest(aeId, cseBaseUrl, RETRIEVE_PATH2,
                userName, password);
        content = contentInstance.getContent();
        CarParkArray carParkArray2 = new CarParkArray(GSON.fromJson(content, CarPark[].class));
        merge(carParkArray1, carParkArray2);
        return carParkArray1;
    }

    public static void getCarParkArrayAsync(Context context,
                                            CarParkArrayCallback carParkArrayCallback, int id) {
        CarParkArray carParkArray = new CarParkArray();
        carParkArray.carParkArrayCallback = carParkArrayCallback;
        carParkArray.id = id;
        String aeId = "C-" + CredentialHelper.getAeId(context);
        String userName = CredentialHelper.getAeId(context);
        String password = CredentialHelper.getSessionToken(context);
        String cseBaseUrl = context.getString(R.string.bucks_cse_base_url);
        completed = 0;
        Container.retrieveLatestAsync(aeId, cseBaseUrl, RETRIEVE_PATH1, userName, password,
                carParkArray);
        Container.retrieveLatestAsync(aeId, cseBaseUrl, RETRIEVE_PATH2, userName, password,
                carParkArray);
    }

    @Override
    public void getResponse(Resource resource, Throwable throwable) {
        if (throwable != null) {
            carParkArrayCallback.onCarParkArrayError(id, throwable);
        } else {
            if (completed == 0) {
                try {
                    String content = ((ContentInstance) resource).getContent();
                    carParks = GSON.fromJson(content, CarPark[].class);
                } catch (Exception e) {
                    carParkArrayCallback.onCarParkArrayError(id, e);
                }
                completed++;
            } else {
                try {
                    String content = ((ContentInstance) resource).getContent();
                    CarPark[] carParks2 = GSON.fromJson(content, CarPark[].class);
                    CarParkArray carParkArray2 = new CarParkArray(carParks2);
                    merge(this, carParkArray2);
                    carParkArrayCallback.onCarParkArrayReady(id, this);
                } catch (Exception e) {
                    carParkArrayCallback.onCarParkArrayError(id, e);
                }
            }

        }
    }

    public CarPark[] getCarParks() {
        return carParks;
    }

    private static void merge(CarParkArray cpa1, CarParkArray cpa2) {
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
    }
}
