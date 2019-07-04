package adasda;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddUser extends JFrame implements ActionListener,LabelLook{

	private JLabel name, lastName, peselNumber , adress,city ,phoneNumber, icon , addUser;
	private JTextField tName, tLastName , tPeselNumber, tAdress,tCity, tPhoneNumber;
	private JPanel labels,texts, head;
	private JButton butBack, butCreate;
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	
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
		
		
		ImageIcon i2 = new ImageIcon("buttons\\back.png");
		Image img2 = i2.getImage();
		Image new_img2 = img2.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		i2 = new ImageIcon(new_img2);
		butBack = new JButton("Powrót",i2);
		butBack.addActionListener(this);
		butBack.setBounds(200, 600, 150, 50);
		
		
		ImageIcon i3 = new ImageIcon("buttons\\plus.png");
		Image img3 = i3.getImage();
		Image new_img3 = img3.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		i3 = new ImageIcon(new_img3);
		butCreate = new JButton("Dodaj",i3);
		butCreate.addActionListener(this);
		butCreate.setBounds(400, 600, 150, 50);
		
		ImageIcon i4 = new ImageIcon("buttons\\add.png");
		Image img4 = i4.getImage();
		Image new_img4 = img4.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
		i3 = new ImageIcon(new_img4);
		icon = new JLabel(i4);
		icon.setBounds(50, 20, 100, 100);
		
		head = new JPanel();
		head.setLayout(new FlowLayout(FlowLayout.LEFT));
		head.add(icon);
		head.setBounds(0,20,800,80);
		
		add(head);
		add(butCreate);
		add(labels);
		add(texts);
		add(butBack);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==butBack)
		{
			setVisible(false);
			Home home = new Home();
			home.setVisible(true);
		}
		
		if(e.getSource()==butCreate)
		{
			conn=DatabaseConnection.connection();
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate("INSERT INTO patients (name,lastname) values('"+tName.getText()+"','"+tLastName.getText()+"')");
				JOptionPane.showMessageDialog(null, "Dodano Pacjenta");
				this.setVisible(false);
				Home home = new Home();
				home.setVisible(true);
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
