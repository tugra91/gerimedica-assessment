package com.gerimedica.codelist.common.constant;

/***
 * @author M.Tugra Er
 *     18.09.2022
 */
public enum RequestExceptionEnum {

    CODE_LIST_EMPTY_CODE_VALUE_EXCEPTION("-20001", "Can not be empty code field."),
    CODE_LIST_EMPTY_CODE_OBJECT_EXCEPTION("-20002", "Can not be empty code object.");

    private final String code;
    private final String message;

    RequestExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
