package com.cavetale.manager.util.console;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public final class Console {
    public static @NotNull Detail detail = Detail.STD;
    private static @Nullable Type type = null;
    private static boolean empty = true;

    public static boolean log(@NotNull Type type, @NotNull Style style, @NotNull String msg) {
        if (!Console.logs(type.detail)) {
            return false;
        }
        Console.sep(type);
        System.out.print(XCode.RESET + style.toString() + msg);
        Console.empty = false;
        return true;
    }

    public static boolean logF(@NotNull Type type, @NotNull String format, @NotNull String... params) {
        if (!Console.logs(type.detail)) {
            return false;
        }
        Console.sep(type);
        System.out.printf(XCode.RESET + type.style.toString() + format, (Object[]) params);
        Console.empty = false;
        return true;
    }

    public static boolean logL(
            @NotNull Type type, @NotNull Style style, @NotNull String header,
            int cols, int colSize, @NotNull Object... objects) {
        if (!Console.logs(type.detail)) {
            return false;
        }
        StringBuilder b = new StringBuilder(XCode.BOLD + header + "\n" +
                "-".repeat(cols * colSize + cols - 1) + "\n" + XCode.WEIGHT_OFF);
        Arrays.sort(objects);
        int i = 1;
        for (Object o : objects) {
            b.append(o).append(" ".repeat(Math.max(0, colSize - o.toString().length())));
            if (i >= cols) {
                b.append("\n");
                i = 1;
            } else {
                b.append(" ");
                i++;
            }
        }
        String s = b.toString();
        if (!s.endsWith("\n")) {
            s += "\n";
        }
        Console.log(type, style, s);
        return true;
    }

    public static boolean log(@NotNull Type type, @NotNull Style style) {
        return Console.log(type, style, "");
    }

    public static boolean log(@NotNull Type type, @NotNull String msg) {
        return Console.log(type, type.style, msg);
    }

    public static boolean logs(@NotNull Detail detail) {
        return Console.detail.val >= detail.val;
    }

    private static void sep(@Nullable Type type) {
        if (Console.type != type || null == type) {
            if (!Console.empty) {
                System.out.println();
            }
            Console.type = type;
            Console.empty = true;
        }
    }

    public static void sep() {
        Console.sep(null);
    }

    public static @NotNull String[] in() {
        Console.log(Type.PROMPT, "> ");
        Console.log(Type.PROMPT, Style.INPUT);
        return System.console().readLine().split(" ");
    }

    public static @NotNull String in(@NotNull String prompt) {
        Console.log(Type.PROMPT, prompt + " ");
        Console.log(Type.PROMPT, Style.INPUT);
        return System.console().readLine();
    }
}
