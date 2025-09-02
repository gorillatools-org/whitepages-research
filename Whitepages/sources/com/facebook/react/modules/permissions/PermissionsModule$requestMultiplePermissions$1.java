package com.facebook.react.modules.permissions;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.modules.core.PermissionAwareActivity;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;

public final class PermissionsModule$requestMultiplePermissions$1 implements Callback {
    final /* synthetic */ WritableNativeMap $grantedPermissions;
    final /* synthetic */ ArrayList<String> $permissionsToCheck;
    final /* synthetic */ Promise $promise;
    final /* synthetic */ PermissionsModule this$0;

    PermissionsModule$requestMultiplePermissions$1(ArrayList<String> arrayList, WritableNativeMap writableNativeMap, PermissionsModule permissionsModule, Promise promise) {
        this.$permissionsToCheck = arrayList;
        this.$grantedPermissions = writableNativeMap;
        this.this$0 = permissionsModule;
        this.$promise = promise;
    }

    public void invoke(Object... objArr) {
        Intrinsics.checkNotNullParameter(objArr, "args");
        int[] iArr = objArr[0];
        Intrinsics.checkNotNull(iArr, "null cannot be cast to non-null type kotlin.IntArray");
        int[] iArr2 = iArr;
        PermissionAwareActivity permissionAwareActivity = objArr[1];
        Intrinsics.checkNotNull(permissionAwareActivity, "null cannot be cast to non-null type com.facebook.react.modules.core.PermissionAwareActivity");
        PermissionAwareActivity permissionAwareActivity2 = permissionAwareActivity;
        int size = this.$permissionsToCheck.size();
        for (int i = 0; i < size; i++) {
            String str = this.$permissionsToCheck.get(i);
            Intrinsics.checkNotNullExpressionValue(str, "get(...)");
            String str2 = str;
            if (iArr2.length > i && iArr2[i] == 0) {
                this.$grantedPermissions.putString(str2, this.this$0.GRANTED);
            } else if (permissionAwareActivity2.shouldShowRequestPermissionRationale(str2)) {
                this.$grantedPermissions.putString(str2, this.this$0.DENIED);
            } else {
                this.$grantedPermissions.putString(str2, this.this$0.NEVER_ASK_AGAIN);
            }
        }
        this.$promise.resolve(this.$grantedPermissions);
    }
}
