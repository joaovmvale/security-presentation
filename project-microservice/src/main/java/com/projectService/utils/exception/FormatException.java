package com.projectService.utils.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class FormatException {

    @ExceptionHandler({CustomException.class, RuntimeException.class})
    public ResponseEntity<Object> sqlError(CustomException ex) {
        String response = "{" +
                "\"code\":\"" + ex.getCode() + "\"," +
                "\"message\":\"" + ex.getMessage() + "\"" +
                "}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<Object>(response, headers, HttpStatus.BAD_REQUEST);
    }
}
