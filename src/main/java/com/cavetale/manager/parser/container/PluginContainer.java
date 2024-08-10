package com.cavetale.manager.parser.container;

import com.cavetale.manager.data.Plugin;
import com.cavetale.manager.parser.InputException;
import com.cavetale.manager.util.cmd.Cmd;
import com.cavetale.manager.util.cmd.Style;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public final class PluginContainer extends Container {
    private final @NotNull Set<Plugin> plugins = new HashSet<>();

    @Override
    public boolean option(@NotNull String option) throws InputException {
        Plugin plugin = Plugin.get(option);
        if (this.plugins.contains(plugin)) {
            Cmd.out(Style.INFO, "Ignoring duplicate plugins \"" + option + "\n");
        }
        this.plugins.add(plugin);
        return true;
    }

    @Override
    public boolean isEmpty() {
        return this.plugins.isEmpty();
    }

    public @NotNull Set<Plugin> get() { return this.plugins; }
}
