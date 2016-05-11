package net.uk.onetransport.android.county.bucks.carparks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.interdigital.android.dougal.resource.Container;
import com.interdigital.android.dougal.resource.ContentInstance;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

public class CarParkArray implements DougalCallback {

    private static final String RETRIEVE_PATH = "BCCCarPark2FeedImport/All";

    private CarPark[] carParks;
    private CarParkArrayCallback carParkArrayCallback;
    private int id;

    public CarParkArray() {
    }

    public CarParkArray(CarPark[] carParks) {
        this.carParks = carParks;
    }

    public static CarParkArray getCarParkArray(String aeId, String baseUrl, String userName,
                                               String password) throws Exception {
        ContentInstance contentInstance = Container.retrieveLatest(aeId, baseUrl, RETRIEVE_PATH,
                userName, password);
        String content = contentInstance.getContent();
        // TODO Use one instance of Gson everywhere.
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return new CarParkArray(gson.fromJson(content, CarPark[].class));
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
            carParkArrayCallback.onCarParkListError(id, throwable);
        } else {
            String content = ((ContentInstance) resource).getContent();
            // TODO Use one instance of Gson everywhere.
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            carParks = gson.fromJson(content, CarPark[].class);
            carParkArrayCallback.onCarParkListReady(id, this);
        }
    }

    public CarPark[] getCarParks() {
        return carParks;
    }
}
