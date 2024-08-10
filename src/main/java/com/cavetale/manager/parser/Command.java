package com.cavetale.manager.parser;

import com.cavetale.manager.command.*;
import com.cavetale.manager.util.console.Console;
import com.cavetale.manager.util.console.Style;
import org.jetbrains.annotations.NotNull;

public enum Command {
    EXIT("Close the application", "quit", "q") {
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
    INSTALL("Install plugins and software") {
        @Override
        public void exec(@NotNull Result result) {
            new InstallExec(result);
        }
    },
    LINK("Install plugins as links") {
        @Override
        public void exec(@NotNull Result result) {
            new LinkExec(result);
        }
    },
    LIST("List plugins, categories, servers and software") {
        @Override
        public void exec(@NotNull Result result) {
            new ListExec(result);
        }
    },
    STATUS("Get installation status") {
        @Override
        public void exec(@NotNull Result result) {
            new StatusExec(result);
        }
    },
    UNINSTALL("Delete plugins and software") {
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

    public final @NotNull String[] refs;
    public final @NotNull String info;

    Command(@NotNull String info, @NotNull String... aliases) {
        this.refs = new String[aliases.length + 1];
        this.refs[0] = this.name().toLowerCase();
        System.arraycopy(aliases, 0, this.refs, 1, aliases.length);
        this.info = info;
    }

    public final void run(@NotNull Result result) {
        Console.log(Style.DEBUG, "Running command " + this.refs[0] + "\n\n");
        if (result.tokens().flags().containsKey(Flag.HELP)) {
            this.help();
        } else {
            this.exec(result);
        }
        Console.log(Style.DEBUG, "Finished running command " + this.refs[0] + "\n\n");
    }

    public void help() {
        Console.log(Style.HELP, this.refs[0] + ": " + this.info + "\n\n");
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
