package adasda;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Home extends JFrame implements ActionListener{
	
	JFrame frame;
	JButton addButton,searchButton,displayButton;
	JLabel addLabel,searchLabel,displayLabel;
	JPanel buttons;
	ImageIcon addIcon;
	private String displayAllSql = "SELECT id As 'ID', name AS 'Imie' ,lastname AS 'Nazwisko' FROM patients";
	
	public Home() {
		Color bgColor = new Color(30,30,30);
		setBounds(10, 10, 800, 700);
		getContentPane().setBackground(bgColor);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		ImageIcon i = new ImageIcon("buttons\\add-user.png");
		Image img = i.getImage();
		Image new_img = img.getScaledInstance(140, 140, Image.SCALE_SMOOTH);
		i = new ImageIcon(new_img);
		addButton = new JButton(i);
		addButton.addActionListener(this);
		
		
		
		ImageIcon i2 = new ImageIcon("buttons\\search.png");
		Image img2 = i2.getImage();
		Image new_img2 = img2.getScaledInstance(140, 140, Image.SCALE_SMOOTH);
		i2 = new ImageIcon(new_img2);
		searchButton = new JButton(i2);
		searchButton.addActionListener(this);
		
		ImageIcon i3 = new ImageIcon("buttons\\display.png");
		Image img3 = i3.getImage();
		Image new_img3 = img3.getScaledInstance(140, 140, Image.SCALE_SMOOTH);
		i3 = new ImageIcon(new_img3);
		displayButton = new JButton(i3);
		displayButton.addActionListener(this);
		
		buttons = new JPanel();
		buttons.setBackground(bgColor);
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		buttons.setBounds(100, 250, 600, 300);
		buttons.add(addButton);
		buttons.add(Box.createHorizontalStrut(40));
		buttons.add(displayButton);
		buttons.add(Box.createHorizontalStrut(40));
		buttons.add(searchButton);
		
		addLabel = new JLabel("<html>Dodaj<br/>pacjenta</html>", SwingConstants.CENTER);;
		searchLabel = new JLabel("Wyszukaj");
		displayLabel = new JLabel("<html>Wyœwietl<br/> wszystkich</html>");
		Font font = new Font("Arial" , Font.BOLD, 30);
		
		addLabel.setBounds(90, 480, 200, 100);
		addLabel.setForeground(Color.white);
		addLabel.setFont(font);
		
		
		searchLabel.setBounds(540, 480, 200, 100);
		searchLabel.setForeground(Color.white);
		searchLabel.setFont(font);
		
		displayLabel.setBounds(335, 480, 200, 100);
		displayLabel.setForeground(Color.white);
		displayLabel.setFont(font);
		
		
		add(searchLabel);
		add(displayLabel);
		add(addLabel);
		add(buttons);
		setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getSource();
		if(source==addButton)
		{
	
		frame = new AddUser();
		frame.setVisible(true);
		setVisible(false);

		
		}
		if(source==searchButton)
		{
			
			frame = new SearchUser();
			frame.setVisible(true);
			setVisible(false);
		}
		if(source==displayButton)
		{
			frame=new ShowUsers(displayAllSql);
			frame.setVisible(true);
			setVisible(false);
			
		}
		
		
	}


	
	}


