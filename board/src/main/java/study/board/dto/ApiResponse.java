package study.board.dto;

public record ApiResponse<T> (
    String error,
    T data,
    String message
) {
}
