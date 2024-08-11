package com.cavetale.manager.util.console;

public enum Detail {
    OVERRIDE(Integer.MIN_VALUE),
    MIN(-2),
    LOW(-1),
    STD(0),
    HIGH(1);

    public final int val;

    Detail(int val) {
        this.val = val;
    }
}
