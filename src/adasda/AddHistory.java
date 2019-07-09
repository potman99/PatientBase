package adasda;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import com.toedter.calendar.JDateChooser;

public class AddHistory extends JFrame implements ActionListener{

		private JButton butAccept = new JButton("Akceptuj");
		private JButton butCancel = new JButton("Anuluj");
		private JButton butNewAccept = new JButton("Edytuj");
		private JButton butEdit;
		private JButton butDelete = new JButton("Usuñ");
		private JTextArea textArea = new JTextArea();
		private JLabel lbDate, lbDescrition;
		JDateChooser calendar = new JDateChooser();
		SimpleDateFormat dcn = new SimpleDateFormat("yyyy-MM-dd");
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		private int id;
		

	
	public AddHistory(int id ) {
		this.id=id;
		Color bgColor = new Color(30,30,30);
		setBounds(10, 10, 800, 700);
		getContentPane().setBackground(bgColor);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		textArea.setBounds(100, 65, 600, 300);
		textArea.setFont(new Font("Arial" , Font.PLAIN, 16));
		lbDate = new JLabel("Data");
		lbDescrition = new JLabel("Opis wizyty");
		lbDescrition.setFont(new Font("Arial" , Font.BOLD, 20));
		lbDescrition.setForeground(Color.WHITE);
		lbDate.setFont(new Font("Arial" , Font.BOLD, 20));
		lbDate.setForeground(Color.WHITE);
		
		lbDescrition.setBounds(100, 30, 200, 30);
		lbDate.setBounds(100, 400, 100, 30);
		butAccept.setBounds(240, 580, 100, 50);
		butAccept.addActionListener(this);
		
		butCancel.setBounds(360, 580, 100, 50);
		butCancel.addActionListener(this);
		
		butDelete.setBounds(480, 580, 100, 50);
		
		
		calendar.setBounds(170, 400, 180, 30);
		
		butNewAccept.setBounds(240, 580, 100, 50);
		butNewAccept.addActionListener(this);
		
		
		
		add(lbDate);
		add(calendar);
		add(butAccept);
		add(butCancel);
		add(textArea);
		add(lbDescrition);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==butCancel)
		{
			JOptionPane op = new JOptionPane();
			int response =op.showConfirmDialog(null, "Czy napewno chcesz wyjsc bez zapisu?" ,"Powrót", JOptionPane.YES_NO_OPTION);
			if(response==op.YES_OPTION)
			{
				setVisible(false);
				
			}
			
		}
		if(e.getSource()==butAccept)
		{
			conn = DatabaseConnection.connection();
			try {
				stmt = conn.createStatement();
				String date = dcn.format(calendar.getDate());
				stmt.executeUpdate("INSERT INTO history (id,date,description) VALUES ('"+id+"','"+date+"','"+textArea.getText()+"') ");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			JOptionPane.showMessageDialog(null, "Dodano wpis");
			setVisible(false);
		}
		if(e.getSource()==butEdit)
		{
			textArea.setEditable(true);
			remove(butAccept);
			remove(butEdit);
			add(butNewAccept);
			repaint();
			
			
		}
		
		
		
	}
	
	public void disableEditing()
	{
		textArea.setEditable(false);
		butEdit = new JButton("Edycja");
		butEdit.setBounds(240, 580, 100, 50);
		add(butEdit);
		butEdit.addActionListener(this);
		remove(butAccept);
		add(butDelete);
		repaint();
	}
	
	public void showHistory(int row)
	{
		conn = DatabaseConnection.connection();
		try {
			stmt=conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM history WHERE idvisit='"+row+"'");
			if(rs.next())
			{
				textArea.setText(rs.getString("description"));
				calendar.setDate(rs.getDate("date"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void addUpdate(int row)
	{
		butNewAccept.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				conn = DatabaseConnection.connection();
				try {
					stmt = conn.createStatement();
					String date = dcn.format(calendar.getDate());
					stmt.executeUpdate("UPDATE history SET date='"+date+"' , description='"+textArea.getText()+"' WHERE idvisit='"+row+"'");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(null, "Edytowano wpis");
				setVisible(false);
				
			}
		});
		
		
	}
	
	public void deleteRecord(int row)
	{
		butDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane op = new JOptionPane();
				int response = op.showConfirmDialog(null, "Czy napewno chcesz usun¹æ ten wpis?","Skasuj Wpis",JOptionPane.YES_NO_OPTION);
				if(response==JOptionPane.YES_OPTION)
				{
					conn = DatabaseConnection.connection();
					try {
						stmt = conn.createStatement();
						stmt.executeUpdate("DELETE FROM history WHERE idvisit='"+row+"'");
						JOptionPane.showMessageDialog(null, "Usuniêto wpis");
						setVisible(false);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
	}

}
