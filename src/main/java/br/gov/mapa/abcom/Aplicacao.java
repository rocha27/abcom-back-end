package br.gov.mapa.abcom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages = {"br.gov.mapa"})
@PropertySource(value = {"classpath:application.properties", "classpath:arquitetura-mapa.properties"})
@EntityScan(basePackages = {"br.gov.mapa.abcom.infra.persistencia"})
@EnableTransactionManagement
public class Aplicacao {

	public static void main(String[] args) {
		SpringApplication.run(Aplicacao.class, args);
	}

}
