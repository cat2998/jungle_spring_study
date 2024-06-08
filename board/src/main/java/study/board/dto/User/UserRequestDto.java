package study.board.dto.User;

import jakarta.validation.constraints.Pattern;
import study.board.domain.Board;
import study.board.domain.User;
import study.board.domain.UserRole;

public record UserRequestDto(
        @Pattern(regexp = "[a-z0-9]{4,10}")
        String username,

        @Pattern(regexp = "[a-zA-Z0-9]{8,15}")
        String password,

        UserRole role
) {
    public User toEntity() {
        return User.builder()
                .username(this.username)
                .password(this.password)
                .role(this.role)
                .build();
    }
}