package com.lenap.hermes.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PasswordHashTest {

    @Test
    public void testHash() {
        String password = "password";
        String encrypted = PasswordHash.encrypt(password);
        System.out.println(encrypted);
        assertTrue(PasswordHash.checkPassword(password, encrypted));
    }
}
