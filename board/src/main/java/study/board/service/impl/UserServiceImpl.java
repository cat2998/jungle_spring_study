package study.board.service.impl;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.board.domain.User;
import study.board.domain.UserRole;
import study.board.dto.User.UserRequestDto;
import study.board.exception.ApiException;
import study.board.exception.ErrorCode;
import study.board.jwt.JwtUtil;
import study.board.repository.UserRepository;
import study.board.service.UserService;

@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void saveUser(UserRequestDto userDto) {
        User user = userDto.toEntity();
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new ApiException(ErrorCode.USERNAME_ALREADY_EXISTS);
        }
        user.passwordEncode(passwordEncoder);
        user.updateUserRole(UserRole.USER);
        userRepository.save(user);
    }

    @Override
    public void loginUser(UserRequestDto userDto, HttpServletResponse response) {
        User user = userDto.toEntity();
        userRepository.findByUsername(user.getUsername())
                .filter(u-> passwordEncoder.matches(user.getPassword(), u.getPassword()))
                .orElseThrow(() -> new ApiException(ErrorCode.USER_LONGIN_ERROR));
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername(), user.getRole()));
    }
}
