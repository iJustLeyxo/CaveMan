package com.cavetale.manager.command;

import com.cavetale.manager.parser.Flag;
import com.cavetale.manager.parser.Result;
import org.jetbrains.annotations.NotNull;

public abstract class Executor {
    protected final @NotNull Result result;

    public Executor(@NotNull Result result) {
        this.result = result;
        if (this.result.tokens().flags().containsKey(Flag.HELP)) {
            this.help();
        } else {
            this.run();
        }
    }

    protected abstract void run();

    protected abstract void help();
}
