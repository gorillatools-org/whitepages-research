package com.facebook;

import android.os.Handler;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class GraphRequestBatch extends AbstractList {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final AtomicInteger idGenerator = new AtomicInteger();
    private String batchApplicationId;
    private Handler callbackHandler;
    private List callbacks = new ArrayList();
    private final String id = String.valueOf(Integer.valueOf(idGenerator.incrementAndGet()));
    private List requests;
    private int timeoutInMilliseconds;

    public interface Callback {
        void onBatchCompleted(GraphRequestBatch graphRequestBatch);
    }

    public /* bridge */ boolean contains(GraphRequest graphRequest) {
        return super.contains(graphRequest);
    }

    public final /* bridge */ boolean contains(Object obj) {
        if (!(obj == null ? true : obj instanceof GraphRequest)) {
            return false;
        }
        return contains((GraphRequest) obj);
    }

    public /* bridge */ int indexOf(GraphRequest graphRequest) {
        return super.indexOf(graphRequest);
    }

    public final /* bridge */ int indexOf(Object obj) {
        if (!(obj == null ? true : obj instanceof GraphRequest)) {
            return -1;
        }
        return indexOf((GraphRequest) obj);
    }

    public /* bridge */ int lastIndexOf(GraphRequest graphRequest) {
        return super.lastIndexOf(graphRequest);
    }

    public final /* bridge */ int lastIndexOf(Object obj) {
        if (!(obj == null ? true : obj instanceof GraphRequest)) {
            return -1;
        }
        return lastIndexOf((GraphRequest) obj);
    }

    public final /* bridge */ GraphRequest remove(int i) {
        return removeAt(i);
    }

    public /* bridge */ boolean remove(GraphRequest graphRequest) {
        return super.remove(graphRequest);
    }

    public final /* bridge */ boolean remove(Object obj) {
        if (!(obj == null ? true : obj instanceof GraphRequest)) {
            return false;
        }
        return remove((GraphRequest) obj);
    }

    public final /* bridge */ int size() {
        return getSize();
    }

    public final Handler getCallbackHandler() {
        return this.callbackHandler;
    }

    public final void setCallbackHandler(Handler handler) {
        this.callbackHandler = handler;
    }

    public final String getId() {
        return this.id;
    }

    public final List getRequests() {
        return this.requests;
    }

    public final List getCallbacks() {
        return this.callbacks;
    }

    public final int getTimeout() {
        return this.timeoutInMilliseconds;
    }

    public final String getBatchApplicationId() {
        return this.batchApplicationId;
    }

    public GraphRequestBatch(Collection collection) {
        Intrinsics.checkNotNullParameter(collection, "requests");
        this.requests = new ArrayList(collection);
    }

    public GraphRequestBatch(GraphRequest... graphRequestArr) {
        Intrinsics.checkNotNullParameter(graphRequestArr, "requests");
        this.requests = new ArrayList(ArraysKt.asList(graphRequestArr));
    }

    public final void addCallback(Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (!this.callbacks.contains(callback)) {
            this.callbacks.add(callback);
        }
    }

    public boolean add(GraphRequest graphRequest) {
        Intrinsics.checkNotNullParameter(graphRequest, "element");
        return this.requests.add(graphRequest);
    }

    public void add(int i, GraphRequest graphRequest) {
        Intrinsics.checkNotNullParameter(graphRequest, "element");
        this.requests.add(i, graphRequest);
    }

    public void clear() {
        this.requests.clear();
    }

    public GraphRequest get(int i) {
        return (GraphRequest) this.requests.get(i);
    }

    public GraphRequest removeAt(int i) {
        return (GraphRequest) this.requests.remove(i);
    }

    public GraphRequest set(int i, GraphRequest graphRequest) {
        Intrinsics.checkNotNullParameter(graphRequest, "element");
        return (GraphRequest) this.requests.set(i, graphRequest);
    }

    public int getSize() {
        return this.requests.size();
    }

    public final List executeAndWait() {
        return executeAndWaitImpl();
    }

    public final GraphRequestAsyncTask executeAsync() {
        return executeAsyncImpl();
    }

    private final List executeAndWaitImpl() {
        return GraphRequest.Companion.executeBatchAndWait(this);
    }

    private final GraphRequestAsyncTask executeAsyncImpl() {
        return GraphRequest.Companion.executeBatchAsync(this);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
