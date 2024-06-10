package study.board.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    USERNAME_ALREADY_EXISTS(HttpStatus.BAD_REQUEST,"중복된 username 입니다."),
    TOKEN_INVALID(HttpStatus.UNAUTHORIZED,"토큰이 유효하지 않습니다."),
    USERNAME_INVALID(HttpStatus.BAD_REQUEST,"작성자만 삭제/수정할 수 있습니다."),
    USER_LONGIN_ERROR(HttpStatus.BAD_REQUEST,"회원을 찾을 수 없습니다."),
    USERNAME_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 username을 찾을 수 없습니다."),
    BOARD_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 게시물을 찾을 수 없습니다.");

    private HttpStatus httpStatus;
    private String message;
}