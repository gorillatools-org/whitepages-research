package com.android.billingclient.api;

import android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0;
import android.text.TextUtils;
import com.android.billingclient.api.ProductDetails;
import com.google.android.gms.internal.play_billing.zzaa;
import com.google.android.gms.internal.play_billing.zzai;
import java.util.ArrayList;
import java.util.List;

public class BillingFlowParams {
    /* access modifiers changed from: private */
    public boolean zza;
    /* access modifiers changed from: private */
    public String zzb;
    /* access modifiers changed from: private */
    public String zzc;
    /* access modifiers changed from: private */
    public SubscriptionUpdateParams zzd;
    /* access modifiers changed from: private */
    public zzai zze;
    /* access modifiers changed from: private */
    public ArrayList zzf;
    /* access modifiers changed from: private */
    public boolean zzg;

    public static class Builder {
        private String zza;
        private String zzb;
        private List zzc;
        private ArrayList zzd;
        private boolean zze;
        private SubscriptionUpdateParams.Builder zzf;

        public BillingFlowParams build() {
            zzai zzai;
            ArrayList arrayList = this.zzd;
            boolean z = true;
            boolean z2 = arrayList != null && !arrayList.isEmpty();
            List list = this.zzc;
            boolean z3 = list != null && !list.isEmpty();
            if (!z2 && !z3) {
                throw new IllegalArgumentException("Details of the products must be provided.");
            } else if (!z2 || !z3) {
                if (!z2) {
                    ProductDetailsParams productDetailsParams = (ProductDetailsParams) this.zzc.get(0);
                    int i = 0;
                    while (i < this.zzc.size()) {
                        ProductDetailsParams productDetailsParams2 = (ProductDetailsParams) this.zzc.get(i);
                        if (productDetailsParams2 == null) {
                            throw new IllegalArgumentException("ProductDetailsParams cannot be null.");
                        } else if (i == 0 || productDetailsParams2.zza().getProductType().equals(productDetailsParams.zza().getProductType()) || productDetailsParams2.zza().getProductType().equals("play_pass_subs")) {
                            i++;
                        } else {
                            throw new IllegalArgumentException("All products should have same ProductType.");
                        }
                    }
                    String zza2 = productDetailsParams.zza().zza();
                    for (ProductDetailsParams productDetailsParams3 : this.zzc) {
                        if (!productDetailsParams.zza().getProductType().equals("play_pass_subs") && !productDetailsParams3.zza().getProductType().equals("play_pass_subs") && !zza2.equals(productDetailsParams3.zza().zza())) {
                            throw new IllegalArgumentException("All products must have the same package name.");
                        }
                    }
                } else if (this.zzd.contains((Object) null)) {
                    throw new IllegalArgumentException("SKU cannot be null.");
                } else if (this.zzd.size() > 1) {
                    MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0.m(this.zzd.get(0));
                    throw null;
                }
                BillingFlowParams billingFlowParams = new BillingFlowParams((zzca) null);
                if (!z2) {
                    if (!z3 || ((ProductDetailsParams) this.zzc.get(0)).zza().zza().isEmpty()) {
                        z = false;
                    }
                    billingFlowParams.zza = z;
                    billingFlowParams.zzb = this.zza;
                    billingFlowParams.zzc = this.zzb;
                    billingFlowParams.zzd = this.zzf.build();
                    ArrayList arrayList2 = this.zzd;
                    billingFlowParams.zzf = arrayList2 != null ? new ArrayList(arrayList2) : new ArrayList();
                    billingFlowParams.zzg = this.zze;
                    List list2 = this.zzc;
                    if (list2 != null) {
                        zzai = zzai.zzj(list2);
                    } else {
                        zzai = zzai.zzk();
                    }
                    billingFlowParams.zze = zzai;
                    return billingFlowParams;
                }
                MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0.m(this.zzd.get(0));
                throw null;
            } else {
                throw new IllegalArgumentException("Set SkuDetails or ProductDetailsParams, not both.");
            }
        }

        public Builder setIsOfferPersonalized(boolean z) {
            this.zze = z;
            return this;
        }

        public Builder setObfuscatedAccountId(String str) {
            this.zza = str;
            return this;
        }

        public Builder setObfuscatedProfileId(String str) {
            this.zzb = str;
            return this;
        }

        public Builder setProductDetailsParamsList(List list) {
            this.zzc = new ArrayList(list);
            return this;
        }

        public Builder setSubscriptionUpdateParams(SubscriptionUpdateParams subscriptionUpdateParams) {
            this.zzf = SubscriptionUpdateParams.zzb(subscriptionUpdateParams);
            return this;
        }

        /* synthetic */ Builder(zzbv zzbv) {
            SubscriptionUpdateParams.Builder newBuilder = SubscriptionUpdateParams.newBuilder();
            SubscriptionUpdateParams.Builder unused = newBuilder.zzc = true;
            this.zzf = newBuilder;
        }
    }

    public static final class ProductDetailsParams {
        private final ProductDetails zza;
        private final String zzb;

        public static class Builder {
            /* access modifiers changed from: private */
            public ProductDetails zza;
            /* access modifiers changed from: private */
            public String zzb;

            /* synthetic */ Builder(zzbw zzbw) {
            }

            public ProductDetailsParams build() {
                zzaa.zzc(this.zza, "ProductDetails is required for constructing ProductDetailsParams.");
                if (this.zza.getSubscriptionOfferDetails() != null) {
                    zzaa.zzc(this.zzb, "offerToken is required for constructing ProductDetailsParams for subscriptions.");
                }
                return new ProductDetailsParams(this, (zzbx) null);
            }

            public Builder setOfferToken(String str) {
                if (!TextUtils.isEmpty(str)) {
                    this.zzb = str;
                    return this;
                }
                throw new IllegalArgumentException("offerToken can not be empty");
            }

            public Builder setProductDetails(ProductDetails productDetails) {
                this.zza = productDetails;
                if (productDetails.getOneTimePurchaseOfferDetails() != null) {
                    productDetails.getOneTimePurchaseOfferDetails().getClass();
                    ProductDetails.OneTimePurchaseOfferDetails oneTimePurchaseOfferDetails = productDetails.getOneTimePurchaseOfferDetails();
                    if (oneTimePurchaseOfferDetails.zza() != null) {
                        this.zzb = oneTimePurchaseOfferDetails.zza();
                    }
                }
                return this;
            }
        }

        /* synthetic */ ProductDetailsParams(Builder builder, zzbx zzbx) {
            this.zza = builder.zza;
            this.zzb = builder.zzb;
        }

        public static Builder newBuilder() {
            return new Builder((zzbw) null);
        }

        public final ProductDetails zza() {
            return this.zza;
        }

        public final String zzb() {
            return this.zzb;
        }
    }

    public static class SubscriptionUpdateParams {
        /* access modifiers changed from: private */
        public String zza;
        /* access modifiers changed from: private */
        public String zzb;
        /* access modifiers changed from: private */
        public int zzc = 0;

        public static class Builder {
            private String zza;
            private String zzb;
            /* access modifiers changed from: private */
            public boolean zzc;
            private int zzd = 0;

            /* synthetic */ Builder(zzby zzby) {
            }

            public SubscriptionUpdateParams build() {
                boolean z = true;
                if (TextUtils.isEmpty(this.zza) && TextUtils.isEmpty((CharSequence) null)) {
                    z = false;
                }
                boolean isEmpty = TextUtils.isEmpty(this.zzb);
                if (z && !isEmpty) {
                    throw new IllegalArgumentException("Please provide Old SKU purchase information(token/id) or original external transaction id, not both.");
                } else if (this.zzc || z || !isEmpty) {
                    SubscriptionUpdateParams subscriptionUpdateParams = new SubscriptionUpdateParams((zzbz) null);
                    subscriptionUpdateParams.zza = this.zza;
                    subscriptionUpdateParams.zzc = this.zzd;
                    subscriptionUpdateParams.zzb = this.zzb;
                    return subscriptionUpdateParams;
                } else {
                    throw new IllegalArgumentException("Old SKU purchase information(token/id) or original external transaction id must be provided.");
                }
            }

            public Builder setOldPurchaseToken(String str) {
                this.zza = str;
                return this;
            }

            public Builder setOriginalExternalTransactionId(String str) {
                this.zzb = str;
                return this;
            }

            public Builder setSubscriptionReplacementMode(int i) {
                this.zzd = i;
                return this;
            }

            public final Builder zzb(String str) {
                this.zza = str;
                return this;
            }
        }

        /* synthetic */ SubscriptionUpdateParams(zzbz zzbz) {
        }

        public static Builder newBuilder() {
            return new Builder((zzby) null);
        }

        static /* bridge */ /* synthetic */ Builder zzb(SubscriptionUpdateParams subscriptionUpdateParams) {
            Builder newBuilder = newBuilder();
            newBuilder.zzb(subscriptionUpdateParams.zza);
            newBuilder.setSubscriptionReplacementMode(subscriptionUpdateParams.zzc);
            newBuilder.setOriginalExternalTransactionId(subscriptionUpdateParams.zzb);
            return newBuilder;
        }

        /* access modifiers changed from: package-private */
        public final int zza() {
            return this.zzc;
        }

        /* access modifiers changed from: package-private */
        public final String zzc() {
            return this.zza;
        }

        /* access modifiers changed from: package-private */
        public final String zzd() {
            return this.zzb;
        }
    }

    /* synthetic */ BillingFlowParams(zzca zzca) {
    }

    public static Builder newBuilder() {
        return new Builder((zzbv) null);
    }

    public final int zza() {
        return this.zzd.zza();
    }

    public final String zzb() {
        return this.zzb;
    }

    public final String zzc() {
        return this.zzc;
    }

    public final String zzd() {
        return this.zzd.zzc();
    }

    public final String zze() {
        return this.zzd.zzd();
    }

    public final ArrayList zzf() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.zzf);
        return arrayList;
    }

    public final List zzg() {
        return this.zze;
    }

    public final boolean zzo() {
        return this.zzg;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzp() {
        return (this.zzb == null && this.zzc == null && this.zzd.zzd() == null && this.zzd.zza() == 0 && !this.zza && !this.zzg) ? false : true;
    }
}
