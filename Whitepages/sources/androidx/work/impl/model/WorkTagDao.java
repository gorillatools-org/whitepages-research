package androidx.work.impl.model;

import com.salesforce.marketingcloud.storage.db.k;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;

public interface WorkTagDao {
    void deleteByWorkSpecId(String str);

    List getTagsForWorkSpecId(String str);

    void insert(WorkTag workTag);

    void insertTags(String str, Set set);

    public static final class DefaultImpls {
        public static void insertTags(WorkTagDao workTagDao, String str, Set set) {
            Intrinsics.checkNotNullParameter(str, "id");
            Intrinsics.checkNotNullParameter(set, k.a.g);
            Iterator it = set.iterator();
            while (it.hasNext()) {
                workTagDao.insert(new WorkTag((String) it.next(), str));
            }
        }
    }
}
