package com.cavetale.manager.parser.container;

import com.cavetale.manager.data.ServerConfig;
import com.cavetale.manager.parser.InputException;
import com.cavetale.manager.util.cmd.Cmd;
import com.cavetale.manager.util.cmd.Style;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public final class ServerConfigContainer extends Container {
    private final @NotNull Set<ServerConfig> serverConfigs = new HashSet<>();

    @Override
    public boolean option(@NotNull String option) throws InputException {
        ServerConfig serverConfig = ServerConfig.get(option);
        if (this.serverConfigs.contains(serverConfig)) {
            Cmd.out(Style.INFO, "Ignoring duplicate \"" + option + "\n");
        } else {
            this.serverConfigs.add(serverConfig);
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        return serverConfigs.isEmpty();
    }

    public @NotNull Set<ServerConfig> get() { return this.serverConfigs; }
}
