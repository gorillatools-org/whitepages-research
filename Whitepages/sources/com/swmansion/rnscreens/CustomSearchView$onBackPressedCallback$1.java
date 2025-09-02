package com.swmansion.rnscreens;

import androidx.activity.OnBackPressedCallback;

public final class CustomSearchView$onBackPressedCallback$1 extends OnBackPressedCallback {
    final /* synthetic */ CustomSearchView this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CustomSearchView$onBackPressedCallback$1(CustomSearchView customSearchView) {
        super(true);
        this.this$0 = customSearchView;
    }

    public void handleOnBackPressed() {
        this.this$0.setIconified(true);
    }
}
