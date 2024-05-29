package com.SIS.SPM.controller;

import com.SIS.SPM.models.Password;
import com.SIS.SPM.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    @GetMapping
    public List<Password> getAllPasswords() {
        return passwordService.getAllPasswords();
    }
}
