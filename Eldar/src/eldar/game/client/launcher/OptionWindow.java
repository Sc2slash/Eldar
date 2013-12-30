package eldar.game.client.launcher;

import java.awt.Checkbox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import eldar.game.client.GameProperties;
import eldar.game.client.Resources;

public class OptionWindow extends JFrame {

	private JPanel contentPane;
	
	private JTextField txtMasterVolume;
	private JSlider sliderMasterVolume;
	private JTextField txtMusicVolume;
	private JSlider sliderMusicVolume;
	private JTextField txtEffectsVolume;
	private JSlider sliderEffectsVolume;
	private JTextField txtVoiceVolume;
	private JSlider sliderVoiceVolume;
	private JTextField txtMaxFps;
	private Checkbox checkboxDisableSound;
	private Checkbox checkboxFullscreen;
	private JComboBox cmbResolution;
	private JComboBox cmbWindow;
	
	public OptionWindow() {
		File file = null;
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setSize(750,450);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		String[] resArray = new String[GameProperties.resolutions.length];
		for(int i = 0; i < resArray.length; i++){
			resArray[i] = GameProperties.resolutions[i].width + "x" + GameProperties.resolutions[i].height;
		}
		cmbResolution = new JComboBox();
		cmbResolution.setModel(new DefaultComboBoxModel(resArray));
		cmbResolution.setBounds(37, 48, 75, 22);
		layeredPane.add(cmbResolution);
		cmbResolution.setSelectedIndex(Resources.gameProperties.resolution);
		
		JLabel lblWindowSize = new JLabel("Window size");
		lblWindowSize.setBounds(37, 83, 72, 16);
		layeredPane.add(lblWindowSize);
		
		String[] winSizeArray = new String[GameProperties.windowSizes.length];
		for(int i = 0; i < winSizeArray.length; i++){
			winSizeArray[i] = GameProperties.windowSizes[i].width + "x" + GameProperties.windowSizes[i].height;
		}
		cmbWindow = new JComboBox();
		cmbWindow.setModel(new DefaultComboBoxModel(winSizeArray));
		cmbWindow.setBounds(37, 101, 89, 22);
		layeredPane.add(cmbWindow);
		cmbWindow.setSelectedIndex(Resources.gameProperties.windowSize);
		
		checkboxFullscreen = new Checkbox("Fullscreen");
		checkboxFullscreen.setBounds(37, 141, 127, 25);
		layeredPane.add(checkboxFullscreen);
		checkboxFullscreen.setState(Resources.gameProperties.fullscreen);
		
		JLabel lblMaxFps = new JLabel("Max Fps");
		lblMaxFps.setBounds(275, 30, 56, 16);
		layeredPane.add(lblMaxFps);
		
		txtMaxFps = new JTextField();
		txtMaxFps.setBounds(275, 48, 59, 22);
		layeredPane.add(txtMaxFps);
		txtMaxFps.setColumns(10);
		txtMaxFps.setText(Integer.toString(Resources.gameProperties.maxFps));
		txtMaxFps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = -1;
				try{
					i = Integer.parseInt(txtMaxFps.getText());
					if(i < 0){
						txtMaxFps.setText(Integer.toString(Resources.gameProperties.maxFps));
					}
				}catch(NumberFormatException ex){
					txtMaxFps.setText(Integer.toString(Resources.gameProperties.maxFps));
				}
				
			}
		});
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		tabbedPane.addTab("Audio", null, layeredPane_1, null);
		
		sliderMasterVolume = new JSlider();
		sliderMasterVolume.setBounds(29, 48, 257, 23);
		layeredPane_1.add(sliderMasterVolume);
		sliderMasterVolume.setValue(Resources.gameProperties.masterVolume);
		sliderMasterVolume.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
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
		txtMasterVolume.setText(Integer.toString(Resources.gameProperties.masterVolume));
		txtMasterVolume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = -1;
				try{
				 n = Integer.parseInt(txtMasterVolume.getText());
				if(n > 100){
					sliderMasterVolume.setValue(100);
					txtMasterVolume.setText(Integer.toString(100));
				}
				else if(n < 0){
					sliderMasterVolume.setValue(0);
					txtMasterVolume.setText(Integer.toString(0));
				}
				else
					sliderMasterVolume.setValue(Integer.parseInt(txtMasterVolume.getText()));
				}catch(NumberFormatException ex){
					txtMasterVolume.setText(Integer.toString(sliderMasterVolume.getValue()));
				}
			}
		});
		
		sliderMusicVolume = new JSlider();
		sliderMusicVolume.setBounds(29, 88, 257, 23);
		layeredPane_1.add(sliderMusicVolume);
		sliderMusicVolume.setValue(Resources.gameProperties.musicVolume);
		sliderMusicVolume.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				txtMusicVolume.setText(Integer.toString(sliderMusicVolume.getValue()));
			}
		});
		
		JLabel lblMusicVolume = new JLabel("Music Volume");
		lblMusicVolume.setBounds(354, 88, 86, 23);
		layeredPane_1.add(lblMusicVolume);
		
		txtMusicVolume = new JTextField();
		txtMusicVolume.setBounds(297, 88, 49, 22);
		layeredPane_1.add(txtMusicVolume);
		txtMusicVolume.setColumns(10);
		txtMusicVolume.setText(Integer.toString(Resources.gameProperties.musicVolume));
		txtMusicVolume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = -1;
				try{
				 n = Integer.parseInt(txtMusicVolume.getText());
				if(n > 100){
					sliderMusicVolume.setValue(100);
					txtMusicVolume.setText(Integer.toString(100));
				}
				else if(n < 0){
					sliderMusicVolume.setValue(0);
					txtMusicVolume.setText(Integer.toString(0));
				}
				else
					sliderMusicVolume.setValue(Integer.parseInt(txtMusicVolume.getText()));
				}catch(NumberFormatException ex){
					txtMusicVolume.setText(Integer.toString(sliderMusicVolume.getValue()));
				}
			}
		});
		
		sliderEffectsVolume = new JSlider();
		sliderEffectsVolume.setBounds(29, 128, 257, 23);
		layeredPane_1.add(sliderEffectsVolume);
		sliderEffectsVolume.setValue(Resources.gameProperties.effectsVolume);
		sliderEffectsVolume.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				txtEffectsVolume.setText(Integer.toString(sliderEffectsVolume.getValue()));
			}
		});
		
		JLabel lblEffectsVolume = new JLabel("Effects Volume");
		lblEffectsVolume.setBounds(354, 128, 86, 23);
		layeredPane_1.add(lblEffectsVolume);
		
		txtEffectsVolume = new JTextField();
		txtEffectsVolume.setBounds(297, 128, 49, 22);
		layeredPane_1.add(txtEffectsVolume);
		txtEffectsVolume.setColumns(10);
		txtEffectsVolume.setText(Integer.toString(Resources.gameProperties.effectsVolume));
		txtEffectsVolume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = -1;
				try{
				 n = Integer.parseInt(txtEffectsVolume.getText());
				if(n > 100){
					sliderEffectsVolume.setValue(100);
					txtEffectsVolume.setText(Integer.toString(100));
				}
				else if(n < 0){
					sliderEffectsVolume.setValue(0);
					txtEffectsVolume.setText(Integer.toString(0));
				}
				else
					sliderEffectsVolume.setValue(Integer.parseInt(txtEffectsVolume.getText()));
				}catch(NumberFormatException ex){
					txtEffectsVolume.setText(Integer.toString(sliderEffectsVolume.getValue()));
				}
			}
		});
		

		sliderVoiceVolume = new JSlider();
		sliderVoiceVolume.setBounds(29, 168, 257, 23);
		layeredPane_1.add(sliderVoiceVolume);
		sliderVoiceVolume.setValue(Resources.gameProperties.voicesVolume);
		sliderVoiceVolume.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				txtVoiceVolume.setText(Integer.toString(sliderVoiceVolume.getValue()));
			}
		});
		
		JLabel lblVoiceVolume = new JLabel("Voice Volume");
		lblVoiceVolume.setBounds(354, 168, 86, 23);
		layeredPane_1.add(lblVoiceVolume);
		
		txtVoiceVolume = new JTextField();
		txtVoiceVolume.setBounds(297, 168, 49, 22);
		layeredPane_1.add(txtVoiceVolume);
		txtVoiceVolume.setColumns(10);
		txtVoiceVolume.setText(Integer.toString(Resources.gameProperties.voicesVolume));
		txtVoiceVolume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = -1;
				try{
				 n = Integer.parseInt(txtVoiceVolume.getText());
				if(n > 100){
					sliderVoiceVolume.setValue(100);
					txtVoiceVolume.setText(Integer.toString(100));
				}
				else if(n < 0){
					sliderVoiceVolume.setValue(0);
					txtVoiceVolume.setText(Integer.toString(0));
				}
				else
					sliderVoiceVolume.setValue(Integer.parseInt(txtVoiceVolume.getText()));
				}catch(NumberFormatException ex){
					txtVoiceVolume.setText(Integer.toString(sliderVoiceVolume.getValue()));
				}
			}
		});
		
		checkboxDisableSound = new Checkbox("Disable Sound");
		checkboxDisableSound.setBounds(34, 207, 108, 24);
		layeredPane_1.add(checkboxDisableSound);
		checkboxDisableSound.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				checkSound();		
			}
		});
		checkboxDisableSound.setState(Resources.gameProperties.disableSound);
		checkSound();
		
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
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveProperties();
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(627, 88, 97, 25);
		contentPane.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
	}
	public void saveProperties(){
		Resources.gameProperties.windowSize = cmbWindow.getSelectedIndex();
		Resources.gameProperties.resolution = cmbResolution.getSelectedIndex();
		Resources.gameProperties.fullscreen = checkboxFullscreen.getState();
		Resources.gameProperties.maxFps = 120;
		//Audio
		Resources.gameProperties.masterVolume = sliderMasterVolume.getValue();
		Resources.gameProperties.musicVolume = sliderMusicVolume.getValue();
		Resources.gameProperties.effectsVolume = sliderEffectsVolume.getValue();
		Resources.gameProperties.voicesVolume = sliderVoiceVolume.getValue();
		Resources.gameProperties.disableSound = checkboxDisableSound.getState();
		//Gameplay
		Resources.gameProperties.languageFilter = false;
		Resources.gameProperties.includeTips = false;
		Resources.gameProperties.generalChat = false;
		
		Resources.gameProperties.writeProperties();
		dispose();
	}
	public void resetProperties(){
		
	}
	public void start(){
		setVisible(true);
	}
	public void checkSound(){
		if(checkboxDisableSound.getState()){
			txtMasterVolume.setEnabled(false);
			sliderMasterVolume.setEnabled(false);
			txtMusicVolume.setEnabled(false);
			sliderMusicVolume.setEnabled(false);
			txtEffectsVolume.setEnabled(false);
			sliderEffectsVolume.setEnabled(false);
			txtVoiceVolume.setEnabled(false);
			sliderVoiceVolume.setEnabled(false);
		}
		else{
			txtMasterVolume.setEnabled(true);
			sliderMasterVolume.setEnabled(true);
			txtMusicVolume.setEnabled(true);
			sliderMusicVolume.setEnabled(true);
			txtEffectsVolume.setEnabled(true);
			sliderEffectsVolume.setEnabled(true);
			txtVoiceVolume.setEnabled(true);
			sliderVoiceVolume.setEnabled(true);
		}
	}
}
