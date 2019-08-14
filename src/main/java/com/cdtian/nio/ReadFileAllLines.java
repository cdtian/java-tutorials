package com.cdtian.nio;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadFileAllLines {
    public static void main(String[] args) throws IOException, URISyntaxException {
        URI uri1 = ReadFileAllLines.class.getResource("/files/file1").toURI();
        URI uri2 = ReadFileAllLines.class.getResource("/files/file2").toURI();
        URI uri3 = ReadFileAllLines.class.getResource("/files/file3").toURI();
        Files.readAllLines(Paths.get(uri1)).forEach(t -> System.out.println(t));
        String mainPath = Paths.get(uri2).toString();
        System.out.println(mainPath);
        System.out.println(uri2.toString());
        Files.readAllLines(Paths.get(mainPath,new String[]{"",""})).forEach(t -> System.out.println(t));
    }
}
