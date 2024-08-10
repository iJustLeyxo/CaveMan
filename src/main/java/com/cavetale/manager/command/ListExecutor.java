package com.cavetale.manager.command;

import com.cavetale.manager.data.Plugin;
import com.cavetale.manager.data.PluginCategory;
import com.cavetale.manager.data.ServerConfig;
import com.cavetale.manager.data.ServerSoftware;
import com.cavetale.manager.parser.Flag;
import com.cavetale.manager.parser.Result;
import com.cavetale.manager.util.cmd.Cmd;
import com.cavetale.manager.util.cmd.EscCode;
import com.cavetale.manager.util.cmd.Style;
import com.cavetale.manager.util.cmd.Verbosity;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

public final  class ListExecutor extends Executor {
    public ListExecutor(@NotNull Result result) {
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
            Set<Plugin> selected = this.result.plugins().get(null, true, null);
            if (!selected.isEmpty()) {
                Cmd.list(selected.size() + " plugins(s) selected",
                        selected, Verbosity.OVERRIDE, EscCode.BLUE, 4, 21);
            } else {
                Cmd.out(Style.WARN, "No plugins selected\n\n");
            }
        }
    }

    private static void plugins() {
        Cmd.list("Plugins", List.of(Plugin.values()), Verbosity.OVERRIDE, EscCode.BLUE, 4, 21);
    }

    private static void categories() {
        Cmd.list("Categories", List.of(PluginCategory.values()), Verbosity.OVERRIDE, EscCode.BLUE, 4, 21);
    }

    private static void servers() {
        Cmd.list("Servers", List.of(ServerConfig.values()), Verbosity.OVERRIDE, EscCode.BLUE, 4, 21);
    }

    private static void serverSoftware() {
        Cmd.list("Server software", List.of(ServerSoftware.values()), Verbosity.OVERRIDE, EscCode.BLUE, 4, 21);
    }

    @Override
    public void help() {

    }
}
