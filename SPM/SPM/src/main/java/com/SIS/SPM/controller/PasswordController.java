package com.SIS.SPM.controller;

import com.SIS.SPM.models.AES128;
import com.SIS.SPM.models.RSA128;
import com.SIS.SPM.models.SHA256;
import com.SIS.SPM.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passwords")
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    @GetMapping("/sha")
    public List<SHA256> getAllPasswords() {
        return passwordService.getAllSHA();
    }

    @PostMapping("/sha")
    public void addPassword(@RequestBody SHA256 SHA256) {
        passwordService.addSHA(SHA256);
    }

    @GetMapping("/rsa")
    public List<RSA128> getAllRSA() {
        return passwordService.getAllRSA();
    }

    @PostMapping("/rsa")
    public void addRSA(@RequestBody RSA128 rsa128) throws Exception {
        passwordService.addRSA(rsa128);
    }

    @GetMapping("/aes")
    public List<AES128> getAllAES() {
        return passwordService.getAllAES();
    }

    @PostMapping("/aes")
    public void addAES(@RequestBody AES128 aes128){
        passwordService.addAES(aes128);
    }
}
