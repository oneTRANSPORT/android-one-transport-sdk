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
package net.uk.onetransport.android.test.onetransporttest.tests.herts.provider;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;

import net.uk.onetransport.android.test.onetransporttest.tests.OneTransportTest;

public class AdapterObserver extends ContentObserver {

    private OneTransportTest oneTransportTest;

    public AdapterObserver(Handler handler, OneTransportTest oneTransportTest) {
        super(handler);
        this.oneTransportTest = oneTransportTest;
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        oneTransportTest.adapterFinished = true;
    }

    @Override
    public void onChange(boolean selfChange, Uri uri) {
        super.onChange(selfChange, uri);
        oneTransportTest.adapterFinished = true;
    }
}
