package study.board.dto.User;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import study.board.domain.User;

public record UserRequestDto(
        @NotNull
        @Size(min = 4, max = 10)
        @Pattern(regexp = "^[a-z0-9]{4,10}$", message = "최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)로 구성되어야 합니다.")
        String username,

        @NotNull
        @Size(min = 8, max = 15)
        @Pattern(regexp = "^[a-zA-Z0-9@$!%*?&]{8,15}$", message = "최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9), 특수문자(@,$,!,%,*,?,&)로 구성되어야 합니다.")
        String password
) {
        public User toEntity() {
                return new User(this.username, this.password);
        }
}