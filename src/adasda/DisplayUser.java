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
	private JButton butDelete = new JButton("Usuñ");
	private JTable tab = new JTable();
	private JFrame frame;
	private int id=0;
	
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	public DisplayUser(int id ) {
		this.id=id;
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
	
	
	butBack.setBounds(240, 580, 100, 50);
	butBack.addActionListener(this);
	
	butAdd.setBounds(360, 580, 100, 50);
	butAdd.addActionListener(this);
	
	butDelete.setBounds(480, 580, 100, 50);
	butDelete.addActionListener(this);
	
	add(butDelete);
	add(butBack);
	add(butAdd);
	add(mainPanel);
	add(panel);
	
	displayTable(id);
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
				pPeselNumber.setText(rs.getString("pesel"));
				pAdress.setText(rs.getString("adres"));
				pCity.setText(rs.getString("city"));
				pPhoneNumber.setText(rs.getString("phone"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showInternalMessageDialog(null, "Brak po³¹czenia z baz¹ danych");
			e.printStackTrace();
		}

		
	}
	
	public void displayTable(int row)
	{
		
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery("SELECT idvisit AS 'ID_Wizyty', id AS 'ID_Pacjenta' , date AS 'Data' , description AS 'Opis' FROM history WHERE id='"+row+"' ");
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
		
		if(e.getSource()==butAdd)
		{
			setVisible(false);
			frame = new AddHistory(id);
			frame.setVisible(true);
		}
		
		if(e.getSource()==butDelete)
		{
			JOptionPane op = new JOptionPane();
			int response = op.showConfirmDialog(null, "Czy napewno chcesz usun¹æ tego pacjenta?","Usuñ pacjenta",JOptionPane.YES_NO_OPTION);
			if(response==JOptionPane.YES_OPTION)
			{
				conn = DatabaseConnection.connection();
				try {
					stmt = conn.createStatement();
					stmt.executeUpdate("DELETE FROM patients WHERE id='"+id+"'");
					JOptionPane.showMessageDialog(null, "Usuniêto pacjenta");
					setVisible(false);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(e.getSource()==tab)
		{
			int row =tab.getSelectedRow();
			int newId= (int) tab.getModel().getValueAt(row, 0);
			AddHistory ah = new AddHistory(id);
			ah.disableEditing();
			ah.showHistory(newId);
			ah.addUpdate(newId);
			ah.deleteRecord(newId);
			ah.setVisible(true);
		}
		
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
