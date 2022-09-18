package com.gerimedica.codelist.util;

import org.apache.commons.csv.CSVFormat;

import java.io.InputStreamReader;
import java.util.List;

/***
 * @author M.Tugra Er
 *     18.09.2022
 */
public class CsvOperationsBuilder {

    private InputStreamReader reader;
    private CSVFormat csvFormat;
    private List<List<String>> dataList;
    private String[] headers;
    private boolean skipHeader;

    public static CsvOperationsBuilder builder() {
        return new CsvOperationsBuilder();
    }

    public CsvOperationsBuilder reader(InputStreamReader reader) {
        this.reader = reader;
        return this;
    }

    public CsvOperationsBuilder skipHeader(boolean skipHeader) {
        this.skipHeader = skipHeader;
        return this;
    }

    public CsvOperationsBuilder format(CSVFormat csvFormat) {
        this.csvFormat = csvFormat;
        return this;
    }

    public CsvOperationsBuilder dataList(List<List<String>> dataList) {
        this.dataList = dataList;
        return this;
    }

    public CsvOperationsBuilder headers(String ... headers) {
        this.headers = headers;
        return this;
    }

    public CsvOperations build() {
        return new CsvOperations(this.reader, this.csvFormat, this.dataList, this.headers, this.skipHeader);
    }
}
