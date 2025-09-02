package com.google.firebase.sessions;

import android.content.ServiceConnection;
import android.os.Messenger;

public interface SessionLifecycleServiceBinder {
    void bindToService(Messenger messenger, ServiceConnection serviceConnection);
}
