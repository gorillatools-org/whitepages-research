package com.google.android.datatransport;

public abstract class Event {
    public abstract Integer getCode();

    public abstract EventContext getEventContext();

    public abstract Object getPayload();

    public abstract Priority getPriority();

    public abstract ProductData getProductData();

    public static Event ofData(Object obj, ProductData productData) {
        return new AutoValue_Event((Integer) null, obj, Priority.DEFAULT, productData, (EventContext) null);
    }

    public static Event ofData(Object obj) {
        return new AutoValue_Event((Integer) null, obj, Priority.DEFAULT, (ProductData) null, (EventContext) null);
    }

    public static Event ofUrgent(Object obj) {
        return new AutoValue_Event((Integer) null, obj, Priority.HIGHEST, (ProductData) null, (EventContext) null);
    }
}
