package net.uk.onetransport.android.county.northants.variablemessagesigns;

import android.content.Context;

import net.uk.onetransport.android.county.northants.generic.RetrieverLoader;

public class VariableMessageSignRetrieverLoader extends RetrieverLoader<VariableMessageSign> {

    public VariableMessageSignRetrieverLoader(Context context) {
        super(context, new VariableMessageSignRetriever(context));
    }
}
