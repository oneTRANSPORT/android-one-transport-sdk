package net.uk.onetransport.android.county.northants.events;

import android.content.Context;

import net.uk.onetransport.android.county.northants.generic.Retriever;

public class EventRetriever extends Retriever<Event> implements EventParams {

    public EventRetriever(Context context) {
        super(context);
    }

    @Override
    protected String getRetrievePrefix() {
        return RETRIEVE_PREFIX;
    }

    @Override
    protected Event[] fromJson(String content, String cinId, Long creationTime) {
        Event[] events = GSON.fromJson(content, Event[].class);
        for (Event event : events) {
            event.setCinId(cinId);
            event.setCreationTime(creationTime);
        }
        return events;
    }
}
