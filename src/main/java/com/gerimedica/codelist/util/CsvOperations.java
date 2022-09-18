package com.gerimedica.codelist.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.util.ObjectUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/***
 * @author M.Tugra Er
 *     18.09.2022
 */
public record CsvOperations(InputStreamReader reader,
                            CSVFormat csvFormat,
                            List<List<String>> dataList,
                            String[] headerList,
                            boolean skipHeader) {

    public CsvOperations {
        if (ObjectUtils.isEmpty(csvFormat)) {
            csvFormat = CSVFormat.DEFAULT.builder().build();
        }
    }

    public List<List<String>> read() throws IOException {
        if (ObjectUtils.isEmpty(reader))
            throw new IllegalArgumentException("File have to be set before read csv processing.");
        try (CSVParser csvRecordList = new CSVParser(reader, csvFormat)) {
            List<List<String>> resultList = new ArrayList<>();
            int rowNumber = 0;
            for(CSVRecord record : csvRecordList) {
                if (skipHeader && rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                List<String> row = new ArrayList<>();
                for (int i = 0; i < record.size(); i++) {
                    row.add(record.get(i));
                }
                resultList.add(row);
            }
            return resultList;
        }
    }

    public byte[] write() throws IOException {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             BufferedWriter bfWrite = new BufferedWriter(new OutputStreamWriter(outputStream));
             CSVPrinter csvPrinter = new CSVPrinter(bfWrite, csvFormat.builder()
                     .setHeader(headerList).build())
        ) {
            for(List<String> row : dataList) {
                csvPrinter.printRecord(row);
            }
            csvPrinter.flush();
            return outputStream.toByteArray();
        }
    }
}
