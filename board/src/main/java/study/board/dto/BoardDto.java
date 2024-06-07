package study.board.dto;

import study.board.domain.Board;

public record BoardDto(
        Long id,
        Long userId,
        String title,
        String content
) {
    public BoardDto(Board board) {
        this(
                board.getId(),
                board.getUserId(),
                board.getTitle(),
                board.getContent()
        );
    }
}