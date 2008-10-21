// Author: Jesse Fish


import java.util.InputMismatchException;
import java.util.Scanner;


public class BinSet extends InputSet{
	/**
	 * Created Oct 19 2008 by Jesse Fish
	 */
	private static final long serialVersionUID = 10192008L;
	public BinSet()
	{
		super("Binary");
	}
	
	@Override
	public void convert_from(int[] input) {
		String answer="";
		for(int i=0;i<input.length;i++)
		{
			answer+=Integer.toBinaryString(Integer.valueOf(input[i]))+delim;
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
			scanner.next();
			total++;
		}
		scanner.close();
		int[] answer=new int[total];
		
		
		scanner=new Scanner(value);
		scanner.useDelimiter(delim);
		String temp;
		for(int i=0;i<total;i++)
		{
			temp=scanner.next();
			answer[i]=Integer.valueOf(temp, 2);
		}
		scanner.close();
		return answer;
		}
		catch(InputMismatchException e)
		{
			return null;
		}
		catch(NumberFormatException e)
		{
			return null;
		}
	}
}
