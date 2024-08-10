package com.cavetale.manager.command;

import com.cavetale.manager.data.plugin.Plugin;
import com.cavetale.manager.parser.Command;
import com.cavetale.manager.parser.Flag;
import com.cavetale.manager.parser.Result;
import com.cavetale.manager.util.console.Console;
import com.cavetale.manager.util.console.Detail;
import com.cavetale.manager.util.console.Style;
import com.cavetale.manager.util.console.XCode;
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
                    selected, Detail.OVERRIDE, XCode.BLUE, 4, 21);
        } else {
            Console.log(Style.INFO, "Nothing selected for unlink\n\n");
            return;
        }
        File folder = new File("plugins/");
        if (!result.tokens().flags().containsKey(Flag.FORCE)) {
            if (!Console.in("Proceed with unlinking (Y/n)?").equalsIgnoreCase("y")) {
                return;
            }
        }
        for (Plugin p : selected) {
            Console.log(Style.INFO, "Unlinking " + p.name);
            File file = new File(folder, p.name + ".jar");
            if (!file.exists()) {
                if(!Console.log(Style.INFO, Style.WARN, " skipped (file no longer exists)\n")) {
                    Console.log(Style.WARN, "Unlinking " + p.name + " skipped (file no longer exists)\n");
                }
                continue;
            }
            if (!Files.isSymbolicLink(file.toPath())) {
                if (!Console.log(Style.INFO, Style.WARN,
                        " skipped (not a symbolic link, use " + Command.UNINSTALL.refs[0] + " to remove)\n")) {
                    Console.log(Style.WARN, "Uninstalling " + p.name +
                            " skipped (not a symbolic link, use " + Command.UNINSTALL.refs[0] + " to remove)\n");
                }
                continue;
            }
            if (file.delete()) {
                Console.log(Style.INFO, Style.DONE, " done\n");
                continue;
            }
            if(!Console.log(Style.INFO, Style.ERR, " failed\n")) {
                Console.log(Style.ERR, "Deleting " + p.name + " failed");
            }
        }
    }
}
