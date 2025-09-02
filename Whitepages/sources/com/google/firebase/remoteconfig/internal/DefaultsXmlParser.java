package com.google.firebase.remoteconfig.internal;

public class DefaultsXmlParser {
    private static final String XML_TAG_ENTRY = "entry";
    private static final String XML_TAG_KEY = "key";
    private static final String XML_TAG_VALUE = "value";

    /* JADX WARNING: Removed duplicated region for block: B:42:0x007c A[Catch:{ XmlPullParserException -> 0x0016, IOException -> 0x0013 }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0089 A[Catch:{ XmlPullParserException -> 0x0016, IOException -> 0x0013 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Map<java.lang.String, java.lang.String> getDefaultsFromXml(android.content.Context r8, int r9) {
        /*
            java.lang.String r0 = "FirebaseRemoteConfig"
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            android.content.res.Resources r8 = r8.getResources()     // Catch:{ XmlPullParserException -> 0x0016, IOException -> 0x0013 }
            if (r8 != 0) goto L_0x0019
            java.lang.String r8 = "Could not find the resources of the current context while trying to set defaults from an XML."
            android.util.Log.e(r0, r8)     // Catch:{ XmlPullParserException -> 0x0016, IOException -> 0x0013 }
            return r1
        L_0x0013:
            r8 = move-exception
            goto L_0x0092
        L_0x0016:
            r8 = move-exception
            goto L_0x0092
        L_0x0019:
            android.content.res.XmlResourceParser r8 = r8.getXml(r9)     // Catch:{ XmlPullParserException -> 0x0016, IOException -> 0x0013 }
            int r9 = r8.getEventType()     // Catch:{ XmlPullParserException -> 0x0016, IOException -> 0x0013 }
            r2 = 0
            r3 = r2
            r4 = r3
            r5 = r4
        L_0x0025:
            r6 = 1
            if (r9 == r6) goto L_0x0097
            r7 = 2
            if (r9 != r7) goto L_0x0031
            java.lang.String r3 = r8.getName()     // Catch:{ XmlPullParserException -> 0x0016, IOException -> 0x0013 }
            goto L_0x008d
        L_0x0031:
            r7 = 3
            if (r9 != r7) goto L_0x0051
            java.lang.String r9 = r8.getName()     // Catch:{ XmlPullParserException -> 0x0016, IOException -> 0x0013 }
            java.lang.String r3 = "entry"
            boolean r9 = r9.equals(r3)     // Catch:{ XmlPullParserException -> 0x0016, IOException -> 0x0013 }
            if (r9 == 0) goto L_0x004f
            if (r4 == 0) goto L_0x0048
            if (r5 == 0) goto L_0x0048
            r1.put(r4, r5)     // Catch:{ XmlPullParserException -> 0x0016, IOException -> 0x0013 }
            goto L_0x004d
        L_0x0048:
            java.lang.String r9 = "An entry in the defaults XML has an invalid key and/or value tag."
            android.util.Log.w(r0, r9)     // Catch:{ XmlPullParserException -> 0x0016, IOException -> 0x0013 }
        L_0x004d:
            r4 = r2
            r5 = r4
        L_0x004f:
            r3 = r2
            goto L_0x008d
        L_0x0051:
            r7 = 4
            if (r9 != r7) goto L_0x008d
            if (r3 == 0) goto L_0x008d
            int r9 = r3.hashCode()     // Catch:{ XmlPullParserException -> 0x0016, IOException -> 0x0013 }
            r7 = 106079(0x19e5f, float:1.48648E-40)
            if (r9 == r7) goto L_0x006f
            r7 = 111972721(0x6ac9171, float:6.4912916E-35)
            if (r9 == r7) goto L_0x0065
            goto L_0x0079
        L_0x0065:
            java.lang.String r9 = "value"
            boolean r9 = r3.equals(r9)     // Catch:{ XmlPullParserException -> 0x0016, IOException -> 0x0013 }
            if (r9 == 0) goto L_0x0079
            r9 = r6
            goto L_0x007a
        L_0x006f:
            java.lang.String r9 = "key"
            boolean r9 = r3.equals(r9)     // Catch:{ XmlPullParserException -> 0x0016, IOException -> 0x0013 }
            if (r9 == 0) goto L_0x0079
            r9 = 0
            goto L_0x007a
        L_0x0079:
            r9 = -1
        L_0x007a:
            if (r9 == 0) goto L_0x0089
            if (r9 == r6) goto L_0x0084
            java.lang.String r9 = "Encountered an unexpected tag while parsing the defaults XML."
            android.util.Log.w(r0, r9)     // Catch:{ XmlPullParserException -> 0x0016, IOException -> 0x0013 }
            goto L_0x008d
        L_0x0084:
            java.lang.String r5 = r8.getText()     // Catch:{ XmlPullParserException -> 0x0016, IOException -> 0x0013 }
            goto L_0x008d
        L_0x0089:
            java.lang.String r4 = r8.getText()     // Catch:{ XmlPullParserException -> 0x0016, IOException -> 0x0013 }
        L_0x008d:
            int r9 = r8.next()     // Catch:{ XmlPullParserException -> 0x0016, IOException -> 0x0013 }
            goto L_0x0025
        L_0x0092:
            java.lang.String r9 = "Encountered an error while parsing the defaults XML file."
            android.util.Log.e(r0, r9, r8)
        L_0x0097:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.remoteconfig.internal.DefaultsXmlParser.getDefaultsFromXml(android.content.Context, int):java.util.Map");
    }
}
