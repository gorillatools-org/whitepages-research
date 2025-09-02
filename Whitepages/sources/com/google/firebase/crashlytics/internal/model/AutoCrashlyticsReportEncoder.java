package com.google.firebase.crashlytics.internal.model;

import com.facebook.react.modules.appstate.AppStateModule;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.config.Configurator;
import com.google.firebase.encoders.config.EncoderConfig;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.google.firebase.remoteconfig.internal.ConfigContainer;
import com.salesforce.marketingcloud.storage.db.k;
import java.io.IOException;

public final class AutoCrashlyticsReportEncoder implements Configurator {
    public static final int CODEGEN_VERSION = 2;
    public static final Configurator CONFIG = new AutoCrashlyticsReportEncoder();

    private AutoCrashlyticsReportEncoder() {
    }

    public void configure(EncoderConfig<?> encoderConfig) {
        CrashlyticsReportEncoder crashlyticsReportEncoder = CrashlyticsReportEncoder.INSTANCE;
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.class, (ObjectEncoder<? super U>) crashlyticsReportEncoder);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport.class, (ObjectEncoder<? super U>) crashlyticsReportEncoder);
        CrashlyticsReportSessionEncoder crashlyticsReportSessionEncoder = CrashlyticsReportSessionEncoder.INSTANCE;
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.Session.class, (ObjectEncoder<? super U>) crashlyticsReportSessionEncoder);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport_Session.class, (ObjectEncoder<? super U>) crashlyticsReportSessionEncoder);
        CrashlyticsReportSessionApplicationEncoder crashlyticsReportSessionApplicationEncoder = CrashlyticsReportSessionApplicationEncoder.INSTANCE;
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.Session.Application.class, (ObjectEncoder<? super U>) crashlyticsReportSessionApplicationEncoder);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport_Session_Application.class, (ObjectEncoder<? super U>) crashlyticsReportSessionApplicationEncoder);
        CrashlyticsReportSessionApplicationOrganizationEncoder crashlyticsReportSessionApplicationOrganizationEncoder = CrashlyticsReportSessionApplicationOrganizationEncoder.INSTANCE;
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.Session.Application.Organization.class, (ObjectEncoder<? super U>) crashlyticsReportSessionApplicationOrganizationEncoder);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport_Session_Application_Organization.class, (ObjectEncoder<? super U>) crashlyticsReportSessionApplicationOrganizationEncoder);
        CrashlyticsReportSessionUserEncoder crashlyticsReportSessionUserEncoder = CrashlyticsReportSessionUserEncoder.INSTANCE;
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.Session.User.class, (ObjectEncoder<? super U>) crashlyticsReportSessionUserEncoder);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport_Session_User.class, (ObjectEncoder<? super U>) crashlyticsReportSessionUserEncoder);
        CrashlyticsReportSessionOperatingSystemEncoder crashlyticsReportSessionOperatingSystemEncoder = CrashlyticsReportSessionOperatingSystemEncoder.INSTANCE;
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.Session.OperatingSystem.class, (ObjectEncoder<? super U>) crashlyticsReportSessionOperatingSystemEncoder);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport_Session_OperatingSystem.class, (ObjectEncoder<? super U>) crashlyticsReportSessionOperatingSystemEncoder);
        CrashlyticsReportSessionDeviceEncoder crashlyticsReportSessionDeviceEncoder = CrashlyticsReportSessionDeviceEncoder.INSTANCE;
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.Session.Device.class, (ObjectEncoder<? super U>) crashlyticsReportSessionDeviceEncoder);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport_Session_Device.class, (ObjectEncoder<? super U>) crashlyticsReportSessionDeviceEncoder);
        CrashlyticsReportSessionEventEncoder crashlyticsReportSessionEventEncoder = CrashlyticsReportSessionEventEncoder.INSTANCE;
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.Session.Event.class, (ObjectEncoder<? super U>) crashlyticsReportSessionEventEncoder);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport_Session_Event.class, (ObjectEncoder<? super U>) crashlyticsReportSessionEventEncoder);
        CrashlyticsReportSessionEventApplicationEncoder crashlyticsReportSessionEventApplicationEncoder = CrashlyticsReportSessionEventApplicationEncoder.INSTANCE;
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.Session.Event.Application.class, (ObjectEncoder<? super U>) crashlyticsReportSessionEventApplicationEncoder);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport_Session_Event_Application.class, (ObjectEncoder<? super U>) crashlyticsReportSessionEventApplicationEncoder);
        CrashlyticsReportSessionEventApplicationExecutionEncoder crashlyticsReportSessionEventApplicationExecutionEncoder = CrashlyticsReportSessionEventApplicationExecutionEncoder.INSTANCE;
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.Session.Event.Application.Execution.class, (ObjectEncoder<? super U>) crashlyticsReportSessionEventApplicationExecutionEncoder);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport_Session_Event_Application_Execution.class, (ObjectEncoder<? super U>) crashlyticsReportSessionEventApplicationExecutionEncoder);
        CrashlyticsReportSessionEventApplicationExecutionThreadEncoder crashlyticsReportSessionEventApplicationExecutionThreadEncoder = CrashlyticsReportSessionEventApplicationExecutionThreadEncoder.INSTANCE;
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.Session.Event.Application.Execution.Thread.class, (ObjectEncoder<? super U>) crashlyticsReportSessionEventApplicationExecutionThreadEncoder);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Thread.class, (ObjectEncoder<? super U>) crashlyticsReportSessionEventApplicationExecutionThreadEncoder);
        CrashlyticsReportSessionEventApplicationExecutionThreadFrameEncoder crashlyticsReportSessionEventApplicationExecutionThreadFrameEncoder = CrashlyticsReportSessionEventApplicationExecutionThreadFrameEncoder.INSTANCE;
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame.class, (ObjectEncoder<? super U>) crashlyticsReportSessionEventApplicationExecutionThreadFrameEncoder);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Thread_Frame.class, (ObjectEncoder<? super U>) crashlyticsReportSessionEventApplicationExecutionThreadFrameEncoder);
        CrashlyticsReportSessionEventApplicationExecutionExceptionEncoder crashlyticsReportSessionEventApplicationExecutionExceptionEncoder = CrashlyticsReportSessionEventApplicationExecutionExceptionEncoder.INSTANCE;
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.Session.Event.Application.Execution.Exception.class, (ObjectEncoder<? super U>) crashlyticsReportSessionEventApplicationExecutionExceptionEncoder);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Exception.class, (ObjectEncoder<? super U>) crashlyticsReportSessionEventApplicationExecutionExceptionEncoder);
        CrashlyticsReportApplicationExitInfoEncoder crashlyticsReportApplicationExitInfoEncoder = CrashlyticsReportApplicationExitInfoEncoder.INSTANCE;
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.ApplicationExitInfo.class, (ObjectEncoder<? super U>) crashlyticsReportApplicationExitInfoEncoder);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport_ApplicationExitInfo.class, (ObjectEncoder<? super U>) crashlyticsReportApplicationExitInfoEncoder);
        CrashlyticsReportApplicationExitInfoBuildIdMappingForArchEncoder crashlyticsReportApplicationExitInfoBuildIdMappingForArchEncoder = CrashlyticsReportApplicationExitInfoBuildIdMappingForArchEncoder.INSTANCE;
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch.class, (ObjectEncoder<? super U>) crashlyticsReportApplicationExitInfoBuildIdMappingForArchEncoder);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport_ApplicationExitInfo_BuildIdMappingForArch.class, (ObjectEncoder<? super U>) crashlyticsReportApplicationExitInfoBuildIdMappingForArchEncoder);
        CrashlyticsReportSessionEventApplicationExecutionSignalEncoder crashlyticsReportSessionEventApplicationExecutionSignalEncoder = CrashlyticsReportSessionEventApplicationExecutionSignalEncoder.INSTANCE;
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.Session.Event.Application.Execution.Signal.class, (ObjectEncoder<? super U>) crashlyticsReportSessionEventApplicationExecutionSignalEncoder);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Signal.class, (ObjectEncoder<? super U>) crashlyticsReportSessionEventApplicationExecutionSignalEncoder);
        CrashlyticsReportSessionEventApplicationExecutionBinaryImageEncoder crashlyticsReportSessionEventApplicationExecutionBinaryImageEncoder = CrashlyticsReportSessionEventApplicationExecutionBinaryImageEncoder.INSTANCE;
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.class, (ObjectEncoder<? super U>) crashlyticsReportSessionEventApplicationExecutionBinaryImageEncoder);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport_Session_Event_Application_Execution_BinaryImage.class, (ObjectEncoder<? super U>) crashlyticsReportSessionEventApplicationExecutionBinaryImageEncoder);
        CrashlyticsReportCustomAttributeEncoder crashlyticsReportCustomAttributeEncoder = CrashlyticsReportCustomAttributeEncoder.INSTANCE;
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.CustomAttribute.class, (ObjectEncoder<? super U>) crashlyticsReportCustomAttributeEncoder);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport_CustomAttribute.class, (ObjectEncoder<? super U>) crashlyticsReportCustomAttributeEncoder);
        CrashlyticsReportSessionEventApplicationProcessDetailsEncoder crashlyticsReportSessionEventApplicationProcessDetailsEncoder = CrashlyticsReportSessionEventApplicationProcessDetailsEncoder.INSTANCE;
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.Session.Event.Application.ProcessDetails.class, (ObjectEncoder<? super U>) crashlyticsReportSessionEventApplicationProcessDetailsEncoder);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport_Session_Event_Application_ProcessDetails.class, (ObjectEncoder<? super U>) crashlyticsReportSessionEventApplicationProcessDetailsEncoder);
        CrashlyticsReportSessionEventDeviceEncoder crashlyticsReportSessionEventDeviceEncoder = CrashlyticsReportSessionEventDeviceEncoder.INSTANCE;
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.Session.Event.Device.class, (ObjectEncoder<? super U>) crashlyticsReportSessionEventDeviceEncoder);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport_Session_Event_Device.class, (ObjectEncoder<? super U>) crashlyticsReportSessionEventDeviceEncoder);
        CrashlyticsReportSessionEventLogEncoder crashlyticsReportSessionEventLogEncoder = CrashlyticsReportSessionEventLogEncoder.INSTANCE;
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.Session.Event.Log.class, (ObjectEncoder<? super U>) crashlyticsReportSessionEventLogEncoder);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport_Session_Event_Log.class, (ObjectEncoder<? super U>) crashlyticsReportSessionEventLogEncoder);
        CrashlyticsReportSessionEventRolloutsStateEncoder crashlyticsReportSessionEventRolloutsStateEncoder = CrashlyticsReportSessionEventRolloutsStateEncoder.INSTANCE;
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.Session.Event.RolloutsState.class, (ObjectEncoder<? super U>) crashlyticsReportSessionEventRolloutsStateEncoder);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport_Session_Event_RolloutsState.class, (ObjectEncoder<? super U>) crashlyticsReportSessionEventRolloutsStateEncoder);
        CrashlyticsReportSessionEventRolloutAssignmentEncoder crashlyticsReportSessionEventRolloutAssignmentEncoder = CrashlyticsReportSessionEventRolloutAssignmentEncoder.INSTANCE;
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.Session.Event.RolloutAssignment.class, (ObjectEncoder<? super U>) crashlyticsReportSessionEventRolloutAssignmentEncoder);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport_Session_Event_RolloutAssignment.class, (ObjectEncoder<? super U>) crashlyticsReportSessionEventRolloutAssignmentEncoder);
        CrashlyticsReportSessionEventRolloutAssignmentRolloutVariantEncoder crashlyticsReportSessionEventRolloutAssignmentRolloutVariantEncoder = CrashlyticsReportSessionEventRolloutAssignmentRolloutVariantEncoder.INSTANCE;
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant.class, (ObjectEncoder<? super U>) crashlyticsReportSessionEventRolloutAssignmentRolloutVariantEncoder);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport_Session_Event_RolloutAssignment_RolloutVariant.class, (ObjectEncoder<? super U>) crashlyticsReportSessionEventRolloutAssignmentRolloutVariantEncoder);
        CrashlyticsReportFilesPayloadEncoder crashlyticsReportFilesPayloadEncoder = CrashlyticsReportFilesPayloadEncoder.INSTANCE;
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.FilesPayload.class, (ObjectEncoder<? super U>) crashlyticsReportFilesPayloadEncoder);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport_FilesPayload.class, (ObjectEncoder<? super U>) crashlyticsReportFilesPayloadEncoder);
        CrashlyticsReportFilesPayloadFileEncoder crashlyticsReportFilesPayloadFileEncoder = CrashlyticsReportFilesPayloadFileEncoder.INSTANCE;
        encoderConfig.registerEncoder((Class<U>) CrashlyticsReport.FilesPayload.File.class, (ObjectEncoder<? super U>) crashlyticsReportFilesPayloadFileEncoder);
        encoderConfig.registerEncoder((Class<U>) AutoValue_CrashlyticsReport_FilesPayload_File.class, (ObjectEncoder<? super U>) crashlyticsReportFilesPayloadFileEncoder);
    }

    private static final class CrashlyticsReportEncoder implements ObjectEncoder<CrashlyticsReport> {
        private static final FieldDescriptor APPEXITINFO_DESCRIPTOR = FieldDescriptor.of("appExitInfo");
        private static final FieldDescriptor APPQUALITYSESSIONID_DESCRIPTOR = FieldDescriptor.of("appQualitySessionId");
        private static final FieldDescriptor BUILDVERSION_DESCRIPTOR = FieldDescriptor.of("buildVersion");
        private static final FieldDescriptor DISPLAYVERSION_DESCRIPTOR = FieldDescriptor.of("displayVersion");
        private static final FieldDescriptor FIREBASEAUTHENTICATIONTOKEN_DESCRIPTOR = FieldDescriptor.of("firebaseAuthenticationToken");
        private static final FieldDescriptor FIREBASEINSTALLATIONID_DESCRIPTOR = FieldDescriptor.of("firebaseInstallationId");
        private static final FieldDescriptor GMPAPPID_DESCRIPTOR = FieldDescriptor.of("gmpAppId");
        private static final FieldDescriptor INSTALLATIONUUID_DESCRIPTOR = FieldDescriptor.of("installationUuid");
        static final CrashlyticsReportEncoder INSTANCE = new CrashlyticsReportEncoder();
        private static final FieldDescriptor NDKPAYLOAD_DESCRIPTOR = FieldDescriptor.of("ndkPayload");
        private static final FieldDescriptor PLATFORM_DESCRIPTOR = FieldDescriptor.of(k.a.b);
        private static final FieldDescriptor SDKVERSION_DESCRIPTOR = FieldDescriptor.of(RemoteConfigConstants.RequestFieldKey.SDK_VERSION);
        private static final FieldDescriptor SESSION_DESCRIPTOR = FieldDescriptor.of("session");

        private CrashlyticsReportEncoder() {
        }

        public void encode(CrashlyticsReport crashlyticsReport, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(SDKVERSION_DESCRIPTOR, (Object) crashlyticsReport.getSdkVersion());
            objectEncoderContext.add(GMPAPPID_DESCRIPTOR, (Object) crashlyticsReport.getGmpAppId());
            objectEncoderContext.add(PLATFORM_DESCRIPTOR, crashlyticsReport.getPlatform());
            objectEncoderContext.add(INSTALLATIONUUID_DESCRIPTOR, (Object) crashlyticsReport.getInstallationUuid());
            objectEncoderContext.add(FIREBASEINSTALLATIONID_DESCRIPTOR, (Object) crashlyticsReport.getFirebaseInstallationId());
            objectEncoderContext.add(FIREBASEAUTHENTICATIONTOKEN_DESCRIPTOR, (Object) crashlyticsReport.getFirebaseAuthenticationToken());
            objectEncoderContext.add(APPQUALITYSESSIONID_DESCRIPTOR, (Object) crashlyticsReport.getAppQualitySessionId());
            objectEncoderContext.add(BUILDVERSION_DESCRIPTOR, (Object) crashlyticsReport.getBuildVersion());
            objectEncoderContext.add(DISPLAYVERSION_DESCRIPTOR, (Object) crashlyticsReport.getDisplayVersion());
            objectEncoderContext.add(SESSION_DESCRIPTOR, (Object) crashlyticsReport.getSession());
            objectEncoderContext.add(NDKPAYLOAD_DESCRIPTOR, (Object) crashlyticsReport.getNdkPayload());
            objectEncoderContext.add(APPEXITINFO_DESCRIPTOR, (Object) crashlyticsReport.getAppExitInfo());
        }
    }

    private static final class CrashlyticsReportSessionEncoder implements ObjectEncoder<CrashlyticsReport.Session> {
        private static final FieldDescriptor APPQUALITYSESSIONID_DESCRIPTOR = FieldDescriptor.of("appQualitySessionId");
        private static final FieldDescriptor APP_DESCRIPTOR = FieldDescriptor.of("app");
        private static final FieldDescriptor CRASHED_DESCRIPTOR = FieldDescriptor.of("crashed");
        private static final FieldDescriptor DEVICE_DESCRIPTOR = FieldDescriptor.of("device");
        private static final FieldDescriptor ENDEDAT_DESCRIPTOR = FieldDescriptor.of("endedAt");
        private static final FieldDescriptor EVENTS_DESCRIPTOR = FieldDescriptor.of("events");
        private static final FieldDescriptor GENERATORTYPE_DESCRIPTOR = FieldDescriptor.of("generatorType");
        private static final FieldDescriptor GENERATOR_DESCRIPTOR = FieldDescriptor.of("generator");
        private static final FieldDescriptor IDENTIFIER_DESCRIPTOR = FieldDescriptor.of("identifier");
        static final CrashlyticsReportSessionEncoder INSTANCE = new CrashlyticsReportSessionEncoder();
        private static final FieldDescriptor OS_DESCRIPTOR = FieldDescriptor.of("os");
        private static final FieldDescriptor STARTEDAT_DESCRIPTOR = FieldDescriptor.of("startedAt");
        private static final FieldDescriptor USER_DESCRIPTOR = FieldDescriptor.of("user");

        private CrashlyticsReportSessionEncoder() {
        }

        public void encode(CrashlyticsReport.Session session, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(GENERATOR_DESCRIPTOR, (Object) session.getGenerator());
            objectEncoderContext.add(IDENTIFIER_DESCRIPTOR, (Object) session.getIdentifierUtf8Bytes());
            objectEncoderContext.add(APPQUALITYSESSIONID_DESCRIPTOR, (Object) session.getAppQualitySessionId());
            objectEncoderContext.add(STARTEDAT_DESCRIPTOR, session.getStartedAt());
            objectEncoderContext.add(ENDEDAT_DESCRIPTOR, (Object) session.getEndedAt());
            objectEncoderContext.add(CRASHED_DESCRIPTOR, session.isCrashed());
            objectEncoderContext.add(APP_DESCRIPTOR, (Object) session.getApp());
            objectEncoderContext.add(USER_DESCRIPTOR, (Object) session.getUser());
            objectEncoderContext.add(OS_DESCRIPTOR, (Object) session.getOs());
            objectEncoderContext.add(DEVICE_DESCRIPTOR, (Object) session.getDevice());
            objectEncoderContext.add(EVENTS_DESCRIPTOR, (Object) session.getEvents());
            objectEncoderContext.add(GENERATORTYPE_DESCRIPTOR, session.getGeneratorType());
        }
    }

    private static final class CrashlyticsReportSessionApplicationEncoder implements ObjectEncoder<CrashlyticsReport.Session.Application> {
        private static final FieldDescriptor DEVELOPMENTPLATFORMVERSION_DESCRIPTOR = FieldDescriptor.of("developmentPlatformVersion");
        private static final FieldDescriptor DEVELOPMENTPLATFORM_DESCRIPTOR = FieldDescriptor.of("developmentPlatform");
        private static final FieldDescriptor DISPLAYVERSION_DESCRIPTOR = FieldDescriptor.of("displayVersion");
        private static final FieldDescriptor IDENTIFIER_DESCRIPTOR = FieldDescriptor.of("identifier");
        private static final FieldDescriptor INSTALLATIONUUID_DESCRIPTOR = FieldDescriptor.of("installationUuid");
        static final CrashlyticsReportSessionApplicationEncoder INSTANCE = new CrashlyticsReportSessionApplicationEncoder();
        private static final FieldDescriptor ORGANIZATION_DESCRIPTOR = FieldDescriptor.of("organization");
        private static final FieldDescriptor VERSION_DESCRIPTOR = FieldDescriptor.of("version");

        private CrashlyticsReportSessionApplicationEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Application application, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(IDENTIFIER_DESCRIPTOR, (Object) application.getIdentifier());
            objectEncoderContext.add(VERSION_DESCRIPTOR, (Object) application.getVersion());
            objectEncoderContext.add(DISPLAYVERSION_DESCRIPTOR, (Object) application.getDisplayVersion());
            objectEncoderContext.add(ORGANIZATION_DESCRIPTOR, (Object) application.getOrganization());
            objectEncoderContext.add(INSTALLATIONUUID_DESCRIPTOR, (Object) application.getInstallationUuid());
            objectEncoderContext.add(DEVELOPMENTPLATFORM_DESCRIPTOR, (Object) application.getDevelopmentPlatform());
            objectEncoderContext.add(DEVELOPMENTPLATFORMVERSION_DESCRIPTOR, (Object) application.getDevelopmentPlatformVersion());
        }
    }

    private static final class CrashlyticsReportSessionApplicationOrganizationEncoder implements ObjectEncoder<CrashlyticsReport.Session.Application.Organization> {
        private static final FieldDescriptor CLSID_DESCRIPTOR = FieldDescriptor.of("clsId");
        static final CrashlyticsReportSessionApplicationOrganizationEncoder INSTANCE = new CrashlyticsReportSessionApplicationOrganizationEncoder();

        private CrashlyticsReportSessionApplicationOrganizationEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Application.Organization organization, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(CLSID_DESCRIPTOR, (Object) organization.getClsId());
        }
    }

    private static final class CrashlyticsReportSessionUserEncoder implements ObjectEncoder<CrashlyticsReport.Session.User> {
        private static final FieldDescriptor IDENTIFIER_DESCRIPTOR = FieldDescriptor.of("identifier");
        static final CrashlyticsReportSessionUserEncoder INSTANCE = new CrashlyticsReportSessionUserEncoder();

        private CrashlyticsReportSessionUserEncoder() {
        }

        public void encode(CrashlyticsReport.Session.User user, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(IDENTIFIER_DESCRIPTOR, (Object) user.getIdentifier());
        }
    }

    private static final class CrashlyticsReportSessionOperatingSystemEncoder implements ObjectEncoder<CrashlyticsReport.Session.OperatingSystem> {
        private static final FieldDescriptor BUILDVERSION_DESCRIPTOR = FieldDescriptor.of("buildVersion");
        static final CrashlyticsReportSessionOperatingSystemEncoder INSTANCE = new CrashlyticsReportSessionOperatingSystemEncoder();
        private static final FieldDescriptor JAILBROKEN_DESCRIPTOR = FieldDescriptor.of("jailbroken");
        private static final FieldDescriptor PLATFORM_DESCRIPTOR = FieldDescriptor.of(k.a.b);
        private static final FieldDescriptor VERSION_DESCRIPTOR = FieldDescriptor.of("version");

        private CrashlyticsReportSessionOperatingSystemEncoder() {
        }

        public void encode(CrashlyticsReport.Session.OperatingSystem operatingSystem, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(PLATFORM_DESCRIPTOR, operatingSystem.getPlatform());
            objectEncoderContext.add(VERSION_DESCRIPTOR, (Object) operatingSystem.getVersion());
            objectEncoderContext.add(BUILDVERSION_DESCRIPTOR, (Object) operatingSystem.getBuildVersion());
            objectEncoderContext.add(JAILBROKEN_DESCRIPTOR, operatingSystem.isJailbroken());
        }
    }

    private static final class CrashlyticsReportSessionDeviceEncoder implements ObjectEncoder<CrashlyticsReport.Session.Device> {
        private static final FieldDescriptor ARCH_DESCRIPTOR = FieldDescriptor.of("arch");
        private static final FieldDescriptor CORES_DESCRIPTOR = FieldDescriptor.of("cores");
        private static final FieldDescriptor DISKSPACE_DESCRIPTOR = FieldDescriptor.of("diskSpace");
        static final CrashlyticsReportSessionDeviceEncoder INSTANCE = new CrashlyticsReportSessionDeviceEncoder();
        private static final FieldDescriptor MANUFACTURER_DESCRIPTOR = FieldDescriptor.of("manufacturer");
        private static final FieldDescriptor MODELCLASS_DESCRIPTOR = FieldDescriptor.of("modelClass");
        private static final FieldDescriptor MODEL_DESCRIPTOR = FieldDescriptor.of("model");
        private static final FieldDescriptor RAM_DESCRIPTOR = FieldDescriptor.of("ram");
        private static final FieldDescriptor SIMULATOR_DESCRIPTOR = FieldDescriptor.of("simulator");
        private static final FieldDescriptor STATE_DESCRIPTOR = FieldDescriptor.of(RemoteConfigConstants.ResponseFieldKey.STATE);

        private CrashlyticsReportSessionDeviceEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Device device, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(ARCH_DESCRIPTOR, device.getArch());
            objectEncoderContext.add(MODEL_DESCRIPTOR, (Object) device.getModel());
            objectEncoderContext.add(CORES_DESCRIPTOR, device.getCores());
            objectEncoderContext.add(RAM_DESCRIPTOR, device.getRam());
            objectEncoderContext.add(DISKSPACE_DESCRIPTOR, device.getDiskSpace());
            objectEncoderContext.add(SIMULATOR_DESCRIPTOR, device.isSimulator());
            objectEncoderContext.add(STATE_DESCRIPTOR, device.getState());
            objectEncoderContext.add(MANUFACTURER_DESCRIPTOR, (Object) device.getManufacturer());
            objectEncoderContext.add(MODELCLASS_DESCRIPTOR, (Object) device.getModelClass());
        }
    }

    private static final class CrashlyticsReportSessionEventEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event> {
        private static final FieldDescriptor APP_DESCRIPTOR = FieldDescriptor.of("app");
        private static final FieldDescriptor DEVICE_DESCRIPTOR = FieldDescriptor.of("device");
        static final CrashlyticsReportSessionEventEncoder INSTANCE = new CrashlyticsReportSessionEventEncoder();
        private static final FieldDescriptor LOG_DESCRIPTOR = FieldDescriptor.of("log");
        private static final FieldDescriptor ROLLOUTS_DESCRIPTOR = FieldDescriptor.of("rollouts");
        private static final FieldDescriptor TIMESTAMP_DESCRIPTOR = FieldDescriptor.of("timestamp");
        private static final FieldDescriptor TYPE_DESCRIPTOR = FieldDescriptor.of("type");

        private CrashlyticsReportSessionEventEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event event, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(TIMESTAMP_DESCRIPTOR, event.getTimestamp());
            objectEncoderContext.add(TYPE_DESCRIPTOR, (Object) event.getType());
            objectEncoderContext.add(APP_DESCRIPTOR, (Object) event.getApp());
            objectEncoderContext.add(DEVICE_DESCRIPTOR, (Object) event.getDevice());
            objectEncoderContext.add(LOG_DESCRIPTOR, (Object) event.getLog());
            objectEncoderContext.add(ROLLOUTS_DESCRIPTOR, (Object) event.getRollouts());
        }
    }

    private static final class CrashlyticsReportSessionEventApplicationEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Application> {
        private static final FieldDescriptor APPPROCESSDETAILS_DESCRIPTOR = FieldDescriptor.of("appProcessDetails");
        private static final FieldDescriptor BACKGROUND_DESCRIPTOR = FieldDescriptor.of(AppStateModule.APP_STATE_BACKGROUND);
        private static final FieldDescriptor CURRENTPROCESSDETAILS_DESCRIPTOR = FieldDescriptor.of("currentProcessDetails");
        private static final FieldDescriptor CUSTOMATTRIBUTES_DESCRIPTOR = FieldDescriptor.of("customAttributes");
        private static final FieldDescriptor EXECUTION_DESCRIPTOR = FieldDescriptor.of("execution");
        static final CrashlyticsReportSessionEventApplicationEncoder INSTANCE = new CrashlyticsReportSessionEventApplicationEncoder();
        private static final FieldDescriptor INTERNALKEYS_DESCRIPTOR = FieldDescriptor.of("internalKeys");
        private static final FieldDescriptor UIORIENTATION_DESCRIPTOR = FieldDescriptor.of("uiOrientation");

        private CrashlyticsReportSessionEventApplicationEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event.Application application, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(EXECUTION_DESCRIPTOR, (Object) application.getExecution());
            objectEncoderContext.add(CUSTOMATTRIBUTES_DESCRIPTOR, (Object) application.getCustomAttributes());
            objectEncoderContext.add(INTERNALKEYS_DESCRIPTOR, (Object) application.getInternalKeys());
            objectEncoderContext.add(BACKGROUND_DESCRIPTOR, (Object) application.getBackground());
            objectEncoderContext.add(CURRENTPROCESSDETAILS_DESCRIPTOR, (Object) application.getCurrentProcessDetails());
            objectEncoderContext.add(APPPROCESSDETAILS_DESCRIPTOR, (Object) application.getAppProcessDetails());
            objectEncoderContext.add(UIORIENTATION_DESCRIPTOR, application.getUiOrientation());
        }
    }

    private static final class CrashlyticsReportSessionEventApplicationExecutionEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Application.Execution> {
        private static final FieldDescriptor APPEXITINFO_DESCRIPTOR = FieldDescriptor.of("appExitInfo");
        private static final FieldDescriptor BINARIES_DESCRIPTOR = FieldDescriptor.of("binaries");
        private static final FieldDescriptor EXCEPTION_DESCRIPTOR = FieldDescriptor.of("exception");
        static final CrashlyticsReportSessionEventApplicationExecutionEncoder INSTANCE = new CrashlyticsReportSessionEventApplicationExecutionEncoder();
        private static final FieldDescriptor SIGNAL_DESCRIPTOR = FieldDescriptor.of("signal");
        private static final FieldDescriptor THREADS_DESCRIPTOR = FieldDescriptor.of("threads");

        private CrashlyticsReportSessionEventApplicationExecutionEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event.Application.Execution execution, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(THREADS_DESCRIPTOR, (Object) execution.getThreads());
            objectEncoderContext.add(EXCEPTION_DESCRIPTOR, (Object) execution.getException());
            objectEncoderContext.add(APPEXITINFO_DESCRIPTOR, (Object) execution.getAppExitInfo());
            objectEncoderContext.add(SIGNAL_DESCRIPTOR, (Object) execution.getSignal());
            objectEncoderContext.add(BINARIES_DESCRIPTOR, (Object) execution.getBinaries());
        }
    }

    private static final class CrashlyticsReportSessionEventApplicationExecutionThreadEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Application.Execution.Thread> {
        private static final FieldDescriptor FRAMES_DESCRIPTOR = FieldDescriptor.of("frames");
        private static final FieldDescriptor IMPORTANCE_DESCRIPTOR = FieldDescriptor.of("importance");
        static final CrashlyticsReportSessionEventApplicationExecutionThreadEncoder INSTANCE = new CrashlyticsReportSessionEventApplicationExecutionThreadEncoder();
        private static final FieldDescriptor NAME_DESCRIPTOR = FieldDescriptor.of("name");

        private CrashlyticsReportSessionEventApplicationExecutionThreadEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event.Application.Execution.Thread thread, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(NAME_DESCRIPTOR, (Object) thread.getName());
            objectEncoderContext.add(IMPORTANCE_DESCRIPTOR, thread.getImportance());
            objectEncoderContext.add(FRAMES_DESCRIPTOR, (Object) thread.getFrames());
        }
    }

    private static final class CrashlyticsReportSessionEventApplicationExecutionThreadFrameEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame> {
        private static final FieldDescriptor FILE_DESCRIPTOR = FieldDescriptor.of("file");
        private static final FieldDescriptor IMPORTANCE_DESCRIPTOR = FieldDescriptor.of("importance");
        static final CrashlyticsReportSessionEventApplicationExecutionThreadFrameEncoder INSTANCE = new CrashlyticsReportSessionEventApplicationExecutionThreadFrameEncoder();
        private static final FieldDescriptor OFFSET_DESCRIPTOR = FieldDescriptor.of("offset");
        private static final FieldDescriptor PC_DESCRIPTOR = FieldDescriptor.of("pc");
        private static final FieldDescriptor SYMBOL_DESCRIPTOR = FieldDescriptor.of("symbol");

        private CrashlyticsReportSessionEventApplicationExecutionThreadFrameEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame frame, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(PC_DESCRIPTOR, frame.getPc());
            objectEncoderContext.add(SYMBOL_DESCRIPTOR, (Object) frame.getSymbol());
            objectEncoderContext.add(FILE_DESCRIPTOR, (Object) frame.getFile());
            objectEncoderContext.add(OFFSET_DESCRIPTOR, frame.getOffset());
            objectEncoderContext.add(IMPORTANCE_DESCRIPTOR, frame.getImportance());
        }
    }

    private static final class CrashlyticsReportSessionEventApplicationExecutionExceptionEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Application.Execution.Exception> {
        private static final FieldDescriptor CAUSEDBY_DESCRIPTOR = FieldDescriptor.of("causedBy");
        private static final FieldDescriptor FRAMES_DESCRIPTOR = FieldDescriptor.of("frames");
        static final CrashlyticsReportSessionEventApplicationExecutionExceptionEncoder INSTANCE = new CrashlyticsReportSessionEventApplicationExecutionExceptionEncoder();
        private static final FieldDescriptor OVERFLOWCOUNT_DESCRIPTOR = FieldDescriptor.of("overflowCount");
        private static final FieldDescriptor REASON_DESCRIPTOR = FieldDescriptor.of("reason");
        private static final FieldDescriptor TYPE_DESCRIPTOR = FieldDescriptor.of("type");

        private CrashlyticsReportSessionEventApplicationExecutionExceptionEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event.Application.Execution.Exception exception, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(TYPE_DESCRIPTOR, (Object) exception.getType());
            objectEncoderContext.add(REASON_DESCRIPTOR, (Object) exception.getReason());
            objectEncoderContext.add(FRAMES_DESCRIPTOR, (Object) exception.getFrames());
            objectEncoderContext.add(CAUSEDBY_DESCRIPTOR, (Object) exception.getCausedBy());
            objectEncoderContext.add(OVERFLOWCOUNT_DESCRIPTOR, exception.getOverflowCount());
        }
    }

    private static final class CrashlyticsReportApplicationExitInfoEncoder implements ObjectEncoder<CrashlyticsReport.ApplicationExitInfo> {
        private static final FieldDescriptor BUILDIDMAPPINGFORARCH_DESCRIPTOR = FieldDescriptor.of("buildIdMappingForArch");
        private static final FieldDescriptor IMPORTANCE_DESCRIPTOR = FieldDescriptor.of("importance");
        static final CrashlyticsReportApplicationExitInfoEncoder INSTANCE = new CrashlyticsReportApplicationExitInfoEncoder();
        private static final FieldDescriptor PID_DESCRIPTOR = FieldDescriptor.of("pid");
        private static final FieldDescriptor PROCESSNAME_DESCRIPTOR = FieldDescriptor.of("processName");
        private static final FieldDescriptor PSS_DESCRIPTOR = FieldDescriptor.of("pss");
        private static final FieldDescriptor REASONCODE_DESCRIPTOR = FieldDescriptor.of("reasonCode");
        private static final FieldDescriptor RSS_DESCRIPTOR = FieldDescriptor.of("rss");
        private static final FieldDescriptor TIMESTAMP_DESCRIPTOR = FieldDescriptor.of("timestamp");
        private static final FieldDescriptor TRACEFILE_DESCRIPTOR = FieldDescriptor.of("traceFile");

        private CrashlyticsReportApplicationExitInfoEncoder() {
        }

        public void encode(CrashlyticsReport.ApplicationExitInfo applicationExitInfo, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(PID_DESCRIPTOR, applicationExitInfo.getPid());
            objectEncoderContext.add(PROCESSNAME_DESCRIPTOR, (Object) applicationExitInfo.getProcessName());
            objectEncoderContext.add(REASONCODE_DESCRIPTOR, applicationExitInfo.getReasonCode());
            objectEncoderContext.add(IMPORTANCE_DESCRIPTOR, applicationExitInfo.getImportance());
            objectEncoderContext.add(PSS_DESCRIPTOR, applicationExitInfo.getPss());
            objectEncoderContext.add(RSS_DESCRIPTOR, applicationExitInfo.getRss());
            objectEncoderContext.add(TIMESTAMP_DESCRIPTOR, applicationExitInfo.getTimestamp());
            objectEncoderContext.add(TRACEFILE_DESCRIPTOR, (Object) applicationExitInfo.getTraceFile());
            objectEncoderContext.add(BUILDIDMAPPINGFORARCH_DESCRIPTOR, (Object) applicationExitInfo.getBuildIdMappingForArch());
        }
    }

    private static final class CrashlyticsReportApplicationExitInfoBuildIdMappingForArchEncoder implements ObjectEncoder<CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch> {
        private static final FieldDescriptor ARCH_DESCRIPTOR = FieldDescriptor.of("arch");
        private static final FieldDescriptor BUILDID_DESCRIPTOR = FieldDescriptor.of("buildId");
        static final CrashlyticsReportApplicationExitInfoBuildIdMappingForArchEncoder INSTANCE = new CrashlyticsReportApplicationExitInfoBuildIdMappingForArchEncoder();
        private static final FieldDescriptor LIBRARYNAME_DESCRIPTOR = FieldDescriptor.of("libraryName");

        private CrashlyticsReportApplicationExitInfoBuildIdMappingForArchEncoder() {
        }

        public void encode(CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch buildIdMappingForArch, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(ARCH_DESCRIPTOR, (Object) buildIdMappingForArch.getArch());
            objectEncoderContext.add(LIBRARYNAME_DESCRIPTOR, (Object) buildIdMappingForArch.getLibraryName());
            objectEncoderContext.add(BUILDID_DESCRIPTOR, (Object) buildIdMappingForArch.getBuildId());
        }
    }

    private static final class CrashlyticsReportSessionEventApplicationExecutionSignalEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Application.Execution.Signal> {
        private static final FieldDescriptor ADDRESS_DESCRIPTOR = FieldDescriptor.of("address");
        private static final FieldDescriptor CODE_DESCRIPTOR = FieldDescriptor.of("code");
        static final CrashlyticsReportSessionEventApplicationExecutionSignalEncoder INSTANCE = new CrashlyticsReportSessionEventApplicationExecutionSignalEncoder();
        private static final FieldDescriptor NAME_DESCRIPTOR = FieldDescriptor.of("name");

        private CrashlyticsReportSessionEventApplicationExecutionSignalEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event.Application.Execution.Signal signal, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(NAME_DESCRIPTOR, (Object) signal.getName());
            objectEncoderContext.add(CODE_DESCRIPTOR, (Object) signal.getCode());
            objectEncoderContext.add(ADDRESS_DESCRIPTOR, signal.getAddress());
        }
    }

    private static final class CrashlyticsReportSessionEventApplicationExecutionBinaryImageEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Application.Execution.BinaryImage> {
        private static final FieldDescriptor BASEADDRESS_DESCRIPTOR = FieldDescriptor.of("baseAddress");
        static final CrashlyticsReportSessionEventApplicationExecutionBinaryImageEncoder INSTANCE = new CrashlyticsReportSessionEventApplicationExecutionBinaryImageEncoder();
        private static final FieldDescriptor NAME_DESCRIPTOR = FieldDescriptor.of("name");
        private static final FieldDescriptor SIZE_DESCRIPTOR = FieldDescriptor.of("size");
        private static final FieldDescriptor UUID_DESCRIPTOR = FieldDescriptor.of("uuid");

        private CrashlyticsReportSessionEventApplicationExecutionBinaryImageEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event.Application.Execution.BinaryImage binaryImage, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(BASEADDRESS_DESCRIPTOR, binaryImage.getBaseAddress());
            objectEncoderContext.add(SIZE_DESCRIPTOR, binaryImage.getSize());
            objectEncoderContext.add(NAME_DESCRIPTOR, (Object) binaryImage.getName());
            objectEncoderContext.add(UUID_DESCRIPTOR, (Object) binaryImage.getUuidUtf8Bytes());
        }
    }

    private static final class CrashlyticsReportCustomAttributeEncoder implements ObjectEncoder<CrashlyticsReport.CustomAttribute> {
        static final CrashlyticsReportCustomAttributeEncoder INSTANCE = new CrashlyticsReportCustomAttributeEncoder();
        private static final FieldDescriptor KEY_DESCRIPTOR = FieldDescriptor.of("key");
        private static final FieldDescriptor VALUE_DESCRIPTOR = FieldDescriptor.of("value");

        private CrashlyticsReportCustomAttributeEncoder() {
        }

        public void encode(CrashlyticsReport.CustomAttribute customAttribute, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(KEY_DESCRIPTOR, (Object) customAttribute.getKey());
            objectEncoderContext.add(VALUE_DESCRIPTOR, (Object) customAttribute.getValue());
        }
    }

    private static final class CrashlyticsReportSessionEventApplicationProcessDetailsEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Application.ProcessDetails> {
        private static final FieldDescriptor DEFAULTPROCESS_DESCRIPTOR = FieldDescriptor.of("defaultProcess");
        private static final FieldDescriptor IMPORTANCE_DESCRIPTOR = FieldDescriptor.of("importance");
        static final CrashlyticsReportSessionEventApplicationProcessDetailsEncoder INSTANCE = new CrashlyticsReportSessionEventApplicationProcessDetailsEncoder();
        private static final FieldDescriptor PID_DESCRIPTOR = FieldDescriptor.of("pid");
        private static final FieldDescriptor PROCESSNAME_DESCRIPTOR = FieldDescriptor.of("processName");

        private CrashlyticsReportSessionEventApplicationProcessDetailsEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event.Application.ProcessDetails processDetails, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(PROCESSNAME_DESCRIPTOR, (Object) processDetails.getProcessName());
            objectEncoderContext.add(PID_DESCRIPTOR, processDetails.getPid());
            objectEncoderContext.add(IMPORTANCE_DESCRIPTOR, processDetails.getImportance());
            objectEncoderContext.add(DEFAULTPROCESS_DESCRIPTOR, processDetails.isDefaultProcess());
        }
    }

    private static final class CrashlyticsReportSessionEventDeviceEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Device> {
        private static final FieldDescriptor BATTERYLEVEL_DESCRIPTOR = FieldDescriptor.of("batteryLevel");
        private static final FieldDescriptor BATTERYVELOCITY_DESCRIPTOR = FieldDescriptor.of("batteryVelocity");
        private static final FieldDescriptor DISKUSED_DESCRIPTOR = FieldDescriptor.of("diskUsed");
        static final CrashlyticsReportSessionEventDeviceEncoder INSTANCE = new CrashlyticsReportSessionEventDeviceEncoder();
        private static final FieldDescriptor ORIENTATION_DESCRIPTOR = FieldDescriptor.of("orientation");
        private static final FieldDescriptor PROXIMITYON_DESCRIPTOR = FieldDescriptor.of("proximityOn");
        private static final FieldDescriptor RAMUSED_DESCRIPTOR = FieldDescriptor.of("ramUsed");

        private CrashlyticsReportSessionEventDeviceEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event.Device device, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(BATTERYLEVEL_DESCRIPTOR, (Object) device.getBatteryLevel());
            objectEncoderContext.add(BATTERYVELOCITY_DESCRIPTOR, device.getBatteryVelocity());
            objectEncoderContext.add(PROXIMITYON_DESCRIPTOR, device.isProximityOn());
            objectEncoderContext.add(ORIENTATION_DESCRIPTOR, device.getOrientation());
            objectEncoderContext.add(RAMUSED_DESCRIPTOR, device.getRamUsed());
            objectEncoderContext.add(DISKUSED_DESCRIPTOR, device.getDiskUsed());
        }
    }

    private static final class CrashlyticsReportSessionEventLogEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Log> {
        private static final FieldDescriptor CONTENT_DESCRIPTOR = FieldDescriptor.of(FirebaseAnalytics.Param.CONTENT);
        static final CrashlyticsReportSessionEventLogEncoder INSTANCE = new CrashlyticsReportSessionEventLogEncoder();

        private CrashlyticsReportSessionEventLogEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event.Log log, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(CONTENT_DESCRIPTOR, (Object) log.getContent());
        }
    }

    private static final class CrashlyticsReportSessionEventRolloutsStateEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.RolloutsState> {
        private static final FieldDescriptor ASSIGNMENTS_DESCRIPTOR = FieldDescriptor.of("assignments");
        static final CrashlyticsReportSessionEventRolloutsStateEncoder INSTANCE = new CrashlyticsReportSessionEventRolloutsStateEncoder();

        private CrashlyticsReportSessionEventRolloutsStateEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event.RolloutsState rolloutsState, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(ASSIGNMENTS_DESCRIPTOR, (Object) rolloutsState.getRolloutAssignments());
        }
    }

    private static final class CrashlyticsReportSessionEventRolloutAssignmentEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.RolloutAssignment> {
        static final CrashlyticsReportSessionEventRolloutAssignmentEncoder INSTANCE = new CrashlyticsReportSessionEventRolloutAssignmentEncoder();
        private static final FieldDescriptor PARAMETERKEY_DESCRIPTOR = FieldDescriptor.of("parameterKey");
        private static final FieldDescriptor PARAMETERVALUE_DESCRIPTOR = FieldDescriptor.of("parameterValue");
        private static final FieldDescriptor ROLLOUTVARIANT_DESCRIPTOR = FieldDescriptor.of("rolloutVariant");
        private static final FieldDescriptor TEMPLATEVERSION_DESCRIPTOR = FieldDescriptor.of(RemoteConfigConstants.ResponseFieldKey.TEMPLATE_VERSION_NUMBER);

        private CrashlyticsReportSessionEventRolloutAssignmentEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event.RolloutAssignment rolloutAssignment, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(ROLLOUTVARIANT_DESCRIPTOR, (Object) rolloutAssignment.getRolloutVariant());
            objectEncoderContext.add(PARAMETERKEY_DESCRIPTOR, (Object) rolloutAssignment.getParameterKey());
            objectEncoderContext.add(PARAMETERVALUE_DESCRIPTOR, (Object) rolloutAssignment.getParameterValue());
            objectEncoderContext.add(TEMPLATEVERSION_DESCRIPTOR, rolloutAssignment.getTemplateVersion());
        }
    }

    private static final class CrashlyticsReportSessionEventRolloutAssignmentRolloutVariantEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant> {
        static final CrashlyticsReportSessionEventRolloutAssignmentRolloutVariantEncoder INSTANCE = new CrashlyticsReportSessionEventRolloutAssignmentRolloutVariantEncoder();
        private static final FieldDescriptor ROLLOUTID_DESCRIPTOR = FieldDescriptor.of(ConfigContainer.ROLLOUT_METADATA_ID);
        private static final FieldDescriptor VARIANTID_DESCRIPTOR = FieldDescriptor.of("variantId");

        private CrashlyticsReportSessionEventRolloutAssignmentRolloutVariantEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant rolloutVariant, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(ROLLOUTID_DESCRIPTOR, (Object) rolloutVariant.getRolloutId());
            objectEncoderContext.add(VARIANTID_DESCRIPTOR, (Object) rolloutVariant.getVariantId());
        }
    }

    private static final class CrashlyticsReportFilesPayloadEncoder implements ObjectEncoder<CrashlyticsReport.FilesPayload> {
        private static final FieldDescriptor FILES_DESCRIPTOR = FieldDescriptor.of("files");
        static final CrashlyticsReportFilesPayloadEncoder INSTANCE = new CrashlyticsReportFilesPayloadEncoder();
        private static final FieldDescriptor ORGID_DESCRIPTOR = FieldDescriptor.of("orgId");

        private CrashlyticsReportFilesPayloadEncoder() {
        }

        public void encode(CrashlyticsReport.FilesPayload filesPayload, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(FILES_DESCRIPTOR, (Object) filesPayload.getFiles());
            objectEncoderContext.add(ORGID_DESCRIPTOR, (Object) filesPayload.getOrgId());
        }
    }

    private static final class CrashlyticsReportFilesPayloadFileEncoder implements ObjectEncoder<CrashlyticsReport.FilesPayload.File> {
        private static final FieldDescriptor CONTENTS_DESCRIPTOR = FieldDescriptor.of("contents");
        private static final FieldDescriptor FILENAME_DESCRIPTOR = FieldDescriptor.of("filename");
        static final CrashlyticsReportFilesPayloadFileEncoder INSTANCE = new CrashlyticsReportFilesPayloadFileEncoder();

        private CrashlyticsReportFilesPayloadFileEncoder() {
        }

        public void encode(CrashlyticsReport.FilesPayload.File file, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(FILENAME_DESCRIPTOR, (Object) file.getFilename());
            objectEncoderContext.add(CONTENTS_DESCRIPTOR, (Object) file.getContents());
        }
    }
}
