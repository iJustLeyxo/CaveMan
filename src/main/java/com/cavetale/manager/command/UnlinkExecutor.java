package com.cavetale.manager.command;

import com.cavetale.manager.parser.Flag;
import com.cavetale.manager.parser.Result;
import com.cavetale.manager.data.Plugin;
import com.cavetale.manager.util.cmd.Cmd;
import com.cavetale.manager.util.cmd.EscCode;
import com.cavetale.manager.util.cmd.Style;
import com.cavetale.manager.util.cmd.Verbosity;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.nio.file.Files;
import java.util.Set;

public final class UnlinkExecutor extends Executor {
    public UnlinkExecutor(@NotNull Result result) {
        super(result);
    }

    @Override
    public void run() {
        Set<Plugin> selected;
        if (!result.tokens().flags().containsKey(Flag.ALL)) {
            selected = result.plugins().get(null, true, null);
        } else {
            selected = result.plugins().get(true, null, true);
        }
        if (!selected.isEmpty()) {
            Cmd.list(selected.size() + " plugins(s) to unlink",
                    selected, Verbosity.OVERRIDE, EscCode.BLUE, 4, 21);
        } else {
            Cmd.out(Style.INFO, "Nothing selected for unlink\n\n");
            return;
        }
        File folder = new File("plugins/");
        if (!result.tokens().flags().containsKey(Flag.FORCE)) {
            if (!Cmd.in("Proceed with unlinking (Y/n)?").equalsIgnoreCase("y")) {
                return;
            }
        }
        for (Plugin p : selected) {
            Cmd.out(Style.INFO, "Unlinking " + p.name);
            File file = new File(folder, p.name + ".jar");
            if (!file.exists()) {
                if(!Cmd.out(Style.INFO, Style.WARN, " skipped (file no longer exists)\n")) {
                    Cmd.out(Style.WARN, "Unlinking " + p.name + " skipped (file no longer exists)\n");
                }
                continue;
            }
            if (!Files.isSymbolicLink(file.toPath())) {
                if(!Cmd.out(Style.INFO, Style.WARN, " skipped (not a symbolic link)\n")) {
                    Cmd.out(Style.WARN, "Unlinking " + p.name + " skipped (not a symbolic link)");
                }
                continue;
            }
            if (file.delete()) {
                Cmd.out(Style.INFO, Style.DONE, " done\n");
                continue;
            }
            if(!Cmd.out(Style.INFO, Style.WARN, " failed\n")) {
                Cmd.out(Style.WARN, "Deleting " + p.name + " failed");
            }
        }
    }

    @Override
    public void help() {

    }
}
