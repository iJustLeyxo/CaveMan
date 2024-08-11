package com.cavetale.manager.data.plugin;

import com.cavetale.manager.parser.InputException;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public enum Server implements Provider {
    BASE("Plugins for all softwareManager", Category.CORE, Category.GLOBAL),
    VOID("Plugins for void softwareManager", Server.BASE),
    HUB("Plugins for hub softwareManager", Server.BASE, Category.SURVIVAL, Category.BUILD, Category.HUB, Plugin.STRUCTURE),
    EINS("Plugins for build server one", Server.BASE, Category.SURVIVAL, Category.BUILD, Category.HOME),
    ZWEI("Plugins for build server two", Server.BASE, Category.SURVIVAL, Category.BUILD, Category.HOME),
    MINE("Plugins for mine softwareManager", Server.BASE, Category.SURVIVAL, Category.BUILD, Category.MINE),
    CREATIVE("Plugins for creative softwareManager", Server.BASE, Category.CREATIVE, Category.BUILD, Plugin.ENEMY, Plugin.FESTIVAL,
            Plugin.LINK_PORTAL, Plugin.RACE, Plugin.RAID, Plugin.RESIDENT),
    EVENT("Plugins for event softwareManager", Server.BASE, Plugin.WORLDS),
    CLASSIC("Plugins for classic softwareManager", Server.BASE, Category.SURVIVAL, Category.BUILD);

    public final @NotNull String info;
    public final @NotNull Provider[] providers;

    Server(@NotNull String info, @NotNull Provider... providers) {
        this.info = info;
        this.providers = providers;
    }

    @Override
    public Set<Plugin> plugins() {
        Set<Plugin> plugins = new HashSet<>();
        for (Provider p : providers) {
            plugins.addAll(p.plugins());
        }
        return plugins;
    }

    public static @NotNull Server get(@NotNull String ref) throws NotFoundException {
        for (Server s : Server.values()) {
            if (ref.equalsIgnoreCase(s.name())) return s;
        }
        throw new NotFoundException(ref);
    }

    public static class NotFoundException extends InputException {
        public NotFoundException(@NotNull String ref) {
            super("Server \"" + ref + "\" not found");
        }
    }
}