package org.greenrobot.eventbus;

final class PendingPostQueue {
    private PendingPost head;
    private PendingPost tail;

    PendingPostQueue() {
    }

    /* access modifiers changed from: package-private */
    public synchronized void enqueue(PendingPost pendingPost) {
        if (pendingPost != null) {
            try {
                PendingPost pendingPost2 = this.tail;
                if (pendingPost2 != null) {
                    pendingPost2.next = pendingPost;
                    this.tail = pendingPost;
                } else if (this.head == null) {
                    this.tail = pendingPost;
                    this.head = pendingPost;
                } else {
                    throw new IllegalStateException("Head present, but no tail");
                }
                notifyAll();
            } catch (Throwable th) {
                throw th;
            }
        } else {
            throw new NullPointerException("null cannot be enqueued");
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized PendingPost poll() {
        PendingPost pendingPost;
        pendingPost = this.head;
        if (pendingPost != null) {
            PendingPost pendingPost2 = pendingPost.next;
            this.head = pendingPost2;
            if (pendingPost2 == null) {
                this.tail = null;
            }
        }
        return pendingPost;
    }

    /* access modifiers changed from: package-private */
    public synchronized PendingPost poll(int i) {
        try {
            if (this.head == null) {
                wait((long) i);
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return poll();
    }
}
