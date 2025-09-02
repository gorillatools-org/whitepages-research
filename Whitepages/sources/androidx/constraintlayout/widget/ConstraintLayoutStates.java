package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import android.util.SparseArray;
import android.util.Xml;
import com.google.firebase.sessions.settings.RemoteSettings;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;

public class ConstraintLayoutStates {
    private final ConstraintLayout mConstraintLayout;
    private SparseArray mConstraintSetMap = new SparseArray();
    int mCurrentConstraintNumber = -1;
    int mCurrentStateId = -1;
    ConstraintSet mDefaultConstraintSet;
    private SparseArray mStateList = new SparseArray();

    public void setOnConstraintsChanged(ConstraintsChangedListener constraintsChangedListener) {
    }

    ConstraintLayoutStates(Context context, ConstraintLayout constraintLayout, int i) {
        this.mConstraintLayout = constraintLayout;
        load(context, i);
    }

    public void updateConstraints(int i, float f, float f2) {
        ConstraintSet constraintSet;
        State state;
        int findMatch;
        ConstraintSet constraintSet2;
        int i2 = this.mCurrentStateId;
        if (i2 == i) {
            if (i == -1) {
                state = (State) this.mStateList.valueAt(0);
            } else {
                state = (State) this.mStateList.get(i2);
            }
            int i3 = this.mCurrentConstraintNumber;
            if ((i3 == -1 || !((Variant) state.mVariants.get(i3)).match(f, f2)) && this.mCurrentConstraintNumber != (findMatch = state.findMatch(f, f2))) {
                if (findMatch == -1) {
                    constraintSet2 = this.mDefaultConstraintSet;
                } else {
                    constraintSet2 = ((Variant) state.mVariants.get(findMatch)).mConstraintSet;
                }
                if (findMatch != -1) {
                    int i4 = ((Variant) state.mVariants.get(findMatch)).mConstraintID;
                }
                if (constraintSet2 != null) {
                    this.mCurrentConstraintNumber = findMatch;
                    constraintSet2.applyTo(this.mConstraintLayout);
                    return;
                }
                return;
            }
            return;
        }
        this.mCurrentStateId = i;
        State state2 = (State) this.mStateList.get(i);
        int findMatch2 = state2.findMatch(f, f2);
        if (findMatch2 == -1) {
            constraintSet = state2.mConstraintSet;
        } else {
            constraintSet = ((Variant) state2.mVariants.get(findMatch2)).mConstraintSet;
        }
        if (findMatch2 != -1) {
            int i5 = ((Variant) state2.mVariants.get(findMatch2)).mConstraintID;
        }
        if (constraintSet == null) {
            Log.v("ConstraintLayoutStates", "NO Constraint set found ! id=" + i + ", dim =" + f + ", " + f2);
            return;
        }
        this.mCurrentConstraintNumber = findMatch2;
        constraintSet.applyTo(this.mConstraintLayout);
    }

    static class State {
        int mConstraintID = -1;
        ConstraintSet mConstraintSet;
        int mId;
        ArrayList mVariants = new ArrayList();

        public State(Context context, XmlPullParser xmlPullParser) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R$styleable.State);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == R$styleable.State_android_id) {
                    this.mId = obtainStyledAttributes.getResourceId(index, this.mId);
                } else if (index == R$styleable.State_constraints) {
                    this.mConstraintID = obtainStyledAttributes.getResourceId(index, this.mConstraintID);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.mConstraintID);
                    context.getResources().getResourceName(this.mConstraintID);
                    if ("layout".equals(resourceTypeName)) {
                        ConstraintSet constraintSet = new ConstraintSet();
                        this.mConstraintSet = constraintSet;
                        constraintSet.clone(context, this.mConstraintID);
                    }
                }
            }
            obtainStyledAttributes.recycle();
        }

        /* access modifiers changed from: package-private */
        public void add(Variant variant) {
            this.mVariants.add(variant);
        }

        public int findMatch(float f, float f2) {
            for (int i = 0; i < this.mVariants.size(); i++) {
                if (((Variant) this.mVariants.get(i)).match(f, f2)) {
                    return i;
                }
            }
            return -1;
        }
    }

    static class Variant {
        int mConstraintID = -1;
        ConstraintSet mConstraintSet;
        float mMaxHeight = Float.NaN;
        float mMaxWidth = Float.NaN;
        float mMinHeight = Float.NaN;
        float mMinWidth = Float.NaN;

        public Variant(Context context, XmlPullParser xmlPullParser) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R$styleable.Variant);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == R$styleable.Variant_constraints) {
                    this.mConstraintID = obtainStyledAttributes.getResourceId(index, this.mConstraintID);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.mConstraintID);
                    context.getResources().getResourceName(this.mConstraintID);
                    if ("layout".equals(resourceTypeName)) {
                        ConstraintSet constraintSet = new ConstraintSet();
                        this.mConstraintSet = constraintSet;
                        constraintSet.clone(context, this.mConstraintID);
                    }
                } else if (index == R$styleable.Variant_region_heightLessThan) {
                    this.mMaxHeight = obtainStyledAttributes.getDimension(index, this.mMaxHeight);
                } else if (index == R$styleable.Variant_region_heightMoreThan) {
                    this.mMinHeight = obtainStyledAttributes.getDimension(index, this.mMinHeight);
                } else if (index == R$styleable.Variant_region_widthLessThan) {
                    this.mMaxWidth = obtainStyledAttributes.getDimension(index, this.mMaxWidth);
                } else if (index == R$styleable.Variant_region_widthMoreThan) {
                    this.mMinWidth = obtainStyledAttributes.getDimension(index, this.mMinWidth);
                } else {
                    Log.v("ConstraintLayoutStates", "Unknown tag");
                }
            }
            obtainStyledAttributes.recycle();
        }

        /* access modifiers changed from: package-private */
        public boolean match(float f, float f2) {
            if (!Float.isNaN(this.mMinWidth) && f < this.mMinWidth) {
                return false;
            }
            if (!Float.isNaN(this.mMinHeight) && f2 < this.mMinHeight) {
                return false;
            }
            if (!Float.isNaN(this.mMaxWidth) && f > this.mMaxWidth) {
                return false;
            }
            if (Float.isNaN(this.mMaxHeight) || f2 <= this.mMaxHeight) {
                return true;
            }
            return false;
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void load(android.content.Context r8, int r9) {
        /*
            r7 = this;
            android.content.res.Resources r0 = r8.getResources()
            android.content.res.XmlResourceParser r9 = r0.getXml(r9)
            int r0 = r9.getEventType()     // Catch:{ XmlPullParserException -> 0x0031, IOException -> 0x002f }
            r1 = 0
        L_0x000d:
            r2 = 1
            if (r0 == r2) goto L_0x008d
            if (r0 == 0) goto L_0x007e
            r3 = 2
            if (r0 == r3) goto L_0x0017
            goto L_0x0081
        L_0x0017:
            java.lang.String r0 = r9.getName()     // Catch:{ XmlPullParserException -> 0x0031, IOException -> 0x002f }
            int r4 = r0.hashCode()     // Catch:{ XmlPullParserException -> 0x0031, IOException -> 0x002f }
            r5 = 4
            r6 = 3
            switch(r4) {
                case -1349929691: goto L_0x0050;
                case 80204913: goto L_0x0046;
                case 1382829617: goto L_0x003d;
                case 1657696882: goto L_0x0033;
                case 1901439077: goto L_0x0025;
                default: goto L_0x0024;
            }     // Catch:{ XmlPullParserException -> 0x0031, IOException -> 0x002f }
        L_0x0024:
            goto L_0x005a
        L_0x0025:
            java.lang.String r2 = "Variant"
            boolean r0 = r0.equals(r2)     // Catch:{ XmlPullParserException -> 0x0031, IOException -> 0x002f }
            if (r0 == 0) goto L_0x005a
            r2 = r6
            goto L_0x005b
        L_0x002f:
            r8 = move-exception
            goto L_0x0086
        L_0x0031:
            r8 = move-exception
            goto L_0x008a
        L_0x0033:
            java.lang.String r2 = "layoutDescription"
            boolean r0 = r0.equals(r2)     // Catch:{ XmlPullParserException -> 0x0031, IOException -> 0x002f }
            if (r0 == 0) goto L_0x005a
            r2 = 0
            goto L_0x005b
        L_0x003d:
            java.lang.String r4 = "StateSet"
            boolean r0 = r0.equals(r4)     // Catch:{ XmlPullParserException -> 0x0031, IOException -> 0x002f }
            if (r0 == 0) goto L_0x005a
            goto L_0x005b
        L_0x0046:
            java.lang.String r2 = "State"
            boolean r0 = r0.equals(r2)     // Catch:{ XmlPullParserException -> 0x0031, IOException -> 0x002f }
            if (r0 == 0) goto L_0x005a
            r2 = r3
            goto L_0x005b
        L_0x0050:
            java.lang.String r2 = "ConstraintSet"
            boolean r0 = r0.equals(r2)     // Catch:{ XmlPullParserException -> 0x0031, IOException -> 0x002f }
            if (r0 == 0) goto L_0x005a
            r2 = r5
            goto L_0x005b
        L_0x005a:
            r2 = -1
        L_0x005b:
            if (r2 == r3) goto L_0x0071
            if (r2 == r6) goto L_0x0066
            if (r2 == r5) goto L_0x0062
            goto L_0x0081
        L_0x0062:
            r7.parseConstraintSet(r8, r9)     // Catch:{ XmlPullParserException -> 0x0031, IOException -> 0x002f }
            goto L_0x0081
        L_0x0066:
            androidx.constraintlayout.widget.ConstraintLayoutStates$Variant r0 = new androidx.constraintlayout.widget.ConstraintLayoutStates$Variant     // Catch:{ XmlPullParserException -> 0x0031, IOException -> 0x002f }
            r0.<init>(r8, r9)     // Catch:{ XmlPullParserException -> 0x0031, IOException -> 0x002f }
            if (r1 == 0) goto L_0x0081
            r1.add(r0)     // Catch:{ XmlPullParserException -> 0x0031, IOException -> 0x002f }
            goto L_0x0081
        L_0x0071:
            androidx.constraintlayout.widget.ConstraintLayoutStates$State r1 = new androidx.constraintlayout.widget.ConstraintLayoutStates$State     // Catch:{ XmlPullParserException -> 0x0031, IOException -> 0x002f }
            r1.<init>(r8, r9)     // Catch:{ XmlPullParserException -> 0x0031, IOException -> 0x002f }
            android.util.SparseArray r0 = r7.mStateList     // Catch:{ XmlPullParserException -> 0x0031, IOException -> 0x002f }
            int r2 = r1.mId     // Catch:{ XmlPullParserException -> 0x0031, IOException -> 0x002f }
            r0.put(r2, r1)     // Catch:{ XmlPullParserException -> 0x0031, IOException -> 0x002f }
            goto L_0x0081
        L_0x007e:
            r9.getName()     // Catch:{ XmlPullParserException -> 0x0031, IOException -> 0x002f }
        L_0x0081:
            int r0 = r9.next()     // Catch:{ XmlPullParserException -> 0x0031, IOException -> 0x002f }
            goto L_0x000d
        L_0x0086:
            r8.printStackTrace()
            goto L_0x008d
        L_0x008a:
            r8.printStackTrace()
        L_0x008d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayoutStates.load(android.content.Context, int):void");
    }

    private void parseConstraintSet(Context context, XmlPullParser xmlPullParser) {
        int i;
        ConstraintSet constraintSet = new ConstraintSet();
        int attributeCount = xmlPullParser.getAttributeCount();
        int i2 = 0;
        while (i2 < attributeCount) {
            String attributeName = xmlPullParser.getAttributeName(i2);
            String attributeValue = xmlPullParser.getAttributeValue(i2);
            if (attributeName == null || attributeValue == null || !"id".equals(attributeName)) {
                i2++;
            } else {
                if (attributeValue.contains(RemoteSettings.FORWARD_SLASH_STRING)) {
                    i = context.getResources().getIdentifier(attributeValue.substring(attributeValue.indexOf(47) + 1), "id", context.getPackageName());
                } else {
                    i = -1;
                }
                if (i == -1) {
                    if (attributeValue.length() > 1) {
                        i = Integer.parseInt(attributeValue.substring(1));
                    } else {
                        Log.e("ConstraintLayoutStates", "error in parsing id");
                    }
                }
                constraintSet.load(context, xmlPullParser);
                this.mConstraintSetMap.put(i, constraintSet);
                return;
            }
        }
    }
}
