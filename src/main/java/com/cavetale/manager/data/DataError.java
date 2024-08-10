package com.cavetale.manager.data;

import org.jetbrains.annotations.NotNull;

public abstract class DataError extends Error {
    public DataError(@NotNull String message, @NotNull Throwable cause) {
        super(message, cause);
    }
}
