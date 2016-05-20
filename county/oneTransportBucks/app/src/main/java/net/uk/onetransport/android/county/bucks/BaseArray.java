package net.uk.onetransport.android.county.bucks;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.interdigital.android.dougal.Types;
import com.interdigital.android.dougal.exception.DougalException;
import com.interdigital.android.dougal.resource.ApplicationEntity;

import net.uk.onetransport.android.county.bucks.storage.Prefs;

import java.util.UUID;

public class BaseArray {

    protected static final Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    protected static String maybeCreateAe(Context context) {
        String installationId = maybeCreateInstallationId(context);
        String aeId = "C-one-transport-bucks-" + installationId;
        String appName = context.getString(R.string.one_transport_app_name) + installationId;
        String applicationId = "App-id-" + installationId;
        String baseUrl = context.getString(R.string.bucks_base_url);
        String cseName = context.getString(R.string.bucks_cse_name);
        ApplicationEntity applicationEntity = new ApplicationEntity(aeId, appName, applicationId,
                baseUrl, cseName, false);
        String userName = context.getString(R.string.one_transport_user_name);
        String password = context.getString(R.string.one_transport_password);
        try {
            applicationEntity.create(userName, password);
        } catch (Exception e) {
            // Will fail if the AE already exists or if there is a network issue.
            if (e instanceof DougalException) {
                DougalException dougalException = (DougalException) e;
                switch (dougalException.getCode()) {
                    case Types.STATUS_CODE_ALREADY_EXISTS:
                        // No problem.
                        return aeId;
                    default:
                        // Problem.
                        return null;
                }
            } else {
                // Possible network or other problem?
                return null;
            }
        }
        // No exception, so AE is valid.
        return aeId;
    }

    protected static String maybeCreateInstallationId(Context context) {
        String installationId = Prefs.getInstallationId(context);
        if (installationId == null) {
            installationId = UUID.randomUUID().toString();
            Prefs.putInstallationId(context, installationId);
        }
        return installationId;
    }
}
