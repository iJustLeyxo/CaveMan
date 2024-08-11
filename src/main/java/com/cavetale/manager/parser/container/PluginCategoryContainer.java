package com.cavetale.manager.parser.container;

import com.cavetale.manager.data.plugin.Category;
import com.cavetale.manager.parser.InputException;
import com.cavetale.manager.util.console.Console;
import com.cavetale.manager.util.console.Type;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public final class PluginCategoryContainer extends EmptyContainer {
    private final @NotNull Set<Category> categories = new HashSet<>();

    @Override
    public boolean option(@NotNull String option) throws InputException {
        Category category = Category.get(option);
        if (this.categories.contains(category)) {
            Console.log(Type.INFO, "Ignoring duplicate category \"" + option + "\n");
        } else {
            this.categories.add(category);
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        return this.categories.isEmpty();
    }

    public @NotNull Set<Category> get() { return this.categories; }
}
