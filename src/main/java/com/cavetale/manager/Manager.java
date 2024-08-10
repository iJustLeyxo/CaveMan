package com.cavetale.manager;

import com.cavetale.manager.parser.Command;
import com.cavetale.manager.parser.InputException;
import com.cavetale.manager.parser.Parser;
import com.cavetale.manager.parser.Result;
import com.cavetale.manager.util.console.Console;
import com.cavetale.manager.util.console.Style;

public final class Manager {
    public static boolean interactive = true;

    public static void main(String[] args) {
        Console.out("\n");

        while (interactive) {
            if (args != null && args.length > 0) {
                interactive = false;
            } else {
                args = Console.in();
            }
            try {
                Result result = Parser.parse(args);
                boolean changed = result.tokens().analyse();
                if (result.tokens().commands().isEmpty()) {
                    if (!changed) {
                        Console.out(Style.WARN, "Nothing to do. Try typing \"help\".\n\n");
                    }
                } else {
                    Console.out(Style.LOG, "Running " + result.tokens().commands().size() + " commands\n");
                    for (Command cmd : result.tokens().commands()) {
                        cmd.run(result);
                    }
                    Console.out(Style.LOG, Style.DONE, "Finished running commands\n");
                }
            } catch (InputException e) {
                Console.out(Style.WARN, e.getMessage() + "\n\n");
                if (!interactive) {
                    System.exit(1);
                }
            }
            args = null;
        }
        Console.out(Style.LOG, "Exiting\n\n");
        System.exit(0);
    }
}