package androidx.work.impl.model;

public interface PreferenceDao {
    Long getLongValue(String str);

    void insertPreference(Preference preference);
}
