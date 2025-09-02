package com.swmansion.rnscreens;

import android.util.Log;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.viewmanagers.RNSSearchBarManagerDelegate;
import com.facebook.react.viewmanagers.RNSSearchBarManagerInterface;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "RNSSearchBar")
public final class SearchBarManager extends ViewGroupManager<SearchBarView> implements RNSSearchBarManagerInterface<SearchBarView> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String REACT_CLASS = "RNSSearchBar";
    private final ViewManagerDelegate<SearchBarView> delegate = new RNSSearchBarManagerDelegate(this);

    /* access modifiers changed from: protected */
    public ViewManagerDelegate<SearchBarView> getDelegate() {
        return this.delegate;
    }

    public String getName() {
        return REACT_CLASS;
    }

    /* access modifiers changed from: protected */
    public SearchBarView createViewInstance(ThemedReactContext themedReactContext) {
        Intrinsics.checkNotNullParameter(themedReactContext, "context");
        return new SearchBarView(themedReactContext);
    }

    /* access modifiers changed from: protected */
    public void onAfterUpdateTransaction(SearchBarView searchBarView) {
        Intrinsics.checkNotNullParameter(searchBarView, "view");
        super.onAfterUpdateTransaction(searchBarView);
        searchBarView.onUpdate();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0036, code lost:
        if (r3.equals("none") != false) goto L_0x0041;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0040, code lost:
        throw new com.facebook.react.bridge.JSApplicationIllegalArgumentException("Forbidden auto capitalize value passed");
     */
    @com.facebook.react.uimanager.annotations.ReactProp(name = "autoCapitalize")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setAutoCapitalize(com.swmansion.rnscreens.SearchBarView r2, java.lang.String r3) {
        /*
            r1 = this;
            java.lang.String r0 = "view"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            if (r3 == 0) goto L_0x0041
            int r0 = r3.hashCode()
            switch(r0) {
                case 3387192: goto L_0x0030;
                case 113318569: goto L_0x0025;
                case 490141296: goto L_0x001a;
                case 1245424234: goto L_0x000f;
                default: goto L_0x000e;
            }
        L_0x000e:
            goto L_0x0039
        L_0x000f:
            java.lang.String r0 = "characters"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0039
            com.swmansion.rnscreens.SearchBarView$SearchBarAutoCapitalize r3 = com.swmansion.rnscreens.SearchBarView.SearchBarAutoCapitalize.CHARACTERS
            goto L_0x0043
        L_0x001a:
            java.lang.String r0 = "sentences"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0039
            com.swmansion.rnscreens.SearchBarView$SearchBarAutoCapitalize r3 = com.swmansion.rnscreens.SearchBarView.SearchBarAutoCapitalize.SENTENCES
            goto L_0x0043
        L_0x0025:
            java.lang.String r0 = "words"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0039
            com.swmansion.rnscreens.SearchBarView$SearchBarAutoCapitalize r3 = com.swmansion.rnscreens.SearchBarView.SearchBarAutoCapitalize.WORDS
            goto L_0x0043
        L_0x0030:
            java.lang.String r0 = "none"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0039
            goto L_0x0041
        L_0x0039:
            com.facebook.react.bridge.JSApplicationIllegalArgumentException r2 = new com.facebook.react.bridge.JSApplicationIllegalArgumentException
            java.lang.String r3 = "Forbidden auto capitalize value passed"
            r2.<init>(r3)
            throw r2
        L_0x0041:
            com.swmansion.rnscreens.SearchBarView$SearchBarAutoCapitalize r3 = com.swmansion.rnscreens.SearchBarView.SearchBarAutoCapitalize.NONE
        L_0x0043:
            r2.setAutoCapitalize(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.rnscreens.SearchBarManager.setAutoCapitalize(com.swmansion.rnscreens.SearchBarView, java.lang.String):void");
    }

    @ReactProp(name = "autoFocus")
    public final void setAutoFocus(SearchBarView searchBarView, Boolean bool) {
        Intrinsics.checkNotNullParameter(searchBarView, "view");
        searchBarView.setAutoFocus(bool != null ? bool.booleanValue() : false);
    }

    @ReactProp(customType = "Color", name = "barTintColor")
    public void setBarTintColor(SearchBarView searchBarView, Integer num) {
        Intrinsics.checkNotNullParameter(searchBarView, "view");
        searchBarView.setTintColor(num);
    }

    @ReactProp(name = "disableBackButtonOverride")
    public void setDisableBackButtonOverride(SearchBarView searchBarView, boolean z) {
        Intrinsics.checkNotNullParameter(searchBarView, "view");
        boolean z2 = true;
        if (z) {
            z2 = false;
        }
        searchBarView.setShouldOverrideBackButton(z2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002b, code lost:
        if (r3.equals("text") != false) goto L_0x0041;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0040, code lost:
        throw new com.facebook.react.bridge.JSApplicationIllegalArgumentException("Forbidden input type value");
     */
    @com.facebook.react.uimanager.annotations.ReactProp(name = "inputType")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setInputType(com.swmansion.rnscreens.SearchBarView r2, java.lang.String r3) {
        /*
            r1 = this;
            java.lang.String r0 = "view"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            if (r3 == 0) goto L_0x0041
            int r0 = r3.hashCode()
            switch(r0) {
                case -1034364087: goto L_0x002e;
                case 3556653: goto L_0x0025;
                case 96619420: goto L_0x001a;
                case 106642798: goto L_0x000f;
                default: goto L_0x000e;
            }
        L_0x000e:
            goto L_0x0039
        L_0x000f:
            java.lang.String r0 = "phone"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0039
            com.swmansion.rnscreens.SearchBarView$SearchBarInputTypes r3 = com.swmansion.rnscreens.SearchBarView.SearchBarInputTypes.PHONE
            goto L_0x0043
        L_0x001a:
            java.lang.String r0 = "email"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0039
            com.swmansion.rnscreens.SearchBarView$SearchBarInputTypes r3 = com.swmansion.rnscreens.SearchBarView.SearchBarInputTypes.EMAIL
            goto L_0x0043
        L_0x0025:
            java.lang.String r0 = "text"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0039
            goto L_0x0041
        L_0x002e:
            java.lang.String r0 = "number"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0039
            com.swmansion.rnscreens.SearchBarView$SearchBarInputTypes r3 = com.swmansion.rnscreens.SearchBarView.SearchBarInputTypes.NUMBER
            goto L_0x0043
        L_0x0039:
            com.facebook.react.bridge.JSApplicationIllegalArgumentException r2 = new com.facebook.react.bridge.JSApplicationIllegalArgumentException
            java.lang.String r3 = "Forbidden input type value"
            r2.<init>(r3)
            throw r2
        L_0x0041:
            com.swmansion.rnscreens.SearchBarView$SearchBarInputTypes r3 = com.swmansion.rnscreens.SearchBarView.SearchBarInputTypes.TEXT
        L_0x0043:
            r2.setInputType(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.rnscreens.SearchBarManager.setInputType(com.swmansion.rnscreens.SearchBarView, java.lang.String):void");
    }

    @ReactProp(name = "placeholder")
    public void setPlaceholder(SearchBarView searchBarView, String str) {
        Intrinsics.checkNotNullParameter(searchBarView, "view");
        if (str != null) {
            searchBarView.setPlaceholder(str);
        }
    }

    @ReactProp(customType = "Color", name = "textColor")
    public void setTextColor(SearchBarView searchBarView, Integer num) {
        Intrinsics.checkNotNullParameter(searchBarView, "view");
        searchBarView.setTextColor(num);
    }

    @ReactProp(customType = "Color", name = "headerIconColor")
    public void setHeaderIconColor(SearchBarView searchBarView, Integer num) {
        Intrinsics.checkNotNullParameter(searchBarView, "view");
        searchBarView.setHeaderIconColor(num);
    }

    @ReactProp(customType = "Color", name = "hintTextColor")
    public void setHintTextColor(SearchBarView searchBarView, Integer num) {
        Intrinsics.checkNotNullParameter(searchBarView, "view");
        searchBarView.setHintTextColor(num);
    }

    @ReactProp(name = "shouldShowHintSearchIcon")
    public void setShouldShowHintSearchIcon(SearchBarView searchBarView, boolean z) {
        Intrinsics.checkNotNullParameter(searchBarView, "view");
        searchBarView.setShouldShowHintSearchIcon(z);
    }

    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of("topSearchBlur", MapBuilder.of("registrationName", "onSearchBlur"), "topChangeText", MapBuilder.of("registrationName", "onChangeText"), "topClose", MapBuilder.of("registrationName", "onClose"), "topSearchFocus", MapBuilder.of("registrationName", "onSearchFocus"), "topOpen", MapBuilder.of("registrationName", "onOpen"), "topSearchButtonPress", MapBuilder.of("registrationName", "onSearchButtonPress"));
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    private final void logNotAvailable(String str) {
        Log.w("[RNScreens]", str + " prop is not available on Android");
    }

    public void blur(SearchBarView searchBarView) {
        if (searchBarView != null) {
            searchBarView.handleBlurJsRequest();
        }
    }

    public void focus(SearchBarView searchBarView) {
        if (searchBarView != null) {
            searchBarView.handleFocusJsRequest();
        }
    }

    public void clearText(SearchBarView searchBarView) {
        if (searchBarView != null) {
            searchBarView.handleClearTextJsRequest();
        }
    }

    public void toggleCancelButton(SearchBarView searchBarView, boolean z) {
        if (searchBarView != null) {
            searchBarView.handleToggleCancelButtonJsRequest(z);
        }
    }

    public void setText(SearchBarView searchBarView, String str) {
        if (searchBarView != null) {
            searchBarView.handleSetTextJsRequest(str);
        }
    }

    public void cancelSearch(SearchBarView searchBarView) {
        if (searchBarView != null) {
            searchBarView.handleFocusJsRequest();
        }
    }

    public void setPlacement(SearchBarView searchBarView, String str) {
        Intrinsics.checkNotNullParameter(searchBarView, "view");
        logNotAvailable("setPlacement");
    }

    public void setHideWhenScrolling(SearchBarView searchBarView, boolean z) {
        logNotAvailable("hideWhenScrolling");
    }

    public void setObscureBackground(SearchBarView searchBarView, boolean z) {
        logNotAvailable("hideNavigationBar");
    }

    public void setHideNavigationBar(SearchBarView searchBarView, boolean z) {
        logNotAvailable("hideNavigationBar");
    }

    public void setCancelButtonText(SearchBarView searchBarView, String str) {
        logNotAvailable("cancelButtonText");
    }

    public void setTintColor(SearchBarView searchBarView, Integer num) {
        logNotAvailable("tintColor");
    }
}
