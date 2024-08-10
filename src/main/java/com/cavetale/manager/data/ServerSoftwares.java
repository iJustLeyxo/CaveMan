package com.cavetale.manager.data;

import com.cavetale.manager.parser.Flag;
import com.cavetale.manager.parser.Tokens;
import com.cavetale.manager.parser.container.ServerSoftwareContainer;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public final class ServerSoftwares {
    private final @NotNull Tokens tokens;
    private @NotNull Set<ServerSoftware> selected = new HashSet<>();

    public ServerSoftwares(@NotNull Tokens tokens) {
        this.tokens = tokens;
    }

    public Set<ServerSoftware> selected() {
        if (this.selected.isEmpty()) {
            if (this.tokens.flags().containsKey(Flag.SOFTWARE)) {
                this.selected.addAll(((ServerSoftwareContainer) this.tokens.flags().get(Flag.SOFTWARE)).get());
            }
            if (this.selected.isEmpty()) {
                this.selected.add(ServerSoftware.DEFAULT);
            }
        }
        return this.selected;
    }
}
