package com.gerimedica.codelist.util;

import com.gerimedica.codelist.common.constant.DateFormatterEnum;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/***
 * @author M.Tugra Er
 *     18.09.2022
 */
public class DateUtil {

    public static LocalDate convertStringLocalDateByFormatter(String date, DateFormatterEnum dateFormatterEnum) throws DateTimeParseException {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern(dateFormatterEnum.getFormat()));
    }

    public static String convertLocalDateToStringByFormatter(LocalDate date, DateFormatterEnum dateFormatterEnum) {
        return date.format(DateTimeFormatter.ofPattern(dateFormatterEnum.getFormat()));
    }
}
