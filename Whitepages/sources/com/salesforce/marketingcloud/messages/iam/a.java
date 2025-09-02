package com.salesforce.marketingcloud.messages.iam;

import android.os.CountDownTimer;

abstract class a extends CountDownTimer {
    private long a;
    private long b;

    a(long j, long j2) {
        super(j - j2, 500);
        this.b = j;
    }

    public long a() {
        return this.b - this.a;
    }

    public void onFinish() {
        this.a = 0;
    }

    public final void onTick(long j) {
        this.a = j;
    }
}
