package androidx.fragment.app;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

class FragmentStore {
    private final HashMap mActive = new HashMap();
    private final ArrayList mAdded = new ArrayList();
    private FragmentManagerViewModel mNonConfig;
    private final HashMap mSavedState = new HashMap();

    FragmentStore() {
    }

    /* access modifiers changed from: package-private */
    public void setNonConfig(FragmentManagerViewModel fragmentManagerViewModel) {
        this.mNonConfig = fragmentManagerViewModel;
    }

    /* access modifiers changed from: package-private */
    public FragmentManagerViewModel getNonConfig() {
        return this.mNonConfig;
    }

    /* access modifiers changed from: package-private */
    public void resetActiveFragments() {
        this.mActive.clear();
    }

    /* access modifiers changed from: package-private */
    public void restoreAddedFragments(List list) {
        this.mAdded.clear();
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                Fragment findActiveFragment = findActiveFragment(str);
                if (findActiveFragment != null) {
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "restoreSaveState: added (" + str + "): " + findActiveFragment);
                    }
                    addFragment(findActiveFragment);
                } else {
                    throw new IllegalStateException("No instantiated fragment for (" + str + ")");
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void makeActive(FragmentStateManager fragmentStateManager) {
        Fragment fragment = fragmentStateManager.getFragment();
        if (!containsActiveFragment(fragment.mWho)) {
            this.mActive.put(fragment.mWho, fragmentStateManager);
            if (fragment.mRetainInstanceChangedWhileDetached) {
                if (fragment.mRetainInstance) {
                    this.mNonConfig.addRetainedFragment(fragment);
                } else {
                    this.mNonConfig.removeRetainedFragment(fragment);
                }
                fragment.mRetainInstanceChangedWhileDetached = false;
            }
            if (FragmentManager.isLoggingEnabled(2)) {
                Log.v("FragmentManager", "Added fragment to active set " + fragment);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void addFragment(Fragment fragment) {
        if (!this.mAdded.contains(fragment)) {
            synchronized (this.mAdded) {
                this.mAdded.add(fragment);
            }
            fragment.mAdded = true;
            return;
        }
        throw new IllegalStateException("Fragment already added: " + fragment);
    }

    /* access modifiers changed from: package-private */
    public void dispatchStateChange(int i) {
        for (FragmentStateManager fragmentStateManager : this.mActive.values()) {
            if (fragmentStateManager != null) {
                fragmentStateManager.setFragmentManagerState(i);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void moveToExpectedState() {
        Iterator it = this.mAdded.iterator();
        while (it.hasNext()) {
            FragmentStateManager fragmentStateManager = (FragmentStateManager) this.mActive.get(((Fragment) it.next()).mWho);
            if (fragmentStateManager != null) {
                fragmentStateManager.moveToExpectedState();
            }
        }
        for (FragmentStateManager fragmentStateManager2 : this.mActive.values()) {
            if (fragmentStateManager2 != null) {
                fragmentStateManager2.moveToExpectedState();
                Fragment fragment = fragmentStateManager2.getFragment();
                if (fragment.mRemoving && !fragment.isInBackStack()) {
                    if (fragment.mBeingSaved && !this.mSavedState.containsKey(fragment.mWho)) {
                        setSavedState(fragment.mWho, fragmentStateManager2.saveState());
                    }
                    makeInactive(fragmentStateManager2);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void removeFragment(Fragment fragment) {
        synchronized (this.mAdded) {
            this.mAdded.remove(fragment);
        }
        fragment.mAdded = false;
    }

    /* access modifiers changed from: package-private */
    public void makeInactive(FragmentStateManager fragmentStateManager) {
        Fragment fragment = fragmentStateManager.getFragment();
        if (fragment.mRetainInstance) {
            this.mNonConfig.removeRetainedFragment(fragment);
        }
        if (this.mActive.get(fragment.mWho) == fragmentStateManager && ((FragmentStateManager) this.mActive.put(fragment.mWho, (Object) null)) != null && FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Removed fragment from active set " + fragment);
        }
    }

    /* access modifiers changed from: package-private */
    public void burpActive() {
        this.mActive.values().removeAll(Collections.singleton((Object) null));
    }

    /* access modifiers changed from: package-private */
    public Bundle getSavedState(String str) {
        return (Bundle) this.mSavedState.get(str);
    }

    /* access modifiers changed from: package-private */
    public Bundle setSavedState(String str, Bundle bundle) {
        if (bundle != null) {
            return (Bundle) this.mSavedState.put(str, bundle);
        }
        return (Bundle) this.mSavedState.remove(str);
    }

    /* access modifiers changed from: package-private */
    public void restoreSaveState(HashMap hashMap) {
        this.mSavedState.clear();
        this.mSavedState.putAll(hashMap);
    }

    /* access modifiers changed from: package-private */
    public HashMap getAllSavedState() {
        return this.mSavedState;
    }

    /* access modifiers changed from: package-private */
    public ArrayList saveActiveFragments() {
        ArrayList arrayList = new ArrayList(this.mActive.size());
        for (FragmentStateManager fragmentStateManager : this.mActive.values()) {
            if (fragmentStateManager != null) {
                Fragment fragment = fragmentStateManager.getFragment();
                setSavedState(fragment.mWho, fragmentStateManager.saveState());
                arrayList.add(fragment.mWho);
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "Saved state of " + fragment + ": " + fragment.mSavedFragmentState);
                }
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public ArrayList saveAddedFragments() {
        synchronized (this.mAdded) {
            try {
                if (this.mAdded.isEmpty()) {
                    return null;
                }
                ArrayList arrayList = new ArrayList(this.mAdded.size());
                Iterator it = this.mAdded.iterator();
                while (it.hasNext()) {
                    Fragment fragment = (Fragment) it.next();
                    arrayList.add(fragment.mWho);
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "saveAllState: adding fragment (" + fragment.mWho + "): " + fragment);
                    }
                }
                return arrayList;
            } finally {
            }
        }
    }

    /* access modifiers changed from: package-private */
    public List getActiveFragmentStateManagers() {
        ArrayList arrayList = new ArrayList();
        for (FragmentStateManager fragmentStateManager : this.mActive.values()) {
            if (fragmentStateManager != null) {
                arrayList.add(fragmentStateManager);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public List getFragments() {
        ArrayList arrayList;
        if (this.mAdded.isEmpty()) {
            return Collections.emptyList();
        }
        synchronized (this.mAdded) {
            arrayList = new ArrayList(this.mAdded);
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public List getActiveFragments() {
        ArrayList arrayList = new ArrayList();
        for (FragmentStateManager fragmentStateManager : this.mActive.values()) {
            if (fragmentStateManager != null) {
                arrayList.add(fragmentStateManager.getFragment());
            } else {
                arrayList.add((Object) null);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public Fragment findFragmentById(int i) {
        for (int size = this.mAdded.size() - 1; size >= 0; size--) {
            Fragment fragment = (Fragment) this.mAdded.get(size);
            if (fragment != null && fragment.mFragmentId == i) {
                return fragment;
            }
        }
        for (FragmentStateManager fragmentStateManager : this.mActive.values()) {
            if (fragmentStateManager != null) {
                Fragment fragment2 = fragmentStateManager.getFragment();
                if (fragment2.mFragmentId == i) {
                    return fragment2;
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public Fragment findFragmentByTag(String str) {
        if (str != null) {
            for (int size = this.mAdded.size() - 1; size >= 0; size--) {
                Fragment fragment = (Fragment) this.mAdded.get(size);
                if (fragment != null && str.equals(fragment.mTag)) {
                    return fragment;
                }
            }
        }
        if (str == null) {
            return null;
        }
        for (FragmentStateManager fragmentStateManager : this.mActive.values()) {
            if (fragmentStateManager != null) {
                Fragment fragment2 = fragmentStateManager.getFragment();
                if (str.equals(fragment2.mTag)) {
                    return fragment2;
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public boolean containsActiveFragment(String str) {
        return this.mActive.get(str) != null;
    }

    /* access modifiers changed from: package-private */
    public FragmentStateManager getFragmentStateManager(String str) {
        return (FragmentStateManager) this.mActive.get(str);
    }

    /* access modifiers changed from: package-private */
    public Fragment findFragmentByWho(String str) {
        Fragment findFragmentByWho;
        for (FragmentStateManager fragmentStateManager : this.mActive.values()) {
            if (fragmentStateManager != null && (findFragmentByWho = fragmentStateManager.getFragment().findFragmentByWho(str)) != null) {
                return findFragmentByWho;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public Fragment findActiveFragment(String str) {
        FragmentStateManager fragmentStateManager = (FragmentStateManager) this.mActive.get(str);
        if (fragmentStateManager != null) {
            return fragmentStateManager.getFragment();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public int findFragmentIndexInContainer(Fragment fragment) {
        View view;
        View view2;
        ViewGroup viewGroup = fragment.mContainer;
        if (viewGroup == null) {
            return -1;
        }
        int indexOf = this.mAdded.indexOf(fragment);
        for (int i = indexOf - 1; i >= 0; i--) {
            Fragment fragment2 = (Fragment) this.mAdded.get(i);
            if (fragment2.mContainer == viewGroup && (view2 = fragment2.mView) != null) {
                return viewGroup.indexOfChild(view2) + 1;
            }
        }
        while (true) {
            indexOf++;
            if (indexOf >= this.mAdded.size()) {
                return -1;
            }
            Fragment fragment3 = (Fragment) this.mAdded.get(indexOf);
            if (fragment3.mContainer == viewGroup && (view = fragment3.mView) != null) {
                return viewGroup.indexOfChild(view);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        String str2 = str + "    ";
        if (!this.mActive.isEmpty()) {
            printWriter.print(str);
            printWriter.println("Active Fragments:");
            for (FragmentStateManager fragmentStateManager : this.mActive.values()) {
                printWriter.print(str);
                if (fragmentStateManager != null) {
                    Fragment fragment = fragmentStateManager.getFragment();
                    printWriter.println(fragment);
                    fragment.dump(str2, fileDescriptor, printWriter, strArr);
                } else {
                    printWriter.println("null");
                }
            }
        }
        int size = this.mAdded.size();
        if (size > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            for (int i = 0; i < size; i++) {
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i);
                printWriter.print(": ");
                printWriter.println(((Fragment) this.mAdded.get(i)).toString());
            }
        }
    }
}
