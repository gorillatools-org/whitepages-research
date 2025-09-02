package com.google.firebase.sessions;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.config.Configurator;
import com.google.firebase.encoders.config.EncoderConfig;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.io.IOException;

public final class AutoSessionEventEncoder implements Configurator {
    public static final int CODEGEN_VERSION = 2;
    public static final Configurator CONFIG = new AutoSessionEventEncoder();

    private AutoSessionEventEncoder() {
    }

    public void configure(EncoderConfig<?> encoderConfig) {
        encoderConfig.registerEncoder((Class<U>) SessionEvent.class, (ObjectEncoder<? super U>) SessionEventEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) SessionInfo.class, (ObjectEncoder<? super U>) SessionInfoEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) DataCollectionStatus.class, (ObjectEncoder<? super U>) DataCollectionStatusEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) ApplicationInfo.class, (ObjectEncoder<? super U>) ApplicationInfoEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) AndroidApplicationInfo.class, (ObjectEncoder<? super U>) AndroidApplicationInfoEncoder.INSTANCE);
        encoderConfig.registerEncoder((Class<U>) ProcessDetails.class, (ObjectEncoder<? super U>) ProcessDetailsEncoder.INSTANCE);
    }

    private static final class SessionEventEncoder implements ObjectEncoder<SessionEvent> {
        private static final FieldDescriptor APPLICATIONINFO_DESCRIPTOR = FieldDescriptor.of("applicationInfo");
        private static final FieldDescriptor EVENTTYPE_DESCRIPTOR = FieldDescriptor.of("eventType");
        static final SessionEventEncoder INSTANCE = new SessionEventEncoder();
        private static final FieldDescriptor SESSIONDATA_DESCRIPTOR = FieldDescriptor.of("sessionData");

        private SessionEventEncoder() {
        }

        public void encode(SessionEvent sessionEvent, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(EVENTTYPE_DESCRIPTOR, (Object) sessionEvent.getEventType());
            objectEncoderContext.add(SESSIONDATA_DESCRIPTOR, (Object) sessionEvent.getSessionData());
            objectEncoderContext.add(APPLICATIONINFO_DESCRIPTOR, (Object) sessionEvent.getApplicationInfo());
        }
    }

    private static final class SessionInfoEncoder implements ObjectEncoder<SessionInfo> {
        private static final FieldDescriptor DATACOLLECTIONSTATUS_DESCRIPTOR = FieldDescriptor.of("dataCollectionStatus");
        private static final FieldDescriptor EVENTTIMESTAMPUS_DESCRIPTOR = FieldDescriptor.of("eventTimestampUs");
        private static final FieldDescriptor FIREBASEAUTHENTICATIONTOKEN_DESCRIPTOR = FieldDescriptor.of("firebaseAuthenticationToken");
        private static final FieldDescriptor FIREBASEINSTALLATIONID_DESCRIPTOR = FieldDescriptor.of("firebaseInstallationId");
        private static final FieldDescriptor FIRSTSESSIONID_DESCRIPTOR = FieldDescriptor.of("firstSessionId");
        static final SessionInfoEncoder INSTANCE = new SessionInfoEncoder();
        private static final FieldDescriptor SESSIONID_DESCRIPTOR = FieldDescriptor.of("sessionId");
        private static final FieldDescriptor SESSIONINDEX_DESCRIPTOR = FieldDescriptor.of("sessionIndex");

        private SessionInfoEncoder() {
        }

        public void encode(SessionInfo sessionInfo, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(SESSIONID_DESCRIPTOR, (Object) sessionInfo.getSessionId());
            objectEncoderContext.add(FIRSTSESSIONID_DESCRIPTOR, (Object) sessionInfo.getFirstSessionId());
            objectEncoderContext.add(SESSIONINDEX_DESCRIPTOR, sessionInfo.getSessionIndex());
            objectEncoderContext.add(EVENTTIMESTAMPUS_DESCRIPTOR, sessionInfo.getEventTimestampUs());
            objectEncoderContext.add(DATACOLLECTIONSTATUS_DESCRIPTOR, (Object) sessionInfo.getDataCollectionStatus());
            objectEncoderContext.add(FIREBASEINSTALLATIONID_DESCRIPTOR, (Object) sessionInfo.getFirebaseInstallationId());
            objectEncoderContext.add(FIREBASEAUTHENTICATIONTOKEN_DESCRIPTOR, (Object) sessionInfo.getFirebaseAuthenticationToken());
        }
    }

    private static final class DataCollectionStatusEncoder implements ObjectEncoder<DataCollectionStatus> {
        private static final FieldDescriptor CRASHLYTICS_DESCRIPTOR = FieldDescriptor.of("crashlytics");
        static final DataCollectionStatusEncoder INSTANCE = new DataCollectionStatusEncoder();
        private static final FieldDescriptor PERFORMANCE_DESCRIPTOR = FieldDescriptor.of("performance");
        private static final FieldDescriptor SESSIONSAMPLINGRATE_DESCRIPTOR = FieldDescriptor.of("sessionSamplingRate");

        private DataCollectionStatusEncoder() {
        }

        public void encode(DataCollectionStatus dataCollectionStatus, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(PERFORMANCE_DESCRIPTOR, (Object) dataCollectionStatus.getPerformance());
            objectEncoderContext.add(CRASHLYTICS_DESCRIPTOR, (Object) dataCollectionStatus.getCrashlytics());
            objectEncoderContext.add(SESSIONSAMPLINGRATE_DESCRIPTOR, dataCollectionStatus.getSessionSamplingRate());
        }
    }

    private static final class ApplicationInfoEncoder implements ObjectEncoder<ApplicationInfo> {
        private static final FieldDescriptor ANDROIDAPPINFO_DESCRIPTOR = FieldDescriptor.of("androidAppInfo");
        private static final FieldDescriptor APPID_DESCRIPTOR = FieldDescriptor.of(RemoteConfigConstants.RequestFieldKey.APP_ID);
        private static final FieldDescriptor DEVICEMODEL_DESCRIPTOR = FieldDescriptor.of("deviceModel");
        static final ApplicationInfoEncoder INSTANCE = new ApplicationInfoEncoder();
        private static final FieldDescriptor LOGENVIRONMENT_DESCRIPTOR = FieldDescriptor.of("logEnvironment");
        private static final FieldDescriptor OSVERSION_DESCRIPTOR = FieldDescriptor.of("osVersion");
        private static final FieldDescriptor SESSIONSDKVERSION_DESCRIPTOR = FieldDescriptor.of("sessionSdkVersion");

        private ApplicationInfoEncoder() {
        }

        public void encode(ApplicationInfo applicationInfo, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(APPID_DESCRIPTOR, (Object) applicationInfo.getAppId());
            objectEncoderContext.add(DEVICEMODEL_DESCRIPTOR, (Object) applicationInfo.getDeviceModel());
            objectEncoderContext.add(SESSIONSDKVERSION_DESCRIPTOR, (Object) applicationInfo.getSessionSdkVersion());
            objectEncoderContext.add(OSVERSION_DESCRIPTOR, (Object) applicationInfo.getOsVersion());
            objectEncoderContext.add(LOGENVIRONMENT_DESCRIPTOR, (Object) applicationInfo.getLogEnvironment());
            objectEncoderContext.add(ANDROIDAPPINFO_DESCRIPTOR, (Object) applicationInfo.getAndroidAppInfo());
        }
    }

    private static final class AndroidApplicationInfoEncoder implements ObjectEncoder<AndroidApplicationInfo> {
        private static final FieldDescriptor APPBUILDVERSION_DESCRIPTOR = FieldDescriptor.of("appBuildVersion");
        private static final FieldDescriptor APPPROCESSDETAILS_DESCRIPTOR = FieldDescriptor.of("appProcessDetails");
        private static final FieldDescriptor CURRENTPROCESSDETAILS_DESCRIPTOR = FieldDescriptor.of("currentProcessDetails");
        private static final FieldDescriptor DEVICEMANUFACTURER_DESCRIPTOR = FieldDescriptor.of("deviceManufacturer");
        static final AndroidApplicationInfoEncoder INSTANCE = new AndroidApplicationInfoEncoder();
        private static final FieldDescriptor PACKAGENAME_DESCRIPTOR = FieldDescriptor.of(RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME);
        private static final FieldDescriptor VERSIONNAME_DESCRIPTOR = FieldDescriptor.of("versionName");

        private AndroidApplicationInfoEncoder() {
        }

        public void encode(AndroidApplicationInfo androidApplicationInfo, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(PACKAGENAME_DESCRIPTOR, (Object) androidApplicationInfo.getPackageName());
            objectEncoderContext.add(VERSIONNAME_DESCRIPTOR, (Object) androidApplicationInfo.getVersionName());
            objectEncoderContext.add(APPBUILDVERSION_DESCRIPTOR, (Object) androidApplicationInfo.getAppBuildVersion());
            objectEncoderContext.add(DEVICEMANUFACTURER_DESCRIPTOR, (Object) androidApplicationInfo.getDeviceManufacturer());
            objectEncoderContext.add(CURRENTPROCESSDETAILS_DESCRIPTOR, (Object) androidApplicationInfo.getCurrentProcessDetails());
            objectEncoderContext.add(APPPROCESSDETAILS_DESCRIPTOR, (Object) androidApplicationInfo.getAppProcessDetails());
        }
    }

    private static final class ProcessDetailsEncoder implements ObjectEncoder<ProcessDetails> {
        private static final FieldDescriptor DEFAULTPROCESS_DESCRIPTOR = FieldDescriptor.of("defaultProcess");
        private static final FieldDescriptor IMPORTANCE_DESCRIPTOR = FieldDescriptor.of("importance");
        static final ProcessDetailsEncoder INSTANCE = new ProcessDetailsEncoder();
        private static final FieldDescriptor PID_DESCRIPTOR = FieldDescriptor.of("pid");
        private static final FieldDescriptor PROCESSNAME_DESCRIPTOR = FieldDescriptor.of("processName");

        private ProcessDetailsEncoder() {
        }

        public void encode(ProcessDetails processDetails, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(PROCESSNAME_DESCRIPTOR, (Object) processDetails.getProcessName());
            objectEncoderContext.add(PID_DESCRIPTOR, processDetails.getPid());
            objectEncoderContext.add(IMPORTANCE_DESCRIPTOR, processDetails.getImportance());
            objectEncoderContext.add(DEFAULTPROCESS_DESCRIPTOR, processDetails.isDefaultProcess());
        }
    }
}
