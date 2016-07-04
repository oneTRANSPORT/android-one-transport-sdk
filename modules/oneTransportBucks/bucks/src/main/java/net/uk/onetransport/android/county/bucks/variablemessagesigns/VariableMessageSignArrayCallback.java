package net.uk.onetransport.android.county.bucks.variablemessagesigns;

public interface VariableMessageSignArrayCallback {

    void onVariableMessageSignArrayReady(int id, VariableMessageSignArray variableMessageSignArray);

    void onVariableMessageSignArrayError(int id, Throwable throwable);

}
