package adasda;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Home extends JFrame{
	
	JButton addButton,searchButton,displayButton;
	JLabel addLabel,searchLabel,displayLabel;
	JPanel buttons;
	ImageIcon addIcon;
	
	
	public Home() {
	
		setBounds(10, 10, 800, 700);
		getContentPane().setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		ImageIcon i = new ImageIcon("buttons\\add-user.png");
		Image img = i.getImage();
		Image new_img = img.getScaledInstance(140, 140, Image.SCALE_SMOOTH);
		i = new ImageIcon(new_img);
		addButton = new JButton(i);
		
		
		
		ImageIcon i2 = new ImageIcon("buttons\\display.png");
		Image img2 = i2.getImage();
		Image new_img2 = img2.getScaledInstance(140, 140, Image.SCALE_SMOOTH);
		i2 = new ImageIcon(new_img2);
		searchButton = new JButton(i2);
		
		ImageIcon i3 = new ImageIcon("buttons\\search.png");
		Image img3 = i3.getImage();
		Image new_img3 = img3.getScaledInstance(140, 140, Image.SCALE_SMOOTH);
		i3 = new ImageIcon(new_img3);
		displayButton = new JButton(i3);
		
		buttons = new JPanel();
		buttons.setBackground(Color.darkGray);
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		buttons.setBounds(100, 250, 600, 300);
		buttons.add(addButton);
		buttons.add(Box.createHorizontalStrut(40));
		buttons.add(displayButton);
		buttons.add(Box.createHorizontalStrut(40));
		buttons.add(searchButton);
		
		add(buttons);
		
		
		
		setVisible(true);

	}
	
	
	
	
	}


