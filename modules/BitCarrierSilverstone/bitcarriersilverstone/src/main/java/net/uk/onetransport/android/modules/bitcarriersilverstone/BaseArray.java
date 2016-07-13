package net.uk.onetransport.android.modules.bitcarriersilverstone;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BaseArray {

    protected static final Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
}
