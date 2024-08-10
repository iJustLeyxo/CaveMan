package com.cavetale.manager.command;

import com.cavetale.manager.data.plugin.Plugin;
import com.cavetale.manager.parser.Flag;
import com.cavetale.manager.parser.Result;
import com.cavetale.manager.util.console.Console;
import com.cavetale.manager.util.console.EscCode;
import com.cavetale.manager.util.console.Style;
import com.cavetale.manager.util.console.Verbosity;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.nio.file.Files;
import java.util.Set;

public final class UnlinkExec extends Exec {
    public UnlinkExec(@NotNull Result result) {
        super(result);
    }

    @Override
    public void run() {
        Set<Plugin> selected;
        if (!result.tokens().flags().containsKey(Flag.ALL)) {
            selected = result.pluginManager().get(null, true, null);
        } else {
            selected = result.pluginManager().get(true, null, true);
        }
        if (!selected.isEmpty()) {
            Console.list(selected.size() + " plugins(s) to unlink",
                    selected, Verbosity.OVERRIDE, EscCode.BLUE, 4, 21);
        } else {
            Console.out(Style.INFO, "Nothing selected for unlink\n\n");
            return;
        }
        File folder = new File("plugins/");
        if (!result.tokens().flags().containsKey(Flag.FORCE)) {
            if (!Console.in("Proceed with unlinking (Y/n)?").equalsIgnoreCase("y")) {
                return;
            }
        }
        for (Plugin p : selected) {
            Console.out(Style.INFO, "Unlinking " + p.name);
            File file = new File(folder, p.name + ".jar");
            if (!file.exists()) {
                if(!Console.out(Style.INFO, Style.WARN, " skipped (file no longer exists)\n")) {
                    Console.out(Style.WARN, "Unlinking " + p.name + " skipped (file no longer exists)\n");
                }
                continue;
            }
            if (!Files.isSymbolicLink(file.toPath())) {
                if(!Console.out(Style.INFO, Style.WARN, " skipped (not a symbolic link)\n")) {
                    Console.out(Style.WARN, "Unlinking " + p.name + " skipped (not a symbolic link)");
                }
                continue;
            }
            if (file.delete()) {
                Console.out(Style.INFO, Style.DONE, " done\n");
                continue;
            }
            if(!Console.out(Style.INFO, Style.WARN, " failed\n")) {
                Console.out(Style.WARN, "Deleting " + p.name + " failed");
            }
        }
    }

    @Override
    public void help() {

    }
}
