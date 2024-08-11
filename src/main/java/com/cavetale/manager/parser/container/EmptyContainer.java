package com.cavetale.manager.parser.container;

import com.cavetale.manager.parser.InputException;
import org.jetbrains.annotations.NotNull;

/**
 * Empty container, used for flags with no options / arguments
 */
public class EmptyContainer {
    /**
     * Adds an option to the container
     * @param option The option to add
     * @return {@code true} if the option could be added
     * @throws InputException If adding should've been possible but failed
     */
    public boolean option(@NotNull String option) throws InputException {
        return false;
    }

    public boolean isEmpty() {
        return true;
    }
}
