package com.SIS.SPM.service.Impl;

import com.SIS.SPM.models.*;
import com.SIS.SPM.service.PasswordService;
import com.SIS.SPM.util.AESEncryption;
import com.SIS.SPM.util.BlowfishEncryption;
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
import java.util.Base64;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class PasswordServiceImpl implements PasswordService {
    @Autowired
    SHAEncryption SHAEncryption;
    @Autowired
    RSAEncryption rsaEncryption;
    @Autowired
    AESEncryption aesEncryption;
    @Autowired
    BlowfishEncryption blowfishEncryption;

    @Override
    public List<Password> getAllSHA() {
        Firestore firestore = FirestoreClient.getFirestore();
        List<Password> PasswordList = new ArrayList<>();

        try {
            firestore.collection(String.valueOf(Algorithms.SHA))
                    .get()
                    .get()
                    .getDocuments()
                    .forEach(document -> PasswordList.add(document.toObject(Password.class)));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return PasswordList;
    }

    @Override
    public void addSHA(Password Password) {
        Firestore firestore = FirestoreClient.getFirestore();
        String encryptedPassword = null;
        try {
            encryptedPassword = SHAEncryption.encrypt(Password.getPassword());
        } catch (NoSuchAlgorithmException e) {
            return;
        }
        Password.setPassword(encryptedPassword);

        ApiFuture<DocumentReference> future = firestore.collection(Algorithms.SHA).add(Password);
        try {
            DocumentReference documentReference = future.get();
            System.out.println("Added SHA document with ID: " + documentReference.getId());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Password> getAllRSA() {
        Firestore firestore = FirestoreClient.getFirestore();
        List<Password> Password = new ArrayList<>();

        try {
            firestore.collection(String.valueOf(Algorithms.RSA))
                    .get()
                    .get()
                    .getDocuments()
                    .forEach(document -> Password.add(document.toObject(Password.class)));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return Password;
    }

    @Override
    public void addRSA(Password Password) {
        Firestore firestore = FirestoreClient.getFirestore();
        try {
            byte[] encryptedPassword = rsaEncryption.encrypt(Password.getPassword(), Password.getKey());
            String encryptedPasswordBase64 = Base64.getEncoder().encodeToString(encryptedPassword);
            Password.setPassword(encryptedPasswordBase64);
            ApiFuture<DocumentReference> future = firestore.collection(Algorithms.RSA).add(Password);
            DocumentReference documentReference = future.get();
            System.out.println("Added RSA document with ID: " + documentReference.getId());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Password> getAllAES() {
        Firestore firestore = FirestoreClient.getFirestore();
        List<Password> Password = new ArrayList<>();

        try {
            firestore.collection(String.valueOf(Algorithms.AES))
                    .get()
                    .get()
                    .getDocuments()
                    .forEach(document -> Password.add(document.toObject(Password.class)));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return Password;
    }

    @Override
    public void addAES(Password Password) {
        Firestore firestore = FirestoreClient.getFirestore();
        try {
            byte[] encryptedPassword = aesEncryption.encrypt(Password.getPassword(), Password.getKey()).getBytes();
            String encryptedPasswordBase64 = Base64.getEncoder().encodeToString(encryptedPassword);
            Password.setPassword(encryptedPasswordBase64);
            ApiFuture<DocumentReference> future = firestore.collection(Algorithms.AES).add(Password);
            DocumentReference documentReference = future.get();
            System.out.println("Added AES document with ID: " + documentReference.getId());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Password> getAllBlowfish() {
        Firestore firestore = FirestoreClient.getFirestore();
        List<Password> Password = new ArrayList<>();

        try {
            firestore.collection(String.valueOf(Algorithms.BLOWFISH))
                    .get()
                    .get()
                    .getDocuments()
                    .forEach(document -> Password.add(document.toObject(Password.class)));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return Password;
    }

    @Override
    public void addBlowfish(Password Password) {
        Firestore firestore = FirestoreClient.getFirestore();
        try {
            byte[] encryptedPassword = blowfishEncryption.encrypt(Password.getPassword(), Password.getKey());
            String encryptedPasswordBase64 = Base64.getEncoder().encodeToString(encryptedPassword);
            Password.setPassword(encryptedPasswordBase64);
            ApiFuture<DocumentReference> future = firestore.collection(Algorithms.BLOWFISH).add(Password);
            DocumentReference documentReference = future.get();
            System.out.println("Added Blowfish document with ID: " + documentReference.getId());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
