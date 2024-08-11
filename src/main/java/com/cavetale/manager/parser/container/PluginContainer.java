package com.cavetale.manager.parser.container;

import com.cavetale.manager.data.plugin.Plugin;
import com.cavetale.manager.parser.InputException;
import com.cavetale.manager.util.console.Console;
import com.cavetale.manager.util.console.Type;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public final class PluginContainer extends EmptyContainer {
    private final @NotNull Set<Plugin> plugins = new HashSet<>();

    @Override
    public boolean option(@NotNull String option) throws InputException {
        Plugin plugin = Plugin.get(option);
        if (this.plugins.contains(plugin)) {
            Console.log(Type.INFO, "Ignoring duplicate plugins \"" + option + "\n");
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
