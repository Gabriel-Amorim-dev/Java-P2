package br.com.gabriel.aluno_online;

import br.com.gabriel.aluno_online.model.Aluno;
import br.com.gabriel.aluno_online.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class AlunoOnlineApplication {

    @Autowired
    public AlunoService alunoService;

	public static void main(String[] args) {
		SpringApplication.run(AlunoOnlineApplication.class, args);
	}

}
