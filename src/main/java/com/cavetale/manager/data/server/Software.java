package com.cavetale.manager.data.server;

import com.cavetale.manager.data.DataError;
import com.cavetale.manager.parser.InputException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

public enum Software {
    PAPER("https://api.papermc.io/v2/projects/paper/versions/1.21/builds/127/downloads/paper-1.21-127.jar", "Paper", "PaperMC"),
    SPIGOT("https://download.getbukkit.org/spigot/spigot-1.21.jar", "Spigot", "SpigotMC"),
    BUKKIT("https://download.getbukkit.org/craftbukkit/craftbukkit-1.21.jar", "Bukkit", "Craftbukkit");

    public final @NotNull String[] refs;
    public final @Nullable URI uri;

    Software(@NotNull String uri, @NotNull String ref, @NotNull String... refs) {
        this.refs = new String[refs.length + 1];
        this.refs[0] = ref;
        System.arraycopy(refs, 0, this.refs, 1, this.refs.length - 1);
        try {
            this.uri = new URI(uri);
        } catch (URISyntaxException e) {
            throw new URIError(uri, e);
        }
    }

    public @Nullable File file() {
        if (this.uri == null) {
            return null;
        }
        return new File(new File(this.uri.getPath()).getName());
    }

    public static final @NotNull Software DEFAULT = PAPER;

    public static @NotNull Software get(@NotNull String ref) throws NotFoundException {
        for (Software s : values()) {
            for (String r : s.refs) {
                if (r.equalsIgnoreCase(ref)) {
                    return s;
                }
            }
        }
        throw new NotFoundException(ref);
    }

    public static final class NotFoundException extends InputException {
        public NotFoundException(@NotNull String ref) {
            super("Server software \"" + ref + "\" not found");
        }
    }

    public final class URIError extends DataError {
        public URIError(@NotNull String uri, @NotNull Throwable cause) {
            super("Faulty url \n" + uri + "\n in " + refs[0] + " server software: " + cause.getMessage(), cause);
        }
    }
}
