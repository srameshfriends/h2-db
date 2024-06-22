package srimalar.sample.oauth.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PasswordsTest {

    private static final Logger logger = LoggerFactory.getLogger(PasswordsTest.class);


    @Test
    public void testGeSalt() throws Exception {
        IPasswords passwords = new Passwords();
        final byte[] bytes = passwords.getSalt(0);
        Assertions.assertEquals( bytes.length, 64);
    }

    @Test
    public void testGeSalt32() throws Exception {
        IPasswords passwords = new Passwords();
        final byte[] bytes = passwords.getSalt32();
        Assertions.assertEquals(bytes.length, 32);
    }

    @Test
    public void testGeSalt64() throws Exception {
        IPasswords passwords = new Passwords();
        final byte[] bytes = passwords.getSalt64();
        Assertions.assertEquals(bytes.length, 64);
    }


    @Test
    public void testIsExpectedPasswordIncorrect() throws Exception {

        String password = "givemebeer";
        IPasswords passwords = new Passwords();

        final byte[] salt64 = passwords.getSalt64();
        final byte[] hash = passwords.hash(password, salt64);
        //The salt and the hash go to database.

        final boolean isPasswordCorrect = passwords.isExpectedPassword("jfjdsjfsd", salt64, hash);
        Assertions.assertFalse(isPasswordCorrect);

    }

    @Test
    public void testIsExpectedPasswordCorrect() throws Exception {
        String password = "givemebeer";
        IPasswords passwords = new Passwords();
        final byte[] salt64 = passwords.getSalt64();
        final byte[] hash = passwords.hash(password, salt64);
        //The salt and the hash go to database.
        final boolean isPasswordCorrect = passwords.isExpectedPassword("givemebeer", salt64, hash);
        Assertions.assertTrue(isPasswordCorrect);
    }

    @Test
    public void testGenerateRandomPassword() throws Exception {
        IPasswords passwords = new Passwords();
        final String randomPassword = passwords.generateRandomPassword(10);
        logger.info(randomPassword);
        Assertions.assertNotNull(randomPassword);
    }
}
