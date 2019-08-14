package com.cdtian.aspect;

public aspect ChangeCallsToCurrentTimeInMillisMethod {
    long around():
            call(public static native long java.lang.System.currentTimeMillis())
                    && within(com.cdtian.*) {
        return 0;
    }
}
