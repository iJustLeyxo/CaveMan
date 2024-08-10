package com.cavetale.setup;

import lombok.Getter;

import java.util.EnumSet;
import java.util.Set;

@Getter
public enum Server implements PluginSet {
    BASE("Base", Category.CORE, Category.ALL),

    VOID("Void", Server.BASE),
    HUB("Hub", Server.BASE, Category.SURVIVAL, Category.BUILD, Category.HUB,
        Plugin.STRUCTURE),
    EINS("Eins", Server.BASE, Category.SURVIVAL, Category.BUILD, Category.HOME),
    ZWEI("Zwei", Server.BASE, Category.SURVIVAL, Category.BUILD, Category.HOME),
    MINE("Cavetale", Server.BASE, Category.SURVIVAL, Category.BUILD, Category.MINE),
    CREATIVE("Creative", Server.BASE, Category.CREATIVE, Category.BUILD,
             Plugin.ENEMY, Plugin.FESTIVAL, Plugin.LINK_PORTAL, Plugin.RACE, Plugin.RAID, Plugin.RESIDENT),
    EVENT("Event", Server.BASE, Plugin.WORLDS),
    // Classic
    CAVETALE("Cavetale", Server.BASE, Category.SURVIVAL, Category.BUILD),
    ;

    public final String name;
    public final Set<Plugin> plugins;

    Server(final String name, final PluginSet... pluginSets) {
        this.name = name;
        this.plugins = EnumSet.noneOf(Plugin.class);
        for (PluginSet pluginSet : pluginSets) {
            plugins.addAll(pluginSet.getPlugins());
        }
    }

    public static Server of(String name) {
        try {
            return valueOf(name.toUpperCase());
        } catch (IllegalArgumentException iae) {
            return null;
        }
    }
}
