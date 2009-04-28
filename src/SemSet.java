// Author: Jesse Fish


import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JScrollPane;


public class SemSet extends InputSet {
	/**
	 * Created Oct 19 2008 by Jesse Fish
	 */
	private static final long serialVersionUID = 10192008L;
	private final String fileName="semaphore.txt";
	public Hashtable <Integer,BufferedImage> semlookup;
	public int[] flags;
	public FlagArea flagArea;
	private BufferedImage flagMessage;
	public JScrollPane drawScroll;


	public SemSet(Converter conv)
	{
		super("Semaphore");
		semlookup=new Hashtable<Integer,BufferedImage> ();

		try {
			Scanner input;
			input = new Scanner(Converter.class.getResource(fileName).openStream());

			int temp=0;
			String tempstring="";
			while(input.hasNext())
			{
				temp=input.nextInt();
				tempstring=input.next();
				tempstring="80px/"+tempstring;
				BufferedImage tempimage;
				try {
					tempimage = ImageIO.read(Converter.class.getResource(tempstring));
					//tempimage = ImageIO.read(new URL(conv.getCodeBase(),tempstring));

					semlookup.put(temp, tempimage);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*catch (FileNotFoundException e) {

			e.printStackTrace();
		}*/

		flagArea=new FlagArea();
		flagMessage=new BufferedImage(80*6, 80, BufferedImage.TYPE_4BYTE_ABGR_PRE);
		drawScroll=new JScrollPane(flagArea);
		drawScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		drawScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

	}

	@Override
	public void convert_from(int[] input) {
		boolean numbers=false;
		boolean tempnum=false;
		int swaps=0;
		for(int i=0;i<input.length;i++)
		{
			int temp=input[i];
			if(temp>47&&temp<58&&!tempnum)
			{
				swaps++;
				tempnum=true;
			}
			else if(tempnum)
			{
				swaps++;
				tempnum=false;
			}
		}
		int numflags=input.length+swaps;
		flagMessage=new BufferedImage(80*6+40, 80*(numflags/6+1), BufferedImage.TYPE_4BYTE_ABGR_PRE);
		flags=input;
		int xpos=0;
		int ypos=0;
		Graphics tempgraphics;
		tempgraphics=flagMessage.getGraphics();
		tempgraphics.setColor(Color.white);
		tempgraphics.fillRect(0,0,flagMessage.getWidth(),flagMessage.getHeight());

		tempgraphics.setColor(Color.black);
		for(int i=0;i<input.length;i++)
		{
			int temp=input[i];
			if (temp>96)
			{
				temp-=32;
			}
			if(temp==24)
			{
				tempgraphics.drawImage(semlookup.get(temp), xpos, ypos, null);
				tempgraphics.drawString("cancel", xpos+5, ypos+10);
				xpos+=80;
			}
			else if(temp==32)
			{
				tempgraphics.drawImage(semlookup.get(temp), xpos, ypos, null);
				tempgraphics.drawString("space", xpos+5, ypos+10);
				xpos+=80;
			}
			else if(temp>47&&temp<58)
			{
				if(!numbers)
				{
					tempgraphics.drawImage(semlookup.get(-1), xpos, ypos, null);
					tempgraphics.drawString("numbers", xpos+5, ypos+10);
					xpos+=80;

					if(xpos>80*5)
					{
						xpos=0;
						ypos+=80;
					}
					numbers=true;
				}

				int trans=temp+16;
				if(trans<65)
				{
					trans=75;
				}
				tempgraphics.drawImage(semlookup.get(trans), xpos, ypos, null);
				tempgraphics.drawString(String.valueOf((char)temp), xpos+5, ypos+10);
				xpos+=80;
			}
			else
			{
				if(numbers)
				{
					tempgraphics.drawImage(semlookup.get(-2), xpos, ypos, null);
					tempgraphics.drawString("Letters", xpos+5, ypos+10);
					xpos+=80;
					numbers=false;
					if(xpos>80*5)
					{
						xpos=0;
						ypos+=80;
					}
				}
				tempgraphics.drawImage(semlookup.get(temp), xpos, ypos, null);
				tempgraphics.drawString(String.valueOf((char)temp), xpos+5, ypos+10);
				xpos+=80;
			}
			if(xpos>80*5)
			{
				xpos=0;
				ypos+=80;
			}
		}
		flagArea.currentImage=flagMessage;

		flagArea.revalidate();
	}

	@Override
	public int[] convert_to() {

		return flags;
	}

}
