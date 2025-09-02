package androidx.room.util;

import android.database.Cursor;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.google.firebase.messaging.Constants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;

public abstract class TableInfoKt {
    public static final TableInfo readTableInfo(SupportSQLiteDatabase supportSQLiteDatabase, String str) {
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "database");
        Intrinsics.checkNotNullParameter(str, "tableName");
        return new TableInfo(str, readColumns(supportSQLiteDatabase, str), readForeignKeys(supportSQLiteDatabase, str), readIndices(supportSQLiteDatabase, str));
    }

    private static final Set readForeignKeys(SupportSQLiteDatabase supportSQLiteDatabase, String str) {
        Cursor query = supportSQLiteDatabase.query("PRAGMA foreign_key_list(`" + str + "`)");
        try {
            int columnIndex = query.getColumnIndex("id");
            int columnIndex2 = query.getColumnIndex("seq");
            int columnIndex3 = query.getColumnIndex("table");
            int columnIndex4 = query.getColumnIndex("on_delete");
            int columnIndex5 = query.getColumnIndex("on_update");
            List readForeignKeyFieldMappings = readForeignKeyFieldMappings(query);
            query.moveToPosition(-1);
            Set createSetBuilder = SetsKt.createSetBuilder();
            while (query.moveToNext()) {
                if (query.getInt(columnIndex2) == 0) {
                    int i = query.getInt(columnIndex);
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    ArrayList<TableInfo.ForeignKeyWithSequence> arrayList3 = new ArrayList<>();
                    for (Object next : readForeignKeyFieldMappings) {
                        if (((TableInfo.ForeignKeyWithSequence) next).getId() == i) {
                            arrayList3.add(next);
                        }
                    }
                    for (TableInfo.ForeignKeyWithSequence foreignKeyWithSequence : arrayList3) {
                        arrayList.add(foreignKeyWithSequence.getFrom());
                        arrayList2.add(foreignKeyWithSequence.getTo());
                    }
                    String string = query.getString(columnIndex3);
                    Intrinsics.checkNotNullExpressionValue(string, "cursor.getString(tableColumnIndex)");
                    String string2 = query.getString(columnIndex4);
                    Intrinsics.checkNotNullExpressionValue(string2, "cursor.getString(onDeleteColumnIndex)");
                    String string3 = query.getString(columnIndex5);
                    Intrinsics.checkNotNullExpressionValue(string3, "cursor.getString(onUpdateColumnIndex)");
                    createSetBuilder.add(new TableInfo.ForeignKey(string, string2, string3, arrayList, arrayList2));
                }
            }
            Set build = SetsKt.build(createSetBuilder);
            CloseableKt.closeFinally(query, (Throwable) null);
            return build;
        } catch (Throwable th) {
            CloseableKt.closeFinally(query, th);
            throw th;
        }
    }

    private static final List readForeignKeyFieldMappings(Cursor cursor) {
        int columnIndex = cursor.getColumnIndex("id");
        int columnIndex2 = cursor.getColumnIndex("seq");
        int columnIndex3 = cursor.getColumnIndex(Constants.MessagePayloadKeys.FROM);
        int columnIndex4 = cursor.getColumnIndex("to");
        List createListBuilder = CollectionsKt.createListBuilder();
        while (cursor.moveToNext()) {
            int i = cursor.getInt(columnIndex);
            int i2 = cursor.getInt(columnIndex2);
            String string = cursor.getString(columnIndex3);
            Intrinsics.checkNotNullExpressionValue(string, "cursor.getString(fromColumnIndex)");
            String string2 = cursor.getString(columnIndex4);
            Intrinsics.checkNotNullExpressionValue(string2, "cursor.getString(toColumnIndex)");
            createListBuilder.add(new TableInfo.ForeignKeyWithSequence(i, i2, string, string2));
        }
        return CollectionsKt.sorted(CollectionsKt.build(createListBuilder));
    }

    private static final Map readColumns(SupportSQLiteDatabase supportSQLiteDatabase, String str) {
        Throwable th;
        Cursor query = supportSQLiteDatabase.query("PRAGMA table_info(`" + str + "`)");
        try {
            if (query.getColumnCount() <= 0) {
                Map emptyMap = MapsKt.emptyMap();
                CloseableKt.closeFinally(query, (Throwable) null);
                return emptyMap;
            }
            int columnIndex = query.getColumnIndex("name");
            int columnIndex2 = query.getColumnIndex("type");
            int columnIndex3 = query.getColumnIndex("notnull");
            int columnIndex4 = query.getColumnIndex("pk");
            int columnIndex5 = query.getColumnIndex("dflt_value");
            Map createMapBuilder = MapsKt.createMapBuilder();
            while (query.moveToNext()) {
                String string = query.getString(columnIndex);
                String string2 = query.getString(columnIndex2);
                boolean z = query.getInt(columnIndex3) != 0;
                int i = query.getInt(columnIndex4);
                String string3 = query.getString(columnIndex5);
                Intrinsics.checkNotNullExpressionValue(string, "name");
                Intrinsics.checkNotNullExpressionValue(string2, "type");
                TableInfo.Column column = r11;
                TableInfo.Column column2 = new TableInfo.Column(string, string2, z, i, string3, 2);
                createMapBuilder.put(string, column);
            }
            Map build = MapsKt.build(createMapBuilder);
            CloseableKt.closeFinally(query, (Throwable) null);
            return build;
        } catch (Throwable th2) {
            Throwable th3 = th2;
            CloseableKt.closeFinally(query, th);
            throw th3;
        }
    }

    private static final Set readIndices(SupportSQLiteDatabase supportSQLiteDatabase, String str) {
        Cursor query = supportSQLiteDatabase.query("PRAGMA index_list(`" + str + "`)");
        try {
            int columnIndex = query.getColumnIndex("name");
            int columnIndex2 = query.getColumnIndex("origin");
            int columnIndex3 = query.getColumnIndex("unique");
            if (!(columnIndex == -1 || columnIndex2 == -1)) {
                if (columnIndex3 != -1) {
                    Set createSetBuilder = SetsKt.createSetBuilder();
                    while (query.moveToNext()) {
                        if (Intrinsics.areEqual((Object) "c", (Object) query.getString(columnIndex2))) {
                            String string = query.getString(columnIndex);
                            boolean z = true;
                            if (query.getInt(columnIndex3) != 1) {
                                z = false;
                            }
                            Intrinsics.checkNotNullExpressionValue(string, "name");
                            TableInfo.Index readIndex = readIndex(supportSQLiteDatabase, string, z);
                            if (readIndex == null) {
                                CloseableKt.closeFinally(query, (Throwable) null);
                                return null;
                            }
                            createSetBuilder.add(readIndex);
                        }
                    }
                    Set build = SetsKt.build(createSetBuilder);
                    CloseableKt.closeFinally(query, (Throwable) null);
                    return build;
                }
            }
            CloseableKt.closeFinally(query, (Throwable) null);
            return null;
        } catch (Throwable th) {
            CloseableKt.closeFinally(query, th);
            throw th;
        }
    }

    private static final TableInfo.Index readIndex(SupportSQLiteDatabase supportSQLiteDatabase, String str, boolean z) {
        Cursor query = supportSQLiteDatabase.query("PRAGMA index_xinfo(`" + str + "`)");
        try {
            int columnIndex = query.getColumnIndex("seqno");
            int columnIndex2 = query.getColumnIndex("cid");
            int columnIndex3 = query.getColumnIndex("name");
            int columnIndex4 = query.getColumnIndex("desc");
            if (!(columnIndex == -1 || columnIndex2 == -1 || columnIndex3 == -1)) {
                if (columnIndex4 != -1) {
                    TreeMap treeMap = new TreeMap();
                    TreeMap treeMap2 = new TreeMap();
                    while (query.moveToNext()) {
                        if (query.getInt(columnIndex2) >= 0) {
                            int i = query.getInt(columnIndex);
                            String string = query.getString(columnIndex3);
                            String str2 = query.getInt(columnIndex4) > 0 ? "DESC" : "ASC";
                            Integer valueOf = Integer.valueOf(i);
                            Intrinsics.checkNotNullExpressionValue(string, "columnName");
                            treeMap.put(valueOf, string);
                            treeMap2.put(Integer.valueOf(i), str2);
                        }
                    }
                    Collection values = treeMap.values();
                    Intrinsics.checkNotNullExpressionValue(values, "columnsMap.values");
                    List list = CollectionsKt.toList(values);
                    Collection values2 = treeMap2.values();
                    Intrinsics.checkNotNullExpressionValue(values2, "ordersMap.values");
                    TableInfo.Index index = new TableInfo.Index(str, z, list, CollectionsKt.toList(values2));
                    CloseableKt.closeFinally(query, (Throwable) null);
                    return index;
                }
            }
            CloseableKt.closeFinally(query, (Throwable) null);
            return null;
        } catch (Throwable th) {
            CloseableKt.closeFinally(query, th);
            throw th;
        }
    }
}
