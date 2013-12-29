package eldar.game.launcher;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import java.awt.Font;

import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JList;
import javax.swing.JFormattedTextField;
import javax.swing.Box;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;

import java.awt.Button;

import javax.swing.JButton;

import java.awt.Canvas;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;

import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JSplitPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JSpinner;

public class OptionWindow extends JFrame {

	private JPanel contentPane;
	private JTextField txtMasterVolume;
	private JLabel lblMasterVolume;
	public OptionWindow() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setSize(750,450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 732, 405);
		contentPane.add(tabbedPane);
		
		JLayeredPane layeredPane = new JLayeredPane();
		tabbedPane.addTab("Graphics", null, layeredPane, null);
		
		JLabel lblResolution = new JLabel("Resolution");
		lblResolution.setBounds(37, 30, 59, 16);
		layeredPane.add(lblResolution);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"640x480", "512x384", "320x240"}));
		comboBox.setBounds(37, 48, 75, 22);
		layeredPane.add(comboBox);
		
		JLabel lblWindowSize = new JLabel("Window size");
		lblWindowSize.setBounds(37, 83, 72, 16);
		layeredPane.add(lblWindowSize);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"1920x1080", "1280x800", "1024x768", "768x500"}));
		comboBox_1.setBounds(37, 112, 89, 22);
		layeredPane.add(comboBox_1);
		
		JRadioButton rdbtnFullscreen = new JRadioButton("Fullscreen");
		rdbtnFullscreen.setBounds(37, 151, 127, 25);
		layeredPane.add(rdbtnFullscreen);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		tabbedPane.addTab("Audio", null, layeredPane_1, null);
		
		JSlider sliderMasterVolume = new JSlider();
		sliderMasterVolume.setBounds(29, 48, 257, 23);
		layeredPane_1.add(sliderMasterVolume);
		sliderMasterVolume.addChangeListener(new ChangeListener() {
	
			public void stateChanged(ChangeEvent arg0) {
				
			}
		});
		
		lblMasterVolume = new JLabel("Master Volume");
		lblMasterVolume.setBounds(354, 48, 86, 23);
		layeredPane_1.add(lblMasterVolume);
		
		txtMasterVolume = new JTextField();
		txtMasterVolume.setBounds(297, 48, 49, 22);
		layeredPane_1.add(txtMasterVolume);
		txtMasterVolume.setColumns(10);
	}
	public void start(){
		setVisible(true);
	}
}
