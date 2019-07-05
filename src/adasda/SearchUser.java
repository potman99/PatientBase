package adasda;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchUser extends JFrame implements ActionListener,LabelLook{
	
	private JLabel name, lastName, peselNumber , adress,city ,phoneNumber, icon , addUser;
	private JTextField tName, tLastName , tPeselNumber, tAdress,tCity, tPhoneNumber;
	private JPanel labels,texts;
	private JButton butSearch=new JButton("Szukaj");
	private JButton butBack = new JButton("Powrót");
	private JFrame frame;
	private String texName,texLastName,texPeselNumber,texAdress,texCity,texPhoneNumber;
	private String sql = "SELECT * FROM patients WHERE ";
	
	
	
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

	public SearchUser() {
	
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
		labelLook(name);
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
		labels.setBounds(240, 120,200, 400);
		
		
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
		texts.setBounds(400, 120,200, 400);
		
		butSearch.setBounds(300, 580, 100, 50);
		butSearch.addActionListener(this);
		
		butBack.setBounds(450, 580, 100, 50);
		butBack.addActionListener(this);
		
		conn=DatabaseConnection.connection();
		
		
		add(texts);
		add(labels);
		add(butSearch);
		add(butBack);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==butSearch)
		{
			try {
				stmt=conn.createStatement();
				checkTextFields();
				frame = new ShowUsers(sql);
				frame.setVisible(true);
				System.out.println(sql);


				
				
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		if(e.getSource()==butBack)
		{
			frame = new Home();
			frame.setVisible(true);
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
	
	public void checkTextFields()
	{
		if(!tName.getText().isEmpty())
		{
			texName=tName.getText();
			sql+="name='"+texName+"'";
		}
		
		if(!tLastName.getText().isEmpty())
		{
			texLastName=tLastName.getText();
			if(tName.getText().isEmpty())
			{
				sql+="lastname='"+texLastName+"'";
			}
			
			else
			sql+="AND lastname='"+texLastName+"'";

			
		}
		
		if(!tAdress.getText().isEmpty())
		{
			texAdress=tAdress.getText();
			if(tName.getText().isEmpty()&&tLastName.getText().isEmpty())
			{
				sql+="adress='"+texAdress+"'";
			}
			
			else
			sql+="AND adress='"+texAdress+"'";

		}
		
		if(!tCity.getText().isEmpty())
		{
			texCity=tCity.getText();
			if(tName.getText().isEmpty()&&tLastName.getText().isEmpty()&&tAdress.getText().isEmpty())
			{
				sql+="city='"+texCity+"'";
			}
			
			else
			sql+="AND city='"+texCity+"'";

		}
		
		if(!tPeselNumber.getText().isEmpty())
		{
			texPeselNumber=tPeselNumber.getText();
			if(tName.getText().isEmpty()&&tLastName.getText().isEmpty()&&tCity.getText().isEmpty()&&tAdress.getText().isEmpty())
			{
				sql+="pesel='"+texPeselNumber+"'";
			}
			
			else
			sql+="AND pesel='"+texPeselNumber+"'";

		}
		
		if(!tPhoneNumber.getText().isEmpty())
		{
			texPhoneNumber=tPhoneNumber.getText();
			if(tName.getText().isEmpty()&&tLastName.getText().isEmpty()&&tCity.getText().isEmpty()&&tPeselNumber.getText().isEmpty()&&tAdress.getText().isEmpty())
			{
				sql+="phone='"+texPhoneNumber+"'";
			}
			
			else
			sql+="AND phone='"+texPhoneNumber+"'";

		}
		
	}

}
