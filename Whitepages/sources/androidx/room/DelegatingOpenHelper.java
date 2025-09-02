package androidx.room;

import androidx.sqlite.db.SupportSQLiteOpenHelper;

public interface DelegatingOpenHelper {
    SupportSQLiteOpenHelper getDelegate();
}
