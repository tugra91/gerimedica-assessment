package com.gerimedica.codelist.dto.model;

import com.gerimedica.codelist.common.constant.RequestExceptionEnum;
import com.gerimedica.codelist.common.exception.RequestException;
import com.gerimedica.codelist.entity.CodeEntity;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;

/***
 * @author M.Tugra Er
 *     18.09.2022
 */
public record Code(BigDecimal codeListId,
                   String source,
                   String codeListCode,
                   String code,
                   String displayValue,
                   String longDescription,
                   LocalDate fromDate,
                   LocalDate toDate,
                   Long sortingPriority) {

    public Code {
        if (StringUtils.isEmpty(code) )
            throw new RequestException(RequestExceptionEnum.CODE_LIST_EMPTY_CODE_VALUE_EXCEPTION.getCode(), RequestExceptionEnum.CODE_LIST_EMPTY_CODE_VALUE_EXCEPTION.getMessage());
    }


    public static Code convertEntityToPojo(CodeEntity entity) {
        return new Code(entity.getCodeListId(),
                entity.getSource(),
                entity.getCodeListCode(),
                entity.getCode(),
                entity.getDisplayValue(),
                entity.getLongDescription(),
                entity.getFromDate(),
                entity.getToDate(),
                entity.getSortingPriority());
    }
}
