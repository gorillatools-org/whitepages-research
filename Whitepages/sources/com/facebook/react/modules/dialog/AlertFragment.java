package com.facebook.react.modules.dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.R$styleable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.fragment.app.DialogFragment;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.R;
import com.facebook.react.modules.dialog.DialogModule;

public class AlertFragment extends DialogFragment implements DialogInterface.OnClickListener {
    static final String ARG_BUTTON_NEGATIVE = "button_negative";
    static final String ARG_BUTTON_NEUTRAL = "button_neutral";
    static final String ARG_BUTTON_POSITIVE = "button_positive";
    static final String ARG_ITEMS = "items";
    static final String ARG_MESSAGE = "message";
    static final String ARG_TITLE = "title";
    private final DialogModule.AlertFragmentListener mListener;

    public AlertFragment() {
        this.mListener = null;
    }

    @SuppressLint({"ValidFragment"})
    public AlertFragment(DialogModule.AlertFragmentListener alertFragmentListener, Bundle bundle) {
        this.mListener = alertFragmentListener;
        setArguments(bundle);
    }

    public static Dialog createDialog(Context context, Bundle bundle, DialogInterface.OnClickListener onClickListener) {
        if (isAppCompatTheme(context)) {
            return createAppCompatDialog(context, bundle, onClickListener);
        }
        return createAppDialog(context, bundle, onClickListener);
    }

    private static boolean isAppCompatTheme(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(R$styleable.AppCompatTheme);
        boolean hasValue = obtainStyledAttributes.hasValue(R$styleable.AppCompatTheme_windowActionBar);
        obtainStyledAttributes.recycle();
        return hasValue;
    }

    private static View getAccessibleTitle(Context context, String str) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.alert_title_layout, (ViewGroup) null);
        final TextView textView = (TextView) Assertions.assertNotNull((TextView) inflate.findViewById(R.id.alert_title));
        textView.setText(str);
        textView.setFocusable(true);
        if (Build.VERSION.SDK_INT >= 28) {
            textView.setAccessibilityHeading(true);
        } else {
            ViewCompat.setAccessibilityDelegate(textView, new AccessibilityDelegateCompat() {
                public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                    super.onInitializeAccessibilityNodeInfo(textView, accessibilityNodeInfoCompat);
                    accessibilityNodeInfoCompat.setHeading(true);
                }
            });
        }
        return inflate;
    }

    private static Dialog createAppCompatDialog(Context context, Bundle bundle, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (bundle.containsKey("title")) {
            builder.setCustomTitle(getAccessibleTitle(context, (String) Assertions.assertNotNull(bundle.getString("title"))));
        }
        if (bundle.containsKey(ARG_BUTTON_POSITIVE)) {
            builder.setPositiveButton(bundle.getString(ARG_BUTTON_POSITIVE), onClickListener);
        }
        if (bundle.containsKey(ARG_BUTTON_NEGATIVE)) {
            builder.setNegativeButton(bundle.getString(ARG_BUTTON_NEGATIVE), onClickListener);
        }
        if (bundle.containsKey(ARG_BUTTON_NEUTRAL)) {
            builder.setNeutralButton(bundle.getString(ARG_BUTTON_NEUTRAL), onClickListener);
        }
        if (bundle.containsKey("message")) {
            builder.setMessage(bundle.getString("message"));
        }
        if (bundle.containsKey("items")) {
            builder.setItems(bundle.getCharSequenceArray("items"), onClickListener);
        }
        return builder.create();
    }

    @Deprecated(forRemoval = true, since = "0.75.0")
    private static Dialog createAppDialog(Context context, Bundle bundle, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (bundle.containsKey("title")) {
            builder.setCustomTitle(getAccessibleTitle(context, (String) Assertions.assertNotNull(bundle.getString("title"))));
        }
        if (bundle.containsKey(ARG_BUTTON_POSITIVE)) {
            builder.setPositiveButton(bundle.getString(ARG_BUTTON_POSITIVE), onClickListener);
        }
        if (bundle.containsKey(ARG_BUTTON_NEGATIVE)) {
            builder.setNegativeButton(bundle.getString(ARG_BUTTON_NEGATIVE), onClickListener);
        }
        if (bundle.containsKey(ARG_BUTTON_NEUTRAL)) {
            builder.setNeutralButton(bundle.getString(ARG_BUTTON_NEUTRAL), onClickListener);
        }
        if (bundle.containsKey("message")) {
            builder.setMessage(bundle.getString("message"));
        }
        if (bundle.containsKey("items")) {
            builder.setItems(bundle.getCharSequenceArray("items"), onClickListener);
        }
        return builder.create();
    }

    public Dialog onCreateDialog(Bundle bundle) {
        return createDialog(requireActivity(), requireArguments(), this);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        DialogModule.AlertFragmentListener alertFragmentListener = this.mListener;
        if (alertFragmentListener != null) {
            alertFragmentListener.onClick(dialogInterface, i);
        }
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        DialogModule.AlertFragmentListener alertFragmentListener = this.mListener;
        if (alertFragmentListener != null) {
            alertFragmentListener.onDismiss(dialogInterface);
        }
    }
}
