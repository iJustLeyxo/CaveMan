package com.cavetale.manager.util.console;

import org.jetbrains.annotations.NotNull;

public enum XCode implements XCoded {
    RESET("\u001B[0m"),
    BOLD("\u001B[1m"),
    WEIGHT_OFF("\u001B[22m"),
    GRAY("\u001B[1;30m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    MAGENTA("\u001B[35m"),
    WHITE("\u001B[37m");

    private final @NotNull String code;

    XCode(@NotNull String code) {
        this.code = code;
    }

    @Override
    public @NotNull String toString() {
        return this.code;
    }
}
