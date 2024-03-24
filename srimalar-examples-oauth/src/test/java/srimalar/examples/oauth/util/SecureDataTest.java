package srimalar.examples.oauth.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;


public class SecureDataTest {
    @Test
    void givenString_whenEncrypt_thenSuccess()
            throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException,
            BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException {

        String input = "baeldung";
        SecretKey key = SecureData.generateKey(SecureData.BitLength.B128);
        System.out.println("Key : " + key.getAlgorithm());
        System.out.println("Format : " + key.getFormat());

        IvParameterSpec ivParameterSpec = SecureData.generateIv();
        String algorithm = "AES/CBC/PKCS5Padding";
        String cipherText = SecureData.encrypt(algorithm, input, key, ivParameterSpec);
        String plainText = SecureData.decrypt(algorithm, cipherText, key, ivParameterSpec);
        Assertions.assertEquals(input, plainText);
    }


    @Test
    void givenStringWhenEncryptDecrypt()
            throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException,
            BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException, InvalidKeySpecException {

        String input = "ramesh";
       /* SecretKey key = SecureData.generateKey(SecureData.BitLength.B128);*/


        IPasswords passwords = new Passwords();

        String randomPass = passwords.generateRandomPassword(12);


        byte[] saltByte = passwords.getSalt32();
      /* Just encode 64 to string for save to the config file */
        Base64.Encoder encoder = Base64.getEncoder();
        String encoded = encoder.encodeToString(saltByte);
        System.out.println("Salt 64 Encoded : " + encoded);
        /* Just decode 64 to string for secret key generation */
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decodeStringByte = decoder.decode(encoded);

        /* Just decode to string for save to the config file */
        SecretKey key = SecureData.getKeyFromPassword(randomPass, decodeStringByte, SecureData.BitLength.B256, 10000);








      /*  System.out.println("Salt 64 Encoded  : " + salt);*/

        IvParameterSpec ivParameterSpec = SecureData.generateIv();
        String algorithm = "AES/CBC/PKCS5Padding";
        String cipherText = SecureData.encrypt(algorithm, input, key, ivParameterSpec);
        String plainText = SecureData.decrypt(algorithm, cipherText, key, ivParameterSpec);
        System.out.println(plainText);
        Assertions.assertEquals(input, plainText);
    }
}
