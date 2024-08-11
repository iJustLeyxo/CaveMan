package com.cavetale.manager.command;

import com.cavetale.manager.data.plugin.Plugin;
import com.cavetale.manager.parser.Command;
import com.cavetale.manager.parser.Flag;
import com.cavetale.manager.parser.Result;
import com.cavetale.manager.util.console.Console;
import com.cavetale.manager.util.console.Style;
import com.cavetale.manager.util.console.Type;
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
        // Check inputs
        Set<Plugin> selected;
        if (!result.tokens().flags().containsKey(Flag.ALL)) {
            selected = result.pluginManager().get(null, true, null);
        } else {
            selected = result.pluginManager().get(true, null, true);
        }
        if (!selected.isEmpty()) {
            Console.logL(Type.REQUESTED, Style.UNINSTALL, selected.size() + " plugins(s) to unlink",
                    4, 21, selected.toArray());
        } else {
            Console.log(Type.INFO, "Nothing selected for unlink\n");
            return;
        }
        File folder = new File("plugins/");
        if (!result.tokens().flags().containsKey(Flag.FORCE)) {
            if (!Console.in("Proceed with unlinking (Y/n)?").equalsIgnoreCase("y")) {
                return;
            }
        }
        // Unlink plugins
        for (Plugin p : selected) {
            Console.log(Type.INFO, "Unlinking " + p.ref);
            File file = new File(folder, p.ref + ".jar");
            if (!Files.isSymbolicLink(file.toPath())) {
                if (!Console.log(Type.INFO, Style.WARN,
                        " skipped (not a symbolic link, use " + Command.UNINSTALL.refs[0] + " to remove)\n")) {
                    Console.log(Type.WARN, "Uninstalling " + p.ref +
                            " skipped (not a symbolic link, use " + Command.UNINSTALL.refs[0] + " to remove)\n");
                }
                continue;
            }
            if (file.delete()) {
                Console.log(Type.INFO, Style.DONE, " done\n");
                continue;
            }
            if(!Console.log(Type.INFO, Style.ERR, " failed\n")) {
                Console.log(Type.ERR, "Deleting " + p.ref + " failed");
            }
        }
    }
}
