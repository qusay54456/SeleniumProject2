package ch4_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class ReadCSV {
	final static String CSV_FILE = "src/data/PrimeNumberTest.csv";
	final static String DELIMETER = ",|\\t";
	
	/**
	 * 
	 * @return read dummy data from CSV
	 */
	public static Iterator<Object[]> CSVDataProvider() {
		try {
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(new File(CSV_FILE));
			
			if(scanner.hasNextLine()) {
				scanner.nextLine();
			}
			return new Iterator<Object[]>() {
				
				@Override
				public Object[] next() {
					String line = scanner.nextLine();
					String[] values = line.split(DELIMETER);
					
					String description = values[0].trim();
					int number = Integer.valueOf(values[1].trim());
					boolean expected = Boolean.valueOf(values[2].trim());
					return new Object[] {description,number,expected};
				}
				
				@Override
				public boolean hasNext() {
					return scanner.hasNextLine();
				}
			};
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}
