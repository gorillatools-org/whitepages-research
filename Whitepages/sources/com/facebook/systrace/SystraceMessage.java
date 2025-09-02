package com.facebook.systrace;

import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public final class SystraceMessage {
    public static boolean INCLUDE_ARGS;
    public static final SystraceMessage INSTANCE = new SystraceMessage();

    public static abstract class Builder {
        public abstract Builder arg(String str, int i);

        public abstract Builder arg(String str, Object obj);

        public abstract void flush();
    }

    private SystraceMessage() {
    }

    public static final Builder beginSection(long j, String str) {
        Intrinsics.checkNotNullParameter(str, "sectionName");
        return new StartSectionBuilder(j, str);
    }

    public static final Builder endSection(long j) {
        return new EndSectionBuilder(j);
    }

    private static final class StartSectionBuilder extends Builder {
        private final List args = new ArrayList();
        private final String sectionName;
        private final long tag;

        public StartSectionBuilder(long j, String str) {
            Intrinsics.checkNotNullParameter(str, "sectionName");
            this.tag = j;
            this.sectionName = str;
        }

        public void flush() {
            String str;
            long j = this.tag;
            String str2 = this.sectionName;
            if (!SystraceMessage.INCLUDE_ARGS || this.args.isEmpty()) {
                str = "";
            } else {
                str = " (" + String.join(", ", this.args) + ")";
            }
            Systrace.beginSection(j, str2 + str);
        }

        public Builder arg(String str, Object obj) {
            Intrinsics.checkNotNullParameter(str, "key");
            Intrinsics.checkNotNullParameter(obj, "value");
            addArg(str, obj.toString());
            return this;
        }

        public Builder arg(String str, int i) {
            Intrinsics.checkNotNullParameter(str, "key");
            addArg(str, String.valueOf(i));
            return this;
        }

        private final void addArg(String str, String str2) {
            List list = this.args;
            list.add(str + ": " + str2);
        }
    }

    private static final class EndSectionBuilder extends Builder {
        private final long tag;

        public Builder arg(String str, int i) {
            Intrinsics.checkNotNullParameter(str, "key");
            return this;
        }

        public Builder arg(String str, Object obj) {
            Intrinsics.checkNotNullParameter(str, "key");
            Intrinsics.checkNotNullParameter(obj, "value");
            return this;
        }

        public EndSectionBuilder(long j) {
            this.tag = j;
        }

        public void flush() {
            Systrace.endSection(this.tag);
        }
    }
}
