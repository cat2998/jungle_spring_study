package study.board.dto.Board;

import study.board.domain.Board;

public record BoardRequestDto(
        String title,
        String content
) {
    public Board toEntity() {
        return Board.builder()
                .title(this.title)
                .content(this.content)
                .build();
    }
}
