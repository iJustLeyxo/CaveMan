package com.cavetale.manager.command;

import com.cavetale.manager.data.plugin.Category;
import com.cavetale.manager.data.plugin.Plugin;
import com.cavetale.manager.data.plugin.Server;
import com.cavetale.manager.data.server.Software;
import com.cavetale.manager.parser.Flag;
import com.cavetale.manager.parser.Result;
import com.cavetale.manager.util.console.Console;
import com.cavetale.manager.util.console.Style;
import com.cavetale.manager.util.console.Type;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public final  class ListExec extends Exec {
    public ListExec(@NotNull Result result) {
        super(result);
    }

    @Override
    public void run() {
        if (this.result.tokens().flags().containsKey(Flag.ALL)) {
            if (this.result.tokens().flags().containsKey(Flag.PLUGIN)) { plugins(); }
            if (this.result.tokens().flags().containsKey(Flag.CATEGORY)) { categories(); }
            if (this.result.tokens().flags().containsKey(Flag.SERVER)) { servers(); }
            if (this.result.tokens().flags().containsKey(Flag.SOFTWARE)) { serverSoftware(); }
        } else {
            boolean resolve = true;
            if (this.result.tokens().flags().containsKey(Flag.PLUGIN) &&
                    this.result.tokens().flags().get(Flag.PLUGIN).isEmpty()) {
                plugins();
                resolve = false;
            }
            if (this.result.tokens().flags().containsKey(Flag.CATEGORY) &&
                    this.result.tokens().flags().get(Flag.CATEGORY).isEmpty()) {
                categories();
                resolve = false;
            }
            if (this.result.tokens().flags().containsKey(Flag.SERVER) &&
                    this.result.tokens().flags().get(Flag.SERVER).isEmpty()) {
                servers();
                resolve = false;
            }
            if (this.result.tokens().flags().containsKey(Flag.SOFTWARE) &&
                    this.result.tokens().flags().get(Flag.SOFTWARE).isEmpty()) {
                serverSoftware();
                resolve = false;
            }
            if (!resolve) {
                return;
            }
            Set<Plugin> selected = this.result.pluginManager().get(null, true, null);
            if (!selected.isEmpty()) {
                Console.logL(Type.REQUESTED, Style.PLUGIN, selected.size() + " plugin(s) selected",
                        4, 21, selected.toArray());
            } else {
                Console.log(Type.WARN, "No plugins selected\n");
            }
        }
    }

    private static void plugins() {
        Console.logL(Type.REQUESTED, Style.PLUGIN, "Plugins",
                4, 21, (Object[]) Plugin.values());
    }

    private static void categories() {
        Console.logL(Type.REQUESTED, Style.CATEGORY, "Categories",
                4, 21, (Object[]) Category.values());
    }

    private static void servers() {
        Console.logL(Type.REQUESTED, Style.SERVER, "Servers" ,
                4, 21, (Object[]) Server.values());
    }

    private static void serverSoftware() {
        Console.logL(Type.REQUESTED, Style.SOFTWARE, "Server software",
                4, 21, (Object[]) Software.values());
    }
}
