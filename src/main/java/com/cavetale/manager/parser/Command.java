package com.cavetale.manager.parser;

import com.cavetale.manager.command.*;
import org.jetbrains.annotations.NotNull;

public enum Command {
    EXIT("Exit interactive mode") {
        @Override
        public @NotNull Executor get(@NotNull Result result) {
            return new ExitExecutor(result);
        }
    },
    HELP("Show usage help") {
        @Override
        public @NotNull Executor get(@NotNull Result result) {
            return new HelpExecutor(result);
        }
    },
    INSTALL("Install selected plugins") {
        @Override
        public @NotNull Executor get(@NotNull Result result) {
            return new InstallExecutor(result);
        }
    },
    LINK("Create symbolic links") {
        @Override
        public @NotNull Executor get(@NotNull Result result) {
            return new LinkExecutor(result);
        }
    },
    LIST("List selected plugins") {
        @Override
        public @NotNull Executor get(@NotNull Result result) {
            return new ListExecutor(result);
        }
    },
    STATUS("Installation status") {
        @Override
        public @NotNull Executor get(@NotNull Result result) {
            return new StatusExecutor(result);
        }
    },
    UNINSTALL("Delete plugins") {
        @Override
        public @NotNull Executor get(@NotNull Result result) {
            return new UninstallExecutor(result);
        }
    },
    UNLINK("Unlink linked plugins") {
        @Override
        public @NotNull Executor get(@NotNull Result result) {
            return new UnlinkExecutor(result);
        }
    };

    public final @NotNull String ref;
    public final @NotNull String description;

    Command(@NotNull String description) {
        this.ref = this.name().toLowerCase();
        this.description = description;
    }

    public final void run(@NotNull Result result) {
        this.get(result);
    }

    public abstract @NotNull Executor get(@NotNull Result result);

    public static @NotNull Command get(@NotNull String ref) throws NotFoundException {
        for (Command c : values()) {
            if (ref.equalsIgnoreCase(c.ref)) {
                return c;
            }
        }
        throw new NotFoundException(ref);
    }

    public static class NotFoundException extends InputException {
        public NotFoundException(@NotNull String ref) {
            super("Command \"" + ref + "\" not found");
        }
    }
}
