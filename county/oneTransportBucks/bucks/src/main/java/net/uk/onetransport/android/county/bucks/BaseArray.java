package net.uk.onetransport.android.county.bucks;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.uk.onetransport.android.county.bucks.storage.Prefs;

public class BaseArray {

    protected static final Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    private static String aeId;

    public static String getAeId(Context context) {
        if (aeId == null) {
            aeId = context.getString(R.string.bucks_ae_id_prefix) + Prefs.getInstallationId(context);
        }
        return aeId;
    }
}
