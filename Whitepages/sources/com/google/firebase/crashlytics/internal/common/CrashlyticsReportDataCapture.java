package com.google.firebase.crashlytics.internal.common;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import com.google.firebase.crashlytics.internal.ProcessDetailsProvider;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.crashlytics.internal.settings.SettingsProvider;
import com.google.firebase.crashlytics.internal.stacktrace.StackTraceTrimmingStrategy;
import com.google.firebase.crashlytics.internal.stacktrace.TrimmedThrowableData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class CrashlyticsReportDataCapture {
    private static final Map<String, Integer> ARCHITECTURES_BY_NAME;
    static final String GENERATOR = String.format(Locale.US, "Crashlytics Android SDK/%s", new Object[]{"19.4.2"});
    static final int GENERATOR_TYPE = 3;
    static final int REPORT_ANDROID_PLATFORM = 4;
    static final int SESSION_ANDROID_PLATFORM = 3;
    static final String SIGNAL_DEFAULT = "0";
    private final AppData appData;
    private final Context context;
    private final IdManager idManager;
    private final ProcessDetailsProvider processDetailsProvider = ProcessDetailsProvider.INSTANCE;
    private final SettingsProvider settingsProvider;
    private final StackTraceTrimmingStrategy stackTraceTrimmingStrategy;

    private static long ensureNonNegative(long j) {
        if (j > 0) {
            return j;
        }
        return 0;
    }

    static {
        HashMap hashMap = new HashMap();
        ARCHITECTURES_BY_NAME = hashMap;
        hashMap.put("armeabi", 5);
        hashMap.put("armeabi-v7a", 6);
        hashMap.put("arm64-v8a", 9);
        hashMap.put("x86", 0);
        hashMap.put("x86_64", 1);
    }

    public CrashlyticsReportDataCapture(Context context2, IdManager idManager2, AppData appData2, StackTraceTrimmingStrategy stackTraceTrimmingStrategy2, SettingsProvider settingsProvider2) {
        this.context = context2;
        this.idManager = idManager2;
        this.appData = appData2;
        this.stackTraceTrimmingStrategy = stackTraceTrimmingStrategy2;
        this.settingsProvider = settingsProvider2;
    }

    public CrashlyticsReport captureReportData(String str, long j) {
        return buildReportData().setSession(populateSessionData(str, j)).build();
    }

    public CrashlyticsReport.Session.Event captureEventData(Throwable th, Thread thread, String str, long j, int i, int i2, boolean z) {
        int i3 = this.context.getResources().getConfiguration().orientation;
        Throwable th2 = th;
        String str2 = str;
        long j2 = j;
        return CrashlyticsReport.Session.Event.builder().setType(str).setTimestamp(j).setApp(populateEventApplicationData(i3, TrimmedThrowableData.makeTrimmedThrowableData(th, this.stackTraceTrimmingStrategy), thread, i, i2, z)).setDevice(populateEventDeviceData(i3)).build();
    }

    public CrashlyticsReport.Session.Event captureAnrEventData(CrashlyticsReport.ApplicationExitInfo applicationExitInfo) {
        int i = this.context.getResources().getConfiguration().orientation;
        return CrashlyticsReport.Session.Event.builder().setType("anr").setTimestamp(applicationExitInfo.getTimestamp()).setApp(populateEventApplicationData(i, addBuildIdInfo(applicationExitInfo))).setDevice(populateEventDeviceData(i)).build();
    }

    private CrashlyticsReport.ApplicationExitInfo addBuildIdInfo(CrashlyticsReport.ApplicationExitInfo applicationExitInfo) {
        List list;
        if (!this.settingsProvider.getSettingsSync().featureFlagData.collectBuildIds || this.appData.buildIdInfoList.size() <= 0) {
            list = null;
        } else {
            ArrayList arrayList = new ArrayList();
            for (BuildIdInfo next : this.appData.buildIdInfoList) {
                arrayList.add(CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch.builder().setLibraryName(next.getLibraryName()).setArch(next.getArch()).setBuildId(next.getBuildId()).build());
            }
            list = Collections.unmodifiableList(arrayList);
        }
        return CrashlyticsReport.ApplicationExitInfo.builder().setImportance(applicationExitInfo.getImportance()).setProcessName(applicationExitInfo.getProcessName()).setReasonCode(applicationExitInfo.getReasonCode()).setTimestamp(applicationExitInfo.getTimestamp()).setPid(applicationExitInfo.getPid()).setPss(applicationExitInfo.getPss()).setRss(applicationExitInfo.getRss()).setTraceFile(applicationExitInfo.getTraceFile()).setBuildIdMappingForArch(list).build();
    }

    private CrashlyticsReport.Builder buildReportData() {
        return CrashlyticsReport.builder().setSdkVersion("19.4.2").setGmpAppId(this.appData.googleAppId).setInstallationUuid(this.idManager.getInstallIds().getCrashlyticsInstallId()).setFirebaseInstallationId(this.idManager.getInstallIds().getFirebaseInstallationId()).setFirebaseAuthenticationToken(this.idManager.getInstallIds().getFirebaseAuthenticationToken()).setBuildVersion(this.appData.versionCode).setDisplayVersion(this.appData.versionName).setPlatform(4);
    }

    private CrashlyticsReport.Session populateSessionData(String str, long j) {
        return CrashlyticsReport.Session.builder().setStartedAt(j).setIdentifier(str).setGenerator(GENERATOR).setApp(populateSessionApplicationData()).setOs(populateSessionOperatingSystemData()).setDevice(populateSessionDeviceData()).setGeneratorType(3).build();
    }

    private CrashlyticsReport.Session.Application populateSessionApplicationData() {
        return CrashlyticsReport.Session.Application.builder().setIdentifier(this.idManager.getAppIdentifier()).setVersion(this.appData.versionCode).setDisplayVersion(this.appData.versionName).setInstallationUuid(this.idManager.getInstallIds().getCrashlyticsInstallId()).setDevelopmentPlatform(this.appData.developmentPlatformProvider.getDevelopmentPlatform()).setDevelopmentPlatformVersion(this.appData.developmentPlatformProvider.getDevelopmentPlatformVersion()).build();
    }

    private CrashlyticsReport.Session.OperatingSystem populateSessionOperatingSystemData() {
        return CrashlyticsReport.Session.OperatingSystem.builder().setPlatform(3).setVersion(Build.VERSION.RELEASE).setBuildVersion(Build.VERSION.CODENAME).setJailbroken(CommonUtils.isRooted()).build();
    }

    private CrashlyticsReport.Session.Device populateSessionDeviceData() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        int deviceArchitecture = getDeviceArchitecture();
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        long calculateTotalRamInBytes = CommonUtils.calculateTotalRamInBytes(this.context);
        long blockCount = ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
        boolean isEmulator = CommonUtils.isEmulator();
        int deviceState = CommonUtils.getDeviceState();
        String str = Build.MANUFACTURER;
        return CrashlyticsReport.Session.Device.builder().setArch(deviceArchitecture).setModel(Build.MODEL).setCores(availableProcessors).setRam(calculateTotalRamInBytes).setDiskSpace(blockCount).setSimulator(isEmulator).setState(deviceState).setManufacturer(str).setModelClass(Build.PRODUCT).build();
    }

    private CrashlyticsReport.Session.Event.Application populateEventApplicationData(int i, TrimmedThrowableData trimmedThrowableData, Thread thread, int i2, int i3, boolean z) {
        Boolean bool;
        CrashlyticsReport.Session.Event.Application.ProcessDetails currentProcessDetails = this.processDetailsProvider.getCurrentProcessDetails(this.context);
        if (currentProcessDetails.getImportance() > 0) {
            bool = Boolean.valueOf(currentProcessDetails.getImportance() != 100);
        } else {
            bool = null;
        }
        return CrashlyticsReport.Session.Event.Application.builder().setBackground(bool).setCurrentProcessDetails(currentProcessDetails).setAppProcessDetails(this.processDetailsProvider.getAppProcessDetails(this.context)).setUiOrientation(i).setExecution(populateExecutionData(trimmedThrowableData, thread, i2, i3, z)).build();
    }

    private CrashlyticsReport.Session.Event.Application populateEventApplicationData(int i, CrashlyticsReport.ApplicationExitInfo applicationExitInfo) {
        return CrashlyticsReport.Session.Event.Application.builder().setBackground(Boolean.valueOf(applicationExitInfo.getImportance() != 100)).setCurrentProcessDetails(processDetailsFromApplicationExitInfo(applicationExitInfo)).setUiOrientation(i).setExecution(populateExecutionData(applicationExitInfo)).build();
    }

    private CrashlyticsReport.Session.Event.Device populateEventDeviceData(int i) {
        BatteryState batteryState = BatteryState.get(this.context);
        Float batteryLevel = batteryState.getBatteryLevel();
        Double valueOf = batteryLevel != null ? Double.valueOf(batteryLevel.doubleValue()) : null;
        int batteryVelocity = batteryState.getBatteryVelocity();
        boolean proximitySensorEnabled = CommonUtils.getProximitySensorEnabled(this.context);
        return CrashlyticsReport.Session.Event.Device.builder().setBatteryLevel(valueOf).setBatteryVelocity(batteryVelocity).setProximityOn(proximitySensorEnabled).setOrientation(i).setRamUsed(ensureNonNegative(CommonUtils.calculateTotalRamInBytes(this.context) - CommonUtils.calculateFreeRamInBytes(this.context))).setDiskUsed(CommonUtils.calculateUsedDiskSpaceInBytes(Environment.getDataDirectory().getPath())).build();
    }

    private CrashlyticsReport.Session.Event.Application.Execution populateExecutionData(TrimmedThrowableData trimmedThrowableData, Thread thread, int i, int i2, boolean z) {
        return CrashlyticsReport.Session.Event.Application.Execution.builder().setThreads(populateThreadsList(trimmedThrowableData, thread, i, z)).setException(populateExceptionData(trimmedThrowableData, i, i2)).setSignal(populateSignalData()).setBinaries(populateBinaryImagesList()).build();
    }

    private CrashlyticsReport.Session.Event.Application.Execution populateExecutionData(CrashlyticsReport.ApplicationExitInfo applicationExitInfo) {
        return CrashlyticsReport.Session.Event.Application.Execution.builder().setAppExitInfo(applicationExitInfo).setSignal(populateSignalData()).setBinaries(populateBinaryImagesList()).build();
    }

    private List<CrashlyticsReport.Session.Event.Application.Execution.Thread> populateThreadsList(TrimmedThrowableData trimmedThrowableData, Thread thread, int i, boolean z) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(populateThreadData(thread, trimmedThrowableData.stacktrace, i));
        if (z) {
            for (Map.Entry next : Thread.getAllStackTraces().entrySet()) {
                Thread thread2 = (Thread) next.getKey();
                if (!thread2.equals(thread)) {
                    arrayList.add(populateThreadData(thread2, this.stackTraceTrimmingStrategy.getTrimmedStackTrace((StackTraceElement[]) next.getValue())));
                }
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    private CrashlyticsReport.Session.Event.Application.Execution.Thread populateThreadData(Thread thread, StackTraceElement[] stackTraceElementArr) {
        return populateThreadData(thread, stackTraceElementArr, 0);
    }

    private CrashlyticsReport.Session.Event.Application.Execution.Thread populateThreadData(Thread thread, StackTraceElement[] stackTraceElementArr, int i) {
        return CrashlyticsReport.Session.Event.Application.Execution.Thread.builder().setName(thread.getName()).setImportance(i).setFrames(populateFramesList(stackTraceElementArr, i)).build();
    }

    private List<CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame> populateFramesList(StackTraceElement[] stackTraceElementArr, int i) {
        ArrayList arrayList = new ArrayList();
        for (StackTraceElement populateFrameData : stackTraceElementArr) {
            arrayList.add(populateFrameData(populateFrameData, CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame.builder().setImportance(i)));
        }
        return Collections.unmodifiableList(arrayList);
    }

    private CrashlyticsReport.Session.Event.Application.Execution.Exception populateExceptionData(TrimmedThrowableData trimmedThrowableData, int i, int i2) {
        return populateExceptionData(trimmedThrowableData, i, i2, 0);
    }

    private CrashlyticsReport.Session.Event.Application.Execution.Exception populateExceptionData(TrimmedThrowableData trimmedThrowableData, int i, int i2, int i3) {
        String str = trimmedThrowableData.className;
        String str2 = trimmedThrowableData.localizedMessage;
        StackTraceElement[] stackTraceElementArr = trimmedThrowableData.stacktrace;
        int i4 = 0;
        if (stackTraceElementArr == null) {
            stackTraceElementArr = new StackTraceElement[0];
        }
        TrimmedThrowableData trimmedThrowableData2 = trimmedThrowableData.cause;
        if (i3 >= i2) {
            TrimmedThrowableData trimmedThrowableData3 = trimmedThrowableData2;
            while (trimmedThrowableData3 != null) {
                trimmedThrowableData3 = trimmedThrowableData3.cause;
                i4++;
            }
        }
        CrashlyticsReport.Session.Event.Application.Execution.Exception.Builder overflowCount = CrashlyticsReport.Session.Event.Application.Execution.Exception.builder().setType(str).setReason(str2).setFrames(populateFramesList(stackTraceElementArr, i)).setOverflowCount(i4);
        if (trimmedThrowableData2 != null && i4 == 0) {
            overflowCount.setCausedBy(populateExceptionData(trimmedThrowableData2, i, i2, i3 + 1));
        }
        return overflowCount.build();
    }

    private CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame populateFrameData(StackTraceElement stackTraceElement, CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame.Builder builder) {
        long j = 0;
        long max = stackTraceElement.isNativeMethod() ? Math.max((long) stackTraceElement.getLineNumber(), 0) : 0;
        String str = stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName();
        String fileName = stackTraceElement.getFileName();
        if (!stackTraceElement.isNativeMethod() && stackTraceElement.getLineNumber() > 0) {
            j = (long) stackTraceElement.getLineNumber();
        }
        return builder.setPc(max).setSymbol(str).setFile(fileName).setOffset(j).build();
    }

    private List<CrashlyticsReport.Session.Event.Application.Execution.BinaryImage> populateBinaryImagesList() {
        return Collections.singletonList(populateBinaryImageData());
    }

    private CrashlyticsReport.Session.Event.Application.Execution.BinaryImage populateBinaryImageData() {
        return CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.builder().setBaseAddress(0).setSize(0).setName(this.appData.packageName).setUuid(this.appData.buildId).build();
    }

    private CrashlyticsReport.Session.Event.Application.Execution.Signal populateSignalData() {
        return CrashlyticsReport.Session.Event.Application.Execution.Signal.builder().setName(SIGNAL_DEFAULT).setCode(SIGNAL_DEFAULT).setAddress(0).build();
    }

    private static int getDeviceArchitecture() {
        Integer num;
        String str = Build.CPU_ABI;
        if (!TextUtils.isEmpty(str) && (num = ARCHITECTURES_BY_NAME.get(str.toLowerCase(Locale.US))) != null) {
            return num.intValue();
        }
        return 7;
    }

    private CrashlyticsReport.Session.Event.Application.ProcessDetails processDetailsFromApplicationExitInfo(CrashlyticsReport.ApplicationExitInfo applicationExitInfo) {
        return this.processDetailsProvider.buildProcessDetails(applicationExitInfo.getProcessName(), applicationExitInfo.getPid(), applicationExitInfo.getImportance());
    }
}
