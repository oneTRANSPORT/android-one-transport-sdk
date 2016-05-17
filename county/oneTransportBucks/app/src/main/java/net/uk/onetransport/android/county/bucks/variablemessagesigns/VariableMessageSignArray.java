package net.uk.onetransport.android.county.bucks.variablemessagesigns;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;

import com.interdigital.android.dougal.resource.Container;
import com.interdigital.android.dougal.resource.ContentInstance;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.BaseArray;
import net.uk.onetransport.android.county.bucks.provider.BucksContract;
import net.uk.onetransport.android.county.bucks.provider.BucksProvider;

public class VariableMessageSignArray extends BaseArray implements DougalCallback {

    private static final String RETRIEVE_PATH = "BCCSignSettingFeedImport/All";

    private VariableMessageSign[] variableMessageSigns;
    private VariableMessageSignArrayCallback variableMessageSignArrayCallback;
    private int id;

    public VariableMessageSignArray() {
    }

    public VariableMessageSignArray(VariableMessageSign[] variableMessageSigns) {
        this.variableMessageSigns = variableMessageSigns;
    }

    public static VariableMessageSignArray getVariableMessageSignArray(String aeId, String baseUrl,
                                                                       String userName, String password) throws Exception {
        ContentInstance contentInstance = Container.retrieveLatest(aeId, baseUrl, RETRIEVE_PATH,
                userName, password);
        String content = contentInstance.getContent();
        return new VariableMessageSignArray(GSON.fromJson(content, VariableMessageSign[].class));
    }

    public static void getVariableMessageSignArrayAsync(String aeId, String baseUrl, String userName,
                                                        String password,
                                                        VariableMessageSignArrayCallback variableMessageSignArrayCallback, int id) {
        VariableMessageSignArray variableMessageSignArray = new VariableMessageSignArray();
        variableMessageSignArray.variableMessageSignArrayCallback = variableMessageSignArrayCallback;
        variableMessageSignArray.id = id;
        Container.retrieveLatestAsync(aeId, baseUrl, RETRIEVE_PATH, userName, password,
                variableMessageSignArray);
    }

    @Override
    public void getResponse(Resource resource, Throwable throwable) {
        if (throwable != null) {
            variableMessageSignArrayCallback.onVariableMessageSignArrayError(id, throwable);
        } else {
            try {
                String content = ((ContentInstance) resource).getContent();
                variableMessageSigns = GSON.fromJson(content, VariableMessageSign[].class);
                variableMessageSignArrayCallback.onVariableMessageSignArrayReady(id, this);
            } catch (Exception e) {
                variableMessageSignArrayCallback.onVariableMessageSignArrayError(id, e);
            }
        }
    }

    public void insertIntoProvider(Context context) {
        if (variableMessageSigns != null && variableMessageSigns.length > 0) {
            ContentResolver contentResolver = context.getContentResolver();
            ContentValues values = new ContentValues();
            StringBuffer buffer = new StringBuffer();
            for (VariableMessageSign variableMessageSign : variableMessageSigns) {
                values.clear();
                values.put(BucksContract.VariableMessageSign.COLUMN_LOCATION_REFERENCE,
                        variableMessageSign.getLocationReference());
                values.put(BucksContract.VariableMessageSign.COLUMN_NUMBER_OF_CHARACTERS,
                        variableMessageSign.getNumberOfCharacters());
                values.put(BucksContract.VariableMessageSign.COLUMN_NUMBER_OF_ROWS,
                        variableMessageSign.getNumberOfRows());
                buffer.delete(0, buffer.length());
                String[] vmsLegends = variableMessageSign.getVmsLegends();
                for (int i = 0; i < vmsLegends.length; i++) {
                    buffer.append(vmsLegends[i]);
                    if (i < vmsLegends.length - 1) {
                        buffer.append("|");
                    }
                }
                values.put(BucksContract.VariableMessageSign.COLUMN_VMS_LEGENDS, buffer.toString());
                values.put(BucksContract.VariableMessageSign.COLUMN_VMS_TYPE,
                        variableMessageSign.getVmsType());
                contentResolver.insert(BucksProvider.VARIABLE_MESSAGE_SIGN_URI, values);
            }
        }
    }

    public static void deleteFromProvider(Context context){
        ContentResolver contentResolver = context.getContentResolver();
        contentResolver.delete(BucksProvider.VARIABLE_MESSAGE_SIGN_URI, null, null);
    }

    public VariableMessageSign[] getVariableMessageSigns() {
        return variableMessageSigns;
    }
}
