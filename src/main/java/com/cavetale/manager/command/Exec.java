package com.cavetale.manager.command;

import com.cavetale.manager.parser.Flag;
import com.cavetale.manager.parser.Result;
import org.jetbrains.annotations.NotNull;

public abstract class Exec {
    protected final @NotNull Result result;

    public Exec(@NotNull Result result) {
        this.result = result;
        this.run();
    }

    protected abstract void run();
}
