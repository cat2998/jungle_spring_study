package study.board.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.board.dto.Board.BoardUpdateRequestDto;

@Getter
@NoArgsConstructor
@Entity(name = "TB_BOARD")
public class Board extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(BoardUpdateRequestDto boardDto) {
        this.title = boardDto.title();
        this.content = boardDto.content();
    }
}
