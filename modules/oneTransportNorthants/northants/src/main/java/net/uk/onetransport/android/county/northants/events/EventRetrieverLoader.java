package net.uk.onetransport.android.county.northants.events;

import android.content.Context;

import net.uk.onetransport.android.county.northants.generic.RetrieverLoader;

public class EventRetrieverLoader extends RetrieverLoader<Event> {

    public EventRetrieverLoader(Context context) {
        super(context, new EventRetriever(context));
    }
}
