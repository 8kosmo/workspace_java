package com.ch15;

import java.io.FileReader;
import java.util.Scanner;

/*
 * oos.writeObject();
 * dos.writeDouble();
 * is.write();
 */
public class ScannerReadable {
	
	public static void main (String[] args) {
		Scanner scan = null;
		FileReader fr = null;
		try {
			fr = new FileReader(".\\src\\com\\ch15\\scan.txt");
			scan = new Scanner(fr);
			while(scan.hasNextDouble()) {
				System.out.println("Double type : "+scan.nextDouble());
			}
			while(scan.hasNext()) {
				System.out.println("All type : "+scan.next());
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {				
				if(fr!=null) fr.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
}
