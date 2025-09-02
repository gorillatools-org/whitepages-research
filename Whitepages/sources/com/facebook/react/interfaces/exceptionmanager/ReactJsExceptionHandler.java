package com.facebook.react.interfaces.exceptionmanager;

import com.facebook.proguard.annotations.DoNotStripAny;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.react.common.annotations.UnstableReactNativeAPI;
import com.facebook.react.devsupport.StackTraceHelper;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

@DoNotStripAny
@UnstableReactNativeAPI
public interface ReactJsExceptionHandler {

    @DoNotStripAny
    public interface ProcessedError {

        @DoNotStripAny
        public interface StackFrame {
            Integer getColumn();

            String getFile();

            Integer getLineNumber();

            String getMethodName();
        }

        String getComponentStack();

        ReadableMap getExtraData();

        int getId();

        String getMessage();

        String getName();

        String getOriginalMessage();

        List<StackFrame> getStack();

        boolean isFatal();
    }

    void reportJsException(ProcessedError processedError);

    @DoNotStripAny
    private static final class ProcessedErrorStackFrameImpl implements ProcessedError.StackFrame {
        private final Integer column;
        private final String file;
        private final Integer lineNumber;
        private final String methodName;

        public static /* synthetic */ ProcessedErrorStackFrameImpl copy$default(ProcessedErrorStackFrameImpl processedErrorStackFrameImpl, String str, String str2, Integer num, Integer num2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = processedErrorStackFrameImpl.file;
            }
            if ((i & 2) != 0) {
                str2 = processedErrorStackFrameImpl.methodName;
            }
            if ((i & 4) != 0) {
                num = processedErrorStackFrameImpl.lineNumber;
            }
            if ((i & 8) != 0) {
                num2 = processedErrorStackFrameImpl.column;
            }
            return processedErrorStackFrameImpl.copy(str, str2, num, num2);
        }

        public final String component1() {
            return this.file;
        }

        public final String component2() {
            return this.methodName;
        }

        public final Integer component3() {
            return this.lineNumber;
        }

        public final Integer component4() {
            return this.column;
        }

        public final ProcessedErrorStackFrameImpl copy(String str, String str2, Integer num, Integer num2) {
            Intrinsics.checkNotNullParameter(str2, "methodName");
            return new ProcessedErrorStackFrameImpl(str, str2, num, num2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ProcessedErrorStackFrameImpl)) {
                return false;
            }
            ProcessedErrorStackFrameImpl processedErrorStackFrameImpl = (ProcessedErrorStackFrameImpl) obj;
            return Intrinsics.areEqual((Object) this.file, (Object) processedErrorStackFrameImpl.file) && Intrinsics.areEqual((Object) this.methodName, (Object) processedErrorStackFrameImpl.methodName) && Intrinsics.areEqual((Object) this.lineNumber, (Object) processedErrorStackFrameImpl.lineNumber) && Intrinsics.areEqual((Object) this.column, (Object) processedErrorStackFrameImpl.column);
        }

        public int hashCode() {
            String str = this.file;
            int i = 0;
            int hashCode = (((str == null ? 0 : str.hashCode()) * 31) + this.methodName.hashCode()) * 31;
            Integer num = this.lineNumber;
            int hashCode2 = (hashCode + (num == null ? 0 : num.hashCode())) * 31;
            Integer num2 = this.column;
            if (num2 != null) {
                i = num2.hashCode();
            }
            return hashCode2 + i;
        }

        public String toString() {
            String str = this.file;
            String str2 = this.methodName;
            Integer num = this.lineNumber;
            Integer num2 = this.column;
            return "ProcessedErrorStackFrameImpl(file=" + str + ", methodName=" + str2 + ", lineNumber=" + num + ", column=" + num2 + ")";
        }

        public ProcessedErrorStackFrameImpl(String str, String str2, Integer num, Integer num2) {
            Intrinsics.checkNotNullParameter(str2, "methodName");
            this.file = str;
            this.methodName = str2;
            this.lineNumber = num;
            this.column = num2;
        }

        public String getFile() {
            return this.file;
        }

        public String getMethodName() {
            return this.methodName;
        }

        public Integer getLineNumber() {
            return this.lineNumber;
        }

        public Integer getColumn() {
            return this.column;
        }
    }

    @DoNotStripAny
    private static final class ProcessedErrorImpl implements ProcessedError {
        private final String componentStack;
        private final ReadableNativeMap extraData;
        private final int id;
        private final boolean isFatal;
        private final String message;
        private final String name;
        private final String originalMessage;
        private final ArrayList<ProcessedErrorStackFrameImpl> stack;

        public static /* synthetic */ ProcessedErrorImpl copy$default(ProcessedErrorImpl processedErrorImpl, String str, String str2, String str3, String str4, ArrayList arrayList, int i, boolean z, ReadableNativeMap readableNativeMap, int i2, Object obj) {
            ProcessedErrorImpl processedErrorImpl2 = processedErrorImpl;
            int i3 = i2;
            return processedErrorImpl.copy((i3 & 1) != 0 ? processedErrorImpl2.message : str, (i3 & 2) != 0 ? processedErrorImpl2.originalMessage : str2, (i3 & 4) != 0 ? processedErrorImpl2.name : str3, (i3 & 8) != 0 ? processedErrorImpl2.componentStack : str4, (i3 & 16) != 0 ? processedErrorImpl2.stack : arrayList, (i3 & 32) != 0 ? processedErrorImpl2.id : i, (i3 & 64) != 0 ? processedErrorImpl2.isFatal : z, (i3 & 128) != 0 ? processedErrorImpl2.extraData : readableNativeMap);
        }

        public final String component1() {
            return this.message;
        }

        public final String component2() {
            return this.originalMessage;
        }

        public final String component3() {
            return this.name;
        }

        public final String component4() {
            return this.componentStack;
        }

        public final ArrayList<ProcessedErrorStackFrameImpl> component5() {
            return this.stack;
        }

        public final int component6() {
            return this.id;
        }

        public final boolean component7() {
            return this.isFatal;
        }

        public final ReadableNativeMap component8() {
            return this.extraData;
        }

        public final ProcessedErrorImpl copy(String str, String str2, String str3, String str4, ArrayList<ProcessedErrorStackFrameImpl> arrayList, int i, boolean z, ReadableNativeMap readableNativeMap) {
            Intrinsics.checkNotNullParameter(str, StackTraceHelper.MESSAGE_KEY);
            Intrinsics.checkNotNullParameter(arrayList, StackTraceHelper.STACK_KEY);
            ReadableNativeMap readableNativeMap2 = readableNativeMap;
            Intrinsics.checkNotNullParameter(readableNativeMap2, "extraData");
            return new ProcessedErrorImpl(str, str2, str3, str4, arrayList, i, z, readableNativeMap2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ProcessedErrorImpl)) {
                return false;
            }
            ProcessedErrorImpl processedErrorImpl = (ProcessedErrorImpl) obj;
            return Intrinsics.areEqual((Object) this.message, (Object) processedErrorImpl.message) && Intrinsics.areEqual((Object) this.originalMessage, (Object) processedErrorImpl.originalMessage) && Intrinsics.areEqual((Object) this.name, (Object) processedErrorImpl.name) && Intrinsics.areEqual((Object) this.componentStack, (Object) processedErrorImpl.componentStack) && Intrinsics.areEqual((Object) this.stack, (Object) processedErrorImpl.stack) && this.id == processedErrorImpl.id && this.isFatal == processedErrorImpl.isFatal && Intrinsics.areEqual((Object) this.extraData, (Object) processedErrorImpl.extraData);
        }

        public int hashCode() {
            int hashCode = this.message.hashCode() * 31;
            String str = this.originalMessage;
            int i = 0;
            int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.name;
            int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.componentStack;
            if (str3 != null) {
                i = str3.hashCode();
            }
            return ((((((((hashCode3 + i) * 31) + this.stack.hashCode()) * 31) + Integer.hashCode(this.id)) * 31) + Boolean.hashCode(this.isFatal)) * 31) + this.extraData.hashCode();
        }

        public String toString() {
            String str = this.message;
            String str2 = this.originalMessage;
            String str3 = this.name;
            String str4 = this.componentStack;
            ArrayList<ProcessedErrorStackFrameImpl> arrayList = this.stack;
            int i = this.id;
            boolean z = this.isFatal;
            ReadableNativeMap readableNativeMap = this.extraData;
            return "ProcessedErrorImpl(message=" + str + ", originalMessage=" + str2 + ", name=" + str3 + ", componentStack=" + str4 + ", stack=" + arrayList + ", id=" + i + ", isFatal=" + z + ", extraData=" + readableNativeMap + ")";
        }

        public ProcessedErrorImpl(String str, String str2, String str3, String str4, ArrayList<ProcessedErrorStackFrameImpl> arrayList, int i, boolean z, ReadableNativeMap readableNativeMap) {
            Intrinsics.checkNotNullParameter(str, StackTraceHelper.MESSAGE_KEY);
            Intrinsics.checkNotNullParameter(arrayList, StackTraceHelper.STACK_KEY);
            Intrinsics.checkNotNullParameter(readableNativeMap, "extraData");
            this.message = str;
            this.originalMessage = str2;
            this.name = str3;
            this.componentStack = str4;
            this.stack = arrayList;
            this.id = i;
            this.isFatal = z;
            this.extraData = readableNativeMap;
        }

        public String getMessage() {
            return this.message;
        }

        public String getOriginalMessage() {
            return this.originalMessage;
        }

        public String getName() {
            return this.name;
        }

        public String getComponentStack() {
            return this.componentStack;
        }

        public ArrayList<ProcessedErrorStackFrameImpl> getStack() {
            return this.stack;
        }

        public int getId() {
            return this.id;
        }

        public boolean isFatal() {
            return this.isFatal;
        }

        public ReadableNativeMap getExtraData() {
            return this.extraData;
        }
    }
}
