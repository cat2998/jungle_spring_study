package study.board.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.board.dto.Board.BoardUpdateRequestDto;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
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

    public void update(BoardUpdateRequestDto boardDto) {
        this.title = boardDto.title();
        this.content = boardDto.content();
    }
}
