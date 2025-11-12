package br.com.fiap.gsjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GsJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(GsJavaApplication.class, args);
    }

}
