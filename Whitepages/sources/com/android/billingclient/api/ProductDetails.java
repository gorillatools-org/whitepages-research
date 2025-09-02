package com.android.billingclient.api;

import android.text.TextUtils;
import com.google.android.gms.internal.play_billing.zzai;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public final class ProductDetails {
    private final String zza;
    private final JSONObject zzb;
    private final String zzc;
    private final String zzd;
    private final String zze;
    private final String zzf;
    private final String zzg;
    private final String zzh;
    private final String zzi;
    private final String zzj;
    private final String zzk;
    private final List zzl;
    private final List zzm;

    public static final class InstallmentPlanDetails {
        private final int commitmentPaymentsCount;
        private final int subsequentCommitmentPaymentsCount;

        InstallmentPlanDetails(JSONObject jSONObject) {
            this.commitmentPaymentsCount = jSONObject.getInt("commitmentPaymentsCount");
            this.subsequentCommitmentPaymentsCount = jSONObject.optInt("subsequentCommitmentPaymentsCount");
        }
    }

    public static final class OneTimePurchaseOfferDetails {
        private final String zza;
        private final long zzb;
        private final String zzc;
        private final String zzd;
        private final String zze;
        private final String zzf;
        private final zzai zzg;
        private final Long zzh;
        private final zzcq zzi;
        private final zzcu zzj;
        private final zzcr zzk;
        private final zzcs zzl;
        private final zzct zzm;

        OneTimePurchaseOfferDetails(JSONObject jSONObject) {
            zzcq zzcq;
            zzcu zzcu;
            zzcr zzcr;
            zzcs zzcs;
            this.zza = jSONObject.optString("formattedPrice");
            this.zzb = jSONObject.optLong("priceAmountMicros");
            this.zzc = jSONObject.optString("priceCurrencyCode");
            String optString = jSONObject.optString("offerIdToken");
            zzct zzct = null;
            this.zzd = true == optString.isEmpty() ? null : optString;
            String optString2 = jSONObject.optString("offerId");
            this.zze = true == optString2.isEmpty() ? null : optString2;
            String optString3 = jSONObject.optString("purchaseOptionId");
            this.zzf = true == optString3.isEmpty() ? null : optString3;
            jSONObject.optInt("offerType");
            JSONArray optJSONArray = jSONObject.optJSONArray("offerTags");
            ArrayList arrayList = new ArrayList();
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    arrayList.add(optJSONArray.getString(i));
                }
            }
            this.zzg = zzai.zzj(arrayList);
            this.zzh = jSONObject.has("fullPriceMicros") ? Long.valueOf(jSONObject.optLong("fullPriceMicros")) : null;
            JSONObject optJSONObject = jSONObject.optJSONObject("discountDisplayInfo");
            if (optJSONObject == null) {
                zzcq = null;
            } else {
                zzcq = new zzcq(optJSONObject);
            }
            this.zzi = zzcq;
            JSONObject optJSONObject2 = jSONObject.optJSONObject("validTimeWindow");
            if (optJSONObject2 == null) {
                zzcu = null;
            } else {
                zzcu = new zzcu(optJSONObject2);
            }
            this.zzj = zzcu;
            JSONObject optJSONObject3 = jSONObject.optJSONObject("limitedQuantityInfo");
            if (optJSONObject3 == null) {
                zzcr = null;
            } else {
                zzcr = new zzcr(optJSONObject3);
            }
            this.zzk = zzcr;
            JSONObject optJSONObject4 = jSONObject.optJSONObject("preorderDetails");
            if (optJSONObject4 == null) {
                zzcs = null;
            } else {
                zzcs = new zzcs(optJSONObject4);
            }
            this.zzl = zzcs;
            JSONObject optJSONObject5 = jSONObject.optJSONObject("rentalDetails");
            this.zzm = optJSONObject5 != null ? new zzct(optJSONObject5) : zzct;
        }

        public String getFormattedPrice() {
            return this.zza;
        }

        public long getPriceAmountMicros() {
            return this.zzb;
        }

        public String getPriceCurrencyCode() {
            return this.zzc;
        }

        public final String zza() {
            return this.zzd;
        }
    }

    public static final class PricingPhase {
        private final String zza;
        private final long zzb;
        private final String zzc;
        private final String zzd;
        private final int zze;
        private final int zzf;

        PricingPhase(JSONObject jSONObject) {
            this.zzd = jSONObject.optString("billingPeriod");
            this.zzc = jSONObject.optString("priceCurrencyCode");
            this.zza = jSONObject.optString("formattedPrice");
            this.zzb = jSONObject.optLong("priceAmountMicros");
            this.zzf = jSONObject.optInt("recurrenceMode");
            this.zze = jSONObject.optInt("billingCycleCount");
        }

        public int getBillingCycleCount() {
            return this.zze;
        }

        public String getBillingPeriod() {
            return this.zzd;
        }

        public String getFormattedPrice() {
            return this.zza;
        }

        public long getPriceAmountMicros() {
            return this.zzb;
        }

        public String getPriceCurrencyCode() {
            return this.zzc;
        }

        public int getRecurrenceMode() {
            return this.zzf;
        }
    }

    public static class PricingPhases {
        private final List zza;

        PricingPhases(JSONArray jSONArray) {
            ArrayList arrayList = new ArrayList();
            if (jSONArray != null) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject optJSONObject = jSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        arrayList.add(new PricingPhase(optJSONObject));
                    }
                }
            }
            this.zza = arrayList;
        }

        public List getPricingPhaseList() {
            return this.zza;
        }
    }

    public static final class SubscriptionOfferDetails {
        private final String zza;
        private final String zzb;
        private final String zzc;
        private final PricingPhases zzd;
        private final List zze;
        private final InstallmentPlanDetails zzf;
        private final zzcv zzg;

        SubscriptionOfferDetails(JSONObject jSONObject) {
            InstallmentPlanDetails installmentPlanDetails;
            this.zza = jSONObject.optString("basePlanId");
            String optString = jSONObject.optString("offerId");
            zzcv zzcv = null;
            this.zzb = true == optString.isEmpty() ? null : optString;
            this.zzc = jSONObject.getString("offerIdToken");
            this.zzd = new PricingPhases(jSONObject.getJSONArray("pricingPhases"));
            JSONObject optJSONObject = jSONObject.optJSONObject("installmentPlanDetails");
            if (optJSONObject == null) {
                installmentPlanDetails = null;
            } else {
                installmentPlanDetails = new InstallmentPlanDetails(optJSONObject);
            }
            this.zzf = installmentPlanDetails;
            JSONObject optJSONObject2 = jSONObject.optJSONObject("transitionPlanDetails");
            this.zzg = optJSONObject2 != null ? new zzcv(optJSONObject2) : zzcv;
            ArrayList arrayList = new ArrayList();
            JSONArray optJSONArray = jSONObject.optJSONArray("offerTags");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    arrayList.add(optJSONArray.getString(i));
                }
            }
            this.zze = arrayList;
        }

        public String getBasePlanId() {
            return this.zza;
        }

        public String getOfferId() {
            return this.zzb;
        }

        public List getOfferTags() {
            return this.zze;
        }

        public String getOfferToken() {
            return this.zzc;
        }

        public PricingPhases getPricingPhases() {
            return this.zzd;
        }
    }

    ProductDetails(String str) {
        ArrayList arrayList;
        this.zza = str;
        JSONObject jSONObject = new JSONObject(str);
        this.zzb = jSONObject;
        String optString = jSONObject.optString("productId");
        this.zzc = optString;
        String optString2 = jSONObject.optString("type");
        this.zzd = optString2;
        if (TextUtils.isEmpty(optString)) {
            throw new IllegalArgumentException("Product id cannot be empty.");
        } else if (!TextUtils.isEmpty(optString2)) {
            this.zze = jSONObject.optString("title");
            this.zzf = jSONObject.optString("name");
            this.zzg = jSONObject.optString("description");
            this.zzi = jSONObject.optString("packageDisplayName");
            this.zzj = jSONObject.optString("iconUrl");
            this.zzh = jSONObject.optString("skuDetailsToken");
            this.zzk = jSONObject.optString("serializedDocid");
            JSONArray optJSONArray = jSONObject.optJSONArray("subscriptionOfferDetails");
            if (optJSONArray != null) {
                ArrayList arrayList2 = new ArrayList();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    arrayList2.add(new SubscriptionOfferDetails(optJSONArray.getJSONObject(i)));
                }
                this.zzl = arrayList2;
            } else {
                if (optString2.equals("subs") || optString2.equals("play_pass_subs")) {
                    arrayList = new ArrayList();
                } else {
                    arrayList = null;
                }
                this.zzl = arrayList;
            }
            JSONObject optJSONObject = this.zzb.optJSONObject("oneTimePurchaseOfferDetails");
            JSONArray optJSONArray2 = this.zzb.optJSONArray("oneTimePurchaseOfferDetailsList");
            ArrayList arrayList3 = new ArrayList();
            if (optJSONArray2 != null) {
                for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                    arrayList3.add(new OneTimePurchaseOfferDetails(optJSONArray2.getJSONObject(i2)));
                }
                this.zzm = arrayList3;
            } else if (optJSONObject != null) {
                arrayList3.add(new OneTimePurchaseOfferDetails(optJSONObject));
                this.zzm = arrayList3;
            } else {
                this.zzm = null;
            }
        } else {
            throw new IllegalArgumentException("Product type cannot be empty.");
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ProductDetails)) {
            return false;
        }
        return TextUtils.equals(this.zza, ((ProductDetails) obj).zza);
    }

    public String getDescription() {
        return this.zzg;
    }

    public String getName() {
        return this.zzf;
    }

    public OneTimePurchaseOfferDetails getOneTimePurchaseOfferDetails() {
        List list = this.zzm;
        if (list == null || list.isEmpty()) {
            return null;
        }
        return (OneTimePurchaseOfferDetails) this.zzm.get(0);
    }

    public String getProductId() {
        return this.zzc;
    }

    public String getProductType() {
        return this.zzd;
    }

    public List getSubscriptionOfferDetails() {
        return this.zzl;
    }

    public String getTitle() {
        return this.zze;
    }

    public int hashCode() {
        return this.zza.hashCode();
    }

    public String toString() {
        List list = this.zzl;
        String obj = this.zzb.toString();
        String valueOf = String.valueOf(list);
        return "ProductDetails{jsonString='" + this.zza + "', parsedJson=" + obj + ", productId='" + this.zzc + "', productType='" + this.zzd + "', title='" + this.zze + "', productDetailsToken='" + this.zzh + "', subscriptionOfferDetails=" + valueOf + "}";
    }

    public final String zza() {
        return this.zzb.optString(RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME);
    }

    /* access modifiers changed from: package-private */
    public final String zzb() {
        return this.zzh;
    }

    public String zzc() {
        return this.zzk;
    }
}
