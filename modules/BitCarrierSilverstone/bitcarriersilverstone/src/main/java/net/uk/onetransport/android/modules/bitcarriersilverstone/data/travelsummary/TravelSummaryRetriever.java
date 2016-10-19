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
package net.uk.onetransport.android.modules.bitcarriersilverstone.data.travelsummary;

import android.content.Context;

import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.Retriever;

import java.util.ArrayList;

public class TravelSummaryRetriever extends Retriever<TravelSummary>
        implements TravelSummaryParams {

    public TravelSummaryRetriever(Context context) {
        super(context);
    }

    public ArrayList<TravelSummary> retrieve() throws Exception {
        ArrayList<TravelSummary> travelSummaries = new ArrayList<>();
        retrieve(travelSummaries);
        return travelSummaries;
    }

    @Override
    protected String getRetrievePrefix() {
        return RETRIEVE_PREFIX;
    }

    @Override
    protected TravelSummary fromJson(String content, String cinId, Long creationTime) {
        TravelSummary travelSummary = GSON.fromJson(content, TravelSummary.class);
        travelSummary.setCinId(cinId);
        travelSummary.setCreationTime(creationTime);
        return travelSummary;
    }

}
