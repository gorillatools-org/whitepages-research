package com.salesforce.marketingcloud.messages.iam;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.salesforce.marketingcloud.R;
import com.salesforce.marketingcloud.messages.iam.InAppMessage;

@SuppressLint({"UnknownNullness"})
public class c extends Fragment {
    private k a;

    class a implements Animation.AnimationListener {
        final /* synthetic */ boolean a;

        a(boolean z) {
            this.a = z;
        }

        public void onAnimationEnd(Animation animation) {
            FragmentActivity activity;
            View view = c.this.getView();
            if (view != null) {
                view.setLayerType(0, (Paint) null);
            }
            if (!this.a && (activity = c.this.getActivity()) != null) {
                activity.finish();
            }
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }
    }

    private int a(InAppMessage inAppMessage) {
        return inAppMessage.type() == InAppMessage.Type.bannerTop ? R.layout.mcsdk_iam_banner_top : R.layout.mcsdk_iam_banner_bottom;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.a = (k) getArguments().getParcelable("messageHandler");
        }
    }

    public Animation onCreateAnimation(int i, boolean z, int i2) {
        View view;
        Animation onCreateAnimation = super.onCreateAnimation(i, z, i2);
        if (onCreateAnimation == null && i2 != 0) {
            onCreateAnimation = AnimationUtils.loadAnimation(getActivity(), i2);
        }
        if (!(onCreateAnimation == null || (view = getView()) == null)) {
            view.setLayerType(2, (Paint) null);
            onCreateAnimation.setAnimationListener(new a(z));
        }
        return onCreateAnimation;
    }

    /* JADX WARNING: type inference failed for: r4v7, types: [androidx.fragment.app.FragmentActivity] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View onCreateView(android.view.LayoutInflater r3, android.view.ViewGroup r4, android.os.Bundle r5) {
        /*
            r2 = this;
            com.salesforce.marketingcloud.messages.iam.k r5 = r2.a
            r0 = 0
            if (r5 != 0) goto L_0x0006
            return r0
        L_0x0006:
            com.salesforce.marketingcloud.messages.iam.InAppMessage r5 = r5.d()
            int r5 = r2.a((com.salesforce.marketingcloud.messages.iam.InAppMessage) r5)
            r1 = 0
            android.view.View r3 = r3.inflate(r5, r4, r1)
            androidx.fragment.app.FragmentActivity r4 = r2.getActivity()
            boolean r4 = r4 instanceof android.view.View.OnClickListener
            if (r4 == 0) goto L_0x0022
            androidx.fragment.app.FragmentActivity r4 = r2.getActivity()
            r0 = r4
            android.view.View$OnClickListener r0 = (android.view.View.OnClickListener) r0
        L_0x0022:
            com.salesforce.marketingcloud.messages.iam.b r4 = new com.salesforce.marketingcloud.messages.iam.b
            com.salesforce.marketingcloud.messages.iam.k r5 = r2.a
            android.graphics.Typeface r5 = r5.k()
            r4.<init>(r0, r5)
            com.salesforce.marketingcloud.messages.iam.k r5 = r2.a
            r4.a((android.view.View) r3, (com.salesforce.marketingcloud.messages.iam.k) r5)
            int r4 = r4.g()
            android.view.View r4 = r3.findViewById(r4)
            com.salesforce.marketingcloud.messages.iam.SwipeDismissConstraintLayout r4 = (com.salesforce.marketingcloud.messages.iam.SwipeDismissConstraintLayout) r4
            if (r4 == 0) goto L_0x004f
            androidx.fragment.app.FragmentActivity r5 = r2.getActivity()
            boolean r5 = r5 instanceof com.salesforce.marketingcloud.messages.iam.SwipeDismissConstraintLayout.SwipeDismissListener
            if (r5 == 0) goto L_0x004f
            androidx.fragment.app.FragmentActivity r5 = r2.getActivity()
            com.salesforce.marketingcloud.messages.iam.SwipeDismissConstraintLayout$SwipeDismissListener r5 = (com.salesforce.marketingcloud.messages.iam.SwipeDismissConstraintLayout.SwipeDismissListener) r5
            r4.setListener(r5)
        L_0x004f:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.messages.iam.c.onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle):android.view.View");
    }

    public static c a(k kVar) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("messageHandler", kVar);
        c cVar = new c();
        cVar.setArguments(bundle);
        return cVar;
    }
}
