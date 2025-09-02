package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.List;

public final class zzbk extends zzaw {
    protected zzbk() {
        this.zza.add(zzbl.ASSIGN);
        this.zza.add(zzbl.CONST);
        this.zza.add(zzbl.CREATE_ARRAY);
        this.zza.add(zzbl.CREATE_OBJECT);
        this.zza.add(zzbl.EXPRESSION_LIST);
        this.zza.add(zzbl.GET);
        this.zza.add(zzbl.GET_INDEX);
        this.zza.add(zzbl.GET_PROPERTY);
        this.zza.add(zzbl.NULL);
        this.zza.add(zzbl.SET_PROPERTY);
        this.zza.add(zzbl.TYPEOF);
        this.zza.add(zzbl.UNDEFINED);
        this.zza.add(zzbl.VAR);
    }

    public final zzap zza(String str, zzg zzg, List list) {
        zzap zzb;
        String str2;
        zzbl zzbl = zzbl.ADD;
        int ordinal = zzh.zze(str).ordinal();
        int i = 0;
        if (ordinal == 3) {
            zzh.zzh(zzbl.ASSIGN.name(), 2, list);
            zzap zzb2 = zzg.zzb((zzap) list.get(0));
            if (!(zzb2 instanceof zzat)) {
                throw new IllegalArgumentException(String.format("Expected string for assign var. got %s", new Object[]{zzb2.getClass().getCanonicalName()}));
            } else if (zzg.zzh(zzb2.zzi())) {
                zzap zzb3 = zzg.zzb((zzap) list.get(1));
                zzg.zzg(zzb2.zzi(), zzb3);
                return zzb3;
            } else {
                throw new IllegalArgumentException(String.format("Attempting to assign undefined value %s", new Object[]{zzb2.zzi()}));
            }
        } else if (ordinal == 14) {
            zzh.zzi(zzbl.CONST.name(), 2, list);
            if (list.size() % 2 == 0) {
                while (i < list.size() - 1) {
                    zzap zzb4 = zzg.zzb((zzap) list.get(i));
                    if (zzb4 instanceof zzat) {
                        zzg.zzf(zzb4.zzi(), zzg.zzb((zzap) list.get(i + 1)));
                        i += 2;
                    } else {
                        throw new IllegalArgumentException(String.format("Expected string for const name. got %s", new Object[]{zzb4.getClass().getCanonicalName()}));
                    }
                }
                return zzap.zzf;
            }
            throw new IllegalArgumentException(String.format("CONST requires an even number of arguments, found %s", new Object[]{Integer.valueOf(list.size())}));
        } else if (ordinal == 24) {
            zzh.zzi(zzbl.EXPRESSION_LIST.name(), 1, list);
            zzap zzap = zzap.zzf;
            while (i < list.size()) {
                zzap = zzg.zzb((zzap) list.get(i));
                if (!(zzap instanceof zzag)) {
                    i++;
                } else {
                    throw new IllegalStateException("ControlValue cannot be in an expression list");
                }
            }
            return zzap;
        } else if (ordinal == 33) {
            zzh.zzh(zzbl.GET.name(), 1, list);
            zzap zzb5 = zzg.zzb((zzap) list.get(0));
            if (zzb5 instanceof zzat) {
                return zzg.zzd(zzb5.zzi());
            }
            throw new IllegalArgumentException(String.format("Expected string for get var. got %s", new Object[]{zzb5.getClass().getCanonicalName()}));
        } else if (ordinal != 49) {
            if (ordinal == 58) {
                zzh.zzh(zzbl.SET_PROPERTY.name(), 3, list);
                zzap zzb6 = zzg.zzb((zzap) list.get(0));
                zzap zzb7 = zzg.zzb((zzap) list.get(1));
                zzb = zzg.zzb((zzap) list.get(2));
                if (zzb6 == zzap.zzf || zzb6 == zzap.zzg) {
                    throw new IllegalStateException(String.format("Can't set property %s of %s", new Object[]{zzb7.zzi(), zzb6.zzi()}));
                } else if ((zzb6 instanceof zzae) && (zzb7 instanceof zzah)) {
                    ((zzae) zzb6).zzq(zzb7.zzh().intValue(), zzb);
                } else if (zzb6 instanceof zzal) {
                    ((zzal) zzb6).zzr(zzb7.zzi(), zzb);
                    return zzb;
                }
            } else if (ordinal != 17) {
                if (ordinal != 18) {
                    if (ordinal == 35 || ordinal == 36) {
                        zzh.zzh(zzbl.GET_PROPERTY.name(), 2, list);
                        zzap zzb8 = zzg.zzb((zzap) list.get(0));
                        zzap zzb9 = zzg.zzb((zzap) list.get(1));
                        if ((zzb8 instanceof zzae) && zzh.zzk(zzb9)) {
                            return ((zzae) zzb8).zze(zzb9.zzh().intValue());
                        }
                        if (zzb8 instanceof zzal) {
                            return ((zzal) zzb8).zzf(zzb9.zzi());
                        }
                        if (zzb8 instanceof zzat) {
                            if ("length".equals(zzb9.zzi())) {
                                zzb = new zzah(Double.valueOf((double) zzb8.zzi().length()));
                            } else if (zzh.zzk(zzb9) && zzb9.zzh().doubleValue() < ((double) zzb8.zzi().length())) {
                                return new zzat(String.valueOf(zzb8.zzi().charAt(zzb9.zzh().intValue())));
                            }
                        }
                        return zzap.zzf;
                    }
                    switch (ordinal) {
                        case 62:
                            zzh.zzh(zzbl.TYPEOF.name(), 1, list);
                            zzap zzb10 = zzg.zzb((zzap) list.get(0));
                            if (zzb10 instanceof zzau) {
                                str2 = "undefined";
                            } else if (zzb10 instanceof zzaf) {
                                str2 = "boolean";
                            } else if (zzb10 instanceof zzah) {
                                str2 = "number";
                            } else if (zzb10 instanceof zzat) {
                                str2 = "string";
                            } else if (zzb10 instanceof zzao) {
                                str2 = "function";
                            } else if ((zzb10 instanceof zzaq) || (zzb10 instanceof zzag)) {
                                throw new IllegalArgumentException(String.format("Unsupported value type %s in typeof", new Object[]{zzb10}));
                            } else {
                                str2 = "object";
                            }
                            return new zzat(str2);
                        case 63:
                            zzh.zzh(zzbl.UNDEFINED.name(), 0, list);
                            return zzap.zzf;
                        case 64:
                            zzh.zzi(zzbl.VAR.name(), 1, list);
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                zzap zzb11 = zzg.zzb((zzap) it.next());
                                if (zzb11 instanceof zzat) {
                                    zzg.zze(zzb11.zzi(), zzap.zzf);
                                } else {
                                    throw new IllegalArgumentException(String.format("Expected string for var name. got %s", new Object[]{zzb11.getClass().getCanonicalName()}));
                                }
                            }
                            return zzap.zzf;
                        default:
                            return super.zzb(str);
                    }
                } else if (list.isEmpty()) {
                    return new zzam();
                } else {
                    if (list.size() % 2 == 0) {
                        zzam zzam = new zzam();
                        while (i < list.size() - 1) {
                            zzap zzb12 = zzg.zzb((zzap) list.get(i));
                            zzap zzb13 = zzg.zzb((zzap) list.get(i + 1));
                            if ((zzb12 instanceof zzag) || (zzb13 instanceof zzag)) {
                                throw new IllegalStateException("Failed to evaluate map entry");
                            }
                            zzam.zzr(zzb12.zzi(), zzb13);
                            i += 2;
                        }
                        return zzam;
                    }
                    throw new IllegalArgumentException(String.format("CREATE_OBJECT requires an even number of arguments, found %s", new Object[]{Integer.valueOf(list.size())}));
                }
            } else if (list.isEmpty()) {
                return new zzae();
            } else {
                zzae zzae = new zzae();
                Iterator it2 = list.iterator();
                while (it2.hasNext()) {
                    zzap zzb14 = zzg.zzb((zzap) it2.next());
                    if (!(zzb14 instanceof zzag)) {
                        zzae.zzq(i, zzb14);
                        i++;
                    } else {
                        throw new IllegalStateException("Failed to evaluate array element");
                    }
                }
                return zzae;
            }
            return zzb;
        } else {
            zzh.zzh(zzbl.NULL.name(), 0, list);
            return zzap.zzg;
        }
    }
}
