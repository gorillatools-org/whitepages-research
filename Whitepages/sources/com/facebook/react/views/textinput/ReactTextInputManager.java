package com.facebook.react.views.textinput;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.common.mapbuffer.MapBuffer;
import com.facebook.react.common.mapbuffer.ReadableMapBuffer;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.BackgroundStyleApplicator;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.FilterHelper$$ExternalSyntheticApiModelOutline10;
import com.facebook.react.uimanager.FilterHelper$$ExternalSyntheticApiModelOutline11;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.LengthPercentage;
import com.facebook.react.uimanager.LengthPercentageType;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.uimanager.style.BorderRadiusProp;
import com.facebook.react.uimanager.style.BorderStyle;
import com.facebook.react.uimanager.style.LogicalEdge;
import com.facebook.react.views.imagehelper.ResourceDrawableIdHelper;
import com.facebook.react.views.scroll.ScrollEvent;
import com.facebook.react.views.scroll.ScrollEventType;
import com.facebook.react.views.text.DefaultStyleValuesUtil;
import com.facebook.react.views.text.ReactBaseTextShadowNode;
import com.facebook.react.views.text.ReactTextUpdate;
import com.facebook.react.views.text.ReactTextViewManagerCallback;
import com.facebook.react.views.text.ReactTypefaceUtils;
import com.facebook.react.views.text.TextAttributeProps;
import com.facebook.react.views.text.TextLayoutManager;
import com.facebook.react.views.text.TextTransform;
import com.facebook.react.views.text.internal.span.TextInlineImageSpan;
import com.google.android.gms.common.Scopes;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import com.salesforce.marketingcloud.b;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import okhttp3.internal.http2.Http2;

@ReactModule(name = "AndroidTextInput")
public class ReactTextInputManager extends BaseViewManager<ReactEditText, LayoutShadowNode> {
    private static final int AUTOCAPITALIZE_FLAGS = 28672;
    private static final int BLUR_TEXT_INPUT = 2;
    private static final String[] DRAWABLE_HANDLE_FIELDS = {"mSelectHandleLeft", "mSelectHandleRight", "mSelectHandleCenter"};
    private static final String[] DRAWABLE_HANDLE_RESOURCES = {"mTextSelectHandleLeftRes", "mTextSelectHandleRightRes", "mTextSelectHandleRes"};
    private static final InputFilter[] EMPTY_FILTERS = new InputFilter[0];
    private static final int FOCUS_TEXT_INPUT = 1;
    private static final int IME_ACTION_ID = 1648;
    private static final int INPUT_TYPE_KEYBOARD_DECIMAL_PAD = 8194;
    private static final int INPUT_TYPE_KEYBOARD_NUMBERED = 12290;
    private static final int INPUT_TYPE_KEYBOARD_NUMBER_PAD = 2;
    private static final String KEYBOARD_TYPE_DECIMAL_PAD = "decimal-pad";
    private static final String KEYBOARD_TYPE_EMAIL_ADDRESS = "email-address";
    private static final String KEYBOARD_TYPE_NUMBER_PAD = "number-pad";
    private static final String KEYBOARD_TYPE_NUMERIC = "numeric";
    private static final String KEYBOARD_TYPE_PHONE_PAD = "phone-pad";
    private static final String KEYBOARD_TYPE_URI = "url";
    private static final String KEYBOARD_TYPE_VISIBLE_PASSWORD = "visible-password";
    private static final int PASSWORD_VISIBILITY_FLAG = 16;
    public static final String REACT_CLASS = "AndroidTextInput";
    private static final Map<String, String> REACT_PROPS_AUTOFILL_HINTS_MAP = new HashMap<String, String>() {
        {
            put("birthdate-day", "birthDateDay");
            put("birthdate-full", "birthDateFull");
            put("birthdate-month", "birthDateMonth");
            put("birthdate-year", "birthDateYear");
            put("cc-csc", "creditCardSecurityCode");
            put("cc-exp", "creditCardExpirationDate");
            put("cc-exp-day", "creditCardExpirationDay");
            put("cc-exp-month", "creditCardExpirationMonth");
            put("cc-exp-year", "creditCardExpirationYear");
            put("cc-number", "creditCardNumber");
            put(Scopes.EMAIL, "emailAddress");
            put("gender", "gender");
            put("name", "personName");
            put("name-family", "personFamilyName");
            put("name-given", "personGivenName");
            put("name-middle", "personMiddleName");
            put("name-middle-initial", "personMiddleInitial");
            put("name-prefix", "personNamePrefix");
            put("name-suffix", "personNameSuffix");
            put("password", "password");
            put("password-new", "newPassword");
            put("postal-address", "postalAddress");
            put("postal-address-country", "addressCountry");
            put("postal-address-extended", "extendedAddress");
            put("postal-address-extended-postal-code", "extendedPostalCode");
            put("postal-address-locality", "addressLocality");
            put("postal-address-region", "addressRegion");
            put("postal-code", "postalCode");
            put("street-address", "streetAddress");
            put("sms-otp", "smsOTPCode");
            put("tel", "phoneNumber");
            put("tel-country-code", "phoneCountryCode");
            put("tel-national", "phoneNational");
            put("tel-device", "phoneNumberDevice");
            put("username", "username");
            put("username-new", "newUsername");
        }
    };
    private static final int SET_MOST_RECENT_EVENT_COUNT = 3;
    private static final int SET_TEXT_AND_SELECTION = 4;
    private static final int[] SPACING_TYPES = {8, 0, 2, 1, 3};
    public static final String TAG = "ReactTextInputManager";
    private static final short TX_STATE_KEY_ATTRIBUTED_STRING = 0;
    private static final short TX_STATE_KEY_HASH = 2;
    private static final short TX_STATE_KEY_MOST_RECENT_EVENT_COUNT = 3;
    private static final short TX_STATE_KEY_PARAGRAPH_ATTRIBUTES = 1;
    private static final int UNSET = -1;
    protected ReactTextViewManagerCallback mReactTextViewManagerCallback;

    public String getName() {
        return REACT_CLASS;
    }

    public ReactEditText createViewInstance(ThemedReactContext themedReactContext) {
        ReactEditText reactEditText = new ReactEditText(themedReactContext);
        reactEditText.setInputType(reactEditText.getInputType() & -131073);
        reactEditText.setReturnKeyType("done");
        reactEditText.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        return reactEditText;
    }

    public ReactBaseTextShadowNode createShadowNodeInstance() {
        return new ReactTextInputShadowNode();
    }

    public ReactBaseTextShadowNode createShadowNodeInstance(ReactTextViewManagerCallback reactTextViewManagerCallback) {
        return new ReactTextInputShadowNode(reactTextViewManagerCallback);
    }

    public Class<? extends LayoutShadowNode> getShadowNodeClass() {
        return ReactTextInputShadowNode.class;
    }

    public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        Map<String, Object> exportedCustomBubblingEventTypeConstants = super.getExportedCustomBubblingEventTypeConstants();
        if (exportedCustomBubblingEventTypeConstants == null) {
            exportedCustomBubblingEventTypeConstants = new HashMap<>();
        }
        exportedCustomBubblingEventTypeConstants.putAll(MapBuilder.builder().put("topSubmitEditing", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onSubmitEditing", "captured", "onSubmitEditingCapture"))).put("topEndEditing", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onEndEditing", "captured", "onEndEditingCapture"))).put("topFocus", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onFocus", "captured", "onFocusCapture"))).put("topBlur", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onBlur", "captured", "onBlurCapture"))).put(ReactTextInputKeyPressEvent.EVENT_NAME, MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onKeyPress", "captured", "onKeyPressCapture"))).build());
        return exportedCustomBubblingEventTypeConstants;
    }

    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        Map<String, Object> exportedCustomDirectEventTypeConstants = super.getExportedCustomDirectEventTypeConstants();
        if (exportedCustomDirectEventTypeConstants == null) {
            exportedCustomDirectEventTypeConstants = new HashMap<>();
        }
        exportedCustomDirectEventTypeConstants.putAll(MapBuilder.builder().put(ScrollEventType.getJSEventName(ScrollEventType.SCROLL), MapBuilder.of("registrationName", "onScroll")).build());
        return exportedCustomDirectEventTypeConstants;
    }

    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of("focusTextInput", 1, "blurTextInput", 2);
    }

    public void receiveCommand(ReactEditText reactEditText, int i, ReadableArray readableArray) {
        if (i == 1) {
            receiveCommand(reactEditText, "focus", readableArray);
        } else if (i == 2) {
            receiveCommand(reactEditText, "blur", readableArray);
        } else if (i == 4) {
            receiveCommand(reactEditText, "setTextAndSelection", readableArray);
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void receiveCommand(com.facebook.react.views.textinput.ReactEditText r7, java.lang.String r8, com.facebook.react.bridge.ReadableArray r9) {
        /*
            r6 = this;
            r0 = 3
            r1 = 2
            r2 = 1
            r3 = 0
            r8.hashCode()
            r4 = -1
            int r5 = r8.hashCode()
            switch(r5) {
                case -1699362314: goto L_0x003d;
                case 3027047: goto L_0x0032;
                case 97604824: goto L_0x0027;
                case 1427010500: goto L_0x001c;
                case 1690703013: goto L_0x0011;
                default: goto L_0x000f;
            }
        L_0x000f:
            r8 = r4
            goto L_0x0047
        L_0x0011:
            java.lang.String r5 = "focusTextInput"
            boolean r8 = r8.equals(r5)
            if (r8 != 0) goto L_0x001a
            goto L_0x000f
        L_0x001a:
            r8 = 4
            goto L_0x0047
        L_0x001c:
            java.lang.String r5 = "setTextAndSelection"
            boolean r8 = r8.equals(r5)
            if (r8 != 0) goto L_0x0025
            goto L_0x000f
        L_0x0025:
            r8 = r0
            goto L_0x0047
        L_0x0027:
            java.lang.String r5 = "focus"
            boolean r8 = r8.equals(r5)
            if (r8 != 0) goto L_0x0030
            goto L_0x000f
        L_0x0030:
            r8 = r1
            goto L_0x0047
        L_0x0032:
            java.lang.String r5 = "blur"
            boolean r8 = r8.equals(r5)
            if (r8 != 0) goto L_0x003b
            goto L_0x000f
        L_0x003b:
            r8 = r2
            goto L_0x0047
        L_0x003d:
            java.lang.String r5 = "blurTextInput"
            boolean r8 = r8.equals(r5)
            if (r8 != 0) goto L_0x0046
            goto L_0x000f
        L_0x0046:
            r8 = r3
        L_0x0047:
            switch(r8) {
                case 0: goto L_0x0076;
                case 1: goto L_0x0076;
                case 2: goto L_0x0072;
                case 3: goto L_0x004b;
                case 4: goto L_0x0072;
                default: goto L_0x004a;
            }
        L_0x004a:
            goto L_0x0079
        L_0x004b:
            int r8 = r9.getInt(r3)
            if (r8 != r4) goto L_0x0052
            return
        L_0x0052:
            int r1 = r9.getInt(r1)
            int r0 = r9.getInt(r0)
            if (r0 != r4) goto L_0x005d
            r0 = r1
        L_0x005d:
            boolean r3 = r9.isNull(r2)
            if (r3 != 0) goto L_0x006e
            java.lang.String r9 = r9.getString(r2)
            com.facebook.react.views.text.ReactTextUpdate r9 = r6.getReactTextUpdate(r9, r8)
            r7.maybeSetTextFromJS(r9)
        L_0x006e:
            r7.maybeSetSelection(r8, r1, r0)
            goto L_0x0079
        L_0x0072:
            r7.requestFocusFromJS()
            goto L_0x0079
        L_0x0076:
            r7.clearFocusFromJS()
        L_0x0079:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.textinput.ReactTextInputManager.receiveCommand(com.facebook.react.views.textinput.ReactEditText, java.lang.String, com.facebook.react.bridge.ReadableArray):void");
    }

    private ReactTextUpdate getReactTextUpdate(String str, int i) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(TextTransform.apply(str, TextTransform.UNSET));
        return new ReactTextUpdate(spannableStringBuilder, i, false, 0.0f, 0.0f, 0.0f, 0.0f, 0, 0, 0);
    }

    public void updateExtraData(ReactEditText reactEditText, Object obj) {
        if (obj instanceof ReactTextUpdate) {
            ReactTextUpdate reactTextUpdate = (ReactTextUpdate) obj;
            int paddingLeft = (int) reactTextUpdate.getPaddingLeft();
            int paddingTop = (int) reactTextUpdate.getPaddingTop();
            int paddingRight = (int) reactTextUpdate.getPaddingRight();
            int paddingBottom = (int) reactTextUpdate.getPaddingBottom();
            int i = -1;
            if (!(paddingLeft == -1 && paddingTop == -1 && paddingRight == -1 && paddingBottom == -1)) {
                if (paddingLeft == -1) {
                    paddingLeft = reactEditText.getPaddingLeft();
                }
                if (paddingTop == -1) {
                    paddingTop = reactEditText.getPaddingTop();
                }
                if (paddingRight == -1) {
                    paddingRight = reactEditText.getPaddingRight();
                }
                if (paddingBottom == -1) {
                    paddingBottom = reactEditText.getPaddingBottom();
                }
                reactEditText.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
            }
            if (reactTextUpdate.containsImages()) {
                TextInlineImageSpan.possiblyUpdateInlineImageSpans(reactTextUpdate.getText(), reactEditText);
            }
            int i2 = 0;
            if (reactEditText.getSelectionStart() == reactEditText.getSelectionEnd()) {
                if (reactEditText.getText() != null) {
                    i2 = reactEditText.getText().length();
                }
                i = reactTextUpdate.getText().length() - (i2 - reactEditText.getSelectionStart());
            }
            reactEditText.maybeSetTextFromState(reactTextUpdate);
            reactEditText.maybeSetSelection(reactTextUpdate.getJsEventCounter(), i, i);
        }
    }

    @ReactProp(defaultFloat = 0.0f, name = "lineHeight")
    public void setLineHeight(ReactEditText reactEditText, int i) {
        reactEditText.setLineHeight(i);
    }

    @ReactProp(defaultFloat = 14.0f, name = "fontSize")
    public void setFontSize(ReactEditText reactEditText, float f) {
        reactEditText.setFontSize(f);
    }

    @ReactProp(name = "fontFamily")
    public void setFontFamily(ReactEditText reactEditText, String str) {
        reactEditText.setFontFamily(str);
    }

    @ReactProp(defaultFloat = Float.NaN, name = "maxFontSizeMultiplier")
    public void setMaxFontSizeMultiplier(ReactEditText reactEditText, float f) {
        reactEditText.setMaxFontSizeMultiplier(f);
    }

    @ReactProp(name = "fontWeight")
    public void setFontWeight(ReactEditText reactEditText, String str) {
        reactEditText.setFontWeight(str);
    }

    @ReactProp(name = "fontStyle")
    public void setFontStyle(ReactEditText reactEditText, String str) {
        reactEditText.setFontStyle(str);
    }

    @ReactProp(name = "fontVariant")
    public void setFontVariant(ReactEditText reactEditText, ReadableArray readableArray) {
        reactEditText.setFontFeatureSettings(ReactTypefaceUtils.parseFontVariant(readableArray));
    }

    @ReactProp(defaultBoolean = true, name = "includeFontPadding")
    public void setIncludeFontPadding(ReactEditText reactEditText, boolean z) {
        reactEditText.setIncludeFontPadding(z);
    }

    @ReactProp(name = "importantForAutofill")
    public void setImportantForAutofill(ReactEditText reactEditText, String str) {
        int i;
        if ("no".equals(str)) {
            i = 2;
        } else if ("noExcludeDescendants".equals(str)) {
            i = 8;
        } else if ("yes".equals(str)) {
            i = 1;
        } else {
            i = "yesExcludeDescendants".equals(str) ? 4 : 0;
        }
        setImportantForAutofill(reactEditText, i);
    }

    private void setImportantForAutofill(ReactEditText reactEditText, int i) {
        reactEditText.setImportantForAutofill(i);
    }

    private void setAutofillHints(ReactEditText reactEditText, String... strArr) {
        reactEditText.setAutofillHints(strArr);
    }

    @ReactProp(defaultBoolean = false, name = "onSelectionChange")
    public void setOnSelectionChange(ReactEditText reactEditText, boolean z) {
        if (z) {
            reactEditText.setSelectionWatcher(new ReactSelectionWatcher(reactEditText));
        } else {
            reactEditText.setSelectionWatcher((SelectionWatcher) null);
        }
    }

    @ReactProp(name = "submitBehavior")
    public void setSubmitBehavior(ReactEditText reactEditText, String str) {
        reactEditText.setSubmitBehavior(str);
    }

    @ReactProp(defaultBoolean = false, name = "onContentSizeChange")
    public void setOnContentSizeChange(ReactEditText reactEditText, boolean z) {
        if (z) {
            reactEditText.setContentSizeWatcher(new ReactContentSizeWatcher(reactEditText));
        } else {
            reactEditText.setContentSizeWatcher((ContentSizeWatcher) null);
        }
    }

    @ReactProp(defaultBoolean = false, name = "onScroll")
    public void setOnScroll(ReactEditText reactEditText, boolean z) {
        if (z) {
            reactEditText.setScrollWatcher(new ReactScrollWatcher(reactEditText));
        } else {
            reactEditText.setScrollWatcher((ScrollWatcher) null);
        }
    }

    @ReactProp(defaultBoolean = false, name = "onKeyPress")
    public void setOnKeyPress(ReactEditText reactEditText, boolean z) {
        reactEditText.setOnKeyPress(z);
    }

    @ReactProp(defaultFloat = 0.0f, name = "letterSpacing")
    public void setLetterSpacing(ReactEditText reactEditText, float f) {
        reactEditText.setLetterSpacingPt(f);
    }

    @ReactProp(defaultBoolean = true, name = "allowFontScaling")
    public void setAllowFontScaling(ReactEditText reactEditText, boolean z) {
        reactEditText.setAllowFontScaling(z);
    }

    @ReactProp(name = "placeholder")
    public void setPlaceholder(ReactEditText reactEditText, String str) {
        reactEditText.setPlaceholder(str);
    }

    @ReactProp(customType = "Color", name = "placeholderTextColor")
    public void setPlaceholderTextColor(ReactEditText reactEditText, Integer num) {
        if (num == null) {
            reactEditText.setHintTextColor(DefaultStyleValuesUtil.getDefaultTextColorHint(reactEditText.getContext()));
        } else {
            reactEditText.setHintTextColor(num.intValue());
        }
    }

    @ReactProp(customType = "Color", name = "selectionColor")
    public void setSelectionColor(ReactEditText reactEditText, Integer num) {
        if (num == null) {
            reactEditText.setHighlightColor(DefaultStyleValuesUtil.getDefaultTextColorHighlight(reactEditText.getContext()));
        } else {
            reactEditText.setHighlightColor(num.intValue());
        }
    }

    @ReactProp(customType = "Color", name = "selectionHandleColor")
    public void setSelectionHandleColor(ReactEditText reactEditText, Integer num) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 29) {
            Drawable mutate = reactEditText.getTextSelectHandle().mutate();
            Drawable mutate2 = reactEditText.getTextSelectHandleLeft().mutate();
            Drawable mutate3 = reactEditText.getTextSelectHandleRight().mutate();
            if (num != null) {
                FilterHelper$$ExternalSyntheticApiModelOutline11.m();
                BlendModeColorFilter m = FilterHelper$$ExternalSyntheticApiModelOutline10.m(num.intValue(), BlendMode.SRC_IN);
                mutate.setColorFilter(m);
                mutate2.setColorFilter(m);
                mutate3.setColorFilter(m);
            } else {
                mutate.clearColorFilter();
                mutate2.clearColorFilter();
                mutate3.clearColorFilter();
            }
            reactEditText.setTextSelectHandle(mutate);
            reactEditText.setTextSelectHandleLeft(mutate2);
            reactEditText.setTextSelectHandleRight(mutate3);
        } else if (i != 28) {
            int i2 = 0;
            while (true) {
                String[] strArr = DRAWABLE_HANDLE_RESOURCES;
                if (i2 < strArr.length) {
                    try {
                        Field declaredField = reactEditText.getClass().getDeclaredField(strArr[i2]);
                        declaredField.setAccessible(true);
                        int i3 = declaredField.getInt(reactEditText);
                        if (i3 != 0) {
                            Drawable mutate4 = ContextCompat.getDrawable(reactEditText.getContext(), i3).mutate();
                            if (num != null) {
                                mutate4.setColorFilter(num.intValue(), PorterDuff.Mode.SRC_IN);
                            } else {
                                mutate4.clearColorFilter();
                            }
                            Field declaredField2 = TextView.class.getDeclaredField("mEditor");
                            declaredField2.setAccessible(true);
                            Object obj = declaredField2.get(reactEditText);
                            Field declaredField3 = obj.getClass().getDeclaredField(DRAWABLE_HANDLE_FIELDS[i2]);
                            declaredField3.setAccessible(true);
                            declaredField3.set(obj, mutate4);
                            i2++;
                        } else {
                            return;
                        }
                    } catch (IllegalAccessException | NoSuchFieldException unused) {
                    }
                } else {
                    return;
                }
            }
        }
    }

    @ReactProp(customType = "Color", name = "cursorColor")
    public void setCursorColor(ReactEditText reactEditText, Integer num) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 29) {
            Drawable m = reactEditText.getTextCursorDrawable();
            if (m != null) {
                if (num != null) {
                    FilterHelper$$ExternalSyntheticApiModelOutline11.m();
                    m.setColorFilter(FilterHelper$$ExternalSyntheticApiModelOutline10.m(num.intValue(), BlendMode.SRC_IN));
                } else {
                    m.clearColorFilter();
                }
                reactEditText.setTextCursorDrawable(m);
            }
        } else if (i != 28) {
            try {
                Field declaredField = reactEditText.getClass().getDeclaredField("mCursorDrawableRes");
                declaredField.setAccessible(true);
                int i2 = declaredField.getInt(reactEditText);
                if (i2 != 0) {
                    Drawable mutate = ContextCompat.getDrawable(reactEditText.getContext(), i2).mutate();
                    if (num != null) {
                        mutate.setColorFilter(num.intValue(), PorterDuff.Mode.SRC_IN);
                    } else {
                        mutate.clearColorFilter();
                    }
                    Field declaredField2 = TextView.class.getDeclaredField("mEditor");
                    declaredField2.setAccessible(true);
                    Object obj = declaredField2.get(reactEditText);
                    Field declaredField3 = obj.getClass().getDeclaredField("mCursorDrawable");
                    declaredField3.setAccessible(true);
                    declaredField3.set(obj, new Drawable[]{mutate, mutate});
                }
            } catch (IllegalAccessException | NoSuchFieldException unused) {
            }
        }
    }

    private static boolean shouldHideCursorForEmailTextInput() {
        return Build.VERSION.SDK_INT == 29 && Build.MANUFACTURER.toLowerCase(Locale.ROOT).contains("xiaomi");
    }

    @ReactProp(defaultBoolean = false, name = "caretHidden")
    public void setCaretHidden(ReactEditText reactEditText, boolean z) {
        if (reactEditText.getStagedInputType() != 32 || !shouldHideCursorForEmailTextInput()) {
            reactEditText.setCursorVisible(!z);
        }
    }

    @ReactProp(defaultBoolean = false, name = "contextMenuHidden")
    public void setContextMenuHidden(ReactEditText reactEditText, boolean z) {
        reactEditText.setContextMenuHidden(z);
    }

    @ReactProp(defaultBoolean = false, name = "selectTextOnFocus")
    public void setSelectTextOnFocus(ReactEditText reactEditText, boolean z) {
        reactEditText.setSelectTextOnFocus(z);
    }

    @ReactProp(customType = "Color", name = "color")
    public void setColor(ReactEditText reactEditText, Integer num) {
        if (num == null) {
            ColorStateList defaultTextColor = DefaultStyleValuesUtil.getDefaultTextColor(reactEditText.getContext());
            if (defaultTextColor != null) {
                reactEditText.setTextColor(defaultTextColor);
                return;
            }
            Context context = reactEditText.getContext();
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Could not get default text color from View Context: ");
            sb.append(context != null ? context.getClass().getCanonicalName() : "null");
            ReactSoftExceptionLogger.logSoftException(str, new IllegalStateException(sb.toString()));
            return;
        }
        reactEditText.setTextColor(num.intValue());
    }

    @ReactProp(customType = "Color", name = "underlineColorAndroid")
    public void setUnderlineColor(ReactEditText reactEditText, Integer num) {
        Drawable background = reactEditText.getBackground();
        if (background != null) {
            if (background.getConstantState() != null) {
                try {
                    background = background.mutate();
                } catch (NullPointerException e) {
                    FLog.e(TAG, "NullPointerException when setting underlineColorAndroid for TextInput", (Throwable) e);
                }
            }
            if (num == null) {
                background.clearColorFilter();
            } else {
                background.setColorFilter(num.intValue(), PorterDuff.Mode.SRC_IN);
            }
        }
    }

    @ReactProp(name = "textAlign")
    public void setTextAlign(ReactEditText reactEditText, String str) {
        if ("justify".equals(str)) {
            reactEditText.setJustificationMode(1);
            reactEditText.setGravityHorizontal(3);
            return;
        }
        reactEditText.setJustificationMode(0);
        if (str == null || "auto".equals(str)) {
            reactEditText.setGravityHorizontal(0);
        } else if (ViewProps.LEFT.equals(str)) {
            reactEditText.setGravityHorizontal(3);
        } else if (ViewProps.RIGHT.equals(str)) {
            reactEditText.setGravityHorizontal(5);
        } else if ("center".equals(str)) {
            reactEditText.setGravityHorizontal(1);
        } else {
            FLog.w(ReactConstants.TAG, "Invalid textAlign: " + str);
            reactEditText.setGravityHorizontal(0);
        }
    }

    @ReactProp(name = "textAlignVertical")
    public void setTextAlignVertical(ReactEditText reactEditText, String str) {
        if (str == null || "auto".equals(str)) {
            reactEditText.setGravityVertical(0);
        } else if (ViewProps.TOP.equals(str)) {
            reactEditText.setGravityVertical(48);
        } else if (ViewProps.BOTTOM.equals(str)) {
            reactEditText.setGravityVertical(80);
        } else if ("center".equals(str)) {
            reactEditText.setGravityVertical(16);
        } else {
            FLog.w(ReactConstants.TAG, "Invalid textAlignVertical: " + str);
            reactEditText.setGravityVertical(0);
        }
    }

    @ReactProp(name = "inlineImageLeft")
    public void setInlineImageLeft(ReactEditText reactEditText, String str) {
        reactEditText.setCompoundDrawablesWithIntrinsicBounds(ResourceDrawableIdHelper.getInstance().getResourceDrawableId(reactEditText.getContext(), str), 0, 0, 0);
    }

    @ReactProp(name = "inlineImagePadding")
    public void setInlineImagePadding(ReactEditText reactEditText, int i) {
        reactEditText.setCompoundDrawablePadding(i);
    }

    @ReactProp(defaultBoolean = true, name = "editable")
    public void setEditable(ReactEditText reactEditText, boolean z) {
        reactEditText.setEnabled(z);
    }

    @ReactProp(defaultInt = 1, name = "numberOfLines")
    public void setNumLines(ReactEditText reactEditText, int i) {
        reactEditText.setLines(i);
    }

    /* JADX WARNING: type inference failed for: r8v5, types: [java.lang.Object[]] */
    /* JADX WARNING: Multi-variable type inference failed */
    @com.facebook.react.uimanager.annotations.ReactProp(name = "maxLength")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setMaxLength(com.facebook.react.views.textinput.ReactEditText r7, java.lang.Integer r8) {
        /*
            r6 = this;
            android.text.InputFilter[] r0 = r7.getFilters()
            android.text.InputFilter[] r1 = EMPTY_FILTERS
            r2 = 0
            if (r8 != 0) goto L_0x0034
            int r8 = r0.length
            if (r8 <= 0) goto L_0x0078
            java.util.LinkedList r8 = new java.util.LinkedList
            r8.<init>()
            int r3 = r0.length
        L_0x0012:
            if (r2 >= r3) goto L_0x0020
            r4 = r0[r2]
            boolean r5 = r4 instanceof android.text.InputFilter.LengthFilter
            if (r5 != 0) goto L_0x001d
            r8.add(r4)
        L_0x001d:
            int r2 = r2 + 1
            goto L_0x0012
        L_0x0020:
            boolean r0 = r8.isEmpty()
            if (r0 != 0) goto L_0x0078
            int r0 = r8.size()
            android.text.InputFilter[] r0 = new android.text.InputFilter[r0]
            java.lang.Object[] r8 = r8.toArray(r0)
            r1 = r8
            android.text.InputFilter[] r1 = (android.text.InputFilter[]) r1
            goto L_0x0078
        L_0x0034:
            int r1 = r0.length
            r3 = 1
            if (r1 <= 0) goto L_0x006b
            r1 = r2
            r4 = r1
        L_0x003a:
            int r5 = r0.length
            if (r1 >= r5) goto L_0x0052
            r5 = r0[r1]
            boolean r5 = r5 instanceof android.text.InputFilter.LengthFilter
            if (r5 == 0) goto L_0x004f
            android.text.InputFilter$LengthFilter r4 = new android.text.InputFilter$LengthFilter
            int r5 = r8.intValue()
            r4.<init>(r5)
            r0[r1] = r4
            r4 = r3
        L_0x004f:
            int r1 = r1 + 1
            goto L_0x003a
        L_0x0052:
            if (r4 != 0) goto L_0x0069
            int r1 = r0.length
            int r1 = r1 + r3
            android.text.InputFilter[] r1 = new android.text.InputFilter[r1]
            int r3 = r0.length
            java.lang.System.arraycopy(r0, r2, r1, r2, r3)
            int r2 = r0.length
            android.text.InputFilter$LengthFilter r3 = new android.text.InputFilter$LengthFilter
            int r8 = r8.intValue()
            r3.<init>(r8)
            r0[r2] = r3
            r0 = r1
        L_0x0069:
            r1 = r0
            goto L_0x0078
        L_0x006b:
            android.text.InputFilter[] r1 = new android.text.InputFilter[r3]
            android.text.InputFilter$LengthFilter r0 = new android.text.InputFilter$LengthFilter
            int r8 = r8.intValue()
            r0.<init>(r8)
            r1[r2] = r0
        L_0x0078:
            r7.setFilters(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.textinput.ReactTextInputManager.setMaxLength(com.facebook.react.views.textinput.ReactEditText, java.lang.Integer):void");
    }

    @ReactProp(name = "autoComplete")
    public void setTextContentType(ReactEditText reactEditText, String str) {
        if (str == null) {
            setImportantForAutofill(reactEditText, 2);
        } else if ("off".equals(str)) {
            setImportantForAutofill(reactEditText, 2);
        } else {
            Map<String, String> map = REACT_PROPS_AUTOFILL_HINTS_MAP;
            if (map.containsKey(str)) {
                setAutofillHints(reactEditText, map.get(str));
                return;
            }
            FLog.w(ReactConstants.TAG, "Invalid autoComplete: " + str);
            setImportantForAutofill(reactEditText, 2);
        }
    }

    @ReactProp(name = "autoCorrect")
    public void setAutoCorrect(ReactEditText reactEditText, Boolean bool) {
        updateStagedInputTypeFlag(reactEditText, 557056, bool != null ? bool.booleanValue() ? 32768 : 524288 : 0);
    }

    @ReactProp(defaultBoolean = false, name = "multiline")
    public void setMultiline(ReactEditText reactEditText, boolean z) {
        int i = 131072;
        int i2 = z ? 0 : 131072;
        if (!z) {
            i = 0;
        }
        updateStagedInputTypeFlag(reactEditText, i2, i);
    }

    @ReactProp(defaultBoolean = false, name = "secureTextEntry")
    public void setSecureTextEntry(ReactEditText reactEditText, boolean z) {
        updateStagedInputTypeFlag(reactEditText, 144, z ? 128 : 0);
        checkPasswordType(reactEditText);
    }

    @ReactProp(name = "autoCapitalize")
    public void setAutoCapitalize(ReactEditText reactEditText, Dynamic dynamic) {
        int i = 0;
        if (dynamic.getType() == ReadableType.Number) {
            i = dynamic.asInt();
        } else {
            if (dynamic.getType() == ReadableType.String) {
                String asString = dynamic.asString();
                asString.hashCode();
                char c = 65535;
                switch (asString.hashCode()) {
                    case 3387192:
                        if (asString.equals("none")) {
                            c = 0;
                            break;
                        }
                        break;
                    case 113318569:
                        if (asString.equals("words")) {
                            c = 1;
                            break;
                        }
                        break;
                    case 490141296:
                        if (asString.equals("sentences")) {
                            c = 2;
                            break;
                        }
                        break;
                    case 1245424234:
                        if (asString.equals("characters")) {
                            c = 3;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        break;
                    case 1:
                        i = UserMetadata.MAX_INTERNAL_KEY_SIZE;
                        break;
                    case 3:
                        i = b.v;
                        break;
                }
            }
            i = 16384;
        }
        updateStagedInputTypeFlag(reactEditText, AUTOCAPITALIZE_FLAGS, i);
    }

    @ReactProp(name = "keyboardType")
    public void setKeyboardType(ReactEditText reactEditText, String str) {
        int i;
        if ("numeric".equalsIgnoreCase(str)) {
            i = INPUT_TYPE_KEYBOARD_NUMBERED;
        } else if (KEYBOARD_TYPE_NUMBER_PAD.equalsIgnoreCase(str)) {
            i = 2;
        } else if (KEYBOARD_TYPE_DECIMAL_PAD.equalsIgnoreCase(str)) {
            i = INPUT_TYPE_KEYBOARD_DECIMAL_PAD;
        } else if (KEYBOARD_TYPE_EMAIL_ADDRESS.equalsIgnoreCase(str)) {
            if (shouldHideCursorForEmailTextInput()) {
                reactEditText.setCursorVisible(false);
            }
            i = 33;
        } else if (KEYBOARD_TYPE_PHONE_PAD.equalsIgnoreCase(str)) {
            i = 3;
        } else if (KEYBOARD_TYPE_VISIBLE_PASSWORD.equalsIgnoreCase(str)) {
            i = 144;
        } else {
            i = "url".equalsIgnoreCase(str) ? 16 : 1;
        }
        updateStagedInputTypeFlag(reactEditText, 15, i);
        checkPasswordType(reactEditText);
    }

    @ReactProp(name = "returnKeyType")
    public void setReturnKeyType(ReactEditText reactEditText, String str) {
        reactEditText.setReturnKeyType(str);
    }

    @ReactProp(defaultBoolean = false, name = "disableFullscreenUI")
    public void setDisableFullscreenUI(ReactEditText reactEditText, boolean z) {
        reactEditText.setDisableFullscreenUI(z);
    }

    @ReactProp(name = "returnKeyLabel")
    public void setReturnKeyLabel(ReactEditText reactEditText, String str) {
        reactEditText.setImeActionLabel(str, IME_ACTION_ID);
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderRadius", "borderTopLeftRadius", "borderTopRightRadius", "borderBottomRightRadius", "borderBottomLeftRadius"})
    public void setBorderRadius(ReactEditText reactEditText, int i, float f) {
        LengthPercentage lengthPercentage;
        if (Float.isNaN(f)) {
            lengthPercentage = null;
        } else {
            lengthPercentage = new LengthPercentage(f, LengthPercentageType.POINT);
        }
        BackgroundStyleApplicator.setBorderRadius(reactEditText, BorderRadiusProp.values()[i], lengthPercentage);
    }

    @ReactProp(name = "borderStyle")
    public void setBorderStyle(ReactEditText reactEditText, String str) {
        BackgroundStyleApplicator.setBorderStyle(reactEditText, str == null ? null : BorderStyle.fromString(str));
    }

    @ReactProp(defaultBoolean = true, name = "showSoftInputOnFocus")
    public void showKeyboardOnFocus(ReactEditText reactEditText, boolean z) {
        reactEditText.setShowSoftInputOnFocus(z);
    }

    @ReactProp(defaultBoolean = false, name = "autoFocus")
    public void setAutoFocus(ReactEditText reactEditText, boolean z) {
        reactEditText.setAutoFocus(z);
    }

    @ReactProp(name = "textDecorationLine")
    public void setTextDecorationLine(ReactEditText reactEditText, String str) {
        reactEditText.setPaintFlags(reactEditText.getPaintFlags() & -25);
        if (str != null) {
            for (String str2 : str.split(" ")) {
                if (str2.equals("underline")) {
                    reactEditText.setPaintFlags(reactEditText.getPaintFlags() | 8);
                } else if (str2.equals("line-through")) {
                    reactEditText.setPaintFlags(reactEditText.getPaintFlags() | 16);
                }
            }
        }
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderWidth", "borderLeftWidth", "borderRightWidth", "borderTopWidth", "borderBottomWidth"})
    public void setBorderWidth(ReactEditText reactEditText, int i, float f) {
        BackgroundStyleApplicator.setBorderWidth(reactEditText, LogicalEdge.values()[i], Float.valueOf(f));
    }

    @ReactPropGroup(customType = "Color", names = {"borderColor", "borderLeftColor", "borderRightColor", "borderTopColor", "borderBottomColor"})
    public void setBorderColor(ReactEditText reactEditText, int i, Integer num) {
        BackgroundStyleApplicator.setBorderColor(reactEditText, LogicalEdge.ALL, num);
    }

    @ReactProp(name = "overflow")
    public void setOverflow(ReactEditText reactEditText, String str) {
        reactEditText.setOverflow(str);
    }

    /* access modifiers changed from: protected */
    public void onAfterUpdateTransaction(ReactEditText reactEditText) {
        super.onAfterUpdateTransaction(reactEditText);
        reactEditText.maybeUpdateTypeface();
        reactEditText.commitStagedInputType();
    }

    private static void checkPasswordType(ReactEditText reactEditText) {
        if ((reactEditText.getStagedInputType() & INPUT_TYPE_KEYBOARD_NUMBERED) != 0 && (reactEditText.getStagedInputType() & 128) != 0) {
            updateStagedInputTypeFlag(reactEditText, 128, 16);
        }
    }

    private static void updateStagedInputTypeFlag(ReactEditText reactEditText, int i, int i2) {
        reactEditText.setStagedInputType(((~i) & reactEditText.getStagedInputType()) | i2);
    }

    /* access modifiers changed from: private */
    public static EventDispatcher getEventDispatcher(ReactContext reactContext, ReactEditText reactEditText) {
        return UIManagerHelper.getEventDispatcherForReactTag(reactContext, reactEditText.getId());
    }

    private final class ReactTextInputTextWatcher implements TextWatcher {
        private final ReactEditText mEditText;
        private final EventDispatcher mEventDispatcher;
        private String mPreviousText = null;
        private final int mSurfaceId;

        public void afterTextChanged(Editable editable) {
        }

        public ReactTextInputTextWatcher(ReactContext reactContext, ReactEditText reactEditText) {
            this.mEventDispatcher = ReactTextInputManager.getEventDispatcher(reactContext, reactEditText);
            this.mEditText = reactEditText;
            this.mSurfaceId = UIManagerHelper.getSurfaceId((Context) reactContext);
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            this.mPreviousText = charSequence.toString();
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (!this.mEditText.mDisableTextDiffing) {
                if (i3 != 0 || i2 != 0) {
                    Assertions.assertNotNull(this.mPreviousText);
                    String substring = charSequence.toString().substring(i, i + i3);
                    String substring2 = this.mPreviousText.substring(i, i + i2);
                    if (i3 != i2 || !substring.equals(substring2)) {
                        StateWrapper stateWrapper = this.mEditText.getStateWrapper();
                        if (stateWrapper != null) {
                            WritableNativeMap writableNativeMap = new WritableNativeMap();
                            writableNativeMap.putInt("mostRecentEventCount", this.mEditText.incrementAndGetEventCounter());
                            writableNativeMap.putInt("opaqueCacheId", this.mEditText.getId());
                            stateWrapper.updateState(writableNativeMap);
                        }
                        this.mEventDispatcher.dispatchEvent(new ReactTextChangedEvent(this.mSurfaceId, this.mEditText.getId(), charSequence.toString(), this.mEditText.incrementAndGetEventCounter()));
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void addEventEmitters(ThemedReactContext themedReactContext, ReactEditText reactEditText) {
        reactEditText.setEventDispatcher(getEventDispatcher(themedReactContext, reactEditText));
        reactEditText.addTextChangedListener(new ReactTextInputTextWatcher(themedReactContext, reactEditText));
        reactEditText.setOnFocusChangeListener(new ReactTextInputManager$$ExternalSyntheticLambda8(themedReactContext, reactEditText));
        reactEditText.setOnEditorActionListener(new ReactTextInputManager$$ExternalSyntheticLambda9(reactEditText, themedReactContext));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$addEventEmitters$0(ThemedReactContext themedReactContext, ReactEditText reactEditText, View view, boolean z) {
        int surfaceId = themedReactContext.getSurfaceId();
        EventDispatcher eventDispatcher = getEventDispatcher(themedReactContext, reactEditText);
        if (z) {
            eventDispatcher.dispatchEvent(new ReactTextInputFocusEvent(surfaceId, reactEditText.getId()));
            return;
        }
        eventDispatcher.dispatchEvent(new ReactTextInputBlurEvent(surfaceId, reactEditText.getId()));
        eventDispatcher.dispatchEvent(new ReactTextInputEndEditingEvent(surfaceId, reactEditText.getId(), reactEditText.getText().toString()));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$addEventEmitters$1(ReactEditText reactEditText, ThemedReactContext themedReactContext, TextView textView, int i, KeyEvent keyEvent) {
        if ((i & 255) == 0 && i != 0) {
            return true;
        }
        boolean isMultiline = reactEditText.isMultiline();
        boolean shouldSubmitOnReturn = reactEditText.shouldSubmitOnReturn();
        boolean shouldBlurOnReturn = reactEditText.shouldBlurOnReturn();
        if (shouldSubmitOnReturn) {
            getEventDispatcher(themedReactContext, reactEditText).dispatchEvent(new ReactTextInputSubmitEditingEvent(themedReactContext.getSurfaceId(), reactEditText.getId(), reactEditText.getText().toString()));
        }
        if (shouldBlurOnReturn) {
            reactEditText.clearFocus();
        }
        if (shouldBlurOnReturn || shouldSubmitOnReturn || !isMultiline || i == 5 || i == 7) {
            return true;
        }
        return false;
    }

    private static class ReactContentSizeWatcher implements ContentSizeWatcher {
        private final ReactEditText mEditText;
        private final EventDispatcher mEventDispatcher;
        private int mPreviousContentHeight = 0;
        private int mPreviousContentWidth = 0;
        private final int mSurfaceId;

        public ReactContentSizeWatcher(ReactEditText reactEditText) {
            this.mEditText = reactEditText;
            ReactContext reactContext = UIManagerHelper.getReactContext(reactEditText);
            this.mEventDispatcher = ReactTextInputManager.getEventDispatcher(reactContext, reactEditText);
            this.mSurfaceId = UIManagerHelper.getSurfaceId((Context) reactContext);
        }

        public void onLayout() {
            if (this.mEventDispatcher != null) {
                int width = this.mEditText.getWidth();
                int height = this.mEditText.getHeight();
                if (this.mEditText.getLayout() != null) {
                    width = this.mEditText.getCompoundPaddingLeft() + this.mEditText.getLayout().getWidth() + this.mEditText.getCompoundPaddingRight();
                    height = this.mEditText.getCompoundPaddingTop() + this.mEditText.getLayout().getHeight() + this.mEditText.getCompoundPaddingBottom();
                }
                if (width != this.mPreviousContentWidth || height != this.mPreviousContentHeight) {
                    this.mPreviousContentHeight = height;
                    this.mPreviousContentWidth = width;
                    this.mEventDispatcher.dispatchEvent(new ReactContentSizeChangedEvent(this.mSurfaceId, this.mEditText.getId(), PixelUtil.toDIPFromPixel((float) width), PixelUtil.toDIPFromPixel((float) height)));
                }
            }
        }
    }

    private static class ReactSelectionWatcher implements SelectionWatcher {
        private final EventDispatcher mEventDispatcher;
        private int mPreviousSelectionEnd;
        private int mPreviousSelectionStart;
        private final ReactEditText mReactEditText;
        private final int mSurfaceId;

        public ReactSelectionWatcher(ReactEditText reactEditText) {
            this.mReactEditText = reactEditText;
            ReactContext reactContext = UIManagerHelper.getReactContext(reactEditText);
            this.mEventDispatcher = ReactTextInputManager.getEventDispatcher(reactContext, reactEditText);
            this.mSurfaceId = UIManagerHelper.getSurfaceId((Context) reactContext);
        }

        public void onSelectionChanged(int i, int i2) {
            int min = Math.min(i, i2);
            int max = Math.max(i, i2);
            if (this.mPreviousSelectionStart != min || this.mPreviousSelectionEnd != max) {
                this.mEventDispatcher.dispatchEvent(new ReactTextInputSelectionEvent(this.mSurfaceId, this.mReactEditText.getId(), min, max));
                this.mPreviousSelectionStart = min;
                this.mPreviousSelectionEnd = max;
            }
        }
    }

    private static class ReactScrollWatcher implements ScrollWatcher {
        private final EventDispatcher mEventDispatcher;
        private int mPreviousHorizontal;
        private int mPreviousVert;
        private final ReactEditText mReactEditText;
        private final int mSurfaceId;

        public ReactScrollWatcher(ReactEditText reactEditText) {
            this.mReactEditText = reactEditText;
            ReactContext reactContext = UIManagerHelper.getReactContext(reactEditText);
            this.mEventDispatcher = ReactTextInputManager.getEventDispatcher(reactContext, reactEditText);
            this.mSurfaceId = UIManagerHelper.getSurfaceId((Context) reactContext);
        }

        public void onScrollChanged(int i, int i2, int i3, int i4) {
            if (this.mPreviousHorizontal != i || this.mPreviousVert != i2) {
                this.mEventDispatcher.dispatchEvent(ScrollEvent.obtain(this.mSurfaceId, this.mReactEditText.getId(), ScrollEventType.SCROLL, (float) i, (float) i2, 0.0f, 0.0f, 0, 0, this.mReactEditText.getWidth(), this.mReactEditText.getHeight()));
                this.mPreviousHorizontal = i;
                this.mPreviousVert = i2;
            }
        }
    }

    public Map<String, Object> getExportedViewConstants() {
        return MapBuilder.of("AutoCapitalizationType", MapBuilder.of("none", 0, "characters", Integer.valueOf(b.v), "words", Integer.valueOf(UserMetadata.MAX_INTERNAL_KEY_SIZE), "sentences", Integer.valueOf(Http2.INITIAL_MAX_FRAME_SIZE)));
    }

    public void setPadding(ReactEditText reactEditText, int i, int i2, int i3, int i4) {
        reactEditText.setPadding(i, i2, i3, i4);
    }

    public Object updateState(ReactEditText reactEditText, ReactStylesDiffMap reactStylesDiffMap, StateWrapper stateWrapper) {
        if (ReactEditText.DEBUG_MODE) {
            String str = TAG;
            FLog.e(str, "updateState: [" + reactEditText.getId() + "]");
        }
        if (reactEditText.getStateWrapper() == null) {
            reactEditText.setPadding(0, 0, 0, 0);
        }
        reactEditText.setStateWrapper(stateWrapper);
        ReadableMapBuffer stateDataMapBuffer = stateWrapper.getStateDataMapBuffer();
        if (stateDataMapBuffer != null) {
            return getReactTextUpdate(reactEditText, reactStylesDiffMap, stateDataMapBuffer);
        }
        return null;
    }

    public Object getReactTextUpdate(ReactEditText reactEditText, ReactStylesDiffMap reactStylesDiffMap, MapBuffer mapBuffer) {
        if (mapBuffer.getCount() == 0) {
            return null;
        }
        MapBuffer mapBuffer2 = mapBuffer.getMapBuffer(0);
        MapBuffer mapBuffer3 = mapBuffer.getMapBuffer(1);
        return ReactTextUpdate.buildReactTextUpdateFromState(TextLayoutManager.getOrCreateSpannableForText(reactEditText.getContext(), mapBuffer2, this.mReactTextViewManagerCallback), mapBuffer.getInt(3), TextAttributeProps.getTextAlignment(reactStylesDiffMap, TextLayoutManager.isRTL(mapBuffer2), reactEditText.getGravityHorizontal()), TextAttributeProps.getTextBreakStrategy(mapBuffer3.getString(2)), TextAttributeProps.getJustificationMode(reactStylesDiffMap, reactEditText.getJustificationMode()));
    }
}
