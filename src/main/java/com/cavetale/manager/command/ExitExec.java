package com.cavetale.manager.command;

import com.cavetale.manager.parser.Result;
import com.cavetale.manager.util.console.Console;
import com.cavetale.manager.util.console.Style;
import org.jetbrains.annotations.NotNull;

public final class ExitExec extends Exec {
    public ExitExec(@NotNull Result result) {
        super(result);
    }

    @Override
    public void run() {
        Console.out(Style.DEBUG, "Exiting\n\n");
        System.exit(0);
    }
}
