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
import android.support.v4.content.AsyncTaskLoader;

public class RetrieverLoader<T> extends AsyncTaskLoader<RetrieverResult<T>> {

    private Retriever<T> retriever;
    private RetrieverResult<T> retrieverResult;

    public RetrieverLoader(Context context, Retriever<T> retriever) {
        super(context);
        this.retriever = retriever;
        retrieverResult = new RetrieverResult<>();
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
        if (retrieverResult.getContent() != null) {
            deliverResult(retrieverResult);
        }
        if (takeContentChanged() || retrieverResult.getContent() == null) {
            forceLoad();
        }
    }

    @Override
    public RetrieverResult<T> loadInBackground() {
        try {
            retrieverResult.setContent(retriever.retrieve());
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
        retrieverResult = new RetrieverResult<>();
    }
}
