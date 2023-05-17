package com.babak.gerimedicaassignment.controller;

import com.babak.gerimedicaassignment.domian.UploadedData;
import com.babak.gerimedicaassignment.model.UploadFileResult;
import com.babak.gerimedicaassignment.service.UploadedDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadedDataController {

    final private UploadedDataService service;

    public UploadedDataController(UploadedDataService service) {
        this.service = service;
    }

    @PostMapping(value = "upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UploadFileResult> upload(@RequestPart("file") MultipartFile file) {
        return new ResponseEntity(service.upload(file), HttpStatus.OK);
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
