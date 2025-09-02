package com.facebook.react.uimanager;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Layout;
import android.text.Spannable;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.EditText;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeProviderCompat;
import androidx.customview.widget.ExploreByTouchHelper;
import com.facebook.react.R;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.common.ViewUtil;
import com.facebook.react.uimanager.drawable.InsetBoxShadowDrawableKt;
import com.facebook.react.uimanager.drawable.OutsetBoxShadowDrawableKt;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.util.ReactFindViewUtil;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.firebase.messaging.Constants;
import com.google.firebase.remoteconfig.RemoteConfigComponent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReactAccessibilityDelegate extends ExploreByTouchHelper {
    private static final int SEND_EVENT = 1;
    private static final String STATE_CHECKED = "checked";
    private static final String STATE_DISABLED = "disabled";
    private static final String STATE_SELECTED = "selected";
    private static final String TAG = "ReactAccessibilityDelegate";
    private static final int TIMEOUT_SEND_ACCESSIBILITY_EVENT = 200;
    public static final String TOP_ACCESSIBILITY_ACTION_EVENT = "topAccessibilityAction";
    private static final String delimiter = ", ";
    private static final int delimiterLength = 2;
    public static final HashMap<String, Integer> sActionIdMap;
    private static int sCounter = 1056964608;
    private final HashMap<Integer, String> mAccessibilityActionsMap = new HashMap<>();
    View mAccessibilityLabelledBy;
    private final AccessibilityLinks mAccessibilityLinks;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            ((View) message.obj).sendAccessibilityEvent(4);
        }
    };
    private final View mView;

    /* access modifiers changed from: protected */
    public boolean onPerformActionForVirtualView(int i, int i2, Bundle bundle) {
        return false;
    }

    static {
        HashMap<String, Integer> hashMap = new HashMap<>();
        sActionIdMap = hashMap;
        hashMap.put(RemoteConfigComponent.ACTIVATE_FILE_NAME, Integer.valueOf(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK.getId()));
        hashMap.put("longpress", Integer.valueOf(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_LONG_CLICK.getId()));
        hashMap.put("increment", Integer.valueOf(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_FORWARD.getId()));
        hashMap.put("decrement", Integer.valueOf(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD.getId()));
        hashMap.put("expand", Integer.valueOf(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_EXPAND.getId()));
        hashMap.put("collapse", Integer.valueOf(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_COLLAPSE.getId()));
    }

    private void scheduleAccessibilityEventSender(View view) {
        if (this.mHandler.hasMessages(1, view)) {
            this.mHandler.removeMessages(1, view);
        }
        this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(1, view), 200);
    }

    public enum Role {
        ALERT,
        ALERTDIALOG,
        APPLICATION,
        ARTICLE,
        BANNER,
        BUTTON,
        CELL,
        CHECKBOX,
        COLUMNHEADER,
        COMBOBOX,
        COMPLEMENTARY,
        CONTENTINFO,
        DEFINITION,
        DIALOG,
        DIRECTORY,
        DOCUMENT,
        FEED,
        FIGURE,
        FORM,
        GRID,
        GROUP,
        HEADING,
        IMG,
        LINK,
        LIST,
        LISTITEM,
        LOG,
        MAIN,
        MARQUEE,
        MATH,
        MENU,
        MENUBAR,
        MENUITEM,
        METER,
        NAVIGATION,
        NONE,
        NOTE,
        OPTION,
        PRESENTATION,
        PROGRESSBAR,
        RADIO,
        RADIOGROUP,
        REGION,
        ROW,
        ROWGROUP,
        ROWHEADER,
        SCROLLBAR,
        SEARCHBOX,
        SEPARATOR,
        SLIDER,
        SPINBUTTON,
        STATUS,
        SUMMARY,
        SWITCH,
        TAB,
        TABLE,
        TABLIST,
        TABPANEL,
        TERM,
        TIMER,
        TOOLBAR,
        TOOLTIP,
        TREE,
        TREEGRID,
        TREEITEM;

        public static Role fromValue(String str) {
            for (Role role : values()) {
                if (role.name().equalsIgnoreCase(str)) {
                    return role;
                }
            }
            return null;
        }
    }

    public enum AccessibilityRole {
        NONE,
        BUTTON,
        DROPDOWNLIST,
        TOGGLEBUTTON,
        LINK,
        SEARCH,
        IMAGE,
        IMAGEBUTTON,
        KEYBOARDKEY,
        TEXT,
        ADJUSTABLE,
        SUMMARY,
        HEADER,
        ALERT,
        CHECKBOX,
        COMBOBOX,
        MENU,
        MENUBAR,
        MENUITEM,
        PROGRESSBAR,
        RADIO,
        RADIOGROUP,
        SCROLLBAR,
        SPINBUTTON,
        SWITCH,
        TAB,
        TABLIST,
        TIMER,
        LIST,
        GRID,
        PAGER,
        SCROLLVIEW,
        HORIZONTALSCROLLVIEW,
        VIEWGROUP,
        WEBVIEW,
        DRAWERLAYOUT,
        SLIDINGDRAWER,
        ICONMENU,
        TOOLBAR;

        public static String getValue(AccessibilityRole accessibilityRole) {
            switch (AnonymousClass3.$SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$AccessibilityRole[accessibilityRole.ordinal()]) {
                case 1:
                    return "android.widget.Button";
                case 2:
                    return "android.widget.Spinner";
                case 3:
                    return "android.widget.ToggleButton";
                case 4:
                    return "android.widget.EditText";
                case 5:
                    return "android.widget.ImageView";
                case 6:
                    return "android.widget.ImageButton";
                case 7:
                    return "android.inputmethodservice.Keyboard$Key";
                case 8:
                    return "android.widget.TextView";
                case 9:
                    return "android.widget.SeekBar";
                case 10:
                    return "android.widget.CheckBox";
                case 11:
                    return "android.widget.RadioButton";
                case 12:
                    return "android.widget.SpinButton";
                case 13:
                    return "android.widget.Switch";
                case 14:
                    return "android.widget.AbsListView";
                case 15:
                    return "android.widget.GridView";
                case 16:
                    return "android.widget.ScrollView";
                case 17:
                    return "android.widget.HorizontalScrollView";
                case 18:
                    return "androidx.viewpager.widget.ViewPager";
                case 19:
                    return "androidx.drawerlayout.widget.DrawerLayout";
                case 20:
                    return "android.widget.SlidingDrawer";
                case CommonStatusCodes.RECONNECTION_TIMED_OUT_DURING_UPDATE /*21*/:
                    return "com.android.internal.view.menu.IconMenuView";
                case 22:
                    return "android.view.ViewGroup";
                case ConnectionResult.API_DISABLED /*23*/:
                    return "android.webkit.WebView";
                case ConnectionResult.API_DISABLED_FOR_CONNECTION /*24*/:
                case 25:
                case 26:
                case 27:
                case OutsetBoxShadowDrawableKt.MIN_OUTSET_BOX_SHADOW_SDK_VERSION /*28*/:
                case InsetBoxShadowDrawableKt.MIN_INSET_BOX_SHADOW_SDK_VERSION /*29*/:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                    return "android.view.View";
                default:
                    throw new IllegalArgumentException("Invalid accessibility role value: " + accessibilityRole);
            }
        }

        public static AccessibilityRole fromValue(String str) {
            if (str == null) {
                return NONE;
            }
            for (AccessibilityRole accessibilityRole : values()) {
                if (accessibilityRole.name().equalsIgnoreCase(str)) {
                    return accessibilityRole;
                }
            }
            throw new IllegalArgumentException("Invalid accessibility role value: " + str);
        }

        public static AccessibilityRole fromRole(Role role) {
            switch (AnonymousClass3.$SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$Role[role.ordinal()]) {
                case 1:
                    return ALERT;
                case 2:
                    return BUTTON;
                case 3:
                    return CHECKBOX;
                case 4:
                    return COMBOBOX;
                case 5:
                    return GRID;
                case 6:
                    return HEADER;
                case 7:
                    return IMAGE;
                case 8:
                    return LINK;
                case 9:
                    return LIST;
                case 10:
                    return MENU;
                case 11:
                    return MENUBAR;
                case 12:
                    return MENUITEM;
                case 13:
                    return NONE;
                case 14:
                    return PROGRESSBAR;
                case 15:
                    return RADIO;
                case 16:
                    return RADIOGROUP;
                case 17:
                    return SCROLLBAR;
                case 18:
                    return SEARCH;
                case 19:
                    return ADJUSTABLE;
                case 20:
                    return SPINBUTTON;
                case CommonStatusCodes.RECONNECTION_TIMED_OUT_DURING_UPDATE /*21*/:
                    return SUMMARY;
                case 22:
                    return SWITCH;
                case ConnectionResult.API_DISABLED /*23*/:
                    return TAB;
                case ConnectionResult.API_DISABLED_FOR_CONNECTION /*24*/:
                    return TABLIST;
                case 25:
                    return TIMER;
                case 26:
                    return TOOLBAR;
                default:
                    return null;
            }
        }

        public static AccessibilityRole fromViewTag(View view) {
            Role role = (Role) view.getTag(R.id.role);
            if (role != null) {
                return fromRole(role);
            }
            return (AccessibilityRole) view.getTag(R.id.accessibility_role);
        }
    }

    /* renamed from: com.facebook.react.uimanager.ReactAccessibilityDelegate$3  reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$AccessibilityRole;
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$Role;

        /* JADX WARNING: Can't wrap try/catch for region: R(130:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|(2:25|26)|27|(2:29|30)|31|(2:33|34)|35|(2:37|38)|39|(2:41|42)|43|(2:45|46)|47|(2:49|50)|51|(2:53|54)|55|(2:57|58)|59|(2:61|62)|63|(2:65|66)|67|(2:69|70)|71|(2:73|74)|75|(2:77|78)|79|(2:81|82)|83|85|86|87|88|89|90|91|92|(2:93|94)|95|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|127|128|129|130|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|147|148|149|150|151|152|153|154|155|156|157|158|159|160|161|162|163|164|165|166|167|168|169|170|171|172|(3:173|174|176)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(131:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|(2:25|26)|27|(2:29|30)|31|(2:33|34)|35|(2:37|38)|39|41|42|43|(2:45|46)|47|(2:49|50)|51|(2:53|54)|55|(2:57|58)|59|(2:61|62)|63|(2:65|66)|67|(2:69|70)|71|(2:73|74)|75|(2:77|78)|79|(2:81|82)|83|85|86|87|88|89|90|91|92|(2:93|94)|95|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|127|128|129|130|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|147|148|149|150|151|152|153|154|155|156|157|158|159|160|161|162|163|164|165|166|167|168|169|170|171|172|(3:173|174|176)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(132:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|(2:25|26)|27|(2:29|30)|31|(2:33|34)|35|(2:37|38)|39|41|42|43|(2:45|46)|47|(2:49|50)|51|(2:53|54)|55|(2:57|58)|59|(2:61|62)|63|(2:65|66)|67|(2:69|70)|71|(2:73|74)|75|(2:77|78)|79|81|82|83|85|86|87|88|89|90|91|92|(2:93|94)|95|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|127|128|129|130|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|147|148|149|150|151|152|153|154|155|156|157|158|159|160|161|162|163|164|165|166|167|168|169|170|171|172|(3:173|174|176)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(133:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|(2:25|26)|27|(2:29|30)|31|(2:33|34)|35|37|38|39|41|42|43|(2:45|46)|47|(2:49|50)|51|(2:53|54)|55|(2:57|58)|59|(2:61|62)|63|(2:65|66)|67|(2:69|70)|71|(2:73|74)|75|(2:77|78)|79|81|82|83|85|86|87|88|89|90|91|92|(2:93|94)|95|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|127|128|129|130|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|147|148|149|150|151|152|153|154|155|156|157|158|159|160|161|162|163|164|165|166|167|168|169|170|171|172|(3:173|174|176)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(134:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|(2:25|26)|27|(2:29|30)|31|(2:33|34)|35|37|38|39|41|42|43|(2:45|46)|47|(2:49|50)|51|(2:53|54)|55|(2:57|58)|59|(2:61|62)|63|(2:65|66)|67|(2:69|70)|71|(2:73|74)|75|77|78|79|81|82|83|85|86|87|88|89|90|91|92|(2:93|94)|95|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|127|128|129|130|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|147|148|149|150|151|152|153|154|155|156|157|158|159|160|161|162|163|164|165|166|167|168|169|170|171|172|(3:173|174|176)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(135:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|(2:25|26)|27|(2:29|30)|31|33|34|35|37|38|39|41|42|43|(2:45|46)|47|(2:49|50)|51|(2:53|54)|55|(2:57|58)|59|(2:61|62)|63|(2:65|66)|67|(2:69|70)|71|(2:73|74)|75|77|78|79|81|82|83|85|86|87|88|89|90|91|92|(2:93|94)|95|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|127|128|129|130|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|147|148|149|150|151|152|153|154|155|156|157|158|159|160|161|162|163|164|165|166|167|168|169|170|171|172|(3:173|174|176)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(137:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|(2:25|26)|27|(2:29|30)|31|33|34|35|37|38|39|41|42|43|(2:45|46)|47|(2:49|50)|51|(2:53|54)|55|(2:57|58)|59|(2:61|62)|63|(2:65|66)|67|(2:69|70)|71|(2:73|74)|75|77|78|79|81|82|83|85|86|87|88|89|90|91|92|(2:93|94)|95|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|127|128|129|130|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|147|148|149|150|151|152|153|154|155|156|157|158|159|160|161|162|163|164|165|166|167|168|169|170|171|172|173|174|176) */
        /* JADX WARNING: Can't wrap try/catch for region: R(138:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|(2:25|26)|27|(2:29|30)|31|33|34|35|37|38|39|41|42|43|(2:45|46)|47|(2:49|50)|51|(2:53|54)|55|(2:57|58)|59|(2:61|62)|63|(2:65|66)|67|(2:69|70)|71|73|74|75|77|78|79|81|82|83|85|86|87|88|89|90|91|92|(2:93|94)|95|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|127|128|129|130|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|147|148|149|150|151|152|153|154|155|156|157|158|159|160|161|162|163|164|165|166|167|168|169|170|171|172|173|174|176) */
        /* JADX WARNING: Can't wrap try/catch for region: R(139:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|(2:25|26)|27|29|30|31|33|34|35|37|38|39|41|42|43|(2:45|46)|47|(2:49|50)|51|(2:53|54)|55|(2:57|58)|59|(2:61|62)|63|(2:65|66)|67|(2:69|70)|71|73|74|75|77|78|79|81|82|83|85|86|87|88|89|90|91|92|(2:93|94)|95|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|127|128|129|130|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|147|148|149|150|151|152|153|154|155|156|157|158|159|160|161|162|163|164|165|166|167|168|169|170|171|172|173|174|176) */
        /* JADX WARNING: Can't wrap try/catch for region: R(140:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|(2:25|26)|27|29|30|31|33|34|35|37|38|39|41|42|43|(2:45|46)|47|(2:49|50)|51|(2:53|54)|55|(2:57|58)|59|(2:61|62)|63|(2:65|66)|67|69|70|71|73|74|75|77|78|79|81|82|83|85|86|87|88|89|90|91|92|(2:93|94)|95|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|127|128|129|130|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|147|148|149|150|151|152|153|154|155|156|157|158|159|160|161|162|163|164|165|166|167|168|169|170|171|172|173|174|176) */
        /* JADX WARNING: Can't wrap try/catch for region: R(141:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|25|26|27|29|30|31|33|34|35|37|38|39|41|42|43|(2:45|46)|47|(2:49|50)|51|(2:53|54)|55|(2:57|58)|59|(2:61|62)|63|(2:65|66)|67|69|70|71|73|74|75|77|78|79|81|82|83|85|86|87|88|89|90|91|92|(2:93|94)|95|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|127|128|129|130|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|147|148|149|150|151|152|153|154|155|156|157|158|159|160|161|162|163|164|165|166|167|168|169|170|171|172|173|174|176) */
        /* JADX WARNING: Can't wrap try/catch for region: R(142:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|25|26|27|29|30|31|33|34|35|37|38|39|41|42|43|(2:45|46)|47|(2:49|50)|51|(2:53|54)|55|(2:57|58)|59|(2:61|62)|63|65|66|67|69|70|71|73|74|75|77|78|79|81|82|83|85|86|87|88|89|90|91|92|(2:93|94)|95|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|127|128|129|130|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|147|148|149|150|151|152|153|154|155|156|157|158|159|160|161|162|163|164|165|166|167|168|169|170|171|172|173|174|176) */
        /* JADX WARNING: Can't wrap try/catch for region: R(143:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|21|22|23|25|26|27|29|30|31|33|34|35|37|38|39|41|42|43|(2:45|46)|47|(2:49|50)|51|(2:53|54)|55|(2:57|58)|59|(2:61|62)|63|65|66|67|69|70|71|73|74|75|77|78|79|81|82|83|85|86|87|88|89|90|91|92|(2:93|94)|95|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|127|128|129|130|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|147|148|149|150|151|152|153|154|155|156|157|158|159|160|161|162|163|164|165|166|167|168|169|170|171|172|173|174|176) */
        /* JADX WARNING: Can't wrap try/catch for region: R(144:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|21|22|23|25|26|27|29|30|31|33|34|35|37|38|39|41|42|43|(2:45|46)|47|(2:49|50)|51|(2:53|54)|55|(2:57|58)|59|61|62|63|65|66|67|69|70|71|73|74|75|77|78|79|81|82|83|85|86|87|88|89|90|91|92|(2:93|94)|95|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|127|128|129|130|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|147|148|149|150|151|152|153|154|155|156|157|158|159|160|161|162|163|164|165|166|167|168|169|170|171|172|173|174|176) */
        /* JADX WARNING: Can't wrap try/catch for region: R(145:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|17|18|19|21|22|23|25|26|27|29|30|31|33|34|35|37|38|39|41|42|43|(2:45|46)|47|(2:49|50)|51|(2:53|54)|55|(2:57|58)|59|61|62|63|65|66|67|69|70|71|73|74|75|77|78|79|81|82|83|85|86|87|88|89|90|91|92|(2:93|94)|95|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|127|128|129|130|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|147|148|149|150|151|152|153|154|155|156|157|158|159|160|161|162|163|164|165|166|167|168|169|170|171|172|173|174|176) */
        /* JADX WARNING: Can't wrap try/catch for region: R(147:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|17|18|19|21|22|23|25|26|27|29|30|31|33|34|35|37|38|39|41|42|43|(2:45|46)|47|(2:49|50)|51|(2:53|54)|55|57|58|59|61|62|63|65|66|67|69|70|71|73|74|75|77|78|79|81|82|83|85|86|87|88|89|90|91|92|93|94|95|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|127|128|129|130|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|147|148|149|150|151|152|153|154|155|156|157|158|159|160|161|162|163|164|165|166|167|168|169|170|171|172|173|174|176) */
        /* JADX WARNING: Can't wrap try/catch for region: R(148:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|15|17|18|19|21|22|23|25|26|27|29|30|31|33|34|35|37|38|39|41|42|43|(2:45|46)|47|(2:49|50)|51|(2:53|54)|55|57|58|59|61|62|63|65|66|67|69|70|71|73|74|75|77|78|79|81|82|83|85|86|87|88|89|90|91|92|93|94|95|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|127|128|129|130|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|147|148|149|150|151|152|153|154|155|156|157|158|159|160|161|162|163|164|165|166|167|168|169|170|171|172|173|174|176) */
        /* JADX WARNING: Can't wrap try/catch for region: R(150:0|(2:1|2)|3|(2:5|6)|7|9|10|11|13|14|15|17|18|19|21|22|23|25|26|27|29|30|31|33|34|35|37|38|39|41|42|43|(2:45|46)|47|(2:49|50)|51|53|54|55|57|58|59|61|62|63|65|66|67|69|70|71|73|74|75|77|78|79|81|82|83|85|86|87|88|89|90|91|92|93|94|95|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|127|128|129|130|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|147|148|149|150|151|152|153|154|155|156|157|158|159|160|161|162|163|164|165|166|167|168|169|170|171|172|173|174|176) */
        /* JADX WARNING: Can't wrap try/catch for region: R(151:0|(2:1|2)|3|5|6|7|9|10|11|13|14|15|17|18|19|21|22|23|25|26|27|29|30|31|33|34|35|37|38|39|41|42|43|(2:45|46)|47|(2:49|50)|51|53|54|55|57|58|59|61|62|63|65|66|67|69|70|71|73|74|75|77|78|79|81|82|83|85|86|87|88|89|90|91|92|93|94|95|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|127|128|129|130|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|147|148|149|150|151|152|153|154|155|156|157|158|159|160|161|162|163|164|165|166|167|168|169|170|171|172|173|174|176) */
        /* JADX WARNING: Can't wrap try/catch for region: R(152:0|(2:1|2)|3|5|6|7|9|10|11|13|14|15|17|18|19|21|22|23|25|26|27|29|30|31|33|34|35|37|38|39|41|42|43|(2:45|46)|47|49|50|51|53|54|55|57|58|59|61|62|63|65|66|67|69|70|71|73|74|75|77|78|79|81|82|83|85|86|87|88|89|90|91|92|93|94|95|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|127|128|129|130|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|147|148|149|150|151|152|153|154|155|156|157|158|159|160|161|162|163|164|165|166|167|168|169|170|171|172|173|174|176) */
        /* JADX WARNING: Can't wrap try/catch for region: R(153:0|1|2|3|5|6|7|9|10|11|13|14|15|17|18|19|21|22|23|25|26|27|29|30|31|33|34|35|37|38|39|41|42|43|(2:45|46)|47|49|50|51|53|54|55|57|58|59|61|62|63|65|66|67|69|70|71|73|74|75|77|78|79|81|82|83|85|86|87|88|89|90|91|92|93|94|95|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|127|128|129|130|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|147|148|149|150|151|152|153|154|155|156|157|158|159|160|161|162|163|164|165|166|167|168|169|170|171|172|173|174|176) */
        /* JADX WARNING: Can't wrap try/catch for region: R(154:0|1|2|3|5|6|7|9|10|11|13|14|15|17|18|19|21|22|23|25|26|27|29|30|31|33|34|35|37|38|39|41|42|43|45|46|47|49|50|51|53|54|55|57|58|59|61|62|63|65|66|67|69|70|71|73|74|75|77|78|79|81|82|83|85|86|87|88|89|90|91|92|93|94|95|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|127|128|129|130|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|147|148|149|150|151|152|153|154|155|156|157|158|159|160|161|162|163|164|165|166|167|168|169|170|171|172|173|174|176) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:101:0x0153 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:103:0x015d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:105:0x0167 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:107:0x0171 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:109:0x017b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:111:0x0185 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:113:0x018f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:115:0x0199 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:117:0x01a3 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:119:0x01ad */
        /* JADX WARNING: Missing exception handler attribute for start block: B:121:0x01b7 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:123:0x01c1 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:125:0x01cb */
        /* JADX WARNING: Missing exception handler attribute for start block: B:127:0x01d5 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:129:0x01e1 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:131:0x01eb */
        /* JADX WARNING: Missing exception handler attribute for start block: B:133:0x01f5 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:135:0x01ff */
        /* JADX WARNING: Missing exception handler attribute for start block: B:137:0x0209 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:139:0x0213 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:141:0x021d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:143:0x0229 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:145:0x0235 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:147:0x0241 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:149:0x024d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:151:0x0259 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:153:0x0265 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:155:0x0271 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:157:0x027d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:159:0x0289 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:161:0x0295 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:163:0x02a1 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:165:0x02ad */
        /* JADX WARNING: Missing exception handler attribute for start block: B:167:0x02b9 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:169:0x02c5 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:171:0x02d1 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:173:0x02dd */
        /* JADX WARNING: Missing exception handler attribute for start block: B:87:0x0108 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:89:0x0114 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:91:0x0120 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:93:0x012c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:99:0x0149 */
        static {
            /*
                com.facebook.react.uimanager.ReactAccessibilityDelegate$Role[] r0 = com.facebook.react.uimanager.ReactAccessibilityDelegate.Role.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$Role = r0
                r1 = 1
                com.facebook.react.uimanager.ReactAccessibilityDelegate$Role r2 = com.facebook.react.uimanager.ReactAccessibilityDelegate.Role.ALERT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$Role     // Catch:{ NoSuchFieldError -> 0x001d }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$Role r3 = com.facebook.react.uimanager.ReactAccessibilityDelegate.Role.BUTTON     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$Role     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$Role r4 = com.facebook.react.uimanager.ReactAccessibilityDelegate.Role.CHECKBOX     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                r3 = 4
                int[] r4 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$Role     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$Role r5 = com.facebook.react.uimanager.ReactAccessibilityDelegate.Role.COMBOBOX     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                r4 = 5
                int[] r5 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$Role     // Catch:{ NoSuchFieldError -> 0x003e }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$Role r6 = com.facebook.react.uimanager.ReactAccessibilityDelegate.Role.GRID     // Catch:{ NoSuchFieldError -> 0x003e }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r5[r6] = r4     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                r5 = 6
                int[] r6 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$Role     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$Role r7 = com.facebook.react.uimanager.ReactAccessibilityDelegate.Role.HEADING     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r6[r7] = r5     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                r6 = 7
                int[] r7 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$Role     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$Role r8 = com.facebook.react.uimanager.ReactAccessibilityDelegate.Role.IMG     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r8 = r8.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r7[r8] = r6     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                r7 = 8
                int[] r8 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$Role     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$Role r9 = com.facebook.react.uimanager.ReactAccessibilityDelegate.Role.LINK     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r9 = r9.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r8[r9] = r7     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                r8 = 9
                int[] r9 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$Role     // Catch:{ NoSuchFieldError -> 0x006c }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$Role r10 = com.facebook.react.uimanager.ReactAccessibilityDelegate.Role.LIST     // Catch:{ NoSuchFieldError -> 0x006c }
                int r10 = r10.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r9[r10] = r8     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                r9 = 10
                int[] r10 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$Role     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$Role r11 = com.facebook.react.uimanager.ReactAccessibilityDelegate.Role.MENU     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r11 = r11.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r10[r11] = r9     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                r10 = 11
                int[] r11 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$Role     // Catch:{ NoSuchFieldError -> 0x0084 }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$Role r12 = com.facebook.react.uimanager.ReactAccessibilityDelegate.Role.MENUBAR     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r12 = r12.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r11[r12] = r10     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                r11 = 12
                int[] r12 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$Role     // Catch:{ NoSuchFieldError -> 0x0090 }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$Role r13 = com.facebook.react.uimanager.ReactAccessibilityDelegate.Role.MENUITEM     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r13 = r13.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r12[r13] = r11     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                r12 = 13
                int[] r13 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$Role     // Catch:{ NoSuchFieldError -> 0x009c }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$Role r14 = com.facebook.react.uimanager.ReactAccessibilityDelegate.Role.NONE     // Catch:{ NoSuchFieldError -> 0x009c }
                int r14 = r14.ordinal()     // Catch:{ NoSuchFieldError -> 0x009c }
                r13[r14] = r12     // Catch:{ NoSuchFieldError -> 0x009c }
            L_0x009c:
                r13 = 14
                int[] r14 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$Role     // Catch:{ NoSuchFieldError -> 0x00a8 }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$Role r15 = com.facebook.react.uimanager.ReactAccessibilityDelegate.Role.PROGRESSBAR     // Catch:{ NoSuchFieldError -> 0x00a8 }
                int r15 = r15.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a8 }
                r14[r15] = r13     // Catch:{ NoSuchFieldError -> 0x00a8 }
            L_0x00a8:
                r14 = 15
                int[] r15 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$Role     // Catch:{ NoSuchFieldError -> 0x00b4 }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$Role r16 = com.facebook.react.uimanager.ReactAccessibilityDelegate.Role.RADIO     // Catch:{ NoSuchFieldError -> 0x00b4 }
                int r16 = r16.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b4 }
                r15[r16] = r14     // Catch:{ NoSuchFieldError -> 0x00b4 }
            L_0x00b4:
                r15 = 16
                int[] r16 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$Role     // Catch:{ NoSuchFieldError -> 0x00c0 }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$Role r17 = com.facebook.react.uimanager.ReactAccessibilityDelegate.Role.RADIOGROUP     // Catch:{ NoSuchFieldError -> 0x00c0 }
                int r17 = r17.ordinal()     // Catch:{ NoSuchFieldError -> 0x00c0 }
                r16[r17] = r15     // Catch:{ NoSuchFieldError -> 0x00c0 }
            L_0x00c0:
                r16 = 17
                int[] r17 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$Role     // Catch:{ NoSuchFieldError -> 0x00cc }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$Role r18 = com.facebook.react.uimanager.ReactAccessibilityDelegate.Role.SCROLLBAR     // Catch:{ NoSuchFieldError -> 0x00cc }
                int r18 = r18.ordinal()     // Catch:{ NoSuchFieldError -> 0x00cc }
                r17[r18] = r16     // Catch:{ NoSuchFieldError -> 0x00cc }
            L_0x00cc:
                r17 = 18
                int[] r18 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$Role     // Catch:{ NoSuchFieldError -> 0x00d8 }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$Role r19 = com.facebook.react.uimanager.ReactAccessibilityDelegate.Role.SEARCHBOX     // Catch:{ NoSuchFieldError -> 0x00d8 }
                int r19 = r19.ordinal()     // Catch:{ NoSuchFieldError -> 0x00d8 }
                r18[r19] = r17     // Catch:{ NoSuchFieldError -> 0x00d8 }
            L_0x00d8:
                r18 = 19
                int[] r19 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$Role     // Catch:{ NoSuchFieldError -> 0x00e4 }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$Role r20 = com.facebook.react.uimanager.ReactAccessibilityDelegate.Role.SLIDER     // Catch:{ NoSuchFieldError -> 0x00e4 }
                int r20 = r20.ordinal()     // Catch:{ NoSuchFieldError -> 0x00e4 }
                r19[r20] = r18     // Catch:{ NoSuchFieldError -> 0x00e4 }
            L_0x00e4:
                r19 = 20
                int[] r20 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$Role     // Catch:{ NoSuchFieldError -> 0x00f0 }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$Role r21 = com.facebook.react.uimanager.ReactAccessibilityDelegate.Role.SPINBUTTON     // Catch:{ NoSuchFieldError -> 0x00f0 }
                int r21 = r21.ordinal()     // Catch:{ NoSuchFieldError -> 0x00f0 }
                r20[r21] = r19     // Catch:{ NoSuchFieldError -> 0x00f0 }
            L_0x00f0:
                r20 = 21
                int[] r21 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$Role     // Catch:{ NoSuchFieldError -> 0x00fc }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$Role r22 = com.facebook.react.uimanager.ReactAccessibilityDelegate.Role.SUMMARY     // Catch:{ NoSuchFieldError -> 0x00fc }
                int r22 = r22.ordinal()     // Catch:{ NoSuchFieldError -> 0x00fc }
                r21[r22] = r20     // Catch:{ NoSuchFieldError -> 0x00fc }
            L_0x00fc:
                r21 = 22
                int[] r22 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$Role     // Catch:{ NoSuchFieldError -> 0x0108 }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$Role r23 = com.facebook.react.uimanager.ReactAccessibilityDelegate.Role.SWITCH     // Catch:{ NoSuchFieldError -> 0x0108 }
                int r23 = r23.ordinal()     // Catch:{ NoSuchFieldError -> 0x0108 }
                r22[r23] = r21     // Catch:{ NoSuchFieldError -> 0x0108 }
            L_0x0108:
                int[] r22 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$Role     // Catch:{ NoSuchFieldError -> 0x0114 }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$Role r23 = com.facebook.react.uimanager.ReactAccessibilityDelegate.Role.TAB     // Catch:{ NoSuchFieldError -> 0x0114 }
                int r23 = r23.ordinal()     // Catch:{ NoSuchFieldError -> 0x0114 }
                r24 = 23
                r22[r23] = r24     // Catch:{ NoSuchFieldError -> 0x0114 }
            L_0x0114:
                int[] r22 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$Role     // Catch:{ NoSuchFieldError -> 0x0120 }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$Role r23 = com.facebook.react.uimanager.ReactAccessibilityDelegate.Role.TABLIST     // Catch:{ NoSuchFieldError -> 0x0120 }
                int r23 = r23.ordinal()     // Catch:{ NoSuchFieldError -> 0x0120 }
                r24 = 24
                r22[r23] = r24     // Catch:{ NoSuchFieldError -> 0x0120 }
            L_0x0120:
                int[] r22 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$Role     // Catch:{ NoSuchFieldError -> 0x012c }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$Role r23 = com.facebook.react.uimanager.ReactAccessibilityDelegate.Role.TIMER     // Catch:{ NoSuchFieldError -> 0x012c }
                int r23 = r23.ordinal()     // Catch:{ NoSuchFieldError -> 0x012c }
                r24 = 25
                r22[r23] = r24     // Catch:{ NoSuchFieldError -> 0x012c }
            L_0x012c:
                int[] r22 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$Role     // Catch:{ NoSuchFieldError -> 0x0138 }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$Role r23 = com.facebook.react.uimanager.ReactAccessibilityDelegate.Role.TOOLBAR     // Catch:{ NoSuchFieldError -> 0x0138 }
                int r23 = r23.ordinal()     // Catch:{ NoSuchFieldError -> 0x0138 }
                r24 = 26
                r22[r23] = r24     // Catch:{ NoSuchFieldError -> 0x0138 }
            L_0x0138:
                com.facebook.react.uimanager.ReactAccessibilityDelegate$AccessibilityRole[] r15 = com.facebook.react.uimanager.ReactAccessibilityDelegate.AccessibilityRole.values()
                int r15 = r15.length
                int[] r15 = new int[r15]
                $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$AccessibilityRole = r15
                com.facebook.react.uimanager.ReactAccessibilityDelegate$AccessibilityRole r23 = com.facebook.react.uimanager.ReactAccessibilityDelegate.AccessibilityRole.BUTTON     // Catch:{ NoSuchFieldError -> 0x0149 }
                int r23 = r23.ordinal()     // Catch:{ NoSuchFieldError -> 0x0149 }
                r15[r23] = r1     // Catch:{ NoSuchFieldError -> 0x0149 }
            L_0x0149:
                int[] r1 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$AccessibilityRole     // Catch:{ NoSuchFieldError -> 0x0153 }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$AccessibilityRole r15 = com.facebook.react.uimanager.ReactAccessibilityDelegate.AccessibilityRole.DROPDOWNLIST     // Catch:{ NoSuchFieldError -> 0x0153 }
                int r15 = r15.ordinal()     // Catch:{ NoSuchFieldError -> 0x0153 }
                r1[r15] = r0     // Catch:{ NoSuchFieldError -> 0x0153 }
            L_0x0153:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$AccessibilityRole     // Catch:{ NoSuchFieldError -> 0x015d }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$AccessibilityRole r1 = com.facebook.react.uimanager.ReactAccessibilityDelegate.AccessibilityRole.TOGGLEBUTTON     // Catch:{ NoSuchFieldError -> 0x015d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x015d }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x015d }
            L_0x015d:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$AccessibilityRole     // Catch:{ NoSuchFieldError -> 0x0167 }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$AccessibilityRole r1 = com.facebook.react.uimanager.ReactAccessibilityDelegate.AccessibilityRole.SEARCH     // Catch:{ NoSuchFieldError -> 0x0167 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0167 }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x0167 }
            L_0x0167:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$AccessibilityRole     // Catch:{ NoSuchFieldError -> 0x0171 }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$AccessibilityRole r1 = com.facebook.react.uimanager.ReactAccessibilityDelegate.AccessibilityRole.IMAGE     // Catch:{ NoSuchFieldError -> 0x0171 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0171 }
                r0[r1] = r4     // Catch:{ NoSuchFieldError -> 0x0171 }
            L_0x0171:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$AccessibilityRole     // Catch:{ NoSuchFieldError -> 0x017b }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$AccessibilityRole r1 = com.facebook.react.uimanager.ReactAccessibilityDelegate.AccessibilityRole.IMAGEBUTTON     // Catch:{ NoSuchFieldError -> 0x017b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x017b }
                r0[r1] = r5     // Catch:{ NoSuchFieldError -> 0x017b }
            L_0x017b:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$AccessibilityRole     // Catch:{ NoSuchFieldError -> 0x0185 }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$AccessibilityRole r1 = com.facebook.react.uimanager.ReactAccessibilityDelegate.AccessibilityRole.KEYBOARDKEY     // Catch:{ NoSuchFieldError -> 0x0185 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0185 }
                r0[r1] = r6     // Catch:{ NoSuchFieldError -> 0x0185 }
            L_0x0185:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$AccessibilityRole     // Catch:{ NoSuchFieldError -> 0x018f }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$AccessibilityRole r1 = com.facebook.react.uimanager.ReactAccessibilityDelegate.AccessibilityRole.TEXT     // Catch:{ NoSuchFieldError -> 0x018f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x018f }
                r0[r1] = r7     // Catch:{ NoSuchFieldError -> 0x018f }
            L_0x018f:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$AccessibilityRole     // Catch:{ NoSuchFieldError -> 0x0199 }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$AccessibilityRole r1 = com.facebook.react.uimanager.ReactAccessibilityDelegate.AccessibilityRole.ADJUSTABLE     // Catch:{ NoSuchFieldError -> 0x0199 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0199 }
                r0[r1] = r8     // Catch:{ NoSuchFieldError -> 0x0199 }
            L_0x0199:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$AccessibilityRole     // Catch:{ NoSuchFieldError -> 0x01a3 }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$AccessibilityRole r1 = com.facebook.react.uimanager.ReactAccessibilityDelegate.AccessibilityRole.CHECKBOX     // Catch:{ NoSuchFieldError -> 0x01a3 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x01a3 }
                r0[r1] = r9     // Catch:{ NoSuchFieldError -> 0x01a3 }
            L_0x01a3:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$AccessibilityRole     // Catch:{ NoSuchFieldError -> 0x01ad }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$AccessibilityRole r1 = com.facebook.react.uimanager.ReactAccessibilityDelegate.AccessibilityRole.RADIO     // Catch:{ NoSuchFieldError -> 0x01ad }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x01ad }
                r0[r1] = r10     // Catch:{ NoSuchFieldError -> 0x01ad }
            L_0x01ad:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$AccessibilityRole     // Catch:{ NoSuchFieldError -> 0x01b7 }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$AccessibilityRole r1 = com.facebook.react.uimanager.ReactAccessibilityDelegate.AccessibilityRole.SPINBUTTON     // Catch:{ NoSuchFieldError -> 0x01b7 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x01b7 }
                r0[r1] = r11     // Catch:{ NoSuchFieldError -> 0x01b7 }
            L_0x01b7:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$AccessibilityRole     // Catch:{ NoSuchFieldError -> 0x01c1 }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$AccessibilityRole r1 = com.facebook.react.uimanager.ReactAccessibilityDelegate.AccessibilityRole.SWITCH     // Catch:{ NoSuchFieldError -> 0x01c1 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x01c1 }
                r0[r1] = r12     // Catch:{ NoSuchFieldError -> 0x01c1 }
            L_0x01c1:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$AccessibilityRole     // Catch:{ NoSuchFieldError -> 0x01cb }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$AccessibilityRole r1 = com.facebook.react.uimanager.ReactAccessibilityDelegate.AccessibilityRole.LIST     // Catch:{ NoSuchFieldError -> 0x01cb }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x01cb }
                r0[r1] = r13     // Catch:{ NoSuchFieldError -> 0x01cb }
            L_0x01cb:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$AccessibilityRole     // Catch:{ NoSuchFieldError -> 0x01d5 }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$AccessibilityRole r1 = com.facebook.react.uimanager.ReactAccessibilityDelegate.AccessibilityRole.GRID     // Catch:{ NoSuchFieldError -> 0x01d5 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x01d5 }
                r0[r1] = r14     // Catch:{ NoSuchFieldError -> 0x01d5 }
            L_0x01d5:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$AccessibilityRole     // Catch:{ NoSuchFieldError -> 0x01e1 }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$AccessibilityRole r1 = com.facebook.react.uimanager.ReactAccessibilityDelegate.AccessibilityRole.SCROLLVIEW     // Catch:{ NoSuchFieldError -> 0x01e1 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x01e1 }
                r2 = 16
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x01e1 }
            L_0x01e1:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$AccessibilityRole     // Catch:{ NoSuchFieldError -> 0x01eb }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$AccessibilityRole r1 = com.facebook.react.uimanager.ReactAccessibilityDelegate.AccessibilityRole.HORIZONTALSCROLLVIEW     // Catch:{ NoSuchFieldError -> 0x01eb }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x01eb }
                r0[r1] = r16     // Catch:{ NoSuchFieldError -> 0x01eb }
            L_0x01eb:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$AccessibilityRole     // Catch:{ NoSuchFieldError -> 0x01f5 }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$AccessibilityRole r1 = com.facebook.react.uimanager.ReactAccessibilityDelegate.AccessibilityRole.PAGER     // Catch:{ NoSuchFieldError -> 0x01f5 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x01f5 }
                r0[r1] = r17     // Catch:{ NoSuchFieldError -> 0x01f5 }
            L_0x01f5:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$AccessibilityRole     // Catch:{ NoSuchFieldError -> 0x01ff }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$AccessibilityRole r1 = com.facebook.react.uimanager.ReactAccessibilityDelegate.AccessibilityRole.DRAWERLAYOUT     // Catch:{ NoSuchFieldError -> 0x01ff }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x01ff }
                r0[r1] = r18     // Catch:{ NoSuchFieldError -> 0x01ff }
            L_0x01ff:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$AccessibilityRole     // Catch:{ NoSuchFieldError -> 0x0209 }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$AccessibilityRole r1 = com.facebook.react.uimanager.ReactAccessibilityDelegate.AccessibilityRole.SLIDINGDRAWER     // Catch:{ NoSuchFieldError -> 0x0209 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0209 }
                r0[r1] = r19     // Catch:{ NoSuchFieldError -> 0x0209 }
            L_0x0209:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$AccessibilityRole     // Catch:{ NoSuchFieldError -> 0x0213 }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$AccessibilityRole r1 = com.facebook.react.uimanager.ReactAccessibilityDelegate.AccessibilityRole.ICONMENU     // Catch:{ NoSuchFieldError -> 0x0213 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0213 }
                r0[r1] = r20     // Catch:{ NoSuchFieldError -> 0x0213 }
            L_0x0213:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$AccessibilityRole     // Catch:{ NoSuchFieldError -> 0x021d }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$AccessibilityRole r1 = com.facebook.react.uimanager.ReactAccessibilityDelegate.AccessibilityRole.VIEWGROUP     // Catch:{ NoSuchFieldError -> 0x021d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x021d }
                r0[r1] = r21     // Catch:{ NoSuchFieldError -> 0x021d }
            L_0x021d:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$AccessibilityRole     // Catch:{ NoSuchFieldError -> 0x0229 }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$AccessibilityRole r1 = com.facebook.react.uimanager.ReactAccessibilityDelegate.AccessibilityRole.WEBVIEW     // Catch:{ NoSuchFieldError -> 0x0229 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0229 }
                r2 = 23
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0229 }
            L_0x0229:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$AccessibilityRole     // Catch:{ NoSuchFieldError -> 0x0235 }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$AccessibilityRole r1 = com.facebook.react.uimanager.ReactAccessibilityDelegate.AccessibilityRole.NONE     // Catch:{ NoSuchFieldError -> 0x0235 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0235 }
                r2 = 24
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0235 }
            L_0x0235:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$AccessibilityRole     // Catch:{ NoSuchFieldError -> 0x0241 }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$AccessibilityRole r1 = com.facebook.react.uimanager.ReactAccessibilityDelegate.AccessibilityRole.LINK     // Catch:{ NoSuchFieldError -> 0x0241 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0241 }
                r2 = 25
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0241 }
            L_0x0241:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$AccessibilityRole     // Catch:{ NoSuchFieldError -> 0x024d }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$AccessibilityRole r1 = com.facebook.react.uimanager.ReactAccessibilityDelegate.AccessibilityRole.SUMMARY     // Catch:{ NoSuchFieldError -> 0x024d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x024d }
                r2 = 26
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x024d }
            L_0x024d:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$AccessibilityRole     // Catch:{ NoSuchFieldError -> 0x0259 }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$AccessibilityRole r1 = com.facebook.react.uimanager.ReactAccessibilityDelegate.AccessibilityRole.HEADER     // Catch:{ NoSuchFieldError -> 0x0259 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0259 }
                r2 = 27
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0259 }
            L_0x0259:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$AccessibilityRole     // Catch:{ NoSuchFieldError -> 0x0265 }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$AccessibilityRole r1 = com.facebook.react.uimanager.ReactAccessibilityDelegate.AccessibilityRole.ALERT     // Catch:{ NoSuchFieldError -> 0x0265 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0265 }
                r2 = 28
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0265 }
            L_0x0265:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$AccessibilityRole     // Catch:{ NoSuchFieldError -> 0x0271 }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$AccessibilityRole r1 = com.facebook.react.uimanager.ReactAccessibilityDelegate.AccessibilityRole.COMBOBOX     // Catch:{ NoSuchFieldError -> 0x0271 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0271 }
                r2 = 29
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0271 }
            L_0x0271:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$AccessibilityRole     // Catch:{ NoSuchFieldError -> 0x027d }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$AccessibilityRole r1 = com.facebook.react.uimanager.ReactAccessibilityDelegate.AccessibilityRole.MENU     // Catch:{ NoSuchFieldError -> 0x027d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x027d }
                r2 = 30
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x027d }
            L_0x027d:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$AccessibilityRole     // Catch:{ NoSuchFieldError -> 0x0289 }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$AccessibilityRole r1 = com.facebook.react.uimanager.ReactAccessibilityDelegate.AccessibilityRole.MENUBAR     // Catch:{ NoSuchFieldError -> 0x0289 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0289 }
                r2 = 31
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0289 }
            L_0x0289:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$AccessibilityRole     // Catch:{ NoSuchFieldError -> 0x0295 }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$AccessibilityRole r1 = com.facebook.react.uimanager.ReactAccessibilityDelegate.AccessibilityRole.MENUITEM     // Catch:{ NoSuchFieldError -> 0x0295 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0295 }
                r2 = 32
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0295 }
            L_0x0295:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$AccessibilityRole     // Catch:{ NoSuchFieldError -> 0x02a1 }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$AccessibilityRole r1 = com.facebook.react.uimanager.ReactAccessibilityDelegate.AccessibilityRole.PROGRESSBAR     // Catch:{ NoSuchFieldError -> 0x02a1 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x02a1 }
                r2 = 33
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x02a1 }
            L_0x02a1:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$AccessibilityRole     // Catch:{ NoSuchFieldError -> 0x02ad }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$AccessibilityRole r1 = com.facebook.react.uimanager.ReactAccessibilityDelegate.AccessibilityRole.RADIOGROUP     // Catch:{ NoSuchFieldError -> 0x02ad }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x02ad }
                r2 = 34
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x02ad }
            L_0x02ad:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$AccessibilityRole     // Catch:{ NoSuchFieldError -> 0x02b9 }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$AccessibilityRole r1 = com.facebook.react.uimanager.ReactAccessibilityDelegate.AccessibilityRole.SCROLLBAR     // Catch:{ NoSuchFieldError -> 0x02b9 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x02b9 }
                r2 = 35
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x02b9 }
            L_0x02b9:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$AccessibilityRole     // Catch:{ NoSuchFieldError -> 0x02c5 }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$AccessibilityRole r1 = com.facebook.react.uimanager.ReactAccessibilityDelegate.AccessibilityRole.TAB     // Catch:{ NoSuchFieldError -> 0x02c5 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x02c5 }
                r2 = 36
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x02c5 }
            L_0x02c5:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$AccessibilityRole     // Catch:{ NoSuchFieldError -> 0x02d1 }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$AccessibilityRole r1 = com.facebook.react.uimanager.ReactAccessibilityDelegate.AccessibilityRole.TABLIST     // Catch:{ NoSuchFieldError -> 0x02d1 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x02d1 }
                r2 = 37
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x02d1 }
            L_0x02d1:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$AccessibilityRole     // Catch:{ NoSuchFieldError -> 0x02dd }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$AccessibilityRole r1 = com.facebook.react.uimanager.ReactAccessibilityDelegate.AccessibilityRole.TIMER     // Catch:{ NoSuchFieldError -> 0x02dd }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x02dd }
                r2 = 38
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x02dd }
            L_0x02dd:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$ReactAccessibilityDelegate$AccessibilityRole     // Catch:{ NoSuchFieldError -> 0x02e9 }
                com.facebook.react.uimanager.ReactAccessibilityDelegate$AccessibilityRole r1 = com.facebook.react.uimanager.ReactAccessibilityDelegate.AccessibilityRole.TOOLBAR     // Catch:{ NoSuchFieldError -> 0x02e9 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x02e9 }
                r2 = 39
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x02e9 }
            L_0x02e9:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.ReactAccessibilityDelegate.AnonymousClass3.<clinit>():void");
        }
    }

    public ReactAccessibilityDelegate(View view, boolean z, int i) {
        super(view);
        this.mView = view;
        view.setFocusable(z);
        ViewCompat.setImportantForAccessibility(view, i);
        this.mAccessibilityLinks = (AccessibilityLinks) view.getTag(R.id.accessibility_links);
    }

    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        ReadableType readableType;
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        if (view.getTag(R.id.accessibility_state_expanded) != null) {
            accessibilityNodeInfoCompat.addAction(((Boolean) view.getTag(R.id.accessibility_state_expanded)).booleanValue() ? 524288 : 262144);
        }
        AccessibilityRole fromViewTag = AccessibilityRole.fromViewTag(view);
        String str = (String) view.getTag(R.id.accessibility_hint);
        if (fromViewTag != null) {
            setRole(accessibilityNodeInfoCompat, fromViewTag, view.getContext());
        }
        if (str != null) {
            accessibilityNodeInfoCompat.setTooltipText(str);
        }
        Object tag = view.getTag(R.id.labelled_by);
        if (tag != null) {
            View findView = ReactFindViewUtil.findView(view.getRootView(), (String) tag);
            this.mAccessibilityLabelledBy = findView;
            if (findView != null) {
                accessibilityNodeInfoCompat.setLabeledBy(findView);
            }
        }
        ReadableMap readableMap = (ReadableMap) view.getTag(R.id.accessibility_state);
        if (readableMap != null) {
            setState(accessibilityNodeInfoCompat, readableMap, view.getContext());
        }
        ReadableArray readableArray = (ReadableArray) view.getTag(R.id.accessibility_actions);
        ReadableMap readableMap2 = (ReadableMap) view.getTag(R.id.accessibility_collection_item);
        if (readableMap2 != null) {
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(readableMap2.getInt("rowIndex"), readableMap2.getInt("rowSpan"), readableMap2.getInt("columnIndex"), readableMap2.getInt("columnSpan"), readableMap2.getBoolean("heading")));
        }
        boolean z = true;
        if (readableArray != null) {
            int i = 0;
            while (i < readableArray.size()) {
                ReadableMap map = readableArray.getMap(i);
                if (map.hasKey("name")) {
                    int i2 = sCounter;
                    String string = map.hasKey(Constants.ScionAnalytics.PARAM_LABEL) ? map.getString(Constants.ScionAnalytics.PARAM_LABEL) : null;
                    HashMap<String, Integer> hashMap = sActionIdMap;
                    if (hashMap.containsKey(map.getString("name"))) {
                        i2 = hashMap.get(map.getString("name")).intValue();
                    } else {
                        sCounter++;
                    }
                    this.mAccessibilityActionsMap.put(Integer.valueOf(i2), map.getString("name"));
                    accessibilityNodeInfoCompat.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(i2, string));
                    i++;
                } else {
                    throw new IllegalArgumentException("Unknown accessibility action.");
                }
            }
        }
        ReadableMap readableMap3 = (ReadableMap) view.getTag(R.id.accessibility_value);
        if (readableMap3 != null && readableMap3.hasKey("min") && readableMap3.hasKey("now") && readableMap3.hasKey("max")) {
            Dynamic dynamic = readableMap3.getDynamic("min");
            Dynamic dynamic2 = readableMap3.getDynamic("now");
            Dynamic dynamic3 = readableMap3.getDynamic("max");
            if (dynamic != null && dynamic.getType() == (readableType = ReadableType.Number) && dynamic2 != null && dynamic2.getType() == readableType && dynamic3 != null && dynamic3.getType() == readableType) {
                int asInt = dynamic.asInt();
                int asInt2 = dynamic2.asInt();
                int asInt3 = dynamic3.asInt();
                if (asInt3 > asInt && asInt2 >= asInt && asInt3 >= asInt2) {
                    accessibilityNodeInfoCompat.setRangeInfo(AccessibilityNodeInfoCompat.RangeInfoCompat.obtain(0, (float) asInt, (float) asInt3, (float) asInt2));
                }
            }
        }
        String str2 = (String) view.getTag(R.id.react_test_id);
        if (str2 != null) {
            accessibilityNodeInfoCompat.setViewIdResourceName(str2);
        }
        boolean z2 = TextUtils.isEmpty(accessibilityNodeInfoCompat.getContentDescription()) && TextUtils.isEmpty(accessibilityNodeInfoCompat.getText());
        if (readableArray == null && readableMap == null && tag == null && fromViewTag == null) {
            z = false;
        }
        if (z2 && z) {
            accessibilityNodeInfoCompat.setContentDescription(getTalkbackDescription(view, accessibilityNodeInfoCompat));
        }
    }

    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        ReadableType readableType;
        super.onInitializeAccessibilityEvent(view, accessibilityEvent);
        ReadableMap readableMap = (ReadableMap) view.getTag(R.id.accessibility_value);
        if (readableMap != null && readableMap.hasKey("min") && readableMap.hasKey("now") && readableMap.hasKey("max")) {
            Dynamic dynamic = readableMap.getDynamic("min");
            Dynamic dynamic2 = readableMap.getDynamic("now");
            Dynamic dynamic3 = readableMap.getDynamic("max");
            if (dynamic != null && dynamic.getType() == (readableType = ReadableType.Number) && dynamic2 != null && dynamic2.getType() == readableType && dynamic3 != null && dynamic3.getType() == readableType) {
                int asInt = dynamic.asInt();
                int asInt2 = dynamic2.asInt();
                int asInt3 = dynamic3.asInt();
                if (asInt3 > asInt && asInt2 >= asInt && asInt3 >= asInt2) {
                    accessibilityEvent.setItemCount(asInt3 - asInt);
                    accessibilityEvent.setCurrentItemIndex(asInt2);
                }
            }
        }
    }

    public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
        if (i == 524288) {
            view.setTag(R.id.accessibility_state_expanded, Boolean.FALSE);
        }
        if (i == 262144) {
            view.setTag(R.id.accessibility_state_expanded, Boolean.TRUE);
        }
        if (!this.mAccessibilityActionsMap.containsKey(Integer.valueOf(i))) {
            return super.performAccessibilityAction(view, i, bundle);
        }
        final WritableMap createMap = Arguments.createMap();
        createMap.putString("actionName", this.mAccessibilityActionsMap.get(Integer.valueOf(i)));
        ReactContext reactContext = (ReactContext) view.getContext();
        if (reactContext.hasActiveReactInstance()) {
            int id = view.getId();
            int surfaceId = UIManagerHelper.getSurfaceId((Context) reactContext);
            UIManager uIManager = UIManagerHelper.getUIManager(reactContext, ViewUtil.getUIManagerType(id));
            if (uIManager != null) {
                uIManager.getEventDispatcher().dispatchEvent(new Event(surfaceId, id) {
                    public String getEventName() {
                        return ReactAccessibilityDelegate.TOP_ACCESSIBILITY_ACTION_EVENT;
                    }

                    /* access modifiers changed from: protected */
                    public WritableMap getEventData() {
                        return createMap;
                    }
                });
            }
        } else {
            ReactSoftExceptionLogger.logSoftException(TAG, new ReactNoCrashSoftException("Cannot get RCTEventEmitter, no CatalystInstance"));
        }
        AccessibilityRole accessibilityRole = (AccessibilityRole) view.getTag(R.id.accessibility_role);
        ReadableMap readableMap = (ReadableMap) view.getTag(R.id.accessibility_value);
        if (accessibilityRole != AccessibilityRole.ADJUSTABLE) {
            return true;
        }
        if (i != AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_FORWARD.getId() && i != AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD.getId()) {
            return true;
        }
        if (readableMap != null && !readableMap.hasKey("text")) {
            scheduleAccessibilityEventSender(view);
        }
        return super.performAccessibilityAction(view, i, bundle);
    }

    private static void setState(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, ReadableMap readableMap, Context context) {
        ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            Dynamic dynamic = readableMap.getDynamic(nextKey);
            if (nextKey.equals(STATE_SELECTED) && dynamic.getType() == ReadableType.Boolean) {
                accessibilityNodeInfoCompat.setSelected(dynamic.asBoolean());
            } else if (nextKey.equals(STATE_DISABLED) && dynamic.getType() == ReadableType.Boolean) {
                accessibilityNodeInfoCompat.setEnabled(!dynamic.asBoolean());
            } else if (nextKey.equals(STATE_CHECKED) && dynamic.getType() == ReadableType.Boolean) {
                boolean asBoolean = dynamic.asBoolean();
                accessibilityNodeInfoCompat.setCheckable(true);
                accessibilityNodeInfoCompat.setChecked(asBoolean);
            }
        }
    }

    public static void setRole(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, AccessibilityRole accessibilityRole, Context context) {
        if (accessibilityRole == null) {
            accessibilityRole = AccessibilityRole.NONE;
        }
        accessibilityNodeInfoCompat.setClassName(AccessibilityRole.getValue(accessibilityRole));
        if (accessibilityRole.equals(AccessibilityRole.LINK)) {
            accessibilityNodeInfoCompat.setRoleDescription(context.getString(R.string.link_description));
        } else if (accessibilityRole.equals(AccessibilityRole.IMAGE)) {
            accessibilityNodeInfoCompat.setRoleDescription(context.getString(R.string.image_description));
        } else if (accessibilityRole.equals(AccessibilityRole.IMAGEBUTTON)) {
            accessibilityNodeInfoCompat.setRoleDescription(context.getString(R.string.imagebutton_description));
            accessibilityNodeInfoCompat.setClickable(true);
        } else if (accessibilityRole.equals(AccessibilityRole.BUTTON)) {
            accessibilityNodeInfoCompat.setClickable(true);
        } else if (accessibilityRole.equals(AccessibilityRole.TOGGLEBUTTON)) {
            accessibilityNodeInfoCompat.setClickable(true);
            accessibilityNodeInfoCompat.setCheckable(true);
        } else if (accessibilityRole.equals(AccessibilityRole.SUMMARY)) {
            accessibilityNodeInfoCompat.setRoleDescription(context.getString(R.string.summary_description));
        } else if (accessibilityRole.equals(AccessibilityRole.HEADER)) {
            accessibilityNodeInfoCompat.setHeading(true);
        } else if (accessibilityRole.equals(AccessibilityRole.ALERT)) {
            accessibilityNodeInfoCompat.setRoleDescription(context.getString(R.string.alert_description));
        } else if (accessibilityRole.equals(AccessibilityRole.COMBOBOX)) {
            accessibilityNodeInfoCompat.setRoleDescription(context.getString(R.string.combobox_description));
        } else if (accessibilityRole.equals(AccessibilityRole.MENU)) {
            accessibilityNodeInfoCompat.setRoleDescription(context.getString(R.string.menu_description));
        } else if (accessibilityRole.equals(AccessibilityRole.MENUBAR)) {
            accessibilityNodeInfoCompat.setRoleDescription(context.getString(R.string.menubar_description));
        } else if (accessibilityRole.equals(AccessibilityRole.MENUITEM)) {
            accessibilityNodeInfoCompat.setRoleDescription(context.getString(R.string.menuitem_description));
        } else if (accessibilityRole.equals(AccessibilityRole.PROGRESSBAR)) {
            accessibilityNodeInfoCompat.setRoleDescription(context.getString(R.string.progressbar_description));
        } else if (accessibilityRole.equals(AccessibilityRole.RADIOGROUP)) {
            accessibilityNodeInfoCompat.setRoleDescription(context.getString(R.string.radiogroup_description));
        } else if (accessibilityRole.equals(AccessibilityRole.SCROLLBAR)) {
            accessibilityNodeInfoCompat.setRoleDescription(context.getString(R.string.scrollbar_description));
        } else if (accessibilityRole.equals(AccessibilityRole.SPINBUTTON)) {
            accessibilityNodeInfoCompat.setRoleDescription(context.getString(R.string.spinbutton_description));
        } else if (accessibilityRole.equals(AccessibilityRole.TAB)) {
            accessibilityNodeInfoCompat.setRoleDescription(context.getString(R.string.rn_tab_description));
        } else if (accessibilityRole.equals(AccessibilityRole.TABLIST)) {
            accessibilityNodeInfoCompat.setRoleDescription(context.getString(R.string.tablist_description));
        } else if (accessibilityRole.equals(AccessibilityRole.TIMER)) {
            accessibilityNodeInfoCompat.setRoleDescription(context.getString(R.string.timer_description));
        } else if (accessibilityRole.equals(AccessibilityRole.TOOLBAR)) {
            accessibilityNodeInfoCompat.setRoleDescription(context.getString(R.string.toolbar_description));
        }
    }

    public static void setDelegate(View view, boolean z, int i) {
        if (ViewCompat.hasAccessibilityDelegate(view)) {
            return;
        }
        if (view.getTag(R.id.accessibility_role) != null || view.getTag(R.id.accessibility_state) != null || view.getTag(R.id.accessibility_actions) != null || view.getTag(R.id.react_test_id) != null || view.getTag(R.id.accessibility_collection_item) != null || view.getTag(R.id.accessibility_links) != null || view.getTag(R.id.role) != null) {
            ViewCompat.setAccessibilityDelegate(view, new ReactAccessibilityDelegate(view, z, i));
        }
    }

    public static void resetDelegate(View view, boolean z, int i) {
        ViewCompat.setAccessibilityDelegate(view, new ReactAccessibilityDelegate(view, z, i));
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0051, code lost:
        r6 = (android.text.Spanned) r0.getText();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getVirtualViewAt(float r5, float r6) {
        /*
            r4 = this;
            com.facebook.react.uimanager.ReactAccessibilityDelegate$AccessibilityLinks r0 = r4.mAccessibilityLinks
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r0 == 0) goto L_0x0069
            int r0 = r0.size()
            if (r0 == 0) goto L_0x0069
            android.view.View r0 = r4.mView
            boolean r2 = r0 instanceof android.widget.TextView
            if (r2 != 0) goto L_0x0013
            goto L_0x0069
        L_0x0013:
            android.widget.TextView r0 = (android.widget.TextView) r0
            java.lang.CharSequence r2 = r0.getText()
            boolean r2 = r2 instanceof android.text.Spanned
            if (r2 != 0) goto L_0x001e
            return r1
        L_0x001e:
            android.text.Layout r2 = r0.getLayout()
            if (r2 != 0) goto L_0x0025
            return r1
        L_0x0025:
            int r3 = r0.getTotalPaddingLeft()
            float r3 = (float) r3
            float r5 = r5 - r3
            int r3 = r0.getTotalPaddingTop()
            float r3 = (float) r3
            float r6 = r6 - r3
            int r3 = r0.getScrollX()
            float r3 = (float) r3
            float r5 = r5 + r3
            int r3 = r0.getScrollY()
            float r3 = (float) r3
            float r6 = r6 + r3
            int r6 = (int) r6
            int r6 = r2.getLineForVertical(r6)
            int r5 = r2.getOffsetForHorizontal(r6, r5)
            java.lang.Class<android.text.style.ClickableSpan> r6 = android.text.style.ClickableSpan.class
            java.lang.Object r5 = r4.getFirstSpan(r5, r5, r6)
            android.text.style.ClickableSpan r5 = (android.text.style.ClickableSpan) r5
            if (r5 != 0) goto L_0x0051
            return r1
        L_0x0051:
            java.lang.CharSequence r6 = r0.getText()
            android.text.Spanned r6 = (android.text.Spanned) r6
            int r0 = r6.getSpanStart(r5)
            int r5 = r6.getSpanEnd(r5)
            com.facebook.react.uimanager.ReactAccessibilityDelegate$AccessibilityLinks r6 = r4.mAccessibilityLinks
            com.facebook.react.uimanager.ReactAccessibilityDelegate$AccessibilityLinks$AccessibleLink r5 = r6.getLinkBySpanPos(r0, r5)
            if (r5 == 0) goto L_0x0069
            int r1 = r5.id
        L_0x0069:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.ReactAccessibilityDelegate.getVirtualViewAt(float, float):int");
    }

    /* access modifiers changed from: protected */
    public void getVisibleVirtualViews(List<Integer> list) {
        if (this.mAccessibilityLinks != null) {
            for (int i = 0; i < this.mAccessibilityLinks.size(); i++) {
                list.add(Integer.valueOf(i));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onPopulateNodeForVirtualView(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        AccessibilityLinks accessibilityLinks = this.mAccessibilityLinks;
        if (accessibilityLinks == null) {
            accessibilityNodeInfoCompat.setContentDescription("");
            accessibilityNodeInfoCompat.setBoundsInParent(new Rect(0, 0, 1, 1));
            return;
        }
        AccessibilityLinks.AccessibleLink linkById = accessibilityLinks.getLinkById(i);
        if (linkById == null) {
            accessibilityNodeInfoCompat.setContentDescription("");
            accessibilityNodeInfoCompat.setBoundsInParent(new Rect(0, 0, 1, 1));
            return;
        }
        Rect boundsInParent = getBoundsInParent(linkById);
        if (boundsInParent == null) {
            accessibilityNodeInfoCompat.setContentDescription("");
            accessibilityNodeInfoCompat.setBoundsInParent(new Rect(0, 0, 1, 1));
            return;
        }
        accessibilityNodeInfoCompat.setContentDescription(linkById.description);
        accessibilityNodeInfoCompat.addAction(16);
        accessibilityNodeInfoCompat.setBoundsInParent(boundsInParent);
        accessibilityNodeInfoCompat.setRoleDescription(this.mView.getResources().getString(R.string.link_description));
        accessibilityNodeInfoCompat.setClassName(AccessibilityRole.getValue(AccessibilityRole.BUTTON));
    }

    private Rect getBoundsInParent(AccessibilityLinks.AccessibleLink accessibleLink) {
        View view = this.mView;
        boolean z = false;
        if (!(view instanceof TextView)) {
            return new Rect(0, 0, this.mView.getWidth(), this.mView.getHeight());
        }
        TextView textView = (TextView) view;
        Layout layout = textView.getLayout();
        if (layout == null) {
            return new Rect(0, 0, textView.getWidth(), textView.getHeight());
        }
        double d = (double) accessibleLink.start;
        double d2 = (double) accessibleLink.end;
        int i = (int) d;
        int lineForOffset = layout.getLineForOffset(i);
        if (d > ((double) layout.getLineEnd(lineForOffset))) {
            return null;
        }
        Rect rect = new Rect();
        double primaryHorizontal = (double) layout.getPrimaryHorizontal(i);
        Paint paint = new Paint();
        AbsoluteSizeSpan absoluteSizeSpan = (AbsoluteSizeSpan) getFirstSpan(accessibleLink.start, accessibleLink.end, AbsoluteSizeSpan.class);
        paint.setTextSize(absoluteSizeSpan != null ? (float) absoluteSizeSpan.getSize() : textView.getTextSize());
        int ceil = (int) Math.ceil((double) paint.measureText(accessibleLink.description));
        if (lineForOffset != layout.getLineForOffset((int) d2)) {
            z = true;
        }
        layout.getLineBounds(lineForOffset, rect);
        int scrollY = textView.getScrollY() + textView.getTotalPaddingTop();
        rect.top += scrollY;
        rect.bottom += scrollY;
        rect.left = (int) (((double) rect.left) + ((primaryHorizontal + ((double) textView.getTotalPaddingLeft())) - ((double) textView.getScrollX())));
        if (z) {
            return new Rect(rect.left, rect.top, rect.right, rect.bottom);
        }
        int i2 = rect.left;
        return new Rect(i2, rect.top, ceil + i2, rect.bottom);
    }

    /* access modifiers changed from: protected */
    public <T> T getFirstSpan(int i, int i2, Class<T> cls) {
        View view = this.mView;
        if (!(view instanceof TextView) || !(((TextView) view).getText() instanceof Spanned)) {
            return null;
        }
        T[] spans = ((Spanned) ((TextView) this.mView).getText()).getSpans(i, i2, cls);
        if (spans.length > 0) {
            return spans[0];
        }
        return null;
    }

    public static class AccessibilityLinks {
        private final List<AccessibleLink> mLinks;

        public AccessibilityLinks(ClickableSpan[] clickableSpanArr, Spannable spannable) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < clickableSpanArr.length; i++) {
                ClickableSpan clickableSpan = clickableSpanArr[i];
                int spanStart = spannable.getSpanStart(clickableSpan);
                int spanEnd = spannable.getSpanEnd(clickableSpan);
                if (spanStart != spanEnd && spanStart >= 0 && spanEnd >= 0 && spanStart <= spannable.length() && spanEnd <= spannable.length()) {
                    AccessibleLink accessibleLink = new AccessibleLink();
                    accessibleLink.description = spannable.subSequence(spanStart, spanEnd).toString();
                    accessibleLink.start = spanStart;
                    accessibleLink.end = spanEnd;
                    accessibleLink.id = (clickableSpanArr.length - 1) - i;
                    arrayList.add(accessibleLink);
                }
            }
            this.mLinks = arrayList;
        }

        public AccessibleLink getLinkById(int i) {
            for (AccessibleLink next : this.mLinks) {
                if (next.id == i) {
                    return next;
                }
            }
            return null;
        }

        public AccessibleLink getLinkBySpanPos(int i, int i2) {
            for (AccessibleLink next : this.mLinks) {
                if (next.start == i && next.end == i2) {
                    return next;
                }
            }
            return null;
        }

        public int size() {
            return this.mLinks.size();
        }

        private static class AccessibleLink {
            public String description;
            public int end;
            public int id;
            public int start;

            private AccessibleLink() {
            }
        }
    }

    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View view) {
        if (this.mAccessibilityLinks != null) {
            return super.getAccessibilityNodeProvider(view);
        }
        return null;
    }

    public static boolean hasNonActionableSpeakingDescendants(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, View view) {
        if (!(accessibilityNodeInfoCompat == null || view == null || !(view instanceof ViewGroup))) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt != null) {
                    boolean obtain = AccessibilityNodeInfoCompat.obtain();
                    try {
                        ViewCompat.onInitializeAccessibilityNodeInfo(childAt, obtain);
                        if (obtain.isVisibleToUser()) {
                            if (!isAccessibilityFocusable(obtain, childAt)) {
                                obtain = isSpeakingNode(obtain, childAt);
                                if (obtain) {
                                    obtain.recycle();
                                    return true;
                                }
                            }
                        }
                    } finally {
                        if (obtain != null) {
                            obtain.recycle();
                        }
                    }
                }
            }
        }
        return false;
    }

    public static boolean hasValidRangeInfo(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        AccessibilityNodeInfoCompat.RangeInfoCompat rangeInfo;
        if (accessibilityNodeInfoCompat == null || (rangeInfo = accessibilityNodeInfoCompat.getRangeInfo()) == null) {
            return false;
        }
        float max = rangeInfo.getMax();
        float min = rangeInfo.getMin();
        float current = rangeInfo.getCurrent();
        if (max - min <= 0.0f || current < min || current > max) {
            return false;
        }
        return true;
    }

    private static boolean hasStateDescription(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        return accessibilityNodeInfoCompat != null && (!TextUtils.isEmpty(accessibilityNodeInfoCompat.getStateDescription()) || accessibilityNodeInfoCompat.isCheckable() || hasValidRangeInfo(accessibilityNodeInfoCompat));
    }

    public static boolean isSpeakingNode(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, View view) {
        int importantForAccessibility;
        if (accessibilityNodeInfoCompat == null || view == null || (importantForAccessibility = ViewCompat.getImportantForAccessibility(view)) == 4) {
            return false;
        }
        if (importantForAccessibility == 2 && accessibilityNodeInfoCompat.getChildCount() <= 0) {
            return false;
        }
        if (hasText(accessibilityNodeInfoCompat) || hasStateDescription(accessibilityNodeInfoCompat) || accessibilityNodeInfoCompat.isCheckable() || hasNonActionableSpeakingDescendants(accessibilityNodeInfoCompat, view)) {
            return true;
        }
        return false;
    }

    public static boolean hasText(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        return accessibilityNodeInfoCompat != null && accessibilityNodeInfoCompat.getCollectionInfo() == null && (!TextUtils.isEmpty(accessibilityNodeInfoCompat.getText()) || !TextUtils.isEmpty(accessibilityNodeInfoCompat.getContentDescription()) || !TextUtils.isEmpty(accessibilityNodeInfoCompat.getHintText()));
    }

    public static boolean isAccessibilityFocusable(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, View view) {
        if (accessibilityNodeInfoCompat == null || view == null || !accessibilityNodeInfoCompat.isVisibleToUser()) {
            return false;
        }
        if (accessibilityNodeInfoCompat.isScreenReaderFocusable() || isActionableForAccessibility(accessibilityNodeInfoCompat)) {
            return true;
        }
        return false;
    }

    public static boolean isActionableForAccessibility(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (accessibilityNodeInfoCompat == null) {
            return false;
        }
        if (accessibilityNodeInfoCompat.isClickable() || accessibilityNodeInfoCompat.isLongClickable() || accessibilityNodeInfoCompat.isFocusable()) {
            return true;
        }
        List actionList = accessibilityNodeInfoCompat.getActionList();
        if (actionList.contains(16) || actionList.contains(32) || actionList.contains(1)) {
            return true;
        }
        return false;
    }

    public static AccessibilityNodeInfoCompat createNodeInfoFromView(View view) {
        if (view == null) {
            return null;
        }
        AccessibilityNodeInfoCompat obtain = AccessibilityNodeInfoCompat.obtain();
        try {
            ViewCompat.onInitializeAccessibilityNodeInfo(view, obtain);
            return obtain;
        } catch (NullPointerException unused) {
            if (obtain != null) {
                obtain.recycle();
            }
            return null;
        }
    }

    public static CharSequence getTalkbackDescription(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        AccessibilityNodeInfoCompat createNodeInfoFromView = accessibilityNodeInfoCompat == null ? createNodeInfoFromView(view) : AccessibilityNodeInfoCompat.obtain(accessibilityNodeInfoCompat);
        if (createNodeInfoFromView == null) {
            return null;
        }
        try {
            CharSequence contentDescription = createNodeInfoFromView.getContentDescription();
            CharSequence text = createNodeInfoFromView.getText();
            boolean isEmpty = TextUtils.isEmpty(text);
            boolean z = view instanceof EditText;
            StringBuilder sb = new StringBuilder();
            if (!TextUtils.isEmpty(contentDescription) && (!z || isEmpty)) {
                sb.append(contentDescription);
                return sb;
            } else if (!isEmpty) {
                sb.append(text);
                createNodeInfoFromView.recycle();
                return sb;
            } else if (view instanceof ViewGroup) {
                StringBuilder sb2 = new StringBuilder();
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = viewGroup.getChildAt(i);
                    AccessibilityNodeInfoCompat obtain = AccessibilityNodeInfoCompat.obtain();
                    ViewCompat.onInitializeAccessibilityNodeInfo(childAt, obtain);
                    if (isSpeakingNode(obtain, childAt) && !isAccessibilityFocusable(obtain, childAt)) {
                        CharSequence talkbackDescription = getTalkbackDescription(childAt, (AccessibilityNodeInfoCompat) null);
                        if (!TextUtils.isEmpty(talkbackDescription)) {
                            sb2.append(talkbackDescription + delimiter);
                        }
                    }
                    obtain.recycle();
                }
                String removeFinalDelimiter = removeFinalDelimiter(sb2);
                createNodeInfoFromView.recycle();
                return removeFinalDelimiter;
            } else {
                createNodeInfoFromView.recycle();
                return null;
            }
        } finally {
            createNodeInfoFromView.recycle();
        }
    }

    private static String removeFinalDelimiter(StringBuilder sb) {
        int length = sb.length();
        if (length > 0) {
            sb.delete(length - delimiterLength, length);
        }
        return sb.toString();
    }
}
