package com.google.android.gms.internal.measurement;

public final class zzqt implements zzqs {
    public static final zzki zza;
    public static final zzki zzb;
    public static final zzki zzc;
    public static final zzki zzd;
    public static final zzki zze;
    public static final zzki zzf;
    public static final zzki zzg;
    public static final zzki zzh;

    static {
        zzkf zza2 = new zzkf(zzjx.zza("com.google.android.gms.measurement")).zzb().zza();
        zza2.zzf("measurement.rb.attribution.ad_campaign_info", true);
        zza2.zzf("measurement.rb.attribution.service.bundle_on_backgrounded", true);
        zza = zza2.zzf("measurement.rb.attribution.client2", true);
        zza2.zzf("measurement.rb.attribution.dma_fix", true);
        zzb = zza2.zzf("measurement.rb.attribution.followup1.service", false);
        zza2.zzf("measurement.rb.attribution.client.get_trigger_uris_async", true);
        zzc = zza2.zzf("measurement.rb.attribution.service.trigger_uris_high_priority", true);
        zza2.zzf("measurement.rb.attribution.index_out_of_bounds_fix", true);
        zzd = zza2.zzf("measurement.rb.attribution.service.enable_max_trigger_uris_queried_at_once", true);
        zze = zza2.zzf("measurement.rb.attribution.retry_disposition", false);
        zzf = zza2.zzf("measurement.rb.attribution.service", true);
        zzg = zza2.zzf("measurement.rb.attribution.enable_trigger_redaction", true);
        zzh = zza2.zzf("measurement.rb.attribution.uuid_generation", true);
        zza2.zzd("measurement.id.rb.attribution.retry_disposition", 0);
        zza2.zzf("measurement.rb.attribution.improved_retry", true);
    }

    public final boolean zza() {
        return true;
    }

    public final boolean zzb() {
        return ((Boolean) zza.zzb()).booleanValue();
    }

    public final boolean zzc() {
        return ((Boolean) zzb.zzb()).booleanValue();
    }

    public final boolean zzd() {
        return ((Boolean) zzc.zzb()).booleanValue();
    }

    public final boolean zze() {
        return ((Boolean) zzd.zzb()).booleanValue();
    }

    public final boolean zzf() {
        return ((Boolean) zze.zzb()).booleanValue();
    }

    public final boolean zzg() {
        return ((Boolean) zzf.zzb()).booleanValue();
    }

    public final boolean zzh() {
        return ((Boolean) zzg.zzb()).booleanValue();
    }

    public final boolean zzi() {
        return ((Boolean) zzh.zzb()).booleanValue();
    }
}
