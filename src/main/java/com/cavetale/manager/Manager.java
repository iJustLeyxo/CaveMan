package com.cavetale.manager;

import com.cavetale.manager.parser.Command;
import com.cavetale.manager.parser.InputException;
import com.cavetale.manager.parser.Parser;
import com.cavetale.manager.parser.Result;
import com.cavetale.manager.util.console.Console;
import com.cavetale.manager.util.console.Type;

public final class Manager {
    public static boolean interactive = true;

    public static void main(String[] args) {
        System.out.println();

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
                        Console.log(Type.WARN, "Nothing to do. Try typing \"help\".\n");
                    }
                } else {
                    Console.log(Type.DEBUG, "Running " + result.tokens().commands().size() + " commands\n");
                    for (Command cmd : result.tokens().commands()) {
                        cmd.run(result);
                    }
                    Console.log(Type.DEBUG, "Finished running commands\n");
                }
            } catch (InputException e) {
                Console.log(Type.ERR, e.getMessage() + "\n");
                if (!interactive) {
                    System.exit(1);
                }
            }
            args = null;
        }
        Console.log(Type.DEBUG, "Exiting\n");
        Console.sep();
        System.exit(0);
    }
}