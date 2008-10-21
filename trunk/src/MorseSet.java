
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.Scanner;


public class MorseSet extends InputSet {
	/**
	 * Created Oct 19 2008 by Jesse Fish
	 */
	private static final long serialVersionUID = 10192008L;
	private final String fileName="morsecode.txt";
	private Hashtable <Integer,String> morseLookup;
	private Hashtable <String,Integer> reverseLookup;

	public MorseSet(Converter conv)
	{
		super("Morse Code");
		morseLookup=new Hashtable<Integer,String> ();
		reverseLookup=new Hashtable<String,Integer> ();
		Scanner input;
		try {
			if(conv.applet)
			{
				input = new Scanner((new URL(conv.getCodeBase(),fileName)).openStream());
			}
			else
			{
				input = new Scanner((new File(fileName)));
			}
			
			int temp=0;
			String tempstring="";
			while(input.hasNext())
			{
				temp=input.nextInt();
				tempstring=input.next();
				morseLookup.put(temp, tempstring);
				reverseLookup.put(tempstring, temp);
			}

		} 
		catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void convert_from(int[] input) {
		String answer="";
		for(int i=0;i<input.length;i++)
		{
			int temp=input[i];
			if (temp>96)
			{
				temp-=32;
			}

			if(temp==32)
			{
				answer+=" ";
			}
			else
			{
				answer+=morseLookup.get(temp)+delim;
			}
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
			for(int i=0;i<total;i++)
			{
				Integer a=this.reverseLookup.get(scanner.next());
				if(a==null)
				{
					answer[i]=32;
				}
				else{
					answer[i]=a;

				}

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
