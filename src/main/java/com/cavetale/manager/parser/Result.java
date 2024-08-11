package com.cavetale.manager.parser;

import com.cavetale.manager.data.plugin.PluginManager;
import com.cavetale.manager.data.server.SoftwareManager;
import org.jetbrains.annotations.NotNull;

/**
 * Parsing result, used to store the result of parsing a list of arguments
 * @param tokens Resulting tokens of parsing
 * @param pluginManager Plugin manager for the resulting tokens
 * @param softwareManager Software manager for the resulting tokens
 */
public record Result (
        @NotNull Tokens tokens,
        @NotNull PluginManager pluginManager,
        @NotNull SoftwareManager softwareManager
) { }
