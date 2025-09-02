package org.greenrobot.eventbus;

import java.util.ArrayList;
import java.util.List;

final class PendingPost {
    private static final List pendingPostPool = new ArrayList();
    Object event;
    PendingPost next;
    Subscription subscription;

    private PendingPost(Object obj, Subscription subscription2) {
        this.event = obj;
        this.subscription = subscription2;
    }

    static PendingPost obtainPendingPost(Subscription subscription2, Object obj) {
        List list = pendingPostPool;
        synchronized (list) {
            try {
                int size = list.size();
                if (size <= 0) {
                    return new PendingPost(obj, subscription2);
                }
                PendingPost pendingPost = (PendingPost) list.remove(size - 1);
                pendingPost.event = obj;
                pendingPost.subscription = subscription2;
                pendingPost.next = null;
                return pendingPost;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    static void releasePendingPost(PendingPost pendingPost) {
        pendingPost.event = null;
        pendingPost.subscription = null;
        pendingPost.next = null;
        List list = pendingPostPool;
        synchronized (list) {
            try {
                if (list.size() < 10000) {
                    list.add(pendingPost);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
