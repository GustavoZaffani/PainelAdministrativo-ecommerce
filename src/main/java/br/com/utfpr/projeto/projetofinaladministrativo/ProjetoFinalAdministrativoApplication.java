package br.com.utfpr.projeto.projetofinaladministrativo;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProjetoFinalAdministrativoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetoFinalAdministrativoApplication.class, args);
    }

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }
}
