package com.cavetale.manager.data.plugin;

import com.cavetale.manager.parser.Flag;
import com.cavetale.manager.parser.Tokens;
import com.cavetale.manager.parser.container.CategoryContainer;
import com.cavetale.manager.parser.container.PluginContainer;
import com.cavetale.manager.parser.container.ServerContainer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Plugin manager, used to analyse installed and selected plugins
 */
public final class PluginManager {
    private final @NotNull Tokens tokens;
    private final @NotNull Map<Plugin, Details> plugins = new HashMap<>();
    private final @NotNull Set<String> unknown = new HashSet<>();

    public PluginManager(@NotNull Tokens tokens) {
        this.tokens = tokens;
        this.resolve();
    }

    /**
     * Resolve installed and selected plugins
     */
    private void resolve() {
        this.plugins.clear();
        this.unknown.clear();
        // Resolve selected plugins
        Set<Plugin> selected = new HashSet<>();
        if (this.tokens.flags().containsKey(Flag.PLUGIN)) {
            selected.addAll(((PluginContainer) this.tokens.flags().get(Flag.PLUGIN)).get());
        }
        if (this.tokens.flags().containsKey(Flag.CATEGORY)) {
            for (Category category : ((CategoryContainer) this.tokens.flags().get(Flag.CATEGORY)).get()) {
                selected.addAll(category.plugins());
            }
        }
        if (this.tokens.flags().containsKey(Flag.SERVER)) {
            for (Server server : ((ServerContainer) this.tokens.flags().get(Flag.SERVER)).get()) {
                selected.addAll(server.plugins());
            }
        }
        // Resolve installed plugins
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
                    this.plugins.put(p, new Details(selected.contains(p), true,
                            Files.isSymbolicLink(f.toPath())));
                } catch (Plugin.NotFoundException e) {
                    this.unknown.add(ref);
                }
            }
        }
        // Resolve registered plugins
        for (Plugin p : Plugin.values()) {
            if (this.plugins.containsKey(p)) {
                continue;
            }
            this.plugins.put(p, new Details(selected.contains(p), false, null));
        }
    }

    public @NotNull Set<Plugin> get(@Nullable Boolean installed, @Nullable Boolean selected,
                                    @Nullable Boolean link) {
        Set<Plugin> plugins = new HashSet<>();
        for (Map.Entry<Plugin, Details> p : this.plugins.entrySet()) {
            if ((installed == null || installed == p.getValue().installed) &&
                    (selected == null || selected == p.getValue().selected) &&
                    (link == null || null == p.getValue().link || link == p.getValue().link)) {
                plugins.add(p.getKey());
            }
        }
        return plugins;
    }

    public @NotNull Set<String> unknown() {
        return this.unknown;
    }

    private record Details (
            @NotNull Boolean selected, @NotNull Boolean installed, @Nullable Boolean link) {

    }
}