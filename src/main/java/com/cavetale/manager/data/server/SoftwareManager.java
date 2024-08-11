package com.cavetale.manager.data.server;

import com.cavetale.manager.parser.Flag;
import com.cavetale.manager.parser.Tokens;
import com.cavetale.manager.parser.container.ServerSoftwareContainer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class SoftwareManager {
    private final @NotNull Tokens tokens;
    private final @NotNull Map<Software, Details> softwares = new HashMap<>();
    private final @NotNull Set<String> unknown = new HashSet<>();

    public SoftwareManager(@NotNull Tokens tokens) {
        this.tokens = tokens;
    }

    public void resolve() {
        this.softwares.clear();
        Set<Software> selected = new HashSet<>();
        if (this.tokens.flags().containsKey(Flag.SOFTWARE)) {
            selected.addAll(((ServerSoftwareContainer) this.tokens.flags().get(Flag.SOFTWARE)).get());
        }
        if (selected.isEmpty()) {
            selected.add(Software.DEFAULT);
        }
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
                    this.softwares.put(s, new Details(
                            selected.contains(s),
                            true
                    ));
                } catch (Software.NotFoundException e) {
                    this.unknown.add(ref);
                }
            }
        }
        for (Software s : Software.values()) {
            if (this.softwares.containsKey(s)) {
                continue;
            }
            this.softwares.put(s, new Details(
                    selected.contains(s),
                    false
            ));
        }
    }

    public Set<Software> get(@Nullable Boolean installed, @Nullable Boolean selected) {
        Set<Software> softwares = new HashSet<>();
        for (Map.Entry<Software, Details> s : this.softwares.entrySet()) {
            if ((installed == null || installed == s.getValue().installed) &&
                    (selected == null || selected == s.getValue().selected)) {
                softwares.add(s.getKey());
            }
        }
        return softwares;
    }

    public Set<String> unknown() {
        return this.unknown;
    }

    private record Details(
            @NotNull Boolean selected,
            @NotNull Boolean installed
    ) { }
}
