package com.cavetale.manager;

import com.cavetale.manager.command.Command;
import com.cavetale.manager.parser.Flag;
import com.cavetale.manager.parser.InputException;
import com.cavetale.manager.parser.Parser;
import com.cavetale.manager.parser.Result;
import com.cavetale.manager.util.console.Console;
import com.cavetale.manager.util.console.Style;
import com.cavetale.manager.util.console.Type;
import com.cavetale.manager.util.console.XCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Cavetale installation manager, used to manage plugins and server software for testing servers
 */
public final class Manager {
    public static boolean interactive = true;

    public static void main(String[] args) {
        System.out.println();

        while (interactive) { // Cycle of inputs respective command executions
            if (args != null && args.length > 0) {
                interactive = false;
            } else {
                args = Console.in();
            }
            try {
                Result result = Parser.parse(args);
                boolean changed = result.tokens().analyse();
                if (result.tokens().commands().isEmpty()) {
                    if (!changed) {
                        Console.log(Type.WARN, "Nothing to do. Try typing \"help\".\n");
                    }
                } else {
                    Console.log(Type.DEBUG, "Running " + result.tokens().commands().size() + " commands\n");
                    for (Command cmd : result.tokens().commands()) {
                        cmd.run(result);
                    }
                    Console.log(Type.DEBUG, "Finished running commands\n");
                }
            } catch (InputException e) {
                Console.log(Type.ERR, e.getMessage() + "\n");
                if (!interactive) {
                    System.exit(1);
                }
            }
            args = null;
        }
        Manager.exit();
    }

    public static void exit() {
        Console.log(Type.DEBUG, "Exiting\n");
        Console.sep();
        System.exit(0);
    }

    public static void help() { // TODO: Update help logic
        Console.log(Type.REQUESTED, Style.HELP, XCode.BOLD +
                "---------------------------------------- " +
                "Help -----------------------------------------\n" + XCode.WEIGHT_OFF +
                "Interactive: java -jar Manager.jar\n" +
                "Single: java -jar Manager.jar <1+command(s)> <0+flag(s)>\n\n" + XCode.BOLD +
                "-------------------------------------- " +
                "Commands ---------------------------------------\n");
        Console.logF(Type.REQUESTED, Style.HELP, "%-16s | %-68s\n", "Command", "Info");
        Console.log(Type.REQUESTED, Style.HELP, "------------------------------------------------" +
                "---------------------------------------\n");
        ArrayList<Command> commands = new ArrayList<>(List.of(Command.values()));
        Collections.sort(commands);
        for (Command c : commands) {
            Console.logF(Type.REQUESTED, Style.HELP, "%-16s | %-68s\n",
                    c.refs[0], c.info);
        }
        Console.log(Type.REQUESTED, Style.HELP, XCode.BOLD +
                "\n--------------------------------------- " +
                "Flags -----------------------------------------\n");
        Console.logF(Type.REQUESTED, Style.HELP, "%-16s | %-32s | %-33s\n", "Flag", "Info", "Usage");
        Console.log(Type.REQUESTED, Style.HELP, "------------------------------------------------" +
                "---------------------------------------\n");
        ArrayList<Flag> flags = new ArrayList<>(List.of(Flag.values()));
        Collections.sort(flags);
        for (Flag f : flags) {
            Console.logF(Type.REQUESTED, Style.HELP, "%2s %-13s | %-32s | %-33s\n", "-" + f.shortRef,
                    "--" + f.longRef, f.info, Objects.requireNonNullElse(f.usage, ""));
        }
    }
}