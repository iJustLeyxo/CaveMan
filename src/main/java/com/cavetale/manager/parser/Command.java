package com.cavetale.manager.parser;

import com.cavetale.manager.Manager;
import com.cavetale.manager.data.plugin.Category;
import com.cavetale.manager.data.plugin.Plugin;
import com.cavetale.manager.data.plugin.Server;
import com.cavetale.manager.data.server.Software;
import com.cavetale.manager.parser.container.PathContainer;
import com.cavetale.manager.util.console.Console;
import com.cavetale.manager.util.console.Style;
import com.cavetale.manager.util.console.Type;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public enum Command {
    EXIT("Exit interactive mode", "quit", "q", "stop") {
        @Override
        public void run(@NotNull Result result) {
            Manager.exit();
        }
    },

    HELP("Show command and flag help") {
        @Override
        public void run(@NotNull Result result) {
            Manager.help();
        }
    },

    INSTALL("Install plugins and server software", "add") {
        @Override
        public void run(@NotNull Result result) {
            Set<Plugin> plugins = result.plugIndexer().getAll(null, true);
            Set<Software> software = result.softwareIndexer().getAll(null, true);
            if (plugins.isEmpty() && software.isEmpty()) {
                Console.log(Type.REQUESTED, Style.WARN, "Nothing selected\n");
                return;
            }
            Console.log(Type.REQUESTED, Style.INSTALL,
                    plugins.size() + " plugins and " + software.size() + " software to install");
            if (!Console.confirm("Continue installation")) {
                return;
            }
            Set<Plugin> installed = result.plugIndexer().getAll(true, null); // TODO: Move to plugins
            for (Plugin p : plugins) {
                p.install(installed);
            }
            for (Software s : software) {
                s.install();
            }
        }
    },

    LINK("Install plugins as symbolic links") {
        @Override
        public
        void run(@NotNull Result result) {
            Set<Plugin> plugins = result.plugIndexer().getAll(null, true);
            if (!result.tokens().flags().containsKey(Flag.PATH) ||
                    result.tokens().flags().get(Flag.PATH).isEmpty()) {
                Console.log(Type.REQUESTED, Style.WARN, "No path specified\n");
                return;
            }
            String path = ((PathContainer) result.tokens().flags().get(Flag.PATH)).get();
            assert path != null;
            if (plugins.isEmpty()) {
                Console.log(Type.REQUESTED, Style.WARN, "Nothing selected\n");
                return;
            }
            Console.log(Type.REQUESTED, Style.LINK, plugins.size() + " plugins to link");
            if (!Console.confirm("Continue linking")) {
                return;
            }
            Set<Plugin> installed = result.plugIndexer().getAll(true, null); // TODO: Move to plugins
            for (Plugin p : plugins) {
                p.link(path, installed);
            }
        }
    },

    LIST("List plugins, categories, servers and server software", "show", "resolve") {
        @Override
        public void run(@NotNull Result result) {
            if (result.tokens().flags().containsKey(Flag.ALL)) {
                boolean all = true;
                if (result.tokens().flags().containsKey(Flag.PLUGIN)) {
                    Plugin.list();
                    all = false;
                }
                if (result.tokens().flags().containsKey(Flag.CATEGORY)) {
                    Category.list();
                    all = false;
                }
                if (result.tokens().flags().containsKey(Flag.SERVER)) {
                    Server.list();
                    all = false;
                }
                if (result.tokens().flags().containsKey(Flag.SOFTWARE)) {
                    Software.list();
                    all = false;
                }
                if (all) {
                    Plugin.list();
                    Category.list();
                    Server.list();
                    Software.list();
                }
            } else { // List selected plugins
                Set<Plugin> selected = result.plugIndexer().getAll(null, true);
                if (!selected.isEmpty()) {
                    Console.logL(Type.REQUESTED, Style.PLUGIN, selected.size() +
                            " plugin(s) selected", 4, 21, selected.toArray());
                } else {
                    Console.log(Type.WARN, "No plugins selected\n");
                }
            }
        }
    },

    STATUS("View installation status", "info", "verify", "check") {
        @Override
        public
        void run(@NotNull Result result) {
            result.plugIndexer().summarize();
            result.softwareIndexer().summarize();
        }
    },

    UNINSTALL("Uninstall plugins, server software and files", "remove", "delete") {
        @Override
        public void run(@NotNull Result result) {
            Set<Plugin> plugins;
            if (result.tokens().flags().containsKey(Flag.ALL)) {
                plugins = result.plugIndexer().getAll(true, null);
            } else {
                plugins = result.plugIndexer().getAll(null, true);
            }
            Set<Software> software = result.softwareIndexer().getAll(null, true);
            if (plugins.isEmpty() && software.isEmpty()) {
                Console.log(Type.REQUESTED, Style.WARN, "Nothing selected\n");
                return;
            }
            Console.log(Type.REQUESTED, Style.UPDATE,
                    plugins.size() + " plugins and " + software.size() + " software to uninstall");
            if (!Console.confirm("Continue removal")) {
                return;
            }
            for (Plugin p : plugins) {
                p.uninstall();
            }
            for (Software s : software) {
                s.uninstall();
            }
        }
    },

    UPDATE("Update plugins and software", "upgrade") {
        @Override
        public void run(@NotNull Result result) {
            Set<Plugin> plugins = result.plugIndexer().getAll(null, true);
            Set<Software> software = result.softwareIndexer().getAll(null, true);
            if (plugins.isEmpty() && software.isEmpty()) {
                Console.log(Type.REQUESTED, Style.WARN, "Nothing selected\n");
                return;
            }
            Console.log(Type.REQUESTED, Style.UPDATE,
                    plugins.size() + " plugins and " + software.size() + " software to update");
            if (!Console.confirm("Continue update")) {
                return;
            }
            for (Plugin p : plugins) {
                // TODO: Update plugins
            }
            for (Software s  : software) {
                // TODO: Update soware
            }
        }
    };

    public final @NotNull String[] refs;
    public final @NotNull String info;

    Command(@NotNull String info, @NotNull String... refs) {
        this.refs = new String[refs.length + 1];
        this.refs[0] = this.name().toLowerCase();
        System.arraycopy(refs, 0, this.refs, 1, refs.length);
        this.info = info;
    }

    public abstract void run(@NotNull Result result);

    public void help(@NotNull Result result) {
        Console.log(Type.REQUESTED, Style.HELP, this.refs[0] + ": " + this.info);
    }

    public static @NotNull Command get(@NotNull String ref) throws NotFoundException {
        for (Command c : Command.values()) {
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
