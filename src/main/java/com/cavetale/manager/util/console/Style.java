package com.cavetale.manager.util.console;

import org.jetbrains.annotations.NotNull;

public enum Style implements EscCodeProvider, VerbosityProvider {
    PROMPT(Verbosity.OVERRIDE, EscCode.MAGENTA, EscCode.BOLD),
    INPUT(Verbosity.BLOCK, EscCode.WHITE, EscCode.BOLD),
    LOG(Verbosity.VERBOSE, EscCode.GRAY),
    INFO(Verbosity.DEFAULT, EscCode.BLUE),
    DONE(Verbosity.DEFAULT, EscCode.GREEN),
    HELP(Verbosity.OVERRIDE, EscCode.GREEN),
    WARN(Verbosity.QUIET, EscCode.YELLOW),
    ERR(Verbosity.SILENT, EscCode.RED),
    OVERRIDE(Verbosity.OVERRIDE, EscCode.RESET);

    private final @NotNull Verbosity verbosity;
    private final @NotNull EscCode[] codes;

    Style(@NotNull Verbosity verbosity, @NotNull EscCode... codes) {
        this.verbosity = verbosity;
        this.codes = codes;
    }

    @Override
    public @NotNull String toString() {
        StringBuilder s = new StringBuilder();
        for (EscCode c : codes) {
            s.append(c);
        }
        return s.toString();
    }

    @Override
    public @NotNull Verbosity verbosity() {
        return this.verbosity;
    }
}
