package com.cavetale.manager.parser.container;

import com.cavetale.manager.data.server.Software;
import com.cavetale.manager.parser.InputException;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public final class ServerSoftwareContainer extends EmptyContainer {
    private final @NotNull Set<Software> software = new HashSet<>();

    @Override
    public boolean option(@NotNull String option) throws InputException {
        this.software.add(Software.get(option));
        return true;
    }

    @Override
    public boolean isEmpty() {
        return software.isEmpty();
    }

    public @NotNull Set<Software> get() { return this.software; }
}
