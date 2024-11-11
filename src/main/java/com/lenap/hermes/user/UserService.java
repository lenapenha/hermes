package com.lenap.hermes.user;

import com.lenap.hermes.exception.ConflictException;
import com.lenap.hermes.utils.PasswordHash;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}
