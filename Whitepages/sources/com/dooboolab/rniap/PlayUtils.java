package com.dooboolab.rniap;

import android.util.Log;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.Promise;
import kotlin.jvm.internal.Intrinsics;

public final class PlayUtils {
    public static final PlayUtils INSTANCE = new PlayUtils();

    private PlayUtils() {
    }

    public final void rejectPromiseWithBillingError(Promise promise, int i) {
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        BillingResponse billingResponseData = getBillingResponseData(i);
        PromiseUtlisKt.safeReject(promise, billingResponseData.getCode(), billingResponseData.getMessage());
    }

    public final BillingResponse getBillingResponseData(int i) {
        BillingResponse billingResponse;
        BillingResponse billingResponse2;
        if (i != 12) {
            switch (i) {
                case -2:
                    billingResponse = new BillingResponse("E_SERVICE_ERROR", "This feature is not available on your device.");
                    break;
                case -1:
                    billingResponse = new BillingResponse("E_NETWORK_ERROR", "The service is disconnected (check your internet connection.)");
                    break;
                case 0:
                    billingResponse = new BillingResponse("OK", "");
                    break;
                case 1:
                    billingResponse = new BillingResponse("E_USER_CANCELLED", "Payment is Cancelled.");
                    break;
                case 2:
                    billingResponse = new BillingResponse("E_SERVICE_ERROR", "The service is unreachable. This may be your internet connection, or the Play Store may be down.");
                    break;
                case 3:
                    billingResponse = new BillingResponse("E_SERVICE_ERROR", "Billing is unavailable. This may be a problem with your device, or the Play Store may be down.");
                    break;
                case 4:
                    billingResponse2 = new BillingResponse("E_ITEM_UNAVAILABLE", "That item is unavailable.");
                    break;
                case 5:
                    billingResponse = new BillingResponse("E_DEVELOPER_ERROR", "Google is indicating that we have some issue connecting to payment.");
                    break;
                case 6:
                    billingResponse = new BillingResponse("E_UNKNOWN", "An unknown or unexpected error has occurred. Please try again later.");
                    break;
                case 7:
                    billingResponse = new BillingResponse("E_ALREADY_OWNED", "You already own this item.");
                    break;
                case 8:
                    billingResponse2 = new BillingResponse("E_ITEM_UNAVAILABLE", "Item not owned. It may have already been consumed.");
                    break;
                default:
                    billingResponse = new BillingResponse("E_UNKNOWN", "Purchase failed with code: " + i);
                    break;
            }
            billingResponse = billingResponse2;
        } else {
            billingResponse = new BillingResponse("E_NETWORK_ERROR", "You have problem with network connection.");
        }
        Log.e("IapPromises", "Error Code : " + i);
        return billingResponse;
    }

    public final void rejectPromisesWithBillingError(String str, int i) {
        Intrinsics.checkNotNullParameter(str, "key");
        BillingResponse billingResponseData = getBillingResponseData(i);
        PromiseUtils.INSTANCE.rejectPromisesForKey(str, billingResponseData.getCode(), billingResponseData.getMessage(), (Exception) null);
    }
}
