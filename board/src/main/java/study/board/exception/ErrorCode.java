package study.board.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    USERNAME_ALREADY_EXISTS(HttpStatus.BAD_REQUEST,"이미 존재하는 username입니다."),
//    TOKEN_NOT_FOUND(HttpStatus.BAD_REQUEST,"잘못된 접근 입니다."),
//    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED,"토큰이 만료되었습니다."),
    TOKEN_INVALID(HttpStatus.UNAUTHORIZED,"유효하지 않은 토큰입니다."),
    ACCESS_INVALID(HttpStatus.BAD_REQUEST,"유효하지 않은 접근입니다."),
//    STOCK_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 주식이 존재하지 않습니다."),
//    NEWS_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 뉴스가 존재하지 않습니다."),
//    CHATROOM_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 채팅방이 존재하지 않습니다."),
//    NOT_FAVORITE_STOCK(HttpStatus.NOT_FOUND, "즐겨찾기 되어있지 않은 주식입니다"),
//    RE_LOGIN_REQUIRED(HttpStatus.UNAUTHORIZED,"인증 만료. 다시 로그인 해주시기 바랍니다."),
//    NEW_PASSWORD_NOT_CORRECT(HttpStatus.BAD_REQUEST,"새 비밀번호를 다시 확인해주세요."),
    //    EMAIL_ALREADY_EXISTS(HttpStatus.BAD_REQUEST,"해당 E-mail 은 이미 사용중입니다."),
//    PASSWORD_ERROR(HttpStatus.BAD_REQUEST,"비밀번호가 올바르지 않습니다."),
//    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 username을 찾을 수 없습니다."),

    USER_LONGIN_ERROR(HttpStatus.BAD_REQUEST,"username 또는 비밀번호가 올바르지 않습니다."),
    USERNAME_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 username을 찾을 수 없습니다."),
    BOARD_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 게시물을 찾을 수 없습니다.");

    private HttpStatus httpStatus;
    private String message;
}