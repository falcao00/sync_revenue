package com.sinReceita.sinReceita;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SinReceitaApplicationTests {

	@Test
	void atualizaContaParametrosNotNull() throws IOException, InterruptedException {
		ReceitaService receita = new ReceitaService();
		String agencia = "0505";
		String conta = "22559-5";
		Double saldo = 10.10;
		String status = "A";
		assertNotNull(agencia);
		assertNotNull(conta);
		assertNotNull(saldo);
		assertNotNull(status);
		
		boolean rc = receita.atualizarConta(agencia, conta, saldo, status);
		assertTrue(rc);
	}
	
	@Test
	void atualizaContaAgenciaCincoDigitos() throws IOException, InterruptedException {
		ReceitaService receita = new ReceitaService();
		String agencia = "05052"; //ELEMENTO ERRADO
		String conta = "22559-5";
		Double saldo = 10.10;
		String status = "A";
		assertNotNull(agencia);
		assertNotNull(conta);
		assertNotNull(saldo);
		assertNotNull(status);
		
		boolean rc = receita.atualizarConta(agencia, conta, saldo, status);
		assertFalse(rc);
	}
	
	@Test
	void atualizaContaContaSeisDigitosSemTraco() throws IOException, InterruptedException {
		ReceitaService receita = new ReceitaService();
		String agencia = "0505";
		String conta = "225595"; //ELEMENTO ERRRADO
		Double saldo = 10.10;
		String status = "A";
		assertNotNull(agencia);
		assertNotNull(conta);
		assertNotNull(saldo);
		assertNotNull(status);
		
		boolean rc = receita.atualizarConta(agencia, conta, saldo, status);
		assertFalse(rc);
	}
	
	@Test
	void atualizaContaStatusForaDaLista() throws IOException, InterruptedException {
		ReceitaService receita = new ReceitaService();
		String agencia = "0505";
		String conta = "22559-5";
		Double saldo = 10.10;
		String status = "Z"; //ELEMENTO ERRADO
		assertNotNull(agencia);
		assertNotNull(conta);
		assertNotNull(saldo);
		assertNotNull(status);
		
		boolean rc = receita.atualizarConta(agencia, conta, saldo, status);
		assertFalse(rc);
	}
	
	@Test
	void readCsvFileHappyPath() throws IOException, InterruptedException, FileNotFoundException {
		ReceitaService receita = new ReceitaService();
		
		String agencia = "0505";
		String conta = "22559-5";
		Double saldo = 10.10;
		String status = "A";
		String csvFileName = "csvReceita.csv";
		assertNotNull(agencia);
		assertNotNull(conta);
		assertNotNull(saldo);
		assertNotNull(status);
		assertNotNull(csvFileName);
			
		receita.readCsvFile(agencia, conta, 0, status, csvFileName);
		
	}
}
