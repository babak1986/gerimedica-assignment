package com.babak.gerimedicaassignment.model;

import java.util.LinkedList;
import java.util.List;

public class UploadFileResult extends FileResult {

    private int rowsUploaded;
    private int rowsFailed;
    private List<String> duplicatedCodes = new LinkedList<>();

    public int getRowsUploaded() {
        return rowsUploaded;
    }

    public void setRowsUploaded(int rowsUploaded) {
        this.rowsUploaded = rowsUploaded;
    }

    public int getRowsFailed() {
        return rowsFailed;
    }

    public void setRowsFailed(int rowsFailed) {
        this.rowsFailed = rowsFailed;
    }

    public List<String> getDuplicatedCodes() {
        return duplicatedCodes;
    }

    public void setDuplicatedCodes(List<String> duplicatedCodes) {
        this.duplicatedCodes = duplicatedCodes;
    }

//    @Override
//    public String getMessage() {
//        return super.getMessage() + ", Uploaded rows: " + rowsUploaded + "\nFailed rows: " + rowsFailed;
//    }
}
