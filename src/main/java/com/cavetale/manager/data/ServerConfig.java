package com.cavetale.manager.data;

import com.cavetale.manager.parser.InputException;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public enum ServerConfig implements PluginProvider {
    BASE("Plugins for all serverSoftware", PluginCategory.CORE, PluginCategory.GLOBAL),
    VOID("Plugins for void serverSoftware", ServerConfig.BASE),
    HUB("Plugins for hub serverSoftware", ServerConfig.BASE, PluginCategory.SURVIVAL, PluginCategory.BUILD, PluginCategory.HUB, Plugin.STRUCTURE),
    EINS("Plugins for build server one", ServerConfig.BASE, PluginCategory.SURVIVAL, PluginCategory.BUILD, PluginCategory.HOME),
    ZWEI("Plugins for build server two", ServerConfig.BASE, PluginCategory.SURVIVAL, PluginCategory.BUILD, PluginCategory.HOME),
    MINE("Plugins for mine serverSoftware", ServerConfig.BASE, PluginCategory.SURVIVAL, PluginCategory.BUILD, PluginCategory.MINE),
    CREATIVE("Plugins for creative serverSoftware", ServerConfig.BASE, PluginCategory.CREATIVE, PluginCategory.BUILD, Plugin.ENEMY, Plugin.FESTIVAL,
            Plugin.LINK_PORTAL, Plugin.RACE, Plugin.RAID, Plugin.RESIDENT),
    EVENT("Plugins for event serverSoftware", ServerConfig.BASE, Plugin.WORLDS),
    CLASSIC("Plugins for classic serverSoftware", ServerConfig.BASE, PluginCategory.SURVIVAL, PluginCategory.BUILD);

    public final @NotNull String info;
    public final @NotNull PluginProvider[] providers;

    ServerConfig(@NotNull String info, @NotNull PluginProvider... providers) {
        this.info = info;
        this.providers = providers;
    }

    @Override
    public Set<Plugin> plugins() {
        Set<Plugin> plugins = new HashSet<>();
        for (PluginProvider p : providers) {
            plugins.addAll(p.plugins());
        }
        return plugins;
    }

    public static @NotNull ServerConfig get(@NotNull String ref) throws NotFoundException {
        for (ServerConfig s : ServerConfig.values()) {
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