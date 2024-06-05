package com.SIS.SPM.service;

import com.SIS.SPM.models.Password;

import java.util.List;

public interface PasswordService {

    List<Password> getAllSHA();

    void addSHA(Password password);

    List<Password> getAllRSA();

    void addRSA(Password password) throws Exception;

    List<Password> getAllAES();

    void addAES(Password password);

    List<Password> getAllBlowfish();

    void addBlowfish(Password password);
}
