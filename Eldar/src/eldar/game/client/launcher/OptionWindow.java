package eldar.game.client.launcher;

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
import java.awt.Checkbox;
import javax.swing.JCheckBox;

public class OptionWindow extends JFrame {

	private JPanel contentPane;
	private JTextField txtMasterVolume;
	private JSlider sliderMasterVolume;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField txtMaxFps;
	
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
		tabbedPane.setBounds(0, 0, 615, 405);
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
		comboBox_1.setBounds(37, 101, 89, 22);
		layeredPane.add(comboBox_1);
		
		JRadioButton rdbtnFullscreen = new JRadioButton("Fullscreen");
		rdbtnFullscreen.setBounds(37, 141, 127, 25);
		layeredPane.add(rdbtnFullscreen);
		
		JLabel lblMaxFps = new JLabel("Max Fps");
		lblMaxFps.setBounds(275, 30, 56, 16);
		layeredPane.add(lblMaxFps);
		
		txtMaxFps = new JTextField();
		txtMaxFps.setBounds(275, 48, 59, 22);
		layeredPane.add(txtMaxFps);
		txtMaxFps.setColumns(10);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		tabbedPane.addTab("Audio", null, layeredPane_1, null);
		
		sliderMasterVolume = new JSlider();
		sliderMasterVolume.setBounds(29, 48, 257, 23);
		layeredPane_1.add(sliderMasterVolume);
		sliderMasterVolume.addChangeListener(new ChangeListener() {
	
			public void stateChanged(ChangeEvent e) {
				sliderMasterVolume.getValue();
				txtMasterVolume.setText(Integer.toString(sliderMasterVolume.getValue()));
			}
		});
		
		JLabel lblMasterVolume = new JLabel("Master Volume");
		lblMasterVolume.setBounds(354, 48, 86, 23);
		layeredPane_1.add(lblMasterVolume);
		
		txtMasterVolume = new JTextField();
		txtMasterVolume.setBounds(297, 48, 49, 22);
		layeredPane_1.add(txtMasterVolume);
		txtMasterVolume.setColumns(10);
		
		JSlider slider = new JSlider();
		slider.setBounds(29, 84, 257, 23);
		layeredPane_1.add(slider);
		
		textField = new JTextField();
		textField.setBounds(297, 85, 49, 22);
		layeredPane_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblMusicVolume = new JLabel("Music Volume");
		lblMusicVolume.setBounds(354, 84, 79, 23);
		layeredPane_1.add(lblMusicVolume);
		
		JSlider slider_1 = new JSlider();
		slider_1.setBounds(29, 120, 257, 23);
		layeredPane_1.add(slider_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(297, 120, 49, 22);
		layeredPane_1.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblEffectsVolume = new JLabel("Effects Volume");
		lblEffectsVolume.setBounds(354, 120, 85, 23);
		layeredPane_1.add(lblEffectsVolume);
		
		JSlider slider_2 = new JSlider();
		slider_2.setBounds(29, 156, 257, 23);
		layeredPane_1.add(slider_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(297, 157, 49, 22);
		layeredPane_1.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblVoicesVolume = new JLabel("Voices Volume");
		lblVoicesVolume.setBounds(354, 156, 84, 23);
		layeredPane_1.add(lblVoicesVolume);
		
		Checkbox checkboxDisableSound = new Checkbox("Disable Sound");
		checkboxDisableSound.setBounds(34, 207, 108, 24);
		layeredPane_1.add(checkboxDisableSound);
		
		JLayeredPane layeredPane_2 = new JLayeredPane();
		tabbedPane.addTab("Gameplay", null, layeredPane_2, null);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Enable Language Filter");
		chckbxNewCheckBox.setBounds(26, 24, 159, 25);
		layeredPane_2.add(chckbxNewCheckBox);
		
		JCheckBox chckbxEnableTips = new JCheckBox("Enable Tips");
		chckbxEnableTips.setBounds(26, 63, 113, 25);
		layeredPane_2.add(chckbxEnableTips);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Enable General Chat");
		chckbxNewCheckBox_1.setBounds(26, 104, 145, 25);
		layeredPane_2.add(chckbxNewCheckBox_1);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(627, 50, 97, 25);
		contentPane.add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(627, 88, 97, 25);
		contentPane.add(btnCancel);
	}
	public void start(){
		setVisible(true);
	}
	public void loadProperties(){
		
	}
}
