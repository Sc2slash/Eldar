package eldar.game.client.launcher;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import eldar.game.client.Game;

public class Launcher extends JFrame {
	private JTextField txtName;
	private JPasswordField passwordField;
	
	private JLabel lblInvalidUsernameOr;
	private JLabel lblUnnableToConnect;
	
	private OptionWindow optionWindow= null;
	Game game;
	
	public Launcher(Game game) {
		this.game = game;
		this.optionWindow = new OptionWindow();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Launcher.class.getResource("/graphics/icon.png")));
		setFont(new Font("Calibri", Font.PLAIN, 16));
		setTitle("Eldar Launcher");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300,400);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		txtName = new JTextField();
		txtName.setBounds(73, 45, 148, 22);
		getContentPane().add(txtName);
		txtName.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setBounds(118, 23, 58, 16);
		getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(119, 80, 56, 16);
		getContentPane().add(lblPassword);
		
		JButton btnLaunch = new JButton("Launch");
		btnLaunch.setBounds(73, 145, 148, 43);
		getContentPane().add(btnLaunch);
		btnLaunch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				launchGame();
			}
		});
		
		JButton btnOptions = new JButton("Options");
		btnOptions.setBounds(98, 260, 97, 25);
		getContentPane().add(btnOptions);
		btnOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openOptions();
			}
		});
		JButton btnAbout = new JButton("About");
		btnAbout.setBounds(98, 298, 97, 25);
		getContentPane().add(btnAbout);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(73, 109, 148, 22);
		getContentPane().add(passwordField);
		
		lblInvalidUsernameOr = new JLabel("Invalid username or password");
		lblInvalidUsernameOr.setForeground(new Color(255, 0, 0));
		lblInvalidUsernameOr.setBounds(60, 201, 173, 16);
		getContentPane().add(lblInvalidUsernameOr);
		
		lblUnnableToConnect = new JLabel("Unnable to connect to server");
		lblUnnableToConnect.setForeground(new Color(255, 0, 0));
		lblUnnableToConnect.setBounds(65, 201, 164, 16);
		getContentPane().add(lblUnnableToConnect);
		
		lblInvalidUsernameOr.setVisible(false);
		lblUnnableToConnect.setVisible(false);
	}
	
	public void start(){
		setVisible(true);
		requestFocus();
	}
	public void openOptions(){
		optionWindow.start();
	}
	public boolean checkConnection(){
		return true;
	}
	public boolean checkLogin(){
		return true;
	}
	public void launchGame(){
		if(checkConnection()){
			if(checkLogin()){
				this.dispose();
				optionWindow.dispose();
				game.launch();
			}
			else{
				lblInvalidUsernameOr.setVisible(true);
				lblUnnableToConnect.setVisible(false);
			}
		}
		else{
			lblInvalidUsernameOr.setVisible(false);
			lblUnnableToConnect.setVisible(true);
		}
	}
}
