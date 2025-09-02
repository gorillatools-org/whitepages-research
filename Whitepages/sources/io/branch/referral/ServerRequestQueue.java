package io.branch.referral;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import io.branch.referral.Branch;
import io.branch.referral.BranchLogger;
import io.branch.referral.ServerRequest;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ServerRequestQueue {
    private static ServerRequestQueue SharedInstance;
    private static final Object reqQueueLockObject = new Object();
    private SharedPreferences.Editor editor;
    final ConcurrentHashMap instrumentationExtraData_ = new ConcurrentHashMap();
    int networkCount_ = 0;
    private final List queue;
    private final Semaphore serverSema_ = new Semaphore(1);
    private SharedPreferences sharedPref;

    public static ServerRequestQueue getInstance(Context context) {
        if (SharedInstance == null) {
            synchronized (ServerRequestQueue.class) {
                try {
                    if (SharedInstance == null) {
                        SharedInstance = new ServerRequestQueue(context);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return SharedInstance;
    }

    private ServerRequestQueue(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("BNC_Server_Request_Queue", 0);
        this.sharedPref = sharedPreferences;
        this.editor = sharedPreferences.edit();
        this.queue = retrieve(context);
    }

    private void persist() {
        JSONObject json;
        try {
            JSONArray jSONArray = new JSONArray();
            synchronized (reqQueueLockObject) {
                for (ServerRequest serverRequest : this.queue) {
                    if (serverRequest.isPersistable() && (json = serverRequest.toJSON()) != null) {
                        jSONArray.put(json);
                    }
                }
            }
            this.editor.putString("BNCServerRequestQueue", jSONArray.toString()).apply();
        } catch (Exception e) {
            String message = e.getMessage();
            StringBuilder sb = new StringBuilder();
            sb.append("Failed to persist queue");
            if (message == null) {
                message = "";
            }
            sb.append(message);
            BranchLogger.e(sb.toString());
        } catch (Throwable th) {
            while (true) {
            }
            throw th;
        }
    }

    private List retrieve(Context context) {
        String string = this.sharedPref.getString("BNCServerRequestQueue", (String) null);
        List synchronizedList = Collections.synchronizedList(new LinkedList());
        synchronized (reqQueueLockObject) {
            if (string != null) {
                try {
                    JSONArray jSONArray = new JSONArray(string);
                    int min = Math.min(jSONArray.length(), 25);
                    for (int i = 0; i < min; i++) {
                        ServerRequest fromJSON = ServerRequest.fromJSON(jSONArray.getJSONObject(i), context);
                        if (fromJSON != null) {
                            synchronizedList.add(fromJSON);
                        }
                    }
                } catch (JSONException e) {
                    BranchLogger.w("Caught JSONException " + e.getMessage());
                }
            }
        }
        return synchronizedList;
    }

    public int getSize() {
        int size;
        synchronized (reqQueueLockObject) {
            size = this.queue.size();
        }
        return size;
    }

    /* access modifiers changed from: package-private */
    public void enqueue(ServerRequest serverRequest) {
        synchronized (reqQueueLockObject) {
            if (serverRequest != null) {
                try {
                    this.queue.add(serverRequest);
                    if (getSize() >= 25) {
                        this.queue.remove(1);
                    }
                    persist();
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public ServerRequest peek() {
        ServerRequest serverRequest;
        synchronized (reqQueueLockObject) {
            try {
                serverRequest = (ServerRequest) this.queue.get(0);
            } catch (IndexOutOfBoundsException | NoSuchElementException e) {
                BranchLogger.w("Caught Exception ServerRequestQueue peek: " + e.getMessage());
                serverRequest = null;
            }
        }
        return serverRequest;
    }

    public void printQueue() {
        if (BranchLogger.getLoggingLevel().getLevel() == BranchLogger.BranchLogLevel.VERBOSE.getLevel()) {
            synchronized (reqQueueLockObject) {
                try {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < this.queue.size(); i++) {
                        sb.append(this.queue.get(i));
                        sb.append(" with locks ");
                        sb.append(((ServerRequest) this.queue.get(i)).printWaitLocks());
                        sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                    }
                    BranchLogger.v("Queue is: " + sb);
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public ServerRequest peekAt(int i) {
        ServerRequest serverRequest;
        synchronized (reqQueueLockObject) {
            try {
                serverRequest = (ServerRequest) this.queue.get(i);
            } catch (IndexOutOfBoundsException | NoSuchElementException e) {
                BranchLogger.e("Caught Exception ServerRequestQueue peekAt " + i + ": " + e.getMessage());
                serverRequest = null;
            }
        }
        return serverRequest;
    }

    /* access modifiers changed from: package-private */
    public void insert(ServerRequest serverRequest, int i) {
        synchronized (reqQueueLockObject) {
            try {
                if (this.queue.size() < i) {
                    i = this.queue.size();
                }
                this.queue.add(i, serverRequest);
                persist();
            } catch (IndexOutOfBoundsException e) {
                BranchLogger.e("Caught IndexOutOfBoundsException " + e.getMessage());
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public boolean remove(ServerRequest serverRequest) {
        boolean z;
        synchronized (reqQueueLockObject) {
            z = false;
            try {
                z = this.queue.remove(serverRequest);
                persist();
            } catch (UnsupportedOperationException e) {
                BranchLogger.e("Caught UnsupportedOperationException " + e.getMessage());
            }
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public void clear() {
        synchronized (reqQueueLockObject) {
            try {
                this.queue.clear();
                persist();
            } catch (UnsupportedOperationException e) {
                BranchLogger.e("Caught UnsupportedOperationException " + e.getMessage());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public ServerRequestInitSession getSelfInitRequest() {
        synchronized (reqQueueLockObject) {
            try {
                for (ServerRequest serverRequest : this.queue) {
                    if (serverRequest instanceof ServerRequestInitSession) {
                        ServerRequestInitSession serverRequestInitSession = (ServerRequestInitSession) serverRequest;
                        if (serverRequestInitSession.initiatedByClient) {
                            return serverRequestInitSession;
                        }
                    }
                }
                return null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void unlockProcessWait(ServerRequest.PROCESS_WAIT_LOCK process_wait_lock) {
        synchronized (reqQueueLockObject) {
            try {
                for (ServerRequest serverRequest : this.queue) {
                    if (serverRequest != null) {
                        serverRequest.removeProcessWaitLock(process_wait_lock);
                    }
                }
            } finally {
            }
        }
    }

    public void postInitClear() {
        PrefHelper prefHelper = Branch.getInstance().getPrefHelper();
        boolean canClearInitData = canClearInitData();
        BranchLogger.v("postInitClear " + prefHelper + " can clear init data " + canClearInitData);
        if (prefHelper != null && canClearInitData) {
            prefHelper.setLinkClickIdentifier("bnc_no_value");
            prefHelper.setGoogleSearchInstallIdentifier("bnc_no_value");
            prefHelper.setAppStoreReferrer("bnc_no_value");
            prefHelper.setExternalIntentUri("bnc_no_value");
            prefHelper.setExternalIntentExtra("bnc_no_value");
            prefHelper.setAppLink("bnc_no_value");
            prefHelper.setPushIdentifier("bnc_no_value");
            prefHelper.setInstallReferrerParams("bnc_no_value");
            prefHelper.setIsFullAppConversion(false);
            prefHelper.setInitialReferrer("bnc_no_value");
            if (prefHelper.getLong("bnc_previous_update_time") == 0) {
                prefHelper.setLong("bnc_previous_update_time", prefHelper.getLong("bnc_last_known_update_time"));
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void processNextQueueItem(String str) {
        BranchLogger.v("processNextQueueItem " + str);
        printQueue();
        try {
            this.serverSema_.acquire();
            if (this.networkCount_ != 0 || getSize() <= 0) {
                this.serverSema_.release();
                return;
            }
            this.networkCount_ = 1;
            ServerRequest peek = peek();
            this.serverSema_.release();
            if (peek != null) {
                BranchLogger.d("processNextQueueItem, req " + peek);
                if (!peek.isWaitingOnProcessToFinish()) {
                    if (!(peek instanceof ServerRequestRegisterInstall)) {
                        if (!hasUser()) {
                            BranchLogger.d("Branch Error: User session has not been initialized!");
                            this.networkCount_ = 0;
                            peek.handleFailure(-101, "");
                            return;
                        }
                    }
                    if (!requestNeedsSession(peek) || isSessionAvailableForRequest()) {
                        executeTimedBranchPostTask(peek, Branch.getInstance().prefHelper_.getTaskTimeout());
                        return;
                    }
                    this.networkCount_ = 0;
                    peek.handleFailure(-101, "");
                    return;
                }
                this.networkCount_ = 0;
                return;
            }
            remove((ServerRequest) null);
        } catch (Exception e) {
            BranchLogger.e("Caught Exception " + str + " processNextQueueItem: " + e.getMessage() + " stacktrace: " + BranchLogger.stackTraceToString(e));
        }
    }

    /* access modifiers changed from: package-private */
    public void insertRequestAtFront(ServerRequest serverRequest) {
        if (this.networkCount_ == 0) {
            insert(serverRequest, 0);
        } else {
            insert(serverRequest, 1);
        }
    }

    private boolean requestNeedsSession(ServerRequest serverRequest) {
        if (!(serverRequest instanceof ServerRequestInitSession) && !(serverRequest instanceof ServerRequestCreateUrl)) {
            return true;
        }
        return false;
    }

    private boolean isSessionAvailableForRequest() {
        return hasSession() && hasRandomizedDeviceToken();
    }

    private boolean hasSession() {
        return !Branch.getInstance().prefHelper_.getSessionID().equals("bnc_no_value");
    }

    private boolean hasRandomizedDeviceToken() {
        return !Branch.getInstance().prefHelper_.getRandomizedDeviceToken().equals("bnc_no_value");
    }

    /* access modifiers changed from: package-private */
    public boolean hasUser() {
        return !Branch.getInstance().prefHelper_.getRandomizedBundleToken().equals("bnc_no_value");
    }

    /* access modifiers changed from: package-private */
    public void updateAllRequestsInQueue() {
        JSONObject post;
        int i = 0;
        while (i < getSize()) {
            try {
                ServerRequest peekAt = peekAt(i);
                if (!(peekAt == null || (post = peekAt.getPost()) == null)) {
                    Defines$Jsonkey defines$Jsonkey = Defines$Jsonkey.SessionID;
                    if (post.has(defines$Jsonkey.getKey())) {
                        peekAt.getPost().put(defines$Jsonkey.getKey(), Branch.getInstance().prefHelper_.getSessionID());
                    }
                    Defines$Jsonkey defines$Jsonkey2 = Defines$Jsonkey.RandomizedBundleToken;
                    if (post.has(defines$Jsonkey2.getKey())) {
                        peekAt.getPost().put(defines$Jsonkey2.getKey(), Branch.getInstance().prefHelper_.getRandomizedBundleToken());
                    }
                    Defines$Jsonkey defines$Jsonkey3 = Defines$Jsonkey.RandomizedDeviceToken;
                    if (post.has(defines$Jsonkey3.getKey())) {
                        peekAt.getPost().put(defines$Jsonkey3.getKey(), Branch.getInstance().prefHelper_.getRandomizedDeviceToken());
                    }
                }
                i++;
            } catch (JSONException e) {
                BranchLogger.e("Caught JSONException " + e.getMessage());
                return;
            }
        }
    }

    private void executeTimedBranchPostTask(ServerRequest serverRequest, final int i) {
        BranchLogger.v("executeTimedBranchPostTask " + serverRequest);
        if (serverRequest instanceof ServerRequestInitSession) {
            BranchLogger.v("callback to be returned " + ((ServerRequestInitSession) serverRequest).callback_);
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final BranchPostTask branchPostTask = new BranchPostTask(serverRequest, countDownLatch);
        branchPostTask.executeTask(new Void[0]);
        if (Looper.myLooper() == Looper.getMainLooper()) {
            new Thread(new Runnable() {
                public void run() {
                    ServerRequestQueue.this.awaitTimedBranchPostTask(countDownLatch, i, branchPostTask);
                }
            }).start();
        } else {
            awaitTimedBranchPostTask(countDownLatch, i, branchPostTask);
        }
    }

    /* access modifiers changed from: private */
    public void awaitTimedBranchPostTask(CountDownLatch countDownLatch, int i, BranchPostTask branchPostTask) {
        try {
            if (!countDownLatch.await((long) i, TimeUnit.MILLISECONDS)) {
                branchPostTask.cancel(true);
                branchPostTask.onPostExecuteInner(new ServerResponse(branchPostTask.thisReq_.getRequestPath(), -120, "", ""));
            }
        } catch (InterruptedException e) {
            BranchLogger.e("Caught InterruptedException " + e.getMessage());
            branchPostTask.cancel(true);
            branchPostTask.onPostExecuteInner(new ServerResponse(branchPostTask.thisReq_.getRequestPath(), -120, "", e.getMessage()));
        }
    }

    public void handleNewRequest(ServerRequest serverRequest) {
        BranchLogger.d("handleNewRequest " + serverRequest);
        if (!Branch.getInstance().getTrackingController().isTrackingDisabled() || serverRequest.prepareExecuteWithoutTracking()) {
            if (Branch.getInstance().initState_ != Branch.SESSION_STATE.INITIALISED && !(serverRequest instanceof ServerRequestInitSession) && requestNeedsSession(serverRequest)) {
                BranchLogger.d("handleNewRequest " + serverRequest + " needs a session");
                serverRequest.addProcessWaitLock(ServerRequest.PROCESS_WAIT_LOCK.SDK_INIT_WAIT_LOCK);
            }
            enqueue(serverRequest);
            serverRequest.onRequestQueued();
            processNextQueueItem("handleNewRequest");
            return;
        }
        BranchLogger.d("Requested operation cannot be completed since tracking is disabled [" + serverRequest.requestPath_.getPath() + "]");
        serverRequest.handleFailure(-117, "");
    }

    public boolean canClearInitData() {
        int i;
        synchronized (reqQueueLockObject) {
            int i2 = 0;
            i = 0;
            while (i2 < this.queue.size()) {
                try {
                    if (this.queue.get(i2) instanceof ServerRequestInitSession) {
                        i++;
                    }
                    i2++;
                } finally {
                }
            }
        }
        if (i <= 1) {
            return true;
        }
        return false;
    }

    private class BranchPostTask extends BranchAsyncTask {
        final CountDownLatch latch_;
        ServerRequest thisReq_;

        public BranchPostTask(ServerRequest serverRequest, CountDownLatch countDownLatch) {
            this.thisReq_ = serverRequest;
            this.latch_ = countDownLatch;
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            super.onPreExecute();
            this.thisReq_.onPreExecute();
            this.thisReq_.doFinalUpdateOnMainThread();
        }

        /* access modifiers changed from: protected */
        public ServerResponse doInBackground(Void... voidArr) {
            ServerResponse serverResponse;
            this.thisReq_.doFinalUpdateOnBackgroundThread();
            if (Branch.getInstance().getTrackingController().isTrackingDisabled() && !this.thisReq_.prepareExecuteWithoutTracking()) {
                return new ServerResponse(this.thisReq_.getRequestPath(), -117, "", "");
            }
            String branchKey = Branch.getInstance().prefHelper_.getBranchKey();
            if (this.thisReq_.isGetRequest()) {
                serverResponse = Branch.getInstance().getBranchRemoteInterface().make_restful_get(this.thisReq_.getRequestUrl(), this.thisReq_.getGetParams(), this.thisReq_.getRequestPath(), branchKey);
            } else {
                BranchLogger.v("Beginning rest post for " + this.thisReq_);
                serverResponse = Branch.getInstance().getBranchRemoteInterface().make_restful_post(this.thisReq_.getPostWithInstrumentationValues(ServerRequestQueue.this.instrumentationExtraData_), this.thisReq_.getRequestUrl(), this.thisReq_.getRequestPath(), branchKey);
            }
            CountDownLatch countDownLatch = this.latch_;
            if (countDownLatch != null) {
                countDownLatch.countDown();
            }
            return serverResponse;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(ServerResponse serverResponse) {
            super.onPostExecute(serverResponse);
            onPostExecuteInner(serverResponse);
        }

        /* access modifiers changed from: package-private */
        public void onPostExecuteInner(ServerResponse serverResponse) {
            BranchLogger.v("onPostExecuteInner " + this + " " + serverResponse);
            CountDownLatch countDownLatch = this.latch_;
            if (countDownLatch != null) {
                countDownLatch.countDown();
            }
            if (serverResponse == null) {
                this.thisReq_.handleFailure(-116, "Null response.");
                return;
            }
            int statusCode = serverResponse.getStatusCode();
            if (statusCode == 200) {
                onRequestSuccess(serverResponse);
            } else {
                onRequestFailed(serverResponse, statusCode);
            }
            ServerRequestQueue.this.networkCount_ = 0;
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    ServerRequestQueue.this.processNextQueueItem("onPostExecuteInner");
                }
            });
        }

        private void onRequestSuccess(ServerResponse serverResponse) {
            boolean z;
            BranchLogger.v("onRequestSuccess " + serverResponse);
            JSONObject object = serverResponse.getObject();
            if (object == null) {
                this.thisReq_.handleFailure(500, "Null response json.");
            }
            ServerRequest serverRequest = this.thisReq_;
            if ((serverRequest instanceof ServerRequestCreateUrl) && object != null) {
                try {
                    Branch.getInstance().linkCache_.put(((ServerRequestCreateUrl) serverRequest).getLinkPost(), object.getString("url"));
                } catch (JSONException e) {
                    BranchLogger.w("Caught JSONException " + e.getMessage());
                }
            }
            if (this.thisReq_ instanceof ServerRequestInitSession) {
                if (!Branch.getInstance().isTrackingDisabled() && object != null) {
                    try {
                        Defines$Jsonkey defines$Jsonkey = Defines$Jsonkey.SessionID;
                        boolean z2 = true;
                        if (object.has(defines$Jsonkey.getKey())) {
                            Branch.getInstance().prefHelper_.setSessionID(object.getString(defines$Jsonkey.getKey()));
                            z = true;
                        } else {
                            z = false;
                        }
                        Defines$Jsonkey defines$Jsonkey2 = Defines$Jsonkey.RandomizedBundleToken;
                        if (object.has(defines$Jsonkey2.getKey())) {
                            String string = object.getString(defines$Jsonkey2.getKey());
                            if (!Branch.getInstance().prefHelper_.getRandomizedBundleToken().equals(string)) {
                                Branch.getInstance().linkCache_.clear();
                                Branch.getInstance().prefHelper_.setRandomizedBundleToken(string);
                                z = true;
                            }
                        }
                        Defines$Jsonkey defines$Jsonkey3 = Defines$Jsonkey.RandomizedDeviceToken;
                        if (object.has(defines$Jsonkey3.getKey())) {
                            Branch.getInstance().prefHelper_.setRandomizedDeviceToken(object.getString(defines$Jsonkey3.getKey()));
                        } else {
                            z2 = z;
                        }
                        if (z2) {
                            ServerRequestQueue.this.updateAllRequestsInQueue();
                        }
                    } catch (JSONException e2) {
                        BranchLogger.w("Caught JSONException " + e2.getMessage());
                    }
                }
                if (this.thisReq_ instanceof ServerRequestInitSession) {
                    Branch.getInstance().setInitState(Branch.SESSION_STATE.INITIALISED);
                    Branch.getInstance().checkForAutoDeepLinkConfiguration();
                    if (Branch.getInstance().getLatestReferringParamsLatch != null) {
                        Branch.getInstance().getLatestReferringParamsLatch.countDown();
                    }
                    if (Branch.getInstance().getFirstReferringParamsLatch != null) {
                        Branch.getInstance().getFirstReferringParamsLatch.countDown();
                    }
                }
            }
            if (object != null) {
                this.thisReq_.onRequestSucceeded(serverResponse, Branch.getInstance());
                ServerRequestQueue.this.remove(this.thisReq_);
            } else if (this.thisReq_.shouldRetryOnFail()) {
                this.thisReq_.clearCallbacks();
            } else {
                ServerRequestQueue.this.remove(this.thisReq_);
            }
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:22:0x0097  */
        /* JADX WARNING: Removed duplicated region for block: B:23:0x009d  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onRequestFailed(io.branch.referral.ServerResponse r5, int r6) {
            /*
                r4 = this;
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r1 = "onRequestFailed "
                r0.append(r1)
                java.lang.String r1 = r5.getMessage()
                r0.append(r1)
                java.lang.String r0 = r0.toString()
                io.branch.referral.BranchLogger.v(r0)
                io.branch.referral.ServerRequest r0 = r4.thisReq_
                boolean r0 = r0 instanceof io.branch.referral.ServerRequestInitSession
                if (r0 == 0) goto L_0x0039
                io.branch.referral.Branch r0 = io.branch.referral.Branch.getInstance()
                io.branch.referral.PrefHelper r0 = r0.prefHelper_
                java.lang.String r0 = r0.getSessionParams()
                java.lang.String r1 = "bnc_no_value"
                boolean r0 = r1.equals(r0)
                if (r0 == 0) goto L_0x0039
                io.branch.referral.Branch r0 = io.branch.referral.Branch.getInstance()
                io.branch.referral.Branch$SESSION_STATE r1 = io.branch.referral.Branch.SESSION_STATE.UNINITIALISED
                r0.setInitState(r1)
            L_0x0039:
                r0 = 400(0x190, float:5.6E-43)
                if (r6 == r0) goto L_0x0041
                r1 = 409(0x199, float:5.73E-43)
                if (r6 != r1) goto L_0x004d
            L_0x0041:
                io.branch.referral.ServerRequest r1 = r4.thisReq_
                boolean r2 = r1 instanceof io.branch.referral.ServerRequestCreateUrl
                if (r2 == 0) goto L_0x004d
                io.branch.referral.ServerRequestCreateUrl r1 = (io.branch.referral.ServerRequestCreateUrl) r1
                r1.handleDuplicateURLError()
                goto L_0x0073
            L_0x004d:
                io.branch.referral.ServerRequestQueue r1 = io.branch.referral.ServerRequestQueue.this
                r2 = 0
                r1.networkCount_ = r2
                io.branch.referral.ServerRequest r1 = r4.thisReq_
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                java.lang.String r3 = r5.getFailReason()
                r2.append(r3)
                java.lang.String r3 = " "
                r2.append(r3)
                java.lang.String r5 = r5.getMessage()
                r2.append(r5)
                java.lang.String r5 = r2.toString()
                r1.handleFailure(r6, r5)
            L_0x0073:
                if (r0 > r6) goto L_0x0079
                r5 = 451(0x1c3, float:6.32E-43)
                if (r6 <= r5) goto L_0x009d
            L_0x0079:
                r5 = -117(0xffffffffffffff8b, float:NaN)
                if (r6 != r5) goto L_0x007e
                goto L_0x009d
            L_0x007e:
                io.branch.referral.ServerRequest r5 = r4.thisReq_
                boolean r5 = r5.shouldRetryOnFail()
                if (r5 == 0) goto L_0x009d
                io.branch.referral.ServerRequest r5 = r4.thisReq_
                int r5 = r5.currentRetryCount
                io.branch.referral.Branch r6 = io.branch.referral.Branch.getInstance()
                io.branch.referral.PrefHelper r6 = r6.prefHelper_
                int r6 = r6.getNoConnectionRetryMax()
                if (r5 < r6) goto L_0x0097
                goto L_0x009d
            L_0x0097:
                io.branch.referral.ServerRequest r5 = r4.thisReq_
                r5.clearCallbacks()
                goto L_0x00a8
            L_0x009d:
                io.branch.referral.Branch r5 = io.branch.referral.Branch.getInstance()
                io.branch.referral.ServerRequestQueue r5 = r5.requestQueue_
                io.branch.referral.ServerRequest r6 = r4.thisReq_
                r5.remove(r6)
            L_0x00a8:
                io.branch.referral.ServerRequest r5 = r4.thisReq_
                int r6 = r5.currentRetryCount
                int r6 = r6 + 1
                r5.currentRetryCount = r6
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.ServerRequestQueue.BranchPostTask.onRequestFailed(io.branch.referral.ServerResponse, int):void");
        }
    }

    public void addExtraInstrumentationData(String str, String str2) {
        this.instrumentationExtraData_.put(str, str2);
    }
}
