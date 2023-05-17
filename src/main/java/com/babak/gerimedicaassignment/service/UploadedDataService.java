package com.babak.gerimedicaassignment.service;

import com.babak.gerimedicaassignment.domian.UploadedData;
import com.babak.gerimedicaassignment.model.UploadFileResult;
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

    public UploadFileResult upload(MultipartFile file) {
        UploadFileResult result = new UploadFileResult();
        try {
            List<UploadedData> uploadedData = CsvUtil.loadCsvIntoEntityList(file.getInputStream());
            if (repository.count() == 0) {
                repository.saveAll(uploadedData);
                result.setRowsUploaded(uploadedData.size());
            } else {
                uploadedData.forEach(ud -> {
                    if (findByCode(ud.getCode()) == null) {
                        repository.save(ud);
                        result.setRowsUploaded(result.getRowsUploaded() + 1);
                    } else {
                        result.setRowsFailed(result.getRowsFailed() + 1);
                        result.getDuplicatedCodes().add(ud.getCode());
                    }
                });
            }
            result.setSuccess(true);
        } catch (IOException e) {
            result.setMessage(e.getMessage());
        }
        return result;
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
