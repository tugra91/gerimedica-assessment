package com.gerimedica.codelist.common.constant;

/***
 * @author M.Tugra Er
 *     18.09.2022
 */
public enum DateFormatterEnum {

    DATE_FORMAT_BASIC_LOCALDATE_WITH_HYPEN("dd-MM-yyyy");

    private String format;

    DateFormatterEnum(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}
