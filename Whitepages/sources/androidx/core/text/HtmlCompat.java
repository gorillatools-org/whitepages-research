package androidx.core.text;

import android.text.Html;
import android.text.Spanned;

public abstract class HtmlCompat {
    public static Spanned fromHtml(String str, int i) {
        return Api24Impl.fromHtml(str, i);
    }

    static class Api24Impl {
        static Spanned fromHtml(String str, int i) {
            return Html.fromHtml(str, i);
        }

        static Spanned fromHtml(String str, int i, Html.ImageGetter imageGetter, Html.TagHandler tagHandler) {
            return Html.fromHtml(str, i, imageGetter, tagHandler);
        }

        static String toHtml(Spanned spanned, int i) {
            return Html.toHtml(spanned, i);
        }
    }
}
