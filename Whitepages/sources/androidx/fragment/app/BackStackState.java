package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

class BackStackState implements Parcelable {
    public static final Parcelable.Creator<BackStackState> CREATOR = new Parcelable.Creator() {
        public BackStackState createFromParcel(Parcel parcel) {
            return new BackStackState(parcel);
        }

        public BackStackState[] newArray(int i) {
            return new BackStackState[i];
        }
    };
    final List mFragments;
    final List mTransactions;

    public int describeContents() {
        return 0;
    }

    BackStackState(Parcel parcel) {
        this.mFragments = parcel.createStringArrayList();
        this.mTransactions = parcel.createTypedArrayList(BackStackRecordState.CREATOR);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringList(this.mFragments);
        parcel.writeTypedList(this.mTransactions);
    }
}
