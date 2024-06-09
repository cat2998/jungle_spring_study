package study.board.dto.Board;

import study.board.domain.Board;
import java.time.LocalDateTime;

public record BoardResponseDto(
        Long id,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    public static BoardResponseDto of(Board board) {
        return new BoardResponseDto(board.getId(), board.getTitle(), board.getContent(), board.getCreatedAt(), board.getModifiedAt());
    }
}
