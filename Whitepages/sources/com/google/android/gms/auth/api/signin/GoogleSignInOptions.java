package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.internal.GoogleSignInOptionsExtensionParcelable;
import com.google.android.gms.auth.api.signin.internal.HashAccumulator;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SafeParcelable.Class(creator = "GoogleSignInOptionsCreator")
@Deprecated
public class GoogleSignInOptions extends AbstractSafeParcelable implements Api.ApiOptions.Optional, ReflectedParcelable {
    public static final Parcelable.Creator<GoogleSignInOptions> CREATOR = new zae();
    public static final GoogleSignInOptions DEFAULT_GAMES_SIGN_IN;
    public static final GoogleSignInOptions DEFAULT_SIGN_IN;
    public static final Scope zaa = new Scope(Scopes.PROFILE);
    public static final Scope zab = new Scope(Scopes.EMAIL);
    public static final Scope zac = new Scope(Scopes.OPEN_ID);
    public static final Scope zad;
    public static final Scope zae = new Scope(Scopes.GAMES);
    private static final Comparator zag = new zac();
    @SafeParcelable.VersionField(id = 1)
    final int zaf;
    /* access modifiers changed from: private */
    @SafeParcelable.Field(getter = "getScopes", id = 2)
    public final ArrayList zah;
    /* access modifiers changed from: private */
    @SafeParcelable.Field(getter = "getAccount", id = 3)
    public Account zai;
    /* access modifiers changed from: private */
    @SafeParcelable.Field(getter = "isIdTokenRequested", id = 4)
    public boolean zaj;
    /* access modifiers changed from: private */
    @SafeParcelable.Field(getter = "isServerAuthCodeRequested", id = 5)
    public final boolean zak;
    /* access modifiers changed from: private */
    @SafeParcelable.Field(getter = "isForceCodeForRefreshToken", id = 6)
    public final boolean zal;
    /* access modifiers changed from: private */
    @SafeParcelable.Field(getter = "getServerClientId", id = 7)
    public String zam;
    /* access modifiers changed from: private */
    @SafeParcelable.Field(getter = "getHostedDomain", id = 8)
    public String zan;
    /* access modifiers changed from: private */
    @SafeParcelable.Field(getter = "getExtensions", id = 9)
    public ArrayList zao;
    /* access modifiers changed from: private */
    @SafeParcelable.Field(getter = "getLogSessionId", id = 10)
    public String zap;
    private Map zaq;

    public static final class Builder {
        private Set zaa = new HashSet();
        private boolean zab;
        private boolean zac;
        private boolean zad;
        private String zae;
        private Account zaf;
        private String zag;
        private Map zah = new HashMap();
        private String zai;

        public Builder() {
        }

        private final String zaa(String str) {
            Preconditions.checkNotEmpty(str);
            String str2 = this.zae;
            boolean z = true;
            if (str2 != null && !str2.equals(str)) {
                z = false;
            }
            Preconditions.checkArgument(z, "two different server client ids provided");
            return str;
        }

        public Builder addExtension(GoogleSignInOptionsExtension googleSignInOptionsExtension) {
            if (!this.zah.containsKey(Integer.valueOf(googleSignInOptionsExtension.getExtensionType()))) {
                List<Scope> impliedScopes = googleSignInOptionsExtension.getImpliedScopes();
                if (impliedScopes != null) {
                    this.zaa.addAll(impliedScopes);
                }
                this.zah.put(Integer.valueOf(googleSignInOptionsExtension.getExtensionType()), new GoogleSignInOptionsExtensionParcelable(googleSignInOptionsExtension));
                return this;
            }
            throw new IllegalStateException("Only one extension per type may be added");
        }

        public GoogleSignInOptions build() {
            if (this.zaa.contains(GoogleSignInOptions.zae)) {
                Set set = this.zaa;
                Scope scope = GoogleSignInOptions.zad;
                if (set.contains(scope)) {
                    this.zaa.remove(scope);
                }
            }
            if (this.zad && (this.zaf == null || !this.zaa.isEmpty())) {
                requestId();
            }
            return new GoogleSignInOptions(3, new ArrayList(this.zaa), this.zaf, this.zad, this.zab, this.zac, this.zae, this.zag, this.zah, this.zai, (zad) null);
        }

        public Builder requestEmail() {
            this.zaa.add(GoogleSignInOptions.zab);
            return this;
        }

        public Builder requestId() {
            this.zaa.add(GoogleSignInOptions.zac);
            return this;
        }

        public Builder requestIdToken(String str) {
            this.zad = true;
            zaa(str);
            this.zae = str;
            return this;
        }

        public Builder requestProfile() {
            this.zaa.add(GoogleSignInOptions.zaa);
            return this;
        }

        public Builder requestScopes(Scope scope, Scope... scopeArr) {
            this.zaa.add(scope);
            this.zaa.addAll(Arrays.asList(scopeArr));
            return this;
        }

        public Builder requestServerAuthCode(String str) {
            requestServerAuthCode(str, false);
            return this;
        }

        public Builder setAccountName(String str) {
            this.zaf = new Account(Preconditions.checkNotEmpty(str), "com.google");
            return this;
        }

        public Builder setHostedDomain(String str) {
            this.zag = Preconditions.checkNotEmpty(str);
            return this;
        }

        @KeepForSdk
        public Builder setLogSessionId(String str) {
            this.zai = str;
            return this;
        }

        public Builder requestServerAuthCode(String str, boolean z) {
            this.zab = true;
            zaa(str);
            this.zae = str;
            this.zac = z;
            return this;
        }

        public Builder(GoogleSignInOptions googleSignInOptions) {
            Preconditions.checkNotNull(googleSignInOptions);
            this.zaa = new HashSet(googleSignInOptions.zah);
            this.zab = googleSignInOptions.zak;
            this.zac = googleSignInOptions.zal;
            this.zad = googleSignInOptions.zaj;
            this.zae = googleSignInOptions.zam;
            this.zaf = googleSignInOptions.zai;
            this.zag = googleSignInOptions.zan;
            this.zah = GoogleSignInOptions.zam(googleSignInOptions.zao);
            this.zai = googleSignInOptions.zap;
        }
    }

    static {
        Scope scope = new Scope(Scopes.GAMES_LITE);
        zad = scope;
        Builder builder = new Builder();
        builder.requestId();
        builder.requestProfile();
        DEFAULT_SIGN_IN = builder.build();
        Builder builder2 = new Builder();
        builder2.requestScopes(scope, new Scope[0]);
        DEFAULT_GAMES_SIGN_IN = builder2.build();
    }

    @SafeParcelable.Constructor
    GoogleSignInOptions(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) ArrayList arrayList, @SafeParcelable.Param(id = 3) Account account, @SafeParcelable.Param(id = 4) boolean z, @SafeParcelable.Param(id = 5) boolean z2, @SafeParcelable.Param(id = 6) boolean z3, @SafeParcelable.Param(id = 7) String str, @SafeParcelable.Param(id = 8) String str2, @SafeParcelable.Param(id = 9) ArrayList arrayList2, @SafeParcelable.Param(id = 10) String str3) {
        this(i, arrayList, account, z, z2, z3, str, str2, zam(arrayList2), str3);
    }

    /* synthetic */ GoogleSignInOptions(int i, ArrayList arrayList, Account account, boolean z, boolean z2, boolean z3, String str, String str2, Map map, String str3, zad zad2) {
        this(3, arrayList, account, z, z2, z3, str, str2, map, str3);
    }

    public static GoogleSignInOptions zab(String str) throws JSONException {
        String str2 = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        HashSet hashSet = new HashSet();
        JSONArray jSONArray = jSONObject.getJSONArray("scopes");
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            hashSet.add(new Scope(jSONArray.getString(i)));
        }
        String optString = jSONObject.has("accountName") ? jSONObject.optString("accountName") : null;
        Account account = !TextUtils.isEmpty(optString) ? new Account(optString, "com.google") : null;
        ArrayList arrayList = new ArrayList(hashSet);
        boolean z = jSONObject.getBoolean("idTokenRequested");
        boolean z2 = jSONObject.getBoolean("serverAuthRequested");
        boolean z3 = jSONObject.getBoolean("forceCodeForRefreshToken");
        String optString2 = jSONObject.has("serverClientId") ? jSONObject.optString("serverClientId") : null;
        if (jSONObject.has("hostedDomain")) {
            str2 = jSONObject.optString("hostedDomain");
        }
        return new GoogleSignInOptions(3, arrayList, account, z, z2, z3, optString2, str2, (Map) new HashMap(), (String) null);
    }

    /* access modifiers changed from: private */
    public static Map zam(List list) {
        HashMap hashMap = new HashMap();
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                GoogleSignInOptionsExtensionParcelable googleSignInOptionsExtensionParcelable = (GoogleSignInOptionsExtensionParcelable) it.next();
                hashMap.put(Integer.valueOf(googleSignInOptionsExtensionParcelable.getType()), googleSignInOptionsExtensionParcelable);
            }
        }
        return hashMap;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0048, code lost:
        if (r1.equals(r4.getAccount()) != false) goto L_0x004a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r4) {
        /*
            r3 = this;
            r0 = 0
            if (r4 != 0) goto L_0x0004
            return r0
        L_0x0004:
            com.google.android.gms.auth.api.signin.GoogleSignInOptions r4 = (com.google.android.gms.auth.api.signin.GoogleSignInOptions) r4     // Catch:{ ClassCastException -> 0x0090 }
            java.util.ArrayList r1 = r3.zao     // Catch:{ ClassCastException -> 0x0090 }
            boolean r1 = r1.isEmpty()     // Catch:{ ClassCastException -> 0x0090 }
            if (r1 == 0) goto L_0x0090
            java.util.ArrayList r1 = r4.zao     // Catch:{ ClassCastException -> 0x0090 }
            boolean r1 = r1.isEmpty()     // Catch:{ ClassCastException -> 0x0090 }
            if (r1 != 0) goto L_0x0018
            goto L_0x0090
        L_0x0018:
            java.util.ArrayList r1 = r3.zah     // Catch:{ ClassCastException -> 0x0090 }
            int r1 = r1.size()     // Catch:{ ClassCastException -> 0x0090 }
            java.util.ArrayList r2 = r4.getScopes()     // Catch:{ ClassCastException -> 0x0090 }
            int r2 = r2.size()     // Catch:{ ClassCastException -> 0x0090 }
            if (r1 != r2) goto L_0x0090
            java.util.ArrayList r1 = r3.zah     // Catch:{ ClassCastException -> 0x0090 }
            java.util.ArrayList r2 = r4.getScopes()     // Catch:{ ClassCastException -> 0x0090 }
            boolean r1 = r1.containsAll(r2)     // Catch:{ ClassCastException -> 0x0090 }
            if (r1 != 0) goto L_0x0035
            goto L_0x0090
        L_0x0035:
            android.accounts.Account r1 = r3.zai     // Catch:{ ClassCastException -> 0x0090 }
            if (r1 != 0) goto L_0x0040
            android.accounts.Account r1 = r4.getAccount()     // Catch:{ ClassCastException -> 0x0090 }
            if (r1 != 0) goto L_0x0090
            goto L_0x004a
        L_0x0040:
            android.accounts.Account r2 = r4.getAccount()     // Catch:{ ClassCastException -> 0x0090 }
            boolean r1 = r1.equals(r2)     // Catch:{ ClassCastException -> 0x0090 }
            if (r1 == 0) goto L_0x0090
        L_0x004a:
            java.lang.String r1 = r3.zam     // Catch:{ ClassCastException -> 0x0090 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ ClassCastException -> 0x0090 }
            if (r1 == 0) goto L_0x005d
            java.lang.String r1 = r4.getServerClientId()     // Catch:{ ClassCastException -> 0x0090 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ ClassCastException -> 0x0090 }
            if (r1 == 0) goto L_0x0090
            goto L_0x006a
        L_0x005d:
            java.lang.String r1 = r3.zam     // Catch:{ ClassCastException -> 0x0090 }
            java.lang.String r2 = r4.getServerClientId()     // Catch:{ ClassCastException -> 0x0090 }
            boolean r1 = r1.equals(r2)     // Catch:{ ClassCastException -> 0x0090 }
            if (r1 != 0) goto L_0x006a
            goto L_0x0090
        L_0x006a:
            boolean r1 = r3.zal     // Catch:{ ClassCastException -> 0x0090 }
            boolean r2 = r4.isForceCodeForRefreshToken()     // Catch:{ ClassCastException -> 0x0090 }
            if (r1 != r2) goto L_0x0090
            boolean r1 = r3.zaj     // Catch:{ ClassCastException -> 0x0090 }
            boolean r2 = r4.isIdTokenRequested()     // Catch:{ ClassCastException -> 0x0090 }
            if (r1 != r2) goto L_0x0090
            boolean r1 = r3.zak     // Catch:{ ClassCastException -> 0x0090 }
            boolean r2 = r4.isServerAuthCodeRequested()     // Catch:{ ClassCastException -> 0x0090 }
            if (r1 != r2) goto L_0x0090
            java.lang.String r1 = r3.zap     // Catch:{ ClassCastException -> 0x0090 }
            java.lang.String r4 = r4.getLogSessionId()     // Catch:{ ClassCastException -> 0x0090 }
            boolean r4 = android.text.TextUtils.equals(r1, r4)     // Catch:{ ClassCastException -> 0x0090 }
            if (r4 == 0) goto L_0x0090
            r4 = 1
            return r4
        L_0x0090:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.auth.api.signin.GoogleSignInOptions.equals(java.lang.Object):boolean");
    }

    @KeepForSdk
    public Account getAccount() {
        return this.zai;
    }

    @KeepForSdk
    public ArrayList<GoogleSignInOptionsExtensionParcelable> getExtensions() {
        return this.zao;
    }

    @KeepForSdk
    public String getLogSessionId() {
        return this.zap;
    }

    public Scope[] getScopeArray() {
        return (Scope[]) this.zah.toArray(new Scope[this.zah.size()]);
    }

    @KeepForSdk
    public ArrayList<Scope> getScopes() {
        return new ArrayList<>(this.zah);
    }

    @KeepForSdk
    public String getServerClientId() {
        return this.zam;
    }

    public int hashCode() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = this.zah;
        int size = arrayList2.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(((Scope) arrayList2.get(i)).getScopeUri());
        }
        Collections.sort(arrayList);
        HashAccumulator hashAccumulator = new HashAccumulator();
        hashAccumulator.addObject(arrayList);
        hashAccumulator.addObject(this.zai);
        hashAccumulator.addObject(this.zam);
        hashAccumulator.zaa(this.zal);
        hashAccumulator.zaa(this.zaj);
        hashAccumulator.zaa(this.zak);
        hashAccumulator.addObject(this.zap);
        return hashAccumulator.hash();
    }

    @KeepForSdk
    public boolean isForceCodeForRefreshToken() {
        return this.zal;
    }

    @KeepForSdk
    public boolean isIdTokenRequested() {
        return this.zaj;
    }

    @KeepForSdk
    public boolean isServerAuthCodeRequested() {
        return this.zak;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = this.zaf;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, i2);
        SafeParcelWriter.writeTypedList(parcel, 2, getScopes(), false);
        SafeParcelWriter.writeParcelable(parcel, 3, getAccount(), i, false);
        SafeParcelWriter.writeBoolean(parcel, 4, isIdTokenRequested());
        SafeParcelWriter.writeBoolean(parcel, 5, isServerAuthCodeRequested());
        SafeParcelWriter.writeBoolean(parcel, 6, isForceCodeForRefreshToken());
        SafeParcelWriter.writeString(parcel, 7, getServerClientId(), false);
        SafeParcelWriter.writeString(parcel, 8, this.zan, false);
        SafeParcelWriter.writeTypedList(parcel, 9, getExtensions(), false);
        SafeParcelWriter.writeString(parcel, 10, getLogSessionId(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String zaf() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            Collections.sort(this.zah, zag);
            Iterator it = this.zah.iterator();
            while (it.hasNext()) {
                jSONArray.put(((Scope) it.next()).getScopeUri());
            }
            jSONObject.put("scopes", jSONArray);
            Account account = this.zai;
            if (account != null) {
                jSONObject.put("accountName", account.name);
            }
            jSONObject.put("idTokenRequested", this.zaj);
            jSONObject.put("forceCodeForRefreshToken", this.zal);
            jSONObject.put("serverAuthRequested", this.zak);
            if (!TextUtils.isEmpty(this.zam)) {
                jSONObject.put("serverClientId", this.zam);
            }
            if (!TextUtils.isEmpty(this.zan)) {
                jSONObject.put("hostedDomain", this.zan);
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private GoogleSignInOptions(int i, ArrayList arrayList, Account account, boolean z, boolean z2, boolean z3, String str, String str2, Map map, String str3) {
        this.zaf = i;
        this.zah = arrayList;
        this.zai = account;
        this.zaj = z;
        this.zak = z2;
        this.zal = z3;
        this.zam = str;
        this.zan = str2;
        this.zao = new ArrayList(map.values());
        this.zaq = map;
        this.zap = str3;
    }
}
