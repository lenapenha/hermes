package com.lenap.hermes.user;

import com.lenap.hermes.exception.ConflictException;
import com.lenap.hermes.utils.JwtUtil;
import com.lenap.hermes.utils.PasswordHash;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public UserRecord saveNewUser(User user) {
        Optional<User> userDb = Optional.ofNullable(userRepository.findByUsername(user.getUsername()));
        if (userDb.isPresent()) {
           throw new ConflictException("This user already exists");
        }
        user.setPassword(PasswordHash.encrypt(user.getPassword()));
        User newUser = userRepository.save(user);
        return new UserRecord(newUser.getId(), newUser.getUsername(), newUser.getEmail());
    }

    public AuthenticatedUserRecord login(LoginRecord loginInfo) {
        Optional<User> userDb = Optional.ofNullable(userRepository.findByUsername(loginInfo.username()));
        if (userDb.isEmpty()) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        if (!PasswordHash.checkPassword(loginInfo.password(), userDb.get().getPassword())) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        String token = jwtUtil.getToken(loginInfo.username());

        return new AuthenticatedUserRecord(token);
    }

    public UserRecord getUserDetails(String username) {
        Optional<User> userDb = Optional.ofNullable(userRepository.findByUsername(username));
        if (userDb.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return new UserRecord(userDb.get().getId(), userDb.get().getUsername(), userDb.get().getEmail());
    }
}
