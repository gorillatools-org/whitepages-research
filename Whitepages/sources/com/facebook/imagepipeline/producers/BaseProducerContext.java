package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.ImmutableSet;
import com.facebook.hermes.intl.Constants;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.core.ImagePipelineConfigInterface;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BaseProducerContext implements ProducerContext {
    public static final Object CALLER_CONTEXT_UNSET = new Object();
    public static final Set INITIAL_KEYS = ImmutableSet.of("id", "uri_source");
    private final List mCallbacks;
    private final Object mCallerContext;
    private final Map mExtras;
    private final String mId;
    private final ImagePipelineConfigInterface mImagePipelineConfig;
    private final ImageRequest mImageRequest;
    private boolean mIsCancelled;
    private boolean mIsIntermediateResultExpected;
    private boolean mIsPrefetch;
    private final ImageRequest.RequestLevel mLowestPermittedRequestLevel;
    private Priority mPriority;
    private final ProducerListener2 mProducerListener;
    private final String mUiComponentId;

    public BaseProducerContext(ImageRequest imageRequest, String str, ProducerListener2 producerListener2, Object obj, ImageRequest.RequestLevel requestLevel, boolean z, boolean z2, Priority priority, ImagePipelineConfigInterface imagePipelineConfigInterface) {
        this(imageRequest, str, (String) null, (Map) null, producerListener2, obj, requestLevel, z, z2, priority, imagePipelineConfigInterface);
    }

    public BaseProducerContext(ImageRequest imageRequest, String str, String str2, Map map, ProducerListener2 producerListener2, Object obj, ImageRequest.RequestLevel requestLevel, boolean z, boolean z2, Priority priority, ImagePipelineConfigInterface imagePipelineConfigInterface) {
        this.mImageRequest = imageRequest;
        this.mId = str;
        HashMap hashMap = new HashMap();
        this.mExtras = hashMap;
        hashMap.put("id", str);
        hashMap.put("uri_source", imageRequest == null ? "null-request" : imageRequest.getSourceUri());
        putExtras(map);
        this.mUiComponentId = str2;
        this.mProducerListener = producerListener2;
        this.mCallerContext = obj == null ? CALLER_CONTEXT_UNSET : obj;
        this.mLowestPermittedRequestLevel = requestLevel;
        this.mIsPrefetch = z;
        this.mPriority = priority;
        this.mIsIntermediateResultExpected = z2;
        this.mIsCancelled = false;
        this.mCallbacks = new ArrayList();
        this.mImagePipelineConfig = imagePipelineConfigInterface;
    }

    public ImageRequest getImageRequest() {
        return this.mImageRequest;
    }

    public String getId() {
        return this.mId;
    }

    public String getUiComponentId() {
        return this.mUiComponentId;
    }

    public ProducerListener2 getProducerListener() {
        return this.mProducerListener;
    }

    public Object getCallerContext() {
        return this.mCallerContext;
    }

    public ImageRequest.RequestLevel getLowestPermittedRequestLevel() {
        return this.mLowestPermittedRequestLevel;
    }

    public synchronized boolean isPrefetch() {
        return this.mIsPrefetch;
    }

    public synchronized Priority getPriority() {
        return this.mPriority;
    }

    public synchronized boolean isIntermediateResultExpected() {
        return this.mIsIntermediateResultExpected;
    }

    public void addCallbacks(ProducerContextCallbacks producerContextCallbacks) {
        boolean z;
        synchronized (this) {
            this.mCallbacks.add(producerContextCallbacks);
            z = this.mIsCancelled;
        }
        if (z) {
            producerContextCallbacks.onCancellationRequested();
        }
    }

    public ImagePipelineConfigInterface getImagePipelineConfig() {
        return this.mImagePipelineConfig;
    }

    public void cancel() {
        callOnCancellationRequested(cancelNoCallbacks());
    }

    public synchronized List setIsPrefetchNoCallbacks(boolean z) {
        if (z == this.mIsPrefetch) {
            return null;
        }
        this.mIsPrefetch = z;
        return new ArrayList(this.mCallbacks);
    }

    public synchronized List setPriorityNoCallbacks(Priority priority) {
        if (priority == this.mPriority) {
            return null;
        }
        this.mPriority = priority;
        return new ArrayList(this.mCallbacks);
    }

    public synchronized List setIsIntermediateResultExpectedNoCallbacks(boolean z) {
        if (z == this.mIsIntermediateResultExpected) {
            return null;
        }
        this.mIsIntermediateResultExpected = z;
        return new ArrayList(this.mCallbacks);
    }

    public synchronized List cancelNoCallbacks() {
        if (this.mIsCancelled) {
            return null;
        }
        this.mIsCancelled = true;
        return new ArrayList(this.mCallbacks);
    }

    public static void callOnCancellationRequested(List list) {
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                ((ProducerContextCallbacks) it.next()).onCancellationRequested();
            }
        }
    }

    public static void callOnIsPrefetchChanged(List list) {
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                ((ProducerContextCallbacks) it.next()).onIsPrefetchChanged();
            }
        }
    }

    public static void callOnIsIntermediateResultExpectedChanged(List list) {
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                ((ProducerContextCallbacks) it.next()).onIsIntermediateResultExpectedChanged();
            }
        }
    }

    public static void callOnPriorityChanged(List list) {
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                ((ProducerContextCallbacks) it.next()).onPriorityChanged();
            }
        }
    }

    public void putExtra(String str, Object obj) {
        if (!INITIAL_KEYS.contains(str)) {
            this.mExtras.put(str, obj);
        }
    }

    public void putExtras(Map map) {
        if (map != null) {
            for (Map.Entry entry : map.entrySet()) {
                putExtra((String) entry.getKey(), entry.getValue());
            }
        }
    }

    public Object getExtra(String str) {
        return this.mExtras.get(str);
    }

    public Map getExtras() {
        return this.mExtras;
    }

    public void putOriginExtra(String str, String str2) {
        this.mExtras.put("origin", str);
        this.mExtras.put("origin_sub", str2);
    }

    public void putOriginExtra(String str) {
        putOriginExtra(str, Constants.COLLATION_DEFAULT);
    }
}
