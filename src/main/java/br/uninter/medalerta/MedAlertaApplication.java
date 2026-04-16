package br.uninter.medalerta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MedAlertaApplication {

    private static final Logger log = LoggerFactory.getLogger(MedAlertaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MedAlertaApplication.class, args);
        log.info(">>> MedAlerta iniciado com sucesso! <<<");
    }
}