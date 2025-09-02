package com.facebook.react.animated;

import android.util.SparseArray;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.JSApplicationCausedNativeException;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.EventDispatcherListener;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

public class NativeAnimatedNodesManager implements EventDispatcherListener {
    private static final String TAG = "NativeAnimatedNodesManager";
    private final SparseArray<AnimationDriver> mActiveAnimations = new SparseArray<>();
    private int mAnimatedGraphBFSColor = 0;
    private final SparseArray<AnimatedNode> mAnimatedNodes = new SparseArray<>();
    private final List<EventAnimationDriver> mEventDrivers = new ArrayList();
    private boolean mEventListenerInitializedForFabric = false;
    private boolean mEventListenerInitializedForNonFabric = false;
    private final ReactApplicationContext mReactApplicationContext;
    private final List<AnimatedNode> mRunUpdateNodeList = new LinkedList();
    private final SparseArray<AnimatedNode> mUpdatedNodes = new SparseArray<>();
    private boolean mWarnedAboutGraphTraversal = false;

    public NativeAnimatedNodesManager(ReactApplicationContext reactApplicationContext) {
        this.mReactApplicationContext = reactApplicationContext;
    }

    public void initializeEventListenerForUIManagerType(int i) {
        if (i == 2) {
            if (this.mEventListenerInitializedForFabric) {
                return;
            }
        } else if (this.mEventListenerInitializedForNonFabric) {
            return;
        }
        UIManager uIManager = UIManagerHelper.getUIManager(this.mReactApplicationContext, i);
        if (uIManager != null) {
            uIManager.getEventDispatcher().addListener(this);
            if (i == 2) {
                this.mEventListenerInitializedForFabric = true;
            } else {
                this.mEventListenerInitializedForNonFabric = true;
            }
        }
    }

    public AnimatedNode getNodeById(int i) {
        return this.mAnimatedNodes.get(i);
    }

    public boolean hasActiveAnimations() {
        return this.mActiveAnimations.size() > 0 || this.mUpdatedNodes.size() > 0;
    }

    public void createAnimatedNode(int i, ReadableMap readableMap) {
        AnimatedNode animatedNode;
        if (this.mAnimatedNodes.get(i) == null) {
            String string = readableMap.getString("type");
            if ("style".equals(string)) {
                animatedNode = new StyleAnimatedNode(readableMap, this);
            } else if ("value".equals(string)) {
                animatedNode = new ValueAnimatedNode(readableMap);
            } else if (ViewProps.COLOR.equals(string)) {
                animatedNode = new ColorAnimatedNode(readableMap, this, this.mReactApplicationContext);
            } else if ("props".equals(string)) {
                animatedNode = new PropsAnimatedNode(readableMap, this);
            } else if ("interpolation".equals(string)) {
                animatedNode = new InterpolationAnimatedNode(readableMap);
            } else if ("addition".equals(string)) {
                animatedNode = new AdditionAnimatedNode(readableMap, this);
            } else if ("subtraction".equals(string)) {
                animatedNode = new SubtractionAnimatedNode(readableMap, this);
            } else if ("division".equals(string)) {
                animatedNode = new DivisionAnimatedNode(readableMap, this);
            } else if ("multiplication".equals(string)) {
                animatedNode = new MultiplicationAnimatedNode(readableMap, this);
            } else if ("modulus".equals(string)) {
                animatedNode = new ModulusAnimatedNode(readableMap, this);
            } else if ("diffclamp".equals(string)) {
                animatedNode = new DiffClampAnimatedNode(readableMap, this);
            } else if (ViewProps.TRANSFORM.equals(string)) {
                animatedNode = new TransformAnimatedNode(readableMap, this);
            } else if ("tracking".equals(string)) {
                animatedNode = new TrackingAnimatedNode(readableMap, this);
            } else if ("object".equals(string)) {
                animatedNode = new ObjectAnimatedNode(readableMap, this);
            } else {
                throw new JSApplicationIllegalArgumentException("Unsupported node type: " + string);
            }
            animatedNode.tag = i;
            this.mAnimatedNodes.put(i, animatedNode);
            this.mUpdatedNodes.put(i, animatedNode);
            return;
        }
        throw new JSApplicationIllegalArgumentException("createAnimatedNode: Animated node [" + i + "] already exists");
    }

    public void updateAnimatedNodeConfig(int i, ReadableMap readableMap) {
        AnimatedNode animatedNode = this.mAnimatedNodes.get(i);
        if (animatedNode == null) {
            throw new JSApplicationIllegalArgumentException("updateAnimatedNode: Animated node [" + i + "] does not exist");
        } else if (animatedNode instanceof AnimatedNodeWithUpdateableConfig) {
            stopAnimationsForNode(animatedNode);
            ((AnimatedNodeWithUpdateableConfig) animatedNode).onUpdateConfig(readableMap);
            this.mUpdatedNodes.put(i, animatedNode);
        }
    }

    public void dropAnimatedNode(int i) {
        this.mAnimatedNodes.remove(i);
        this.mUpdatedNodes.remove(i);
    }

    public void startListeningToAnimatedNodeValue(int i, AnimatedNodeValueListener animatedNodeValueListener) {
        AnimatedNode animatedNode = this.mAnimatedNodes.get(i);
        if (animatedNode == null || !(animatedNode instanceof ValueAnimatedNode)) {
            throw new JSApplicationIllegalArgumentException("startListeningToAnimatedNodeValue: Animated node [" + i + "] does not exist, or is not a 'value' node");
        }
        ((ValueAnimatedNode) animatedNode).setValueListener(animatedNodeValueListener);
    }

    public void stopListeningToAnimatedNodeValue(int i) {
        AnimatedNode animatedNode = this.mAnimatedNodes.get(i);
        if (animatedNode == null || !(animatedNode instanceof ValueAnimatedNode)) {
            throw new JSApplicationIllegalArgumentException("startListeningToAnimatedNodeValue: Animated node [" + i + "] does not exist, or is not a 'value' node");
        }
        ((ValueAnimatedNode) animatedNode).setValueListener((AnimatedNodeValueListener) null);
    }

    public void setAnimatedNodeValue(int i, double d) {
        AnimatedNode animatedNode = this.mAnimatedNodes.get(i);
        if (animatedNode == null || !(animatedNode instanceof ValueAnimatedNode)) {
            throw new JSApplicationIllegalArgumentException("setAnimatedNodeValue: Animated node [" + i + "] does not exist, or is not a 'value' node");
        }
        stopAnimationsForNode(animatedNode);
        ((ValueAnimatedNode) animatedNode).nodeValue = d;
        this.mUpdatedNodes.put(i, animatedNode);
    }

    public void setAnimatedNodeOffset(int i, double d) {
        AnimatedNode animatedNode = this.mAnimatedNodes.get(i);
        if (animatedNode == null || !(animatedNode instanceof ValueAnimatedNode)) {
            throw new JSApplicationIllegalArgumentException("setAnimatedNodeOffset: Animated node [" + i + "] does not exist, or is not a 'value' node");
        }
        ((ValueAnimatedNode) animatedNode).offset = d;
        this.mUpdatedNodes.put(i, animatedNode);
    }

    public void flattenAnimatedNodeOffset(int i) {
        AnimatedNode animatedNode = this.mAnimatedNodes.get(i);
        if (animatedNode == null || !(animatedNode instanceof ValueAnimatedNode)) {
            throw new JSApplicationIllegalArgumentException("flattenAnimatedNodeOffset: Animated node [" + i + "] does not exist, or is not a 'value' node");
        }
        ((ValueAnimatedNode) animatedNode).flattenOffset();
    }

    public void extractAnimatedNodeOffset(int i) {
        AnimatedNode animatedNode = this.mAnimatedNodes.get(i);
        if (animatedNode == null || !(animatedNode instanceof ValueAnimatedNode)) {
            throw new JSApplicationIllegalArgumentException("extractAnimatedNodeOffset: Animated node [" + i + "] does not exist, or is not a 'value' node");
        }
        ((ValueAnimatedNode) animatedNode).extractOffset();
    }

    public void startAnimatingNode(int i, int i2, ReadableMap readableMap, Callback callback) {
        AnimationDriver animationDriver;
        AnimatedNode animatedNode = this.mAnimatedNodes.get(i2);
        if (animatedNode == null) {
            throw new JSApplicationIllegalArgumentException("startAnimatingNode: Animated node [" + i2 + "] does not exist");
        } else if (animatedNode instanceof ValueAnimatedNode) {
            AnimationDriver animationDriver2 = this.mActiveAnimations.get(i);
            if (animationDriver2 != null) {
                animationDriver2.resetConfig(readableMap);
                return;
            }
            String string = readableMap.getString("type");
            if ("frames".equals(string)) {
                animationDriver = new FrameBasedAnimationDriver(readableMap);
            } else if ("spring".equals(string)) {
                animationDriver = new SpringAnimation(readableMap);
            } else if ("decay".equals(string)) {
                animationDriver = new DecayAnimation(readableMap);
            } else {
                throw new JSApplicationIllegalArgumentException("startAnimatingNode: Unsupported animation type [" + i2 + "]: " + string);
            }
            animationDriver.id = i;
            animationDriver.endCallback = callback;
            animationDriver.animatedValue = (ValueAnimatedNode) animatedNode;
            this.mActiveAnimations.put(i, animationDriver);
        } else {
            throw new JSApplicationIllegalArgumentException("startAnimatingNode: Animated node [" + i2 + "] should be of type " + ValueAnimatedNode.class.getName());
        }
    }

    private void stopAnimationsForNode(AnimatedNode animatedNode) {
        WritableArray writableArray = null;
        int i = 0;
        while (i < this.mActiveAnimations.size()) {
            AnimationDriver valueAt = this.mActiveAnimations.valueAt(i);
            if (animatedNode.equals(valueAt.animatedValue)) {
                if (valueAt.endCallback != null) {
                    WritableMap createMap = Arguments.createMap();
                    createMap.putBoolean("finished", false);
                    createMap.putDouble("value", valueAt.animatedValue.nodeValue);
                    valueAt.endCallback.invoke(createMap);
                } else if (this.mReactApplicationContext != null) {
                    WritableMap createMap2 = Arguments.createMap();
                    createMap2.putInt("animationId", valueAt.id);
                    createMap2.putBoolean("finished", false);
                    createMap2.putDouble("value", valueAt.animatedValue.nodeValue);
                    if (writableArray == null) {
                        writableArray = Arguments.createArray();
                    }
                    writableArray.pushMap(createMap2);
                }
                this.mActiveAnimations.removeAt(i);
                i--;
            }
            i++;
        }
        if (writableArray != null) {
            this.mReactApplicationContext.emitDeviceEvent("onNativeAnimatedModuleAnimationFinished", writableArray);
        }
    }

    public void stopAnimation(int i) {
        WritableArray writableArray;
        int i2 = 0;
        while (true) {
            writableArray = null;
            if (i2 >= this.mActiveAnimations.size()) {
                break;
            }
            AnimationDriver valueAt = this.mActiveAnimations.valueAt(i2);
            if (valueAt.id == i) {
                if (valueAt.endCallback != null) {
                    WritableMap createMap = Arguments.createMap();
                    createMap.putBoolean("finished", false);
                    createMap.putDouble("value", valueAt.animatedValue.nodeValue);
                    valueAt.endCallback.invoke(createMap);
                } else if (this.mReactApplicationContext != null) {
                    WritableMap createMap2 = Arguments.createMap();
                    createMap2.putInt("animationId", valueAt.id);
                    createMap2.putBoolean("finished", false);
                    createMap2.putDouble("value", valueAt.animatedValue.nodeValue);
                    writableArray = Arguments.createArray();
                    writableArray.pushMap(createMap2);
                }
                this.mActiveAnimations.removeAt(i2);
            } else {
                i2++;
            }
        }
        if (writableArray != null) {
            this.mReactApplicationContext.emitDeviceEvent("onNativeAnimatedModuleAnimationFinished", writableArray);
        }
    }

    public void connectAnimatedNodes(int i, int i2) {
        AnimatedNode animatedNode = this.mAnimatedNodes.get(i);
        if (animatedNode != null) {
            AnimatedNode animatedNode2 = this.mAnimatedNodes.get(i2);
            if (animatedNode2 != null) {
                animatedNode.addChild(animatedNode2);
                this.mUpdatedNodes.put(i2, animatedNode2);
                return;
            }
            throw new JSApplicationIllegalArgumentException("connectAnimatedNodes: Animated node with tag (child) [" + i2 + "] does not exist");
        }
        throw new JSApplicationIllegalArgumentException("connectAnimatedNodes: Animated node with tag (parent) [" + i + "] does not exist");
    }

    public void disconnectAnimatedNodes(int i, int i2) {
        AnimatedNode animatedNode = this.mAnimatedNodes.get(i);
        if (animatedNode != null) {
            AnimatedNode animatedNode2 = this.mAnimatedNodes.get(i2);
            if (animatedNode2 != null) {
                animatedNode.removeChild(animatedNode2);
                this.mUpdatedNodes.put(i2, animatedNode2);
                return;
            }
            throw new JSApplicationIllegalArgumentException("disconnectAnimatedNodes: Animated node with tag (child) [" + i2 + "] does not exist");
        }
        throw new JSApplicationIllegalArgumentException("disconnectAnimatedNodes: Animated node with tag (parent) [" + i + "] does not exist");
    }

    public void connectAnimatedNodeToView(int i, int i2) {
        AnimatedNode animatedNode = this.mAnimatedNodes.get(i);
        if (animatedNode == null) {
            throw new JSApplicationIllegalArgumentException("connectAnimatedNodeToView: Animated node with tag [" + i + "] does not exist");
        } else if (animatedNode instanceof PropsAnimatedNode) {
            ReactApplicationContext reactApplicationContext = this.mReactApplicationContext;
            if (reactApplicationContext != null) {
                UIManager uIManagerForReactTag = UIManagerHelper.getUIManagerForReactTag(reactApplicationContext, i2);
                if (uIManagerForReactTag == null) {
                    ReactSoftExceptionLogger.logSoftException(TAG, new ReactNoCrashSoftException("connectAnimatedNodeToView: Animated node could not be connected to UIManager - uiManager disappeared for tag: " + i2));
                    return;
                }
                ((PropsAnimatedNode) animatedNode).connectToView(i2, uIManagerForReactTag);
                this.mUpdatedNodes.put(i, animatedNode);
                return;
            }
            throw new IllegalStateException("connectAnimatedNodeToView: Animated node could not be connected, no ReactApplicationContext: " + i2);
        } else {
            throw new JSApplicationIllegalArgumentException("connectAnimatedNodeToView: Animated node connected to view [" + i2 + "] should be of type " + PropsAnimatedNode.class.getName());
        }
    }

    public void disconnectAnimatedNodeFromView(int i, int i2) {
        AnimatedNode animatedNode = this.mAnimatedNodes.get(i);
        if (animatedNode == null) {
            throw new JSApplicationIllegalArgumentException("disconnectAnimatedNodeFromView: Animated node with tag [" + i + "] does not exist");
        } else if (animatedNode instanceof PropsAnimatedNode) {
            ((PropsAnimatedNode) animatedNode).disconnectFromView(i2);
        } else {
            throw new JSApplicationIllegalArgumentException("disconnectAnimatedNodeFromView: Animated node connected to view [" + i2 + "] should be of type " + PropsAnimatedNode.class.getName());
        }
    }

    public void getValue(int i, Callback callback) {
        AnimatedNode animatedNode = this.mAnimatedNodes.get(i);
        if (animatedNode == null || !(animatedNode instanceof ValueAnimatedNode)) {
            throw new JSApplicationIllegalArgumentException("getValue: Animated node with tag [" + i + "] does not exist or is not a 'value' node");
        }
        double value = ((ValueAnimatedNode) animatedNode).getValue();
        if (callback != null) {
            callback.invoke(Double.valueOf(value));
        } else if (this.mReactApplicationContext != null) {
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("tag", i);
            createMap.putDouble("value", value);
            this.mReactApplicationContext.emitDeviceEvent("onNativeAnimatedModuleGetValue", createMap);
        }
    }

    public void restoreDefaultValues(int i) {
        AnimatedNode animatedNode = this.mAnimatedNodes.get(i);
        if (animatedNode != null) {
            if (animatedNode instanceof PropsAnimatedNode) {
                ((PropsAnimatedNode) animatedNode).restoreDefaultValues();
                return;
            }
            throw new JSApplicationIllegalArgumentException("Animated node connected to view [?] should be of type " + PropsAnimatedNode.class.getName());
        }
    }

    public void addAnimatedEventToView(int i, String str, ReadableMap readableMap) {
        int i2 = readableMap.getInt("animatedValueTag");
        AnimatedNode animatedNode = this.mAnimatedNodes.get(i2);
        if (animatedNode == null) {
            throw new JSApplicationIllegalArgumentException("addAnimatedEventToView: Animated node with tag [" + i2 + "] does not exist");
        } else if (animatedNode instanceof ValueAnimatedNode) {
            ReadableArray array = readableMap.getArray("nativeEventPath");
            ArrayList arrayList = new ArrayList(array.size());
            for (int i3 = 0; i3 < array.size(); i3++) {
                arrayList.add(array.getString(i3));
            }
            String normalizeEventName = normalizeEventName(str);
            this.mEventDrivers.add(new EventAnimationDriver(normalizeEventName, i, arrayList, (ValueAnimatedNode) animatedNode));
            if (normalizeEventName.equals("topScroll")) {
                addAnimatedEventToView(i, "topScrollEnded", readableMap);
            }
        } else {
            throw new JSApplicationIllegalArgumentException("addAnimatedEventToView: Animated node on view [" + i + "] connected to event handler (" + str + ") should be of type " + ValueAnimatedNode.class.getName());
        }
    }

    public void removeAnimatedEventFromView(int i, String str, int i2) {
        String normalizeEventName = normalizeEventName(str);
        ListIterator<EventAnimationDriver> listIterator = this.mEventDrivers.listIterator();
        while (true) {
            if (!listIterator.hasNext()) {
                break;
            }
            EventAnimationDriver next = listIterator.next();
            if (normalizeEventName.equals(next.eventName) && i == next.viewTag && i2 == next.valueNode.tag) {
                listIterator.remove();
                break;
            }
        }
        if (normalizeEventName.equals("topScroll")) {
            removeAnimatedEventFromView(i, "topScrollEnded", i2);
        }
    }

    public void onEventDispatch(final Event event) {
        if (UiThreadUtil.isOnUiThread()) {
            handleEvent(event);
        } else {
            UiThreadUtil.runOnUiThread(new Runnable() {
                public void run() {
                    NativeAnimatedNodesManager.this.handleEvent(event);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void handleEvent(Event event) {
        if (!this.mEventDrivers.isEmpty()) {
            Event.EventAnimationDriverMatchSpec eventAnimationDriverMatchSpec = event.getEventAnimationDriverMatchSpec();
            boolean z = false;
            for (EventAnimationDriver next : this.mEventDrivers) {
                if (eventAnimationDriverMatchSpec.match(next.viewTag, next.eventName)) {
                    stopAnimationsForNode(next.valueNode);
                    event.dispatchModern(next);
                    this.mRunUpdateNodeList.add(next.valueNode);
                    z = true;
                }
            }
            if (z) {
                updateNodes(this.mRunUpdateNodeList);
                this.mRunUpdateNodeList.clear();
            }
        }
    }

    public void runUpdates(long j) {
        UiThreadUtil.assertOnUiThread();
        for (int i = 0; i < this.mUpdatedNodes.size(); i++) {
            this.mRunUpdateNodeList.add(this.mUpdatedNodes.valueAt(i));
        }
        this.mUpdatedNodes.clear();
        boolean z = false;
        for (int i2 = 0; i2 < this.mActiveAnimations.size(); i2++) {
            AnimationDriver valueAt = this.mActiveAnimations.valueAt(i2);
            valueAt.runAnimationStep(j);
            this.mRunUpdateNodeList.add(valueAt.animatedValue);
            if (valueAt.hasFinished) {
                z = true;
            }
        }
        updateNodes(this.mRunUpdateNodeList);
        this.mRunUpdateNodeList.clear();
        if (z) {
            WritableArray writableArray = null;
            for (int size = this.mActiveAnimations.size() - 1; size >= 0; size--) {
                AnimationDriver valueAt2 = this.mActiveAnimations.valueAt(size);
                if (valueAt2.hasFinished) {
                    if (valueAt2.endCallback != null) {
                        WritableMap createMap = Arguments.createMap();
                        createMap.putBoolean("finished", true);
                        createMap.putDouble("value", valueAt2.animatedValue.nodeValue);
                        valueAt2.endCallback.invoke(createMap);
                    } else if (this.mReactApplicationContext != null) {
                        WritableMap createMap2 = Arguments.createMap();
                        createMap2.putInt("animationId", valueAt2.id);
                        createMap2.putBoolean("finished", true);
                        createMap2.putDouble("value", valueAt2.animatedValue.nodeValue);
                        if (writableArray == null) {
                            writableArray = Arguments.createArray();
                        }
                        writableArray.pushMap(createMap2);
                    }
                    this.mActiveAnimations.removeAt(size);
                }
            }
            if (writableArray != null) {
                this.mReactApplicationContext.emitDeviceEvent("onNativeAnimatedModuleAnimationFinished", writableArray);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Set<Integer> getTagsOfConnectedNodes(int i, String str) {
        int i2;
        List<AnimatedNode> list;
        HashSet hashSet = new HashSet();
        ListIterator<EventAnimationDriver> listIterator = this.mEventDrivers.listIterator();
        while (listIterator.hasNext()) {
            EventAnimationDriver next = listIterator.next();
            if (next != null && str.equals(next.eventName) && i == (i2 = next.viewTag)) {
                hashSet.add(Integer.valueOf(i2));
                ValueAnimatedNode valueAnimatedNode = next.valueNode;
                if (!(valueAnimatedNode == null || (list = valueAnimatedNode.children) == null)) {
                    for (AnimatedNode animatedNode : list) {
                        hashSet.add(Integer.valueOf(animatedNode.tag));
                    }
                }
            }
        }
        return hashSet;
    }

    private void updateNodes(List<AnimatedNode> list) {
        String str;
        int i;
        int i2 = this.mAnimatedGraphBFSColor;
        int i3 = i2 + 1;
        this.mAnimatedGraphBFSColor = i3;
        if (i3 == 0) {
            this.mAnimatedGraphBFSColor = i2 + 2;
        }
        ArrayDeque arrayDeque = new ArrayDeque();
        int i4 = 0;
        for (AnimatedNode next : list) {
            int i5 = next.BFSColor;
            int i6 = this.mAnimatedGraphBFSColor;
            if (i5 != i6) {
                next.BFSColor = i6;
                i4++;
                arrayDeque.add(next);
            }
        }
        while (!arrayDeque.isEmpty()) {
            AnimatedNode animatedNode = (AnimatedNode) arrayDeque.poll();
            if (animatedNode.children != null) {
                for (int i7 = 0; i7 < animatedNode.children.size(); i7++) {
                    AnimatedNode animatedNode2 = animatedNode.children.get(i7);
                    animatedNode2.activeIncomingNodes++;
                    int i8 = animatedNode2.BFSColor;
                    int i9 = this.mAnimatedGraphBFSColor;
                    if (i8 != i9) {
                        animatedNode2.BFSColor = i9;
                        i4++;
                        arrayDeque.add(animatedNode2);
                    }
                }
            }
        }
        int i10 = this.mAnimatedGraphBFSColor;
        int i11 = i10 + 1;
        this.mAnimatedGraphBFSColor = i11;
        if (i11 == 0) {
            this.mAnimatedGraphBFSColor = i10 + 2;
        }
        int i12 = 0;
        for (AnimatedNode next2 : list) {
            if (next2.activeIncomingNodes == 0 && next2.BFSColor != (i = this.mAnimatedGraphBFSColor)) {
                next2.BFSColor = i;
                i12++;
                arrayDeque.add(next2);
            }
        }
        int i13 = 0;
        while (!arrayDeque.isEmpty()) {
            AnimatedNode animatedNode3 = (AnimatedNode) arrayDeque.poll();
            try {
                animatedNode3.update();
                if (animatedNode3 instanceof PropsAnimatedNode) {
                    ((PropsAnimatedNode) animatedNode3).updateView();
                }
            } catch (JSApplicationCausedNativeException e) {
                FLog.e(TAG, "Native animation workaround, frame lost as result of race condition", (Throwable) e);
            }
            if (animatedNode3 instanceof ValueAnimatedNode) {
                ((ValueAnimatedNode) animatedNode3).onValueUpdate();
            }
            if (animatedNode3.children != null) {
                for (int i14 = 0; i14 < animatedNode3.children.size(); i14++) {
                    AnimatedNode animatedNode4 = animatedNode3.children.get(i14);
                    int i15 = animatedNode4.activeIncomingNodes - 1;
                    animatedNode4.activeIncomingNodes = i15;
                    int i16 = animatedNode4.BFSColor;
                    int i17 = this.mAnimatedGraphBFSColor;
                    if (i16 != i17 && i15 == 0) {
                        animatedNode4.BFSColor = i17;
                        i12++;
                        arrayDeque.add(animatedNode4);
                    } else if (i16 == i17) {
                        i13++;
                    }
                }
            }
        }
        if (i4 == i12) {
            this.mWarnedAboutGraphTraversal = false;
        } else if (!this.mWarnedAboutGraphTraversal) {
            this.mWarnedAboutGraphTraversal = true;
            FLog.e(TAG, "Detected animation cycle or disconnected graph. ");
            for (AnimatedNode prettyPrintWithChildren : list) {
                FLog.e(TAG, prettyPrintWithChildren.prettyPrintWithChildren());
            }
            if (i13 > 0) {
                str = "cycles (" + i13 + ")";
            } else {
                str = "disconnected regions";
            }
            IllegalStateException illegalStateException = new IllegalStateException("Looks like animated nodes graph has " + str + ", there are " + i4 + " but toposort visited only " + i12);
            boolean z = this.mEventListenerInitializedForFabric;
            if (z && i13 == 0) {
                ReactSoftExceptionLogger.logSoftException(TAG, new ReactNoCrashSoftException((Throwable) illegalStateException));
            } else if (z) {
                ReactSoftExceptionLogger.logSoftException(TAG, new ReactNoCrashSoftException((Throwable) illegalStateException));
            } else {
                throw illegalStateException;
            }
        }
    }

    private String normalizeEventName(String str) {
        if (!str.startsWith(ViewProps.ON)) {
            return str;
        }
        return ViewProps.TOP + str.substring(2);
    }
}
