package study.board.dto.Board;

public record BoardUpdateRequestDto(
        Long id,
        String title,
        String content
) {
}