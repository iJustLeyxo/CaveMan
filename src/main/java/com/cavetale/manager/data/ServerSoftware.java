package com.cavetale.manager.data;

import com.cavetale.manager.parser.InputException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

public enum ServerSoftware {
    PAPER("https://api.papermc.io/v2/projects/paper/versions/1.21/builds/127/downloads/paper-1.21-127.jar"),
    SPIGOT("https://download.getbukkit.org/spigot/spigot-1.21.jar"),
    BUKKIT("https://download.getbukkit.org/craftbukkit/craftbukkit-1.21.jar");

    public final @NotNull String ref;
    public final @Nullable URI uri;

    ServerSoftware(@NotNull String uri) {
        this.ref = this.name().toLowerCase();
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

    public static final @NotNull ServerSoftware DEFAULT = PAPER;

    public static @NotNull ServerSoftware get(@NotNull String ref) throws NotFoundException {
        for (ServerSoftware s : values()) {
            if (s.ref.equalsIgnoreCase(ref)) {
                return s;
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
            super("Faulty url \n" + uri + "\n in " + ref + " server software: " + cause.getMessage(), cause);
        }
    }
}
