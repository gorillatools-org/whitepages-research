package com.salesforce.marketingcloud.messages.iam;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.salesforce.marketingcloud.R;
import com.salesforce.marketingcloud.media.o;
import com.salesforce.marketingcloud.media.t;
import com.salesforce.marketingcloud.messages.iam.InAppMessage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

abstract class d {
    private final View.OnClickListener a;
    private final Typeface b;

    class a implements Comparator<InAppMessage.Button> {
        a() {
        }

        /* renamed from: a */
        public int compare(InAppMessage.Button button, InAppMessage.Button button2) {
            return button.index() - button2.index();
        }
    }

    class b implements Runnable {
        final /* synthetic */ View a;
        final /* synthetic */ int b;
        final /* synthetic */ View c;

        b(View view, int i, View view2) {
            this.a = view;
            this.b = i;
            this.c = view2;
        }

        public void run() {
            Rect rect = new Rect();
            this.a.getHitRect(rect);
            int i = rect.top;
            int i2 = this.b;
            rect.top = i - i2;
            rect.left -= i2;
            rect.bottom += i2;
            rect.right += i2;
            this.c.setTouchDelegate(new TouchDelegate(rect, this.a));
        }
    }

    static /* synthetic */ class c {
        static final /* synthetic */ int[] a;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.salesforce.marketingcloud.messages.iam.InAppMessage$Size[] r0 = com.salesforce.marketingcloud.messages.iam.InAppMessage.Size.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                a = r0
                com.salesforce.marketingcloud.messages.iam.InAppMessage$Size r1 = com.salesforce.marketingcloud.messages.iam.InAppMessage.Size.l     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.salesforce.marketingcloud.messages.iam.InAppMessage$Size r1 = com.salesforce.marketingcloud.messages.iam.InAppMessage.Size.m     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.salesforce.marketingcloud.messages.iam.InAppMessage$Size r1 = com.salesforce.marketingcloud.messages.iam.InAppMessage.Size.s     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.messages.iam.d.c.<clinit>():void");
        }
    }

    d(View.OnClickListener onClickListener, Typeface typeface) {
        this.a = onClickListener;
        this.b = typeface;
    }

    private void a(ViewGroup viewGroup) {
        Space space = new Space(viewGroup.getContext());
        space.setLayoutParams(new LinearLayout.LayoutParams(Math.round(b(viewGroup.getResources())), -1));
        viewGroup.addView(space);
    }

    private void b(ViewGroup viewGroup) {
        Space space = new Space(viewGroup.getContext());
        space.setLayoutParams(new LinearLayout.LayoutParams(-1, Math.round(c(viewGroup.getResources()))));
        viewGroup.addView(space);
    }

    /* access modifiers changed from: protected */
    public abstract float a(Resources resources);

    /* access modifiers changed from: protected */
    public abstract float a(Resources resources, InAppMessage.Size size);

    /* access modifiers changed from: protected */
    public abstract int a();

    /* access modifiers changed from: protected */
    public abstract float b(Resources resources);

    /* access modifiers changed from: protected */
    public abstract float b(Resources resources, InAppMessage.Size size);

    /* access modifiers changed from: protected */
    public abstract int b();

    /* access modifiers changed from: protected */
    public void b(View view, k kVar) {
    }

    /* access modifiers changed from: protected */
    public abstract float c(Resources resources);

    /* access modifiers changed from: protected */
    public abstract float c(Resources resources, InAppMessage.Size size);

    /* access modifiers changed from: protected */
    public abstract int c();

    /* access modifiers changed from: protected */
    public abstract int d();

    /* access modifiers changed from: protected */
    public abstract int e();

    /* access modifiers changed from: protected */
    public abstract int f();

    /* access modifiers changed from: protected */
    public abstract int g();

    /* access modifiers changed from: protected */
    public abstract int h();

    private void a(View view, InAppMessage.TextField textField) {
        TextView textView = (TextView) view.findViewById(a());
        if (textView != null) {
            if (a(textField)) {
                textView.setVisibility(0);
                Typeface typeface = this.b;
                if (typeface != null) {
                    textView.setTypeface(typeface, 0);
                }
                g.a(textView, textField.text(), g.a(view.getContext(), textField.fontColor(), R.color.mcsdk_iam_default_font_color), a(view.getContext().getResources(), textField.fontSize()), g.a(textField.alignment()));
                return;
            }
            textView.setVisibility(8);
        }
    }

    private void b(View view, InAppMessage.TextField textField) {
        TextView textView = (TextView) view.findViewById(h());
        if (textView != null) {
            if (a(textField)) {
                textView.setVisibility(0);
                Typeface typeface = this.b;
                if (typeface != null) {
                    textView.setTypeface(typeface, 1);
                }
                g.a(textView, textField.text(), g.a(view.getContext(), textField.fontColor(), R.color.mcsdk_iam_default_font_color), c(view.getContext().getResources(), textField.fontSize()), g.a(textField.alignment()));
                return;
            }
            textView.setVisibility(8);
        }
    }

    private void a(View view) {
        if (view != null) {
            View view2 = (View) view.getParent();
            view2.post(new b(view, Math.round(a(view.getContext().getResources())), view2));
        }
    }

    private float b(Context context, InAppMessage.Size size) {
        if (size == null) {
            size = InAppMessage.Size.s;
        }
        Resources resources = context.getResources();
        int i = c.a[size.ordinal()];
        return resources.getDimension(i != 1 ? i != 2 ? R.dimen.mcsdk_corner_radius_small : R.dimen.mcsdk_corner_radius_medium : R.dimen.mcsdk_corner_radius_large);
    }

    private void a(View view, o oVar, InAppMessage.Media media, InAppMessage.Type type) {
        ImageView imageView = (ImageView) view.findViewById(e());
        if (imageView != null) {
            Context context = view.getContext();
            if (media == null) {
                View findViewById = view.findViewById(d());
                if (findViewById != null) {
                    findViewById.setVisibility(8);
                } else {
                    imageView.setVisibility(8);
                }
            } else if (oVar != null) {
                imageView.setVisibility(0);
                t d = oVar.b(media.url()).d();
                if (type == InAppMessage.Type.fullImageFill) {
                    d.b();
                } else {
                    d.a();
                }
                d.a(b(context, media.cornerRadius()), a(context, media.borderWidth()), g.a(context, media.borderColor(), R.color.mcsdk_iam_default_border)).a(imageView);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(View view, String str, String str2, InAppMessage.Size size, InAppMessage.Size size2) {
        View findViewById = view.findViewById(f());
        if (findViewById != null) {
            Context context = view.getContext();
            float b2 = b(context, size2);
            float a2 = a(context, size);
            ViewCompat.setBackground(findViewById, g.a(g.a(context, str, R.color.mcsdk_iam_default_message_background), b2, g.a(context, str2, R.color.mcsdk_iam_default_border), a2));
            if (findViewById instanceof ClippingConstraintLayout) {
                ((ClippingConstraintLayout) findViewById).setClippingDetails(a2, b2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(View view, String str) {
        View findViewById = view.findViewById(g());
        if (findViewById != null) {
            findViewById.setBackgroundColor(g.a(view.getContext(), str, R.color.mcsdk_iam_default_window_background));
        }
    }

    private float a(Context context, InAppMessage.Size size) {
        if (size == null) {
            size = InAppMessage.Size.s;
        }
        Resources resources = context.getResources();
        int i = c.a[size.ordinal()];
        return resources.getDimension(i != 1 ? i != 2 ? R.dimen.mcsdk_border_width_small : R.dimen.mcsdk_border_width_medium : R.dimen.mcsdk_border_width_large);
    }

    /* access modifiers changed from: package-private */
    public void a(View view, k kVar) {
        if (kVar != null && view != null) {
            InAppMessage d = kVar.d();
            a(view, d.windowColor());
            a(view, d.backgroundColor(), d.borderColor(), d.borderWidth(), d.cornerRadius());
            b(view, d.title());
            a(view, d.body());
            a(view, a(d) ? d.closeButton() : com.salesforce.marketingcloud.internal.a.a());
            List<InAppMessage.Button> buttons = d.buttons();
            if (buttons == null || buttons.isEmpty()) {
                View findViewById = view.findViewById(b());
                if (findViewById != null) {
                    findViewById.setVisibility(8);
                }
            } else {
                ArrayList arrayList = new ArrayList(buttons);
                Collections.sort(arrayList, new a());
                a(view, d.buttonConfiguration(), arrayList);
            }
            a(view, kVar.e(), d.media(), d.type());
            b(view, kVar);
        }
    }

    private void a(View view, InAppMessage.CloseButton closeButton) {
        ImageButton imageButton = (ImageButton) view.findViewById(c());
        if (imageButton != null) {
            if (closeButton != null) {
                ViewCompat.setTranslationZ(imageButton, 1.0f);
                imageButton.setVisibility(0);
                imageButton.setTag(closeButton);
                imageButton.setOnClickListener(this.a);
                a((View) imageButton);
                return;
            }
            imageButton.setVisibility(8);
        }
    }

    /* access modifiers changed from: protected */
    public void a(View view, InAppMessage.ButtonConfig buttonConfig, List<InAppMessage.Button> list) {
        int i;
        InAppMessage.ButtonConfig buttonConfig2 = buttonConfig;
        Context context = view.getContext();
        LinearLayout linearLayout = (LinearLayout) view.findViewById(b());
        boolean z = false;
        if (buttonConfig2 == InAppMessage.ButtonConfig.stacked) {
            linearLayout.setOrientation(1);
            i = R.layout.mcsdk_iam_stacked_button;
        } else {
            linearLayout.setOrientation(0);
            i = R.layout.mcsdk_iam_sbs_button;
        }
        LayoutInflater from = LayoutInflater.from(context);
        int size = list.size();
        int i2 = 0;
        while (i2 < 2 && i2 < size) {
            InAppMessage.Button button = list.get(i2);
            Button button2 = (Button) from.inflate(i, linearLayout, z);
            Typeface typeface = this.b;
            if (typeface != null) {
                button2.setTypeface(typeface, z ? 1 : 0);
            }
            g.a(button2, button.text(), g.a(context, button.fontColor(), R.color.mcsdk_iam_default_font_color), b(context.getResources(), button.fontSize()), g.a(InAppMessage.Alignment.center));
            ViewCompat.setBackground(button2, g.a(g.a(context, button.backgroundColor(), R.color.mcsdk_iam_default_btn_background), b(context, button.cornerRadius()), g.a(context, button.borderColor(), R.color.mcsdk_iam_default_border), a(context, button.borderWidth())));
            button2.setTag(button);
            button2.setOnClickListener(this.a);
            linearLayout.addView(button2);
            if (i2 == 0 && size > 1) {
                if (buttonConfig2 == InAppMessage.ButtonConfig.stacked) {
                    b((ViewGroup) linearLayout);
                } else {
                    a((ViewGroup) linearLayout);
                }
            }
            i2++;
            z = false;
        }
    }

    static boolean a(InAppMessage inAppMessage) {
        return ((inAppMessage.type() == InAppMessage.Type.full || inAppMessage.type() == InAppMessage.Type.modal) && inAppMessage.closeButton() == null && (inAppMessage.buttons() == null || inAppMessage.buttons().isEmpty())) ? false : true;
    }

    static boolean a(InAppMessage.TextField textField) {
        return textField != null && !TextUtils.isEmpty(textField.text());
    }
}
