package androidx.lifecycle;

public class MutableLiveData extends LiveData {
    public void postValue(Object obj) {
        super.postValue(obj);
    }

    public void setValue(Object obj) {
        super.setValue(obj);
    }
}
