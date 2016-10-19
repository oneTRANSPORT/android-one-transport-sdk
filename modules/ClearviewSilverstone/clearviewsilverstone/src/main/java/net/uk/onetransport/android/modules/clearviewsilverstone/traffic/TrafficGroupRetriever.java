/* Copyright 2016 InterDigital Communications, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
    protected ResourceChild[] retrieveChildren(String aeId, String cseBaseUrl, String token)
            throws Exception {
        return ApplicationEntity.retrieveChildren(aeId, cseBaseUrl, getRetrievePrefix(),
                token).getResourceChildren();
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
