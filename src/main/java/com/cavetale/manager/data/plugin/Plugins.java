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
import java.nio.file.Files;
import java.util.*;

/**
 * Plugin manager, used to analyse installed and selected plugins
 */
public final class Plugins {
    private final @NotNull List<Index> index;
    
    public Plugins(@NotNull Tokens tokens) {
        // Gather plugin data
        List<Index.Register> registers = this.gatherRegistered(tokens);
        List<Index.Installation> installations = this.gatherInstalled();

        // Sew together plugin index
        this.index = new LinkedList<>();
        List<Index.Installation> instances;
        for (Index.Register r : registers) {
            instances = new LinkedList<>();
            for (Index.Installation i : installations) {
                if (r.plugin == i.plugin) {
                    instances.add(i);
                    installations.remove(i);
                }
            }
            this.index.add(new Index(r, instances));
        }
        // Add unknown plugins
        for (Index.Installation i : installations) {
            this.index.add(new Index(null, List.of(i)));
        }
    }

    private List<Index.Register> gatherRegistered(@NotNull Tokens tokens) {
        // Resolve registered plugins
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
        List<Index.Register> registers = new LinkedList<>();
        for (Plugin p : Plugin.values()) {
            registers.add(new Index.Register(p, selected.contains(p)));
        }
        return registers;
    }

    private List<Index.Installation> gatherInstalled() {
        // Resolve installed plugins
        List<Index.Installation> installations = new LinkedList<>();
        File folder = new File("plugins/");
        File[] files = folder.listFiles();
        if (files == null) {
            return installations;
        }
        for (File f : files) {
            String name = f.getName();
            try {
                Plugin p = Plugin.get(name);
                installations.add(new Index.Installation(p, name, Files.isSymbolicLink(f.toPath())));
            } catch (Plugin.NotFoundException e) {
                installations.add(new Index.Installation(null, name, Files.isSymbolicLink(f.toPath())));
            }
        }
        return installations;
    }

    public @NotNull Set<Plugin> get(@Nullable Boolean installed, @Nullable Boolean selected) {
        Set<Plugin> plugins = new HashSet<>();
        for (Index i : this.index) {
            if (i.register == null) {
                continue;
            }
            if ((installed == null || !i.installations.isEmpty() == installed) &&
                    (selected == null || i.register.isSelected == selected)) {
                plugins.add(i.register.plugin);
            }
        }
        return plugins;
    }

    public @NotNull List<Index.Installation> unknown() {
        List<Index.Installation> unknown = new LinkedList<>();
        for (Index i : this.index) {
            if (i.register == null) {
                unknown.addAll(i.installations);
            }
        }
        return unknown;
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
        List<Index.Installation> unknown = this.unknown();
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

    public record Index(
            @Nullable Plugins.Index.Register register,
            @NotNull List<Installation> installations
    ) {
        public record Register(
                @NotNull Plugin plugin,
                @NotNull Boolean isSelected
        ) { }

        public record Installation (
                @Nullable Plugin plugin,
                @NotNull String file,
                @NotNull Boolean isLink
        ) { }
    }
}