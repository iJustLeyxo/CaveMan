package com.cavetale.manager.parser;

import com.cavetale.manager.Manager;
import com.cavetale.manager.parser.container.EmptyContainer;
import com.cavetale.manager.util.console.Console;
import com.cavetale.manager.util.console.Detail;
import com.cavetale.manager.util.console.Style;
import com.cavetale.manager.util.console.XCode;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;

public record Tokens (
        @NotNull Set<Command> commands,
        @NotNull Map<Flag, EmptyContainer> flags
) {
    public boolean analyse() {
        Console.log(Style.DEBUG, "Analysing tokens");
        StringBuilder s = new StringBuilder();
        if (this.flags().containsKey(Flag.VERBOSE)) {
            Console.detail = Detail.HIGH;
            s.append("Verbose mode activated\n");
        } else if (this.flags().containsKey(Flag.DEFAULT)) {
            Console.detail = Detail.DEFAULT;
            s.append("Default verbosity mode activated\n");
        } else if (this.flags().containsKey(Flag.QUIET)) {
            Console.detail = Detail.LOW;
            s.append("Quiet mode activated\n");
        }
        if (this.flags().containsKey(Flag.INTERACTIVE) && !Manager.interactive) {
            Manager.interactive = true;
            s.append("Interactive mode activated\n");
        }
        Console.log(Style.DEBUG, XCode.GREEN + " done\n\n");
        if (!s.isEmpty()) {
            Console.log(Style.INFO, s + "\n");
            return true;
        }
        return false;
    }
}
