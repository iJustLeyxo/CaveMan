package com.cavetale.manager.data.plugin;

import com.cavetale.manager.parser.Flag;
import com.cavetale.manager.parser.Tokens;
import com.cavetale.manager.parser.container.PluginCategoryContainer;
import com.cavetale.manager.parser.container.PluginContainer;
import com.cavetale.manager.parser.container.ServerConfigContainer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class PluginManager {
    private final @NotNull Tokens tokens;
    private final @NotNull Map<Plugin, Details> plugins = new HashMap<>();
    private final @NotNull Set<String> unknown = new HashSet<>();

    public PluginManager(@NotNull Tokens tokens) {
        this.tokens = tokens;
        this.resolve();
    }

    private void resolve() {
        this.plugins.clear();
        this.unknown.clear();
        Set<Plugin> selected = new HashSet<>();
        if (this.tokens.flags().containsKey(Flag.PLUGIN)) {
            selected.addAll(((PluginContainer) this.tokens.flags().get(Flag.PLUGIN)).get());
        }
        if (this.tokens.flags().containsKey(Flag.CATEGORY)) {
            for (Category category : ((PluginCategoryContainer) this.tokens.flags().get(Flag.CATEGORY)).get()) {
                selected.addAll(category.plugins());
            }
        }
        if (this.tokens.flags().containsKey(Flag.SERVER)) {
            for (Server server : ((ServerConfigContainer) this.tokens.flags().get(Flag.SERVER)).get()) {
                selected.addAll(server.plugins());
            }
        }
        File folder = new File("plugins/");
        File[] files = folder.listFiles();
        if (folder.exists() && files != null) {
            for (File f : files) {
                String ref = f.getName();
                if (!ref.endsWith(".jar")) {
                    continue;
                }
                ref = ref.substring(0, ref.length() - 4);
                try {
                    Plugin p = Plugin.get(ref);
                    this.plugins.put(p, new Details(
                            selected.contains(p),
                            true,
                            Files.isSymbolicLink(f.toPath())
                    ));
                } catch (Plugin.NotFoundException e) {
                    this.unknown.add(ref);
                }
            }
        }
        for (Plugin p : Plugin.values()) {
            if (this.plugins.containsKey(p)) {
                continue;
            }
            this.plugins.put(p, new Details(
                    selected.contains(p),
                    false,
                    null
            ));
        }
    }

    public @NotNull Set<Plugin> get(@Nullable Boolean installed, @Nullable Boolean selected, @Nullable Boolean link) {
        Set<Plugin> plugins = new HashSet<>();
        for (Map.Entry<Plugin, Details> p : this.plugins.entrySet()) {
            if ((installed == null || installed == p.getValue().installed) &&
                    (selected == null || selected == p.getValue().selected) &&
                    (link == null || link == p.getValue().link)) {
                plugins.add(p.getKey());
            }
        }
        return plugins;
    }

    public @NotNull Set<String> unknown() {
        return this.unknown;
    }

    private record Details (
            @NotNull Boolean selected,
            @NotNull Boolean installed,
            @Nullable Boolean link
    ) { }
}