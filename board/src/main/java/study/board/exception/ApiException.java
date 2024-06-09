package study.board.exception;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException{
    private ErrorCode errorCode;
    private String message;

    public ApiException(ErrorCode errorCode){
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
    }
}