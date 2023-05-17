package com.babak.gerimedicaassignment.repository;

import com.babak.gerimedicaassignment.domian.UploadedData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadedDataRepository extends JpaRepository<UploadedData, Long> {
}