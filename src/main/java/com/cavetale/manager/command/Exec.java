package com.cavetale.manager.command;

import com.cavetale.manager.parser.Result;
import org.jetbrains.annotations.NotNull;

/**
 * Command executor superclass
 */
public abstract class Exec {
    /**
     * Parsing result to execute
     */
    protected final @NotNull Result result;

    /**
     * @param result Parsing result to execute
     */
    public Exec(@NotNull Result result) {
        this.result = result;
        this.run();
    }

    /**
     * Runs the command executor on the parsing result
     */
    protected abstract void run();
}
