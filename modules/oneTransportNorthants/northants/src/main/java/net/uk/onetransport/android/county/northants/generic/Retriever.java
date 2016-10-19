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
package net.uk.onetransport.android.county.northants.generic;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.interdigital.android.dougal.resource.Container;
import com.interdigital.android.dougal.resource.ContentInstance;

import net.uk.onetransport.android.county.northants.R;
import net.uk.onetransport.android.county.northants.authentication.CredentialHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Retriever<T> {

    public static final Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    public Context context;

    public Retriever(Context context) {
        this.context = context;
    }

    public T[] retrieve() throws Exception {
        String aeId = CredentialHelper.getAeId(context);
        String token = CredentialHelper.getSessionToken(context);
        String cseBaseUrl = context.getString(R.string.northants_cse_base_url);
        ContentInstance contentInstance = Container.retrieveLatest(aeId, cseBaseUrl,
                getRetrievePrefix(), token);
        String cinId = contentInstance.getResourceId();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
        Long creationTime = null;
        if (contentInstance.getCreationTime() != null) {
            Date date = sdf.parse(contentInstance.getCreationTime());
            creationTime = date.getTime() / 1000L; // Millis to seconds.  Save a bit of db space.
        }
        String content = contentInstance.getContent();
        return fromJson(content, cinId, creationTime);
    }

    protected abstract String getRetrievePrefix();

    protected abstract T[] fromJson(String content, String cinId, Long creationTime);

}

