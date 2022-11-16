package com.sinReceita.sinReceita;

import java.io.IOException;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SinReceitaApplication {


	public static void main(String[] args) throws RuntimeException, InterruptedException, IOException {		
		ReceitaService receita = new ReceitaService();
		receita.atualizarConta("0404", "16255-7", 125.85, "A");
	}

}
