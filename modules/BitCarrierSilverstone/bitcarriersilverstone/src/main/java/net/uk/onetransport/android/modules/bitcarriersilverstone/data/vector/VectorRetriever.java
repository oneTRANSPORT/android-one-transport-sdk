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
package net.uk.onetransport.android.modules.bitcarriersilverstone.data.vector;

import android.content.Context;

import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.Retriever;

import java.util.ArrayList;

public class VectorRetriever extends Retriever<Vector> implements VectorParams {

    public VectorRetriever(Context context) {
        super(context);
    }

    public ArrayList<Vector> retrieve() throws Exception {
        ArrayList<Vector> vectors = new ArrayList<>();
        retrieve(vectors);
        return vectors;
    }

    @Override
    protected String getRetrievePrefix() {
        return RETRIEVE_PREFIX;
    }

    @Override
    protected Vector fromJson(String content, String cinId, Long creationTime) {
        Vector vector = GSON.fromJson(content, Vector.class);
        vector.setCinId(cinId);
        vector.setCreationTime(creationTime);
        return vector;
    }

}
