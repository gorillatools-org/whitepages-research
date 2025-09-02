package com.facebook.react.modules.permissions;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.modules.core.PermissionAwareActivity;
import kotlin.jvm.internal.Intrinsics;

public final class PermissionsModule$requestPermission$1 implements Callback {
    final /* synthetic */ String $permission;
    final /* synthetic */ Promise $promise;
    final /* synthetic */ PermissionsModule this$0;

    PermissionsModule$requestPermission$1(Promise promise, PermissionsModule permissionsModule, String str) {
        this.$promise = promise;
        this.this$0 = permissionsModule;
        this.$permission = str;
    }

    public void invoke(Object... objArr) {
        Intrinsics.checkNotNullParameter(objArr, "args");
        int[] iArr = objArr[0];
        Intrinsics.checkNotNull(iArr, "null cannot be cast to non-null type kotlin.IntArray");
        int[] iArr2 = iArr;
        if (iArr2.length <= 0 || iArr2[0] != 0) {
            PermissionAwareActivity permissionAwareActivity = objArr[1];
            Intrinsics.checkNotNull(permissionAwareActivity, "null cannot be cast to non-null type com.facebook.react.modules.core.PermissionAwareActivity");
            if (permissionAwareActivity.shouldShowRequestPermissionRationale(this.$permission)) {
                this.$promise.resolve(this.this$0.DENIED);
            } else {
                this.$promise.resolve(this.this$0.NEVER_ASK_AGAIN);
            }
        } else {
            this.$promise.resolve(this.this$0.GRANTED);
        }
    }
}
