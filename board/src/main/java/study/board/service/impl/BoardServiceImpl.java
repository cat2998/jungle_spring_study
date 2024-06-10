package study.board.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.board.domain.Board;
import study.board.domain.User;
import study.board.domain.UserRole;
import study.board.dto.Board.BoardRequestDto;
import study.board.dto.Board.BoardResponseDto;
import study.board.dto.Board.BoardUpdateRequestDto;
import study.board.exception.ApiException;
import study.board.exception.ErrorCode;
import study.board.jwt.JwtUtil;
import study.board.repository.BoardRepository;
import study.board.repository.UserRepository;
import study.board.service.BoardService;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository, UserRepository userRepository, JwtUtil jwtUtil) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    @Transactional
    public BoardResponseDto saveBoard(BoardRequestDto boardDto, HttpServletRequest httpServletRequest) {
        if (!jwtUtil.validateToken(jwtUtil.resolveToken(httpServletRequest))) {
            throw new ApiException(ErrorCode.TOKEN_INVALID);
        }

        String username = getCurrentUsername(httpServletRequest);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ApiException(ErrorCode.USERNAME_NOT_FOUND));
        Board board = boardDto.toEntity();
        board.updateUser(user);
        Board saveBoard = boardRepository.save(board);
        return BoardResponseDto.of(saveBoard);
    }

    @Override
    @Transactional
    public BoardResponseDto updateBoard(Long id, BoardUpdateRequestDto boardDto, HttpServletRequest httpServletRequest) {
        if (!jwtUtil.validateToken(jwtUtil.resolveToken(httpServletRequest))) {
            throw new ApiException(ErrorCode.TOKEN_INVALID);
        }

        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.BOARD_NOT_FOUND));

        String username = getCurrentUsername(httpServletRequest);
        if (board.getUser().getRole().equals(UserRole.USER) && !board.getUser().getUsername().equals(username)) {
            throw new ApiException(ErrorCode.USERNAME_INVALID);
        }

        board.update(boardDto);
        return BoardResponseDto.of(board);
    }

    @Override
    @Transactional
    public void deleteBoard(Long id, HttpServletRequest httpServletRequest) {
        if (!jwtUtil.validateToken(jwtUtil.resolveToken(httpServletRequest))) {
            throw new ApiException(ErrorCode.TOKEN_INVALID);
        }

        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.BOARD_NOT_FOUND));

        String username = getCurrentUsername(httpServletRequest);
        if (board.getUser().getRole().equals(UserRole.USER) && !board.getUser().getUsername().equals(username)) {
            throw new ApiException(ErrorCode.USERNAME_INVALID);
        }

        boardRepository.deleteById(id);
    }

    @Override
    public List<BoardResponseDto> findBoardAll() {
        return boardRepository.findAllByOrderByCreatedAtDesc().stream().map(BoardResponseDto::of).collect(Collectors.toList());
    }

    @Override
    public BoardResponseDto findBoardOne(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.BOARD_NOT_FOUND));
        return BoardResponseDto.of(board);
    }

    private String getCurrentUsername(HttpServletRequest httpServletRequest) {
        return jwtUtil.getUserInfoFromToken(jwtUtil.resolveToken(httpServletRequest)).getSubject();
    }
}
