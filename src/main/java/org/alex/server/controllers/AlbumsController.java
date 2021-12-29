package org.alex.server.controllers;

import lombok.extern.slf4j.Slf4j;
import org.alex.server.services.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/albums")
public class AlbumsController {

    private final FileService fileService;

    public AlbumsController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<String>> getAllAlbums() throws IOException {
        return ResponseEntity.ok(fileService.getAlbumsInDir(""));
    }

    @GetMapping("/{albumId}/count")
    public ResponseEntity<String> getImagesFromAlbum(@PathVariable String albumId) throws IOException {
        return ResponseEntity.ok(fileService.getImageCountInDir(albumId)+"");
    }

    @GetMapping("/{albumId}/{count}")
    public ResponseEntity<byte[]> getSingleImageFromAlbum(@PathVariable String albumId, @PathVariable int count) throws IOException {
        return ResponseEntity.ok(fileService.getSingleImageFromDir(albumId, count));
    }
}
