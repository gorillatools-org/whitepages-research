package com.facebook.soloader;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.ClosedByInterruptException;
import okhttp3.internal.ws.WebSocketProtocol;

public abstract class MinElf {
    private static String[] extract_DT_NEEDED_with_retries(ElfFileChannel elfFileChannel) {
        int i = 0;
        while (true) {
            try {
                return extract_DT_NEEDED_no_retries(elfFileChannel);
            } catch (ClosedByInterruptException e) {
                i++;
                if (i <= 4) {
                    Thread.interrupted();
                    LogUtil.e("MinElf", "retrying extract_DT_NEEDED due to ClosedByInterruptException", e);
                    elfFileChannel.openChannel();
                } else {
                    throw e;
                }
            }
        }
    }

    public static String[] extract_DT_NEEDED(ElfByteChannel elfByteChannel) {
        if (elfByteChannel instanceof ElfFileChannel) {
            return extract_DT_NEEDED_with_retries((ElfFileChannel) elfByteChannel);
        }
        return extract_DT_NEEDED_no_retries(elfByteChannel);
    }

    private static String[] extract_DT_NEEDED_no_retries(ElfByteChannel elfByteChannel) {
        long j;
        long j2;
        String str;
        long j3;
        long j4;
        long j5;
        String str2;
        long j6;
        long j7;
        long j8;
        long j9;
        long j10;
        long r9;
        long j11;
        ElfByteChannel elfByteChannel2 = elfByteChannel;
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        long j12 = getu32(elfByteChannel2, allocate, 0);
        if (j12 == 1179403647) {
            boolean z = true;
            if (getu8(elfByteChannel2, allocate, 4) != 1) {
                z = false;
            }
            if (getu8(elfByteChannel2, allocate, 5) == 2) {
                allocate.order(ByteOrder.BIG_ENDIAN);
            }
            long r15 = z ? getu32(elfByteChannel2, allocate, 28) : get64(elfByteChannel2, allocate, 32);
            long j13 = z ? (long) getu16(elfByteChannel2, allocate, 44) : (long) getu16(elfByteChannel2, allocate, 56);
            if (z) {
                j = 42;
            } else {
                j = 54;
            }
            int i = getu16(elfByteChannel2, allocate, j);
            if (j13 == WebSocketProtocol.PAYLOAD_SHORT_MAX) {
                long r2 = z ? getu32(elfByteChannel2, allocate, 32) : get64(elfByteChannel2, allocate, 40);
                if (z) {
                    j13 = getu32(elfByteChannel2, allocate, r2 + 28);
                } else {
                    j13 = getu32(elfByteChannel2, allocate, r2 + 44);
                }
            }
            long j14 = r15;
            long j15 = 0;
            while (true) {
                if (j15 >= j13) {
                    j2 = 0;
                    break;
                }
                if (z) {
                    j11 = getu32(elfByteChannel2, allocate, j14);
                } else {
                    j11 = getu32(elfByteChannel2, allocate, j14);
                }
                if (j11 != 2) {
                    j14 += (long) i;
                    j15++;
                } else if (z) {
                    j2 = getu32(elfByteChannel2, allocate, j14 + 4);
                } else {
                    j2 = get64(elfByteChannel2, allocate, j14 + 8);
                }
            }
            if (j2 != 0) {
                long j16 = j2;
                int i2 = 0;
                long j17 = 0;
                while (true) {
                    long r28 = z ? getu32(elfByteChannel2, allocate, j16) : get64(elfByteChannel2, allocate, j16);
                    if (r28 == 1) {
                        if (i2 != Integer.MAX_VALUE) {
                            i2++;
                            str = "malformed DT_NEEDED section";
                        } else {
                            throw new ElfError("malformed DT_NEEDED section");
                        }
                    } else if (r28 == 5) {
                        str = "malformed DT_NEEDED section";
                        if (z) {
                            r9 = getu32(elfByteChannel2, allocate, j16 + 4);
                        } else {
                            r9 = get64(elfByteChannel2, allocate, j16 + 8);
                        }
                        j17 = r9;
                    } else {
                        str = "malformed DT_NEEDED section";
                    }
                    j16 += z ? 8 : 16;
                    if (r28 == 0) {
                        if (j17 != 0) {
                            long j18 = r15;
                            int i3 = 0;
                            while (true) {
                                if (((long) i3) >= j13) {
                                    j3 = j2;
                                    j4 = 0;
                                    j5 = 0;
                                    break;
                                }
                                if (z) {
                                    j6 = getu32(elfByteChannel2, allocate, j18);
                                } else {
                                    j6 = getu32(elfByteChannel2, allocate, j18);
                                }
                                if (j6 == 1) {
                                    if (z) {
                                        j7 = j13;
                                        j8 = getu32(elfByteChannel2, allocate, j18 + 8);
                                    } else {
                                        j7 = j13;
                                        j8 = get64(elfByteChannel2, allocate, j18 + 16);
                                    }
                                    if (z) {
                                        j3 = j2;
                                        j9 = getu32(elfByteChannel2, allocate, j18 + 20);
                                    } else {
                                        j3 = j2;
                                        j9 = get64(elfByteChannel2, allocate, j18 + 40);
                                    }
                                    if (j8 <= j17 && j17 < j9 + j8) {
                                        if (z) {
                                            j10 = getu32(elfByteChannel2, allocate, j18 + 4);
                                        } else {
                                            j10 = get64(elfByteChannel2, allocate, j18 + 8);
                                        }
                                        j5 = j10 + (j17 - j8);
                                        j4 = 0;
                                    }
                                } else {
                                    j7 = j13;
                                    j3 = j2;
                                }
                                j18 += (long) i;
                                i3++;
                                j13 = j7;
                                j2 = j3;
                            }
                            if (j5 != j4) {
                                String[] strArr = new String[i2];
                                long j19 = j3;
                                int i4 = 0;
                                while (true) {
                                    long r92 = z ? getu32(elfByteChannel2, allocate, j19) : get64(elfByteChannel2, allocate, j19);
                                    if (r92 == 1) {
                                        strArr[i4] = getSz(elfByteChannel2, allocate, (z ? getu32(elfByteChannel2, allocate, j19 + 4) : get64(elfByteChannel2, allocate, j19 + 8)) + j5);
                                        if (i4 != Integer.MAX_VALUE) {
                                            i4++;
                                            str2 = str;
                                        } else {
                                            throw new ElfError(str);
                                        }
                                    } else {
                                        str2 = str;
                                    }
                                    j19 += z ? 8 : 16;
                                    if (r92 != 0) {
                                        str = str2;
                                    } else if (i4 == i2) {
                                        return strArr;
                                    } else {
                                        throw new ElfError(str2);
                                    }
                                }
                            } else {
                                throw new ElfError("did not find file offset of DT_STRTAB table");
                            }
                        } else {
                            throw new ElfError("Dynamic section string-table not found");
                        }
                    }
                }
            } else {
                throw new ElfError("ELF file does not contain dynamic linking information");
            }
        } else {
            throw new ElfError("file is not ELF: magic is 0x" + Long.toHexString(j12) + ", it should be " + Long.toHexString(1179403647));
        }
    }

    private static String getSz(ElfByteChannel elfByteChannel, ByteBuffer byteBuffer, long j) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            long j2 = 1 + j;
            short u8Var = getu8(elfByteChannel, byteBuffer, j);
            if (u8Var == 0) {
                return sb.toString();
            }
            sb.append((char) u8Var);
            j = j2;
        }
    }

    private static void read(ElfByteChannel elfByteChannel, ByteBuffer byteBuffer, int i, long j) {
        int read;
        byteBuffer.position(0);
        byteBuffer.limit(i);
        while (byteBuffer.remaining() > 0 && (read = elfByteChannel.read(byteBuffer, j)) != -1) {
            j += (long) read;
        }
        if (byteBuffer.remaining() <= 0) {
            byteBuffer.position(0);
            return;
        }
        throw new ElfError("ELF file truncated");
    }

    private static long get64(ElfByteChannel elfByteChannel, ByteBuffer byteBuffer, long j) {
        read(elfByteChannel, byteBuffer, 8, j);
        return byteBuffer.getLong();
    }

    private static long getu32(ElfByteChannel elfByteChannel, ByteBuffer byteBuffer, long j) {
        read(elfByteChannel, byteBuffer, 4, j);
        return ((long) byteBuffer.getInt()) & 4294967295L;
    }

    private static int getu16(ElfByteChannel elfByteChannel, ByteBuffer byteBuffer, long j) {
        read(elfByteChannel, byteBuffer, 2, j);
        return byteBuffer.getShort() & 65535;
    }

    private static short getu8(ElfByteChannel elfByteChannel, ByteBuffer byteBuffer, long j) {
        read(elfByteChannel, byteBuffer, 1, j);
        return (short) (byteBuffer.get() & 255);
    }

    protected static class ElfError extends UnsatisfiedLinkError {
        ElfError(String str) {
            super(str);
        }
    }
}
