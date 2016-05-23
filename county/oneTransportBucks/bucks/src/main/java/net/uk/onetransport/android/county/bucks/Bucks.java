package net.uk.onetransport.android.county.bucks;

import android.content.Context;

import com.interdigital.android.dougal.Types;
import com.interdigital.android.dougal.exception.DougalException;
import com.interdigital.android.dougal.resource.ApplicationEntity;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.county.bucks.storage.Prefs;

import java.util.UUID;

public class Bucks implements DougalCallback {

    public interface OnInitialisationCompletedListener {
        void onInitialisationCompleted(Throwable throwable);
    }

    private Context context;
    private OnInitialisationCompletedListener listener;

    public Bucks(Context context) {
        this.context = context;
    }

    public void initialise() {
        maybeCreateAe(false);
    }

    public void initialiseAsync(OnInitialisationCompletedListener listener) {
        this.listener = listener;
        maybeCreateAe(true);
    }

    @Override
    public void getResponse(Resource resource, Throwable throwable) {
        if (throwable == null || (throwable instanceof DougalException
                && ((DougalException) throwable).getCode() == Types.STATUS_CODE_ALREADY_EXISTS)) {
            Prefs.putAeId(context, resource.getResourceId());
        }
        if (listener != null) {
            listener.onInitialisationCompleted(throwable);
        }
    }

    // TODO Also need async version.
    private void maybeCreateAe(boolean async) {
        String installationId = maybeCreateInstallationId();
        String aeId = "C-one-transport-bucks-" + installationId;
        String appName = context.getString(R.string.one_transport_app_name) + installationId;
        String applicationId = "App-id-" + installationId;
        String baseUrl = context.getString(R.string.bucks_base_url);
        String cseName = context.getString(R.string.bucks_cse_name);
        ApplicationEntity applicationEntity = new ApplicationEntity(aeId, appName, applicationId,
                baseUrl, cseName, false);
        String userName = context.getString(R.string.one_transport_user_name);
        String password = context.getString(R.string.one_transport_password);
        if (async) {
            applicationEntity.createAsync(userName, password, this);
        } else {
            try {
                applicationEntity.create(userName, password);
            } catch (Exception e) {
                // Will fail if the AE already exists or if there is a network issue.
                if (e instanceof DougalException) {
                    DougalException dougalException = (DougalException) e;
                    switch (dougalException.getCode()) {
                        case Types.STATUS_CODE_ALREADY_EXISTS:
                            // No problem.
                            Prefs.putAeId(context, aeId);
                            return;
                        default:
                            // Error, don't save.
                            return;
                    }
                } else {
                    // Possible network or other problem?
                    return;
                }
            }
            // No exception, so AE is valid.
            Prefs.putAeId(context, aeId);
        }
    }

    private String maybeCreateInstallationId() {
        String installationId = Prefs.getInstallationId(context);
        if (installationId == null) {
            installationId = UUID.randomUUID().toString();
            Prefs.putInstallationId(context, installationId);
        }
        return installationId;
    }

}
