package com.FoodApp.FoodDelivery.GC.GoogleCalendar;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;

import java.util.Date;

public class GoogleCalendarService {

    private static final String APPLICATION_NAME = "Food Delivery Calendar";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    public void createEvent(String summary, Date startTime, Date endTime) throws Exception {
        Calendar calendar = new Calendar.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                null
        ).setApplicationName(APPLICATION_NAME).build();

        Event event = new Event()
                .setSummary(summary)
                .setStart(new EventDateTime().setDateTime(new com.google.api.client.util.DateTime(startTime)))
                .setEnd(new EventDateTime().setDateTime(new com.google.api.client.util.DateTime(endTime)));

        calendar.events().insert("primary", event).execute();
    }
}

