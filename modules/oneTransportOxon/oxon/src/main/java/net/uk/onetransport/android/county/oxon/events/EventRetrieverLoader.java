package net.uk.onetransport.android.county.oxon.events;

import android.content.Context;

import net.uk.onetransport.android.county.oxon.generic.RetrieverLoader;

public class EventRetrieverLoader extends RetrieverLoader<Event> {

    public EventRetrieverLoader(Context context) {
        super(context, new EventRetriever(context));
    }
}
