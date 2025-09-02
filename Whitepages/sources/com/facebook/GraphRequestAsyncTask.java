package com.facebook;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.facebook.internal.Utility;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import io.branch.rnbranch.RNBranchModule;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

public class GraphRequestAsyncTask extends AsyncTask {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = GraphRequestAsyncTask.class.getCanonicalName();
    private final HttpURLConnection connection;
    private Exception exception;
    private final GraphRequestBatch requests;

    public /* bridge */ /* synthetic */ Object doInBackground(Object[] objArr) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            return doInBackground((Void[]) objArr);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    public /* bridge */ /* synthetic */ void onPostExecute(Object obj) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                onPostExecute((List) obj);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    public GraphRequestAsyncTask(HttpURLConnection httpURLConnection, GraphRequestBatch graphRequestBatch) {
        Intrinsics.checkNotNullParameter(graphRequestBatch, "requests");
        this.connection = httpURLConnection;
        this.requests = graphRequestBatch;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public GraphRequestAsyncTask(GraphRequestBatch graphRequestBatch) {
        this((HttpURLConnection) null, graphRequestBatch);
        Intrinsics.checkNotNullParameter(graphRequestBatch, "requests");
    }

    public String toString() {
        String str = "{RequestAsyncTask: " + " connection: " + this.connection + ", requests: " + this.requests + "}";
        Intrinsics.checkNotNullExpressionValue(str, "StringBuilder()\n        …(\"}\")\n        .toString()");
        return str;
    }

    public void onPreExecute() {
        Handler handler;
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                super.onPreExecute();
                if (FacebookSdk.isDebugEnabled()) {
                    String str = TAG;
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String format = String.format("execute async task: %s", Arrays.copyOf(new Object[]{this}, 1));
                    Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                    Utility.logd(str, format);
                }
                if (this.requests.getCallbackHandler() == null) {
                    if (Thread.currentThread() instanceof HandlerThread) {
                        handler = new Handler();
                    } else {
                        handler = new Handler(Looper.getMainLooper());
                    }
                    this.requests.setCallbackHandler(handler);
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(List list) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                Intrinsics.checkNotNullParameter(list, "result");
                super.onPostExecute(list);
                Exception exc = this.exception;
                if (exc != null) {
                    String str = TAG;
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String format = String.format("onPostExecute: exception encountered during request: %s", Arrays.copyOf(new Object[]{exc.getMessage()}, 1));
                    Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                    Utility.logd(str, format);
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    public List doInBackground(Void... voidArr) {
        List executeConnectionAndWait;
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(voidArr, RNBranchModule.NATIVE_INIT_SESSION_FINISHED_EVENT_PARAMS);
            HttpURLConnection httpURLConnection = this.connection;
            if (httpURLConnection == null) {
                executeConnectionAndWait = this.requests.executeAndWait();
            } else {
                executeConnectionAndWait = GraphRequest.Companion.executeConnectionAndWait(httpURLConnection, this.requests);
            }
            return executeConnectionAndWait;
        } catch (Exception e) {
            this.exception = e;
            return null;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }
}
