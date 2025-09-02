package androidx.work.impl.model;

import java.util.List;

public interface WorkNameDao {
    List getNamesForWorkSpecId(String str);

    void insert(WorkName workName);
}
