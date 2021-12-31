package org.alex.server.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Slf4j
public class FileService {

    public static final String DEFAULT_DIR="/home/aleksandar/Downloads/";

    public void saveImage(String path, byte[] imageBinary) throws IOException {
        Files.write(Path.of(URI.create(path)), imageBinary);
    }

    public List<String> getAlbumsInDir(String dir) throws IOException {
        try (Stream<Path> stream = Files.list(Path.of(DEFAULT_DIR+dir))) {
            return stream
                    .filter(Files::isDirectory)
                    .map(file -> file.getFileName().toString()).collect(Collectors.toList());
        }
    }

    public List<byte[]> getImagesInDir(String dir) throws IOException {
        try (Stream<Path> stream = Files.list(Path.of(DEFAULT_DIR+dir))) {
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(file -> {
                        byte[] result={};
                        try {
                            result = Files.readAllBytes(file.toAbsolutePath());
                        } catch (IOException e) {
                            log.error(e.getMessage());
                        }
                        return result;
                    }).collect(Collectors.toList());
        }
    }

    //VERY bad
    public byte[] getSingleImageFromDir(String dir, int count) throws IOException {
        try (Stream<Path> stream = Files.list(Path.of(DEFAULT_DIR+dir))) {
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(file -> {
                        byte[] result={};
                        try {
                            result = Files.readAllBytes(file.toAbsolutePath());
                        } catch (IOException e) {
                            log.error(e.getMessage());
                        }
                        return result;
                    }).collect(Collectors.toList()).get(count);
        }
    }

    public long getImageCountInDir(String dir) throws IOException {
        try (Stream<Path> stream = Files.list(Path.of(DEFAULT_DIR+dir))) {
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(file -> {
                        byte[] result={};
                        try {
                            result = Files.readAllBytes(file.toAbsolutePath());
                        } catch (IOException e) {
                            log.error(e.getMessage());
                        }
                        return result;
                    }).count();
        }
    }
}
