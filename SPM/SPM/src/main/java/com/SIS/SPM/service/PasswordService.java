package com.SIS.SPM.service;

import com.SIS.SPM.models.AES128;
import com.SIS.SPM.models.RSA128;
import com.SIS.SPM.models.SHA256;

import java.util.List;

public interface PasswordService {

    List<SHA256> getAllSHA();

    void addSHA(SHA256 sha256);

    List<RSA128> getAllRSA();

    void addRSA(RSA128 rsa128) throws Exception;

    List<AES128> getAllAES();

    void addAES(AES128 aes128);
}
