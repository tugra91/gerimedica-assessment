package com.gerimedica.codelist.service.impl;

import com.gerimedica.codelist.common.constant.BusinessExceptionEnum;
import com.gerimedica.codelist.common.constant.DateFormatterEnum;
import com.gerimedica.codelist.common.dto.CommonOutput;
import com.gerimedica.codelist.common.exception.BusinessException;
import com.gerimedica.codelist.dao.CodeListRepository;
import com.gerimedica.codelist.dto.input.GetCodeRecordByCodeInput;
import com.gerimedica.codelist.dto.model.Code;
import com.gerimedica.codelist.dto.output.GetAllDataOutput;
import com.gerimedica.codelist.dto.output.GetCodeRecordByCodeOutput;
import com.gerimedica.codelist.entity.CodeEntity;
import com.gerimedica.codelist.service.CodeListService;
import com.gerimedica.codelist.util.CsvOperations;
import com.gerimedica.codelist.util.CsvOperationsBuilder;
import com.gerimedica.codelist.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/***
 * @author M.Tugra Er
 *     18.09.2022
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ICodeListService implements CodeListService {

    private final CodeListRepository codeListRepository;

    private final String UPLOADALLDATAOUTPUTMESSAGE = "All Data is recorded successed";
    private final String DELETEALLDATAOUTPUTMESSAGE = "All Data is deleted successed";
    private final String CVSFROMDATELOGINFO =  "Cvs From Date Parse Formatter Error: ";
    private final String CVSTODATELOGINFO = "Cvs To Date Parse Formatter Error: ";

    @Override
    public CommonOutput uploadAllData(MultipartFile csvFile) throws IOException {
        CsvOperations csvOperations = CsvOperationsBuilder.builder()
                .reader(new InputStreamReader(csvFile.getInputStream()))
                .skipHeader(true)
                .build();
        List<List<String>> dataList = csvOperations.read();
        List<CodeEntity> codeEntityList = dataList.stream().map(s -> {
            CodeEntity entity = new CodeEntity();
            entity.setSource(s.get(0));
            entity.setCodeListCode(s.get(1));
            entity.setCode(s.get(2));
            entity.setDisplayValue(s.get(3));
            entity.setLongDescription(s.get(4));
            if (StringUtils.isNotEmpty(s.get(5))) {
                try {
                    entity.setFromDate(DateUtil.convertStringLocalDateByFormatter(s.get(5), DateFormatterEnum.DATE_FORMAT_BASIC_LOCALDATE_WITH_HYPEN));
                } catch (DateTimeParseException ex) {
                    log.info(CVSFROMDATELOGINFO, ex);
                }
            }
            if (StringUtils.isNotEmpty(s.get(6))) {
                try {
                    entity.setToDate(DateUtil.convertStringLocalDateByFormatter(s.get(5), DateFormatterEnum.DATE_FORMAT_BASIC_LOCALDATE_WITH_HYPEN));
                } catch (DateTimeParseException ex) {
                    log.info(CVSTODATELOGINFO, ex);
                }
            }
            if (StringUtils.isNotEmpty(s.get(7)) && NumberUtils.isDigits(s.get(7))) {
                entity.setSortingPriority(Long.parseLong(s.get(7)));
            }
            return entity;
        }).toList();
        codeListRepository.saveAll(codeEntityList);
        return new CommonOutput(UPLOADALLDATAOUTPUTMESSAGE);
    }

    @Override
    public GetCodeRecordByCodeOutput getCodeRecordByCode(GetCodeRecordByCodeInput input) {
        CodeEntity existEntity = codeListRepository.findByCode(input.code())
                .orElseThrow(() -> new BusinessException(BusinessExceptionEnum.CODE_LIST_NOT_FOUND_CODE_RECORD_EXCEPTION.getCode(), BusinessExceptionEnum.CODE_LIST_NOT_FOUND_CODE_RECORD_EXCEPTION.getMessage()));
        return new GetCodeRecordByCodeOutput(Code.convertEntityToPojo(existEntity));
    }

    @Override
    public GetAllDataOutput getAllData() {
        long totalCount = codeListRepository.count();
        if ( totalCount == 0)
            throw new BusinessException(BusinessExceptionEnum.CODE_LIST_EMPTY_TABLE.getCode(), BusinessExceptionEnum.CODE_LIST_EMPTY_TABLE.getMessage());
        List<CodeEntity> allEntityList = IteratorUtils.toList(codeListRepository.findAll().iterator());
        List<Code> allCodeList =  allEntityList.stream().map(Code::convertEntityToPojo).toList();
        return new GetAllDataOutput(allCodeList);
    }

    @Override
    public CommonOutput deleteAllData() {
        long totalCount = codeListRepository.count();
        if ( totalCount == 0)
            throw new BusinessException(BusinessExceptionEnum.CODE_LIST_DELETE_EMPTY_DATA_TABLE_EXCEPTION.getCode(), BusinessExceptionEnum.CODE_LIST_NOT_FOUND_CODE_RECORD_EXCEPTION.getMessage());
        codeListRepository.deleteAll();
        return new CommonOutput(DELETEALLDATAOUTPUTMESSAGE);
    }

    @Override
    public byte[] getAllDataAndWriteBinary() throws IOException {
        Iterator<CodeEntity> allEntityIterators = codeListRepository.findAll().iterator();
        List<CodeEntity> allEntityList = IteratorUtils.toList(allEntityIterators);

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
        return csvOperations.write();
    }
}
