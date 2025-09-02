package androidx.room;

import android.content.Context;
import androidx.room.RoomDatabase;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class Room {
    public static final Room INSTANCE = new Room();

    private Room() {
    }

    public static final Object getGeneratedImplementation(Class cls, String str) {
        String str2;
        Intrinsics.checkNotNullParameter(cls, "klass");
        Intrinsics.checkNotNullParameter(str, "suffix");
        Package packageR = cls.getPackage();
        Intrinsics.checkNotNull(packageR);
        String name = packageR.getName();
        String canonicalName = cls.getCanonicalName();
        Intrinsics.checkNotNull(canonicalName);
        Intrinsics.checkNotNullExpressionValue(name, "fullPackage");
        if (name.length() != 0) {
            canonicalName = canonicalName.substring(name.length() + 1);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String).substring(startIndex)");
        }
        String str3 = canonicalName;
        String str4 = StringsKt.replace$default(str3, '.', '_', false, 4, (Object) null) + str;
        try {
            if (name.length() == 0) {
                str2 = str4;
            } else {
                str2 = name + '.' + str4;
            }
            Class<?> cls2 = Class.forName(str2, true, cls.getClassLoader());
            Intrinsics.checkNotNull(cls2, "null cannot be cast to non-null type java.lang.Class<T of androidx.room.Room.getGeneratedImplementation>");
            return cls2.newInstance();
        } catch (ClassNotFoundException unused) {
            throw new RuntimeException("Cannot find implementation for " + cls.getCanonicalName() + ". " + str4 + " does not exist");
        } catch (IllegalAccessException unused2) {
            throw new RuntimeException("Cannot access the constructor " + cls + ".canonicalName");
        } catch (InstantiationException unused3) {
            throw new RuntimeException("Failed to create an instance of " + cls + ".canonicalName");
        }
    }

    public static final RoomDatabase.Builder inMemoryDatabaseBuilder(Context context, Class cls) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(cls, "klass");
        return new RoomDatabase.Builder(context, cls, (String) null);
    }

    public static final RoomDatabase.Builder databaseBuilder(Context context, Class cls, String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(cls, "klass");
        if (!(str == null || StringsKt.isBlank(str))) {
            return new RoomDatabase.Builder(context, cls, str);
        }
        throw new IllegalArgumentException("Cannot build a database with null or empty name. If you are trying to create an in memory database, use Room.inMemoryDatabaseBuilder");
    }
}
