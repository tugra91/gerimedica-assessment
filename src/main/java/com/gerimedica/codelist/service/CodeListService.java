package com.gerimedica.codelist.service;

import com.gerimedica.codelist.common.dto.CommonOutput;
import com.gerimedica.codelist.dto.input.GetCodeRecordByCodeInput;
import com.gerimedica.codelist.dto.output.GetAllDataOutput;
import com.gerimedica.codelist.dto.output.GetCodeRecordByCodeOutput;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/***
 * @author M.Tugra Er
 *     18.09.2022
 */
public interface CodeListService {

    CommonOutput uploadAllData(MultipartFile csvFile) throws IOException;

    GetCodeRecordByCodeOutput getCodeRecordByCode(GetCodeRecordByCodeInput input);

    GetAllDataOutput getAllData();

    CommonOutput deleteAllData();

    byte[] getAllDataAndWriteBinary() throws IOException;
}
