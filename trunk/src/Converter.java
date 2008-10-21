
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

//import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
//import javax.swing.JLabel;
import javax.swing.JPanel;
//import javax.swing.border.TitledBorder;


public class Converter extends Applet{
	/**
	 * Created Oct 19 2008 by Jesse Fish
	 */
	private static final long serialVersionUID = 10192008L;
	public static final int width=650;
	public static final int heigth=750;
	public boolean applet=true;
	private IntSet intSet;
	private ASCIISet asciiSet;
	private HexSet hexSet;
	private BinSet binSet;
	private MorseSet morseSet;
	private SemSet semSet;
	private InputSet[] allInputs;

	private JPanel buttons;
	private JPanel text;
	//private JPanel mainpanel;
	private ButtonGroup selectgroup;

	private JButton convertbutton;
	private SemMaker semMaker;
	public void init()
	{
	//	mainpanel=new JPanel(new BorderLayout());
		//this.add(mainpanel);
		
		this.setLayout(new BorderLayout());
		convertbutton=new JButton("Convert");
		allInputs=new InputSet[6];
		selectgroup=new ButtonGroup();


		GridLayout layout=new GridLayout(7,1);
		layout.setVgap(5);
		buttons=new JPanel(layout);
		text=new JPanel(layout);

	//	mainpanel.add(buttons,BorderLayout.WEST);
	//	mainpanel.add(text,BorderLayout.CENTER);
		
		this.add(buttons,BorderLayout.WEST);
		this.add(text,BorderLayout.CENTER);
			
		//buttons.add(new JLabel("  Input Type"));
		//text.add(new Box(0));
		
		JScrollPane tempscroll;
		
		//add ascii
		asciiSet=new ASCIISet();
		asciiSet.select.setSelected(true);
		selectgroup.add(asciiSet.select);
		buttons.add(asciiSet.select);
		asciiSet.input.setEditable(true);
		tempscroll=new JScrollPane(asciiSet.input);
		tempscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		text.add(tempscroll);


		//add decimal 
		intSet=new IntSet();
		selectgroup.add(intSet.select);
		asciiSet.select.setSelected(false);
		buttons.add(intSet.select);
		tempscroll=new JScrollPane(intSet.input);
		tempscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		text.add(tempscroll);

		//add hex
		hexSet=new HexSet();
		selectgroup.add(hexSet.select);
		hexSet.select.setSelected(false);
		buttons.add(hexSet.select);
		tempscroll=new JScrollPane(hexSet.input);
		tempscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		text.add(tempscroll);
		
		//add bin
		binSet=new BinSet();
		selectgroup.add(binSet.select);
		binSet.select.setSelected(false);
		buttons.add(binSet.select);
		tempscroll=new JScrollPane(binSet.input);
		tempscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		text.add(tempscroll);
		
		//add morse
		morseSet=new MorseSet(this);
		selectgroup.add(morseSet.select);
		morseSet.select.setSelected(false);
		buttons.add(morseSet.select);
		tempscroll=new JScrollPane(morseSet.input);
		tempscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		text.add(tempscroll);
		
		//add sem
		semSet=new SemSet(this);
		selectgroup.add(semSet.select);
		semSet.select.setSelected(false);
		buttons.add(semSet.select);
		text.add(semSet.drawScroll);
		semMaker=new SemMaker(semSet,this);
		
		
		allInputs[0]=asciiSet;
		allInputs[1]=intSet;
		allInputs[2]=hexSet;
		allInputs[3]=binSet;
		allInputs[4]=morseSet;
		allInputs[5]=semSet;
		
		convertbutton.addActionListener(new ConvertHandler());

		buttons.add(convertbutton);


		for(int i=0;i<allInputs.length;i++)
		{
			allInputs[i].select.addItemListener(new RadioButtonHandler());
		}
	
		//mainpanel.setBorder(new TitledBorder("Select the input type and Convert fromt there"));
		setSize(width,heigth);
	}
	public void refresh()
	{
		semSet.drawScroll.repaint();
	}
	public void convert()
	{
		int [] new_value=null;
		for(int i=0;i<allInputs.length;i++)
		{
			if(allInputs[i].select.isSelected())
			{
				new_value=allInputs[i].convert_to();
			}
		}
		if(new_value!=null)
		{
			for(int i=0;i<allInputs.length;i++)
			{
				allInputs[i].convert_from(new_value);
			}
		}
		else
		{
			System.out.println("invalid input for that type");
		}
		refresh();
	}
	private class ConvertHandler implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			convert();
		}

	}
	private class RadioButtonHandler implements ItemListener
	{
		public void itemStateChanged(ItemEvent event)
		{
			for(int i=0;i<allInputs.length;i++)
			{
				if(event.getSource().equals(allInputs[i].select))
				{
					allInputs[i].input.setEditable(true);
				}
				else
				{
					allInputs[i].input.setEditable(false);
				}
			}
			if(semSet.select.isSelected())
			{
				semMaker.setVisible(true);
				semMaker.numbers=false;
				semMaker.translation="";
			}
		}
	}
	
	public static void main(String args[])
	{
		JFrame window = new JFrame("Converter");
		Converter conv = new Converter();

		conv.applet=false;
		conv.init();
		window.add("Center", conv);
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(width,heigth);
		window.setVisible(true);
	}
}
