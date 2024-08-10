package com.cavetale.manager.util.console;

import org.jetbrains.annotations.NotNull;

public enum Style implements Styled {
    PROMPT(Detail.OVERRIDE, XCode.MAGENTA, XCode.BOLD),
    INPUT(Detail.OVERRIDE, XCode.WHITE, XCode.BOLD),
    DEBUG(Detail.HIGH, XCode.GRAY),
    INFO(Detail.DEFAULT, XCode.BLUE),
    DONE(Detail.DEFAULT, XCode.GREEN),
    HELP(Detail.OVERRIDE, XCode.GREEN),
    WARN(Detail.LOW, XCode.YELLOW),
    ERR(Detail.MINIMAL, XCode.RED),
    OVERRIDE(Detail.OVERRIDE, XCode.RESET);

    private final @NotNull Detail detail;
    private final @NotNull String codes;

    Style(@NotNull Detail detail, @NotNull XCode... codes) {
        this.detail = detail;
        StringBuilder s = new StringBuilder();
        for (XCode c : codes) {
            s.append(c);
        }
        this.codes = s.toString();
    }

    @Override
    public @NotNull String toString() {
        return this.codes;
    }

    @Override
    public @NotNull Detail detail() {
        return this.detail;
    }
}
