package com.gerimedica.codelist.dto.output;

import com.gerimedica.codelist.common.constant.RequestExceptionEnum;
import com.gerimedica.codelist.common.exception.RequestException;
import com.gerimedica.codelist.dto.model.Code;
import org.apache.commons.lang3.ObjectUtils;

import java.io.Serial;
import java.io.Serializable;

/***
 * @author M.Tugra Er
 *     18.09.2022
 */
public record GetCodeRecordByCodeOutput(Code code) implements Serializable {

    @Serial
    private static final long serialVersionUID = 5345113761867281523L;

    public GetCodeRecordByCodeOutput {
        if (ObjectUtils.isEmpty(code) )
            throw new RequestException(RequestExceptionEnum.CODE_LIST_EMPTY_CODE_OBJECT_EXCEPTION.getCode(), RequestExceptionEnum.CODE_LIST_EMPTY_CODE_OBJECT_EXCEPTION.getMessage());
    }
}
