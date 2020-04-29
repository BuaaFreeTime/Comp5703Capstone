package usyd.comp5703.capstone;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

@SpringBootApplication
public class WebMainApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {


        String jsonPath = "firebase/comp5703-b7667-firebase-adminsdk-lr1q4-b1e882953c.json";

        FirebaseOptions options = null;
        try {
            InputStream serviceAccount = new ClassPathResource(jsonPath).getInputStream();
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://comp5703-b7667.firebaseio.com")
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FirebaseApp.initializeApp(options);

        SpringApplication.run(WebMainApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

    private static Class<WebMainApplication> applicationClass = WebMainApplication.class;

}
