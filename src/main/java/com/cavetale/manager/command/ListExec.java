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
import com.cavetale.manager.util.console.XCode;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public final  class ListExec extends Exec {
    public ListExec(@NotNull Result result) {
        super(result);
    }

    @Override
    public void run() {
        if (this.result.tokens().flags().containsKey(Flag.ALL)) { // List everything
            boolean everything = true;
            if (this.result.tokens().flags().containsKey(Flag.PLUGIN)) {
                plugins();
                everything = false;
            }
            if (this.result.tokens().flags().containsKey(Flag.CATEGORY)) {
                categories();
                everything = false;
            }
            if (this.result.tokens().flags().containsKey(Flag.SERVER)) {
                servers();
                everything = false;
            }
            if (this.result.tokens().flags().containsKey(Flag.SOFTWARE)) {
                software();
                everything = false;
            }
            if (everything) {
                plugins();
                categories();
                servers();
                software();
            }
        } else { // List respective category if no element of said category is selected
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
                software();
                resolve = false;
            }
            if (!resolve) {
                return;
            }
            // List selected elements of category if any are specified
            Set<Plugin> selected = this.result.pluginManager().get(null, true, null);
            if (!selected.isEmpty()) {
                Console.logL(Type.REQUESTED, Style.PLUGIN, selected.size() +
                        " plugin(s) selected", 4, 21, selected.toArray());
            } else {
                Console.log(Type.WARN, "No plugins selected\n");
            }
        }
    }

    /**
     * List all plugins
     */
    private static void plugins() {
        Console.sep();
        Console.logL(Type.REQUESTED, Style.PLUGIN, "Plugins", 4, 21, (Object[]) Plugin.values());
    }

    /**
     * List all categories
     */
    private static void categories() {
        Console.sep();
        Console.log(Type.REQUESTED, Style.CATEGORY, XCode.BOLD +
                "-------------------------------------- " +
                "Categories -------------------------------------\n");
        Console.logF(Type.REQUESTED, Style.CATEGORY, "%-16s | %-68s\n", "Category", "Info");
        Console.log(Type.REQUESTED, Style.CATEGORY, "--------------------------------------------" +
                "-------------------------------------------\n");
        ArrayList<Category> categories = new ArrayList<>(List.of(Category.values()));
        Collections.sort(categories);
        for (Category c : categories) {
            Console.logF(Type.REQUESTED, Style.CATEGORY, "%-16s | %-68s\n", c.ref, c.info);
        }
    }

    /**
     * list all servers
     */
    private static void servers() {
        Console.sep();
        Console.log(Type.REQUESTED, Style.SERVER, XCode.BOLD +
                "--------------------------------------- " +
                "Servers ---------------------------------------\n");
        Console.logF(Type.REQUESTED, Style.SERVER, "%-16s | %-68s\n", "Server", "Info");
        Console.log(Type.REQUESTED, Style.SERVER, "----------------------------------------------" +
                "-----------------------------------------\n");
        ArrayList<Server> servers = new ArrayList<>(List.of(Server.values()));
        Collections.sort(servers);
        for (Server s : servers) {
            Console.logF(Type.REQUESTED, Style.SERVER, "%-16s | %-68s\n", s.ref, s.info);
        }
    }

    /**
     * List all server software
     */
    private static void software() {
        Console.sep();
        Console.logL(Type.REQUESTED, Style.SOFTWARE, "Server software", 4, 21,
                (Object[]) Software.values());
    }
}
