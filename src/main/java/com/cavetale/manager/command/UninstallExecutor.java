package com.cavetale.manager.command;

import com.cavetale.manager.data.Plugin;
import com.cavetale.manager.data.ServerSoftware;
import com.cavetale.manager.parser.Command;
import com.cavetale.manager.parser.Flag;
import com.cavetale.manager.parser.Result;
import com.cavetale.manager.util.cmd.Cmd;
import com.cavetale.manager.util.cmd.EscCode;
import com.cavetale.manager.util.cmd.Style;
import com.cavetale.manager.util.cmd.Verbosity;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.nio.file.Files;
import java.util.Set;

public final class UninstallExecutor extends Executor {
    public UninstallExecutor(@NotNull Result result) {
        super(result);
    }

    @Override
    public void run() {
        if (!serverSoftware() && !plugins()) {
            Cmd.out(Style.INFO, "Nothing selected for uninstall\n\n");
        }
    }

    @Override
    public void help() {

    }

    private boolean serverSoftware() {
        if (!this.result.tokens().flags().containsKey(Flag.SOFTWARE)) {
            return false;
        }
        Set<ServerSoftware> selected = this.result.serverSoftware().selected();
        Cmd.list(selected.size() + " software(s) selected to uninstall",
                selected, Style.WARN, EscCode.BLUE, 4, 21);
        for (ServerSoftware s : selected) {
            Cmd.out(Style.INFO, "Uninstalling " + s.ref + " server software");
            File file = s.file();
            if (file == null) {
                if (!Cmd.out(Style.INFO, Style.ERR, " skipped (unable to uninstall)")) {
                    Cmd.out(Style.ERR, "Skipping " + s.ref + " server software (unable to uninstall)");
                }
                continue;
            }
            if (!file.exists()) {
                if (!Cmd.out(Style.INFO, Style.WARN, " skipped (not installed)")) {
                    Cmd.out(Style.WARN, "Skipping " + s.ref + " server software (not installed)");
                }
                continue;
            }
            file.delete();
            Cmd.out(Style.INFO, Style.DONE, " done\n\n");
        }
        return true;
    }

    private boolean plugins() {
        Set<Plugin> selected;
        if (this.result.tokens().flags().containsKey(Flag.ALL)) {
            selected = this.result.plugins().get(true, null, null);
        } else {
            selected = this.result.plugins().get(null, true, null);
        }
        if (!selected.isEmpty()) {
            Cmd.list(selected.size() + " plugins(s) to uninstall",
                    selected, Verbosity.OVERRIDE, EscCode.YELLOW, 4, 21);
        } else {
            return false;
        }
        File folder = new File("plugins/");
        if (!this.result.tokens().flags().containsKey(Flag.FORCE)) {
            if (!Cmd.in("Proceed with uninstall (Y/n)?").equalsIgnoreCase("y")) {
                return true;
            }
        }
        for (Plugin p : selected) {
            Cmd.out(Style.INFO, "Uninstalling " + p.name);
            File file = new File(folder, p.name + ".jar");
            if (!file.exists()) {
                if (!Cmd.out(Style.INFO, Style.WARN, " skipped (not installed)\n")) {
                    Cmd.out(Style.WARN, "Uninstalling " + p.name + " skipped (not installed)\n");
                }
                continue;
            }
            if (Files.isSymbolicLink(file.toPath())) {
                if (!Cmd.out(Style.INFO, Style.WARN,
                        " skipped (file is a symbolic link, use " + Command.UNLINK + " to remove)\n")) {
                    Cmd.out(Style.WARN, "Uninstalling " + p.name +
                            " skipped (file is a symbolic link, use " + Command.UNLINK + " to remove)\n");
                }
                continue;
            }
            if (file.delete()) {
                Cmd.out(Style.INFO, Style.DONE, " done\n");
                continue;
            }
            if(!Cmd.out(Style.INFO, Style.WARN, " failed\n")) {
                Cmd.out(Style.WARN, "Uninstalling " + p.name + " failed\n");
            }
        }
        return true;
    }
}
