package com.cavetale.manager.parser;

import com.cavetale.manager.command.*;
import com.cavetale.manager.util.console.Console;
import com.cavetale.manager.util.console.Style;
import org.jetbrains.annotations.NotNull;

public enum Command {
    EXIT("Exit interactive mode") {
        @Override
        public @NotNull Exec get(@NotNull Result result) {
            return new ExitExec(result);
        }
    },
    HELP("Show usage help") {
        @Override
        public @NotNull Exec get(@NotNull Result result) {
            return new HelpExec(result);
        }
    },
    INSTALL("Install selected plugins") {
        @Override
        public @NotNull Exec get(@NotNull Result result) {
            return new InstallExec(result);
        }
    },
    LINK("Create symbolic links") {
        @Override
        public @NotNull Exec get(@NotNull Result result) {
            return new LinkExec(result);
        }
    },
    LIST("List selected plugins") {
        @Override
        public @NotNull Exec get(@NotNull Result result) {
            return new ListExec(result);
        }
    },
    STATUS("Installation status") {
        @Override
        public @NotNull Exec get(@NotNull Result result) {
            return new StatusExec(result);
        }
    },
    UNINSTALL("Delete plugins") {
        @Override
        public @NotNull Exec get(@NotNull Result result) {
            return new UninstallExec(result);
        }
    },
    UNLINK("Unlink linked plugins") {
        @Override
        public @NotNull Exec get(@NotNull Result result) {
            return new UnlinkExec(result);
        }
    };

    public final @NotNull String ref;
    public final @NotNull String description;

    Command(@NotNull String description) {
        this.ref = this.name().toLowerCase();
        this.description = description;
    }

    public final void run(@NotNull Result result) {
        Console.out(Style.LOG, "Running command " + this.ref);
        this.get(result);
        Console.out(Style.LOG, Style.DONE, "Finished running command " + this.ref);
    }

    public abstract @NotNull Exec get(@NotNull Result result);

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
