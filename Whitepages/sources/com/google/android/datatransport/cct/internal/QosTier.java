package com.google.android.datatransport.cct.internal;

import android.util.SparseArray;

public enum QosTier {
    DEFAULT(0),
    UNMETERED_ONLY(1),
    UNMETERED_OR_DAILY(2),
    FAST_IF_RADIO_AWAKE(3),
    NEVER(4),
    UNRECOGNIZED(-1);
    
    private static final SparseArray valueMap = null;
    private final int value;

    static {
        QosTier qosTier;
        QosTier qosTier2;
        QosTier qosTier3;
        QosTier qosTier4;
        QosTier qosTier5;
        QosTier qosTier6;
        SparseArray sparseArray = new SparseArray();
        valueMap = sparseArray;
        sparseArray.put(0, qosTier);
        sparseArray.put(1, qosTier2);
        sparseArray.put(2, qosTier3);
        sparseArray.put(3, qosTier4);
        sparseArray.put(4, qosTier5);
        sparseArray.put(-1, qosTier6);
    }

    private QosTier(int i) {
        this.value = i;
    }
}
