
import javax.swing.JRadioButton;
import javax.swing.JTextArea;


public abstract class InputSet{

	/**
	 * Created Oct 19 2008 by Jesse Fish
	 */
	private static final long serialVersionUID = 10192008;
	
	protected JTextArea input;
	protected JRadioButton select;
	protected String delim=" ";
	public InputSet(String name)
	{
		select=new JRadioButton();
		select.setText(name);
		
		
		input=new JTextArea(name+" Input");
		input.setToolTipText("input text in "+name+" and click \"Convert\"");
		input.setLineWrap(true);
		input.setEditable(false);
	}
	public abstract void convert_from(int[] input);

	public abstract int[] convert_to();

}
