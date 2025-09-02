package com.airbnb.lottie.utils;

public class MeanCalculator {

    /* renamed from: n  reason: collision with root package name */
    private int f4n;
    private float sum;

    public void add(float f) {
        float f2 = this.sum + f;
        this.sum = f2;
        int i = this.f4n + 1;
        this.f4n = i;
        if (i == Integer.MAX_VALUE) {
            this.sum = f2 / 2.0f;
            this.f4n = i / 2;
        }
    }
}
