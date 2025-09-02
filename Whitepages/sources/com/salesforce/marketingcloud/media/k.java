package com.salesforce.marketingcloud.media;

public class k extends IllegalStateException {
    final s a;

    k(s sVar) {
        super("Cannot handle request: " + sVar);
        this.a = sVar;
    }
}
