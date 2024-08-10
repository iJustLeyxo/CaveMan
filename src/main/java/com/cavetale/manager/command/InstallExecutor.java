package com.cavetale.manager.command;

import com.cavetale.manager.data.Plugin;
import com.cavetale.manager.data.ServerSoftware;
import com.cavetale.manager.parser.Flag;
import com.cavetale.manager.parser.Result;
import com.cavetale.manager.util.cmd.Cmd;
import com.cavetale.manager.util.cmd.EscCode;
import com.cavetale.manager.util.cmd.Style;
import com.cavetale.manager.util.download.Download;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public final class InstallExecutor extends Executor {
    public InstallExecutor(@NotNull Result result) {
        super(result);
    }

    @Override
    public void run() {
        if (!serverSoftware() && !plugins()) {
            Cmd.out(Style.WARN, "Nothing selected for installation\n\n");
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
        Cmd.list(selected.size() + " software(s) selected for installation",
                selected, Style.WARN, EscCode.BLUE, 4, 21);
        if (!this.result.tokens().flags().containsKey(Flag.FORCE)) {
            if (!Cmd.in("Proceed with server software installation (Y/n)?").equalsIgnoreCase("y")) {
                return true;
            }
        }
        for (ServerSoftware s : selected) {
            Cmd.out(Style.INFO, "Installing " + s.ref + " server software");
            File file = s.file();
            if (file == null) {
                if (!Cmd.out(Style.INFO, Style.WARN, " skipped (unable to install)\n")) {
                    Cmd.out(Style.WARN, "Skipped " + s.ref + " server software (unable to install)\n");
                }
                Cmd.out(Style.WARN, s.ref + " server software uri is not a file\n");
                continue;
            }
            if (file.exists()) {
                if (!Cmd.out(Style.INFO, Style.WARN, " skipped (already installed)\n")) {
                    Cmd.out(Style.WARN, s.ref + " server software is already installed\n");
                }
                continue;
            }
            try {
                Download.download(s.uri, file);
                Cmd.out(Style.INFO, Style.DONE, " done\n");
            } catch (IOException e) {
                if (!Cmd.out(Style.INFO, Style.WARN, " failed\n")) {
                    Cmd.out(Style.WARN, "Installing " + s.ref + " server software failed\n");
                }
            }
        }
        return true;
    }

    private boolean plugins() {
        Set<Plugin> selected = this.result.plugins().get(null, true, null);
        if (selected.isEmpty()) {
            return false;
        }
        Cmd.list(selected.size() + " plugins(s) selected for installation",
                selected, Style.OVERRIDE, EscCode.BLUE, 4, 21);
        if (!this.result.tokens().flags().containsKey(Flag.FORCE)) {
            if (!Cmd.in("Proceed with plugin installation (Y/n)?").equalsIgnoreCase("y")) {
                return true;
            }
        }
        Cmd.out(Style.LOG, "Creating plugins directory\n");
        File folder = new File("plugins/");
        folder.mkdirs();
        for (Plugin p : selected) {
            Cmd.out(Style.INFO, "Installing " + p.name);
            File file = new File(folder, p.name + ".jar");
            if (file.exists()) {
                if (!Cmd.out(Style.INFO, Style.WARN, " skipped (already installed)\n")) {
                    Cmd.out(Style.WARN, "Installing " + p.name + " skipped (already installed)\n");
                }
                continue;
            }
            if (p.uri == null) {
                if (!Cmd.out(Style.INFO, Style.WARN, " skipped (no url)\n")) {
                    Cmd.out(Style.WARN, "Installing " + p.name + " skipped (no url)\n");
                }
                continue;
            }
            try {
                Download.download(p.uri, file);
                Cmd.out(Style.INFO, Style.DONE, " done\n");
            } catch (IOException e) {
                if (!Cmd.out(Style.INFO, Style.WARN, " failed\n")) {
                    Cmd.out(Style.WARN, "Installing " + p.name + " failed\n");
                }
            }
        }
        Cmd.out("\n");
        return true;
    }
}
