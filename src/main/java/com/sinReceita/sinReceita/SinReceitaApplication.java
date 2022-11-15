package com.sinReceita.sinReceita;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SinReceitaApplication {


	public static void main(String[] args) throws RuntimeException, InterruptedException, IOException {
		
		//LEITURA E CAPTURA DO ARQUIVO
		//String csvFileName = "csvReceita.csv";
        //ReceitaService.readCSVFile(csvFileName);
		//ReceitaService.atualizarConta("0202", "13131-3", 20.00, "A");
		
		ReceitaService receita = new ReceitaService();
		receita.atualizarConta("0303", "15255-7", 100.00, "B");
		
		//readCSVFile(csvFileName);
        
        //ATUALIZAÇÃO DO ARQUIVO
        
        
        //TODO
        	//MAPEAR OS OS ELEMENTOS E CRIAR UM NOVO CSV COM A NOVA ALTERAÇÂO (?) 
		
		//SpringApplication.run(SinReceitaApplication.class, args);
	}

}
