package net.uk.onetransport.android.county.bucks.variablemessagesigns;

import android.content.Context;

import net.uk.onetransport.android.county.bucks.generic.Retriever;

public class VariableMessageSignRetriever extends Retriever<VariableMessageSign>
        implements VariableMessageSignParams {

    public VariableMessageSignRetriever(Context context) {
        super(context);
    }

    @Override
    protected String getRetrievePrefix() {
        return RETRIEVE_PREFIX;
    }

    @Override
    protected VariableMessageSign[] fromJson(String content, String cinId, Long creationTime) {
        VariableMessageSign[] variableMessageSigns = GSON.fromJson(content, VariableMessageSign[].class);
        for (VariableMessageSign variableMessageSign : variableMessageSigns) {
            variableMessageSign.setCinId(cinId);
            variableMessageSign.setCreationTime(creationTime);
        }
        return variableMessageSigns;
    }
}
