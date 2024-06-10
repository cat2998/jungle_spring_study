package study.board.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import study.board.dto.ApiResponse;
import study.board.dto.User.UserRequestDto;
import study.board.service.impl.UserServiceImpl;

@Controller
@RequestMapping("/api/user")
public class UserController {
    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/join")
    public ResponseEntity<ApiResponse<Void>> join(@RequestBody @Valid UserRequestDto userDto) {
        userService.saveUser(userDto);
        return ResponseEntity.ok(new ApiResponse<>(null, null, "Join successfully"));
    }

    @Operation
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Void>> login(@RequestBody UserRequestDto userDto, HttpServletResponse response) {
        userService.loginUser(userDto, response);
        return ResponseEntity.ok(new ApiResponse<>(null, null, "Login successfully"));
    }
}
