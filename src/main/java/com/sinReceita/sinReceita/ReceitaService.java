package com.sinReceita.sinReceita;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

/**
 * @author gabriel_stabel<gabriel_stabel@sicredi.com.br>
 */

public class ReceitaService {
	
	//// Esta é a implementação interna do "servico" do banco central. Veja o código fonte abaixo para ver os formatos esperados pelo Banco Central neste cenário.
	
	public boolean atualizarConta(String agencia, String conta, double saldo, String status) throws IOException, InterruptedException {
		
			
        // Formato agencia: 0000
        if (agencia == null || agencia.length() != 4) {
            return false;
        }
        
        // Formato conta: 00000-0 (ANTES 000000) (CORREÇÃO, ANTES ESTAVA conta,lenght() != 6 sendo que precisa ser != a 7 por conta do - antes do ultimo digito)
        if (conta == null || conta.length() != 7) {
            return false;
        }
        
        // Tipos de status validos:
        List tipos = new ArrayList();
        tipos.add("A");
        tipos.add("I");
        tipos.add("B");
        tipos.add("P");                
                
        if (status == null || !tipos.contains(status)) {
            return false;
        }

        // Simula tempo de resposta do serviço (entre 1 e 5 segundos)
        long wait = Math.round(Math.random() * 4000) + 1000;
        Thread.sleep(wait);

        // Simula cenario de erro no serviço (0,1% de erro)
        long randomError = Math.round(Math.random() * 1000);
        if (randomError == 500) {
            throw new RuntimeException("Error");
        }
        
        String csvFileName = "csvReceita.csv";
        readCsvFile(agencia, conta, saldo, status, csvFileName);

        return true;
    }
	
	public void readCsvFile(String agencia, String conta, double saldo, String status, String csvFileName) throws IOException {
		
		//LENDO O ARQUIVO E ARMAZENANDO NOS ARRAYS
        int i = 0;
		
		ArrayList<String> agenciaList = new ArrayList<String>();
		ArrayList<String> contaList = new ArrayList<String>();
		ArrayList<Double> saldoList = new ArrayList<Double>();
		ArrayList<String> statusList = new ArrayList<String>();
		
		ICsvBeanReader beanReader = null;
		CellProcessor[] processors = new CellProcessor[] {
	            new NotNull(), // agencia
	            new NotNull(), // conta
	            new ParseDouble(), // price
	            new NotNull(), // status
	    };
		
		try {
			beanReader = new CsvBeanReader(new FileReader(csvFileName),
	                CsvPreference.STANDARD_PREFERENCE);
			
			String[] header = beanReader.getHeader(true);
			Conta contaBean = null;
			while ((contaBean = beanReader.read(Conta.class, header, processors)) != null) {
				
				agenciaList.add(contaBean.getAgencia());
				contaList.add(contaBean.getConta());
				saldoList.add(contaBean.getSaldo());
				statusList.add(contaBean.getStatus());
				
			} 
		} catch (FileNotFoundException ex) {
			System.err.println("Could not find the CSV file: " + ex);
		} catch (IOException ex) {
            System.err.println("Error reading the CSV file: " + ex); 
        } finally { 
        	if (beanReader != null) {
        		try {
        			beanReader.close();
        		} catch (IOException ex) {
                    System.err.println("Error closing the reader: " + ex); 
                }
        	}
        }
		
		//COM OS ARQUIVOS LIDOS E ARMAZENADOS, ADICIONAR OS NOVOS ELEMENTOS INPUTADOS
		agenciaList.add(agencia);
		contaList.add(conta);
		saldoList.add(saldo);
		statusList.add(status);
		
		//CRIAR NOVO contaBean COM OS ELEMENTOS CAPTURADOS
		writeCsvFile(agenciaList, contaList, saldoList, statusList, csvFileName);
	}
	
	
	public void writeCsvFile(ArrayList<String> agenciaList, ArrayList<String> contaList, 
			ArrayList<Double> saldoList, ArrayList<String> statusList, String csvFileName) throws IOException {
		
		int newindex = 0;
		ICsvBeanWriter beanWriter = null;
		Conta contaBeanFinal = null;
		
		try {
			beanWriter = new CsvBeanWriter(new FileWriter("csvReceita.csv"), CsvPreference.STANDARD_PREFERENCE);
			final String[] headerFinal = new String[] { "agencia", "conta", "saldo", "status" };
			final CellProcessor[] processorsFinal = new CellProcessor[] {
					new NotNull(), // agencia
		            new NotNull(), // conta
		            new ParseDouble(), // price
		            new NotNull(), // status
			};
			
			//ESCREVE E GERA O CSV COM A ATUALIZAÇÃO
			
			beanWriter.writeHeader(headerFinal);
			for(newindex = 0; newindex < agenciaList.size(); newindex++) {
				contaBeanFinal = new Conta(agenciaList.get(newindex), contaList.get(newindex), saldoList.get(newindex), statusList.get(newindex));
				beanWriter.write(contaBeanFinal, headerFinal, processorsFinal);
			}
		} finally  {
			if( beanWriter != null ) {
                beanWriter.close();
			}
		}
	}

}
