package com.google.android.gms.internal.measurement;

import com.salesforce.marketingcloud.sfmcsdk.components.http.NetworkManager;

public final class zzpd implements zzpc {
    public static final zzki zzA;
    public static final zzki zzB;
    public static final zzki zzC;
    public static final zzki zzD;
    public static final zzki zzE;
    public static final zzki zzF;
    public static final zzki zzG;
    public static final zzki zzH;
    public static final zzki zzI;
    public static final zzki zzJ;
    public static final zzki zzK;
    public static final zzki zzL;
    public static final zzki zzM;
    public static final zzki zzN;
    public static final zzki zzO;
    public static final zzki zzP;
    public static final zzki zzQ;
    public static final zzki zzR;
    public static final zzki zzS;
    public static final zzki zzT;
    public static final zzki zzU;
    public static final zzki zzV;
    public static final zzki zzW;
    public static final zzki zzX;
    public static final zzki zzY;
    public static final zzki zzZ;
    public static final zzki zza;
    public static final zzki zzaa;
    public static final zzki zzab;
    public static final zzki zzac;
    public static final zzki zzad;
    public static final zzki zzae;
    public static final zzki zzaf;
    public static final zzki zzag;
    public static final zzki zzah;
    public static final zzki zzai;
    public static final zzki zzaj;
    public static final zzki zzak;
    public static final zzki zzal;
    public static final zzki zzam;
    public static final zzki zzan;
    public static final zzki zzao;
    public static final zzki zzap;
    public static final zzki zzaq;
    public static final zzki zzar;
    public static final zzki zzas;
    public static final zzki zzat;
    public static final zzki zzb;
    public static final zzki zzc;
    public static final zzki zzd;
    public static final zzki zze;
    public static final zzki zzf;
    public static final zzki zzg;
    public static final zzki zzh;
    public static final zzki zzi;
    public static final zzki zzj;
    public static final zzki zzk;
    public static final zzki zzl;
    public static final zzki zzm;
    public static final zzki zzn;
    public static final zzki zzo;
    public static final zzki zzp;
    public static final zzki zzq;
    public static final zzki zzr;
    public static final zzki zzs;
    public static final zzki zzt;
    public static final zzki zzu;
    public static final zzki zzv;
    public static final zzki zzw;
    public static final zzki zzx;
    public static final zzki zzy;
    public static final zzki zzz;

    static {
        zzkf zza2 = new zzkf(zzjx.zza("com.google.android.gms.measurement")).zzb().zza();
        zza = zza2.zzd("measurement.ad_id_cache_time", 10000);
        zzb = zza2.zzd("measurement.app_uninstalled_additional_ad_id_cache_time", 3600000);
        zzc = zza2.zzf("measurement.config.bundle_for_all_apps_on_backgrounded", true);
        zzd = zza2.zzd("measurement.max_bundles_per_iteration", 100);
        zze = zza2.zzd("measurement.config.cache_time", NetworkManager.MAX_SERVER_RETRY);
        zza2.zze("measurement.log_tag", "FA");
        zzf = zza2.zze("measurement.config.url_authority", "app-measurement.com");
        zzg = zza2.zze("measurement.config.url_scheme", "https");
        zzh = zza2.zzd("measurement.upload.debug_upload_interval", 1000);
        zzi = zza2.zzd("measurement.session.engagement_interval", 3600000);
        zzj = zza2.zze("measurement.rb.attribution.event_params", "value|currency");
        zzk = zza2.zzd("measurement.upload.google_signal_max_queue_time", 605000);
        zzl = zza2.zze("measurement.sgtm.google_signal.url", "https://app-measurement.com/s/d");
        zzm = zza2.zzd("measurement.lifetimevalue.max_currency_tracked", 4);
        zzn = zza2.zzd("measurement.dma_consent.max_daily_dcu_realtime_events", 1);
        zzo = zza2.zzd("measurement.upload.max_event_parameter_value_length", 500);
        zzp = zza2.zzd("measurement.store.max_stored_events_per_app", 100000);
        zzq = zza2.zzd("measurement.experiment.max_ids", 50);
        zzr = zza2.zzd("measurement.audience.filter_result_max_count", 200);
        zzs = zza2.zzd("measurement.upload.max_item_scoped_custom_parameters", 27);
        zzt = zza2.zzd("measurement.rb.max_trigger_registrations_per_day", 1000);
        zzu = zza2.zzd("measurement.rb.attribution.max_trigger_uris_queried_at_once", 0);
        zzv = zza2.zzd("measurement.rb.attribution.client.min_ad_services_version", 7);
        zzw = zza2.zzd("measurement.alarm_manager.minimum_interval", 60000);
        zzx = zza2.zzd("measurement.upload.minimum_delay", 500);
        zzy = zza2.zzd("measurement.monitoring.sample_period_millis", NetworkManager.MAX_SERVER_RETRY);
        zzz = zza2.zzd("measurement.rb.attribution.notify_app_delay_millis", 3000);
        zzA = zza2.zzf("measurement.config.notify_trigger_uris_on_backgrounded", true);
        zzB = zza2.zze("measurement.rb.attribution.app_allowlist", "*");
        zzC = zza2.zzd("measurement.upload.realtime_upload_interval", 10000);
        zzD = zza2.zzd("measurement.upload.refresh_blacklisted_config_interval", 604800000);
        zza2.zzd("measurement.config.cache_time.service", 3600000);
        zzE = zza2.zzd("measurement.service_client.idle_disconnect_millis", 5000);
        zza2.zze("measurement.log_tag.service", "FA-SVC");
        zzF = zza2.zzd("measurement.service_client.reconnect_millis", 1000);
        zza2.zze("measurement.sgtm.app_allowlist", "*");
        zzG = zza2.zzd("measurement.sgtm.batch.retry_interval", 1800000);
        zzH = zza2.zzd("measurement.sgtm.batch.retry_max_count", 10);
        zzI = zza2.zzd("measurement.sgtm.batch.retry_max_wait", 21600000);
        zzJ = zza2.zze("measurement.sgtm.service_upload_apps_list", "");
        zzK = zza2.zze("measurement.sgtm.upload.backoff_http_codes", "404,429,503,504");
        zzL = zza2.zzd("measurement.sgtm.upload.batches_retrieval_limit", 5);
        zzM = zza2.zzd("measurement.sgtm.upload.max_queued_batches", 5000);
        zzN = zza2.zzd("measurement.sgtm.upload.min_delay_after_background", 600000);
        zzO = zza2.zzd("measurement.sgtm.upload.min_delay_after_broadcast", 1000);
        zzP = zza2.zzd("measurement.sgtm.upload.min_delay_after_startup", 5000);
        zzQ = zza2.zzd("measurement.sgtm.upload.retry_interval", 600000);
        zzR = zza2.zzd("measurement.sgtm.upload.retry_max_wait", 21600000);
        zzS = zza2.zzd("measurement.upload.stale_data_deletion_interval", NetworkManager.MAX_SERVER_RETRY);
        zzT = zza2.zzd("measurement.rb.attribution.max_retry_delay_seconds", 16);
        zzU = zza2.zzd("measurement.rb.attribution.client.min_time_after_boot_seconds", 90);
        zzV = zza2.zze("measurement.rb.attribution.uri_authority", "google-analytics.com");
        zzW = zza2.zzd("measurement.rb.attribution.max_queue_time", 864000000);
        zzX = zza2.zze("measurement.rb.attribution.uri_path", "privacy-sandbox/register-app-conversion");
        zzY = zza2.zze("measurement.rb.attribution.query_parameters_to_remove", "");
        zzZ = zza2.zze("measurement.rb.attribution.uri_scheme", "https");
        zzaa = zza2.zzd("measurement.sdk.attribution.cache.ttl", 604800000);
        zzab = zza2.zzd("measurement.redaction.app_instance_id.ttl", 7200000);
        zzac = zza2.zzd("measurement.upload.backoff_period", 43200000);
        zzad = zza2.zzd("measurement.upload.initial_upload_delay_time", 15000);
        zzae = zza2.zzd("measurement.upload.interval", 3600000);
        zzaf = zza2.zzd("measurement.upload.max_bundle_size", 65536);
        zzag = zza2.zzd("measurement.upload.max_bundles", 100);
        zzah = zza2.zzd("measurement.upload.max_conversions_per_day", 500);
        zzai = zza2.zzd("measurement.upload.max_error_events_per_day", 1000);
        zzaj = zza2.zzd("measurement.upload.max_events_per_bundle", 1000);
        zzak = zza2.zzd("measurement.upload.max_events_per_day", 100000);
        zzal = zza2.zzd("measurement.upload.max_public_events_per_day", 50000);
        zzam = zza2.zzd("measurement.upload.max_queue_time", 518400000);
        zzan = zza2.zzd("measurement.upload.max_realtime_events_per_day", 10);
        zzao = zza2.zzd("measurement.upload.max_batch_size", 65536);
        zzap = zza2.zzd("measurement.upload.retry_count", 6);
        zzaq = zza2.zzd("measurement.upload.retry_time", 1800000);
        zzar = zza2.zze("measurement.upload.url", "https://app-measurement.com/a");
        zzas = zza2.zzd("measurement.upload.window_interval", 3600000);
        zzat = zza2.zze("measurement.rb.attribution.user_properties", "_npa,npa|_fot,fot");
    }

    public final long zzA() {
        return ((Long) zzH.zzb()).longValue();
    }

    public final long zzB() {
        return ((Long) zzI.zzb()).longValue();
    }

    public final long zzC() {
        return ((Long) zzL.zzb()).longValue();
    }

    public final long zzD() {
        return ((Long) zzM.zzb()).longValue();
    }

    public final long zzE() {
        return ((Long) zzN.zzb()).longValue();
    }

    public final long zzF() {
        return ((Long) zzO.zzb()).longValue();
    }

    public final long zzG() {
        return ((Long) zzP.zzb()).longValue();
    }

    public final long zzH() {
        return ((Long) zzQ.zzb()).longValue();
    }

    public final long zzI() {
        return ((Long) zzR.zzb()).longValue();
    }

    public final long zzJ() {
        return ((Long) zzS.zzb()).longValue();
    }

    public final long zzK() {
        return ((Long) zzT.zzb()).longValue();
    }

    public final long zzL() {
        return ((Long) zzU.zzb()).longValue();
    }

    public final long zzM() {
        return ((Long) zzW.zzb()).longValue();
    }

    public final long zzN() {
        return ((Long) zzaa.zzb()).longValue();
    }

    public final long zzO() {
        return ((Long) zzab.zzb()).longValue();
    }

    public final long zzP() {
        return ((Long) zzac.zzb()).longValue();
    }

    public final long zzQ() {
        return ((Long) zzad.zzb()).longValue();
    }

    public final long zzR() {
        return ((Long) zzae.zzb()).longValue();
    }

    public final long zzS() {
        return ((Long) zzaf.zzb()).longValue();
    }

    public final long zzT() {
        return ((Long) zzag.zzb()).longValue();
    }

    public final long zzU() {
        return ((Long) zzah.zzb()).longValue();
    }

    public final long zzV() {
        return ((Long) zzai.zzb()).longValue();
    }

    public final long zzW() {
        return ((Long) zzaj.zzb()).longValue();
    }

    public final long zzX() {
        return ((Long) zzak.zzb()).longValue();
    }

    public final long zzY() {
        return ((Long) zzal.zzb()).longValue();
    }

    public final long zzZ() {
        return ((Long) zzam.zzb()).longValue();
    }

    public final long zza() {
        return ((Long) zza.zzb()).longValue();
    }

    public final long zzaa() {
        return ((Long) zzan.zzb()).longValue();
    }

    public final long zzab() {
        return ((Long) zzao.zzb()).longValue();
    }

    public final long zzac() {
        return ((Long) zzap.zzb()).longValue();
    }

    public final long zzad() {
        return ((Long) zzaq.zzb()).longValue();
    }

    public final long zzae() {
        return ((Long) zzas.zzb()).longValue();
    }

    public final String zzaf() {
        return (String) zzf.zzb();
    }

    public final String zzag() {
        return (String) zzg.zzb();
    }

    public final String zzah() {
        return (String) zzj.zzb();
    }

    public final String zzai() {
        return (String) zzl.zzb();
    }

    public final String zzaj() {
        return (String) zzB.zzb();
    }

    public final String zzak() {
        return (String) zzJ.zzb();
    }

    public final String zzal() {
        return (String) zzK.zzb();
    }

    public final String zzam() {
        return (String) zzV.zzb();
    }

    public final String zzan() {
        return (String) zzX.zzb();
    }

    public final String zzao() {
        return (String) zzY.zzb();
    }

    public final String zzap() {
        return (String) zzZ.zzb();
    }

    public final String zzaq() {
        return (String) zzar.zzb();
    }

    public final String zzar() {
        return (String) zzat.zzb();
    }

    public final boolean zzas() {
        return ((Boolean) zzc.zzb()).booleanValue();
    }

    public final boolean zzat() {
        return ((Boolean) zzA.zzb()).booleanValue();
    }

    public final long zzb() {
        return ((Long) zzb.zzb()).longValue();
    }

    public final long zzc() {
        return ((Long) zzd.zzb()).longValue();
    }

    public final long zzd() {
        return ((Long) zze.zzb()).longValue();
    }

    public final long zze() {
        return ((Long) zzh.zzb()).longValue();
    }

    public final long zzf() {
        return ((Long) zzi.zzb()).longValue();
    }

    public final long zzg() {
        return ((Long) zzk.zzb()).longValue();
    }

    public final long zzh() {
        return ((Long) zzm.zzb()).longValue();
    }

    public final long zzi() {
        return ((Long) zzn.zzb()).longValue();
    }

    public final long zzj() {
        return ((Long) zzo.zzb()).longValue();
    }

    public final long zzk() {
        return ((Long) zzp.zzb()).longValue();
    }

    public final long zzl() {
        return ((Long) zzq.zzb()).longValue();
    }

    public final long zzm() {
        return ((Long) zzr.zzb()).longValue();
    }

    public final long zzn() {
        return ((Long) zzs.zzb()).longValue();
    }

    public final long zzo() {
        return ((Long) zzt.zzb()).longValue();
    }

    public final long zzp() {
        return ((Long) zzu.zzb()).longValue();
    }

    public final long zzq() {
        return ((Long) zzv.zzb()).longValue();
    }

    public final long zzr() {
        return ((Long) zzw.zzb()).longValue();
    }

    public final long zzs() {
        return ((Long) zzx.zzb()).longValue();
    }

    public final long zzt() {
        return ((Long) zzy.zzb()).longValue();
    }

    public final long zzu() {
        return ((Long) zzz.zzb()).longValue();
    }

    public final long zzv() {
        return ((Long) zzC.zzb()).longValue();
    }

    public final long zzw() {
        return ((Long) zzD.zzb()).longValue();
    }

    public final long zzx() {
        return ((Long) zzE.zzb()).longValue();
    }

    public final long zzy() {
        return ((Long) zzF.zzb()).longValue();
    }

    public final long zzz() {
        return ((Long) zzG.zzb()).longValue();
    }
}
