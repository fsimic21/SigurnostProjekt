package com.SIS.SPM.util;


import com.SIS.SPM.models.Algorithms;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
public class AESEncryption {
    public String encrypt(String plainText, String secretKey) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), Algorithms.AES);
        Cipher cipher = Cipher.getInstance(Algorithms.AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encryptedTextBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedTextBytes);
    }


    public String generateSecretKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(Algorithms.AES);
        keyGen.init(128);
        SecretKey secretKey = keyGen.generateKey();
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }
}
