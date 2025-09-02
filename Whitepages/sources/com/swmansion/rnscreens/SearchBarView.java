package com.swmansion.rnscreens;

import android.content.Context;
import android.view.View;
import android.view.ViewParent;
import androidx.appcompat.widget.SearchView;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.view.ReactViewGroup;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import com.salesforce.marketingcloud.b;
import com.swmansion.rnscreens.ScreenStackHeaderSubview;
import com.swmansion.rnscreens.events.SearchBarBlurEvent;
import com.swmansion.rnscreens.events.SearchBarChangeTextEvent;
import com.swmansion.rnscreens.events.SearchBarCloseEvent;
import com.swmansion.rnscreens.events.SearchBarFocusEvent;
import com.swmansion.rnscreens.events.SearchBarOpenEvent;
import com.swmansion.rnscreens.events.SearchBarSearchButtonPressEvent;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.http2.Http2;

public final class SearchBarView extends ReactViewGroup {
    private boolean areListenersSet;
    private SearchBarAutoCapitalize autoCapitalize = SearchBarAutoCapitalize.NONE;
    private boolean autoFocus;
    private Integer headerIconColor;
    private Integer hintTextColor;
    private SearchBarInputTypes inputType = SearchBarInputTypes.TEXT;
    private String placeholder = "";
    private SearchViewFormatter searchViewFormatter;
    private boolean shouldOverrideBackButton = true;
    private boolean shouldShowHintSearchIcon = true;
    private final int surfaceId = UIManagerHelper.getSurfaceId((View) this);
    private Integer textColor;
    private Integer tintColor;

    public final void handleToggleCancelButtonJsRequest(boolean z) {
    }

    public SearchBarView(ReactContext reactContext) {
        super(reactContext);
    }

    public final SearchBarInputTypes getInputType() {
        return this.inputType;
    }

    public final void setInputType(SearchBarInputTypes searchBarInputTypes) {
        Intrinsics.checkNotNullParameter(searchBarInputTypes, "<set-?>");
        this.inputType = searchBarInputTypes;
    }

    public final SearchBarAutoCapitalize getAutoCapitalize() {
        return this.autoCapitalize;
    }

    public final void setAutoCapitalize(SearchBarAutoCapitalize searchBarAutoCapitalize) {
        Intrinsics.checkNotNullParameter(searchBarAutoCapitalize, "<set-?>");
        this.autoCapitalize = searchBarAutoCapitalize;
    }

    public final Integer getTextColor() {
        return this.textColor;
    }

    public final void setTextColor(Integer num) {
        this.textColor = num;
    }

    public final Integer getTintColor() {
        return this.tintColor;
    }

    public final void setTintColor(Integer num) {
        this.tintColor = num;
    }

    public final Integer getHeaderIconColor() {
        return this.headerIconColor;
    }

    public final void setHeaderIconColor(Integer num) {
        this.headerIconColor = num;
    }

    public final Integer getHintTextColor() {
        return this.hintTextColor;
    }

    public final void setHintTextColor(Integer num) {
        this.hintTextColor = num;
    }

    public final String getPlaceholder() {
        return this.placeholder;
    }

    public final void setPlaceholder(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.placeholder = str;
    }

    public final boolean getShouldOverrideBackButton() {
        return this.shouldOverrideBackButton;
    }

    public final void setShouldOverrideBackButton(boolean z) {
        this.shouldOverrideBackButton = z;
    }

    public final boolean getAutoFocus() {
        return this.autoFocus;
    }

    public final void setAutoFocus(boolean z) {
        this.autoFocus = z;
    }

    public final boolean getShouldShowHintSearchIcon() {
        return this.shouldShowHintSearchIcon;
    }

    public final void setShouldShowHintSearchIcon(boolean z) {
        this.shouldShowHintSearchIcon = z;
    }

    private final ScreenStackHeaderConfig getHeaderConfig() {
        ViewParent parent = getParent();
        if (parent instanceof ScreenStackHeaderSubview) {
            return ((ScreenStackHeaderSubview) parent).getConfig();
        }
        return null;
    }

    private final ScreenStackFragment getScreenStackFragment() {
        ScreenStackHeaderConfig headerConfig = getHeaderConfig();
        if (headerConfig != null) {
            return headerConfig.getScreenFragment();
        }
        return null;
    }

    public final void onUpdate() {
        setSearchViewProps();
    }

    private final void setSearchViewProps() {
        ScreenStackFragment screenStackFragment = getScreenStackFragment();
        CustomSearchView searchView = screenStackFragment != null ? screenStackFragment.getSearchView() : null;
        if (searchView != null) {
            if (!this.areListenersSet) {
                setSearchViewListeners(searchView);
                this.areListenersSet = true;
            }
            searchView.setInputType(this.inputType.toAndroidInputType(this.autoCapitalize));
            SearchViewFormatter searchViewFormatter2 = this.searchViewFormatter;
            if (searchViewFormatter2 != null) {
                searchViewFormatter2.setTextColor(this.textColor);
            }
            SearchViewFormatter searchViewFormatter3 = this.searchViewFormatter;
            if (searchViewFormatter3 != null) {
                searchViewFormatter3.setTintColor(this.tintColor);
            }
            SearchViewFormatter searchViewFormatter4 = this.searchViewFormatter;
            if (searchViewFormatter4 != null) {
                searchViewFormatter4.setHeaderIconColor(this.headerIconColor);
            }
            SearchViewFormatter searchViewFormatter5 = this.searchViewFormatter;
            if (searchViewFormatter5 != null) {
                searchViewFormatter5.setHintTextColor(this.hintTextColor);
            }
            SearchViewFormatter searchViewFormatter6 = this.searchViewFormatter;
            if (searchViewFormatter6 != null) {
                searchViewFormatter6.setPlaceholder(this.placeholder, this.shouldShowHintSearchIcon);
            }
            searchView.setOverrideBackAction(this.shouldOverrideBackButton);
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ScreenStackFragment screenStackFragment = getScreenStackFragment();
        if (screenStackFragment != null) {
            screenStackFragment.setOnSearchViewCreate(new SearchBarView$$ExternalSyntheticLambda0(this));
        }
    }

    /* access modifiers changed from: private */
    public static final Unit onAttachedToWindow$lambda$0(SearchBarView searchBarView, CustomSearchView customSearchView) {
        ScreenStackFragment screenStackFragment;
        CustomSearchView searchView;
        Intrinsics.checkNotNullParameter(customSearchView, "newSearchView");
        if (searchBarView.searchViewFormatter == null) {
            searchBarView.searchViewFormatter = new SearchViewFormatter(customSearchView);
        }
        searchBarView.setSearchViewProps();
        if (!(!searchBarView.autoFocus || (screenStackFragment = searchBarView.getScreenStackFragment()) == null || (searchView = screenStackFragment.getSearchView()) == null)) {
            searchView.focus();
        }
        return Unit.INSTANCE;
    }

    private final void setSearchViewListeners(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchBarView$setSearchViewListeners$1(this));
        searchView.setOnQueryTextFocusChangeListener(new SearchBarView$$ExternalSyntheticLambda1(this));
        searchView.setOnCloseListener(new SearchBarView$$ExternalSyntheticLambda2(this));
        searchView.setOnSearchClickListener(new SearchBarView$$ExternalSyntheticLambda3(this));
    }

    /* access modifiers changed from: private */
    public static final void setSearchViewListeners$lambda$1(SearchBarView searchBarView, View view, boolean z) {
        searchBarView.handleFocusChange(z);
    }

    /* access modifiers changed from: private */
    public static final boolean setSearchViewListeners$lambda$2(SearchBarView searchBarView) {
        searchBarView.handleClose();
        return false;
    }

    /* access modifiers changed from: private */
    public static final void setSearchViewListeners$lambda$3(SearchBarView searchBarView, View view) {
        searchBarView.handleOpen();
    }

    /* access modifiers changed from: private */
    public final void handleTextChange(String str) {
        sendEvent(new SearchBarChangeTextEvent(this.surfaceId, getId(), str));
    }

    private final void handleFocusChange(boolean z) {
        sendEvent(z ? new SearchBarFocusEvent(this.surfaceId, getId()) : new SearchBarBlurEvent(this.surfaceId, getId()));
    }

    private final void handleClose() {
        sendEvent(new SearchBarCloseEvent(this.surfaceId, getId()));
        setToolbarElementsVisibility(0);
    }

    private final void handleOpen() {
        sendEvent(new SearchBarOpenEvent(this.surfaceId, getId()));
        setToolbarElementsVisibility(8);
    }

    /* access modifiers changed from: private */
    public final void handleTextSubmit(String str) {
        sendEvent(new SearchBarSearchButtonPressEvent(this.surfaceId, getId(), str));
    }

    private final void sendEvent(Event event) {
        Context context = getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
        EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag((ReactContext) context, getId());
        if (eventDispatcherForReactTag != null) {
            eventDispatcherForReactTag.dispatchEvent(event);
        }
    }

    public final void handleClearTextJsRequest() {
        CustomSearchView searchView;
        ScreenStackFragment screenStackFragment = getScreenStackFragment();
        if (screenStackFragment != null && (searchView = screenStackFragment.getSearchView()) != null) {
            searchView.clearText();
        }
    }

    public final void handleFocusJsRequest() {
        CustomSearchView searchView;
        ScreenStackFragment screenStackFragment = getScreenStackFragment();
        if (screenStackFragment != null && (searchView = screenStackFragment.getSearchView()) != null) {
            searchView.focus();
        }
    }

    public final void handleBlurJsRequest() {
        CustomSearchView searchView;
        ScreenStackFragment screenStackFragment = getScreenStackFragment();
        if (screenStackFragment != null && (searchView = screenStackFragment.getSearchView()) != null) {
            searchView.clearFocus();
        }
    }

    public final void handleSetTextJsRequest(String str) {
        ScreenStackFragment screenStackFragment;
        CustomSearchView searchView;
        if (str != null && (screenStackFragment = getScreenStackFragment()) != null && (searchView = screenStackFragment.getSearchView()) != null) {
            searchView.setText(str);
        }
    }

    private final void setToolbarElementsVisibility(int i) {
        ScreenStackHeaderConfig headerConfig = getHeaderConfig();
        int i2 = 0;
        int configSubviewsCount = headerConfig != null ? headerConfig.getConfigSubviewsCount() - 1 : 0;
        if (configSubviewsCount >= 0) {
            while (true) {
                ScreenStackHeaderConfig headerConfig2 = getHeaderConfig();
                ScreenStackHeaderSubview.Type type = null;
                ScreenStackHeaderSubview configSubview = headerConfig2 != null ? headerConfig2.getConfigSubview(i2) : null;
                if (configSubview != null) {
                    type = configSubview.getType();
                }
                if (!(type == ScreenStackHeaderSubview.Type.SEARCH_BAR || configSubview == null)) {
                    configSubview.setVisibility(i);
                }
                if (i2 != configSubviewsCount) {
                    i2++;
                } else {
                    return;
                }
            }
        }
    }

    public enum SearchBarAutoCapitalize {
        NONE,
        WORDS,
        SENTENCES,
        CHARACTERS;

        static {
            SearchBarAutoCapitalize[] $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }
    }

    public enum SearchBarInputTypes {
        ;

        public abstract int toAndroidInputType(SearchBarAutoCapitalize searchBarAutoCapitalize);

        static final class TEXT extends SearchBarInputTypes {

            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

                /* JADX WARNING: Can't wrap try/catch for region: R(11:0|1|2|3|4|5|6|7|8|9|11) */
                /* JADX WARNING: Failed to process nested try/catch */
                /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
                /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
                /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
                static {
                    /*
                        com.swmansion.rnscreens.SearchBarView$SearchBarAutoCapitalize[] r0 = com.swmansion.rnscreens.SearchBarView.SearchBarAutoCapitalize.values()
                        int r0 = r0.length
                        int[] r0 = new int[r0]
                        com.swmansion.rnscreens.SearchBarView$SearchBarAutoCapitalize r1 = com.swmansion.rnscreens.SearchBarView.SearchBarAutoCapitalize.NONE     // Catch:{ NoSuchFieldError -> 0x0010 }
                        int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                        r2 = 1
                        r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
                    L_0x0010:
                        com.swmansion.rnscreens.SearchBarView$SearchBarAutoCapitalize r1 = com.swmansion.rnscreens.SearchBarView.SearchBarAutoCapitalize.WORDS     // Catch:{ NoSuchFieldError -> 0x0019 }
                        int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                        r2 = 2
                        r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
                    L_0x0019:
                        com.swmansion.rnscreens.SearchBarView$SearchBarAutoCapitalize r1 = com.swmansion.rnscreens.SearchBarView.SearchBarAutoCapitalize.SENTENCES     // Catch:{ NoSuchFieldError -> 0x0022 }
                        int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                        r2 = 3
                        r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
                    L_0x0022:
                        com.swmansion.rnscreens.SearchBarView$SearchBarAutoCapitalize r1 = com.swmansion.rnscreens.SearchBarView.SearchBarAutoCapitalize.CHARACTERS     // Catch:{ NoSuchFieldError -> 0x002b }
                        int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                        r2 = 4
                        r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
                    L_0x002b:
                        $EnumSwitchMapping$0 = r0
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.swmansion.rnscreens.SearchBarView.SearchBarInputTypes.TEXT.WhenMappings.<clinit>():void");
                }
            }

            TEXT(String str, int i) {
                super(str, i, (DefaultConstructorMarker) null);
            }

            public int toAndroidInputType(SearchBarAutoCapitalize searchBarAutoCapitalize) {
                Intrinsics.checkNotNullParameter(searchBarAutoCapitalize, "capitalize");
                int i = WhenMappings.$EnumSwitchMapping$0[searchBarAutoCapitalize.ordinal()];
                if (i == 1) {
                    return 1;
                }
                if (i == 2) {
                    return UserMetadata.MAX_INTERNAL_KEY_SIZE;
                }
                if (i == 3) {
                    return Http2.INITIAL_MAX_FRAME_SIZE;
                }
                if (i == 4) {
                    return b.v;
                }
                throw new NoWhenBranchMatchedException();
            }
        }

        static {
            SearchBarInputTypes[] $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }

        static final class PHONE extends SearchBarInputTypes {
            public int toAndroidInputType(SearchBarAutoCapitalize searchBarAutoCapitalize) {
                Intrinsics.checkNotNullParameter(searchBarAutoCapitalize, "capitalize");
                return 3;
            }

            PHONE(String str, int i) {
                super(str, i, (DefaultConstructorMarker) null);
            }
        }

        static final class NUMBER extends SearchBarInputTypes {
            public int toAndroidInputType(SearchBarAutoCapitalize searchBarAutoCapitalize) {
                Intrinsics.checkNotNullParameter(searchBarAutoCapitalize, "capitalize");
                return 2;
            }

            NUMBER(String str, int i) {
                super(str, i, (DefaultConstructorMarker) null);
            }
        }

        static final class EMAIL extends SearchBarInputTypes {
            public int toAndroidInputType(SearchBarAutoCapitalize searchBarAutoCapitalize) {
                Intrinsics.checkNotNullParameter(searchBarAutoCapitalize, "capitalize");
                return 32;
            }

            EMAIL(String str, int i) {
                super(str, i, (DefaultConstructorMarker) null);
            }
        }
    }
}
