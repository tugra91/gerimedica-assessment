package com.gerimedica.codelist.common.constant;

/***
 * @author M.Tugra Er
 *     18.09.2022
 */
public enum BusinessExceptionEnum {

    CODE_LIST_NOT_FOUND_CODE_RECORD_EXCEPTION("-10001", "It is not found a code entity. Please insert a valid code."),
    CODE_LIST_DELETE_EMPTY_DATA_TABLE_EXCEPTION("-10002", "Data table is empty. You can not delete empty table"),
    CODE_LIST_EMPTY_TABLE("-10003", "Empty Data Table");

    private final String code;
    private final String message;

    BusinessExceptionEnum(String code, String message) {
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
