package com.babak.gerimedicaassignment.util;

import com.babak.gerimedicaassignment.domian.UploadedData;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CsvUtil {

    public static List<UploadedData> loadCsvIntoEntityList(InputStream inputStream) throws Exception {
        CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();

        CsvMapper csvMapper = new CsvMapper();
        MappingIterator<UploadedData> rows = csvMapper.readerFor(UploadedData.class)
                .with(csvSchema).readValues(inputStream);
        List<UploadedData> list = new ArrayList<>();
        while (rows.hasNext()) {
            list.add(rows.nextValue());
        }
        return list;
    }
}
