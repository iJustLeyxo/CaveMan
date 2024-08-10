package com.cavetale.manager.command;

import com.cavetale.manager.data.Plugin;
import com.cavetale.manager.parser.Flag;
import com.cavetale.manager.parser.Result;
import com.cavetale.manager.parser.container.PathContainer;
import com.cavetale.manager.util.cmd.Cmd;
import com.cavetale.manager.util.cmd.EscCode;
import com.cavetale.manager.util.cmd.Style;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Set;

public final class LinkExecutor extends Executor {
    public LinkExecutor(@NotNull Result result) {
        super(result);
    }

    @Override
    public void run() {
        if (!this.result.tokens().flags().containsKey(Flag.PATH) ||
                this.result.tokens().flags().get(Flag.PATH).isEmpty()) {
            Cmd.out(Style.WARN, "No path specified for linking\n\n");
            return;
        }
        String path = ((PathContainer) this.result.tokens().flags().get(Flag.PATH)).get();
        Set<Plugin> selected = this.result.plugins().get(null, true, null);
        if (selected.isEmpty()) {
            Cmd.out(Style.WARN, "No plugins selected for linking\n\n");
            return;
        }
        Cmd.list(selected.size() + " plugins(s) selected for linking",
                selected, Style.WARN, EscCode.BLUE, 4, 21);
        if (!this.result.tokens().flags().containsKey(Flag.FORCE)) {
            if (!Cmd.in("Proceed with linking (Y/n)?").equalsIgnoreCase("y")) {
                return;
            }
        }
        Cmd.out(Style.LOG, "Creating plugins directory\n");
        File folder = new File("plugins/");
        folder.mkdirs();
        for (Plugin p : selected) {
            Cmd.out(Style.INFO, "Linking " + p.name);
            File link = new File(new File("plugins"), p.name + ".jar");
            File original = new File(new File(path), p.name + ".jar");
            if (link.exists()) {
                if (!Cmd.out(Style.INFO, Style.WARN, " skipped (already installed)\n")) {
                    Cmd.out(Style.WARN, "Linking " + p.name + " skipped (already installed)\n");
                }
                continue;
            }
            try {
                Files.createSymbolicLink(link.toPath(), original.toPath());
                Cmd.out(Style.DONE, " done\n");
            } catch (IOException e) {
                if (!Cmd.out(Style.INFO, Style.ERR, " failed\n")) {
                    Cmd.out(Style.ERR, "Linking " + p.name + " failed\n");
                }
            }
        }
        Cmd.out("\n");
    }

    @Override
    public void help() {

    }
}
