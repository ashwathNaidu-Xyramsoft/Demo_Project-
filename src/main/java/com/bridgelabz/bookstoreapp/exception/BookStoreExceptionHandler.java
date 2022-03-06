package com.bridgelabz.bookstoreapp.exception;

import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class BookStoreExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        List<ObjectError> errorsList = exception.getBindingResult().getAllErrors();
        System.out.println(errorsList);

        List<String> errMessage = errorsList.stream().map(objErr -> objErr.getDefaultMessage()).collect(Collectors.toList());
        ResponseDTO responseDTO = new ResponseDTO("Exception while processing REST Request", errMessage);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookStoreException.class)
    public ResponseEntity<ResponseDTO> handleAddressBookException(BookStoreException exception){
        ResponseDTO responseDTO = new ResponseDTO("Exception while processing REST Request", exception.getMessage());
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ResponseDTO> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException exception){

        ResponseDTO responseDTO = new ResponseDTO("Exception while processing REST Request",  exception.getMessage());
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
    }


}
