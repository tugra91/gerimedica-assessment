package com.gerimedica.codelist.common.exception;

import java.io.Serial;

/***
 * @author M.Tugra Er
 *     18.09.2022
 */
public class RequestException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = -1193618329651885856L;

    private final String code;

    public RequestException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public RequestException(String code, String message) {
        super(message);
        this.code = code;
    }


    public String getCode() {
        return code;
    }

}
