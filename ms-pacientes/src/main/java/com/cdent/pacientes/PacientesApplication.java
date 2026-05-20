package com.cdent.pacientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Microservicio de gestión de pacientes de la clínica dental CDent.
 */
@SpringBootApplication
public class PacientesApplication {

    public static void main(String[] args) {
        SpringApplication.run(PacientesApplication.class, args);
    }
}
