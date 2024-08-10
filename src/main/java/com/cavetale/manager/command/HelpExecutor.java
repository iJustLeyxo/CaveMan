package com.cavetale.manager.command;

import com.cavetale.manager.parser.Command;
import com.cavetale.manager.parser.Flag;
import com.cavetale.manager.parser.Result;
import com.cavetale.manager.util.cmd.Cmd;
import com.cavetale.manager.util.cmd.EscCode;
import com.cavetale.manager.util.cmd.Style;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class HelpExecutor extends Executor {
    public HelpExecutor(@NotNull Result result) {
        super(result);
    }

    @Override
    public void run() {
        Cmd.out(Style.HELP, EscCode.BOLD +
                "-------------------------------------- Help --------------------------------------\n" +
                EscCode.WEIGHT_OFF + """
                Interactive: java -jar Manager.jar
                Single: java -jar Manager.jar <1+command(s)> <0+flag(s)>
                ------------------------------------ Commands ------------------------------------
                """);
        Cmd.outF(Style.HELP, EscCode.BOLD, "%2s %-13s | %-30s | %-30s%n",
                "", "Command", "Info", "");
        Cmd.out(Style.HELP, "----------------------------------------------------------------------------------\n");
        ArrayList<Command> commands = new ArrayList<>(List.of(Command.values()));
        Collections.sort(commands);
        for (Command c : commands) {
            Cmd.outF(Style.HELP, "%2s %-13s | %-30s | %-30s%n",
                    "", c.ref, c.description, "");
        }
        Cmd.out(Style.HELP, "------------------------------------- Flags --------------------------------------\n");
        Cmd.outF(Style.HELP, EscCode.BOLD + "%2s %-13s | %-30s | %-30s%n",
                "", "Flag", "Info", "Usage");
        Cmd.out(Style.HELP, "----------------------------------------------------------------------------------\n");
        ArrayList<Flag> flags = new ArrayList<>(List.of(Flag.values()));
        Collections.sort(flags);
        for (Flag f : flags) {
            Cmd.outF(Style.HELP, "%2s %-13s | %-30s | %-30s%n",
                   "-" + f.shortRef, "--" + f.longRef, f.info,
                    Objects.requireNonNullElse(f.usage, ""));
        }
        Cmd.out(Style.HELP, "\n");
    }

    @Override
    public void help() {

    }
}
