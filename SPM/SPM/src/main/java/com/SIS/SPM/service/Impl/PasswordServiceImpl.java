package com.SIS.SPM.service.Impl;

import com.SIS.SPM.models.Algorithms;
import com.SIS.SPM.models.RSA128;
import com.SIS.SPM.models.SHA256;
import com.SIS.SPM.service.PasswordService;
import com.SIS.SPM.util.RSAEncryption;
import com.SIS.SPM.util.SHAEncryption;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class PasswordServiceImpl implements PasswordService {
    @Autowired
    SHAEncryption SHAEncryption;
    @Autowired
    RSAEncryption rsaEncryption;

    @Override
    public List<SHA256> getAllSHA() {
        Firestore firestore = FirestoreClient.getFirestore();
        List<SHA256> SHA256List = new ArrayList<>();

        try {
            firestore.collection(String.valueOf(Algorithms.SHA))
                    .get()
                    .get()
                    .getDocuments()
                    .forEach(document -> SHA256List.add(document.toObject(SHA256.class)));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        SHA256List.forEach(document -> document.setAlgorithm(Algorithms.SHA));
        return SHA256List;
    }

    @Override
    public void addSHA(SHA256 sha256) {
        Firestore firestore = FirestoreClient.getFirestore();
        String encryptedPassword = null;
        try {
            encryptedPassword = SHAEncryption.SHA256(sha256.getPassword());
        } catch (NoSuchAlgorithmException e) {
            return;
        }
        sha256.setPassword(encryptedPassword);

        ApiFuture<DocumentReference> future = firestore.collection(Algorithms.SHA).add(sha256);
        try {
            DocumentReference documentReference = future.get();
            System.out.println("Added SHA document with ID: " + documentReference.getId());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<RSA128> getAllRSA() {
        Firestore firestore = FirestoreClient.getFirestore();
        List<RSA128> rsa128 = new ArrayList<>();

        try {
            firestore.collection(String.valueOf(Algorithms.RSA))
                    .get()
                    .get()
                    .getDocuments()
                    .forEach(document -> rsa128.add(document.toObject(RSA128.class)));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        rsa128.forEach(document -> document.setAlgorithm(Algorithms.SHA));
        return rsa128;
    }

    @Override
    public void addRSA(RSA128 rsa128) {
        Firestore firestore = FirestoreClient.getFirestore();

        try {
            byte[] encryptedPassword = rsaEncryption.encrypt(rsa128.getPassword(), rsa128.getPublicKey());
            String encryptedPasswordBase64 = Base64.getEncoder().encodeToString(encryptedPassword);
            rsa128.setPassword(encryptedPasswordBase64);
            ApiFuture<DocumentReference> future = firestore.collection(Algorithms.RSA).add(rsa128);
            DocumentReference documentReference = future.get();
            System.out.println("Added RSA document with ID: " + documentReference.getId());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
