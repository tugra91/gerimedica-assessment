package com.gerimedica.codelist.common.dto;

import com.gerimedica.codelist.common.constant.ExceptionTypeEnum;

import java.io.Serial;
import java.io.Serializable;

/***
 * @author M.Tugra Er
 *     18.09.2022
 */
public record ExceptionOutput(ExceptionTypeEnum exceptionType,
                              String message,
                              String code) implements Serializable {
    @Serial
    private static final long serialVersionUID = 2470611844881913741L;
}
