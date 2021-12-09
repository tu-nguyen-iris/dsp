package io.dev.clientservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ClientserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientserviceApplication.class, args);
    }

}
