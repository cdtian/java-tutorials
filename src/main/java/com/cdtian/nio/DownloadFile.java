package com.cdtian.nio;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class DownloadFile {
    public void donwloadWithJava7(String url, String desc) {
        try (InputStream io = new URL(url).openStream()) {
            Files.copy(io, Paths.get(new URI(desc)), StandardCopyOption.REPLACE_EXISTING);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void downloadWithNio(String url,String desc) throws IOException {
        URL url1 = new URL(url);

        ReadableByteChannel channel = Channels.newChannel(url1.openStream());
        FileOutputStream fileOutputStream = new FileOutputStream(desc);
        FileChannel fileChannel = fileOutputStream.getChannel();
        fileChannel.transferFrom(channel, 0, Long.MAX_VALUE);
        fileOutputStream.close();
    }
}
