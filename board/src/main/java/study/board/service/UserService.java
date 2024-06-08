package study.board.service;

import study.board.dto.Board.BoardRequestDto;
import study.board.dto.Board.BoardResponseDto;
import study.board.dto.Board.BoardUpdateRequestDto;
import study.board.dto.User.UserRequestDto;

import java.util.List;

public interface UserService {
    void saveUser(UserRequestDto userDto);
    void loginUser(UserRequestDto userDto);
}
