package com.cavetale.manager.command;

import com.cavetale.manager.parser.Result;
import com.cavetale.manager.util.cmd.Cmd;
import com.cavetale.manager.util.cmd.Style;
import org.jetbrains.annotations.NotNull;

public final class ExitExecutor extends Executor {
    public ExitExecutor(@NotNull Result result) {
        super(result);
    }

    @Override
    public void run() {
        Cmd.out(Style.LOG, "Exiting\n\n");
        System.exit(0);
    }

    @Override
    public void help() {

    }
}
