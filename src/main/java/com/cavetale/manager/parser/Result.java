package com.cavetale.manager.parser;

import com.cavetale.manager.data.plugin.PluginManager;
import com.cavetale.manager.data.server.SoftwareManager;
import org.jetbrains.annotations.NotNull;

public record Result (
        @NotNull Tokens tokens,
        @NotNull PluginManager pluginManager,
        @NotNull SoftwareManager softwareManager
) { }
