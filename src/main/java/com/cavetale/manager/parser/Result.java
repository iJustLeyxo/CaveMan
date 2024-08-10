package com.cavetale.manager.parser;

import com.cavetale.manager.data.Plugins;
import com.cavetale.manager.data.ServerSoftwares;
import org.jetbrains.annotations.NotNull;

public record Result (
        @NotNull Tokens tokens,
        @NotNull Plugins plugins,
        @NotNull ServerSoftwares serverSoftware
) { }
