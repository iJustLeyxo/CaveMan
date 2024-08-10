package com.cavetale.manager.command;

import com.cavetale.manager.parser.Command;
import com.cavetale.manager.parser.Flag;
import com.cavetale.manager.parser.Result;
import com.cavetale.manager.util.console.Console;
import com.cavetale.manager.util.console.XCode;
import com.cavetale.manager.util.console.Style;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class HelpExec extends Exec {
    public HelpExec(@NotNull Result result) {
        super(result);
    }

    @Override
    public void run() {
        Console.out(Style.HELP, XCode.BOLD +
                "-------------------------------------- Help --------------------------------------\n" +
                XCode.WEIGHT_OFF + "Interactive: java -jar Manager.jar\n" +
                "Single: java -jar Manager.jar <1+command(s)> <0+flag(s)>\n" + XCode.BOLD +
                "\n------------------------------------ Commands ------------------------------------\n");
        Console.outF(Style.HELP, "%2s %-13s | %-32s | %-33s%n",
                "", "Command", "Info", "");
        Console.out(Style.HELP,
                "----------------------------------------------------------------------------------\n");
        ArrayList<Command> commands = new ArrayList<>(List.of(Command.values()));
        Collections.sort(commands);
        for (Command c : commands) {
            Console.outF(Style.HELP, "%2s %-13s | %-32s | %-33s%n",
                    "", c.refs[0], c.info, Objects.requireNonNullElse(c.usage, ""));
        }
        Console.out(Style.HELP,  XCode.BOLD +
                "\n------------------------------------- Flags --------------------------------------\n");
        Console.outF(Style.HELP, "%2s %-13s | %-32s | %-33s%n",
                "", "Flag", "Info", "Usage");
        Console.out(Style.HELP,
                "----------------------------------------------------------------------------------\n");
        ArrayList<Flag> flags = new ArrayList<>(List.of(Flag.values()));
        Collections.sort(flags);
        for (Flag f : flags) {
            Console.outF(Style.HELP, "%2s %-13s | %-32s | %-33s%n",
                   "-" + f.shortRef, "--" + f.longRef, f.info,
                    Objects.requireNonNullElse(f.usage, ""));
        }
        Console.out(Style.HELP, "\n");
    }
}
