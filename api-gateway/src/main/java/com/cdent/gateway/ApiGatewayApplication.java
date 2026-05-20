package com.cdent.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada del API Gateway reactivo basado en Spring Cloud Gateway (Netty).
 * Centraliza el enrutamiento hacia los microservicios de la clínica dental sin exponer Tomcat.
 */
@SpringBootApplication
public class ApiGatewayApplication {

    /**
     * Arranca la aplicación en el puerto 8080 definido en application.yml.
     *
     * @param args argumentos de línea de comandos opcionales
     */
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}
