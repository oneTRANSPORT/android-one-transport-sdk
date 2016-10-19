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
package net.uk.onetransport.android.modules.clearviewsilverstone.generic;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.interdigital.android.dougal.resource.Container;
import com.interdigital.android.dougal.resource.ContentInstance;
import com.interdigital.android.dougal.resource.ResourceChild;

import net.uk.onetransport.android.modules.clearviewsilverstone.R;
import net.uk.onetransport.android.modules.clearviewsilverstone.authentication.CredentialHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public abstract class Retriever<T> {

    public static final Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    public Context context;

    public Retriever(Context context) {
        this.context = context;
    }

    public void retrieve(ArrayList<T> list, String container) throws Exception {
        String aeId = CredentialHelper.getAeId(context);
        String token = CredentialHelper.getSessionToken(context);
        String cseBaseUrl = context.getString(R.string.clearview_cse_base_url);
        // Get the names of child resources.
        // This is very ugly, but we have to follow the spec.
        ResourceChild[] children = retrieveChildren(aeId, cseBaseUrl, token);
        for (int i = 0; i < children.length; i++) {
            String name = children[i].getName();
            if (TextUtils.isEmpty(container) || name.contains(container)) {
                ContentInstance contentInstance = Container.retrieveLatest(aeId, cseBaseUrl,
                        getRetrievePrefix() + "/" + name, token);
                String cinId = contentInstance.getResourceId();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
                Long creationTime = null;
                if (contentInstance.getCreationTime() != null) {
                    Date date = sdf.parse(contentInstance.getCreationTime());
                    creationTime = date.getTime() / 1000L; // Millis to seconds.  Save a bit of db space.
                }
                String content = contentInstance.getContent();
                list.add(fromJson(content, name, cinId, creationTime));
            }
        }
    }

    protected abstract String getRetrievePrefix();

    protected abstract ResourceChild[] retrieveChildren(String aeId, String cseBaseUrl, String token)
            throws Exception;

    protected abstract T fromJson(String content, String container, String cinId, Long creationTime);

}
