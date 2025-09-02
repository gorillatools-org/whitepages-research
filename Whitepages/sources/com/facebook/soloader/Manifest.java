package com.facebook.soloader;

import java.io.DataInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Manifest {
    public final String arch;
    public final List libs;

    Manifest(String str, List list) {
        this.arch = str;
        this.libs = Collections.unmodifiableList(list);
    }

    public static Manifest read(InputStream inputStream) {
        return read(new DataInputStream(inputStream));
    }

    public static Manifest read(DataInputStream dataInputStream) {
        String readArch = readArch(dataInputStream);
        short readShort = dataInputStream.readShort() & 65535;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < readShort; i++) {
            arrayList.add(readLib(dataInputStream));
        }
        return new Manifest(readArch, arrayList);
    }

    private static String readArch(DataInputStream dataInputStream) {
        byte readByte = dataInputStream.readByte();
        if (readByte == 1) {
            return "arm64-v8a";
        }
        if (readByte == 2) {
            return "armeabi-v7a";
        }
        if (readByte == 3) {
            return "x86_64";
        }
        if (readByte == 4) {
            return "x86";
        }
        throw new RuntimeException("Unrecognized arch id: " + readByte);
    }

    private static String readLib(DataInputStream dataInputStream) {
        byte[] bArr = new byte[(dataInputStream.readShort() & 65535)];
        dataInputStream.readFully(bArr);
        return new String(bArr, StandardCharsets.UTF_8);
    }
}
