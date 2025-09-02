package io.branch.referral.util;

import android.content.Context;
import android.text.TextUtils;
import io.branch.indexing.BranchUniversalObject;
import io.branch.referral.Branch;
import io.branch.referral.BranchLogger;
import io.branch.referral.Defines$Jsonkey;
import io.branch.referral.Defines$RequestPath;
import io.branch.referral.ServerRequest;
import io.branch.referral.ServerRequestLogEvent;
import io.branch.referral.ServerResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class BranchEvent {
    private final List buoList;
    private final JSONObject customProperties;
    private final String eventName;
    private final boolean isStandardEvent;
    private final JSONObject standardProperties;
    private final HashMap topLevelProperties;

    public interface BranchLogEventCallback {
        void onFailure(Exception exc);
    }

    public BranchEvent(BRANCH_STANDARD_EVENT branch_standard_event) {
        this(branch_standard_event.getName());
    }

    public BranchEvent(String str) {
        this.topLevelProperties = new HashMap();
        this.standardProperties = new JSONObject();
        this.customProperties = new JSONObject();
        this.eventName = str;
        BRANCH_STANDARD_EVENT[] values = BRANCH_STANDARD_EVENT.values();
        int length = values.length;
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            } else if (str.equals(values[i].getName())) {
                z = true;
                break;
            } else {
                i++;
            }
        }
        this.isStandardEvent = z;
        this.buoList = new ArrayList();
    }

    public BranchEvent setCustomerEventAlias(String str) {
        return addTopLevelProperty(Defines$Jsonkey.CustomerEventAlias.getKey(), str);
    }

    public BranchEvent setTransactionID(String str) {
        return addStandardProperty(Defines$Jsonkey.TransactionID.getKey(), str);
    }

    public BranchEvent setCurrency(CurrencyType currencyType) {
        return addStandardProperty(Defines$Jsonkey.Currency.getKey(), currencyType.toString());
    }

    public BranchEvent setRevenue(double d) {
        return addStandardProperty(Defines$Jsonkey.Revenue.getKey(), Double.valueOf(d));
    }

    public BranchEvent setShipping(double d) {
        return addStandardProperty(Defines$Jsonkey.Shipping.getKey(), Double.valueOf(d));
    }

    public BranchEvent setTax(double d) {
        return addStandardProperty(Defines$Jsonkey.Tax.getKey(), Double.valueOf(d));
    }

    public BranchEvent setCoupon(String str) {
        return addStandardProperty(Defines$Jsonkey.Coupon.getKey(), str);
    }

    public BranchEvent setAffiliation(String str) {
        return addStandardProperty(Defines$Jsonkey.Affiliation.getKey(), str);
    }

    public BranchEvent setDescription(String str) {
        return addStandardProperty(Defines$Jsonkey.Description.getKey(), str);
    }

    public BranchEvent setSearchQuery(String str) {
        return addStandardProperty(Defines$Jsonkey.SearchQuery.getKey(), str);
    }

    private BranchEvent addStandardProperty(String str, Object obj) {
        if (obj != null) {
            try {
                this.standardProperties.put(str, obj);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            this.standardProperties.remove(str);
        }
        return this;
    }

    private BranchEvent addTopLevelProperty(String str, Object obj) {
        if (!this.topLevelProperties.containsKey(str)) {
            this.topLevelProperties.put(str, obj);
        } else {
            this.topLevelProperties.remove(str);
        }
        return this;
    }

    public BranchEvent addCustomDataProperty(String str, String str2) {
        try {
            this.customProperties.put(str, str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public BranchEvent addContentItems(BranchUniversalObject... branchUniversalObjectArr) {
        Collections.addAll(this.buoList, branchUniversalObjectArr);
        return this;
    }

    public BranchEvent addContentItems(List list) {
        this.buoList.addAll(list);
        return this;
    }

    public boolean logEvent(Context context) {
        return logEvent(context, (BranchLogEventCallback) null);
    }

    public boolean logEvent(Context context, BranchLogEventCallback branchLogEventCallback) {
        Defines$RequestPath defines$RequestPath = this.isStandardEvent ? Defines$RequestPath.TrackStandardEvent : Defines$RequestPath.TrackCustomEvent;
        if (Branch.getInstance() != null) {
            AnonymousClass1 r1 = new ServerRequestLogEvent(context, defines$RequestPath, this.eventName, this.topLevelProperties, this.standardProperties, this.customProperties, this.buoList, branchLogEventCallback) {
                public void handleFailure(int i, String str) {
                }

                public void onRequestSucceeded(ServerResponse serverResponse, Branch branch) {
                }
            };
            BranchLogger.v("Preparing V2 event, user agent is " + Branch._userAgentString);
            if (TextUtils.isEmpty(Branch._userAgentString)) {
                BranchLogger.v("handleNewRequest adding process wait lock USER_AGENT_STRING_LOCK");
                r1.addProcessWaitLock(ServerRequest.PROCESS_WAIT_LOCK.USER_AGENT_STRING_LOCK);
            }
            Branch.getInstance().requestQueue_.handleNewRequest(r1);
            return true;
        }
        if (branchLogEventCallback != null) {
            branchLogEventCallback.onFailure(new Exception("Failed logEvent server request: The Branch instance was not available"));
        }
        return false;
    }
}
