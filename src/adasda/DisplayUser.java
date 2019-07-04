package adasda;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DisplayUser extends JFrame implements LabelLook{
	
	private JLabel name, lastName, peselNumber , adress,city ,phoneNumber;
	private JLabel pName , pLastName, pPeselNumber, pAdress, pCity, pPhoneNumber;
	private JPanel panel = new JPanel();
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	public DisplayUser() {
	Color bgColor = new Color(30,30,30);
	setBounds(10, 10, 800, 700);
	getContentPane().setBackground(bgColor);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLayout(null);
	conn = DatabaseConnection.connection();
	name=new JLabel("Imie");
	lastName=new JLabel("Nazwisko");
	peselNumber=new JLabel("Pesel");
	adress=new JLabel("Adres");
	city=new JLabel("Miasto");
	phoneNumber=new JLabel("Telefon");
	
	labelLook(adress);
	labelLook(city);
	labelLook(lastName);
	labelLook(name);
	labelLook(peselNumber);
	labelLook(phoneNumber);
	
	labelLook(pAdress);
	labelLook(pCity);
	labelLook(pLastName);
	labelLook(pName);
	labelLook(pPeselNumber);
	labelLook(pPhoneNumber);
	
	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	panel.setBounds(100, 50, 600, 500);
	panel.setBackground(Color.LIGHT_GRAY);
	panel.add(name);
	panel.add(Box.createVerticalStrut(30));
	panel.add(lastName);
	panel.add(Box.createVerticalStrut(30));
	panel.add(peselNumber);
	panel.add(Box.createVerticalStrut(30));
	panel.add(adress);
	panel.add(Box.createVerticalStrut(30));
	panel.add(city);
	panel.add(Box.createVerticalStrut(30));
	panel.add(phoneNumber);
	

	
	add(panel);
	}
	
	public void show(int row)
	{
		try {
			stmt=conn.createStatement();
			String getName = "SELECT * FROM patients WHERE id='"+(row+1)+"' ";
			rs=stmt.executeQuery(getName);
			if(rs.next())
			{
				

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	@Override
	public void labelLook(JLabel label) {
		label.setFont(new Font("Arial" , Font.BOLD, 30));
		label.setForeground(Color.white);
		label.setSize(200, 10);
	}

	@Override
	public void textLook(JTextField text) {
		// TODO Auto-generated method stub
		
	}

}
