package net.uk.onetransport.android.modules.bitcarriersilverstone.config.sketch;

import android.content.Context;

import com.interdigital.android.dougal.resource.Container;
import com.interdigital.android.dougal.resource.ContentInstance;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.bitcarriersilverstone.BaseArray;
import net.uk.onetransport.android.modules.bitcarriersilverstone.R;
import net.uk.onetransport.android.modules.bitcarriersilverstone.authentication.CredentialHelper;

public class SketchArray extends BaseArray implements DougalCallback {

    public static final int START_SKETCH_ID = 1;
    public static final int END_SKETCH_ID = 46;
    public static final int NUM_SKETCHES = END_SKETCH_ID - START_SKETCH_ID + 1;
    public static final String AE_NAME = "Worldsensing";
    private static final String RETRIEVE_PREFIX = AE_NAME
            + "/BitCarrier/v1.0/InterdigitalDemo/installationtest/silverstone/config/sketches/s";

    private static int completed = 0;

    private Sketch[] sketches;
    private SketchArrayCallback sketchArrayCallback;
    private int id; // TODO    Move to superclass?

    private SketchArray() {
    }

    public SketchArray(Sketch[] sketches) {
        this.sketches = sketches;
    }

    public static SketchArray getSketchArray(Context context) throws Exception {
        String aeId = "C-" + CredentialHelper.getAeId(context);
        String userName = CredentialHelper.getAeId(context);
        String password = CredentialHelper.getSessionToken(context);
        String cseBaseUrl = context.getString(R.string.bitcarrier_cse_base_url);
        Sketch[] newSketches = new Sketch[NUM_SKETCHES];
        for (int i = START_SKETCH_ID; i <= END_SKETCH_ID; i++) {
            ContentInstance contentInstance = Container.retrieveLatest(aeId, cseBaseUrl,
                    RETRIEVE_PREFIX + String.valueOf(i), userName, password);
            String content = contentInstance.getContent();
            newSketches[i] = GSON.fromJson(content, Sketch.class);
            newSketches[i].setPositions(
                    GSON.fromJson(newSketches[i].getLocationJsonArray(), Position[].class));
        }
        return new SketchArray(newSketches);
    }

    public static void getSketchArrayAsync(Context context,
                                           SketchArrayCallback sketchArrayCallback, int id) {
        SketchArray sketchArray = new SketchArray();
        sketchArray.sketches = new Sketch[NUM_SKETCHES];
        sketchArray.sketchArrayCallback = sketchArrayCallback;
        sketchArray.id = id;
        String aeId = "C-" + CredentialHelper.getAeId(context);
        String userName = CredentialHelper.getAeId(context);
        String password = CredentialHelper.getSessionToken(context);
        String cseBaseUrl = context.getString(R.string.bitcarrier_cse_base_url);
        for (int i = START_SKETCH_ID; i <= END_SKETCH_ID; i++) {
            Container.retrieveLatestAsync(aeId, cseBaseUrl,
                    RETRIEVE_PREFIX + String.valueOf(i), userName, password, sketchArray);
        }
    }

    @Override
    public void getResponse(Resource resource, Throwable throwable) {
        // TODO    Should we really only call this once?  Follow the Bucks pattern for now.
        if (throwable != null) {
            sketchArrayCallback.onSketchArrayError(id, throwable);
        } else {
            try {
                String content = ((ContentInstance) resource).getContent();
                // TODO    There will be holes if there is a download error.
                sketches[completed] = GSON.fromJson(content, Sketch.class);
                sketches[completed].setPositions(
                        GSON.fromJson(sketches[completed].getLocationJsonArray(), Position[].class));
            } catch (Exception e) {
                sketchArrayCallback.onSketchArrayError(id, e);
            }
            completed++;
            if (completed == NUM_SKETCHES) {
                sketchArrayCallback.onSketchArrayReady(id, this);
            }
        }
    }

    public Sketch[] getSketches() {
        return sketches;
    }
}
