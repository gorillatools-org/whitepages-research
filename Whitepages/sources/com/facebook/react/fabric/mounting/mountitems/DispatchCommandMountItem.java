package com.facebook.react.fabric.mounting.mountitems;

public abstract class DispatchCommandMountItem implements MountItem {
    private int numRetries;

    public final void incrementRetries() {
        this.numRetries++;
    }

    public final int getRetries() {
        return this.numRetries;
    }
}
