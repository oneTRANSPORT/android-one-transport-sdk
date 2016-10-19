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
package net.uk.onetransport.android.county.herts.trafficflow;

import android.content.Context;

import net.uk.onetransport.android.county.herts.generic.Retriever;

public class TrafficFlowRetriever extends Retriever<TrafficFlow> implements TrafficFlowParams {

    public TrafficFlowRetriever(Context context) {
        super(context);
    }

    @Override
    protected String getRetrievePrefix() {
        return RETRIEVE_PREFIX;
    }

    @Override
    protected TrafficFlow[] fromJson(String content, String cinId, Long creationTime) {
        TrafficFlow[] trafficFlows = GSON.fromJson(content, TrafficFlow[].class);
        for (TrafficFlow trafficFlow : trafficFlows) {
            trafficFlow.setCinId(cinId);
            trafficFlow.setCreationTime(creationTime);
        }
        return trafficFlows;
    }
}
