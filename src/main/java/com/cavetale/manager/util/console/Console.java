package com.cavetale.manager.util.console;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public final class Console {
    public static Detail detail = Detail.DEFAULT;

    public static boolean logs(@NotNull Detailed detailed) {
        return Console.detail.value >= detailed.detail().value;
    }

    public static boolean log(@NotNull Styled styled) {
        if (logs(detail)) {
            System.out.print(styled);
            return true;
        }
        return false;
    }

    public static boolean log(@NotNull Detailed detailed, @NotNull XCoded xcoded, @NotNull String s) {
        if (logs(detailed)) {
            System.out.print(xcoded + s + XCode.RESET);
            return true;
        }
        return false;
    }

    public static boolean log(@NotNull Styled styled, @NotNull String s) {
        return log(styled, styled, s);
    }

    public static boolean log(@NotNull String s) {
        return log(Style.OVERRIDE, s);
    }

    public static boolean logF(@NotNull Styled styled, @NotNull String format, @NotNull String... contents) {
        if (logs(detail)) {
            System.out.printf(styled + format + XCode.RESET, (Object[]) contents);
            return true;
        }
        return false;
    }

    public static String[] in() {
        log(Detail.OVERRIDE, Style.PROMPT, "> ");
        log(Style.INPUT);
        String[] args = System.console().readLine().split(" ");
        log("\n");
        return args;
    }

    public static String in(@NotNull String s) {
        log(Style.PROMPT + s + " " + Style.INPUT);
        String arg = System.console().readLine();
        log("\n");
        return arg;
    }

    public static <T extends Comparable<? super T>> void list(
            @Nullable String header, Collection<T> objects,
            @NotNull Detailed verbosity, @NotNull XCoded style, int cols, int colSize
    ) {
        if (!logs(verbosity.detail())) {
            return;
        }
        LinkedList<T> list = new LinkedList<>(objects);
        Collections.sort(list);
        if (header != null) {
            log(verbosity, style, XCode.BOLD + header + "\n");
        }
        log(verbosity, style, "-".repeat(cols * colSize + cols - 1) + "\n");
        int i = 0;
        for (T o : list) {
            log(verbosity, style, o.toString() + " ".repeat(Math.max(colSize - o.toString().length(), 0)));
            if (i < cols - 1) {
                log(" ");
                i++;
            } else {
                log("\n");
                i = 0;
            }
        }
        log("\n\n");
    }
}
