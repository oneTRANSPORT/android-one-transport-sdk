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

import java.util.ArrayList;

public class RetrieverResult<T> {

    private ArrayList<T> ts;
    private ArrayList<Exception> exceptions;

    public RetrieverResult(ArrayList<T> ts, ArrayList<Exception> exceptions) {
        this.ts = ts;
        this.exceptions = exceptions;
    }

    public ArrayList<T> getTs() {
        return ts;
    }

    public ArrayList<Exception> getExceptions() {
        return exceptions;
    }
}
