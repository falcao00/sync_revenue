package com.sinReceita.sinReceita;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

@SpringBootApplication
public class SinReceitaApplication {
	
	static void readCSVFile(String csvFileName) {
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
				System.out.printf("%-10s %-10s $%-10.2f %-10s", 
						contaBean.getAgencia(), contaBean.getConta(),
						contaBean.getSaldo(), contaBean.getStatus());
				System.out.println();
				
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

	}


	public static void main(String[] args) {
		String csvFileName = "csvReceita.csv";
        readCSVFile(csvFileName);
        
        //TODO
        	//MAPEAR OS OS ELEMENTOS E CRIAR UM NOVO CSV COM A NOVA ALTERAÇÂO (?) 
		
		//SpringApplication.run(SinReceitaApplication.class, args);
	}

}
