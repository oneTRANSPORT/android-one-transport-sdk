package net.uk.onetransport.android.modules.clearviewsilverstone.traffic;

import android.content.Context;

import com.interdigital.android.dougal.resource.ApplicationEntity;
import com.interdigital.android.dougal.resource.ResourceChild;

import net.uk.onetransport.android.modules.clearviewsilverstone.generic.Retriever;

import java.util.ArrayList;

public class TrafficGroupRetriever extends Retriever<TrafficGroup> implements TrafficGroupParams {

    public TrafficGroupRetriever(Context context) {
        super(context);
    }

    public ArrayList<TrafficGroup> retrieve() throws Exception {
        ArrayList<TrafficGroup> trafficGroups = new ArrayList<>();
        retrieve(trafficGroups, "DEVICE_");  // TODO    Extract.
        return trafficGroups;
    }

    @Override
    protected String getRetrievePrefix() {
        return RETRIEVE_PREFIX;
    }

    @Override
    protected ResourceChild[] retrieveChildren(String aeId, String cseBaseUrl, String userName,
                                               String password) throws Exception {
        return ApplicationEntity.retrieveChildren(aeId, cseBaseUrl, getRetrievePrefix(),
                userName, password).getResourceChildren();
    }

    @Override
    protected TrafficGroup fromJson(String content, String container, String cinId, Long creationTime) {
        Traffic[] traffic = GSON.fromJson(content, Traffic[].class);
        TrafficGroup trafficGroup = new TrafficGroup();
        trafficGroup.setSensorId(Integer.parseInt(container.replaceAll("[^0-9]+", "")));
        trafficGroup.setTraffic(traffic);
        trafficGroup.setCinId(cinId);
        trafficGroup.setCreationTime(creationTime);
        return trafficGroup;
    }
}
