package com.SIS.SPM.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FirebaseInitializer {

    @PostConstruct
    public void initialize() throws IOException {
        FileInputStream serviceAccount = null;
        try {
            Path path = Paths.get("SPM/serviceAccountKey.json");
            System.out.println("Path to serviceAccountKey.json: " + path.toAbsolutePath().toString());

            if (!Files.exists(path)) {
                throw new FileNotFoundException("serviceAccountKey.json not found at " + path.toAbsolutePath().toString());
            }

            serviceAccount = new FileInputStream(path.toString());

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (serviceAccount != null) {
                serviceAccount.close();
            }
        }
    }
}
