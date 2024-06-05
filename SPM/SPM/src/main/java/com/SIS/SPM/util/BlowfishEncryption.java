package com.SIS.SPM.util;

import com.SIS.SPM.models.Algorithms;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
@Service
public class BlowfishEncryption {
    public static byte[] encrypt(String password, String publicKeyString) throws Exception {
        SecretKeySpec key = new SecretKeySpec(publicKeyString.getBytes(), Algorithms.BLOWFISH);
        Cipher cipher = Cipher.getInstance(Algorithms.BLOWFISH);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedData = cipher.doFinal(password.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData).getBytes();
    }
}
