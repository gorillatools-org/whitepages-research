package com.salesforce.marketingcloud.sfmcsdk.modules;

import android.content.Context;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.salesforce.marketingcloud.sfmcsdk.InitializationState;
import com.salesforce.marketingcloud.sfmcsdk.SFMCSdkComponents;
import com.salesforce.marketingcloud.sfmcsdk.components.identity.ModuleIdentity;
import com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger;
import com.salesforce.marketingcloud.sfmcsdk.modules.push.PushModule;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import kotlin.NotImplementedError;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

public abstract class Module {
    private final List<ModuleReadyHandler> MODULE_INSTANCE_REQUESTS = new ArrayList();
    private InitializationState initializationState = InitializationState.NONE;
    private ModuleInterface module;

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[InitializationState.values().length];
            iArr[InitializationState.READY.ordinal()] = 1;
            iArr[InitializationState.NONE.ordinal()] = 2;
            iArr[InitializationState.ERROR.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    protected static /* synthetic */ void getMODULE_INSTANCE_REQUESTS$annotations() {
    }

    public abstract String getName();

    /* access modifiers changed from: protected */
    public final List<ModuleReadyHandler> getMODULE_INSTANCE_REQUESTS() {
        return this.MODULE_INSTANCE_REQUESTS;
    }

    /* access modifiers changed from: protected */
    public final InitializationState getInitializationState() {
        return this.initializationState;
    }

    /* access modifiers changed from: protected */
    public final void setInitializationState(InitializationState initializationState2) {
        Intrinsics.checkNotNullParameter(initializationState2, "<set-?>");
        this.initializationState = initializationState2;
    }

    /* access modifiers changed from: protected */
    public final ModuleInterface getModule() {
        return this.module;
    }

    /* access modifiers changed from: protected */
    public final void setModule(ModuleInterface moduleInterface) {
        this.module = moduleInterface;
    }

    public final void requestModule(ModuleReadyListener moduleReadyListener) {
        Intrinsics.checkNotNullParameter(moduleReadyListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        ModuleReadyHandler moduleReadyHandler = new ModuleReadyHandler(moduleReadyListener);
        synchronized (this.MODULE_INSTANCE_REQUESTS) {
            if (WhenMappings.$EnumSwitchMapping$0[this.initializationState.ordinal()] == 1) {
                try {
                    ModuleInterface moduleInterface = this.module;
                    if (moduleInterface != null) {
                        moduleReadyHandler.deliverModule(moduleInterface);
                        Unit unit = Unit.INSTANCE;
                    }
                } catch (Exception e) {
                    SFMCSdkLogger.INSTANCE.e(PushModule.TAG, e, new Module$requestModule$1$2(moduleReadyHandler));
                    Unit unit2 = Unit.INSTANCE;
                }
            } else {
                this.MODULE_INSTANCE_REQUESTS.add(moduleReadyHandler);
            }
        }
    }

    public final void tearDown() {
        this.MODULE_INSTANCE_REQUESTS.clear();
        this.module = null;
        this.initializationState = InitializationState.NONE;
    }

    public final ModuleIdentity getIdentity() {
        ModuleIdentity moduleIdentity;
        ModuleInterface moduleInterface = this.module;
        if (moduleInterface != null && (moduleIdentity = moduleInterface.getModuleIdentity()) != null) {
            return moduleIdentity;
        }
        throw new NotImplementedError("An operation is not implemented: " + "Your module must implement getIdentity().");
    }

    public final JSONObject getState() {
        JSONObject jSONObject;
        ModuleInterface moduleInterface = this.module;
        if (moduleInterface == null || (jSONObject = moduleInterface.getState()) == null) {
            JSONObject jSONObject2 = new JSONObject();
            int i = WhenMappings.$EnumSwitchMapping$0[this.initializationState.ordinal()];
            if (i == 2) {
                jSONObject = jSONObject2.put("INITIALIZATION_STATUS", "NOT IMPLEMENTED OR NOT INITIALIZED");
            } else if (i != 3) {
                jSONObject = jSONObject2.put("INITIALIZATION_STATUS", "NOT READY");
            } else {
                jSONObject = jSONObject2.put("INITIALIZATION_STATUS", "ERROR");
            }
            Intrinsics.checkNotNullExpressionValue(jSONObject, "JSONObject().run {\n    w…\", \"NOT READY\")\n    }\n  }");
        }
        return jSONObject;
    }

    public final void initModule(Context context, Config config, SFMCSdkComponents sFMCSdkComponents, ModuleReadyListener moduleReadyListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(sFMCSdkComponents, "components");
        Intrinsics.checkNotNullParameter(moduleReadyListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        try {
            ExecutorService diskIO = sFMCSdkComponents.getExecutors().getDiskIO();
            diskIO.execute(new Module$initModule$1(this, config, context, sFMCSdkComponents, moduleReadyListener, config.getModuleIdentifier().name() + "_init_thread", new Object[0]));
        } catch (Exception e) {
            SFMCSdkLogger.INSTANCE.w(getName(), e, new Module$initModule$2(this));
        } catch (Error e2) {
            SFMCSdkLogger.INSTANCE.w(getName(), e2, new Module$initModule$3(this));
        }
    }
}
