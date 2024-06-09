package study.board.exception;

import jdk.jshell.spi.ExecutionControl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.board.dto.ApiResponse;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public void ApiExceptionHandler(ApiException e) {
        throw new ApiException(e.getErrorCode());
    }

//    @ExceptionHandler(ApiException.class)
//    public ResponseEntity<ApiResponse<ErrorCode>> ApiExceptionHandler(ApiException e) {
//        ApiException apiException = new ApiException(e.getErrorCode());
//        return ResponseEntity.status(201).body(new ApiResponse<>("api_error", apiException, "Board created successfully"));
//        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
//    }

//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler
//    public ErrorCode exHandler(Exception e) {
//        return new ErrorCode(HttpStatus.INTERNAL_SERVER_ERROR, "내부 오류!");
//    }
}