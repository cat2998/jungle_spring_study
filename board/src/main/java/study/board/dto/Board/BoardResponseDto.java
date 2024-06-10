package study.board.dto.Board;

import study.board.domain.Board;
import java.time.LocalDateTime;

public record BoardResponseDto(
        Long id,
        String title,
        String username,
        String content,
        LocalDateTime createdAt
) {
    public static BoardResponseDto of(Board board) {
        return new BoardResponseDto(board.getId(), board.getTitle(), board.getUser().getUsername(), board.getContent(), board.getCreatedAt());
    }
}
