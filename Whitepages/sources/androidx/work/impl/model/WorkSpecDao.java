package androidx.work.impl.model;

import androidx.work.Data;
import androidx.work.WorkInfo$State;
import java.util.List;

public interface WorkSpecDao {
    void delete(String str);

    List getAllEligibleWorkSpecsForScheduling(int i);

    List getEligibleWorkForScheduling(int i);

    List getInputsFromPrerequisites(String str);

    List getRecentlyCompletedWork(long j);

    List getRunningWork();

    List getScheduledWork();

    WorkInfo$State getState(String str);

    List getUnfinishedWorkWithName(String str);

    List getUnfinishedWorkWithTag(String str);

    WorkSpec getWorkSpec(String str);

    List getWorkSpecIdAndStatesForName(String str);

    boolean hasUnfinishedWork();

    void incrementPeriodCount(String str);

    int incrementWorkSpecRunAttemptCount(String str);

    void insertWorkSpec(WorkSpec workSpec);

    int markWorkSpecScheduled(String str, long j);

    void pruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast();

    int resetScheduledState();

    int resetWorkSpecRunAttemptCount(String str);

    void setLastEnqueuedTime(String str, long j);

    void setOutput(String str, Data data);

    int setState(WorkInfo$State workInfo$State, String str);

    void updateWorkSpec(WorkSpec workSpec);
}
