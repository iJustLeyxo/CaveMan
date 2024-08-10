package com.cavetale.manager.parser;

import org.jetbrains.annotations.NotNull;

public abstract class InputException extends Exception {
    public InputException(@NotNull String message) {
        super(message);
    }
}
