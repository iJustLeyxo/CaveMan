package com.cavetale.manager.data.server;

import com.cavetale.manager.parser.Flag;
import com.cavetale.manager.parser.Tokens;
import com.cavetale.manager.parser.container.SoftwareContainer;
import com.cavetale.manager.util.console.Console;
import com.cavetale.manager.util.console.Style;
import com.cavetale.manager.util.console.Type;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Server software manager, used to analyse installed and selected plugins
 */
public final class SoftwareIndexer {
    private final @NotNull Map<Software, Index> index = new HashMap<>();

    private record Index(
            @Nullable Boolean isSelected,
            @NotNull Set<File> installs
    ) { }

    public SoftwareIndexer(@NotNull Tokens tokens) {
        Set<Software> selected = this.gatherSelected(tokens);
        Map<Software, Set<File>> installs = this.gatherInstalls();
        for (Map.Entry<Software, Set<File>> e : installs.entrySet()) {
            this.index.put(e.getKey(), new Index(selected.contains(e.getKey()), e.getValue()));
        }
    }

    /**
     * Resolve installed and selected software
     */
    public Set<Software> gatherSelected(@NotNull Tokens tokens) {
        Set<Software> selected = new HashSet<>();
        if (tokens.flags().containsKey(Flag.SOFTWARE)) {
            selected.addAll(((SoftwareContainer) tokens.flags().get(Flag.SOFTWARE)).get());
        }
        return selected;
    }

    public Map<Software, Set<File>> gatherInstalls() {
        Map<Software, Set<File>> installs = new HashMap<>();
        File folder = new File("");
        File[] files = folder.listFiles();
        if (files == null) {
            return installs;
        }
        installs.put(null, new HashSet<>());
        for (Software s : Software.values()) {
            installs.put(s, new HashSet<>());
        }
        for (File f : files) {
            try {
                Software s = Software.get(f.getName());
                installs.get(s).add(f);
            } catch (Software.NotFoundException e) {
                installs.get(null).add(f);
            }
        }
        return installs;
    }

    // TODO: Implement one time indexer
    public Set<Software> getAll(@Nullable Boolean installed, @Nullable Boolean selected) {
        Set<Software> software = new HashSet<>();
        for (Map.Entry<Software, Index> e : this.index.entrySet()) {
            Index i = e.getValue();
            if ((installed == null || installed == !i.installs.isEmpty()) &&
                    (selected == null || selected == i.isSelected)) {
                software.add(e.getKey());
            }
        }
        return software;
    }

    public @NotNull Set<File> unknownInstalls() {
        return new HashSet<>(this.index.get(null).installs);
    }

    public void summarize() {
        Set<Software> selected = this.getAll(null, true);
        Set<Software> installed = this.getAll(true, null);
        if (!selected.isEmpty()) {
            this.summarizeSelected(selected); // Compare selected to installed software
        } else if (!installed.isEmpty()) {
            this.summarizeInstalled(installed); // Show installed software if nothing is selected
        } else {
            Console.log(Type.REQUESTED, Style.INFO, "Nothing selected, nothing installed\n");
        }
        Set<File> unknown = this.unknownInstalls(); // Always show unknown software
        if (!unknown.isEmpty()) {
            Console.sep();
            Console.logL(Type.REQUESTED, Style.UNKNOWN, unknown.size() +
                    " software(s) unknown", 4, 21, unknown.toArray());
        }
    }

    private void summarizeSelected(@NotNull Set<Software> software) {
        Console.sep();
        Console.logL(Type.REQUESTED, Style.SELECT, software.size() +
                " software(s) selected", 4, 21, software.toArray());
        software = this.getAll(true, true);
        if (!software.isEmpty()) {
            Console.sep();
            Console.logL(Type.REQUESTED, Style.INSTALL, software.size() +
                    " software(s) installed", 4, 21, software.toArray());
        }
        software = this.getAll(true, false);
        if (!software.isEmpty()) {
            Console.sep();
            Console.logL(Type.REQUESTED, Style.SUPERFLUOUS, software.size() +
                    " software(s) superfluous", 4, 21, software.toArray());
        }
        software = this.getAll(false, true);
        if (!software.isEmpty()) {
            Console.sep();
            Console.logL(Type.REQUESTED, Style.MISSING, software.size() +
                    " software(s) missing", 4, 21, software.toArray());
        }
    }

    private void summarizeInstalled(@NotNull Set<Software> installed) {
            Console.sep();
            Console.logL(Type.REQUESTED, Style.SOFTWARE, installed.size() +
                    " software(s) installed", 4, 21, installed.toArray());
    }
}
