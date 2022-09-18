package com.gerimedica.codelist.dto.input;

import com.gerimedica.codelist.common.constant.RequestExceptionEnum;
import com.gerimedica.codelist.common.exception.RequestException;
import org.apache.commons.lang3.StringUtils;

import java.io.Serial;
import java.io.Serializable;

/***
 * @author M.Tugra Er
 *     18.09.2022
 */
public record GetCodeRecordByCodeInput (String code) implements Serializable {
    @Serial
    private static final long serialVersionUID = -2753891020761522271L;


    public GetCodeRecordByCodeInput {
        if (StringUtils.isEmpty(code) )
            throw new RequestException(RequestExceptionEnum.CODE_LIST_EMPTY_CODE_VALUE_EXCEPTION.getCode(), RequestExceptionEnum.CODE_LIST_EMPTY_CODE_VALUE_EXCEPTION.getMessage());

    }
}
