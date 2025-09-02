package com.swmansion.rnscreens;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.uimanager.ReactPointerEventsView;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.text.ReactTypefaceUtils;
import com.swmansion.rnscreens.ScreenStackHeaderSubview;
import com.swmansion.rnscreens.events.HeaderAttachedEvent;
import com.swmansion.rnscreens.events.HeaderDetachedEvent;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ScreenStackHeaderConfig extends FabricEnabledHeaderConfigViewGroup implements ReactPointerEventsView {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private boolean backButtonInCustomView;
    private final View.OnClickListener backClickListener;
    private Integer backgroundColor;
    private final ArrayList configSubviews;
    private final int defaultStartInset;
    private final int defaultStartInsetWithNavigation;
    private String direction;
    private boolean isAttachedToWindow;
    private boolean isBackButtonHidden;
    private boolean isDestroyed;
    private boolean isHeaderHidden;
    private boolean isHeaderTranslucent;
    private boolean isShadowHidden;
    private boolean isTitleEmpty;
    private boolean isTopInsetEnabled;
    private final ReactPointerEventsView pointerEventsImpl;
    private int tintColor;
    private String title;
    private int titleColor;
    private String titleFontFamily;
    private float titleFontSize;
    private int titleFontWeight;
    private final CustomToolbar toolbar;

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                com.swmansion.rnscreens.ScreenStackHeaderSubview$Type[] r0 = com.swmansion.rnscreens.ScreenStackHeaderSubview.Type.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.swmansion.rnscreens.ScreenStackHeaderSubview$Type r1 = com.swmansion.rnscreens.ScreenStackHeaderSubview.Type.LEFT     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.swmansion.rnscreens.ScreenStackHeaderSubview$Type r1 = com.swmansion.rnscreens.ScreenStackHeaderSubview.Type.RIGHT     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.swmansion.rnscreens.ScreenStackHeaderSubview$Type r1 = com.swmansion.rnscreens.ScreenStackHeaderSubview.Type.CENTER     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.swmansion.rnscreens.ScreenStackHeaderConfig.WhenMappings.<clinit>():void");
        }
    }

    public PointerEvents getPointerEvents() {
        return this.pointerEventsImpl.getPointerEvents();
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ScreenStackHeaderConfig(Context context, ReactPointerEventsView reactPointerEventsView) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(reactPointerEventsView, "pointerEventsImpl");
        this.pointerEventsImpl = reactPointerEventsView;
        this.configSubviews = new ArrayList(3);
        this.isTopInsetEnabled = true;
        this.backClickListener = new ScreenStackHeaderConfig$$ExternalSyntheticLambda0(this);
        setVisibility(8);
        CustomToolbar customToolbar = new CustomToolbar(context, this);
        this.toolbar = customToolbar;
        this.defaultStartInset = customToolbar.getContentInsetStart();
        this.defaultStartInsetWithNavigation = customToolbar.getContentInsetStartWithNavigation();
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(16843827, typedValue, true)) {
            customToolbar.setBackgroundColor(typedValue.data);
        }
        customToolbar.setClipChildren(false);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ScreenStackHeaderConfig(Context context) {
        this(context, new PointerEventsBoxNoneImpl());
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public final CustomToolbar getToolbar() {
        return this.toolbar;
    }

    public final boolean isHeaderHidden() {
        return this.isHeaderHidden;
    }

    public final void setHeaderHidden(boolean z) {
        this.isHeaderHidden = z;
    }

    public final void setHeaderTranslucent(boolean z) {
        this.isHeaderTranslucent = z;
    }

    public final boolean isTopInsetEnabled() {
        return this.isTopInsetEnabled;
    }

    /* access modifiers changed from: private */
    public static final void backClickListener$lambda$1(ScreenStackHeaderConfig screenStackHeaderConfig, View view) {
        ScreenStackFragment screenFragment = screenStackHeaderConfig.getScreenFragment();
        if (screenFragment != null) {
            ScreenStack screenStack = screenStackHeaderConfig.getScreenStack();
            if (screenStack != null && Intrinsics.areEqual((Object) screenStack.getRootScreen(), (Object) screenFragment.getScreen())) {
                Fragment parentFragment = screenFragment.getParentFragment();
                if (parentFragment instanceof ScreenStackFragment) {
                    ScreenStackFragment screenStackFragment = (ScreenStackFragment) parentFragment;
                    if (screenStackFragment.getScreen().getNativeBackButtonDismissalEnabled()) {
                        screenStackFragment.dismissFromContainer();
                    } else {
                        screenStackFragment.dispatchHeaderBackButtonClickedEvent();
                    }
                }
            } else if (screenFragment.getScreen().getNativeBackButtonDismissalEnabled()) {
                screenFragment.dismissFromContainer();
            } else {
                screenFragment.dispatchHeaderBackButtonClickedEvent();
            }
        }
    }

    public final void setTitleEmpty(boolean z) {
        this.isTitleEmpty = z;
    }

    public final int getPreferredContentInsetStart() {
        return this.defaultStartInset;
    }

    public final int getPreferredContentInsetEnd() {
        return this.defaultStartInset;
    }

    public final int getPreferredContentInsetStartWithNavigation() {
        if (this.isTitleEmpty) {
            return 0;
        }
        return this.defaultStartInsetWithNavigation;
    }

    public final void destroy() {
        this.isDestroyed = true;
    }

    public final void onNativeToolbarLayout(Toolbar toolbar2, boolean z) {
        int i;
        Object obj;
        Intrinsics.checkNotNullParameter(toolbar2, "toolbar");
        if (z) {
            if (toolbar2.getNavigationIcon() != null) {
                i = toolbar2.getCurrentContentInsetStart() + toolbar2.getPaddingStart();
            } else {
                i = Math.max(toolbar2.getCurrentContentInsetStart(), toolbar2.getPaddingStart());
            }
            Iterator it = this.configSubviews.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (((ScreenStackHeaderSubview) obj).getType() == ScreenStackHeaderSubview.Type.LEFT) {
                    break;
                }
            }
            ScreenStackHeaderSubview screenStackHeaderSubview = (ScreenStackHeaderSubview) obj;
            if (screenStackHeaderSubview != null) {
                i = screenStackHeaderSubview.getLeft();
            }
            updateHeaderConfigState(toolbar2.getWidth(), toolbar2.getHeight(), i, toolbar2.getCurrentContentInsetEnd() + toolbar2.getPaddingEnd());
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.isAttachedToWindow = true;
        int surfaceId = UIManagerHelper.getSurfaceId((View) this);
        Context context = getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
        EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag((ReactContext) context, getId());
        if (eventDispatcherForReactTag != null) {
            eventDispatcherForReactTag.dispatchEvent(new HeaderAttachedEvent(surfaceId, getId()));
        }
        onUpdate();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.isAttachedToWindow = false;
        int surfaceId = UIManagerHelper.getSurfaceId((View) this);
        Context context = getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
        EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag((ReactContext) context, getId());
        if (eventDispatcherForReactTag != null) {
            eventDispatcherForReactTag.dispatchEvent(new HeaderDetachedEvent(surfaceId, getId()));
        }
    }

    private final Screen getScreen() {
        ViewParent parent = getParent();
        if (parent instanceof Screen) {
            return (Screen) parent;
        }
        return null;
    }

    private final ScreenStack getScreenStack() {
        Screen screen = getScreen();
        ViewParent container = screen != null ? screen.getContainer() : null;
        if (container instanceof ScreenStack) {
            return (ScreenStack) container;
        }
        return null;
    }

    public final ScreenStackFragment getScreenFragment() {
        ViewParent parent = getParent();
        if (!(parent instanceof Screen)) {
            return null;
        }
        Fragment fragment = ((Screen) parent).getFragment();
        if (fragment instanceof ScreenStackFragment) {
            return (ScreenStackFragment) fragment;
        }
        return null;
    }

    public final void onUpdate() {
        Drawable navigationIcon;
        ScreenStackFragment screenFragment;
        ScreenStackFragment screenFragment2;
        ReactContext reactContext;
        ScreenStack screenStack = getScreenStack();
        boolean z = screenStack == null || Intrinsics.areEqual((Object) screenStack.getTopScreen(), (Object) getParent());
        if (this.isAttachedToWindow && z && !this.isDestroyed) {
            ScreenStackFragment screenFragment3 = getScreenFragment();
            AppCompatActivity appCompatActivity = (AppCompatActivity) (screenFragment3 != null ? screenFragment3.getActivity() : null);
            if (appCompatActivity != null) {
                String str = this.direction;
                if (str != null) {
                    if (Intrinsics.areEqual((Object) str, (Object) "rtl")) {
                        this.toolbar.setLayoutDirection(1);
                    } else if (Intrinsics.areEqual((Object) this.direction, (Object) "ltr")) {
                        this.toolbar.setLayoutDirection(0);
                    }
                }
                Screen screen = getScreen();
                if (screen != null) {
                    if (getContext() instanceof ReactContext) {
                        Context context = getContext();
                        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
                        reactContext = (ReactContext) context;
                    } else {
                        ScreenFragmentWrapper fragmentWrapper = screen.getFragmentWrapper();
                        reactContext = fragmentWrapper != null ? fragmentWrapper.tryGetContext() : null;
                    }
                    ScreenWindowTraits.INSTANCE.trySetWindowTraits$react_native_screens_release(screen, appCompatActivity, reactContext);
                }
                if (!this.isHeaderHidden) {
                    if (this.toolbar.getParent() == null && (screenFragment = getScreenFragment()) != null) {
                        screenFragment.setToolbar(this.toolbar);
                    }
                    appCompatActivity.setSupportActionBar(this.toolbar);
                    ActionBar supportActionBar = appCompatActivity.getSupportActionBar();
                    if (supportActionBar != null) {
                        ScreenStackFragment screenFragment4 = getScreenFragment();
                        supportActionBar.setDisplayHomeAsUpEnabled(screenFragment4 != null && screenFragment4.canNavigateBack() && !this.isBackButtonHidden);
                        supportActionBar.setTitle(this.title);
                        if (TextUtils.isEmpty(this.title)) {
                            this.isTitleEmpty = true;
                        }
                        this.toolbar.updateContentInsets();
                        this.toolbar.setNavigationOnClickListener(this.backClickListener);
                        ScreenStackFragment screenFragment5 = getScreenFragment();
                        if (screenFragment5 != null) {
                            screenFragment5.setToolbarShadowHidden(this.isShadowHidden);
                        }
                        ScreenStackFragment screenFragment6 = getScreenFragment();
                        if (screenFragment6 != null) {
                            screenFragment6.setToolbarTranslucent(this.isHeaderTranslucent);
                        }
                        TextView findTitleTextViewInToolbar = Companion.findTitleTextViewInToolbar(this.toolbar);
                        int i = this.titleColor;
                        if (i != 0) {
                            this.toolbar.setTitleTextColor(i);
                        }
                        if (findTitleTextViewInToolbar != null) {
                            String str2 = this.titleFontFamily;
                            if (str2 != null || this.titleFontWeight > 0) {
                                int i2 = this.titleFontWeight;
                                AssetManager assets = getContext().getAssets();
                                Intrinsics.checkNotNullExpressionValue(assets, "getAssets(...)");
                                findTitleTextViewInToolbar.setTypeface(ReactTypefaceUtils.applyStyles((Typeface) null, 0, i2, str2, assets));
                            }
                            float f = this.titleFontSize;
                            if (f > 0.0f) {
                                findTitleTextViewInToolbar.setTextSize(f);
                            }
                        }
                        Integer num = this.backgroundColor;
                        if (num != null) {
                            this.toolbar.setBackgroundColor(num.intValue());
                        }
                        if (!(this.tintColor == 0 || (navigationIcon = this.toolbar.getNavigationIcon()) == null)) {
                            navigationIcon.setColorFilter(new PorterDuffColorFilter(this.tintColor, PorterDuff.Mode.SRC_ATOP));
                        }
                        for (int childCount = this.toolbar.getChildCount() - 1; -1 < childCount; childCount--) {
                            if (this.toolbar.getChildAt(childCount) instanceof ScreenStackHeaderSubview) {
                                this.toolbar.removeViewAt(childCount);
                            }
                        }
                        int size = this.configSubviews.size();
                        for (int i3 = 0; i3 < size; i3++) {
                            Object obj = this.configSubviews.get(i3);
                            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
                            ScreenStackHeaderSubview screenStackHeaderSubview = (ScreenStackHeaderSubview) obj;
                            ScreenStackHeaderSubview.Type type = screenStackHeaderSubview.getType();
                            if (type == ScreenStackHeaderSubview.Type.BACK) {
                                View childAt = screenStackHeaderSubview.getChildAt(0);
                                ImageView imageView = childAt instanceof ImageView ? (ImageView) childAt : null;
                                if (imageView != null) {
                                    supportActionBar.setHomeAsUpIndicator(imageView.getDrawable());
                                } else {
                                    throw new JSApplicationIllegalArgumentException("Back button header config view should have Image as first child");
                                }
                            } else {
                                Toolbar.LayoutParams layoutParams = new Toolbar.LayoutParams(-2, -1);
                                int i4 = WhenMappings.$EnumSwitchMapping$0[type.ordinal()];
                                if (i4 == 1) {
                                    if (!this.backButtonInCustomView) {
                                        this.toolbar.setNavigationIcon((Drawable) null);
                                    }
                                    this.toolbar.setTitle((CharSequence) null);
                                    layoutParams.gravity = 8388611;
                                } else if (i4 == 2) {
                                    layoutParams.gravity = 8388613;
                                } else if (i4 == 3) {
                                    layoutParams.width = -1;
                                    layoutParams.gravity = 1;
                                    this.toolbar.setTitle((CharSequence) null);
                                }
                                screenStackHeaderSubview.setLayoutParams(layoutParams);
                                this.toolbar.addView(screenStackHeaderSubview);
                            }
                        }
                        return;
                    }
                    throw new IllegalArgumentException("Required value was null.");
                } else if (this.toolbar.getParent() != null && (screenFragment2 = getScreenFragment()) != null) {
                    screenFragment2.removeToolbar();
                }
            }
        }
    }

    private final void maybeUpdate() {
        Screen screen;
        if (getParent() != null && !this.isDestroyed && (screen = getScreen()) != null && !screen.isBeingRemoved()) {
            onUpdate();
        }
    }

    public final ScreenStackHeaderSubview getConfigSubview(int i) {
        Object obj = this.configSubviews.get(i);
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        return (ScreenStackHeaderSubview) obj;
    }

    public final int getConfigSubviewsCount() {
        return this.configSubviews.size();
    }

    public final void removeConfigSubview(int i) {
        this.configSubviews.remove(i);
        maybeUpdate();
    }

    public final void removeAllConfigSubviews() {
        this.configSubviews.clear();
        maybeUpdate();
    }

    public final void addConfigSubview(ScreenStackHeaderSubview screenStackHeaderSubview, int i) {
        Intrinsics.checkNotNullParameter(screenStackHeaderSubview, "child");
        this.configSubviews.add(i, screenStackHeaderSubview);
        maybeUpdate();
    }

    public final void setTitle(String str) {
        this.title = str;
    }

    public final void setTitleFontFamily(String str) {
        this.titleFontFamily = str;
    }

    public final void setTitleFontWeight(String str) {
        this.titleFontWeight = ReactTypefaceUtils.parseFontWeight(str);
    }

    public final void setTitleFontSize(float f) {
        this.titleFontSize = f;
    }

    public final void setTitleColor(int i) {
        this.titleColor = i;
    }

    public final void setTintColor(int i) {
        this.tintColor = i;
    }

    public final void setTopInsetEnabled(boolean z) {
        this.isTopInsetEnabled = z;
    }

    public final void setBackgroundColor(Integer num) {
        this.backgroundColor = num;
    }

    public final void setHideShadow(boolean z) {
        this.isShadowHidden = z;
    }

    public final void setHideBackButton(boolean z) {
        this.isBackButtonHidden = z;
    }

    public final void setHidden(boolean z) {
        this.isHeaderHidden = z;
    }

    public final void setTranslucent(boolean z) {
        this.isHeaderTranslucent = z;
    }

    public final void setBackButtonInCustomView(boolean z) {
        this.backButtonInCustomView = z;
    }

    public final void setDirection(String str) {
        this.direction = str;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TextView findTitleTextViewInToolbar(Toolbar toolbar) {
            Intrinsics.checkNotNullParameter(toolbar, "toolbar");
            int childCount = toolbar.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = toolbar.getChildAt(i);
                if (childAt instanceof TextView) {
                    TextView textView = (TextView) childAt;
                    if (TextUtils.equals(textView.getText(), toolbar.getTitle())) {
                        return textView;
                    }
                }
            }
            return null;
        }
    }
}
