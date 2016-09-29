package net.uk.onetransport.android.county.oxon.variablemessagesigns;

import android.content.Context;

import net.uk.onetransport.android.county.oxon.generic.RetrieverLoader;

public class VariableMessageSignRetrieverLoader extends RetrieverLoader<VariableMessageSign> {

    public VariableMessageSignRetrieverLoader(Context context) {
        super(context, new VariableMessageSignRetriever(context));
    }
}
