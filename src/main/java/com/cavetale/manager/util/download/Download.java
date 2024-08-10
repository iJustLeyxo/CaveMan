package com.cavetale.manager.util.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public final class Download {
    public static void download(URI uri, File dest) throws IOException {
        if (dest.exists()) {
            throw new RuntimeException(dest.getPath() + " already exists.");
        }
        ReadableByteChannel byteChannel = Channels.newChannel(uri.toURL().openStream());
        FileOutputStream outputStream = new FileOutputStream(dest);
        outputStream.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);
    }
}
