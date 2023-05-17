package com.babak.gerimedicaassignment.service;

import com.babak.gerimedicaassignment.repository.UploadedDataRepository;
import org.springframework.stereotype.Service;

@Service
public class UploadedDataService {

    final private UploadedDataRepository repository;

    public UploadedDataService(UploadedDataRepository repository) {
        this.repository = repository;
    }
}
