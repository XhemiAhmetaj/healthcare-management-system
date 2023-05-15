package com.ikub.healthcare.controller.advise;

import com.ikub.healthcare.domain.ExceptionResponse;
import com.ikub.healthcare.domain.exception.BadRequestException;
import com.ikub.healthcare.domain.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionAdviseController {
    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handleBadRequestExceptionException(BadRequestException se) {
        ExceptionResponse res = new ExceptionResponse(se.getErrorMessage());
        return res;
    }
}
