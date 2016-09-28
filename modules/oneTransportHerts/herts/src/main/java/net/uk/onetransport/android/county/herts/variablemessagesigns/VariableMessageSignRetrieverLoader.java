package net.uk.onetransport.android.county.herts.variablemessagesigns;

import android.content.Context;

import net.uk.onetransport.android.county.herts.generic.RetrieverLoader;

public class VariableMessageSignRetrieverLoader extends RetrieverLoader<VariableMessageSign> {

    public VariableMessageSignRetrieverLoader(Context context) {
        super(context, new VariableMessageSignRetriever(context));
    }
}
