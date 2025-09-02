package com.google.android.datatransport;

public interface Transport {
    void schedule(Event event, TransportScheduleCallback transportScheduleCallback);

    void send(Event event);
}
