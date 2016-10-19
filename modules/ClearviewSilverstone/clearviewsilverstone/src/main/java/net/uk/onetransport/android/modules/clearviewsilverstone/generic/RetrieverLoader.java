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
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;

public class RetrieverLoader<T> extends AsyncTaskLoader<RetrieverResult<T>> {

    private Retriever<T> retriever;
    private RetrieverResult<T> retrieverResult;
    private String container;

    public RetrieverLoader(Context context, Retriever<T> retriever, String container) {
        super(context);
        this.retriever = retriever;
        retrieverResult = new RetrieverResult<>(new ArrayList<T>(), new ArrayList<Exception>());
        this.container = container;
    }

    @Override
    public void deliverResult(RetrieverResult<T> retrieverResult) {
        if (isReset()) {
            return;
        }
        this.retrieverResult = retrieverResult;
        super.deliverResult(retrieverResult);
    }

    @Override
    protected void onStartLoading() {
        if (retrieverResult.getTs().size() != 0) {
            deliverResult(retrieverResult);
        }
        if (takeContentChanged() || retrieverResult.getTs().size() == 0) {
            forceLoad();
        }
    }

    @Override
    public RetrieverResult<T> loadInBackground() {
        try {
            retriever.retrieve(retrieverResult.getTs(), container);
        } catch (Exception exception) {
            retrieverResult.getExceptions().add(exception);
        }
        return retrieverResult;
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        retrieverResult = new RetrieverResult<>(new ArrayList<T>(), new ArrayList<Exception>());
    }
}
