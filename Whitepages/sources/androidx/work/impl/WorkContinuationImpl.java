package androidx.work.impl;

import android.text.TextUtils;
import androidx.work.ExistingWorkPolicy;
import androidx.work.Logger;
import androidx.work.Operation;
import androidx.work.WorkContinuation;
import androidx.work.WorkRequest;
import androidx.work.impl.utils.EnqueueRunnable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class WorkContinuationImpl extends WorkContinuation {
    private static final String TAG = Logger.tagWithPrefix("WorkContinuationImpl");
    private final List mAllIds;
    private boolean mEnqueued;
    private final ExistingWorkPolicy mExistingWorkPolicy;
    private final List mIds;
    private final String mName;
    private Operation mOperation;
    private final List mParents;
    private final List mWork;
    private final WorkManagerImpl mWorkManagerImpl;

    public WorkManagerImpl getWorkManagerImpl() {
        return this.mWorkManagerImpl;
    }

    public String getName() {
        return this.mName;
    }

    public ExistingWorkPolicy getExistingWorkPolicy() {
        return this.mExistingWorkPolicy;
    }

    public List getWork() {
        return this.mWork;
    }

    public List getIds() {
        return this.mIds;
    }

    public boolean isEnqueued() {
        return this.mEnqueued;
    }

    public void markEnqueued() {
        this.mEnqueued = true;
    }

    public List getParents() {
        return this.mParents;
    }

    public WorkContinuationImpl(WorkManagerImpl workManagerImpl, List list) {
        this(workManagerImpl, (String) null, ExistingWorkPolicy.KEEP, list, (List) null);
    }

    public WorkContinuationImpl(WorkManagerImpl workManagerImpl, String str, ExistingWorkPolicy existingWorkPolicy, List list) {
        this(workManagerImpl, str, existingWorkPolicy, list, (List) null);
    }

    public WorkContinuationImpl(WorkManagerImpl workManagerImpl, String str, ExistingWorkPolicy existingWorkPolicy, List list, List list2) {
        this.mWorkManagerImpl = workManagerImpl;
        this.mName = str;
        this.mExistingWorkPolicy = existingWorkPolicy;
        this.mWork = list;
        this.mParents = list2;
        this.mIds = new ArrayList(list.size());
        this.mAllIds = new ArrayList();
        if (list2 != null) {
            Iterator it = list2.iterator();
            while (it.hasNext()) {
                this.mAllIds.addAll(((WorkContinuationImpl) it.next()).mAllIds);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            String stringId = ((WorkRequest) list.get(i)).getStringId();
            this.mIds.add(stringId);
            this.mAllIds.add(stringId);
        }
    }

    public Operation enqueue() {
        if (!this.mEnqueued) {
            EnqueueRunnable enqueueRunnable = new EnqueueRunnable(this);
            this.mWorkManagerImpl.getWorkTaskExecutor().executeOnTaskThread(enqueueRunnable);
            this.mOperation = enqueueRunnable.getOperation();
        } else {
            Logger logger = Logger.get();
            String str = TAG;
            logger.warning(str, "Already enqueued work ids (" + TextUtils.join(", ", this.mIds) + ")");
        }
        return this.mOperation;
    }

    public boolean hasCycles() {
        return hasCycles(this, new HashSet());
    }

    private static boolean hasCycles(WorkContinuationImpl workContinuationImpl, Set set) {
        set.addAll(workContinuationImpl.getIds());
        Set prerequisitesFor = prerequisitesFor(workContinuationImpl);
        Iterator it = set.iterator();
        while (it.hasNext()) {
            if (prerequisitesFor.contains((String) it.next())) {
                return true;
            }
        }
        List<WorkContinuationImpl> parents = workContinuationImpl.getParents();
        if (parents != null && !parents.isEmpty()) {
            for (WorkContinuationImpl hasCycles : parents) {
                if (hasCycles(hasCycles, set)) {
                    return true;
                }
            }
        }
        set.removeAll(workContinuationImpl.getIds());
        return false;
    }

    public static Set prerequisitesFor(WorkContinuationImpl workContinuationImpl) {
        HashSet hashSet = new HashSet();
        List<WorkContinuationImpl> parents = workContinuationImpl.getParents();
        if (parents != null && !parents.isEmpty()) {
            for (WorkContinuationImpl ids : parents) {
                hashSet.addAll(ids.getIds());
            }
        }
        return hashSet;
    }
}
