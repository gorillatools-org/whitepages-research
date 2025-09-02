package org.greenrobot.eventbus;

public interface Poster {
    void enqueue(Subscription subscription, Object obj);
}
