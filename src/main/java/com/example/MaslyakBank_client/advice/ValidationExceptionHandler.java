package com.example.MaslyakBank_client.advice;

import com.example.MaslyakBank_client.dto.errorsDTOs.GenericErrorDTO;
import com.example.MaslyakBank_client.dto.errorsDTOs.ValidationErrorDTO;
import com.example.MaslyakBank_client.exceptions.AlreadyExistException;
import jakarta.annotation.PostConstruct;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ValidationExceptionHandler {


    @PostConstruct
    public void init() {
        log.info("✅✅ TODO: Передавать JSON вывод ошибки в формате errorCode: Integer; errorMessage: String; errorDetails?: String[]", ValidationExceptionHandler.class.getName() + ".java");
    }



    @ExceptionHandler({MethodArgumentNotValidException.class})//для @Valid @RequestBody
    public ResponseEntity<ValidationErrorDTO> handleValidationMethodArgumentExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        ValidationErrorDTO response = new ValidationErrorDTO(HttpStatus.BAD_REQUEST.value(), "Validation error", errors);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler({ConstraintViolationException.class})//для @Min, @NotBlank
    public ResponseEntity<ValidationErrorDTO> handleValidationConstraintViolationExceptions(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(error -> {
            errors.put(error.getPropertyPath().toString(), error.getMessage());
        });
        ValidationErrorDTO response = new ValidationErrorDTO(HttpStatus.BAD_REQUEST.value(), "Validation error", errors);
        return ResponseEntity.badRequest().body(response);
    }



    @ExceptionHandler({AlreadyExistException.class})
    public ResponseEntity<ValidationErrorDTO> handleValidationAlreadyExistException(AlreadyExistException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("Already exist", ex.getMessage());
        ValidationErrorDTO response = new ValidationErrorDTO(HttpStatus.CONFLICT.value(), "Validation error", errors);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<GenericErrorDTO> handleIllegalArgumentException(IllegalArgumentException ex) {
        GenericErrorDTO  response = new GenericErrorDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericErrorDTO> handleGenericException() {
        GenericErrorDTO response = new GenericErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal server error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }




}
