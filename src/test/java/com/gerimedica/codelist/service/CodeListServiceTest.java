package com.gerimedica.codelist.service;

import com.gerimedica.codelist.common.constant.DateFormatterEnum;
import com.gerimedica.codelist.common.dto.CommonOutput;
import com.gerimedica.codelist.common.exception.BusinessException;
import com.gerimedica.codelist.dao.CodeListRepository;
import com.gerimedica.codelist.dto.input.GetCodeRecordByCodeInput;
import com.gerimedica.codelist.dto.output.GetAllDataOutput;
import com.gerimedica.codelist.dto.output.GetCodeRecordByCodeOutput;
import com.gerimedica.codelist.entity.CodeEntity;
import com.gerimedica.codelist.service.impl.ICodeListService;
import com.gerimedica.codelist.util.CsvOperations;
import com.gerimedica.codelist.util.CsvOperationsBuilder;
import com.gerimedica.codelist.util.DateUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

/***
 * @author M.Tugra Er
 *     18.09.2022
 */
@ExtendWith(MockitoExtension.class)
public class CodeListServiceTest {

    @Mock
    private CodeListRepository codeListRepository;

    private CodeListService codeListService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        codeListService = new ICodeListService(codeListRepository);
    }

    @Test
    public void testGetCodeRecordByCode_validResponse_returnSuccess() {
        GetCodeRecordByCodeInput input = new GetCodeRecordByCodeInput("1");

        CodeEntity codeEntity = new CodeEntity();
        codeEntity.setCode("1");
        codeEntity.setCodeListCode("AB");
        Mockito.when(codeListRepository.findByCode(Mockito.refEq("1"))).thenReturn(Optional.of(codeEntity));

        GetCodeRecordByCodeOutput actualOutput = codeListService.getCodeRecordByCode(input);

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.code());
        Assertions.assertEquals("AB", actualOutput.code().codeListCode());
    }

    @Test
    public void testGetCodeRecordByCode_emptyEntity_throwException() {
        GetCodeRecordByCodeInput input = new GetCodeRecordByCodeInput("1");
        Mockito.when(codeListRepository.findByCode(Mockito.refEq("1"))).thenReturn(Optional.empty());
        Assertions.assertThrows(BusinessException.class, () -> codeListService.getCodeRecordByCode(input));
    }

    @Test
    public void testGetAllData_validResponse_returnSuccess() {
        CodeEntity codeEntity = new CodeEntity();
        codeEntity.setCode("1");
        codeEntity.setCodeListCode("AB");
        Iterable<CodeEntity> iterableResult = List.of(codeEntity);

        Mockito.when(codeListRepository.count()).thenReturn(Long.valueOf("1"));
        Mockito.when(codeListRepository.findAll()).thenReturn(iterableResult);

        GetAllDataOutput actualOutput = codeListService.getAllData();
        Assertions.assertEquals(1, actualOutput.codeList().size());
    }

    @Test
    public void testGetAllData_emptyResponse_throwException() {
        CodeEntity codeEntity = new CodeEntity();
        codeEntity.setCode("1");
        codeEntity.setCodeListCode("AB");
        Iterable<CodeEntity> iterableResult = List.of(codeEntity);

        Mockito.when(codeListRepository.count()).thenReturn(Long.valueOf("0"));

        Assertions.assertThrows(BusinessException.class, () -> codeListService.getAllData());
    }

    @Test
    public void testDeleteAllData_validResponse_returnSuccess() {
        Mockito.when(codeListRepository.count()).thenReturn(Long.valueOf("1"));
        CommonOutput actualOutput = codeListService.deleteAllData();
        Assertions.assertNotNull(actualOutput.message());
    }

    @Test
    public void testDeleteAllData_emptyResponse_throwException() {
        Mockito.when(codeListRepository.count()).thenReturn(Long.valueOf("0"));
        Assertions.assertThrows(BusinessException.class, () -> codeListService.deleteAllData());
    }

    @Test
    public void testGetAllDataAndWriteBinary_validResponse_returnSuccess() throws IOException {

        CodeEntity existEntity = new CodeEntity();
        existEntity.setSource("1");
        existEntity.setCodeListCode("12");
        existEntity.setCode("1");
        existEntity.setDisplayValue("121");
        existEntity.setLongDescription("");
        existEntity.setFromDate(LocalDate.now());
        existEntity.setToDate(LocalDate.now());
        existEntity.setSortingPriority(Long.valueOf("5"));
        List<CodeEntity> allEntityList = Collections.singletonList(existEntity);
        Iterable<CodeEntity> iterableResult = List.of(existEntity);

        List<List<String>> csvDataList = new ArrayList<>();
        for(CodeEntity example: allEntityList) {
            List<String> row = new ArrayList<>();
            row.add(example.getSource());
            row.add(example.getCodeListCode());
            row.add(example.getCode());
            row.add(example.getDisplayValue());
            row.add(example.getLongDescription());
            if (ObjectUtils.isNotEmpty(example.getFromDate())) {
                row.add(DateUtil.convertLocalDateToStringByFormatter(example.getFromDate(), DateFormatterEnum.DATE_FORMAT_BASIC_LOCALDATE_WITH_HYPEN));
            } else {
                row.add("");
            }
            if (ObjectUtils.isNotEmpty(example.getToDate())) {
                row.add(DateUtil.convertLocalDateToStringByFormatter(example.getToDate(), DateFormatterEnum.DATE_FORMAT_BASIC_LOCALDATE_WITH_HYPEN));
            } else {
                row.add("");
            }
            if (ObjectUtils.isNotEmpty(example.getSortingPriority())) {
                row.add(example.getSortingPriority().toString());
            } else {
                row.add("");
            }
            csvDataList.add(row);
        }
        CsvOperations csvOperations = CsvOperationsBuilder.builder()
                .dataList(csvDataList)
                .headers("source", "codeListCode", "code", "displayValue", "longDescription", "fromDate", "toDate", "sortingPriority")
                .build();
        byte[] expectedData = csvOperations.write();

        when(codeListRepository.findAll()).thenReturn(iterableResult);

        byte[] actualData = codeListService.getAllDataAndWriteBinary();

        Assertions.assertArrayEquals(expectedData, actualData);
    }

    @Test
    public void testGetAllDataAndWriteBinary_emptySomeFields_returnSuccess() throws IOException {

        CodeEntity existEntity = new CodeEntity();
        existEntity.setSource("1");
        existEntity.setCodeListCode("12");
        existEntity.setCode("1");
        existEntity.setDisplayValue("121");
        existEntity.setLongDescription("dsada");
        List<CodeEntity> allEntityList = Collections.singletonList(existEntity);
        Iterable<CodeEntity> iterableResult = List.of(existEntity);

        List<List<String>> csvDataList = new ArrayList<>();
        for(CodeEntity example: allEntityList) {
            List<String> row = new ArrayList<>();
            row.add(example.getSource());
            row.add(example.getCodeListCode());
            row.add(example.getCode());
            row.add(example.getDisplayValue());
            row.add(example.getLongDescription());
            if (ObjectUtils.isNotEmpty(example.getFromDate())) {
                row.add(DateUtil.convertLocalDateToStringByFormatter(example.getFromDate(), DateFormatterEnum.DATE_FORMAT_BASIC_LOCALDATE_WITH_HYPEN));
            } else {
                row.add("");
            }
            if (ObjectUtils.isNotEmpty(example.getToDate())) {
                row.add(DateUtil.convertLocalDateToStringByFormatter(example.getToDate(), DateFormatterEnum.DATE_FORMAT_BASIC_LOCALDATE_WITH_HYPEN));
            } else {
                row.add("");
            }
            if (ObjectUtils.isNotEmpty(example.getSortingPriority())) {
                row.add(example.getSortingPriority().toString());
            } else {
                row.add("");
            }
            csvDataList.add(row);
        }
        CsvOperations csvOperations = CsvOperationsBuilder.builder()
                .dataList(csvDataList)
                .headers("source", "codeListCode", "code", "displayValue", "longDescription", "fromDate", "toDate", "sortingPriority")
                .build();
        byte[] expectedData = csvOperations.write();

        when(codeListRepository.findAll()).thenReturn(iterableResult);

        byte[] actualData = codeListService.getAllDataAndWriteBinary();

        Assertions.assertArrayEquals(expectedData, actualData);
    }

}
