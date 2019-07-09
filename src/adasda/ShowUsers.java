package adasda;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import net.proteanit.sql.DbUtils;

public class ShowUsers extends JFrame implements ActionListener,MouseListener{
	
	JTable tab = new JTable();
	JButton butBack = new JButton("Powr�t");
	JLabel head = new JLabel();
	Connection con= null;
	Statement stmt= null;
	ResultSet rs = null;
	
	public ShowUsers(String sql) 
	{
		
	Color bgColor = new Color(30,30,30);
	setBounds(10, 10, 800, 700);
	getContentPane().setBackground(bgColor);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLayout(null);
	
	
	butBack.setBounds(350, 580, 100, 50);	
	butBack.addActionListener(this);
	
	
	con=DatabaseConnection.connection();
	JPanel panel = new JPanel();
	panel.setLayout(new GridLayout());
	JScrollPane js = new JScrollPane(tab);
	panel.add(js);
	panel.setBackground(bgColor);
	tab.addMouseListener(this);
	
	
	try {
		stmt=con.createStatement();
		rs=stmt.executeQuery(sql);
		tab.setModel(DbUtils.resultSetToTableModel(rs));
	} catch (SQLException e) {
		
		JOptionPane.showInternalMessageDialog(null, "Brak po��czenia z baz� danych");
		e.printStackTrace();
	}

	panel.setBounds(100, 50, 600, 500);
	add(panel);
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
		
		
	}




	@Override
	public void mouseClicked(MouseEvent e) {
		
	
		
	}




	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource()==tab)
		{
			int row =tab.getSelectedRow();
			int id= (int) tab.getModel().getValueAt(row, 0);
			DisplayUser du = new DisplayUser(id);
			du.show(id);
			du.setVisible(true);
			
			
			
		}
		
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
