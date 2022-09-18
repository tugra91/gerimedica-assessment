package com.gerimedica.codelist.controller.impl;

import com.gerimedica.codelist.common.dto.CommonOutput;
import com.gerimedica.codelist.controller.CodeListController;
import com.gerimedica.codelist.dto.input.GetCodeRecordByCodeInput;
import com.gerimedica.codelist.dto.output.GetAllDataOutput;
import com.gerimedica.codelist.dto.output.GetCodeRecordByCodeOutput;
import com.gerimedica.codelist.service.CodeListService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/***
 * @author M.Tugra Er
 *     18.09.2022
 */
@RestController
@RequiredArgsConstructor
public class ICodeListController implements CodeListController {

    private final CodeListService codeListService;

    private final static String TEXTCSVCONTENTTYPE = "text/csv";
    private final static String ATTACHMENTFILENAME = "attachment; filename=\"allDataList.csv\"";

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "It is enables to upload cvs file to server by using multipartfile and form-data. Its HttpMethod is POST and body type is form-data. ", content = @Content)
    })
    @PostMapping(value = "/uploadAllData")
    @Override
    public CommonOutput uploadAllData(@RequestPart("csvFile") MultipartFile csvFile) throws IOException {
        return codeListService.uploadAllData(csvFile);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "It is enables to get Code item by using code value. Code value is mandotory", content = @Content)
    })
    @PostMapping(value = "/getCodeRecordByCode")
    @Override
    public GetCodeRecordByCodeOutput getCodeRecordByCode(@RequestBody GetCodeRecordByCodeInput input) {
        return codeListService.getCodeRecordByCode(input);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "It is enables to get all code data as JSON format.", content = @Content)
    })
    @GetMapping(value = "/getAllData")
    @Override
    public GetAllDataOutput getAllData() {
        return codeListService.getAllData();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "It is enables to delete all data.", content = @Content)
    })
    @GetMapping(value = "/deleteAllData")
    @Override
    public CommonOutput deleteAllData() {
        return codeListService.deleteAllData();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "It is enables to get all data as cvs file format. Please don't use postman to get file. You might be use browser to retrieve it.", content = @Content)
    })
    @GetMapping(value = "/getAllDataByCVS")
    @Override
    public ResponseEntity<byte[]> getAllDataByCvs() throws IOException {
        HttpHeaders headers  = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, TEXTCSVCONTENTTYPE);
        headers.add(HttpHeaders.CONTENT_DISPOSITION, ATTACHMENTFILENAME);
        return new ResponseEntity<>(codeListService.getAllDataAndWriteBinary(), headers, HttpStatus.OK);
    }
}
