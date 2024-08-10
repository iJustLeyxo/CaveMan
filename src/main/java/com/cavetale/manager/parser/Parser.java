package com.cavetale.manager.parser;

import com.cavetale.manager.data.plugin.PluginManager;
import com.cavetale.manager.data.server.SoftwareManager;
import com.cavetale.manager.parser.container.EmptyContainer;
import com.cavetale.manager.util.console.Console;
import com.cavetale.manager.util.console.Style;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public final class Parser {
    public static @NotNull Result parse(String[] args) throws InputException {
        Console.out(Style.LOG, "Parsing input\n");
        Set<Command> commands = new LinkedHashSet<>();
        Map<Flag, EmptyContainer> flags = new HashMap<>();
        Flag flag = null;
        for (String arg : args) {
            if (arg.charAt(0) == '-') {
                flag = null;
                if (arg.length() > 2 && arg.charAt(1) == '-') {
                    flag = Flag.get(arg.substring(2));
                    if (!flags.containsKey(flag)) {
                        flags.put(flag, flag.container());
                    }
                } else if (arg.length() > 1) {
                    for (int i = 1; i < arg.length(); i++) {
                        flag = Flag.get(arg.charAt(i));
                        if (!flags.containsKey(flag)) {
                            flags.put(flag, flag.container());
                        }
                    }
                }
            } else {
                if (flag != null) {
                    if (!flags.get(flag).option(arg)) {
                        System.out.println(arg);
                        flag = null;
                    }
                }
                if (flag == null) {
                    Command cmd = Command.get(arg);
                    if (commands.contains(cmd)) {
                        Console.out(Style.INFO, "Ignoring duplicate command \"" + arg + "\n");
                    }
                    commands.add(cmd);
                }
            }
        }
        Tokens tokens = new Tokens(commands, flags);
        Console.out(Style.LOG, Style.DONE, "Finished parsing\n\n");
        return new Result(tokens, new PluginManager(tokens), new SoftwareManager(tokens));
    }
}
