package com.cavetale.manager.util.console;

import org.jetbrains.annotations.NotNull;

public enum Detail implements Detailed {
    OVERRIDE(Integer.MIN_VALUE),
    MINIMAL(-2),
    LOW(-1),
    DEFAULT(0),
    HIGH(1);

    public final int value;

    Detail(int value) {
        this.value = value;
    }

    @Override
    public @NotNull Detail detail() {
        return this;
    }
}
