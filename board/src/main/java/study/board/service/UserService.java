package study.board.service;

import jakarta.servlet.http.HttpServletResponse;
import study.board.dto.User.UserRequestDto;

public interface UserService {
    void saveUser(UserRequestDto userDto);
    void loginUser(UserRequestDto userDto, HttpServletResponse response);
}
