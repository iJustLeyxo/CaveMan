package com.cavetale.manager.parser.container;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class PathContainer extends Container {
    private @Nullable String path = null;

    @Override
    public boolean option(@NotNull String option) {
        if (path == null) {
            this.path = option;
            return true;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return path == null;
    }

    public @Nullable String get() { return this.path; }
}
