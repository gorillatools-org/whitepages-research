package androidx.room.util;

import androidx.room.Index$Order;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.google.firebase.messaging.Constants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class TableInfo {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public final Map columns;
    public final Set foreignKeys;
    public final Set indices;
    public final String name;

    public static final TableInfo read(SupportSQLiteDatabase supportSQLiteDatabase, String str) {
        return Companion.read(supportSQLiteDatabase, str);
    }

    public TableInfo(String str, Map map, Set set, Set set2) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(map, "columns");
        Intrinsics.checkNotNullParameter(set, "foreignKeys");
        this.name = str;
        this.columns = map;
        this.foreignKeys = set;
        this.indices = set2;
    }

    public boolean equals(Object obj) {
        Set set;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TableInfo)) {
            return false;
        }
        TableInfo tableInfo = (TableInfo) obj;
        if (!Intrinsics.areEqual((Object) this.name, (Object) tableInfo.name) || !Intrinsics.areEqual((Object) this.columns, (Object) tableInfo.columns) || !Intrinsics.areEqual((Object) this.foreignKeys, (Object) tableInfo.foreignKeys)) {
            return false;
        }
        Set set2 = this.indices;
        if (set2 == null || (set = tableInfo.indices) == null) {
            return true;
        }
        return Intrinsics.areEqual((Object) set2, (Object) set);
    }

    public int hashCode() {
        return (((this.name.hashCode() * 31) + this.columns.hashCode()) * 31) + this.foreignKeys.hashCode();
    }

    public String toString() {
        return "TableInfo{name='" + this.name + "', columns=" + this.columns + ", foreignKeys=" + this.foreignKeys + ", indices=" + this.indices + '}';
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TableInfo read(SupportSQLiteDatabase supportSQLiteDatabase, String str) {
            Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "database");
            Intrinsics.checkNotNullParameter(str, "tableName");
            return TableInfoKt.readTableInfo(supportSQLiteDatabase, str);
        }
    }

    public static final class Column {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        public final int affinity;
        public final int createdFrom;
        public final String defaultValue;
        public final String name;
        public final boolean notNull;
        public final int primaryKeyPosition;
        public final String type;

        public Column(String str, String str2, boolean z, int i, String str3, int i2) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(str2, "type");
            this.name = str;
            this.type = str2;
            this.notNull = z;
            this.primaryKeyPosition = i;
            this.defaultValue = str3;
            this.createdFrom = i2;
            this.affinity = findAffinity(str2);
        }

        private final int findAffinity(String str) {
            if (str == null) {
                return 5;
            }
            Locale locale = Locale.US;
            Intrinsics.checkNotNullExpressionValue(locale, "US");
            String upperCase = str.toUpperCase(locale);
            Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(locale)");
            if (StringsKt.contains$default((CharSequence) upperCase, (CharSequence) "INT", false, 2, (Object) null)) {
                return 3;
            }
            if (StringsKt.contains$default((CharSequence) upperCase, (CharSequence) "CHAR", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) upperCase, (CharSequence) "CLOB", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) upperCase, (CharSequence) "TEXT", false, 2, (Object) null)) {
                return 2;
            }
            if (StringsKt.contains$default((CharSequence) upperCase, (CharSequence) "BLOB", false, 2, (Object) null)) {
                return 5;
            }
            return (StringsKt.contains$default((CharSequence) upperCase, (CharSequence) "REAL", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) upperCase, (CharSequence) "FLOA", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) upperCase, (CharSequence) "DOUB", false, 2, (Object) null)) ? 4 : 1;
        }

        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final boolean defaultValueEquals(String str, String str2) {
                Intrinsics.checkNotNullParameter(str, "current");
                if (Intrinsics.areEqual((Object) str, (Object) str2)) {
                    return true;
                }
                if (!containsSurroundingParenthesis(str)) {
                    return false;
                }
                String substring = str.substring(1, str.length() - 1);
                Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                return Intrinsics.areEqual((Object) StringsKt.trim(substring).toString(), (Object) str2);
            }

            private final boolean containsSurroundingParenthesis(String str) {
                if (str.length() == 0) {
                    return false;
                }
                int i = 0;
                int i2 = 0;
                int i3 = 0;
                while (i < str.length()) {
                    char charAt = str.charAt(i);
                    int i4 = i3 + 1;
                    if (i3 == 0 && charAt != '(') {
                        return false;
                    }
                    if (charAt == '(') {
                        i2++;
                    } else if (charAt == ')' && i2 - 1 == 0 && i3 != str.length() - 1) {
                        return false;
                    }
                    i++;
                    i3 = i4;
                }
                if (i2 == 0) {
                    return true;
                }
                return false;
            }
        }

        public boolean equals(Object obj) {
            String str;
            String str2;
            String str3;
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Column) || this.primaryKeyPosition != ((Column) obj).primaryKeyPosition) {
                return false;
            }
            Column column = (Column) obj;
            if (!Intrinsics.areEqual((Object) this.name, (Object) column.name) || this.notNull != column.notNull) {
                return false;
            }
            if (this.createdFrom == 1 && column.createdFrom == 2 && (str3 = this.defaultValue) != null && !Companion.defaultValueEquals(str3, column.defaultValue)) {
                return false;
            }
            if (this.createdFrom == 2 && column.createdFrom == 1 && (str2 = column.defaultValue) != null && !Companion.defaultValueEquals(str2, this.defaultValue)) {
                return false;
            }
            int i = this.createdFrom;
            if (i != 0 && i == column.createdFrom && ((str = this.defaultValue) == null ? column.defaultValue != null : !Companion.defaultValueEquals(str, column.defaultValue))) {
                return false;
            }
            if (this.affinity == column.affinity) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return (((((this.name.hashCode() * 31) + this.affinity) * 31) + (this.notNull ? 1231 : 1237)) * 31) + this.primaryKeyPosition;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Column{name='");
            sb.append(this.name);
            sb.append("', type='");
            sb.append(this.type);
            sb.append("', affinity='");
            sb.append(this.affinity);
            sb.append("', notNull=");
            sb.append(this.notNull);
            sb.append(", primaryKeyPosition=");
            sb.append(this.primaryKeyPosition);
            sb.append(", defaultValue='");
            String str = this.defaultValue;
            if (str == null) {
                str = "undefined";
            }
            sb.append(str);
            sb.append("'}");
            return sb.toString();
        }
    }

    public static final class ForeignKey {
        public final List columnNames;
        public final String onDelete;
        public final String onUpdate;
        public final List referenceColumnNames;
        public final String referenceTable;

        public ForeignKey(String str, String str2, String str3, List list, List list2) {
            Intrinsics.checkNotNullParameter(str, "referenceTable");
            Intrinsics.checkNotNullParameter(str2, "onDelete");
            Intrinsics.checkNotNullParameter(str3, "onUpdate");
            Intrinsics.checkNotNullParameter(list, "columnNames");
            Intrinsics.checkNotNullParameter(list2, "referenceColumnNames");
            this.referenceTable = str;
            this.onDelete = str2;
            this.onUpdate = str3;
            this.columnNames = list;
            this.referenceColumnNames = list2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ForeignKey)) {
                return false;
            }
            ForeignKey foreignKey = (ForeignKey) obj;
            if (Intrinsics.areEqual((Object) this.referenceTable, (Object) foreignKey.referenceTable) && Intrinsics.areEqual((Object) this.onDelete, (Object) foreignKey.onDelete) && Intrinsics.areEqual((Object) this.onUpdate, (Object) foreignKey.onUpdate) && Intrinsics.areEqual((Object) this.columnNames, (Object) foreignKey.columnNames)) {
                return Intrinsics.areEqual((Object) this.referenceColumnNames, (Object) foreignKey.referenceColumnNames);
            }
            return false;
        }

        public int hashCode() {
            return (((((((this.referenceTable.hashCode() * 31) + this.onDelete.hashCode()) * 31) + this.onUpdate.hashCode()) * 31) + this.columnNames.hashCode()) * 31) + this.referenceColumnNames.hashCode();
        }

        public String toString() {
            return "ForeignKey{referenceTable='" + this.referenceTable + "', onDelete='" + this.onDelete + " +', onUpdate='" + this.onUpdate + "', columnNames=" + this.columnNames + ", referenceColumnNames=" + this.referenceColumnNames + '}';
        }
    }

    public static final class ForeignKeyWithSequence implements Comparable {
        private final String from;
        private final int id;
        private final int sequence;
        private final String to;

        public ForeignKeyWithSequence(int i, int i2, String str, String str2) {
            Intrinsics.checkNotNullParameter(str, Constants.MessagePayloadKeys.FROM);
            Intrinsics.checkNotNullParameter(str2, "to");
            this.id = i;
            this.sequence = i2;
            this.from = str;
            this.to = str2;
        }

        public final int getId() {
            return this.id;
        }

        public final String getFrom() {
            return this.from;
        }

        public final String getTo() {
            return this.to;
        }

        public int compareTo(ForeignKeyWithSequence foreignKeyWithSequence) {
            Intrinsics.checkNotNullParameter(foreignKeyWithSequence, "other");
            int i = this.id - foreignKeyWithSequence.id;
            return i == 0 ? this.sequence - foreignKeyWithSequence.sequence : i;
        }
    }

    public static final class Index {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        public final List columns;
        public final String name;
        public List orders;
        public final boolean unique;

        public Index(String str, boolean z, List list, List list2) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(list, "columns");
            Intrinsics.checkNotNullParameter(list2, "orders");
            this.name = str;
            this.unique = z;
            this.columns = list;
            this.orders = list2;
            Collection collection = list2;
            boolean isEmpty = collection.isEmpty();
            ArrayList arrayList = collection;
            if (isEmpty) {
                int size = list.size();
                ArrayList arrayList2 = new ArrayList(size);
                for (int i = 0; i < size; i++) {
                    arrayList2.add(Index$Order.ASC.name());
                }
                arrayList = arrayList2;
            }
            this.orders = (List) arrayList;
        }

        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Index)) {
                return false;
            }
            Index index = (Index) obj;
            if (this.unique != index.unique || !Intrinsics.areEqual((Object) this.columns, (Object) index.columns) || !Intrinsics.areEqual((Object) this.orders, (Object) index.orders)) {
                return false;
            }
            if (StringsKt.startsWith$default(this.name, "index_", false, 2, (Object) null)) {
                return StringsKt.startsWith$default(index.name, "index_", false, 2, (Object) null);
            }
            return Intrinsics.areEqual((Object) this.name, (Object) index.name);
        }

        public int hashCode() {
            int i;
            if (StringsKt.startsWith$default(this.name, "index_", false, 2, (Object) null)) {
                i = -1184239155;
            } else {
                i = this.name.hashCode();
            }
            return (((((i * 31) + (this.unique ? 1 : 0)) * 31) + this.columns.hashCode()) * 31) + this.orders.hashCode();
        }

        public String toString() {
            return "Index{name='" + this.name + "', unique=" + this.unique + ", columns=" + this.columns + ", orders=" + this.orders + "'}";
        }
    }
}
