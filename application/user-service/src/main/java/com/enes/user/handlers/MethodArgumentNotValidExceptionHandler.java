package com.enes.user.handlers;

import com.enes.user.model.ApiErrorResponse;
import io.opentracing.Tracer;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class MethodArgumentNotValidExceptionHandler extends BaseExceptionHandler {

    public MethodArgumentNotValidExceptionHandler(Tracer tracer) {
        super(tracer, LoggerFactory.getLogger(MethodArgumentNotValidExceptionHandler.class));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> response(Throwable ex) {

        MethodArgumentNotValidException exception = (MethodArgumentNotValidException) ex;
        String category = "VALIDATION";
        String type = "REQUEST_MODEL_VALIDATION";
        String code = "400001";
        Map<String, Object> parameters = getBindingFieldParameters(exception.getBindingResult());

        logException(category, type, code, ex);

        ApiErrorResponse response = new ApiErrorResponse();
        response.setCategory(category);
        response.setType(type);
        response.setCode(code);
        response.setMessage("Request not validated. Unvalidated fields are listed in the parameters with their values");
        response.setUserMessage("Unexpected error. Please report to support@enes.com with trace id");
        response.setParameters(parameters);
        response.setTraceId(getTraceId());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
