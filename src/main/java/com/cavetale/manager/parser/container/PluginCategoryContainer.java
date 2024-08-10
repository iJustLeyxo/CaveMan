package com.cavetale.manager.parser.container;

import com.cavetale.manager.data.PluginCategory;
import com.cavetale.manager.parser.InputException;
import com.cavetale.manager.util.cmd.Cmd;
import com.cavetale.manager.util.cmd.Style;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public final class PluginCategoryContainer extends Container {
    private final @NotNull Set<PluginCategory> categories = new HashSet<>();

    @Override
    public boolean option(@NotNull String option) throws InputException {
        PluginCategory pluginCategory = PluginCategory.get(option);
        if (this.categories.contains(pluginCategory)) {
            Cmd.out(Style.INFO, "Ignoring duplicate category \"" + option + "\n");
        } else {
            this.categories.add(pluginCategory);
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        return this.categories.isEmpty();
    }

    public @NotNull Set<PluginCategory> get() { return this.categories; }
}
