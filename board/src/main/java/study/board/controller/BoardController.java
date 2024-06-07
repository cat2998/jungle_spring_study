package study.board.controller;

import io.swagger.v3.oas.annotations.Operation;
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
    public ResponseEntity<ApiResponse<BoardResponseDto>> saveBoard(@RequestBody BoardRequestDto boardDto) {
        BoardResponseDto newBoard = boardService.saveBoard(boardDto);
        return ResponseEntity.status(201).body(new ApiResponse<>(null, newBoard, "Board created successfully"));
    }

    @Operation
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BoardResponseDto>> updateBoard(@PathVariable Long id, @RequestBody BoardUpdateRequestDto boardDto) {
        BoardResponseDto updateBoard = boardService.updateBoard(id, boardDto);
        return ResponseEntity.ok(new ApiResponse<>(null, updateBoard, "Board updated successfully"));
    }

    @Operation
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.ok(new ApiResponse<>(null, null, "Board deleted successfully"));
    }
}
