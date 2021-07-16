package ru.hw.hw.exception.adviceController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.hw.hw.exception.NotFoundException;
import ru.hw.hw.exception.NotUniqueNameException;
import ru.hw.hw.models.dtos.ResponseDto;

@ControllerAdvice
public class ExceptionIndexAdviceController {
    @ExceptionHandler
    public ResponseEntity<?> handlerNotFoundException(NotFoundException e) {
        ResponseDto responseDto = new ResponseDto(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<?> handlerNotUniqueName(NotUniqueNameException e) {
        ResponseDto responseDto = new ResponseDto(HttpStatus.IM_USED.value(), e.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.IM_USED);
    }
}
