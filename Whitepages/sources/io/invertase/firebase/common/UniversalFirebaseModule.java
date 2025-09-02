package io.invertase.firebase.common;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

public class UniversalFirebaseModule {
    private final Context context;
    private final TaskExecutorService executorService = new TaskExecutorService(getName());
    private final String serviceName;

    protected UniversalFirebaseModule(Context context2, String str) {
        this.context = context2;
        this.serviceName = str;
    }

    public Context getContext() {
        return this.context;
    }

    public Context getApplicationContext() {
        return getContext().getApplicationContext();
    }

    /* access modifiers changed from: protected */
    public ExecutorService getExecutor() {
        return this.executorService.getExecutor();
    }

    public String getName() {
        return "Universal" + this.serviceName + "Module";
    }

    public void onTearDown() {
        this.executorService.shutdown();
    }

    public Map<String, Object> getConstants() {
        return new HashMap();
    }
}
