package com.swmansion.rnscreens;

import androidx.appcompat.widget.SearchView;

public final class SearchBarView$setSearchViewListeners$1 implements SearchView.OnQueryTextListener {
    final /* synthetic */ SearchBarView this$0;

    SearchBarView$setSearchViewListeners$1(SearchBarView searchBarView) {
        this.this$0 = searchBarView;
    }

    public boolean onQueryTextChange(String str) {
        this.this$0.handleTextChange(str);
        return true;
    }

    public boolean onQueryTextSubmit(String str) {
        this.this$0.handleTextSubmit(str);
        return true;
    }
}
