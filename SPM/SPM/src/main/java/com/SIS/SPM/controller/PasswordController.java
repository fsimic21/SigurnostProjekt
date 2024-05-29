package com.SIS.SPM.controller;

import com.SIS.SPM.models.Password;
import com.SIS.SPM.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passwords")
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    @GetMapping
    public List<Password> getAllPasswords() {
        return passwordService.getAllPasswords();
    }

    @PostMapping
    public void addPassword(@RequestBody Password password,String encryptAlgorithm) {
        passwordService.addPassword(password);
    }
}
