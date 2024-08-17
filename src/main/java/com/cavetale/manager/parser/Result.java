package com.cavetale.manager.parser;

import com.cavetale.manager.data.plugin.Plugins;
import com.cavetale.manager.data.server.SoftwareManager;
import org.jetbrains.annotations.NotNull;

/**
 * Parsing result, used to store the result of parsing a list of arguments
 * @param tokens Resulting tokens of parsing
 * @param plugins Plugin manager for the resulting tokens
 * @param softwareManager Software manager for the resulting tokens
 */
public record Result (
        @NotNull Tokens tokens,
        @NotNull Plugins plugins,
        @NotNull SoftwareManager softwareManager) {

}
