package net.uk.onetransport.android.modules.bitcarriersilverstone.config.vector;

import android.content.Context;

import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.Retriever;

import java.util.ArrayList;

public class VectorRetriever extends Retriever<Vector> implements VectorParams {

    public ArrayList<Vector> retrieve(Context context) throws Exception {
        ArrayList<Vector> vectors = new ArrayList<>();
        retrieve(context, vectors);
        return vectors;
    }

    @Override
    protected int[] getIds() {
        return VECTOR_IDS;
    }

    @Override
    protected String getRetrivePrefix() {
        return RETRIEVE_PREFIX;
    }

    @Override
    protected Vector fromJson(String content) {
        return GSON.fromJson(content, Vector.class);
    }

}
