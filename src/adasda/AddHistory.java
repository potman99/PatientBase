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
		private JTextArea textArea = new JTextArea();
		private JLabel lbDate, lbDescrition;
		JDateChooser calendar = new JDateChooser();
		SimpleDateFormat dcn = new SimpleDateFormat("yyyy-MM-dd");
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		private int id;

	
	public AddHistory(int id) {
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
		butAccept.setBounds(300, 580, 100, 50);
		butAccept.addActionListener(this);
		
		butCancel.setBounds(450, 580, 100, 50);
		butCancel.addActionListener(this);
		
		calendar.setBounds(170, 400, 180, 30);
		
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
			int response =op.showConfirmDialog(null, "Czy napewno chcesz wyjsc bez zapisu?" ,"Powr�t", JOptionPane.YES_NO_OPTION);
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
		
	}

}
