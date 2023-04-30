package co.ud.hashticket.pub.service.impl;

import co.ud.hashticket.pub.service.PasswordService;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Random;

@Service
public class PasswordServiceImpl implements PasswordService {
    private static final Random RANDOM = new SecureRandom();
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    @Override
    public String generatePassayPassword(int length) {
        StringBuilder returnValue = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return new String(returnValue);
    }
}
