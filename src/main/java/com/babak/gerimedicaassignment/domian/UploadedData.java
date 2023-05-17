package com.babak.gerimedicaassignment.domian;

import jakarta.persistence.*;

@Entity
@Table(name = "uploaded_data")
public class UploadedData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}