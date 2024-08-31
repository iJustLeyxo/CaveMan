package com.cavetale.manager.data;

import org.jetbrains.annotations.NotNull;

/**
 * Data error, used for severe issues with hardcoded data
 */
public abstract class DataException extends Exception {
    public DataException(@NotNull String message) {
        super(message);
    }
}