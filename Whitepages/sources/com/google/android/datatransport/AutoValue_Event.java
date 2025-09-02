package com.google.android.datatransport;

final class AutoValue_Event extends Event {
    private final Integer code;
    private final Object payload;
    private final Priority priority;
    private final ProductData productData;

    public EventContext getEventContext() {
        return null;
    }

    AutoValue_Event(Integer num, Object obj, Priority priority2, ProductData productData2, EventContext eventContext) {
        this.code = num;
        if (obj != null) {
            this.payload = obj;
            if (priority2 != null) {
                this.priority = priority2;
                this.productData = productData2;
                return;
            }
            throw new NullPointerException("Null priority");
        }
        throw new NullPointerException("Null payload");
    }

    public Integer getCode() {
        return this.code;
    }

    public Object getPayload() {
        return this.payload;
    }

    public Priority getPriority() {
        return this.priority;
    }

    public ProductData getProductData() {
        return this.productData;
    }

    public String toString() {
        return "Event{code=" + this.code + ", payload=" + this.payload + ", priority=" + this.priority + ", productData=" + this.productData + ", eventContext=" + null + "}";
    }

    public boolean equals(Object obj) {
        ProductData productData2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Event)) {
            return false;
        }
        Event event = (Event) obj;
        Integer num = this.code;
        if (num != null ? num.equals(event.getCode()) : event.getCode() == null) {
            if (this.payload.equals(event.getPayload()) && this.priority.equals(event.getPriority()) && ((productData2 = this.productData) != null ? productData2.equals(event.getProductData()) : event.getProductData() == null)) {
                event.getEventContext();
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        Integer num = this.code;
        int i = 0;
        int hashCode = ((((((num == null ? 0 : num.hashCode()) ^ 1000003) * 1000003) ^ this.payload.hashCode()) * 1000003) ^ this.priority.hashCode()) * 1000003;
        ProductData productData2 = this.productData;
        if (productData2 != null) {
            i = productData2.hashCode();
        }
        return (hashCode ^ i) * 1000003;
    }
}
