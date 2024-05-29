package com.SIS.SPM.service.Impl;

import com.SIS.SPM.models.Password;
import com.SIS.SPM.service.PasswordService;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class PasswordServiceImpl implements PasswordService {
    private static final String COLLECTION_NAME="password";

    @Override
    public List<Password> getAllPasswords() {
        Firestore firestore = FirestoreClient.getFirestore();
        List<Password> passwordList = new ArrayList<>();

        try {
            QuerySnapshot querySnapshot = firestore.collection(COLLECTION_NAME).get().get();

            for (QueryDocumentSnapshot document : querySnapshot.getDocuments()) {
                Password password = document.toObject(Password.class);
                passwordList.add(password);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return passwordList;
    }
}
