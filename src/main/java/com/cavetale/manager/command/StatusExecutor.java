package com.cavetale.manager.command;

import com.cavetale.manager.data.Plugin;
import com.cavetale.manager.parser.Result;
import com.cavetale.manager.util.cmd.Cmd;
import com.cavetale.manager.util.cmd.EscCode;
import com.cavetale.manager.util.cmd.Style;
import com.cavetale.manager.util.cmd.Verbosity;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public final class StatusExecutor extends Executor {
    public StatusExecutor(@NotNull Result result) {
        super(result);
    }

    @Override
    public void run() {
        Set<Plugin> selected = this.result.plugins().get(null, true, null);
        if (!selected.isEmpty()) {
            Cmd.list(selected.size() + " plugins(s) selected",
                    selected, Style.INFO, EscCode.BLUE, 4, 21);
            Set<Plugin> plugins = this.result.plugins().get(true, true, false);
            if (!plugins.isEmpty()) {
                Cmd.list(plugins.size() + " plugins(s) installed (not linked)",
                        plugins, Verbosity.OVERRIDE, EscCode.GREEN, 4, 21);
            }
            plugins = this.result.plugins().get(true, true, true);
            if (!plugins.isEmpty()) {
                Cmd.list(plugins.size() + " plugins(s) installed (linked)",
                        plugins, Verbosity.OVERRIDE, EscCode.MAGENTA, 4, 21);
            }
            plugins = this.result.plugins().get(true, false, null);
            if (!plugins.isEmpty()) {
                Cmd.list(plugins.size() + " plugins(s) superfluous",
                        plugins, Verbosity.OVERRIDE, EscCode.YELLOW, 4, 21);
            }
            plugins = this.result.plugins().get(false, true, null);
            if (!plugins.isEmpty()) {
                Cmd.list(plugins.size() + " plugins(s) missing",
                        plugins, Verbosity.OVERRIDE, EscCode.RED,4, 21);
            }
        } else {
            Set<Plugin> unlinked = this.result.plugins().get(true, null, false);
            Set<Plugin> linked = this.result.plugins().get(true, null, true);
            if (!unlinked.isEmpty() || !linked.isEmpty()) {
                if (!unlinked.isEmpty()) {
                    Cmd.list(unlinked.size() + " plugins(s) installed (not linked)",
                            unlinked, Verbosity.OVERRIDE, EscCode.GREEN, 4, 21);
                }
                if (!linked.isEmpty()) {
                    Cmd.list(linked.size() + " plugins(s) installed (linked)",
                            linked, Verbosity.OVERRIDE, EscCode.MAGENTA, 4, 21);
                }
            }
        }
        Set<String> unknown = this.result.plugins().unknown();
        if (!unknown.isEmpty()) {
            Cmd.list(unknown.size() + " plugins(s) unknown",
                    unknown, Verbosity.OVERRIDE, EscCode.GRAY, 4, 21);
        }
    }

    @Override
    public void help() {

    }
}
