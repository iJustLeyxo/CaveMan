package com.cavetale.manager.command;

import com.cavetale.manager.data.plugin.Plugin;
import com.cavetale.manager.parser.Flag;
import com.cavetale.manager.parser.Result;
import com.cavetale.manager.parser.container.PathContainer;
import com.cavetale.manager.util.console.Console;
import com.cavetale.manager.util.console.Detail;
import com.cavetale.manager.util.console.Style;
import com.cavetale.manager.util.console.XCode;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Set;

public final class LinkExec extends Exec {
    public LinkExec(@NotNull Result result) {
        super(result);
    }

    @Override
    public void run() {
        if (!this.result.tokens().flags().containsKey(Flag.PATH) ||
                this.result.tokens().flags().get(Flag.PATH).isEmpty()) {
            Console.log(Style.WARN, "No path specified for linking\n\n");
            return;
        }
        String path = ((PathContainer) this.result.tokens().flags().get(Flag.PATH)).get();
        Set<Plugin> selected = this.result.pluginManager().get(null, true, null);
        if (selected.isEmpty()) {
            Console.log(Style.WARN, "No plugins selected for linking\n\n");
            return;
        }
        assert path != null;
        Console.list(selected.size() + " plugins(s) selected for linking",
                selected, Detail.OVERRIDE, XCode.BLUE, 4, 21);
        if (!this.result.tokens().flags().containsKey(Flag.FORCE)) {
            if (!Console.in("Proceed with linking (Y/n)?").equalsIgnoreCase("y")) {
                return;
            }
        }
        Console.log(Style.DEBUG, "Creating plugins directory\n");
        File folder = new File("plugins/");
        folder.mkdirs();
        for (Plugin p : selected) {
            Console.log(Style.INFO, "Linking " + p.name);
            File link = new File(new File("plugins"), p.name + ".jar");
            File original = new File(new File(path), p.name + ".jar");
            if (link.exists()) {
                if (!Console.log(Style.INFO, Style.WARN, " skipped (already installed)\n")) {
                    Console.log(Style.WARN, "Linking " + p.name + " skipped (already installed)\n");
                }
                continue;
            }
            try {
                Files.createSymbolicLink(link.toPath(), original.toPath());
                Console.log(Style.DONE, " done\n");
            } catch (IOException e) {
                if (!Console.log(Style.INFO, Style.ERR, " failed\n")) {
                    Console.log(Style.ERR, "Linking " + p.name + " failed\n");
                }
            }
        }
        Console.log("\n");
    }
}
