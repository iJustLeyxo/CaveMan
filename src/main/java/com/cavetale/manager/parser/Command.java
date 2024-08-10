package com.cavetale.manager.parser;

import com.cavetale.manager.command.*;
import com.cavetale.manager.util.console.Console;
import com.cavetale.manager.util.console.Style;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public enum Command {
    EXIT("Exit interactive mode") {
        @Override
        public void exec(@NotNull Result result) {
            new ExitExec(result);
        }
    },
    HELP("Show usage help") {
        @Override
        public void exec(@NotNull Result result) {
            new HelpExec(result);
        }
    },
    INSTALL("Install selected plugins") {
        @Override
        public void exec(@NotNull Result result) {
            new InstallExec(result);
        }
    },
    LINK("Create symbolic links") {
        @Override
        public void exec(@NotNull Result result) {
            new LinkExec(result);
        }
    },
    LIST("List selected plugins") {
        @Override
        public void exec(@NotNull Result result) {
            new ListExec(result);
        }
    },
    STATUS("Installation status") {
        @Override
        public void exec(@NotNull Result result) {
            new StatusExec(result);
        }
    },
    UNINSTALL("Delete plugins") {
        @Override
        public void exec(@NotNull Result result) {
            new UninstallExec(result);
        }
    },
    UNLINK("Unlink linked plugins") {
        @Override
        public void exec(@NotNull Result result) {
            new UnlinkExec(result);
        }
    };

    public final @NotNull String ref;
    public final @NotNull String info;
    public final @Nullable String usage;

    Command(@NotNull String info, @Nullable String usage) {
        this.usage = usage;
        this.ref = this.name().toLowerCase();
        this.info = info;
    }

    Command(@NotNull String info) {
        this.usage = null;
        this.ref = this.name().toLowerCase();
        this.info = info;
    }

    public final void run(@NotNull Result result) {
        Console.out(Style.LOG, "Running command " + this.ref);
        if (result.tokens().flags().containsKey(Flag.HELP)) {
            this.help();
        } else {
            this.exec(result);
        }
        this.exec(result);
        Console.out(Style.LOG, Style.DONE, "Finished running command " + this.ref);
    }

    public void help() {
        Console.out(Style.HELP, this.ref + ": " + this.info + " | " +
                Objects.requireNonNullElse(this.usage, "") + "\n\n");
    }

    public abstract void exec(@NotNull Result result);

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
