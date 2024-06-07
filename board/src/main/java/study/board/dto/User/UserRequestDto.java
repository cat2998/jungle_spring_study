package study.board.dto.User;

import study.board.domain.UserRole;

public record UserRequestDto(
        String username,
        String password,
        UserRole role
) {
}