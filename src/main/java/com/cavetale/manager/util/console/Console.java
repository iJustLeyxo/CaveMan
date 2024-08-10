package com.cavetale.manager.util.console;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public final class Console {
    public static Verbosity verbosity = Verbosity.DEFAULT;

    public static boolean outs(@NotNull Verbosity verbosity) {
        return Console.verbosity.value >= verbosity.value;
    }

    public static boolean outs(@NotNull VerbosityProvider verbosity) {
        return outs(verbosity.verbosity());
    }

    public static boolean out(@NotNull VerbosityProvider verbosity, @NotNull XCodeProvider style) {
        if (outs(verbosity)) {
            System.out.print(style);
            return true;
        }
        return false;
    }

    public static boolean out(@NotNull VerbosityProvider verbosity, @NotNull XCodeProvider style, @NotNull String s) {
        if (outs(verbosity)) {
            System.out.print(style + s + XCode.RESET);
            return true;
        }
        return false;
    }

    public static boolean out(@NotNull Style style, @NotNull String s) {
        return out(style, style, s);
    }

    public static boolean out(@NotNull String s) {
        return out(Style.OVERRIDE, s);
    }

    public static boolean outF(@NotNull VerbosityProvider style, @NotNull XCodeProvider mod,
                               @NotNull String format, @NotNull String... strings) {
        if (outs(style)) {
            System.out.printf(style + mod.toString() + format + XCode.RESET, (Object[]) strings);
            return true;
        }
        return false;
    }

    public static boolean outF(@NotNull Style style, @NotNull String format, @NotNull String... strings) {
        return outF(style, style, format, strings);
    }

    public static String[] in() {
        out(Verbosity.OVERRIDE, Style.PROMPT, "> ");
        out(Verbosity.OVERRIDE, Style.INPUT);
        String[] args = System.console().readLine().split(" ");
        out("\n");
        return args;
    }

    public static String in(@NotNull String s) {
        out(Style.PROMPT + s + " ");
        String arg = System.console().readLine();
        out("\n");
        return arg;
    }

    public static <T extends Comparable<? super T>> void list(
            @Nullable String header, Collection<T> objects,
            @NotNull VerbosityProvider verbosity, @NotNull XCodeProvider style, int cols, int colSize
    ) {
        if (!outs(verbosity.verbosity())) {
            return;
        }
        LinkedList<T> list = new LinkedList<>(objects);
        Collections.sort(list);
        if (header != null) {
            out(verbosity, style, XCode.BOLD + header + "\n");
        }
        out(verbosity, style, "-".repeat(cols * colSize + cols - 1) + "\n");
        int i = 0;
        for (T o : list) {
            out(verbosity, style, o.toString() + " ".repeat(Math.max(colSize - o.toString().length(), 0)));
            if (i < cols - 1) {
                out(" ");
                i++;
            } else {
                out("\n");
                i = 0;
            }
        }
        out("\n\n");
    }
}
