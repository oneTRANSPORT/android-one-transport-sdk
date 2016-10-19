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
package net.uk.onetransport.android.county.herts.variablemessagesigns;

import android.content.Context;

import net.uk.onetransport.android.county.herts.generic.Retriever;

public class VariableMessageSignRetriever extends Retriever<VariableMessageSign>
        implements VariableMessageSignParams {

    public VariableMessageSignRetriever(Context context) {
        super(context);
    }

    @Override
    protected String getRetrievePrefix() {
        return RETRIEVE_PREFIX;
    }

    @Override
    protected VariableMessageSign[] fromJson(String content, String cinId, Long creationTime) {
        VariableMessageSign[] variableMessageSigns = GSON.fromJson(content, VariableMessageSign[].class);
        for (VariableMessageSign variableMessageSign : variableMessageSigns) {
            variableMessageSign.setCinId(cinId);
            variableMessageSign.setCreationTime(creationTime);
        }
        return variableMessageSigns;
    }
}
