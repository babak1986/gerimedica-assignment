package com.babak.gerimedicaassignment.controller;

import com.babak.gerimedicaassignment.domian.UploadedData;
import com.babak.gerimedicaassignment.service.UploadedDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadedDataController {

    final private UploadedDataService service;

    public UploadedDataController(UploadedDataService service) {
        this.service = service;
    }

    @PostMapping("upload")
    public ResponseEntity upload(MultipartFile file) {
        service.upload(file);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("fetchAll")
    public ResponseEntity fetchAll() {
        return new ResponseEntity(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("fetchByCode")
    public ResponseEntity fetchByCode(@RequestParam String code) {
        UploadedData uploadedData = service.findByCode(code);
        if (uploadedData != null) {
            return new ResponseEntity(uploadedData, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("deleteAll")
    public ResponseEntity deleteAll() {
        service.deleteAll();
        return new ResponseEntity(HttpStatus.OK);
    }
}
