package androidx.room;

import androidx.sqlite.db.SupportSQLiteProgram;
import androidx.sqlite.db.SupportSQLiteQuery;
import com.google.android.gms.actions.SearchIntents;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class RoomSQLiteQuery implements SupportSQLiteQuery, SupportSQLiteProgram {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final TreeMap queryPool = new TreeMap();
    private int argCount;
    private final int[] bindingTypes;
    public final byte[][] blobBindings;
    private final int capacity;
    public final double[] doubleBindings;
    public final long[] longBindings;
    private volatile String query;
    public final String[] stringBindings;

    public /* synthetic */ RoomSQLiteQuery(int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(i);
    }

    public static final RoomSQLiteQuery acquire(String str, int i) {
        return Companion.acquire(str, i);
    }

    public void close() {
    }

    private RoomSQLiteQuery(int i) {
        this.capacity = i;
        int i2 = i + 1;
        this.bindingTypes = new int[i2];
        this.longBindings = new long[i2];
        this.doubleBindings = new double[i2];
        this.stringBindings = new String[i2];
        this.blobBindings = new byte[i2][];
    }

    public int getArgCount() {
        return this.argCount;
    }

    public final void init(String str, int i) {
        Intrinsics.checkNotNullParameter(str, SearchIntents.EXTRA_QUERY);
        this.query = str;
        this.argCount = i;
    }

    public final void release() {
        TreeMap treeMap = queryPool;
        synchronized (treeMap) {
            treeMap.put(Integer.valueOf(this.capacity), this);
            Companion.prunePoolLocked$room_runtime_release();
            Unit unit = Unit.INSTANCE;
        }
    }

    public String getSql() {
        String str = this.query;
        if (str != null) {
            return str;
        }
        throw new IllegalStateException("Required value was null.");
    }

    public void bindTo(SupportSQLiteProgram supportSQLiteProgram) {
        Intrinsics.checkNotNullParameter(supportSQLiteProgram, "statement");
        int argCount2 = getArgCount();
        if (1 <= argCount2) {
            int i = 1;
            while (true) {
                int i2 = this.bindingTypes[i];
                if (i2 == 1) {
                    supportSQLiteProgram.bindNull(i);
                } else if (i2 == 2) {
                    supportSQLiteProgram.bindLong(i, this.longBindings[i]);
                } else if (i2 == 3) {
                    supportSQLiteProgram.bindDouble(i, this.doubleBindings[i]);
                } else if (i2 == 4) {
                    String str = this.stringBindings[i];
                    if (str != null) {
                        supportSQLiteProgram.bindString(i, str);
                    } else {
                        throw new IllegalArgumentException("Required value was null.");
                    }
                } else if (i2 == 5) {
                    byte[] bArr = this.blobBindings[i];
                    if (bArr != null) {
                        supportSQLiteProgram.bindBlob(i, bArr);
                    } else {
                        throw new IllegalArgumentException("Required value was null.");
                    }
                }
                if (i != argCount2) {
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    public void bindNull(int i) {
        this.bindingTypes[i] = 1;
    }

    public void bindLong(int i, long j) {
        this.bindingTypes[i] = 2;
        this.longBindings[i] = j;
    }

    public void bindDouble(int i, double d) {
        this.bindingTypes[i] = 3;
        this.doubleBindings[i] = d;
    }

    public void bindString(int i, String str) {
        Intrinsics.checkNotNullParameter(str, "value");
        this.bindingTypes[i] = 4;
        this.stringBindings[i] = str;
    }

    public void bindBlob(int i, byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "value");
        this.bindingTypes[i] = 5;
        this.blobBindings[i] = bArr;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final RoomSQLiteQuery acquire(String str, int i) {
            Intrinsics.checkNotNullParameter(str, SearchIntents.EXTRA_QUERY);
            TreeMap treeMap = RoomSQLiteQuery.queryPool;
            synchronized (treeMap) {
                Map.Entry ceilingEntry = treeMap.ceilingEntry(Integer.valueOf(i));
                if (ceilingEntry != null) {
                    treeMap.remove(ceilingEntry.getKey());
                    RoomSQLiteQuery roomSQLiteQuery = (RoomSQLiteQuery) ceilingEntry.getValue();
                    roomSQLiteQuery.init(str, i);
                    Intrinsics.checkNotNullExpressionValue(roomSQLiteQuery, "sqliteQuery");
                    return roomSQLiteQuery;
                }
                Unit unit = Unit.INSTANCE;
                RoomSQLiteQuery roomSQLiteQuery2 = new RoomSQLiteQuery(i, (DefaultConstructorMarker) null);
                roomSQLiteQuery2.init(str, i);
                return roomSQLiteQuery2;
            }
        }

        public final void prunePoolLocked$room_runtime_release() {
            TreeMap treeMap = RoomSQLiteQuery.queryPool;
            if (treeMap.size() > 15) {
                int size = treeMap.size() - 10;
                Iterator it = treeMap.descendingKeySet().iterator();
                Intrinsics.checkNotNullExpressionValue(it, "queryPool.descendingKeySet().iterator()");
                while (true) {
                    int i = size - 1;
                    if (size > 0) {
                        it.next();
                        it.remove();
                        size = i;
                    } else {
                        return;
                    }
                }
            }
        }
    }
}
