package com.adam.buzas.onlab.main.restcontrollers;

import com.adam.buzas.onlab.main.converter.JsonStringToObjectConverter;
import com.adam.buzas.onlab.main.dto.BookRequest;
import com.adam.buzas.onlab.main.services.ImageUploadService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/api")
@RestController
public class ImageUploadController {

    @Autowired
    private ImageUploadService imageUploadService;
    @Autowired
    private JsonStringToObjectConverter converter;

    @PostMapping("/uploadImage")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("valami") String bookRequest) {
        BookRequest json = null;
        System.out.println(bookRequest);
        try {
            json = (BookRequest) converter.convertJsonStringToObject(bookRequest, BookRequest.class);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Konvertálási hiba");
        }
        System.out.println(json.getTitle());
        try {
            String response = imageUploadService.uploadImage(file, file.getOriginalFilename());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image.");
        }
    }


}
