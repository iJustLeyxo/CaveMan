package com.cavetale.manager.parser.container;

import com.cavetale.manager.data.ServerSoftware;
import com.cavetale.manager.parser.InputException;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public final class ServerSoftwareContainer extends Container {
    private final @NotNull Set<ServerSoftware> serverSoftware = new HashSet<>();

    @Override
    public boolean option(@NotNull String option) throws InputException {
        this.serverSoftware.add(ServerSoftware.get(option));
        return true;
    }

    @Override
    public boolean isEmpty() {
        return serverSoftware.isEmpty();
    }

    public @NotNull Set<ServerSoftware> get() { return this.serverSoftware; }
}
