package com.cavetale.manager.parser;

import com.cavetale.manager.parser.container.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum Flag {
    ALL("Select all"),
    CATEGORY("Specify categor(y/ies)", "-s <categor(y/ies)>") {
        @Override
        public @NotNull Container container() {
            return new PluginCategoryContainer();
        }
    },
    DEFAULT("Normal console output"),
    FORCE("Force execution"),
    HELP("Show command help"),
    INTERACTIVE("Enter command prompt mode"),
    PATH('P', "Specify a file path", "-P <path>") {
        @Override
        public @NotNull Container container() {
            return new PathContainer();
        }
    },
    PLUGIN('p', "Specify plugins(s)", "-p <plugins(s)>") {
        @Override
        public @NotNull Container container() {
            return new PluginContainer();
        }
    },
    QUIET("Reduced console output"),
    SERVER('s', "Specify server(s)", "-s <server(s)>") {
        @Override
        public @NotNull Container container() {
            return new ServerConfigContainer();
        }
    },
    SOFTWARE('S', "Specify server software") {
        @Override
        public @NotNull Container container() {
            return new ServerSoftwareContainer();
        }
    },
    VERBOSE("Detailed console output");

    public final @NotNull Character shortRef;
    public final @NotNull String longRef;
    public final @NotNull String info;
    public final @Nullable String usage;

    Flag(@NotNull String info, @Nullable String usage) {
        this.shortRef = this.name().toLowerCase().charAt(0);
        this.longRef = this.name().toLowerCase();
        this.info = info;
        this.usage = usage;
    }
    Flag(@NotNull String info) {
        this.shortRef = this.name().toLowerCase().charAt(0);
        this.longRef = this.name().toLowerCase();
        this.info = info;
        this.usage = null;
    }

    Flag(@NotNull Character shotRef, @NotNull String info) {
        this.shortRef = shotRef;
        this.longRef = this.name().toLowerCase();
        this.info = info;
        this.usage = null;
    }

    Flag(@NotNull Character shotRef, @NotNull String info, @Nullable String usage) {
        this.shortRef = shotRef;
        this.longRef = this.name().toLowerCase();
        this.info = info;
        this.usage = usage;
    }

    public @NotNull Container container() {
        return new Container();
    }

    public static @NotNull Flag get(@NotNull Character ref) throws NotFoundException {
        for (Flag type : values()) {
            if (ref == type.shortRef) {
                return type;
            }
        }
        throw new NotFoundException(ref);
    }

    public static @NotNull Flag get(@NotNull String ref) throws NotFoundException {
        for (Flag type : values()) {
            if (ref.equalsIgnoreCase(type.longRef)) {
                return type;
            }
        }
        throw new NotFoundException(ref);
    }

    public static class NotFoundException extends InputException {
        public NotFoundException(@NotNull Character ref) {
            super("Flag \"-"  + ref + "\" not found");
        }

        public NotFoundException(@NotNull String ref) {
            super("Flag \"--"  + ref + "\" not found");
        }
    }
}
