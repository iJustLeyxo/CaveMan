package com.cavetale.manager.parser;

import com.cavetale.manager.command.*;
import com.cavetale.manager.util.console.Console;
import com.cavetale.manager.util.console.Style;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public enum Command {
    EXIT("Exit interactive mode", null) {
        @Override
        public void exec(@NotNull Result result) {
            new ExitExec(result);
        }
    },
    HELP("Show usage help", null) {
        @Override
        public void exec(@NotNull Result result) {
            new HelpExec(result);
        }
    },
    INSTALL("Install selected plugins", null) {
        @Override
        public void exec(@NotNull Result result) {
            new InstallExec(result);
        }
    },
    LINK("Create symbolic links", null) {
        @Override
        public void exec(@NotNull Result result) {
            new LinkExec(result);
        }
    },
    LIST("List selected plugins", null) {
        @Override
        public void exec(@NotNull Result result) {
            new ListExec(result);
        }
    },
    STATUS("Installation status", null) {
        @Override
        public void exec(@NotNull Result result) {
            new StatusExec(result);
        }
    },
    UNINSTALL("Delete plugins", null) {
        @Override
        public void exec(@NotNull Result result) {
            new UninstallExec(result);
        }
    },
    UNLINK("Unlink linked plugins", null) {
        @Override
        public void exec(@NotNull Result result) {
            new UnlinkExec(result);
        }
    };

    public final @NotNull String[] refs;
    public final @NotNull String info;
    public final @Nullable String usage;

    Command(@NotNull String info, @Nullable String usage, @NotNull String... aliases) {
        this.refs = new String[aliases.length + 1];
        this.refs[0] = this.name().toLowerCase();
        System.arraycopy(refs, 0, this.refs, 1, aliases.length);
        this.usage = usage;
        this.info = info;
    }

    public final void run(@NotNull Result result) {
        Console.out(Style.LOG, "Running command " + this.refs[0]);
        if (result.tokens().flags().containsKey(Flag.HELP)) {
            this.help();
        } else {
            this.exec(result);
        }
        this.exec(result);
        Console.out(Style.LOG, Style.DONE, "Finished running command " + this.refs[0]);
    }

    public void help() {
        Console.out(Style.HELP, this.refs[0] + ": " + this.info + " | " +
                Objects.requireNonNullElse(this.usage, "") + "\n\n");
    }

    public abstract void exec(@NotNull Result result);

    public static @NotNull Command get(@NotNull String ref) throws NotFoundException {
        for (Command c : values()) {
            for (String r : c.refs) {
                if (r.equalsIgnoreCase(ref)) {
                    return c;
                }
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
