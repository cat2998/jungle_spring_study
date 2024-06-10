package study.board.service;

import jakarta.servlet.http.HttpServletRequest;
import study.board.dto.Board.BoardRequestDto;
import study.board.dto.Board.BoardResponseDto;
import study.board.dto.Board.BoardUpdateRequestDto;

import java.util.List;

public interface BoardService {
    BoardResponseDto saveBoard(BoardRequestDto boardDto, HttpServletRequest httpServletRequest);
    BoardResponseDto updateBoard(Long id, BoardUpdateRequestDto boardDto, HttpServletRequest httpServletRequest);
    void deleteBoard(Long id, HttpServletRequest httpServletRequest);
    List<BoardResponseDto> findBoardAll();
    BoardResponseDto findBoardOne(Long id);
}
