package com.cavetale.manager.parser;

import com.cavetale.manager.Manager;
import com.cavetale.manager.parser.container.Container;
import com.cavetale.manager.util.cmd.Cmd;
import com.cavetale.manager.util.cmd.Style;
import com.cavetale.manager.util.cmd.Verbosity;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;

public record Tokens (
        @NotNull Set<Command> commands,
        @NotNull Map<Flag, Container> flags
) {
    public boolean analyse() {
        boolean changed = false;
        if (this.flags().containsKey(Flag.VERBOSE)) {
            Cmd.verbosity = Verbosity.VERBOSE;
            Cmd.out(Style.INFO, "Verbose mode activated\n");
            changed = true;
        } else if (this.flags().containsKey(Flag.DEFAULT)) {
            Cmd.verbosity = Verbosity.DEFAULT;
            Cmd.out(Style.INFO, "Default verbosity mode activated\n");
            changed = true;
        } else if (this.flags().containsKey(Flag.QUIET)) {
            Cmd.verbosity = Verbosity.QUIET;
            Cmd.out(Style.INFO, "Quiet mode activated\n");
            changed = true;
        }
        if (this.flags().containsKey(Flag.INTERACTIVE) && !Manager.interactive) {
            Manager.interactive = true;
            Cmd.out(Style.INFO, "Interactive mode activated\n");
            changed = true;
        }
        if (changed) {
            Cmd.out(Style.INFO, "\n");
        }
        return changed;
    }
}
