
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


public class FlagArea extends JPanel{
	/**
	 * Created Oct 19 2008 by Jesse Fish
	 */
	private static final long serialVersionUID = 10192008L;
	
	public Graphics2D graphics;
	public BufferedImage currentImage;
	FlagArea() 
	{
		currentImage= new BufferedImage(500, 80, BufferedImage.TYPE_4BYTE_ABGR_PRE);
	}
	public void paintComponent (Graphics g)
	{
		super.paintComponents(g);
	
		g.setColor(Color.white);
		
		g.fillRect(0, 0,currentImage.getWidth() , currentImage.getHeight());

		g.drawImage(currentImage, 0,0, null);
		this.graphics = (Graphics2D) g;
	}
	
	public Dimension getPreferredSize()
	{
		return new Dimension ( currentImage.getWidth(),currentImage.getHeight());
	}
	
	
}
