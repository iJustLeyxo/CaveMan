package com.cavetale.manager;

import com.cavetale.manager.parser.Command;
import com.cavetale.manager.parser.InputException;
import com.cavetale.manager.parser.Parser;
import com.cavetale.manager.parser.Result;
import com.cavetale.manager.util.cmd.Cmd;
import com.cavetale.manager.util.cmd.Style;

public final class Manager {
    public static boolean interactive = true;

    public static void main(String[] args) {
        Cmd.out("\n");

        while (interactive) {
            if (args != null && args.length > 0) {
                interactive = false;
            } else {
                args = Cmd.in();
            }
            try {
                Result result = Parser.parse(args);
                boolean changed = result.tokens().analyse();
                if (result.tokens().commands().isEmpty()) {
                    if (!changed) {
                        Cmd.out(Style.WARN, "Nothing to do. Try typing \"help\".\n\n");
                    }
                } else {
                    for (Command cmd : result.tokens().commands()) {
                        cmd.run(result);
                    }
                }
            } catch (InputException e) {
                Cmd.out(Style.WARN, e.getMessage() + "\n\n");
                if (!interactive) {
                    System.exit(1);
                }
            }
            args = null;
        }
        Cmd.out(Style.LOG, "Exiting\n\n");
        System.exit(0);
    }
}