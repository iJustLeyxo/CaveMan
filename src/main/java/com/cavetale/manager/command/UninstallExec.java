package com.cavetale.manager.command;

import com.cavetale.manager.data.plugin.Plugin;
import com.cavetale.manager.data.server.Software;
import com.cavetale.manager.parser.Command;
import com.cavetale.manager.parser.Flag;
import com.cavetale.manager.parser.Result;
import com.cavetale.manager.parser.container.PathContainer;
import com.cavetale.manager.util.console.Console;
import com.cavetale.manager.util.console.Style;
import com.cavetale.manager.util.console.Type;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.nio.file.Files;
import java.util.Set;

public final class UninstallExec extends Exec {
    public UninstallExec(@NotNull Result result) {
        super(result);
    }

    @Override
    public void run() {
        if (!serverSoftware() && !plugins() && !path()) {
            Console.log(Type.INFO, "Nothing selected for uninstall\n");
        }
    }

    private boolean path() {
        if (!this.result.tokens().flags().containsKey(Flag.PATH)) {
            return false;
        }
        String selected = ((PathContainer) this.result.tokens().flags().get(Flag.PATH)).get();
        Console.log(Type.REQUESTED, Style.UNINSTALL, selected + " selected to uninstall\n");
        if (!this.result.tokens().flags().containsKey(Flag.FORCE)) {
            if (!Console.in("Proceed with uninstall (Y/n)?").equalsIgnoreCase("y")) {
                return true;
            }
        }
        if (selected == null || selected.isEmpty()) {
            return false;
        }
        Console.log(Type.INFO, "Uninstalling " + selected);
        File file = new File(selected);
        if (!file.exists()) {
            if (!Console.log(Type.INFO, Style.WARN, " skipped (not installed)\n")) {
                Console.log(Type.WARN, "Uninstalling " + selected + " server software skipped (not installed)\n");
            }
            return true;
        }
        if (!file.delete()) {
            if (!Console.log(Type.INFO, Style.ERR, " failed\n")) {
                Console.log(Type.ERR, "Uninstalling " + selected + " failed\n");
            }
            return true;
        }
        Console.log(Type.INFO, Style.DONE, " done\n");
        return true;
    }

    private boolean serverSoftware() {
        if (!this.result.tokens().flags().containsKey(Flag.SOFTWARE)) {
            return false;
        }
        Set<Software> selected = this.result.softwareManager().get(null, true);
        Console.logL(Type.REQUESTED, Style.UNINSTALL, selected.size() + " software(s) selected to uninstall",
                4, 21, selected.toArray());
        if (!this.result.tokens().flags().containsKey(Flag.FORCE)) {
            if (!Console.in("Proceed with uninstall (Y/n)?").equalsIgnoreCase("y")) {
                return true;
            }
        }
        for (Software s : selected) {
            Console.log(Type.INFO, "Uninstalling " + s.refs[0] + " server software\n");
            File file = s.file();
            if (file == null) {
                if (!Console.log(Type.INFO, Style.ERR, " skipped (unable to uninstall)\n")) {
                    Console.log(Type.ERR, "Uninstalling " + s.refs[0] + " server software skipped (unable to uninstall)\n");
                }
                continue;
            }
            if (!file.exists()) {
                if (!Console.log(Type.INFO, Style.WARN, " skipped (not installed)\n")) {
                    Console.log(Type.WARN, "Uninstalling " + s.refs[0] + " server software skipped (not installed)\n");
                }
                continue;
            }
            if (!file.delete()) {
                if (!Console.log(Type.INFO, Style.ERR, " failed\n")) {
                    Console.log(Type.ERR, "Uninstalling " + s.refs[0] + " failed\n");
                }
                continue;
            }
            Console.log(Type.INFO, Style.DONE, " done\n");
        }
        return true;
    }

    private boolean plugins() {
        Set<Plugin> selected;
        if (this.result.tokens().flags().containsKey(Flag.ALL)) {
            selected = this.result.pluginManager().get(true, null, null);
        } else {
            selected = this.result.pluginManager().get(null, true, null);
        }
        if (!selected.isEmpty()) {
            Console.logL(Type.REQUESTED, Style.UNINSTALL, selected.size() + " plugins(s) to uninstall",
                    4, 21, selected.toArray());
        } else {
            return false;
        }
        File folder = new File("plugins/");
        if (!this.result.tokens().flags().containsKey(Flag.FORCE)) {
            if (!Console.in("Proceed with uninstall (Y/n)?").equalsIgnoreCase("y")) {
                return true;
            }
        }
        for (Plugin p : selected) {
            Console.log(Type.INFO, "Uninstalling " + p.name);
            File file = new File(folder, p.name + ".jar");
            if (!file.exists()) {
                if (!Console.log(Type.INFO, Style.WARN, " skipped (not installed)\n")) {
                    Console.log(Type.WARN, "Uninstalling " + p.name + " skipped (not installed)\n");
                }
                continue;
            }
            if (Files.isSymbolicLink(file.toPath())) {
                if (!Console.log(Type.INFO, Style.WARN,
                        " skipped (symbolic link, use " + Command.UNLINK.refs[0] + " to remove)\n")) {
                    Console.log(Type.WARN, "Uninstalling " + p.name +
                            " skipped (symbolic link, use " + Command.UNLINK.refs[0] + " to remove)\n");
                }
                continue;
            }
            if (file.delete()) {
                Console.log(Type.INFO, Style.DONE, " done\n");
                continue;
            }
            if(!Console.log(Type.INFO, Style.ERR, " failed\n")) {
                Console.log(Type.ERR, "Uninstalling " + p.name + " failed\n");
            }
        }
        return true;
    }
}
