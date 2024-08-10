package com.cavetale.manager.parser;

import com.cavetale.manager.data.Plugins;
import com.cavetale.manager.data.ServerSoftwares;
import com.cavetale.manager.parser.container.Container;
import com.cavetale.manager.util.cmd.Cmd;
import com.cavetale.manager.util.cmd.Style;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public final class Parser {
    public static @NotNull Result parse(String[] args) throws InputException {
        Set<Command> commands = new LinkedHashSet<>();
        Map<Flag, Container> flags = new HashMap<>();
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
                        Cmd.out(Style.INFO, "Ignoring duplicate command \"" + arg + "\n");
                    }
                    commands.add(cmd);
                }
            }
        }
        Tokens tokens = new Tokens(commands, flags);
        return new Result(tokens, new Plugins(tokens), new ServerSoftwares(tokens));
    }
}
