package androidx.core.view.inputmethod;

import android.content.ClipDescription;
import android.net.Uri;
import android.view.inputmethod.InputContentInfo;

public final class InputContentInfoCompat {
    private final InputContentInfoCompatImpl mImpl;

    private interface InputContentInfoCompatImpl {
        Uri getContentUri();

        ClipDescription getDescription();

        Object getInputContentInfo();

        Uri getLinkUri();

        void requestPermission();
    }

    private static final class InputContentInfoCompatApi25Impl implements InputContentInfoCompatImpl {
        final InputContentInfo mObject;

        InputContentInfoCompatApi25Impl(Object obj) {
            this.mObject = (InputContentInfo) obj;
        }

        public Uri getContentUri() {
            return this.mObject.getContentUri();
        }

        public ClipDescription getDescription() {
            return this.mObject.getDescription();
        }

        public Uri getLinkUri() {
            return this.mObject.getLinkUri();
        }

        public Object getInputContentInfo() {
            return this.mObject;
        }

        public void requestPermission() {
            this.mObject.requestPermission();
        }
    }

    private InputContentInfoCompat(InputContentInfoCompatImpl inputContentInfoCompatImpl) {
        this.mImpl = inputContentInfoCompatImpl;
    }

    public Uri getContentUri() {
        return this.mImpl.getContentUri();
    }

    public ClipDescription getDescription() {
        return this.mImpl.getDescription();
    }

    public Uri getLinkUri() {
        return this.mImpl.getLinkUri();
    }

    public static InputContentInfoCompat wrap(Object obj) {
        if (obj == null) {
            return null;
        }
        return new InputContentInfoCompat(new InputContentInfoCompatApi25Impl(obj));
    }

    public Object unwrap() {
        return this.mImpl.getInputContentInfo();
    }

    public void requestPermission() {
        this.mImpl.requestPermission();
    }
}
