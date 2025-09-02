package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.List;

public final class zzaz extends zzaw {
    protected zzaz() {
        this.zza.add(zzbl.APPLY);
        this.zza.add(zzbl.BLOCK);
        this.zza.add(zzbl.BREAK);
        this.zza.add(zzbl.CASE);
        this.zza.add(zzbl.DEFAULT);
        this.zza.add(zzbl.CONTINUE);
        this.zza.add(zzbl.DEFINE_FUNCTION);
        this.zza.add(zzbl.FN);
        this.zza.add(zzbl.IF);
        this.zza.add(zzbl.QUOTE);
        this.zza.add(zzbl.RETURN);
        this.zza.add(zzbl.SWITCH);
        this.zza.add(zzbl.TERNARY);
    }

    private static zzap zzc(zzg zzg, List list) {
        zzh.zzi(zzbl.FN.name(), 2, list);
        zzap zzb = zzg.zzb((zzap) list.get(0));
        zzap zzb2 = zzg.zzb((zzap) list.get(1));
        if (zzb2 instanceof zzae) {
            List zzm = ((zzae) zzb2).zzm();
            List arrayList = new ArrayList();
            if (list.size() > 2) {
                arrayList = list.subList(2, list.size());
            }
            return new zzao(zzb.zzi(), zzm, arrayList, zzg);
        }
        throw new IllegalArgumentException(String.format("FN requires an ArrayValue of parameter names found %s", new Object[]{zzb2.getClass().getCanonicalName()}));
    }

    public final zzap zza(String str, zzg zzg, List list) {
        zzap zzap;
        zzbl zzbl = zzbl.ADD;
        int ordinal = zzh.zze(str).ordinal();
        if (ordinal == 2) {
            zzh.zzh(zzbl.APPLY.name(), 3, list);
            zzap zzb = zzg.zzb((zzap) list.get(0));
            String zzi = zzg.zzb((zzap) list.get(1)).zzi();
            zzap zzb2 = zzg.zzb((zzap) list.get(2));
            if (!(zzb2 instanceof zzae)) {
                throw new IllegalArgumentException(String.format("Function arguments for Apply are not a list found %s", new Object[]{zzb2.getClass().getCanonicalName()}));
            } else if (!zzi.isEmpty()) {
                return zzb.zzcz(zzi, zzg, ((zzae) zzb2).zzm());
            } else {
                throw new IllegalArgumentException("Function name for apply is undefined");
            }
        } else if (ordinal == 15) {
            zzh.zzh(zzbl.BREAK.name(), 0, list);
            return zzap.zzh;
        } else if (ordinal == 25) {
            return zzc(zzg, list);
        } else {
            if (ordinal == 41) {
                zzh.zzi(zzbl.IF.name(), 2, list);
                zzap zzb3 = zzg.zzb((zzap) list.get(0));
                zzap zzb4 = zzg.zzb((zzap) list.get(1));
                zzap zzb5 = list.size() > 2 ? zzg.zzb((zzap) list.get(2)) : null;
                zzap zzap2 = zzap.zzf;
                if (zzb3.zzg().booleanValue()) {
                    zzap = zzg.zzc((zzae) zzb4);
                } else {
                    zzap = zzb5 != null ? zzg.zzc((zzae) zzb5) : zzap2;
                }
                if (zzap instanceof zzag) {
                    return zzap;
                }
            } else if (ordinal == 54) {
                return new zzae(list);
            } else {
                if (ordinal != 57) {
                    if (ordinal != 19) {
                        if (ordinal == 20) {
                            zzh.zzi(zzbl.DEFINE_FUNCTION.name(), 2, list);
                            zzao zzao = (zzao) zzc(zzg, list);
                            if (zzao.zzc() == null) {
                                zzg.zzg("", zzao);
                                return zzao;
                            }
                            zzg.zzg(zzao.zzc(), zzao);
                            return zzao;
                        } else if (ordinal == 60) {
                            zzh.zzh(zzbl.SWITCH.name(), 3, list);
                            zzap zzb6 = zzg.zzb((zzap) list.get(0));
                            zzap zzb7 = zzg.zzb((zzap) list.get(1));
                            zzap zzb8 = zzg.zzb((zzap) list.get(2));
                            if (!(zzb7 instanceof zzae)) {
                                throw new IllegalArgumentException("Malformed SWITCH statement, cases are not a list");
                            } else if (zzb8 instanceof zzae) {
                                zzae zzae = (zzae) zzb7;
                                zzae zzae2 = (zzae) zzb8;
                                int i = 0;
                                boolean z = false;
                                while (true) {
                                    if (i < zzae.zzc()) {
                                        if (z || zzb6.equals(zzg.zzb(zzae.zze(i)))) {
                                            zzap zzb9 = zzg.zzb(zzae2.zze(i));
                                            if (!(zzb9 instanceof zzag)) {
                                                z = true;
                                            } else if (!((zzag) zzb9).zzc().equals("break")) {
                                                return zzb9;
                                            }
                                        } else {
                                            z = false;
                                        }
                                        i++;
                                    } else if (zzae.zzc() + 1 == zzae2.zzc()) {
                                        zzap zzb10 = zzg.zzb(zzae2.zze(zzae.zzc()));
                                        if (zzb10 instanceof zzag) {
                                            String zzc = ((zzag) zzb10).zzc();
                                            if (zzc.equals("return") || zzc.equals("continue")) {
                                                return zzb10;
                                            }
                                        }
                                    }
                                }
                            } else {
                                throw new IllegalArgumentException("Malformed SWITCH statement, case statements are not a list");
                            }
                        } else if (ordinal != 61) {
                            switch (ordinal) {
                                case 11:
                                    return zzg.zza().zzc(new zzae(list));
                                case 12:
                                    zzh.zzh(zzbl.BREAK.name(), 0, list);
                                    return zzap.zzi;
                                case 13:
                                    break;
                                default:
                                    return super.zzb(str);
                            }
                        } else {
                            zzh.zzh(zzbl.TERNARY.name(), 3, list);
                            if (zzg.zzb((zzap) list.get(0)).zzg().booleanValue()) {
                                return zzg.zzb((zzap) list.get(1));
                            }
                            return zzg.zzb((zzap) list.get(2));
                        }
                    }
                    if (list.isEmpty()) {
                        return zzap.zzf;
                    }
                    zzap zzb11 = zzg.zzb((zzap) list.get(0));
                    if (zzb11 instanceof zzae) {
                        return zzg.zzc((zzae) zzb11);
                    }
                    return zzap.zzf;
                } else if (list.isEmpty()) {
                    return zzap.zzj;
                } else {
                    zzh.zzh(zzbl.RETURN.name(), 1, list);
                    return new zzag("return", zzg.zzb((zzap) list.get(0)));
                }
            }
            return zzap.zzf;
        }
    }
}
