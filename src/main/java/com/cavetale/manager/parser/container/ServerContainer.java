package com.cavetale.manager.parser.container;

import com.cavetale.manager.data.plugin.Server;
import com.cavetale.manager.parser.InputException;
import com.cavetale.manager.util.console.Console;
import com.cavetale.manager.util.console.Type;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

/**
 * Server container, used to store servers from a server flag
 */
public final class ServerContainer extends EmptyContainer {
    private final @NotNull Set<Server> servers = new HashSet<>();

    @Override
    public boolean option(@NotNull String option) throws InputException {
        Server server = Server.get(option);
        if (this.servers.contains(server)) {
            Console.log(Type.INFO, "Ignoring duplicate \"" + option + "\n");
        } else {
            this.servers.add(server);
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        return servers.isEmpty();
    }

    public @NotNull Set<Server> get() { return this.servers; }
}
