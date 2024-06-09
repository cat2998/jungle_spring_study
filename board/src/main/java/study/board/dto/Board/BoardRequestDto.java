package study.board.dto.Board;

import study.board.domain.Board;

public record BoardRequestDto(
        String title,
        String content
) {
    public Board toEntity() {
        return new Board(this.title, this.content);
    }
}
