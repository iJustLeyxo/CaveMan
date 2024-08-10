package com.cavetale.manager.command;

import com.cavetale.manager.data.plugin.Category;
import com.cavetale.manager.data.plugin.Plugin;
import com.cavetale.manager.data.server.Software;
import com.cavetale.manager.parser.Flag;
import com.cavetale.manager.parser.Result;
import com.cavetale.manager.util.console.Console;
import com.cavetale.manager.util.console.EscCode;
import com.cavetale.manager.util.console.Style;
import com.cavetale.manager.util.console.Verbosity;
import org.jetbrains.annotations.NotNull;

import java.util.List;
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
                Console.list(selected.size() + " plugins(s) selected",
                        selected, Verbosity.OVERRIDE, EscCode.BLUE, 4, 21);
            } else {
                Console.out(Style.WARN, "No plugins selected\n\n");
            }
        }
    }

    private static void plugins() {
        Console.list("Plugins", List.of(Plugin.values()), Verbosity.OVERRIDE, EscCode.BLUE, 4, 21);
    }

    private static void categories() {
        Console.list("Categories", List.of(Category.values()), Verbosity.OVERRIDE, EscCode.BLUE, 4, 21);
    }

    private static void servers() {
        Console.list("Servers", List.of(com.cavetale.manager.data.plugin.Server.values()), Verbosity.OVERRIDE, EscCode.BLUE, 4, 21);
    }

    private static void serverSoftware() {
        Console.list("Server software", List.of(Software.values()), Verbosity.OVERRIDE, EscCode.BLUE, 4, 21);
    }
}
