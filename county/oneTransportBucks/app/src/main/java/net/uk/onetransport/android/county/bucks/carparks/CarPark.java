package net.uk.onetransport.android.county.bucks.carparks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.interdigital.android.dougal.resource.Container;
import com.interdigital.android.dougal.resource.ContentInstance;

public class CarPark {

    private static final String BASE_URL = "https://cse-02.onetransport.uk.net/ONETCSE02";
    private static final String RETRIEVE_PATH = "/C-BCCCarPark2FeedImport/All";

    @Expose
    private String carParkIdentity;
    @Expose
    private Integer totalParkingCapacity;
    @Expose
    private Integer almostFullIncreasing;
    @Expose
    private Integer almostFullDecreasing;
    @Expose
    private Integer fullDecreasing;
    @Expose
    private Integer fullIncreasing;
    @Expose
    private Integer entranceFull;
    @Expose
    private Double radius;
    @Expose
    private Double latitude;
    @Expose
    private Double longitude;

    public static CarPark[] getCarParkList(String aeId, String userName, String password)
            throws Exception {
        ContentInstance contentInstance = Container.retrieveLatest(aeId, BASE_URL, RETRIEVE_PATH,
                userName, password);
        String content = contentInstance.getContent();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.fromJson(content, CarPark[].class);
    }

    public static void getCarParkListAsync(CarParkListCallback carParkListCallback) {

    }
}
