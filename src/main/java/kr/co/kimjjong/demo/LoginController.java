package kr.co.kimjjong.demo;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("api")
public class LoginController {

    @PostMapping("/login")
    public String Login(@RequestBody Map<String, String> loginInfo) throws ExecutionException, InterruptedException, IOException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("users").document("jzzW5WMRqt6u3NaMwYqo");

        ApiFuture<DocumentSnapshot> future = docRef.get();

        DocumentSnapshot document = future.get();
        if (document.get("id").equals(loginInfo.get("id")) && document.get("pw").equals(loginInfo.get("pw"))) {
            return "로그인 성공";
        } else {
            return "로그인 실패";
        }
    }
}
