package com.SIS.SPM.service.Impl;

import com.SIS.SPM.models.Password;
import com.SIS.SPM.service.PasswordService;
import com.SIS.SPM.util.EncryptionUtils;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class PasswordServiceImpl implements PasswordService {
    private static final String COLLECTION_NAME = "password";

    @Autowired
    EncryptionUtils encryptionUtils;

    @Override
    public List<Password> getAllPasswords() {
        Firestore firestore = FirestoreClient.getFirestore();
        List<Password> passwordList = new ArrayList<>();

        try {
            firestore.collection(COLLECTION_NAME)
                    .get()
                    .get()
                    .getDocuments()
                    .forEach(document -> passwordList.add(document.toObject(Password.class)));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return passwordList;
    }

    @Override
    public void addPassword(Password password) {
        Firestore firestore = FirestoreClient.getFirestore();
        String encryptedPassword = null;
        try {
            encryptedPassword = encrypt(password.getPassword(), password.getAlgorithm());
        } catch (NoSuchAlgorithmException e) {
            return;
        }
        password.setPassword(encryptedPassword);

        ApiFuture<DocumentReference> future = firestore.collection(COLLECTION_NAME).add(password);
        try {
            DocumentReference documentReference = future.get();
            System.out.println("Added document with ID: " + documentReference.getId());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private String encrypt(String password, String encryptAlgorithm) throws NoSuchAlgorithmException {

        switch (encryptAlgorithm){
            case "SHA-256":
                try {
                    return encryptionUtils.SHA256(password);
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
        }
        return password;
    }
}
