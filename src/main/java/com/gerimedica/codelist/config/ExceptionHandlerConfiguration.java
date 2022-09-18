package com.gerimedica.codelist.config;

import com.gerimedica.codelist.common.constant.ExceptionTypeEnum;
import com.gerimedica.codelist.common.dto.ExceptionOutput;
import com.gerimedica.codelist.common.exception.BusinessException;
import com.gerimedica.codelist.common.exception.RequestException;
import com.gerimedica.codelist.common.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/***
 * @author M.Tugra Er
 *     18.09.2022
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandlerConfiguration {

    private final static String UNEXPECTEDERRORCODE = "-1";
    private final static String UNEXPECTEDERRORMESSAGE = "Unexpected server error - We are facing some critical issues please try again.";

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ExceptionOutput> handleBusinessExceptions(BusinessException ex) {
        log.error(ExceptionTypeEnum.BUSINESS_EXCEPTION.name(), ex);
        return new ResponseEntity<>(new ExceptionOutput(ExceptionTypeEnum.BUSINESS_EXCEPTION, ex.getMessage(), ex.getCode()), new HttpHeaders(), HttpStatus.OK);
    }

    @ExceptionHandler(SystemException.class)
    public ResponseEntity<ExceptionOutput> handleSystemExceptions(SystemException ex) {
        log.error(ExceptionTypeEnum.SYSTEM_EXCEPTION.name(), ex);
        return new ResponseEntity<>(new ExceptionOutput(ExceptionTypeEnum.SYSTEM_EXCEPTION, ex.getMessage(), ex.getCode()), new HttpHeaders(), HttpStatus.OK);
    }

    @ExceptionHandler(RequestException.class)
    public ResponseEntity<ExceptionOutput> handleRequestExceptions(RequestException ex) {
        log.error(ExceptionTypeEnum.REQUEST_EXCEPTION.name(), ex);
        return new ResponseEntity<>(new ExceptionOutput(ExceptionTypeEnum.REQUEST_EXCEPTION, ex.getMessage(), ex.getCode()), new HttpHeaders(), HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionOutput> handleUnexpectedExceptions(Exception ex) {
        log.error(ExceptionTypeEnum.UNEXPECTED_EXCEPTION.name(), ex);
        return new ResponseEntity<>(new ExceptionOutput(ExceptionTypeEnum.UNEXPECTED_EXCEPTION, UNEXPECTEDERRORCODE, UNEXPECTEDERRORMESSAGE), new HttpHeaders(), HttpStatus.OK);
    }

}
