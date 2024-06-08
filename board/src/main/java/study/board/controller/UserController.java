package study.board.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import study.board.domain.User;
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

    @Operation
    @PostMapping("/join")
    public ResponseEntity<ApiResponse<Void>> join(@Valid @RequestBody UserRequestDto userDto, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(null, null, bindingResult.getAllErrors().toString()));
        }
        userService.saveUser(userDto);
        return ResponseEntity.ok(new ApiResponse<>(null, null, "Join successfully"));
    }

    @Operation
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Void>> login(@RequestBody UserRequestDto userDto) {
        userService.loginUser(userDto);
        //성공시 todo
        return ResponseEntity.ok(new ApiResponse<>(null, null, "Login successfully"));
    }
}
