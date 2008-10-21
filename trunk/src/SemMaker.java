
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;


public class SemMaker extends JFrame{
	/**
	 * Created Oct 19 2008 by Jesse Fish
	 */
	private static final long serialVersionUID = 10192008L;
	Hashtable <Integer,BufferedImage>images;
	public boolean numbers=false;
	public String translation;
	private SemSet semSet;
	private Converter converter;
	SemMaker(SemSet in,Converter con)
	{
		converter=con;
		semSet=in;
		images=in.semlookup;
		this.setLayout(new FlowLayout());
		BufferedImage temp;
		Graphics g;
		SemButton semButton;
		for(int i=65;i<91;i++)
		{
			temp=new BufferedImage(80, 80, BufferedImage.TYPE_4BYTE_ABGR_PRE);
			g=temp.getGraphics();
			g.setColor(Color.white);
			g.fillRect(0, 0, 80, 80);

			g.setColor(Color.black);
			g.drawImage(images.get(i), 0,0,null);
			String writeString=String.valueOf((char)i);
			if(i<74)
			{
				writeString+="/"+(i-64);
			}
			if(i==75)
			{
				writeString+="/"+0;
			}
			if(i==74)
			{
				writeString+="/Letters";
			}
			g.drawString(writeString, 5, 10);
			ImageIcon tempicon=new ImageIcon(temp);
			semButton=new SemButton(i,tempicon);
			semButton.addActionListener(new SemButtonHandler());
			this.add(semButton);
		}

		//add space
		int j=32;
		temp=new BufferedImage(80, 80, BufferedImage.TYPE_4BYTE_ABGR_PRE);
		g=temp.getGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, 80, 80);
		g.setColor(Color.black);
		g.drawImage(images.get(j), 0,0,null);
		String writeString="ready";
		g.drawString(writeString, 5, 10);
		ImageIcon tempicon=new ImageIcon(temp);
		semButton=new SemButton(j,tempicon);
		semButton.addActionListener(new SemButtonHandler());
		this.add(semButton);

		j=-1;
		temp=new BufferedImage(80, 80, BufferedImage.TYPE_4BYTE_ABGR_PRE);
		g=temp.getGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, 80, 80);
		g.setColor(Color.black);
		g.drawImage(images.get(j), 0,0,null);
		writeString="numbers";
		g.drawString(writeString, 5, 10);
		tempicon=new ImageIcon(temp);
		semButton=new SemButton(j,tempicon);
		semButton.addActionListener(new SemButtonHandler());
		this.add(semButton);

		j=-2;
		temp=new BufferedImage(80, 80, BufferedImage.TYPE_4BYTE_ABGR_PRE);
		g=temp.getGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, 80, 80);
		g.setColor(Color.black);
		g.drawImage(images.get(j), 0,0,null);
		writeString="cancel";
		g.drawString(writeString, 5, 10);
		tempicon=new ImageIcon(temp);
		semButton=new SemButton(j,tempicon);
		semButton.addActionListener(new SemButtonHandler());
		this.add(semButton);

		j=-3;
		temp=new BufferedImage(80, 80, BufferedImage.TYPE_4BYTE_ABGR_PRE);
		g=temp.getGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, 80, 80);
		g.setColor(Color.black);
		g.drawImage(images.get(j), 0,0,null);
		writeString="error";
		g.drawString(writeString, 5, 10);
		tempicon=new ImageIcon(temp);
		semButton=new SemButton(j,tempicon);
		semButton.addActionListener(new SemButtonHandler());
		this.add(semButton);

		this.setSize(650, 605);
		JButton tempbut=new JButton("Convert");
		tempbut.addActionListener(new ConvertButtonHandler());
		this.add(tempbut);

		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	private class ConvertButtonHandler implements ActionListener{

		public void actionPerformed(ActionEvent e) {

			int flagcount=0;			
			String value=translation;
			Scanner scanner=new Scanner(value);
			scanner.useDelimiter(semSet.delim);
			try{
				while(scanner.hasNext())
				{
					flagcount++;
					scanner.next();
				}
				scanner.close();

				semSet.flags=new int[flagcount];

				scanner=new Scanner(value);
				scanner.useDelimiter(semSet.delim);
				for(int i=0;i<flagcount;i++)
				{
					semSet.flags[i]=scanner.nextInt();
				}
				scanner.close();
			}
			catch(InputMismatchException exception)
			{
				exception.printStackTrace();
			}
			
			converter.convert();
			setVisible(false);
		}

	}
	private class SemButtonHandler implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			int temp=((SemButton)(e.getSource())).value;
			if(temp==-1)
			{
				numbers=true;
			}
			else if(temp==74&&numbers)
			{
				numbers=false;
			}
			else
			{
				if(temp<0)
				{
					temp=32;
				}
				else if(numbers)
				{
					if(temp<74&&temp>64)
					{
						temp-=16;
					}
					else if(temp==75)
					{
						temp=48;
					}
					else
					{
						return;
					}
				}
				else
				{
					//do nothing
				}
				translation+=temp+semSet.delim;
			}
		}

	}
}
