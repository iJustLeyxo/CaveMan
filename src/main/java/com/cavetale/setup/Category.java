package com.cavetale.setup;

import java.util.EnumSet;
import java.util.Set;

public enum Category implements PluginSet {
    CORE, // Required for proper function
    ALL, // Installed everywhere
    SURVIVAL, // All survival serverSoftware
    CREATIVE,
    BUILD, // All build serverSoftware
    HOME, // Home serverSoftware
    HUB,
    MINE,
    MINIGAME, // Custom server
    EVENT, // Event without its own server
    UTIL, // Optional
    DEPRECATED,
    WORLDGEN, // Resource world generation
    SEASONAL; // Event on main

    @Override
    public Set<Plugin> getPlugins() {
        Set<Plugin> result = EnumSet.noneOf(Plugin.class);
        for (Plugin plugin : Plugin.values()) {
            if (plugin.category == this) {
                result.add(plugin);
            }
        }
        return result;
    }

    public static Category of(String name) {
        try {
            return valueOf(name.toUpperCase());
        } catch (IllegalArgumentException iae) {
            return null;
        }
    }
}
