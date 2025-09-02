package com.swmansion.rnscreens;

import android.content.Context;
import android.view.View;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import kotlin.jvm.internal.Intrinsics;

public final class CustomSearchView extends SearchView {
    private final FragmentBackPressOverrider backPressOverrider;
    private OnBackPressedCallback onBackPressedCallback;
    private SearchView.OnCloseListener onCloseListener;
    private View.OnClickListener onSearchClickedListener;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CustomSearchView(Context context, Fragment fragment) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        CustomSearchView$onBackPressedCallback$1 customSearchView$onBackPressedCallback$1 = new CustomSearchView$onBackPressedCallback$1(this);
        this.onBackPressedCallback = customSearchView$onBackPressedCallback$1;
        this.backPressOverrider = new FragmentBackPressOverrider(fragment, customSearchView$onBackPressedCallback$1);
        super.setOnSearchClickListener(new CustomSearchView$$ExternalSyntheticLambda0(this));
        super.setOnCloseListener(new CustomSearchView$$ExternalSyntheticLambda1(this));
        setMaxWidth(Integer.MAX_VALUE);
    }

    public final void setOverrideBackAction(boolean z) {
        this.backPressOverrider.setOverrideBackAction(z);
    }

    public final boolean getOverrideBackAction() {
        return this.backPressOverrider.getOverrideBackAction();
    }

    public final void focus() {
        setIconified(false);
        requestFocusFromTouch();
    }

    public final void clearText() {
        setQuery("", false);
    }

    public final void setText(String str) {
        Intrinsics.checkNotNullParameter(str, "text");
        setQuery(str, false);
    }

    public void setOnCloseListener(SearchView.OnCloseListener onCloseListener2) {
        this.onCloseListener = onCloseListener2;
    }

    public void setOnSearchClickListener(View.OnClickListener onClickListener) {
        this.onSearchClickedListener = onClickListener;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isIconified()) {
            this.backPressOverrider.maybeAddBackCallback();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.backPressOverrider.removeBackCallbackIfAdded();
    }

    /* access modifiers changed from: private */
    public static final void _init_$lambda$0(CustomSearchView customSearchView, View view) {
        View.OnClickListener onClickListener = customSearchView.onSearchClickedListener;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
        customSearchView.backPressOverrider.maybeAddBackCallback();
    }

    /* access modifiers changed from: private */
    public static final boolean _init_$lambda$1(CustomSearchView customSearchView) {
        SearchView.OnCloseListener onCloseListener2 = customSearchView.onCloseListener;
        boolean onClose = onCloseListener2 != null ? onCloseListener2.onClose() : false;
        customSearchView.backPressOverrider.removeBackCallbackIfAdded();
        return onClose;
    }
}
