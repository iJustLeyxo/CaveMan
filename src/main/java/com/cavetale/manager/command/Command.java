package com.cavetale.manager.command;

import com.cavetale.manager.Manager;
import com.cavetale.manager.data.plugin.Category;
import com.cavetale.manager.data.plugin.Plugin;
import com.cavetale.manager.data.plugin.Server;
import com.cavetale.manager.data.server.Software;
import com.cavetale.manager.parser.Flag;
import com.cavetale.manager.parser.InputException;
import com.cavetale.manager.parser.Result;
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
            Set<Plugin> plugins = result.pluginManager().get(null, true, null);
            Set<Software> software = result.softwareManager().get(null, true);
            if (plugins.isEmpty() && software.isEmpty()) {
                Console.log(Type.REQUESTED, Style.WARN, "Nothing selected\n");
                return;
            }
            if (!plugins.isEmpty()) {
                Console.logL(Type.REQUESTED, Style.INSTALL, plugins.size() +
                        " plugin(s) selected", 4, 21, plugins.toArray());
            }
            if (!software.isEmpty()) {
                Console.sep();
                Console.logL(Type.REQUESTED, Style.INSTALL, software.size() +
                        " software(s) selected", 4, 21, software.toArray());
            }
            if (!Console.confirm("Continue installation")) {
                return;
            }
            for (Plugin p : plugins) {
                p.install();
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
            Set<Plugin> plugins = result.pluginManager().get(null, true, null);
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
            Console.logL(Type.REQUESTED, Style.INSTALL, plugins.size() +
                    " plugin(s) selected", 4, 21, plugins.toArray());
            if (!Console.confirm("Continue linking")) {
                return;
            }
            for (Plugin p : plugins) {
                p.link(path);
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
                Set<Plugin> selected = result.pluginManager().get(null, true, null);
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
            Set<Plugin> plugins = result.pluginManager().get(null, true, null);
            Set<Software> software = result.softwareManager().get(null, true);
            // Compare selected elements to installed elements
            if (!plugins.isEmpty() || !software.isEmpty()) { // TODO: Extract to plugin and software manager
                if (!plugins.isEmpty()) { // Compare selected to installed plugins
                    Console.sep();
                    Console.logL(Type.REQUESTED, Style.SELECT, plugins.size() +
                            " plugins(s) selected", 4, 21, plugins.toArray());
                    plugins = result.pluginManager().get(true, true, false);
                    if (!plugins.isEmpty()) {
                        Console.sep();
                        Console.logL(Type.REQUESTED, Style.INSTALL, plugins.size() +
                                " plugins(s) installed (not linked)", 4, 21, plugins.toArray());
                    }
                    plugins = result.pluginManager().get(true, true, true);
                    if (!plugins.isEmpty()) {
                        Console.sep();
                        Console.logL(Type.REQUESTED, Style.LINK, plugins.size() +
                                " plugins(s) installed (linked)", 4, 21, plugins.toArray());
                    }
                    plugins = result.pluginManager().get(true, false, null);
                    if (!plugins.isEmpty()) {
                        Console.sep();
                        Console.logL(Type.REQUESTED, Style.SUPERFLUOUS, plugins.size() +
                                " plugins(s) superfluous", 4, 21, plugins.toArray());
                    }
                    plugins = result.pluginManager().get(false, true, null);
                    if (!plugins.isEmpty()) {
                        Console.sep();
                        Console.logL(Type.REQUESTED, Style.MISSING, plugins.size() +
                                " plugins(s) missing", 4, 21, plugins.toArray());
                    }
                }
                if (!software.isEmpty()) { // Compare selected to installed software
                    Console.sep();
                    Console.logL(Type.REQUESTED, Style.SELECT, software.size() +
                            " software(s) selected", 4, 21, software.toArray());
                    software = result.softwareManager().get(true, true);
                    if (!software.isEmpty()) {
                        Console.sep();
                        Console.logL(Type.REQUESTED, Style.INSTALL, software.size() +
                                " software(s) installed", 4, 21, software.toArray());
                    }
                    software = result.softwareManager().get(true, false);
                    if (!software.isEmpty()) {
                        Console.sep();
                        Console.logL(Type.REQUESTED, Style.SUPERFLUOUS, software.size() +
                                " software(s) superfluous", 4, 21, software.toArray());
                    }
                    software = result.softwareManager().get(false, true);
                    if (!software.isEmpty()) {
                        Console.sep();
                        Console.logL(Type.REQUESTED, Style.MISSING, software.size() +
                                " software(s) missing", 4, 21, software.toArray());
                    }
                }
            } else { // Get installed elements if none have been selected for comparison
                Set<Plugin> unlinked = result.pluginManager().get(true, null, false);
                Set<Plugin> linked = result.pluginManager().get(true, null, true);
                Set<Software> installed = result.softwareManager().get(true, null);
                if (!unlinked.isEmpty() || !linked.isEmpty() || !installed.isEmpty()) {
                    if (!unlinked.isEmpty()) {
                        Console.sep();
                        Console.logL(Type.REQUESTED, Style.INSTALL, unlinked.size() +
                                " plugins(s) installed (not linked)", 4, 21, unlinked.toArray());
                    }
                    if (!linked.isEmpty()) {
                        Console.sep();
                        Console.logL(Type.REQUESTED, Style.LINK, linked.size() +
                                " plugins(s) installed (linked)", 4, 21, linked.toArray());
                    }
                    if (!installed.isEmpty()) {
                        Console.sep();
                        Console.logL(Type.REQUESTED, Style.SOFTWARE, installed.size() +
                                " software(s) installed", 4, 21, linked.toArray());
                    }
                } else {
                    Console.log(Type.REQUESTED, Style.INFO, "Nothing installed\n");
                }
            }
            // Always output unknown elements
            Set<String> unknown = result.pluginManager().unknown();
            if (!unknown.isEmpty()) {
                Console.sep();
                Console.logL(Type.REQUESTED, Style.UNKNOWN, unknown.size() +
                        " plugins(s) unknown", 4, 21, unknown.toArray());
            }
            unknown = result.softwareManager().unknown();
            if (!unknown.isEmpty()) {
                Console.sep();
                Console.logL(Type.REQUESTED, Style.UNKNOWN, unknown.size() +
                        " software(s) unknown", 4, 21, unknown.toArray());
            }
        }
    },

    UNINSTALL("Uninstall plugins, server software and files", "remove", "delete") {
        @Override // TODO: Make work with -a flag
        public void run(@NotNull Result result) {
            Set<Plugin> unlinked = result.pluginManager().get(null, true, false);
            Set<Plugin> linked = result.pluginManager().get(null, true, true);
            Set<Software> software = result.softwareManager().get(null, true);
            if (unlinked.isEmpty() && linked.isEmpty() && software.isEmpty()) {
                Console.log(Type.REQUESTED, Style.WARN, "Nothing selected\n");
                return;
            }
            if (!unlinked.isEmpty()) {
                Console.logL(Type.REQUESTED, Style.INSTALL, unlinked.size() +
                        " plugin(s) installed", 4, 21, unlinked.toArray());
            }
            if (!linked.isEmpty()) {
                Console.logL(Type.REQUESTED, Style.LINK, linked.size() +
                        " plugin(s) linked", 4, 21, linked.toArray());
            }
            if (!software.isEmpty()) {
                Console.sep();
                Console.logL(Type.REQUESTED, Style.INSTALL, software.size() +
                        " software(s) selected", 4, 21, software.toArray());
            }
            if (!Console.confirm("Continue removal")) {
                return;
            }
            for (Plugin p : unlinked) {
                p.uninstall();
            }
            for (Plugin l : linked) {
                l.unlink();
            }
            for (Software s : software) {
                s.uninstall();
            }
            // TODO: FIle removal?
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
