// Author: Jesse Fish


public class ASCIISet extends InputSet{

	/**
	 * Created Oct 19 2008 by Jesse Fish
	 */

	private static final long serialVersionUID = 10192008;
	
	public ASCIISet() {
		super("ASCII");

	}

	@Override
	public void convert_from(int[] input) {
		
		char[] halfway =new char[input.length];
		
		for(int i=0;i<halfway.length;i++)
		{
			halfway[i]=(char)input[i];
		}
		String answer= new String(halfway);
		this.input.setText(answer);
	}

	@Override
	public int[] convert_to() {
		String answer=input.getText();
		char[] halfway = answer.toCharArray();
		int[] output=new int[halfway.length];
		for(int i=0;i<output.length;i++)
		{
			output[i]=(int)halfway[i];
		}
		return output;
	}

}
