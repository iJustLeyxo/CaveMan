package com.cavetale.manager.command;

import com.cavetale.manager.data.plugin.Plugin;
import com.cavetale.manager.parser.Flag;
import com.cavetale.manager.parser.Result;
import com.cavetale.manager.parser.container.PathContainer;
import com.cavetale.manager.util.console.Console;
import com.cavetale.manager.util.console.Style;
import com.cavetale.manager.util.console.Type;
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
        // Check inputs
        if (!this.result.tokens().flags().containsKey(Flag.PATH) ||
                this.result.tokens().flags().get(Flag.PATH).isEmpty()) {
            Console.log(Type.WARN, "No path specified for linking\n");
            return;
        }
        String path = ((PathContainer) this.result.tokens().flags().get(Flag.PATH)).get();
        Set<Plugin> selected = this.result.pluginManager().get(null, true, null);
        if (selected.isEmpty()) {
            Console.log(Type.WARN, "No plugins selected for linking\n");
            return;
        }
        assert path != null;
        Console.logL(Type.REQUESTED, Style.LINK, selected.size() + " plugins(s) selected for linking",
                4, 21, selected);
        if (!this.result.tokens().flags().containsKey(Flag.FORCE)) {
            if (!Console.in("Proceed with linking (Y/n)?").equalsIgnoreCase("y")) {
                return;
            }
        }
        Console.log(Type.DEBUG, "Creating plugins directory\n");
        File folder = new File("plugins/");
        folder.mkdirs();
        // Link files
        for (Plugin p : selected) {
            Console.log(Type.INFO, "Linking " + p.ref);
            File link = new File(new File("plugins"), p.ref + ".jar");
            File original = new File(new File(path), p.ref + ".jar");
            if (link.exists()) {
                if (!Console.log(Type.INFO, Style.WARN, " skipped (already installed)\n")) {
                    Console.log(Type.WARN, "Linking " + p.ref + " skipped (already installed)\n");
                }
                continue;
            }
            try {
                Files.createSymbolicLink(link.toPath(), original.toPath());
                Console.log(Type.INFO, Style.DONE, " done\n");
            } catch (IOException e) {
                if (!Console.log(Type.INFO, Style.ERR, " failed\n")) {
                    Console.log(Type.ERR, "Linking " + p.ref + " failed\n");
                }
            }
        }
        Console.sep();
    }
}
