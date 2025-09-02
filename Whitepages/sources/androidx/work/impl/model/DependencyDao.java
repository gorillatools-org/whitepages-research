package androidx.work.impl.model;

import java.util.List;

public interface DependencyDao {
    List getDependentWorkIds(String str);

    boolean hasCompletedAllPrerequisites(String str);

    boolean hasDependents(String str);

    void insertDependency(Dependency dependency);
}
