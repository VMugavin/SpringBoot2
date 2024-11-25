package org.example.springboot2.controller;

import org.example.springboot2.exception.UnsupportedCodeException;
import org.example.springboot2.exception.ValidationFailedException;
import org.example.springboot2.model.Request;
import org.example.springboot2.model.Response;
import org.example.springboot2.service.UnsupportedCodeMatchException;
import org.example.springboot2.service.ValidationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.SimpleDateFormat;

@RestController
public class MyController {

    private final ValidationService validationService;
    private final UnsupportedCodeMatchException unsupportedCodeMatchException;

    public MyController(ValidationService validationService, UnsupportedCodeMatchException unsupportedCodeMatchException) {
        this.validationService = validationService;
        this.unsupportedCodeMatchException = unsupportedCodeMatchException;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request,
                                             BindingResult bindingResult) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T' HH:mm:ss.SSS'Z'");

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(request.getSystemTime())
                .code("success")
                .errorCode("")
                .errorMessage("")
                .build();

        try{
            validationService.isValid(bindingResult);
            unsupportedCodeMatchException.isUidMatch(request);
        }catch (ValidationFailedException e){
            response.setCode("failed");
            response.setErrorCode("ValidationException");
            response.setErrorMessage("Ошибка валидации");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (UnsupportedCodeException e) {
            response.setCode("failed");
            response.setErrorCode("UnsupportedCodeException");
            response.setErrorMessage("Не поддерживаемый UID");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            response.setCode("failed");
            response.setErrorCode("UnknownException");
            response.setErrorMessage("Произошла непредвиденная ошибка");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
