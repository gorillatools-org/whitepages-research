package com.google.android.datatransport;

public abstract class ProductData {
    public abstract Integer getProductId();

    public static ProductData withProductId(Integer num) {
        return new AutoValue_ProductData(num);
    }
}
