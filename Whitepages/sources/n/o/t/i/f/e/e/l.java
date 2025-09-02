package n.o.t.i.f.e.e;

import android.os.Bundle;
import android.os.Parcel;
import java.util.Objects;

public abstract class l {

    public interface a {
        void a(Object obj);
    }

    public static int a(Object obj) {
        if (obj == null) {
            return 0;
        }
        if (obj instanceof Double) {
            return (int) ((Double) obj).doubleValue();
        }
        return ((Integer) obj).intValue();
    }

    public static long b(Object obj) {
        if (obj instanceof Double) {
            return (long) ((Double) obj).doubleValue();
        }
        return ((Long) obj).longValue();
    }

    public static byte[] a(Bundle bundle) {
        Parcel obtain = Parcel.obtain();
        obtain.writeBundle(bundle);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        return marshall;
    }

    public static Bundle a(byte[] bArr) {
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(bArr, 0, bArr.length);
        obtain.setDataPosition(0);
        Bundle readBundle = obtain.readBundle(l.class.getClassLoader());
        obtain.recycle();
        Objects.requireNonNull(readBundle);
        return readBundle;
    }
}
