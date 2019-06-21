package adasda;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddUser extends JFrame implements ActionListener{

	private JLabel name, lastName, peselNumber , adress,city ,phoneNumber, back , create;
	private JTextField tName, tLastName , tPeselNumber, tAdress,tCity, tPhoneNumber;
	private JPanel labels,texts;
	private JButton butBack, butCreate;
	
	
	public AddUser() {
		
		Color bgColor = new Color(30,30,30);
		setBounds(10, 10, 800, 700);
		getContentPane().setBackground(bgColor);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		name = new JLabel("Imiê");
		lastName = new JLabel("Nazwisko");
		peselNumber = new JLabel("Pesel");
		adress = new JLabel("Adress");
		phoneNumber = new JLabel("Telefon");
		city = new JLabel("Miasto");
		this.labelLook(name);
		labelLook(lastName);
		labelLook(peselNumber);
		labelLook(adress);
		labelLook(phoneNumber);
		labelLook(city);
		
		tName=new JTextField();
		tLastName=new JTextField();
		tPeselNumber=new JTextField();
		tAdress=new JTextField();
		tPhoneNumber=new JTextField();
		tCity=new JTextField();
		
		textLook(tName);
		textLook(tLastName);
		textLook(tPeselNumber);
		textLook(tAdress);
		textLook(tPhoneNumber);
		textLook(tCity);
		
		
		labels = new JPanel();
		labels.setBackground(bgColor);
		labels.setLayout(new BoxLayout(labels, BoxLayout.Y_AXIS));
		labels.add(name);
		labels.add(Box.createVerticalStrut(30));
		labels.add(lastName);
		labels.add(Box.createVerticalStrut(30));
		labels.add(peselNumber);
		labels.add(Box.createVerticalStrut(30));
		labels.add(adress);
		labels.add(Box.createVerticalStrut(30));
		labels.add(city);
		labels.add(Box.createVerticalStrut(30));
		labels.add(phoneNumber);
		labels.setBounds(240, 160,200, 500);
		
		
		texts = new JPanel();
		texts.setBackground(bgColor);
		texts.setLayout(new BoxLayout(texts, BoxLayout.Y_AXIS));
		texts.add(tName);
		texts.add(Box.createVerticalStrut(25));
		texts.add(tLastName);
		texts.add(Box.createVerticalStrut(25));
		texts.add(tPeselNumber);
		texts.add(Box.createVerticalStrut(25));
		texts.add(tAdress);
		texts.add(Box.createVerticalStrut(25));
		texts.add(tCity);
		texts.add(Box.createVerticalStrut(25));
		texts.add(tPhoneNumber);
		texts.setBounds(400, 160,200, 500);
		
		butBack = new JButton();
		butBack.setBounds(200, 600, 30, 30);
		butBack.addActionListener(this);
		
		add(labels);
		add(texts);
		add(butBack);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==butBack)
		{
			setVisible(false);
		}
		
	}
	
	public void labelLook(JLabel label)
	{
		label.setFont(new Font("Arial" , Font.BOLD, 30));
		label.setForeground(Color.white);
		label.setSize(200, 10);
	}
	
	public void textLook(JTextField text)
	{
		text.setFont(new Font("Arial" , Font.BOLD, 20));
		text.setForeground(Color.black);
		text.setBackground(Color.lightGray);
		text.setMinimumSize(new Dimension(180, 40));
		text.setPreferredSize(new Dimension(180, 40));
		text.setMaximumSize(new Dimension(180, 40));
		
	}
	

}
