package com.facebook.react.views.swiperefresh;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.facebook.react.uimanager.ThemedReactContext;

public final /* synthetic */ class SwipeRefreshLayoutManager$$ExternalSyntheticLambda0 implements SwipeRefreshLayout.OnRefreshListener {
    public final /* synthetic */ ThemedReactContext f$0;
    public final /* synthetic */ ReactSwipeRefreshLayout f$1;

    public /* synthetic */ SwipeRefreshLayoutManager$$ExternalSyntheticLambda0(ThemedReactContext themedReactContext, ReactSwipeRefreshLayout reactSwipeRefreshLayout) {
        this.f$0 = themedReactContext;
        this.f$1 = reactSwipeRefreshLayout;
    }

    public final void onRefresh() {
        SwipeRefreshLayoutManager.addEventEmitters$lambda$0(this.f$0, this.f$1);
    }
}
