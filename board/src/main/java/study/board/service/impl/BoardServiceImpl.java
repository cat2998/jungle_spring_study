package study.board.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.board.domain.Board;
import study.board.dto.Board.BoardRequestDto;
import study.board.dto.Board.BoardResponseDto;
import study.board.dto.Board.BoardUpdateRequestDto;
import study.board.exception.ApiException;
import study.board.exception.ErrorCode;
import study.board.repository.BoardRepository;
import study.board.service.BoardService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    @Transactional
    public BoardResponseDto saveBoard(BoardRequestDto boardDto) {
        Board board = boardDto.toEntity();
        Board saveBoard = boardRepository.save(board);
        return BoardResponseDto.of(saveBoard);
    }

    @Override
    @Transactional
    public BoardResponseDto updateBoard(Long id, BoardUpdateRequestDto boardDto) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.BOARD_NOT_FOUND));
        board.update(boardDto);
        return BoardResponseDto.of(board);
    }

    @Override
    @Transactional
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<BoardResponseDto> findBoardAll() {
        return boardRepository.findAllByOrderByCreatedAtDesc().stream().map(BoardResponseDto::of).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public BoardResponseDto findBoardOne(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.BOARD_NOT_FOUND));
        return BoardResponseDto.of(board);
    }
}
