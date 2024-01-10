package com.snavekip.marco.photoz.clone.web;

import com.snavekip.marco.photoz.clone.model.Photo;
import com.snavekip.marco.photoz.clone.service.PhotozService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.*;

@RestController
public class PhotozController {

    private final PhotozService photozService;

    public PhotozController(PhotozService photozService) {
        this.photozService = photozService;
    }



    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/photoz")
    public Iterable<Photo> get() {

        return photozService.get();
    }

    @GetMapping("/photoz/{id}")
    public Photo get(@PathVariable Integer id) {
        Photo photo = photozService.get(id);
        if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return photo;
    }

    @DeleteMapping("/photoz/{id}")
    public void delete(@PathVariable Integer id) {
        photozService.remove(id);

    }

    @PostMapping("/photoz")
    public Photo create(@RequestPart("data") MultipartFile File) throws IOException {
        return photozService.save(File.getOriginalFilename(), File.getContentType(), File.getBytes());

    }

}
