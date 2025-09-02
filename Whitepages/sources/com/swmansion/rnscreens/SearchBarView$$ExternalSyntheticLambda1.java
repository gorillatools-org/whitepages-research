package com.swmansion.rnscreens;

import android.view.View;

public final /* synthetic */ class SearchBarView$$ExternalSyntheticLambda1 implements View.OnFocusChangeListener {
    public final /* synthetic */ SearchBarView f$0;

    public /* synthetic */ SearchBarView$$ExternalSyntheticLambda1(SearchBarView searchBarView) {
        this.f$0 = searchBarView;
    }

    public final void onFocusChange(View view, boolean z) {
        SearchBarView.setSearchViewListeners$lambda$1(this.f$0, view, z);
    }
}
