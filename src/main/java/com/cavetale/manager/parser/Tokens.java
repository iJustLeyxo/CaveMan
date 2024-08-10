package com.cavetale.manager.parser;

import com.cavetale.manager.Manager;
import com.cavetale.manager.parser.container.EmptyContainer;
import com.cavetale.manager.util.console.Console;
import com.cavetale.manager.util.console.Style;
import com.cavetale.manager.util.console.Verbosity;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;

public record Tokens (
        @NotNull Set<Command> commands,
        @NotNull Map<Flag, EmptyContainer> flags
) {
    public boolean analyse() {
        Console.log(Style.DEBUG, "Analysing tokens\n");
        boolean changed = false;
        if (this.flags().containsKey(Flag.VERBOSE)) {
            Console.verbosity = Verbosity.VERBOSE;
            Console.log(Style.INFO, "Verbose mode activated\n");
            changed = true;
        } else if (this.flags().containsKey(Flag.DEFAULT)) {
            Console.verbosity = Verbosity.DEFAULT;
            Console.log(Style.INFO, "Default verbosity mode activated\n");
            changed = true;
        } else if (this.flags().containsKey(Flag.QUIET)) {
            Console.verbosity = Verbosity.QUIET;
            Console.log(Style.INFO, "Quiet mode activated\n");
            changed = true;
        }
        if (this.flags().containsKey(Flag.INTERACTIVE) && !Manager.interactive) {
            Manager.interactive = true;
            Console.log(Style.INFO, "Interactive mode activated\n");
            changed = true;
        }
        if (changed) {
            Console.log(Style.INFO, "\n");
        }
        Console.log(Style.DEBUG, Style.DONE, "Finished analysing tokens\n\n");
        return changed;
    }
}
