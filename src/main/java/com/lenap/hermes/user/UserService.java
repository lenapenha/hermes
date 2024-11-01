package com.lenap.hermes.user;

import com.lenap.hermes.exception.ConflictException;
import com.lenap.hermes.utils.PasswordHash;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserRecord saveNewUser(User user) {
        user.setPassword(PasswordHash.encrypt(user.getPassword()));
        User saved =  new User();
        try {
            saved = userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
           if (e.getMessage().startsWith("could not execute statement [ERROR: duplicate key value violates unique constraint")) {
               throw new ConflictException("This user already exists");
           }
        }
        return new UserRecord(saved.getId(), saved.getUsername(), saved.getEmail());
    }
}
