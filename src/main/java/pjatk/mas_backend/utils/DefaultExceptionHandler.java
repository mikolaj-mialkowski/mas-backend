package pjatk.mas_backend.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pjatk.mas_backend.models.exceptions.BusinessException;

@ControllerAdvice
public class DefaultExceptionHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<String> handleNotFoundException(ResourceNotFoundException resourceNotFoundException, WebRequest webRequest){

        LOGGER.error("ResourceNotFoundException: " + resourceNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resourceNotFoundException.getMessage());
    }

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<String> handleBusinessException(BusinessException businessException, WebRequest webRequest){
        LOGGER.error("BusinessException: " + businessException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(businessException.getMessage());
    }
}
