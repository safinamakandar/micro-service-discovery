package org.example.authorms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
// TODO: Add annotation to enable discovery client
public class AuthormsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthormsApplication.class, args);
    }

}
