package com.gerimedica.codelist.common.exception;

import java.io.Serial;

/***
 * @author M.Tugra Er
 *     18.09.2022
 */
public class SystemException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = -2854738903839812894L;

    private final String code;

    public SystemException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public SystemException(String code, String message) {
        super(message);
        this.code = code;
    }


    public String getCode() {
        return code;
    }

}
