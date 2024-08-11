package com.cavetale.manager.command;

import com.cavetale.manager.data.plugin.Plugin;
import com.cavetale.manager.data.server.Software;
import com.cavetale.manager.parser.Flag;
import com.cavetale.manager.parser.Result;
import com.cavetale.manager.util.Download;
import com.cavetale.manager.util.console.Console;
import com.cavetale.manager.util.console.Style;
import com.cavetale.manager.util.console.Type;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Set;

public final class InstallExec extends Exec {
    public InstallExec(@NotNull Result result) {
        super(result);
    }

    @Override
    public void run() {
        if (!serverSoftware() && !plugins()) {
            Console.log(Type.WARN, "Nothing selected for installation\n");
        }
    }

    private boolean serverSoftware() {
        if (!this.result.tokens().flags().containsKey(Flag.SOFTWARE)) {
            return false;
        }
        Set<Software> selected = this.result.serverSoftware().get(null, true);
        Console.logL(Type.REQUESTED, Style.INSTALL, selected.size() + " server software(s) selected for installation",
                4, 21, selected.toArray());
        if (!this.result.tokens().flags().containsKey(Flag.FORCE)) {
            if (!Console.in("Proceed with server software installation (Y/n)?").equalsIgnoreCase("y")) {
                return true;
            }
        }
        for (Software s : selected) {
            Console.log(Type.INFO, "Installing " + s.refs[0] + " server software");
            File file = s.file();
            if (file == null) {
                if (!Console.log(Type.INFO, Style.WARN, " skipped (unable to install)\n")) {
                    Console.log(Type.WARN, "Skipped " + s.refs[0] + " server software (unable to install)\n");
                }
                Console.log(Type.WARN, s.refs[0] + " server software uri is not a file\n");
                continue;
            }
            if (file.exists()) {
                if (!Console.log(Type.INFO, Style.WARN, " skipped (already installed)\n")) {
                    Console.log(Type.WARN, s.refs[0] + " server software is already installed\n");
                }
                continue;
            }
            try {
                Download.download(s.uri, file);
                Console.log(Type.INFO, Style.DONE, " done\n");
            } catch (IOException e) {
                if (!Console.log(Type.INFO, Style.ERR, " failed\n")) {
                    Console.log(Type.ERR, "Installing " + s.refs[0] + " server software failed\n");
                }
            }
        }
        Console.sep();
        return true;
    }

    private boolean plugins() {
        Set<Plugin> selected = this.result.pluginManager().get(null, true, null);
        if (selected.isEmpty()) {
            return false;
        }
        Console.logL(Type.REQUESTED, Style.INSTALL, selected.size() + " plugins(s) selected for installation",
                4, 21, selected.toArray());
        if (!this.result.tokens().flags().containsKey(Flag.FORCE)) {
            if (!Console.in("Proceed with plugin installation (Y/n)?").equalsIgnoreCase("y")) {
                return true;
            }
        }
        Console.log(Type.DEBUG, "Creating plugins directory\n");
        File folder = new File("plugins/");
        folder.mkdirs();
        for (Plugin p : selected) {
            Console.log(Type.INFO, "Installing " + p.name);
            File file = new File(folder, p.name + ".jar");
            if (file.exists() || Files.isSymbolicLink(file.toPath())) {
                if (!Console.log(Type.INFO, Style.WARN, " skipped (already installed)\n")) {
                    Console.log(Type.WARN, "Installing " + p.name + " skipped (already installed)\n");
                }
                continue;
            }
            if (p.uri == null) {
                if (!Console.log(Type.INFO, Style.WARN, " skipped (no url)\n")) {
                    Console.log(Type.WARN, "Installing " + p.name + " skipped (no url)\n");
                }
                continue;
            }
            try {
                Download.download(p.uri, file);
                Console.log(Type.INFO, Style.DONE, " done\n");
            } catch (IOException e) {
                if (!Console.log(Type.INFO, Style.ERR, " failed\n")) {
                    Console.log(Type.ERR, "Installing " + p.name + " failed\n");
                }
            }
        }
        Console.sep();
        return true;
    }
}
