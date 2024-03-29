// Author: Jesse Fish


import java.util.InputMismatchException;
import java.util.Scanner;



public class IntSet extends InputSet{
	/**
	 * Created Oct 19 2008 by Jesse Fish
	 */
	private static final long serialVersionUID = 10192008;
	
	public IntSet() {
		super("Decimal");
		
	}
	
	@Override
	public void convert_from(int[] input) {
		String answer="";
		for(int i=0;i<input.length;i++)
		{
			answer+=input[i]+delim;
		}
		this.input.setText(answer);
	}

	@Override
	public int[] convert_to() {
		String value=input.getText();
		Scanner scanner=new Scanner(value);
		scanner.useDelimiter(delim);
		int total=0;
		try{
		while(scanner.hasNext())
		{
			scanner.nextInt();
			total++;
		}
		scanner.close();
		int[] answer=new int[total];
		
		
		scanner=new Scanner(value);
		scanner.useDelimiter(delim);
		for(int i=0;i<total;i++)
		{
			answer[i]=scanner.nextInt();
		}
		scanner.close();
		return answer;
		}
		catch(InputMismatchException e)
		{
			
			return null;
		}
	}

}
