package androidx.coordinatorlayout.widget;

import androidx.collection.SimpleArrayMap;
import androidx.core.util.Pools$Pool;
import androidx.core.util.Pools$SimplePool;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public final class DirectedAcyclicGraph {
    private final SimpleArrayMap mGraph = new SimpleArrayMap();
    private final Pools$Pool mListPool = new Pools$SimplePool(10);
    private final ArrayList mSortResult = new ArrayList();
    private final HashSet mSortTmpMarked = new HashSet();

    public void addNode(Object obj) {
        if (!this.mGraph.containsKey(obj)) {
            this.mGraph.put(obj, (Object) null);
        }
    }

    public boolean contains(Object obj) {
        return this.mGraph.containsKey(obj);
    }

    public void addEdge(Object obj, Object obj2) {
        if (!this.mGraph.containsKey(obj) || !this.mGraph.containsKey(obj2)) {
            throw new IllegalArgumentException("All nodes must be present in the graph before being added as an edge");
        }
        ArrayList arrayList = (ArrayList) this.mGraph.get(obj);
        if (arrayList == null) {
            arrayList = getEmptyList();
            this.mGraph.put(obj, arrayList);
        }
        arrayList.add(obj2);
    }

    public List getIncomingEdges(Object obj) {
        ArrayList incomingEdgesInternal = getIncomingEdgesInternal(obj);
        if (incomingEdgesInternal == null) {
            return null;
        }
        return new ArrayList(incomingEdgesInternal);
    }

    /* access modifiers changed from: package-private */
    public ArrayList getIncomingEdgesInternal(Object obj) {
        return (ArrayList) this.mGraph.get(obj);
    }

    public List getOutgoingEdges(Object obj) {
        int size = this.mGraph.size();
        ArrayList arrayList = null;
        for (int i = 0; i < size; i++) {
            ArrayList arrayList2 = (ArrayList) this.mGraph.valueAt(i);
            if (arrayList2 != null && arrayList2.contains(obj)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(this.mGraph.keyAt(i));
            }
        }
        return arrayList;
    }

    public boolean hasOutgoingEdges(Object obj) {
        int size = this.mGraph.size();
        for (int i = 0; i < size; i++) {
            ArrayList arrayList = (ArrayList) this.mGraph.valueAt(i);
            if (arrayList != null && arrayList.contains(obj)) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        int size = this.mGraph.size();
        for (int i = 0; i < size; i++) {
            ArrayList arrayList = (ArrayList) this.mGraph.valueAt(i);
            if (arrayList != null) {
                poolList(arrayList);
            }
        }
        this.mGraph.clear();
    }

    public ArrayList getSortedList() {
        this.mSortResult.clear();
        this.mSortTmpMarked.clear();
        int size = this.mGraph.size();
        for (int i = 0; i < size; i++) {
            dfs(this.mGraph.keyAt(i), this.mSortResult, this.mSortTmpMarked);
        }
        return this.mSortResult;
    }

    private void dfs(Object obj, ArrayList arrayList, HashSet hashSet) {
        if (!arrayList.contains(obj)) {
            if (!hashSet.contains(obj)) {
                hashSet.add(obj);
                ArrayList arrayList2 = (ArrayList) this.mGraph.get(obj);
                if (arrayList2 != null) {
                    int size = arrayList2.size();
                    for (int i = 0; i < size; i++) {
                        dfs(arrayList2.get(i), arrayList, hashSet);
                    }
                }
                hashSet.remove(obj);
                arrayList.add(obj);
                return;
            }
            throw new RuntimeException("This graph contains cyclic dependencies");
        }
    }

    private ArrayList getEmptyList() {
        ArrayList arrayList = (ArrayList) this.mListPool.acquire();
        return arrayList == null ? new ArrayList() : arrayList;
    }

    private void poolList(ArrayList arrayList) {
        arrayList.clear();
        this.mListPool.release(arrayList);
    }
}
