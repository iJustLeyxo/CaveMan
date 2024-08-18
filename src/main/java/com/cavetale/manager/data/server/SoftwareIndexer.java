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
    private final @NotNull Tokens tokens;
    private final @NotNull Map<Software, Details> software = new HashMap<>();
    private final @NotNull Set<String> unknown = new HashSet<>();

    public SoftwareIndexer(@NotNull Tokens tokens) {
        this.tokens = tokens;
        this.resolve();
    }

    /**
     * Resolve installed and selected software
     */
    public void resolve() { // TODO: Update resolver logic to work with new plugin and software detection
        this.software.clear();
        // Resolve selected software
        Set<Software> selected = new HashSet<>();
        if (this.tokens.flags().containsKey(Flag.SOFTWARE)) {
            selected.addAll(((SoftwareContainer) this.tokens.flags().get(Flag.SOFTWARE)).get());
        }
        // Resolve installed software
        File folder = new File("");
        File[] files = folder.listFiles();
        if (folder.exists() && files != null) {
            for (File f : files) {
                String ref = f.getName();
                if (!ref.endsWith(".jar") || ref.equals("CaveMan.jar")) {
                    continue;
                }
                ref = ref.substring(0, ref.length() - 4);
                try {
                    Software s = Software.get(ref);
                    this.software.put(s, new Details(
                            selected.contains(s),
                            true
                    ));
                } catch (Software.NotFoundException e) {
                    this.unknown.add(ref);
                }
            }
        }
        // Resolve registered software
        for (Software s : Software.values()) {
            if (this.software.containsKey(s)) {
                continue;
            }
            this.software.put(s, new Details(selected.contains(s), false));
        }
    }

    public Set<Software> get(@Nullable Boolean installed, @Nullable Boolean selected) {
        Set<Software> software = new HashSet<>();
        for (Map.Entry<Software, Details> s : this.software.entrySet()) {
            if ((installed == null || installed == s.getValue().installed) &&
                    (selected == null || selected == s.getValue().selected)) {
                software.add(s.getKey());
            }
        }
        return software;
    }

    public Set<String> unknown() {
        return this.unknown;
    }

    public void summarize() {
        Set<Software> selected = this.get(null, true);
        Set<Software> installed = this.get(true, null);
        if (!selected.isEmpty()) {
            this.summarizeSelected(selected); // Compare selected to installed software
        } else if (!installed.isEmpty()) {
            this.summarizeInstalled(installed); // Show installed software if nothing is selected
        } else {
            Console.log(Type.REQUESTED, Style.INFO, "Nothing selected, nothing installed\n");
        }
        Set<String> unknown = this.unknown(); // Always show unknown software
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
        software = this.get(true, true);
        if (!software.isEmpty()) {
            Console.sep();
            Console.logL(Type.REQUESTED, Style.INSTALL, software.size() +
                    " software(s) installed", 4, 21, software.toArray());
        }
        software = this.get(true, false);
        if (!software.isEmpty()) {
            Console.sep();
            Console.logL(Type.REQUESTED, Style.SUPERFLUOUS, software.size() +
                    " software(s) superfluous", 4, 21, software.toArray());
        }
        software = this.get(false, true);
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

    private record Details(@NotNull Boolean selected, @NotNull Boolean installed) {

    }
}
