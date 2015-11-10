package pl.edu.agh.root;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.LinkedList;

public class QuadraticCalculator {
		
	public static void main(String [] args){
		IOEquationsHandler iohandler = null;
		
		//check args arr quantity
		if (args.length != 2){
			System.out.println("Two args required paths indicating files: to read from and to write to");
			System.exit(1);
		}
		
		//create object IOEquationsHandler 
		try{
			iohandler = new IOEquationsHandler(args[0], args[1]);
		}
		catch(InvalidPathException invalidPath){
			System.out.println("Paths passed as arguments are incorrenct");
			System.exit(1);
		}
		
		//create list with with factors for each equations
		LinkedList<String> equationFactors = null;
		try {
			equationFactors = iohandler.getEquationFactors();
		} catch (IOException e) {
			System.out.println("Couldn't open input file");
			System.exit(1);
		}
		
		//solve equation and write to a file
		try {
			iohandler.solveEquationsAndWriteToFile(equationFactors);
		} catch (IOException e) {
			System.out.println("Couldn't open output file");
			System.exit(1);
		}
	}
}
