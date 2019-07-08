package adasda;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

public class DisplayUser extends JFrame implements LabelLook,ActionListener,MouseListener{
	
	private JLabel name, lastName, peselNumber , adress,city ,phoneNumber;
	private JLabel pName , pLastName, pPeselNumber, pAdress, pCity, pPhoneNumber;
	private JPanel mainPanel = new JPanel();
	private JPanel panel1 = new JPanel();
	private JPanel panel2 = new JPanel();
	private JButton butBack = new JButton("Powrót");
	private JButton butAdd = new JButton("Dodaj");
	private JTable tab = new JTable();
	
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
	
	pName=new JLabel();
	pLastName=new JLabel();
	pPeselNumber=new JLabel();
	pAdress=new JLabel();
	pCity=new JLabel();
	pPhoneNumber=new JLabel();
	
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
	
	JPanel panel = new JPanel();
	panel.setLayout(new GridLayout());
	JScrollPane js = new JScrollPane(tab);
	panel.add(js);
	panel.setBackground(bgColor);
	tab.addMouseListener(this);
	panel.setBounds(140, 350, 550, 100);
	
	panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
	panel1.setBackground(Color.LIGHT_GRAY);
	panel1.add(name);
	panel1.add(Box.createVerticalStrut(20));
	panel1.add(lastName);
	panel1.add(Box.createVerticalStrut(20));
	panel1.add(peselNumber);
	panel1.add(Box.createVerticalStrut(20));
	panel1.add(adress);
	panel1.add(Box.createVerticalStrut(20));
	panel1.add(city);
	panel1.add(Box.createVerticalStrut(20));
	panel1.add(phoneNumber);
	
	panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
	panel2.setBackground(Color.LIGHT_GRAY);
	panel2.add(pName);
	panel2.add(Box.createVerticalStrut(20));
	panel2.add(pLastName);
	panel2.add(Box.createVerticalStrut(20));
	panel2.add(pPeselNumber);
	panel2.add(Box.createVerticalStrut(20));
	panel2.add(pAdress);
	panel2.add(Box.createVerticalStrut(20));
	panel2.add(pCity);
	panel2.add(Box.createVerticalStrut(20));
	panel2.add(pPhoneNumber);
	
	mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
	mainPanel.add(panel1);
	mainPanel.add(panel2);
	mainPanel.setBackground(Color.LIGHT_GRAY);
	mainPanel.setBounds(140, 40, 550, 280);
	
	
	butBack.setBounds(300, 580, 100, 50);
	butBack.addActionListener(this);

	add(butBack);
	add(mainPanel);
	add(panel);
	
	displayTable();
	}
	
	public void show(int row)
	{
		try {
			stmt=conn.createStatement();
			String getName = "SELECT * FROM patients WHERE id='"+row+"' ";
			rs=stmt.executeQuery(getName);
			if(rs.next())
			{
				pName.setText(rs.getString("name"));
				pLastName.setText(rs.getString("lastname"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showInternalMessageDialog(null, "Brak po³¹czenia z baz¹ danych");
			e.printStackTrace();
		}

		
	}
	
	public void displayTable()
	{
		
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery("SELECT* FROM history");
			tab.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void labelLook(JLabel label) {
		label.setFont(new Font("Arial" , Font.BOLD, 20));
		label.setForeground(Color.BLACK);
		label.setSize(200, 10);
	}

	@Override
	public void textLook(JTextField text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==butBack)
		{
			setVisible(false);
			
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
