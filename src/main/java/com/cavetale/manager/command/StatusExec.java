package com.cavetale.manager.command;

import com.cavetale.manager.data.plugin.Plugin;
import com.cavetale.manager.parser.Result;
import com.cavetale.manager.util.console.Console;
import com.cavetale.manager.util.console.Detail;
import com.cavetale.manager.util.console.XCode;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public final class StatusExec extends Exec {
    public StatusExec(@NotNull Result result) {
        super(result);
    }

    @Override
    public void run() {
        Set<Plugin> selected = this.result.pluginManager().get(null, true, null);
        if (!selected.isEmpty()) {
            Console.list(selected.size() + " plugins(s) selected",
                    selected, Detail.OVERRIDE, XCode.BLUE, 4, 21);
            Set<Plugin> plugins = this.result.pluginManager().get(true, true, false);
            if (!plugins.isEmpty()) {
                Console.list(plugins.size() + " plugins(s) installed (not linked)",
                        plugins, Detail.OVERRIDE, XCode.GREEN, 4, 21);
            }
            plugins = this.result.pluginManager().get(true, true, true);
            if (!plugins.isEmpty()) {
                Console.list(plugins.size() + " plugins(s) installed (linked)",
                        plugins, Detail.OVERRIDE, XCode.MAGENTA, 4, 21);
            }
            plugins = this.result.pluginManager().get(true, false, null);
            if (!plugins.isEmpty()) {
                Console.list(plugins.size() + " plugins(s) superfluous",
                        plugins, Detail.OVERRIDE, XCode.YELLOW, 4, 21);
            }
            plugins = this.result.pluginManager().get(false, true, null);
            if (!plugins.isEmpty()) {
                Console.list(plugins.size() + " plugins(s) missing",
                        plugins, Detail.OVERRIDE, XCode.RED,4, 21);
            }
        } else {
            Set<Plugin> unlinked = this.result.pluginManager().get(true, null, false);
            Set<Plugin> linked = this.result.pluginManager().get(true, null, true);
            if (!unlinked.isEmpty() || !linked.isEmpty()) {
                if (!unlinked.isEmpty()) {
                    Console.list(unlinked.size() + " plugins(s) installed (not linked)",
                            unlinked, Detail.OVERRIDE, XCode.GREEN, 4, 21);
                }
                if (!linked.isEmpty()) {
                    Console.list(linked.size() + " plugins(s) installed (linked)",
                            linked, Detail.OVERRIDE, XCode.MAGENTA, 4, 21);
                }
            }
        }
        Set<String> unknown = this.result.pluginManager().unknown();
        if (!unknown.isEmpty()) {
            Console.list(unknown.size() + " plugins(s) unknown",
                    unknown, Detail.OVERRIDE, XCode.GRAY, 4, 21);
        }
    }
}
