package com.cavetale.manager.command;

import com.cavetale.manager.data.plugin.Plugin;
import com.cavetale.manager.data.server.Software;
import com.cavetale.manager.parser.Result;
import com.cavetale.manager.util.console.Console;
import com.cavetale.manager.util.console.Style;
import com.cavetale.manager.util.console.Type;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public final class StatusExec extends Exec {
    public StatusExec(@NotNull Result result) {
        super(result);
    }

    @Override
    public void run() {
        Set<Plugin> plugins = this.result.pluginManager().get(null, true, null);
        Set<Software> softwares = this.result.softwareManager().get(null, true);
        if (!plugins.isEmpty() || !softwares.isEmpty()) {
            if (!plugins.isEmpty()) {
                Console.sep();
                Console.logL(Type.REQUESTED, Style.SELECT, plugins.size() + " plugins(s) selected",
                        4, 21, plugins.toArray());
                plugins = this.result.pluginManager().get(true, true, false);
                if (!plugins.isEmpty()) {
                    Console.sep();
                    Console.logL(Type.REQUESTED, Style.INSTALL, plugins.size() + " plugins(s) installed (not linked)",
                            4, 21, plugins.toArray());
                }
                plugins = this.result.pluginManager().get(true, true, true);
                if (!plugins.isEmpty()) {
                    Console.sep();
                    Console.logL(Type.REQUESTED, Style.LINK, plugins.size() + " plugins(s) installed (linked)",
                            4, 21, plugins.toArray());
                }
                plugins = this.result.pluginManager().get(true, false, null);
                if (!plugins.isEmpty()) {
                    Console.sep();
                    Console.logL(Type.REQUESTED, Style.SUPERFLUOUS, plugins.size() + " plugins(s) superfluous",
                            4, 21, plugins.toArray());
                }
                plugins = this.result.pluginManager().get(false, true, null);
                if (!plugins.isEmpty()) {
                    Console.sep();
                    Console.logL(Type.REQUESTED, Style.MISSING, plugins.size() + " plugins(s) missing",
                            4, 21, plugins.toArray());
                }
            }
            if (!softwares.isEmpty()) {
                Console.sep();
                Console.logL(Type.REQUESTED, Style.SELECT, softwares.size() + " software(s) selected",
                        4, 21, softwares.toArray());
                softwares = this.result.softwareManager().get(true, true);
                if (!softwares.isEmpty()) {
                    Console.sep();
                    Console.logL(Type.REQUESTED, Style.INSTALL, softwares.size() + " software(s) installed",
                            4, 21, softwares.toArray());
                }
                softwares = this.result.softwareManager().get(true, false);
                if (!softwares.isEmpty()) {
                    Console.sep();
                    Console.logL(Type.REQUESTED, Style.SUPERFLUOUS, softwares.size() + " software(s) superfluous",
                            4, 21, softwares.toArray());
                }
                softwares = this.result.softwareManager().get(false, true);
                if (!softwares.isEmpty()) {
                    Console.sep();
                    Console.logL(Type.REQUESTED, Style.MISSING, softwares.size() + " software(s) missing",
                            4, 21, softwares.toArray());
                }
            }
        } else {
            Set<Plugin> unlinked = this.result.pluginManager().get(true, null, false);
            Set<Plugin> linked = this.result.pluginManager().get(true, null, true);
            Set<Software> installed = this.result.softwareManager().get(true, null);
            if (!unlinked.isEmpty() || !linked.isEmpty() || !installed.isEmpty()) {
                if (!unlinked.isEmpty()) {
                    Console.sep();
                    Console.logL(Type.REQUESTED, Style.INSTALL, unlinked.size() + " plugins(s) installed (not linked)",
                            4, 21, unlinked.toArray());
                }
                if (!linked.isEmpty()) {
                    Console.sep();
                    Console.logL(Type.REQUESTED, Style.LINK, linked.size() + " plugins(s) installed (linked)",
                            4, 21, linked.toArray());
                }
                if (!installed.isEmpty()) {
                    Console.sep();
                    Console.logL(Type.REQUESTED, Style.SOFTWARE, installed.size() + " softwares(s) installed",
                            4, 21, linked.toArray());
                }
            } else {
                Console.log(Type.REQUESTED, Style.INFO, "Nothing installed\n");
            }
        }
        Set<String> unknown = this.result.pluginManager().unknown();
        if (!unknown.isEmpty()) {
            Console.sep();
            Console.logL(Type.REQUESTED, Style.UNKNOWN, unknown.size() + " plugins(s) unknown",
                    4, 21, unknown.toArray());
        }
        unknown = this.result.softwareManager().unknown();
        if (!unknown.isEmpty()) {
            Console.sep();
            Console.logL(Type.REQUESTED, Style.UNKNOWN, unknown.size() + " software(s) unknown",
                    4, 21, unknown.toArray());
        }
    }
}
