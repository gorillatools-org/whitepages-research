package com.swmansion.rnscreens;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.R$id;
import androidx.appcompat.widget.SearchView;
import com.facebook.react.views.textinput.ReactTextInputShadowNode;
import kotlin.jvm.internal.Intrinsics;

public final class SearchViewFormatter {
    private Integer defaultTextColor;
    private Drawable defaultTintBackground;
    private SearchView searchView;

    public SearchViewFormatter(SearchView searchView2) {
        Intrinsics.checkNotNullParameter(searchView2, "searchView");
        this.searchView = searchView2;
    }

    private final EditText getSearchEditText() {
        View findViewById = this.searchView.findViewById(R$id.search_src_text);
        if (findViewById instanceof EditText) {
            return (EditText) findViewById;
        }
        return null;
    }

    private final View getSearchTextPlate() {
        return this.searchView.findViewById(R$id.search_plate);
    }

    private final ImageView getSearchIcon() {
        return (ImageView) this.searchView.findViewById(R$id.search_button);
    }

    private final ImageView getSearchCloseIcon() {
        return (ImageView) this.searchView.findViewById(R$id.search_close_btn);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000c, code lost:
        r0 = r0.getTextColors();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setTextColor(java.lang.Integer r2) {
        /*
            r1 = this;
            java.lang.Integer r0 = r1.defaultTextColor
            if (r2 == 0) goto L_0x002c
            if (r0 != 0) goto L_0x001e
            android.widget.EditText r0 = r1.getSearchEditText()
            if (r0 == 0) goto L_0x001b
            android.content.res.ColorStateList r0 = r0.getTextColors()
            if (r0 == 0) goto L_0x001b
            int r0 = r0.getDefaultColor()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            goto L_0x001c
        L_0x001b:
            r0 = 0
        L_0x001c:
            r1.defaultTextColor = r0
        L_0x001e:
            android.widget.EditText r0 = r1.getSearchEditText()
            if (r0 == 0) goto L_0x003b
            int r2 = r2.intValue()
            r0.setTextColor(r2)
            goto L_0x003b
        L_0x002c:
            if (r0 == 0) goto L_0x003b
            android.widget.EditText r2 = r1.getSearchEditText()
            if (r2 == 0) goto L_0x003b
            int r0 = r0.intValue()
            r2.setTextColor(r0)
        L_0x003b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.rnscreens.SearchViewFormatter.setTextColor(java.lang.Integer):void");
    }

    public final void setTintColor(Integer num) {
        Drawable drawable = this.defaultTintBackground;
        if (num != null) {
            if (drawable == null) {
                this.defaultTintBackground = getSearchTextPlate().getBackground();
            }
            getSearchTextPlate().setBackgroundColor(num.intValue());
        } else if (drawable != null) {
            getSearchTextPlate().setBackground(drawable);
        }
    }

    public final void setHeaderIconColor(Integer num) {
        if (num != null) {
            int intValue = num.intValue();
            getSearchIcon().setColorFilter(intValue);
            getSearchCloseIcon().setColorFilter(intValue);
        }
    }

    public final void setHintTextColor(Integer num) {
        if (num != null) {
            int intValue = num.intValue();
            EditText searchEditText = getSearchEditText();
            if (searchEditText != null) {
                searchEditText.setHintTextColor(intValue);
            }
        }
    }

    public final void setPlaceholder(String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, ReactTextInputShadowNode.PROP_PLACEHOLDER);
        if (z) {
            this.searchView.setQueryHint(str);
            return;
        }
        EditText searchEditText = getSearchEditText();
        if (searchEditText != null) {
            searchEditText.setHint(str);
        }
    }
}
