package com.lenap.hermes.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordHash {
    public static String encrypt(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
