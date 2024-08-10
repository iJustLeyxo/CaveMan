package com.cavetale.manager.data.server;

import com.cavetale.manager.parser.Flag;
import com.cavetale.manager.parser.Tokens;
import com.cavetale.manager.parser.container.ServerSoftwareContainer;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public final class SoftwareManager {
    private final @NotNull Tokens tokens;
    private final @NotNull Set<Software> selected = new HashSet<>();

    public SoftwareManager(@NotNull Tokens tokens) {
        this.tokens = tokens;
    }

    public Set<Software> selected() {
        if (this.selected.isEmpty()) {
            if (this.tokens.flags().containsKey(Flag.SOFTWARE)) {
                this.selected.addAll(((ServerSoftwareContainer) this.tokens.flags().get(Flag.SOFTWARE)).get());
            }
            if (this.selected.isEmpty()) {
                this.selected.add(Software.DEFAULT);
            }
        }
        return this.selected;
    }
}
