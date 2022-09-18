package com.gerimedica.codelist.common.exception;

import java.io.Serial;

/***
 * @author M.Tugra Er
 *     18.09.2022
 */
public class BusinessException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 4074072313228043754L;

    private final String code;

    public BusinessException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }


    public String getCode() {
        return code;
    }

}
