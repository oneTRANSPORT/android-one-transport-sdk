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
package net.uk.onetransport.android.modules.clearviewsilverstone.device;

import android.content.Context;

import com.interdigital.android.dougal.resource.Container;
import com.interdigital.android.dougal.resource.ResourceChild;

import net.uk.onetransport.android.modules.clearviewsilverstone.generic.Retriever;

import java.util.ArrayList;

public class DeviceRetriever extends Retriever<Device> implements DeviceParams {

    public DeviceRetriever(Context context) {
        super(context);
    }

    public ArrayList<Device> retrieve() throws Exception {
        ArrayList<Device> devices = new ArrayList<>();
        retrieve(devices, null);
        return devices;
    }

    @Override
    protected String getRetrievePrefix() {
        return RETRIEVE_PREFIX;
    }

    @Override
    protected ResourceChild[] retrieveChildren(String aeId, String cseBaseUrl, String token)
            throws Exception {
        return Container.retrieveChildren(aeId, cseBaseUrl, getRetrievePrefix(), token).getResourceChildren();

    }

    @Override
    protected Device fromJson(String content, String container, String cinId, Long creationTime) {
        Device device = GSON.fromJson(content, Device.class);
        device.setSensorId(Integer.parseInt(container.replaceAll("[^0-9]+", "")));
        device.setCinId(cinId);
        device.setCreationTime(creationTime);
        return device;
    }
}
