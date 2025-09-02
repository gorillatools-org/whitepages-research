package com.facebook.cache.disk;

import com.facebook.binaryresource.BinaryResource;
import com.facebook.cache.common.WriterCallback;
import java.util.Collection;

public interface DiskStorage {

    public interface Entry {
        String getId();

        long getSize();

        long getTimestamp();
    }

    public interface Inserter {
        boolean cleanUp();

        BinaryResource commit(Object obj);

        void writeData(WriterCallback writerCallback, Object obj);
    }

    void clearAll();

    boolean contains(String str, Object obj);

    Collection getEntries();

    BinaryResource getResource(String str, Object obj);

    Inserter insert(String str, Object obj);

    boolean isExternal();

    void purgeUnexpectedResources();

    long remove(Entry entry);

    long remove(String str);

    boolean touch(String str, Object obj);
}
