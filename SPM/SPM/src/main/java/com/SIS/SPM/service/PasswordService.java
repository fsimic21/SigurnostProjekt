package com.SIS.SPM.service;

import com.SIS.SPM.models.Password;

import java.util.List;

public interface PasswordService {

    public List<Password> getAllPasswords();

    public void addPassword(Password password);
}
