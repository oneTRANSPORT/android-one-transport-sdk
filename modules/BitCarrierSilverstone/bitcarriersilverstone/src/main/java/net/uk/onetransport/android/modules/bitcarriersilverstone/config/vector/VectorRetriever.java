package net.uk.onetransport.android.modules.bitcarriersilverstone.config.vector;

import android.content.Context;
import android.database.Cursor;

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
        return GSON.fromJson(content, Vector.class);
    }

    @Override
    protected Cursor getResourceNames(Context context, int id) {
        return null;
    }
}
