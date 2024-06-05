package com.SIS.SPM.controller;

import com.SIS.SPM.models.Password;
import com.SIS.SPM.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/passwords")
public class PasswordController {
    @Autowired
    private PasswordService passwordService;

    @GetMapping("/")
    public List<Password> getAll(){
        List<Password> listOfAll = new java.util.ArrayList<>(List.of());
        listOfAll.addAll(passwordService.getAllSHA());
        listOfAll.addAll(passwordService.getAllRSA());
        listOfAll.addAll(passwordService.getAllAES());
        listOfAll.addAll(passwordService.getAllBlowfish());
        return  listOfAll;
    }

    @GetMapping("/sha")
    public List<Password> getAllPasswords() {
        return passwordService.getAllSHA();
    }


    @PostMapping("/sha")
    public void addPassword(@RequestBody Password password) {
        passwordService.addSHA(password);
    }

    @GetMapping("/rsa")
    public List<Password> getAllRSA() {
        return passwordService.getAllRSA();
    }

    @PostMapping("/rsa")
    public void addRSA(@RequestBody Password password) throws Exception {
        passwordService.addRSA(password);
    }

    @GetMapping("/aes")
    public List<Password> getAllAES() {
        return passwordService.getAllAES();
    }
    @PostMapping("/aes")
    public void addAES(@RequestBody Password password){
        passwordService.addAES(password);
    }
    @GetMapping("/blowfish")
    public List<Password> getAllBlowfish() {
        return passwordService.getAllBlowfish();
    }

    @PostMapping("/blowfish")
    public void addBlowfish(@RequestBody Password password){
        passwordService.addBlowfish(password);
    }
}
