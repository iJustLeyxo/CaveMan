package com.cavetale.manager.parser.container;

import com.cavetale.manager.parser.InputException;
import org.jetbrains.annotations.NotNull;

public class EmptyContainer {
    public boolean option(@NotNull String option) throws InputException {
        return false;
    }

    public boolean isEmpty() {
        return true;
    }
}
