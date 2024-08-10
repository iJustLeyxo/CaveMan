package com.cavetale.manager.util.console;

import org.jetbrains.annotations.NotNull;

public enum Style implements XCodeProvider, VerbosityProvider {
    PROMPT(Verbosity.OVERRIDE, XCode.MAGENTA, XCode.BOLD),
    INPUT(Verbosity.BLOCK, XCode.WHITE, XCode.BOLD),
    LOG(Verbosity.VERBOSE, XCode.GRAY),
    INFO(Verbosity.DEFAULT, XCode.BLUE),
    DONE(Verbosity.DEFAULT, XCode.GREEN),
    HELP(Verbosity.OVERRIDE, XCode.GREEN),
    WARN(Verbosity.QUIET, XCode.YELLOW),
    ERR(Verbosity.SILENT, XCode.RED),
    OVERRIDE(Verbosity.OVERRIDE, XCode.RESET);

    private final @NotNull Verbosity verbosity;
    private final @NotNull XCode[] codes;

    Style(@NotNull Verbosity verbosity, @NotNull XCode... codes) {
        this.verbosity = verbosity;
        this.codes = codes;
    }

    @Override
    public @NotNull String toString() {
        StringBuilder s = new StringBuilder();
        for (XCode c : codes) {
            s.append(c);
        }
        return s.toString();
    }

    @Override
    public @NotNull Verbosity verbosity() {
        return this.verbosity;
    }
}
