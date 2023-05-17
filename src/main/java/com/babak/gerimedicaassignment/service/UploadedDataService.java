package com.babak.gerimedicaassignment.service;

import com.babak.gerimedicaassignment.domian.UploadedData;
import com.babak.gerimedicaassignment.repository.UploadedDataRepository;
import com.babak.gerimedicaassignment.util.CsvUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class UploadedDataService {

    final private UploadedDataRepository repository;

    public UploadedDataService(UploadedDataRepository repository) {
        this.repository = repository;
    }

    public UploadedData findByCode(String code) {
        return repository.findByCode(code).orElse(null);
    }

    public List<UploadedData> findAll() {
        return repository.findAll();
    }

    public void upload(MultipartFile file) {
        try {
            List<UploadedData> uploadedData = CsvUtil.loadCsvIntoEntityList(file.getInputStream());
            if (repository.count() == 0) {
                repository.saveAll(uploadedData);
            } else {
                uploadedData.forEach(ud -> {
                    if (findByCode(ud.getCode()) == null) {
                        repository.save(ud);
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
