package com.facebook.hermes.intl;

import java.text.AttributedCharacterIterator;

public interface IPlatformDateTimeFormatter {
    void configure(ILocaleObject<?> iLocaleObject, String str, String str2, FormatMatcher formatMatcher, WeekDay weekDay, Era era, Year year, Month month, Day day, Hour hour, Minute minute, Second second, TimeZoneName timeZoneName, HourCycle hourCycle, Object obj, DateStyle dateStyle, TimeStyle timeStyle, Object obj2) throws JSRangeErrorException;

    String fieldToString(AttributedCharacterIterator.Attribute attribute, String str);

    String format(double d) throws JSRangeErrorException;

    AttributedCharacterIterator formatToParts(double d) throws JSRangeErrorException;

    String[] getAvailableLocales();

    String getDefaultCalendarName(ILocaleObject<?> iLocaleObject) throws JSRangeErrorException;

    HourCycle getDefaultHourCycle(ILocaleObject<?> iLocaleObject) throws JSRangeErrorException;

    String getDefaultNumberingSystem(ILocaleObject<?> iLocaleObject) throws JSRangeErrorException;

    String getDefaultTimeZone(ILocaleObject<?> iLocaleObject) throws JSRangeErrorException;

    public enum FormatMatcher {
        BESTFIT,
        BASIC;

        public String toString() {
            int i = AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$FormatMatcher[ordinal()];
            if (i == 1) {
                return Constants.LOCALEMATCHER_BESTFIT;
            }
            if (i == 2) {
                return "basic";
            }
            throw new IllegalArgumentException();
        }
    }

    public enum HourCycle {
        H11,
        H12,
        H23,
        H24,
        UNDEFINED;

        public String toString() {
            int i = AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$HourCycle[ordinal()];
            if (i == 1) {
                return "h11";
            }
            if (i == 2) {
                return "h12";
            }
            if (i == 3) {
                return "h23";
            }
            if (i == 4) {
                return "h24";
            }
            if (i == 5) {
                return "";
            }
            throw new IllegalArgumentException();
        }
    }

    public enum WeekDay {
        LONG,
        SHORT,
        NARROW,
        UNDEFINED;

        public String toString() {
            int i = AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$WeekDay[ordinal()];
            if (i == 1) {
                return "long";
            }
            if (i == 2) {
                return "short";
            }
            if (i == 3) {
                return "narrow";
            }
            if (i == 4) {
                return "";
            }
            throw new IllegalArgumentException();
        }

        public String getSkeleonSymbol() {
            int i = AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$WeekDay[ordinal()];
            if (i == 1) {
                return "EEEE";
            }
            if (i == 2) {
                return "EEE";
            }
            if (i == 3) {
                return "EEEEE";
            }
            if (i == 4) {
                return "";
            }
            throw new IllegalArgumentException();
        }
    }

    public enum Era {
        LONG,
        SHORT,
        NARROW,
        UNDEFINED;

        public String toString() {
            int i = AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Era[ordinal()];
            if (i == 1) {
                return "long";
            }
            if (i == 2) {
                return "short";
            }
            if (i == 3) {
                return "narrow";
            }
            if (i == 4) {
                return "";
            }
            throw new IllegalArgumentException();
        }

        public String getSkeleonSymbol() {
            int i = AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Era[ordinal()];
            if (i == 1) {
                return "GGGG";
            }
            if (i == 2) {
                return "GGG";
            }
            if (i == 3) {
                return "G5";
            }
            if (i == 4) {
                return "";
            }
            throw new IllegalArgumentException();
        }
    }

    public enum Year {
        NUMERIC,
        DIGIT2,
        UNDEFINED;

        public String toString() {
            int i = AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Year[ordinal()];
            if (i == 1) {
                return Constants.COLLATION_OPTION_NUMERIC;
            }
            if (i == 2) {
                return "2-digit";
            }
            if (i == 3) {
                return "";
            }
            throw new IllegalArgumentException();
        }

        public String getSkeleonSymbol() {
            int i = AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Year[ordinal()];
            if (i == 1) {
                return "yyyy";
            }
            if (i == 2) {
                return "yy";
            }
            if (i == 3) {
                return "";
            }
            throw new IllegalArgumentException();
        }
    }

    public enum Month {
        NUMERIC,
        DIGIT2,
        LONG,
        SHORT,
        NARROW,
        UNDEFINED;

        public String toString() {
            switch (AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Month[ordinal()]) {
                case 1:
                    return Constants.COLLATION_OPTION_NUMERIC;
                case 2:
                    return "2-digit";
                case 3:
                    return "long";
                case 4:
                    return "short";
                case 5:
                    return "narrow";
                case 6:
                    return "";
                default:
                    throw new IllegalArgumentException();
            }
        }

        public String getSkeleonSymbol() {
            switch (AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Month[ordinal()]) {
                case 1:
                    return "M";
                case 2:
                    return "MM";
                case 3:
                    return "MMMM";
                case 4:
                    return "MMM";
                case 5:
                    return "MMMMM";
                case 6:
                    return "";
                default:
                    throw new IllegalArgumentException();
            }
        }
    }

    public enum Day {
        NUMERIC,
        DIGIT2,
        UNDEFINED;

        public String toString() {
            int i = AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Day[ordinal()];
            if (i == 1) {
                return Constants.COLLATION_OPTION_NUMERIC;
            }
            if (i == 2) {
                return "2-digit";
            }
            if (i == 3) {
                return "";
            }
            throw new IllegalArgumentException();
        }

        public String getSkeleonSymbol() {
            int i = AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Day[ordinal()];
            if (i == 1) {
                return "d";
            }
            if (i == 2) {
                return "dd";
            }
            if (i == 3) {
                return "";
            }
            throw new IllegalArgumentException();
        }
    }

    public enum Hour {
        NUMERIC,
        DIGIT2,
        UNDEFINED;

        public String toString() {
            int i = AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Hour[ordinal()];
            if (i == 1) {
                return Constants.COLLATION_OPTION_NUMERIC;
            }
            if (i == 2) {
                return "2-digit";
            }
            if (i == 3) {
                return "";
            }
            throw new IllegalArgumentException();
        }

        public String getSkeleonSymbol12() {
            int i = AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Hour[ordinal()];
            if (i == 1) {
                return "h";
            }
            if (i == 2) {
                return "hh";
            }
            if (i == 3) {
                return "";
            }
            throw new IllegalArgumentException();
        }

        public String getSkeleonSymbol24() {
            int i = AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Hour[ordinal()];
            if (i == 1) {
                return "k";
            }
            if (i == 2) {
                return "kk";
            }
            if (i == 3) {
                return "";
            }
            throw new IllegalArgumentException();
        }
    }

    public enum Minute {
        NUMERIC,
        DIGIT2,
        UNDEFINED;

        public String toString() {
            int i = AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Minute[ordinal()];
            if (i == 1) {
                return Constants.COLLATION_OPTION_NUMERIC;
            }
            if (i == 2) {
                return "2-digit";
            }
            if (i == 3) {
                return "";
            }
            throw new IllegalArgumentException();
        }

        public String getSkeleonSymbol() {
            int i = AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Minute[ordinal()];
            if (i == 1) {
                return "m";
            }
            if (i == 2) {
                return "mm";
            }
            if (i == 3) {
                return "";
            }
            throw new IllegalArgumentException();
        }
    }

    public enum Second {
        NUMERIC,
        DIGIT2,
        UNDEFINED;

        public String toString() {
            int i = AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Second[ordinal()];
            if (i == 1) {
                return Constants.COLLATION_OPTION_NUMERIC;
            }
            if (i == 2) {
                return "2-digit";
            }
            if (i == 3) {
                return "";
            }
            throw new IllegalArgumentException();
        }

        public String getSkeleonSymbol() {
            int i = AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Second[ordinal()];
            if (i == 1) {
                return "s";
            }
            if (i == 2) {
                return "ss";
            }
            if (i == 3) {
                return "";
            }
            throw new IllegalArgumentException();
        }
    }

    public enum TimeZoneName {
        LONG,
        LONGOFFSET,
        LONGGENERIC,
        SHORT,
        SHORTOFFSET,
        SHORTGENERIC,
        UNDEFINED;

        public String toString() {
            switch (AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$TimeZoneName[ordinal()]) {
                case 1:
                    return "long";
                case 2:
                    return "longOffset";
                case 3:
                    return "longGeneric";
                case 4:
                    return "short";
                case 5:
                    return "shortOffset";
                case 6:
                    return "shortGeneric";
                case 7:
                    return "";
                default:
                    throw new IllegalArgumentException();
            }
        }

        public String getSkeleonSymbol() {
            switch (AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$TimeZoneName[ordinal()]) {
                case 1:
                    return "zzzz";
                case 2:
                    return "OOOO";
                case 3:
                    return "vvvv";
                case 4:
                    return "z";
                case 5:
                    return "O";
                case 6:
                    return "v";
                case 7:
                    return "";
                default:
                    throw new IllegalArgumentException();
            }
        }
    }

    public enum DateStyle {
        FULL,
        LONG,
        MEDIUM,
        SHORT,
        UNDEFINED;

        public String toString() {
            int i = AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$DateStyle[ordinal()];
            if (i == 1) {
                return "full";
            }
            if (i == 2) {
                return "long";
            }
            if (i == 3) {
                return "medium";
            }
            if (i == 4) {
                return "short";
            }
            if (i == 5) {
                return "";
            }
            throw new IllegalArgumentException();
        }
    }

    /* renamed from: com.facebook.hermes.intl.IPlatformDateTimeFormatter$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$DateStyle;
        static final /* synthetic */ int[] $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Day;
        static final /* synthetic */ int[] $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Era;
        static final /* synthetic */ int[] $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$FormatMatcher;
        static final /* synthetic */ int[] $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Hour;
        static final /* synthetic */ int[] $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$HourCycle;
        static final /* synthetic */ int[] $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Minute;
        static final /* synthetic */ int[] $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Month;
        static final /* synthetic */ int[] $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Second;
        static final /* synthetic */ int[] $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$TimeStyle;
        static final /* synthetic */ int[] $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$TimeZoneName;
        static final /* synthetic */ int[] $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$WeekDay;
        static final /* synthetic */ int[] $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Year;

        /* JADX WARNING: Can't wrap try/catch for region: R(107:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|21|22|23|24|25|26|27|28|(2:29|30)|31|33|34|35|36|37|38|39|40|41|42|43|45|46|(2:47|48)|49|51|52|53|54|(2:55|56)|57|59|60|61|62|(2:63|64)|65|67|68|69|70|(2:71|72)|73|75|76|77|78|(2:79|80)|81|83|84|85|86|87|88|89|90|91|92|(2:93|94)|95|97|98|99|100|(2:101|102)|103|105|106|107|108|109|110|(2:111|112)|113|115|116|117|118|119|120|(2:121|122)|123|125|126|127|128|129|130|131|132|(2:133|134)|135|137|138|(3:139|140|142)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(109:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|21|22|23|24|25|26|27|28|(2:29|30)|31|33|34|35|36|37|38|39|40|41|42|43|45|46|47|48|49|51|52|53|54|(2:55|56)|57|59|60|61|62|(2:63|64)|65|67|68|69|70|(2:71|72)|73|75|76|77|78|(2:79|80)|81|83|84|85|86|87|88|89|90|91|92|(2:93|94)|95|97|98|99|100|101|102|103|105|106|107|108|109|110|(2:111|112)|113|115|116|117|118|119|120|(2:121|122)|123|125|126|127|128|129|130|131|132|(2:133|134)|135|137|138|(3:139|140|142)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(110:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|17|18|19|21|22|23|24|25|26|27|28|(2:29|30)|31|33|34|35|36|37|38|39|40|41|42|43|45|46|47|48|49|51|52|53|54|(2:55|56)|57|59|60|61|62|(2:63|64)|65|67|68|69|70|(2:71|72)|73|75|76|77|78|(2:79|80)|81|83|84|85|86|87|88|89|90|91|92|(2:93|94)|95|97|98|99|100|101|102|103|105|106|107|108|109|110|(2:111|112)|113|115|116|117|118|119|120|(2:121|122)|123|125|126|127|128|129|130|131|132|(2:133|134)|135|137|138|(3:139|140|142)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(111:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|17|18|19|21|22|23|24|25|26|27|28|29|30|31|33|34|35|36|37|38|39|40|41|42|43|45|46|47|48|49|51|52|53|54|(2:55|56)|57|59|60|61|62|(2:63|64)|65|67|68|69|70|(2:71|72)|73|75|76|77|78|(2:79|80)|81|83|84|85|86|87|88|89|90|91|92|(2:93|94)|95|97|98|99|100|101|102|103|105|106|107|108|109|110|(2:111|112)|113|115|116|117|118|119|120|(2:121|122)|123|125|126|127|128|129|130|131|132|(2:133|134)|135|137|138|(3:139|140|142)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(116:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|15|17|18|19|21|22|23|24|25|26|27|28|29|30|31|33|34|35|36|37|38|39|40|41|42|43|45|46|47|48|49|51|52|53|54|(2:55|56)|57|59|60|61|62|(2:63|64)|65|67|68|69|70|71|72|73|75|76|77|78|(2:79|80)|81|83|84|85|86|87|88|89|90|91|92|(2:93|94)|95|97|98|99|100|101|102|103|105|106|107|108|109|110|111|112|113|115|116|117|118|119|120|(2:121|122)|123|125|126|127|128|129|130|131|132|(2:133|134)|135|137|138|139|140|142) */
        /* JADX WARNING: Can't wrap try/catch for region: R(118:0|(2:1|2)|3|(2:5|6)|7|9|10|11|13|14|15|17|18|19|21|22|23|24|25|26|27|28|29|30|31|33|34|35|36|37|38|39|40|41|42|43|45|46|47|48|49|51|52|53|54|55|56|57|59|60|61|62|(2:63|64)|65|67|68|69|70|71|72|73|75|76|77|78|(2:79|80)|81|83|84|85|86|87|88|89|90|91|92|(2:93|94)|95|97|98|99|100|101|102|103|105|106|107|108|109|110|111|112|113|115|116|117|118|119|120|(2:121|122)|123|125|126|127|128|129|130|131|132|(2:133|134)|135|137|138|139|140|142) */
        /* JADX WARNING: Can't wrap try/catch for region: R(121:0|(2:1|2)|3|5|6|7|9|10|11|13|14|15|17|18|19|21|22|23|24|25|26|27|28|29|30|31|33|34|35|36|37|38|39|40|41|42|43|45|46|47|48|49|51|52|53|54|55|56|57|59|60|61|62|(2:63|64)|65|67|68|69|70|71|72|73|75|76|77|78|(2:79|80)|81|83|84|85|86|87|88|89|90|91|92|93|94|95|97|98|99|100|101|102|103|105|106|107|108|109|110|111|112|113|115|116|117|118|119|120|121|122|123|125|126|127|128|129|130|131|132|(2:133|134)|135|137|138|139|140|142) */
        /* JADX WARNING: Can't wrap try/catch for region: R(123:0|(2:1|2)|3|5|6|7|9|10|11|13|14|15|17|18|19|21|22|23|24|25|26|27|28|29|30|31|33|34|35|36|37|38|39|40|41|42|43|45|46|47|48|49|51|52|53|54|55|56|57|59|60|61|62|(2:63|64)|65|67|68|69|70|71|72|73|75|76|77|78|79|80|81|83|84|85|86|87|88|89|90|91|92|93|94|95|97|98|99|100|101|102|103|105|106|107|108|109|110|111|112|113|115|116|117|118|119|120|121|122|123|125|126|127|128|129|130|131|132|133|134|135|137|138|139|140|142) */
        /* JADX WARNING: Can't wrap try/catch for region: R(124:0|1|2|3|5|6|7|9|10|11|13|14|15|17|18|19|21|22|23|24|25|26|27|28|29|30|31|33|34|35|36|37|38|39|40|41|42|43|45|46|47|48|49|51|52|53|54|55|56|57|59|60|61|62|(2:63|64)|65|67|68|69|70|71|72|73|75|76|77|78|79|80|81|83|84|85|86|87|88|89|90|91|92|93|94|95|97|98|99|100|101|102|103|105|106|107|108|109|110|111|112|113|115|116|117|118|119|120|121|122|123|125|126|127|128|129|130|131|132|133|134|135|137|138|139|140|142) */
        /* JADX WARNING: Can't wrap try/catch for region: R(125:0|1|2|3|5|6|7|9|10|11|13|14|15|17|18|19|21|22|23|24|25|26|27|28|29|30|31|33|34|35|36|37|38|39|40|41|42|43|45|46|47|48|49|51|52|53|54|55|56|57|59|60|61|62|63|64|65|67|68|69|70|71|72|73|75|76|77|78|79|80|81|83|84|85|86|87|88|89|90|91|92|93|94|95|97|98|99|100|101|102|103|105|106|107|108|109|110|111|112|113|115|116|117|118|119|120|121|122|123|125|126|127|128|129|130|131|132|133|134|135|137|138|139|140|142) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:101:0x01b8 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:107:0x01d3 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:109:0x01dd */
        /* JADX WARNING: Missing exception handler attribute for start block: B:111:0x01e7 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:117:0x0202 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:119:0x020c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:121:0x0216 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:127:0x0231 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:129:0x023b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:131:0x0245 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:133:0x024f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:139:0x026a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x004f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0059 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0063 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x006d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x0088 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x0092 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x009c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x00a6 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:47:0x00bb */
        /* JADX WARNING: Missing exception handler attribute for start block: B:53:0x00d7 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:55:0x00e1 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:61:0x00fc */
        /* JADX WARNING: Missing exception handler attribute for start block: B:63:0x0106 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:69:0x0121 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:71:0x012b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:77:0x0146 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:79:0x0150 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:85:0x016b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:87:0x0175 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:89:0x017f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:91:0x0189 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:93:0x0193 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:99:0x01ae */
        static {
            /*
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$TimeStyle[] r0 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.TimeStyle.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$TimeStyle = r0
                r1 = 1
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$TimeStyle r2 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.TimeStyle.FULL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$TimeStyle     // Catch:{ NoSuchFieldError -> 0x001d }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$TimeStyle r3 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.TimeStyle.LONG     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$TimeStyle     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$TimeStyle r4 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.TimeStyle.MEDIUM     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                r3 = 4
                int[] r4 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$TimeStyle     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$TimeStyle r5 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.TimeStyle.SHORT     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                r4 = 5
                int[] r5 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$TimeStyle     // Catch:{ NoSuchFieldError -> 0x003e }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$TimeStyle r6 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.TimeStyle.UNDEFINED     // Catch:{ NoSuchFieldError -> 0x003e }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r5[r6] = r4     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$DateStyle[] r5 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.DateStyle.values()
                int r5 = r5.length
                int[] r5 = new int[r5]
                $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$DateStyle = r5
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$DateStyle r6 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.DateStyle.FULL     // Catch:{ NoSuchFieldError -> 0x004f }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x004f }
                r5[r6] = r1     // Catch:{ NoSuchFieldError -> 0x004f }
            L_0x004f:
                int[] r5 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$DateStyle     // Catch:{ NoSuchFieldError -> 0x0059 }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$DateStyle r6 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.DateStyle.LONG     // Catch:{ NoSuchFieldError -> 0x0059 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x0059 }
                r5[r6] = r0     // Catch:{ NoSuchFieldError -> 0x0059 }
            L_0x0059:
                int[] r5 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$DateStyle     // Catch:{ NoSuchFieldError -> 0x0063 }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$DateStyle r6 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.DateStyle.MEDIUM     // Catch:{ NoSuchFieldError -> 0x0063 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x0063 }
                r5[r6] = r2     // Catch:{ NoSuchFieldError -> 0x0063 }
            L_0x0063:
                int[] r5 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$DateStyle     // Catch:{ NoSuchFieldError -> 0x006d }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$DateStyle r6 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.DateStyle.SHORT     // Catch:{ NoSuchFieldError -> 0x006d }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x006d }
                r5[r6] = r3     // Catch:{ NoSuchFieldError -> 0x006d }
            L_0x006d:
                int[] r5 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$DateStyle     // Catch:{ NoSuchFieldError -> 0x0077 }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$DateStyle r6 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.DateStyle.UNDEFINED     // Catch:{ NoSuchFieldError -> 0x0077 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x0077 }
                r5[r6] = r4     // Catch:{ NoSuchFieldError -> 0x0077 }
            L_0x0077:
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$TimeZoneName[] r5 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.TimeZoneName.values()
                int r5 = r5.length
                int[] r5 = new int[r5]
                $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$TimeZoneName = r5
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$TimeZoneName r6 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.TimeZoneName.LONG     // Catch:{ NoSuchFieldError -> 0x0088 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x0088 }
                r5[r6] = r1     // Catch:{ NoSuchFieldError -> 0x0088 }
            L_0x0088:
                int[] r5 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$TimeZoneName     // Catch:{ NoSuchFieldError -> 0x0092 }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$TimeZoneName r6 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.TimeZoneName.LONGOFFSET     // Catch:{ NoSuchFieldError -> 0x0092 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x0092 }
                r5[r6] = r0     // Catch:{ NoSuchFieldError -> 0x0092 }
            L_0x0092:
                int[] r5 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$TimeZoneName     // Catch:{ NoSuchFieldError -> 0x009c }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$TimeZoneName r6 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.TimeZoneName.LONGGENERIC     // Catch:{ NoSuchFieldError -> 0x009c }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x009c }
                r5[r6] = r2     // Catch:{ NoSuchFieldError -> 0x009c }
            L_0x009c:
                int[] r5 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$TimeZoneName     // Catch:{ NoSuchFieldError -> 0x00a6 }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$TimeZoneName r6 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.TimeZoneName.SHORT     // Catch:{ NoSuchFieldError -> 0x00a6 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a6 }
                r5[r6] = r3     // Catch:{ NoSuchFieldError -> 0x00a6 }
            L_0x00a6:
                int[] r5 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$TimeZoneName     // Catch:{ NoSuchFieldError -> 0x00b0 }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$TimeZoneName r6 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.TimeZoneName.SHORTOFFSET     // Catch:{ NoSuchFieldError -> 0x00b0 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b0 }
                r5[r6] = r4     // Catch:{ NoSuchFieldError -> 0x00b0 }
            L_0x00b0:
                r5 = 6
                int[] r6 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$TimeZoneName     // Catch:{ NoSuchFieldError -> 0x00bb }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$TimeZoneName r7 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.TimeZoneName.SHORTGENERIC     // Catch:{ NoSuchFieldError -> 0x00bb }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x00bb }
                r6[r7] = r5     // Catch:{ NoSuchFieldError -> 0x00bb }
            L_0x00bb:
                int[] r6 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$TimeZoneName     // Catch:{ NoSuchFieldError -> 0x00c6 }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$TimeZoneName r7 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.TimeZoneName.UNDEFINED     // Catch:{ NoSuchFieldError -> 0x00c6 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x00c6 }
                r8 = 7
                r6[r7] = r8     // Catch:{ NoSuchFieldError -> 0x00c6 }
            L_0x00c6:
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$Second[] r6 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.Second.values()
                int r6 = r6.length
                int[] r6 = new int[r6]
                $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Second = r6
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$Second r7 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.Second.NUMERIC     // Catch:{ NoSuchFieldError -> 0x00d7 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x00d7 }
                r6[r7] = r1     // Catch:{ NoSuchFieldError -> 0x00d7 }
            L_0x00d7:
                int[] r6 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Second     // Catch:{ NoSuchFieldError -> 0x00e1 }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$Second r7 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.Second.DIGIT2     // Catch:{ NoSuchFieldError -> 0x00e1 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x00e1 }
                r6[r7] = r0     // Catch:{ NoSuchFieldError -> 0x00e1 }
            L_0x00e1:
                int[] r6 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Second     // Catch:{ NoSuchFieldError -> 0x00eb }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$Second r7 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.Second.UNDEFINED     // Catch:{ NoSuchFieldError -> 0x00eb }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x00eb }
                r6[r7] = r2     // Catch:{ NoSuchFieldError -> 0x00eb }
            L_0x00eb:
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$Minute[] r6 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.Minute.values()
                int r6 = r6.length
                int[] r6 = new int[r6]
                $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Minute = r6
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$Minute r7 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.Minute.NUMERIC     // Catch:{ NoSuchFieldError -> 0x00fc }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x00fc }
                r6[r7] = r1     // Catch:{ NoSuchFieldError -> 0x00fc }
            L_0x00fc:
                int[] r6 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Minute     // Catch:{ NoSuchFieldError -> 0x0106 }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$Minute r7 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.Minute.DIGIT2     // Catch:{ NoSuchFieldError -> 0x0106 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x0106 }
                r6[r7] = r0     // Catch:{ NoSuchFieldError -> 0x0106 }
            L_0x0106:
                int[] r6 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Minute     // Catch:{ NoSuchFieldError -> 0x0110 }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$Minute r7 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.Minute.UNDEFINED     // Catch:{ NoSuchFieldError -> 0x0110 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x0110 }
                r6[r7] = r2     // Catch:{ NoSuchFieldError -> 0x0110 }
            L_0x0110:
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$Hour[] r6 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.Hour.values()
                int r6 = r6.length
                int[] r6 = new int[r6]
                $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Hour = r6
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$Hour r7 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.Hour.NUMERIC     // Catch:{ NoSuchFieldError -> 0x0121 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x0121 }
                r6[r7] = r1     // Catch:{ NoSuchFieldError -> 0x0121 }
            L_0x0121:
                int[] r6 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Hour     // Catch:{ NoSuchFieldError -> 0x012b }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$Hour r7 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.Hour.DIGIT2     // Catch:{ NoSuchFieldError -> 0x012b }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x012b }
                r6[r7] = r0     // Catch:{ NoSuchFieldError -> 0x012b }
            L_0x012b:
                int[] r6 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Hour     // Catch:{ NoSuchFieldError -> 0x0135 }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$Hour r7 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.Hour.UNDEFINED     // Catch:{ NoSuchFieldError -> 0x0135 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x0135 }
                r6[r7] = r2     // Catch:{ NoSuchFieldError -> 0x0135 }
            L_0x0135:
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$Day[] r6 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.Day.values()
                int r6 = r6.length
                int[] r6 = new int[r6]
                $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Day = r6
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$Day r7 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.Day.NUMERIC     // Catch:{ NoSuchFieldError -> 0x0146 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x0146 }
                r6[r7] = r1     // Catch:{ NoSuchFieldError -> 0x0146 }
            L_0x0146:
                int[] r6 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Day     // Catch:{ NoSuchFieldError -> 0x0150 }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$Day r7 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.Day.DIGIT2     // Catch:{ NoSuchFieldError -> 0x0150 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x0150 }
                r6[r7] = r0     // Catch:{ NoSuchFieldError -> 0x0150 }
            L_0x0150:
                int[] r6 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Day     // Catch:{ NoSuchFieldError -> 0x015a }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$Day r7 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.Day.UNDEFINED     // Catch:{ NoSuchFieldError -> 0x015a }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x015a }
                r6[r7] = r2     // Catch:{ NoSuchFieldError -> 0x015a }
            L_0x015a:
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$Month[] r6 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.Month.values()
                int r6 = r6.length
                int[] r6 = new int[r6]
                $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Month = r6
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$Month r7 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.Month.NUMERIC     // Catch:{ NoSuchFieldError -> 0x016b }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x016b }
                r6[r7] = r1     // Catch:{ NoSuchFieldError -> 0x016b }
            L_0x016b:
                int[] r6 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Month     // Catch:{ NoSuchFieldError -> 0x0175 }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$Month r7 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.Month.DIGIT2     // Catch:{ NoSuchFieldError -> 0x0175 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x0175 }
                r6[r7] = r0     // Catch:{ NoSuchFieldError -> 0x0175 }
            L_0x0175:
                int[] r6 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Month     // Catch:{ NoSuchFieldError -> 0x017f }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$Month r7 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.Month.LONG     // Catch:{ NoSuchFieldError -> 0x017f }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x017f }
                r6[r7] = r2     // Catch:{ NoSuchFieldError -> 0x017f }
            L_0x017f:
                int[] r6 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Month     // Catch:{ NoSuchFieldError -> 0x0189 }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$Month r7 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.Month.SHORT     // Catch:{ NoSuchFieldError -> 0x0189 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x0189 }
                r6[r7] = r3     // Catch:{ NoSuchFieldError -> 0x0189 }
            L_0x0189:
                int[] r6 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Month     // Catch:{ NoSuchFieldError -> 0x0193 }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$Month r7 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.Month.NARROW     // Catch:{ NoSuchFieldError -> 0x0193 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x0193 }
                r6[r7] = r4     // Catch:{ NoSuchFieldError -> 0x0193 }
            L_0x0193:
                int[] r6 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Month     // Catch:{ NoSuchFieldError -> 0x019d }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$Month r7 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.Month.UNDEFINED     // Catch:{ NoSuchFieldError -> 0x019d }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x019d }
                r6[r7] = r5     // Catch:{ NoSuchFieldError -> 0x019d }
            L_0x019d:
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$Year[] r5 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.Year.values()
                int r5 = r5.length
                int[] r5 = new int[r5]
                $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Year = r5
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$Year r6 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.Year.NUMERIC     // Catch:{ NoSuchFieldError -> 0x01ae }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x01ae }
                r5[r6] = r1     // Catch:{ NoSuchFieldError -> 0x01ae }
            L_0x01ae:
                int[] r5 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Year     // Catch:{ NoSuchFieldError -> 0x01b8 }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$Year r6 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.Year.DIGIT2     // Catch:{ NoSuchFieldError -> 0x01b8 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x01b8 }
                r5[r6] = r0     // Catch:{ NoSuchFieldError -> 0x01b8 }
            L_0x01b8:
                int[] r5 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Year     // Catch:{ NoSuchFieldError -> 0x01c2 }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$Year r6 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.Year.UNDEFINED     // Catch:{ NoSuchFieldError -> 0x01c2 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x01c2 }
                r5[r6] = r2     // Catch:{ NoSuchFieldError -> 0x01c2 }
            L_0x01c2:
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$Era[] r5 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.Era.values()
                int r5 = r5.length
                int[] r5 = new int[r5]
                $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Era = r5
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$Era r6 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.Era.LONG     // Catch:{ NoSuchFieldError -> 0x01d3 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x01d3 }
                r5[r6] = r1     // Catch:{ NoSuchFieldError -> 0x01d3 }
            L_0x01d3:
                int[] r5 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Era     // Catch:{ NoSuchFieldError -> 0x01dd }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$Era r6 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.Era.SHORT     // Catch:{ NoSuchFieldError -> 0x01dd }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x01dd }
                r5[r6] = r0     // Catch:{ NoSuchFieldError -> 0x01dd }
            L_0x01dd:
                int[] r5 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Era     // Catch:{ NoSuchFieldError -> 0x01e7 }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$Era r6 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.Era.NARROW     // Catch:{ NoSuchFieldError -> 0x01e7 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x01e7 }
                r5[r6] = r2     // Catch:{ NoSuchFieldError -> 0x01e7 }
            L_0x01e7:
                int[] r5 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$Era     // Catch:{ NoSuchFieldError -> 0x01f1 }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$Era r6 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.Era.UNDEFINED     // Catch:{ NoSuchFieldError -> 0x01f1 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x01f1 }
                r5[r6] = r3     // Catch:{ NoSuchFieldError -> 0x01f1 }
            L_0x01f1:
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$WeekDay[] r5 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.WeekDay.values()
                int r5 = r5.length
                int[] r5 = new int[r5]
                $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$WeekDay = r5
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$WeekDay r6 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.WeekDay.LONG     // Catch:{ NoSuchFieldError -> 0x0202 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x0202 }
                r5[r6] = r1     // Catch:{ NoSuchFieldError -> 0x0202 }
            L_0x0202:
                int[] r5 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$WeekDay     // Catch:{ NoSuchFieldError -> 0x020c }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$WeekDay r6 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.WeekDay.SHORT     // Catch:{ NoSuchFieldError -> 0x020c }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x020c }
                r5[r6] = r0     // Catch:{ NoSuchFieldError -> 0x020c }
            L_0x020c:
                int[] r5 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$WeekDay     // Catch:{ NoSuchFieldError -> 0x0216 }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$WeekDay r6 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.WeekDay.NARROW     // Catch:{ NoSuchFieldError -> 0x0216 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x0216 }
                r5[r6] = r2     // Catch:{ NoSuchFieldError -> 0x0216 }
            L_0x0216:
                int[] r5 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$WeekDay     // Catch:{ NoSuchFieldError -> 0x0220 }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$WeekDay r6 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.WeekDay.UNDEFINED     // Catch:{ NoSuchFieldError -> 0x0220 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x0220 }
                r5[r6] = r3     // Catch:{ NoSuchFieldError -> 0x0220 }
            L_0x0220:
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$HourCycle[] r5 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.HourCycle.values()
                int r5 = r5.length
                int[] r5 = new int[r5]
                $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$HourCycle = r5
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$HourCycle r6 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.HourCycle.H11     // Catch:{ NoSuchFieldError -> 0x0231 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x0231 }
                r5[r6] = r1     // Catch:{ NoSuchFieldError -> 0x0231 }
            L_0x0231:
                int[] r5 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$HourCycle     // Catch:{ NoSuchFieldError -> 0x023b }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$HourCycle r6 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.HourCycle.H12     // Catch:{ NoSuchFieldError -> 0x023b }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x023b }
                r5[r6] = r0     // Catch:{ NoSuchFieldError -> 0x023b }
            L_0x023b:
                int[] r5 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$HourCycle     // Catch:{ NoSuchFieldError -> 0x0245 }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$HourCycle r6 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.HourCycle.H23     // Catch:{ NoSuchFieldError -> 0x0245 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x0245 }
                r5[r6] = r2     // Catch:{ NoSuchFieldError -> 0x0245 }
            L_0x0245:
                int[] r2 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$HourCycle     // Catch:{ NoSuchFieldError -> 0x024f }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$HourCycle r5 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.HourCycle.H24     // Catch:{ NoSuchFieldError -> 0x024f }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x024f }
                r2[r5] = r3     // Catch:{ NoSuchFieldError -> 0x024f }
            L_0x024f:
                int[] r2 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$HourCycle     // Catch:{ NoSuchFieldError -> 0x0259 }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$HourCycle r3 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.HourCycle.UNDEFINED     // Catch:{ NoSuchFieldError -> 0x0259 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0259 }
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x0259 }
            L_0x0259:
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$FormatMatcher[] r2 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.FormatMatcher.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$FormatMatcher = r2
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$FormatMatcher r3 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.FormatMatcher.BESTFIT     // Catch:{ NoSuchFieldError -> 0x026a }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x026a }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x026a }
            L_0x026a:
                int[] r1 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$FormatMatcher     // Catch:{ NoSuchFieldError -> 0x0274 }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$FormatMatcher r2 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.FormatMatcher.BASIC     // Catch:{ NoSuchFieldError -> 0x0274 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0274 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0274 }
            L_0x0274:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.hermes.intl.IPlatformDateTimeFormatter.AnonymousClass1.<clinit>():void");
        }
    }

    public enum TimeStyle {
        FULL,
        LONG,
        MEDIUM,
        SHORT,
        UNDEFINED;

        public String toString() {
            int i = AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$TimeStyle[ordinal()];
            if (i == 1) {
                return "full";
            }
            if (i == 2) {
                return "long";
            }
            if (i == 3) {
                return "medium";
            }
            if (i == 4) {
                return "short";
            }
            if (i == 5) {
                return "";
            }
            throw new IllegalArgumentException();
        }
    }
}
