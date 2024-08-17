package com.cavetale.manager.data.server;

import com.cavetale.manager.data.DataError;
import com.cavetale.manager.parser.InputException;
import com.cavetale.manager.util.Download;
import com.cavetale.manager.util.console.Console;
import com.cavetale.manager.util.console.Style;
import com.cavetale.manager.util.console.Type;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Server software, used to register downloadable server software
 */
public enum Software {
    PAPER("https://api.papermc.io/v2/projects/paper/versions/1.21/builds/127/downloads/paper-1.21-127.jar",
            "Paper", "PaperMC"),
    SPIGOT("https://download.getbukkit.org/spigot/spigot-1.21.jar", "Spigot", "SpigotMC"),
    BUKKIT("https://download.getbukkit.org/craftbukkit/craftbukkit-1.21.jar", "Bukkit", "Craftbukkit");

    // TODO: add custom install and update logic

    // TODO: Broaden software detection (versions, minecraft version, etc.)

    public final @NotNull String[] refs;
    public final @Nullable URI uri;

    Software(@NotNull String uri, @NotNull String ref, @NotNull String... aliases) {
        this.refs = new String[aliases.length + 1];
        this.refs[0] = ref;
        System.arraycopy(aliases, 0, this.refs, 1, aliases.length);
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

    public void install() {
        Console.log(Type.INFO, "Installing " + this.refs[0] + " server software");
        File file = this.file(); // TODO: Fix this.file() functionality
        if (file == null) {
            if (!Console.log(Type.INFO, Style.WARN, " skipped (unable to install)\n")) {
                Console.log(Type.WARN, "Skipped " + this.refs[0] +
                        " server software (unable to install)\n");
            }
            Console.log(Type.WARN, this.refs[0] + " server software uri is not a file\n");
            return;
        }
        if (file.exists()) {
            if (!Console.log(Type.INFO, Style.WARN, " skipped (already installed)\n")) {
                Console.log(Type.WARN, this.refs[0] + " server software is already installed\n");
            }
            return;
        }
        try {
            Download.download(this.uri, file);
            Console.log(Type.INFO, Style.DONE, " done\n");
        } catch (IOException e) {
            if (!Console.log(Type.INFO, Style.ERR, " failed\n")) {
                Console.log(Type.ERR, "Installing " + this.refs[0] + " server software failed\n");
            }
        }
    }

    // TODO: Add update method

    public void uninstall() {
        Console.log(Type.INFO, "Uninstalling " + this.refs[0] + " server software");
        File file = this.file();
        if (file == null) {
            if (!Console.log(Type.INFO, Style.ERR, " skipped (unable to uninstall)\n")) {
                Console.log(Type.ERR, "Uninstalling " + this.refs[0] +
                        " server software skipped (unable to uninstall)\n");
            }
            return;
        }
        if (!file.exists()) {
            if (!Console.log(Type.INFO, Style.WARN, " skipped (not installed)\n")) {
                Console.log(Type.WARN, "Uninstalling " + this.refs[0] +
                        " server software skipped (not installed)\n");
            }
            return;
        }
        if (!file.delete()) {
            if (!Console.log(Type.INFO, Style.ERR, " failed\n")) {
                Console.log(Type.ERR, "Uninstalling " + this.refs[0] + " failed\n");
            }
            return;
        }
        Console.log(Type.INFO, Style.DONE, " done\n");
    }

    @Override
    public @NotNull String toString() {
        return this.refs[0];
    }

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

    public static void list() {
        Console.logL(Type.REQUESTED, Style.SOFTWARE, "Server software", 4, 21,
                (Object[]) Software.values());
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
