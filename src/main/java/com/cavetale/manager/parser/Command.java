package com.cavetale.manager.parser;

import com.cavetale.manager.command.*;
import com.cavetale.manager.util.console.Console;
import com.cavetale.manager.util.console.Type;
import org.jetbrains.annotations.NotNull;

/**
 * Commands, used to specify command properties
 */
public enum Command {
    EXIT("Close the application", "quit", "q", "stop") {
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
    INSTALL("Install plugins and software", "add") {
        @Override
        public void exec(@NotNull Result result) {
            new InstallExec(result);
        }
    },
    LINK("Install plugins by linking") {
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
    STATUS("View installation status", "info", "verify", "check") {
        @Override
        public void exec(@NotNull Result result) {
            new StatusExec(result);
        }
    },
    UNINSTALL("Delete plugins and software", "remove") {
        @Override
        public void exec(@NotNull Result result) {
            new UninstallExec(result);
        }
    },
    UNLINK("Uninstall linked plugins") {
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
        Console.log(Type.DEBUG, "Running command " + this.refs[0] + "\n");
        if (result.tokens().flags().containsKey(Flag.HELP)) {
            this.help();
        } else {
            this.exec(result);
        }
        Console.log(Type.DEBUG, "Finished running command " + this.refs[0] + "\n");
    }

    public void help() {
        Console.log(Type.HELP, this.refs[0] + ": " + this.info + "\n");
    }

    /**
     * Execute the command executor of the respective command
     * @param result Parser result to execute
     */
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
