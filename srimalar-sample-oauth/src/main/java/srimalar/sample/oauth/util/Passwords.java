package srimalar.sample.oauth.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serial;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Passwords implements IPasswords, Serializable {
    /*serialVersionUID*/
    @Serial
    private static final long serialVersionUID = 8036397974428641579L;
    private static final Logger logger = LoggerFactory.getLogger(Passwords.class);
    private static final Random RANDOM = new SecureRandom();
    private static final int DEFAULT_SIZE = 64;
    private static final char[] SYMBOLS = new char[] {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'
    };

    @Override
    public byte[] getSalt64() {
        return getSalt(DEFAULT_SIZE);
    }

    @Override
    public byte[] getSalt32() {
        return getSalt(32);
    }

    @Override
    public byte[] getSalt(int size) {
        final byte[] salt;
        if (size < 32) {
            final String message = String.format("Size < 32, using default of: %d", DEFAULT_SIZE);
            logger.warn(message);
            salt = new byte[DEFAULT_SIZE];
        } else {
            salt = new byte[size];
        }
        RANDOM.nextBytes(salt);
        return salt;
    }

    @Override
    public byte[] hash(String password, byte[] salt) {
        if (password == null) {
            throw new NullPointerException("Password must not be null");
        }
        if (salt == null) {
            throw new NullPointerException("Salt must not be null");
        }
        try {
            final byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
            final byte[] all = bytesAddAll(passwordBytes, salt);
            MessageDigest md = MessageDigest.getInstance("SHA3-256");
            md.update(all);
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            final String message = String.format("Caught UnsupportedEncodingException e: <%s>", e.getMessage());
            logger.error(message);
        }
        return new byte[0];
    }

    public static byte[] bytesAddAll(final byte[] array1, byte[] array2) {
        byte[] joinedArray = Arrays.copyOf(array1, array1.length + array2.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
    }

    @Override
    public boolean isExpectedPassword(final String password, final byte[] salt, final byte[] hash) {
        if (password == null) {
            throw new NullPointerException("Password must not be null");
        }
        if (salt == null) {
            throw new NullPointerException("Salt must not be null");
        }
        if (hash == null) {
            throw new NullPointerException("Hash must not be null");
        }
        try {
            final byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
            final byte[] all = bytesAddAll(passwordBytes, salt);
            MessageDigest md = MessageDigest.getInstance("SHA3-256");
            md.update(all);
            final byte[] digest = md.digest();
            return Arrays.equals(digest, hash);
        } catch (NoSuchAlgorithmException e) {
            final String message =
                    String.format("Caught NoSuchAlgorithmException e: <%s>", e.getMessage());
            logger.error(message);
        }
        return false;
    }

    @Override
    public String generateRandomPassword(final int length) {
        if (length < 1) {
            throw new IllegalArgumentException("length must be greater than 0");
        }
        final char[] buf = new char[length];
        for (int idx = 0; idx < buf.length; ++idx) {
            buf[idx] = SYMBOLS[RANDOM.nextInt(SYMBOLS.length)];
        }
        return shuffle(new String(buf));
    }


    private String shuffle(final String input) {
        final List<Character> characters = new ArrayList<>();
        for (char c : input.toCharArray()) {
            characters.add(c);
        }
        final StringBuilder output = new StringBuilder(input.length());
        while (!characters.isEmpty()) {
            int randPicker = (int) (Math.random() * characters.size());
            output.append(characters.remove(randPicker));
        }
        return output.toString();
    }
}
