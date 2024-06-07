package study.board.service;

import study.board.dto.Board.BoardRequestDto;
import study.board.dto.Board.BoardResponseDto;
import study.board.dto.Board.BoardUpdateRequestDto;

import java.util.List;

public interface BoardService {
    BoardResponseDto saveBoard(BoardRequestDto boardDto);
    BoardResponseDto updateBoard(Long id, BoardUpdateRequestDto boardDto);
    void deleteBoard(Long id);
    List<BoardResponseDto> findBoardAll();
    BoardResponseDto findBoardOne(Long id);
}
