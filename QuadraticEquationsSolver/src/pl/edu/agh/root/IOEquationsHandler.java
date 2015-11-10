package pl.edu.agh.root;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.ListIterator;

public class IOEquationsHandler {
	Path inPath;
	Path outPath;
	BufferedWriter outFile;
	
	public IOEquationsHandler(String in, String out) throws InvalidPathException{
		this.inPath = Paths.get(in);
		this.outPath = Paths.get(out);		
	}
	
	public LinkedList<String> getEquationFactors() throws IOException{
		
		try(BufferedReader inFile = Files.newBufferedReader(inPath)){
			
			LinkedList<String> equationFactors = new LinkedList<>();
			String equation = null;
			
			while((equation = inFile.readLine()) != null)
				equationFactors.addLast(equation);
			
			return equationFactors;
		}
		
	}
	
	public void solveEquationsAndWriteToFile(LinkedList<String> equationFactors) throws IOException{
		
		try(BufferedWriter outFile = Files.newBufferedWriter(outPath)){
			ListIterator<String> it = equationFactors.listIterator();
			String equation = null;
			String result = null;
			
			while (it.hasNext()){
				equation = it.next();
				String [] factors = equation.split(" +");
				
				if(factors.length == 3){
					result = parseFactorsSolveAndGetResult(factors);
				}
				else
					result = "Error, not enough factors for quadratic equation";
				
				outFile.write(equation + "\tSolution: >>>" + result);
				outFile.newLine();
			}
		}
	}
	
	private String parseFactorsSolveAndGetResult(String[] factors){
		String result = null;
		
		try{
			double a = Double.parseDouble(factors[0]);
			double b = Double.parseDouble(factors[1]);
			double c = Double.parseDouble(factors[2]);	
			result = solveAndGetResult(a,b,c);					    
		} catch (NumberFormatException e){
			result = "Not every factor is double";
		}
		
		return result;
	}
	
	
	private String solveAndGetResult(double a, double b, double c){
		String result = null;
		String pattern = "###.###";
		DecimalFormat decimalFormat = new DecimalFormat(pattern);
		double delta = Math.pow(b, 2) - (4 * a * c);
		if (delta < 0)
			result = "There is no solutions";
		else if(delta == 0){
			double solution = -b/(2*a);
			result = Double.toString(solution);
		}
		else {
			double solution1 = (-b + Math.sqrt(delta)) / (2 * a);
		    double solution2 = (-b - Math.sqrt(delta)) / (2 * a);
		    result = decimalFormat.format(solution1) + "  " + decimalFormat.format(solution2);
		}
		
		return result;
	}
	
	
}


