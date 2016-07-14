package net.uk.onetransport.android.modules.bitcarriersilverstone.data.sketch;

import android.content.Context;

import com.interdigital.android.dougal.resource.Container;
import com.interdigital.android.dougal.resource.ContentInstance;
import com.interdigital.android.dougal.resource.Resource;
import com.interdigital.android.dougal.resource.callback.DougalCallback;

import net.uk.onetransport.android.modules.bitcarriersilverstone.generic.BaseArray;
import net.uk.onetransport.android.modules.bitcarriersilverstone.R;
import net.uk.onetransport.android.modules.bitcarriersilverstone.authentication.CredentialHelper;

public class SketchArray extends BaseArray implements DougalCallback {

    private static final int[] SKETCH_IDS = {122, 128, 133, 134, 145, 146, 147, 150, 151, 152, 153, 154, 156,
            157, 161, 165, 175, 176, 183, 193, 195, 196};
    private static final String AE_NAME = "Worldsensing";
    private static final String RETRIEVE_PREFIX = AE_NAME
            + "/BitCarrier/v1.0/InterdigitalDemo/installationtest/data/sketches/s";

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
        Sketch[] newSketches = new Sketch[SKETCH_IDS.length];
        for (int i = 0; i < SKETCH_IDS.length; i++) {
            ContentInstance contentInstance = Container.retrieveLatest(aeId, cseBaseUrl,
                    RETRIEVE_PREFIX + String.valueOf(SKETCH_IDS[i]), userName, password);
            String content = contentInstance.getContent();
            newSketches[i] = GSON.fromJson(content, Sketch.class);
        }
        return new SketchArray(newSketches);
    }

    public static void getSketchArrayAsync(Context context,
                                           SketchArrayCallback sketchArrayCallback, int id) {
        SketchArray sketchArray = new SketchArray();
        sketchArray.sketches = new Sketch[SKETCH_IDS.length];
        sketchArray.sketchArrayCallback = sketchArrayCallback;
        sketchArray.id = id;
        String aeId = "C-" + CredentialHelper.getAeId(context);
        String userName = CredentialHelper.getAeId(context);
        String password = CredentialHelper.getSessionToken(context);
        String cseBaseUrl = context.getString(R.string.bitcarrier_cse_base_url);
        for (int i = 0; i < SKETCH_IDS.length; i++) {
            Container.retrieveLatestAsync(aeId, cseBaseUrl,
                    RETRIEVE_PREFIX + String.valueOf(SKETCH_IDS[i]), userName, password,
                    sketchArray);
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
            } catch (Exception e) {
                sketchArrayCallback.onSketchArrayError(id, e);
            }
            completed++;
            if (completed == SKETCH_IDS.length) {
                sketchArrayCallback.onSketchArrayReady(id, this);
            }
        }
    }

    public Sketch[] getSketches() {
        return sketches;
    }
}
