package com.example.task_manager_backend.utils;

import java.security.SecureRandom;
import java.util.Base64;

public class SecretKey {
    public static String generateSecretKey() {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[32]; // 256-bit key (32 bytes)
        random.nextBytes(key);
        return Base64.getEncoder().encodeToString(key); // Base64 encoded key
    }
}
