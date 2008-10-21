// Author: Jesse Fish

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class SemButton extends JButton{
	/**
	 * Created Oct 20 2008 by Jesse Fish
	 */
	private static final long serialVersionUID = 10202008L;
	public int value;
	SemButton(int in, ImageIcon tempicon)
	{
		super(tempicon);
		value=in;
	}
}
