package com.cavetale.manager.parser.container;

import com.cavetale.manager.data.server.Software;
import com.cavetale.manager.parser.InputException;
import org.jetbrains.annotations.NotNull;

/**
 * Software container, used to store software from a software flag
 */
public final class SoftwareContainer extends SetContainer<Software> {
    @Override
    public boolean option(@NotNull String option) throws InputException {
        this.contents.add(Software.get(option));
        return true;
    }
}
