package study.board.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import study.board.dto.ApiResponse;
import study.board.dto.Board.BoardRequestDto;
import study.board.dto.Board.BoardResponseDto;
import study.board.dto.Board.BoardUpdateRequestDto;
import study.board.service.impl.BoardServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/api/board")
public class BoardController {
    private final BoardServiceImpl boardService;

    @Autowired
    public BoardController(BoardServiceImpl boardService) {
        this.boardService = boardService;
    }

    @Operation
    @GetMapping
    public ResponseEntity<ApiResponse<List<BoardResponseDto>>> getBoards() {
        List<BoardResponseDto> boardList = boardService.findBoardAll();
        return ResponseEntity.ok(new ApiResponse<>(null, boardList, "BoardList fetched successfully"));
    }

    @Operation
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BoardResponseDto>> getBoardById(@PathVariable Long id) {
        BoardResponseDto findBoard = boardService.findBoardOne(id);
        return ResponseEntity.ok(new ApiResponse<>(null, findBoard, "Board fetched successfully"));
    }

    @Operation
    @PostMapping
    public ResponseEntity<ApiResponse<BoardResponseDto>> saveBoard(@RequestBody BoardRequestDto boardDto, HttpServletRequest httpServletRequest) {
        BoardResponseDto newBoard = boardService.saveBoard(boardDto, httpServletRequest);
        return ResponseEntity.status(201).body(new ApiResponse<>(null, newBoard, "Board created successfully"));
    }

    @Operation
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BoardResponseDto>> updateBoard(@PathVariable Long id, @RequestBody BoardUpdateRequestDto boardDto, HttpServletRequest httpServletRequest) {
        BoardResponseDto updateBoard = boardService.updateBoard(id, boardDto, httpServletRequest);
        return ResponseEntity.ok(new ApiResponse<>(null, updateBoard, "Board updated successfully"));
    }

    @Operation
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBoard(@PathVariable Long id, HttpServletRequest httpServletRequest) {
        boardService.deleteBoard(id, httpServletRequest);
        return ResponseEntity.ok(new ApiResponse<>(null, null, "Board deleted successfully"));
    }
}
