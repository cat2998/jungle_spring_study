package study.board.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.board.domain.User;
import study.board.dto.User.UserRequestDto;
import study.board.exception.ApiException;
import study.board.exception.ErrorCode;
import study.board.repository.UserRepository;
import study.board.service.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void saveUser(UserRequestDto userDto) {
        User user = userDto.toEntity();
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new ApiException(ErrorCode.USERNAME_ALREADY_EXISTS);
        }
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void loginUser(UserRequestDto userDto) {
        User user = userDto.toEntity();
        userRepository.findByUsername(user.getUsername())
                .filter(u -> u.getPassword().equals(user.getPassword()))
                .orElseThrow(() -> new ApiException(ErrorCode.USER_LONGIN_ERROR));
    }
}
