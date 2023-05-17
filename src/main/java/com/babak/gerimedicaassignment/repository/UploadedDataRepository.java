package com.babak.gerimedicaassignment.repository;

import com.babak.gerimedicaassignment.domian.UploadedData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UploadedDataRepository extends JpaRepository<UploadedData, Long> {

    Optional<UploadedData> findByCode(String code);
}