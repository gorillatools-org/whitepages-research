package com.swmansion.rnscreens;

import androidx.appcompat.widget.SearchView;

public final /* synthetic */ class SearchBarView$$ExternalSyntheticLambda2 implements SearchView.OnCloseListener {
    public final /* synthetic */ SearchBarView f$0;

    public /* synthetic */ SearchBarView$$ExternalSyntheticLambda2(SearchBarView searchBarView) {
        this.f$0 = searchBarView;
    }

    public final boolean onClose() {
        return SearchBarView.setSearchViewListeners$lambda$2(this.f$0);
    }
}
