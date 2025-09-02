package com.facebook.fresco.ui.common;

import android.util.Log;
import com.facebook.fresco.ui.common.ControllerListener2;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public class ForwardingControllerListener2 extends BaseControllerListener2 {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final List listeners = new ArrayList(2);

    public final synchronized void addListener(ControllerListener2 controllerListener2) {
        Intrinsics.checkNotNullParameter(controllerListener2, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.listeners.add(controllerListener2);
    }

    public final synchronized void removeListener(ControllerListener2 controllerListener2) {
        Intrinsics.checkNotNullParameter(controllerListener2, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.listeners.remove(controllerListener2);
    }

    public void onFailure(String str, Throwable th, ControllerListener2.Extras extras) {
        Intrinsics.checkNotNullParameter(str, "id");
        int size = this.listeners.size();
        int i = 0;
        while (i < size) {
            try {
                try {
                    ((ControllerListener2) this.listeners.get(i)).onFailure(str, th, extras);
                    Unit unit = Unit.INSTANCE;
                } catch (Exception e) {
                    Log.e("FwdControllerListener2", "InternalListener exception in " + "onFailure", e);
                }
                i++;
            } catch (IndexOutOfBoundsException unused) {
                return;
            }
        }
    }

    public void onFinalImageSet(String str, Object obj, ControllerListener2.Extras extras) {
        Intrinsics.checkNotNullParameter(str, "id");
        int size = this.listeners.size();
        int i = 0;
        while (i < size) {
            try {
                try {
                    ((ControllerListener2) this.listeners.get(i)).onFinalImageSet(str, obj, extras);
                    Unit unit = Unit.INSTANCE;
                } catch (Exception e) {
                    Log.e("FwdControllerListener2", "InternalListener exception in " + "onFinalImageSet", e);
                }
                i++;
            } catch (IndexOutOfBoundsException unused) {
                return;
            }
        }
    }

    public void onIntermediateImageFailed(String str) {
        Intrinsics.checkNotNullParameter(str, "id");
        int size = this.listeners.size();
        int i = 0;
        while (i < size) {
            try {
                try {
                    ((ControllerListener2) this.listeners.get(i)).onIntermediateImageFailed(str);
                    Unit unit = Unit.INSTANCE;
                } catch (Exception e) {
                    Log.e("FwdControllerListener2", "InternalListener exception in " + "onIntermediateImageFailed", e);
                }
                i++;
            } catch (IndexOutOfBoundsException unused) {
                return;
            }
        }
    }

    public void onIntermediateImageSet(String str, Object obj) {
        Intrinsics.checkNotNullParameter(str, "id");
        int size = this.listeners.size();
        int i = 0;
        while (i < size) {
            try {
                try {
                    ((ControllerListener2) this.listeners.get(i)).onIntermediateImageSet(str, obj);
                    Unit unit = Unit.INSTANCE;
                } catch (Exception e) {
                    Log.e("FwdControllerListener2", "InternalListener exception in " + "onIntermediateImageSet", e);
                }
                i++;
            } catch (IndexOutOfBoundsException unused) {
                return;
            }
        }
    }

    public void onRelease(String str, ControllerListener2.Extras extras) {
        Intrinsics.checkNotNullParameter(str, "id");
        int size = this.listeners.size();
        int i = 0;
        while (i < size) {
            try {
                try {
                    ((ControllerListener2) this.listeners.get(i)).onRelease(str, extras);
                    Unit unit = Unit.INSTANCE;
                } catch (Exception e) {
                    Log.e("FwdControllerListener2", "InternalListener exception in " + "onRelease", e);
                }
                i++;
            } catch (IndexOutOfBoundsException unused) {
                return;
            }
        }
    }

    public void onSubmit(String str, Object obj, ControllerListener2.Extras extras) {
        Intrinsics.checkNotNullParameter(str, "id");
        int size = this.listeners.size();
        int i = 0;
        while (i < size) {
            try {
                try {
                    ((ControllerListener2) this.listeners.get(i)).onSubmit(str, obj, extras);
                    Unit unit = Unit.INSTANCE;
                } catch (Exception e) {
                    Log.e("FwdControllerListener2", "InternalListener exception in " + "onSubmit", e);
                }
                i++;
            } catch (IndexOutOfBoundsException unused) {
                return;
            }
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
