package com.SIS.SPM.controller;

import com.SIS.SPM.models.Password;
import com.SIS.SPM.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> addPassword(@RequestBody Password password) {
        passwordService.addSHA(password);
        return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\": \"Password hashed successfully\"}");
    }


    @GetMapping("/rsa")
    public List<Password> getAllRSA() {
        return passwordService.getAllRSA();
    }

    @PostMapping("/rsa")
    public ResponseEntity<String> addRSA(@RequestBody Password password) throws Exception {
        passwordService.addRSA(password);
        return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\": \"Password hashed successfully\"}");
    }

    @GetMapping("/aes")
    public List<Password> getAllAES() {
        return passwordService.getAllAES();
    }
    @PostMapping("/aes")
    public ResponseEntity<String> addAES(@RequestBody Password password){
        passwordService.addAES(password);
        return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\": \"Password hashed successfully\"}");
    }
    @GetMapping("/blowfish")
    public List<Password> getAllBlowfish() {
        return passwordService.getAllBlowfish();
    }

    @PostMapping("/blowfish")
    public ResponseEntity<String> addBlowfish(@RequestBody Password password){
        passwordService.addBlowfish(password);
        return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\": \"Password hashed successfully\"}");
    }
}
