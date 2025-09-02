package com.salesforce.marketingcloud.messages.iam;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.salesforce.marketingcloud.InitializationStatus;
import com.salesforce.marketingcloud.MCKeep;
import com.salesforce.marketingcloud.UrlHandler;
import com.salesforce.marketingcloud.behaviors.b;
import com.salesforce.marketingcloud.behaviors.c;
import com.salesforce.marketingcloud.config.a;
import com.salesforce.marketingcloud.e;
import com.salesforce.marketingcloud.events.f;
import com.salesforce.marketingcloud.internal.l;
import com.salesforce.marketingcloud.k;
import com.salesforce.marketingcloud.media.o;
import com.salesforce.marketingcloud.messages.iam.InAppMessageManager;
import com.salesforce.marketingcloud.sfmcsdk.SFMCSdkComponents;
import com.salesforce.marketingcloud.sfmcsdk.components.behaviors.Behavior;
import com.salesforce.marketingcloud.sfmcsdk.components.behaviors.BehaviorListener;
import com.salesforce.marketingcloud.sfmcsdk.components.behaviors.BehaviorType;
import com.salesforce.marketingcloud.storage.j;
import java.util.Collection;
import java.util.EnumSet;
import org.json.JSONObject;

@SuppressLint({"UnknownNullness"})
@MCKeep
public class InAppMessageComponent implements e, InAppMessageManager, i, k.e, f, b, BehaviorListener {
    static final String EXTRA_MESSAGE_HANDLER = "messageHandler";
    private final com.salesforce.marketingcloud.alarms.b alarmScheduler;
    private final com.salesforce.marketingcloud.analytics.f analyticsListener;
    private final c behaviorManager;
    private a configComponent;
    private final Context context;
    private l executors;
    private o imageHandler;
    private final Handler messageDelayHandler;
    m realInAppMessageComponent;
    private final j storage;
    private final k syncRouteComponent;
    private SFMCSdkComponents uSdkComponents;
    private final UrlHandler urlHandler;

    public InAppMessageComponent(Context context2, j jVar, com.salesforce.marketingcloud.alarms.b bVar, k kVar, c cVar, o oVar, UrlHandler urlHandler2, l lVar, com.salesforce.marketingcloud.analytics.f fVar, a aVar) {
        this(context2, jVar, bVar, kVar, cVar, oVar, urlHandler2, lVar, fVar, (SFMCSdkComponents) null, aVar);
    }

    private void subscribeForBehaviours() {
        SFMCSdkComponents sFMCSdkComponents = this.uSdkComponents;
        if (sFMCSdkComponents != null) {
            sFMCSdkComponents.getBehaviorManager().registerForBehaviors(EnumSet.of(BehaviorType.APPLICATION_FOREGROUNDED, BehaviorType.APPLICATION_BACKGROUNDED), this);
        } else {
            this.behaviorManager.a(this, EnumSet.of(com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_APP_FOREGROUNDED, com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_APP_BACKGROUNDED));
        }
    }

    private void unSubscribeForBehaviours() {
        SFMCSdkComponents sFMCSdkComponents = this.uSdkComponents;
        if (sFMCSdkComponents != null) {
            sFMCSdkComponents.getBehaviorManager().unregisterForAllBehaviors(this);
        }
        c cVar = this.behaviorManager;
        if (cVar != null) {
            cVar.a((b) this);
        }
    }

    public boolean canDisplay(InAppMessage inAppMessage) {
        m mVar = this.realInAppMessageComponent;
        return mVar != null && mVar.canDisplay(inAppMessage);
    }

    public String componentName() {
        return "InAppMessageManager";
    }

    public JSONObject componentState() {
        m mVar = this.realInAppMessageComponent;
        if (mVar != null) {
            return mVar.a();
        }
        return null;
    }

    public void controlChannelInit(int i) {
        if (com.salesforce.marketingcloud.b.a(i, com.salesforce.marketingcloud.b.v)) {
            this.syncRouteComponent.a(k.d.inAppMessages, (k.e) null);
            unSubscribeForBehaviours();
            m mVar = this.realInAppMessageComponent;
            if (mVar != null) {
                mVar.b(com.salesforce.marketingcloud.b.c(i, com.salesforce.marketingcloud.b.v));
                this.realInAppMessageComponent = null;
                return;
            }
            return;
        }
        if (this.realInAppMessageComponent == null) {
            this.realInAppMessageComponent = new m(this.context, this.storage, this.alarmScheduler, this.imageHandler, this.urlHandler, this.executors, this.analyticsListener, this.messageDelayHandler, this.configComponent);
        }
        subscribeForBehaviours();
        this.syncRouteComponent.a(k.d.inAppMessages, (k.e) this);
    }

    public int getStatusBarColor() {
        m mVar = this.realInAppMessageComponent;
        if (mVar != null) {
            return mVar.getStatusBarColor();
        }
        return 0;
    }

    public Typeface getTypeface() {
        m mVar = this.realInAppMessageComponent;
        if (mVar != null) {
            return mVar.getTypeface();
        }
        return null;
    }

    public void handleMessageFinished(InAppMessage inAppMessage, j jVar) {
        m mVar = this.realInAppMessageComponent;
        if (mVar != null) {
            mVar.handleMessageFinished(inAppMessage, jVar);
        }
    }

    public void handleOutcomes(Collection<String> collection) {
        m mVar = this.realInAppMessageComponent;
        if (mVar != null) {
            mVar.handleOutcomes(collection);
        }
    }

    public o imageHandler() {
        m mVar = this.realInAppMessageComponent;
        return mVar != null ? mVar.imageHandler() : this.imageHandler;
    }

    public void init(InitializationStatus.a aVar, int i) {
        if (com.salesforce.marketingcloud.b.b(i, com.salesforce.marketingcloud.b.v)) {
            this.syncRouteComponent.a(k.d.inAppMessages, (k.e) this);
            this.realInAppMessageComponent = new m(this.context, this.storage, this.alarmScheduler, this.imageHandler, this.urlHandler, this.executors, this.analyticsListener, this.messageDelayHandler, this.configComponent);
            subscribeForBehaviours();
        }
    }

    public void onBehavior(com.salesforce.marketingcloud.behaviors.a aVar, Bundle bundle) {
        m mVar = this.realInAppMessageComponent;
        if (mVar == null) {
            return;
        }
        if (aVar == com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_APP_FOREGROUNDED) {
            mVar.b();
        } else if (aVar == com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_APP_BACKGROUNDED) {
            mVar.c();
        }
    }

    public void onSyncReceived(k.d dVar, JSONObject jSONObject) {
        m mVar = this.realInAppMessageComponent;
        if (mVar != null && dVar == k.d.inAppMessages) {
            mVar.a(jSONObject);
        }
    }

    public void setInAppMessageListener(InAppMessageManager.EventListener eventListener) {
        m mVar = this.realInAppMessageComponent;
        if (mVar != null) {
            mVar.setInAppMessageListener(eventListener);
        }
    }

    public void setStatusBarColor(int i) {
        m mVar = this.realInAppMessageComponent;
        if (mVar != null) {
            mVar.setStatusBarColor(i);
        }
    }

    public void setTypeface(Typeface typeface) {
        m mVar = this.realInAppMessageComponent;
        if (mVar != null) {
            mVar.setTypeface(typeface);
        }
    }

    /* access modifiers changed from: package-private */
    public void showMessage(InAppMessage inAppMessage) {
        m mVar = this.realInAppMessageComponent;
        if (mVar != null) {
            mVar.d(inAppMessage);
        }
    }

    public void tearDown(boolean z) {
        m mVar = this.realInAppMessageComponent;
        if (mVar != null) {
            mVar.b(false);
            this.realInAppMessageComponent = null;
        }
        k kVar = this.syncRouteComponent;
        if (kVar != null) {
            kVar.a(k.d.inAppMessages, (k.e) null);
        }
        unSubscribeForBehaviours();
    }

    public UrlHandler urlHandler() {
        m mVar = this.realInAppMessageComponent;
        return mVar != null ? mVar.urlHandler() : this.urlHandler;
    }

    public InAppMessageComponent(Context context2, j jVar, com.salesforce.marketingcloud.alarms.b bVar, k kVar, c cVar, o oVar, UrlHandler urlHandler2, l lVar, com.salesforce.marketingcloud.analytics.f fVar, SFMCSdkComponents sFMCSdkComponents, a aVar) {
        this.messageDelayHandler = new Handler(Looper.getMainLooper());
        this.context = context2;
        this.storage = jVar;
        this.alarmScheduler = bVar;
        this.syncRouteComponent = kVar;
        this.behaviorManager = cVar;
        this.imageHandler = oVar;
        this.urlHandler = urlHandler2;
        this.analyticsListener = fVar;
        this.executors = lVar;
        this.uSdkComponents = sFMCSdkComponents;
        this.configComponent = aVar;
    }

    public void onBehavior(Behavior behavior) {
        m mVar = this.realInAppMessageComponent;
        if (mVar == null) {
            return;
        }
        if (behavior instanceof Behavior.AppForegrounded) {
            mVar.b();
        } else if (behavior instanceof Behavior.AppBackgrounded) {
            mVar.c();
        }
    }

    public void showMessage(String str) {
        m mVar = this.realInAppMessageComponent;
        if (mVar != null) {
            mVar.showMessage(str);
        }
    }

    InAppMessageComponent(m mVar) {
        this((Context) null, (j) null, (com.salesforce.marketingcloud.alarms.b) null, (k) null, (c) null, (o) null, (UrlHandler) null, (l) null, (com.salesforce.marketingcloud.analytics.f) null, (a) null);
        this.realInAppMessageComponent = mVar;
    }
}
