package org.alex.server.controllers;

import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/gallery")
@Slf4j
public class GalleryController {

    private List<byte[]> images;

    @PostConstruct
    private void loadImages() throws IOException {
        images = listFilesInDir("/home/aleksandar/Downloads/photos");
    }

    @GetMapping("/{count}")
    public ResponseEntity<byte[]> gallery(@PathVariable int count) {
        return ResponseEntity.of(Optional.of(images.get(count)));
    }

    @GetMapping("/count")
    public ResponseEntity<String> getImageCount(){
        return ResponseEntity.ok(images.size()+"");
    }

    public List<byte[]> listFilesInDir(String dir) throws IOException {
        try (Stream<Path> stream = Files.list(Path.of(dir))) {
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

}
