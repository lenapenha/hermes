package com.lenap.hermes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired UserRepository userRepository;

    public User saveNewUser(User user) {
        return userRepository.save(user);
    }
}
