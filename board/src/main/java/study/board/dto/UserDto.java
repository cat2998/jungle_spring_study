package study.board.dto;

import study.board.domain.User;
import study.board.domain.UserRole;

public record UserDto(
        Long id,
        String username,
        String password,
        UserRole role
) {
    public UserDto(User user) {
        this(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getRole()
        );
    }
}