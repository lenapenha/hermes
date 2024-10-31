package com.lenap.hermes;

import com.lenap.hermes.utils.PasswordHash;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserRecord saveNewUser(User user) {
        user.setPassword(PasswordHash.encrypt(user.getPassword()));
        User saved = userRepository.save(user);

        return new UserRecord(saved.getId(), saved.getUsername(), saved.getEmail());
    }
}
