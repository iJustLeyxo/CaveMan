package com.cavetale.manager.data.plugin;

import com.cavetale.manager.parser.Flag;
import com.cavetale.manager.parser.Tokens;
import com.cavetale.manager.parser.container.CategoryContainer;
import com.cavetale.manager.parser.container.PluginContainer;
import com.cavetale.manager.parser.container.ServerContainer;
import com.cavetale.manager.util.console.Console;
import com.cavetale.manager.util.console.Style;
import com.cavetale.manager.util.console.Type;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.*;

/**
 * Plugin manager, used to analyse installed and selected plugins
 */
public final class PlugIndexer {
    private final @NotNull Map<Plugin, Index> index = new HashMap<>();

    private record Index(
            @Nullable Boolean isSelected,
            @NotNull List<File> installs
    ) { }
    
    public PlugIndexer(@NotNull Tokens tokens) {
        Set<Plugin> selected = this.gatherSelected(tokens);
        Map<Plugin, List<File>> installs = this.gatherInstalled();
        for (Map.Entry<Plugin, List<File>> e : installs.entrySet()) {
            this.index.put(e.getKey(), new Index(selected.contains(e.getKey()), e.getValue()));
        }
    }

    private Set<Plugin> gatherSelected(@NotNull Tokens tokens) {
        Set<Plugin> selected = new HashSet<>();
        if (tokens.flags().containsKey(Flag.PLUGIN)) {
            selected.addAll(((PluginContainer) tokens.flags().get(Flag.PLUGIN)).get());
        }
        if (tokens.flags().containsKey(Flag.CATEGORY)) {
            for (Category category : ((CategoryContainer) tokens.flags().get(Flag.CATEGORY)).get()) {
                selected.addAll(category.plugins());
            }
        }
        if (tokens.flags().containsKey(Flag.SERVER)) {
            for (Server server : ((ServerContainer) tokens.flags().get(Flag.SERVER)).get()) {
                selected.addAll(server.plugins());
            }
        }
        return selected;
    }

    private Map<Plugin, List<File>> gatherInstalled() {
        Map<Plugin, List<File>> installs = new HashMap<>();
        File folder = new File("plugins/");
        File[] files = folder.listFiles();
        if (files == null) {
            return installs;
        }
        installs.put(null, new LinkedList<>());
        for (Plugin p : Plugin.values()) {
            installs.put(p, new LinkedList<>());
        }
        for (File f : files) {
            String name = f.getName();
            try {
                Plugin p = Plugin.get(name);
                installs.get(p).add(f);
            } catch (Plugin.NotFoundException e) {
                installs.get(null).add(f);
            }
        }
        return installs;
    }

    public @NotNull Set<Plugin> get(@Nullable Boolean installed, @Nullable Boolean selected) {
        Set<Plugin> plugins = new HashSet<>();
        for (Map.Entry<Plugin, Index> e : this.index.entrySet()) {
            Index i = e.getValue();
            if ((installed == null || !i.installs.isEmpty() == installed) &&
                    (selected == null || i.isSelected == selected)) {
                plugins.add(e.getKey());
            }
        }
        return plugins;
    }

    public @NotNull List<File> unknownInstalls() {
        return new LinkedList<>(this.index.get(null).installs);
    }

    public void summarize() {
        Set<Plugin> selected = this.get(null, true);
        Set<Plugin> installed = this.get(true, null);
        if (!selected.isEmpty()) {
            this.summarizeSelected(selected);
        } else if (!installed.isEmpty()) {
            this.summarizeInstalled();
        } else {
            Console.log(Type.REQUESTED, Style.INFO, "Nothing selected, nothing installed\n");
        }
        List<File> unknown = this.unknownInstalls();
        if (!unknown.isEmpty()) {
            Console.sep();
            Console.logL(Type.REQUESTED, Style.UNKNOWN, unknown.size() +
                    " plugins(s) unknown", 4, 21, unknown.toArray());
        }
    }

    private void summarizeSelected(@NotNull Set<Plugin> plugins) {
        Console.sep();
        Console.logL(Type.REQUESTED, Style.SELECT, plugins.size() +
                " plugins(s) selected", 4, 21, plugins.toArray());
        plugins = this.get(true, true);
        if (!plugins.isEmpty()) {
            Console.sep();
            Console.logL(Type.REQUESTED, Style.INSTALL, plugins.size() +
                    " plugins(s) installed", 4, 21, plugins.toArray());
        }
        plugins = this.get(true, false);
        if (!plugins.isEmpty()) {
            Console.sep();
            Console.logL(Type.REQUESTED, Style.SUPERFLUOUS, plugins.size() +
                    " plugins(s) superfluous", 4, 21, plugins.toArray());
        }
        plugins = this.get(false, true);
        if (!plugins.isEmpty()) {
            Console.sep();
            Console.logL(Type.REQUESTED, Style.MISSING, plugins.size() +
                    " plugins(s) missing", 4, 21, plugins.toArray());
        }
    }

    private void summarizeInstalled() {
        Set<Plugin> installed = this.get(true, null);
        if (!installed.isEmpty()) {
            Console.sep();
            Console.logL(Type.REQUESTED, Style.INSTALL, installed.size() +
                    " plugins(s) installed", 4, 21, installed.toArray());
        }
    }
}