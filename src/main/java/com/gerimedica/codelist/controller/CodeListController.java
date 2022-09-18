package com.gerimedica.codelist.controller;

import com.gerimedica.codelist.common.dto.CommonOutput;
import com.gerimedica.codelist.dto.input.GetCodeRecordByCodeInput;
import com.gerimedica.codelist.dto.output.GetAllDataOutput;
import com.gerimedica.codelist.dto.output.GetCodeRecordByCodeOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/***
 * @author M.Tugra Er
 *     18.09.2022
 */
public interface CodeListController {

    CommonOutput uploadAllData(MultipartFile csvFile) throws IOException;

    GetCodeRecordByCodeOutput  getCodeRecordByCode(GetCodeRecordByCodeInput input);

    GetAllDataOutput getAllData();

    CommonOutput deleteAllData();

    ResponseEntity<byte[]> getAllDataByCvs() throws IOException;

}
