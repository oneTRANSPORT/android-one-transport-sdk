package net.uk.onetransport.android.county.bucks.carparks;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.interdigital.android.dougal.resource.Container;
import com.interdigital.android.dougal.resource.ContentInstance;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.BaseArray;
import net.uk.onetransport.android.county.bucks.provider.BucksContract;
import net.uk.onetransport.android.county.bucks.provider.BucksProvider;

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

    public static CarParkArray getCarParkArray(String aeId, String baseUrl, String userName,
                                               String password) throws Exception {
        ContentInstance contentInstance = Container.retrieveLatest(aeId, baseUrl, RETRIEVE_PATH,
                userName, password);
        String content = contentInstance.getContent();
        return new CarParkArray(GSON.fromJson(content, CarPark[].class));
    }

    public static void getCarParkArrayAsync(String aeId, String baseUrl, String userName, String password,
                                            CarParkArrayCallback carParkArrayCallback, int id) {
        CarParkArray carParkArray = new CarParkArray();
        carParkArray.carParkArrayCallback = carParkArrayCallback;
        carParkArray.id = id;
        Container.retrieveLatestAsync(aeId, baseUrl, RETRIEVE_PATH, userName, password, carParkArray);
    }

    @Override
    public void getResponse(Resource resource, Throwable throwable) {
        if (throwable != null) {
            carParkArrayCallback.onCarParkArrayError(id, throwable);
        } else {
            String content = ((ContentInstance) resource).getContent();
            // TODO Use one instance of Gson everywhere.
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            carParks = gson.fromJson(content, CarPark[].class);
            carParkArrayCallback.onCarParkArrayReady(id, this);
        }
    }

    public void insertIntoProvider(Context context) {
        if (carParks != null && carParks.length > 0) {
            ContentResolver contentResolver = context.getContentResolver();
            ContentValues values = new ContentValues();
            for (CarPark carPark : carParks) {
                values.clear();
                values.put(BucksContract.CarPark.RADIUS, carPark.getRadius());
                values.put(BucksContract.CarPark.LATITUDE, carPark.getLatitude());
                values.put(BucksContract.CarPark.LONGITUDE, carPark.getLongitude());
                values.put(BucksContract.CarPark.ENTRANCE_FULL, carPark.getEntranceFull());
                values.put(BucksContract.CarPark.FULL_INCREASING, carPark.getFullIncreasing());
                values.put(BucksContract.CarPark.FULL_DECREASING, carPark.getFullDecreasing());
                values.put(BucksContract.CarPark.CAR_PARK_IDENTITY, carPark.getCarParkIdentity());
                values.put(BucksContract.CarPark.TOTAL_PARKING_CAPACITY, carPark.getTotalParkingCapacity());
                values.put(BucksContract.CarPark.ALMOST_FULL_INCREASING, carPark.getAlmostFullIncreasing());
                values.put(BucksContract.CarPark.ALMOST_FULL_DECREASING, carPark.getAlmostFullDecreasing());
                contentResolver.insert(BucksProvider.CAR_PARK_URI, values);
            }
        }
    }

    public CarPark[] getCarParks() {
        return carParks;
    }
}
