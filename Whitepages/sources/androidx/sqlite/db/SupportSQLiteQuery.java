package androidx.sqlite.db;

public interface SupportSQLiteQuery {
    void bindTo(SupportSQLiteProgram supportSQLiteProgram);

    String getSql();
}
