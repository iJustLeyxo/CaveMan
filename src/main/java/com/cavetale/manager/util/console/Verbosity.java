package com.cavetale.manager.util.console;

import org.jetbrains.annotations.NotNull;

public enum Verbosity implements VerbosityProvider {
    OVERRIDE(Integer.MIN_VALUE),
    SILENT(-2),
    QUIET(-1),
    DEFAULT(0),
    VERBOSE(1);

    public final int value;

    Verbosity(int value) {
        this.value = value;
    }

    @Override
    public @NotNull Verbosity verbosity() {
        return this;
    }
}
