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

import java.util.ArrayList;

public class RetrieverResult<T> {

    private T[] content = null;
    private ArrayList<Exception> exceptions = new ArrayList<>();

    public T[] getContent() {
        return content;
    }

    public ArrayList<Exception> getExceptions() {
        return exceptions;
    }

    public void setContent(T[] content) {
        this.content = content;
    }
}