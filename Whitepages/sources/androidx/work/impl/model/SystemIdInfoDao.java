package androidx.work.impl.model;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public interface SystemIdInfoDao {
    SystemIdInfo getSystemIdInfo(WorkGenerationalId workGenerationalId);

    SystemIdInfo getSystemIdInfo(String str, int i);

    List getWorkSpecIds();

    void insertSystemIdInfo(SystemIdInfo systemIdInfo);

    void removeSystemIdInfo(WorkGenerationalId workGenerationalId);

    void removeSystemIdInfo(String str);

    void removeSystemIdInfo(String str, int i);

    public static final class DefaultImpls {
        public static SystemIdInfo getSystemIdInfo(SystemIdInfoDao systemIdInfoDao, WorkGenerationalId workGenerationalId) {
            Intrinsics.checkNotNullParameter(workGenerationalId, "id");
            return systemIdInfoDao.getSystemIdInfo(workGenerationalId.getWorkSpecId(), workGenerationalId.getGeneration());
        }

        public static void removeSystemIdInfo(SystemIdInfoDao systemIdInfoDao, WorkGenerationalId workGenerationalId) {
            Intrinsics.checkNotNullParameter(workGenerationalId, "id");
            systemIdInfoDao.removeSystemIdInfo(workGenerationalId.getWorkSpecId(), workGenerationalId.getGeneration());
        }
    }
}
