package org.alex.server.controllers;

import lombok.extern.slf4j.Slf4j;
import org.alex.server.services.FileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/upload")
@Slf4j
public class UploadController {

    private final FileService fileService;

    public UploadController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("")
    public void uploadImage(@RequestBody byte[] file) throws IOException {
        fileService.saveImage("file:///", file); //figure out how to set the path
    }
}
