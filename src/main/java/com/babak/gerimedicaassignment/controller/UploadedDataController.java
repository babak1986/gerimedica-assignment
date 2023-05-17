package com.babak.gerimedicaassignment.controller;

import com.babak.gerimedicaassignment.service.UploadedDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UploadedDataController {

    final private UploadedDataService service;

    public UploadedDataController(UploadedDataService service) {
        this.service = service;
    }

    @PostMapping("upload")
    public ResponseEntity upload() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("fetchAll")
    public ResponseEntity fetchAll() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("fetchByCode")
    public ResponseEntity fetchByCode() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("deleteAll")
    public ResponseEntity deleteAll() {
        return new ResponseEntity(HttpStatus.OK);
    }
}
