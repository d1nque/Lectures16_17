package ua.d1nque.mongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class MongoTestApplication {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(MongoTestApplication.class, args);
    }
}

