package eldar.game.client.launcher;

import java.awt.Checkbox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import eldar.game.client.Game;
import eldar.game.client.GameProperties;

public class OptionWindow extends JFrame {

	private JPanel contentPane;
	
	private NumberTextField txtMasterVolume;
	private JSlider sliderMasterVolume;
	private NumberTextField txtMusicVolume;
	private JSlider sliderMusicVolume;
	private NumberTextField txtEffectsVolume;
	private JSlider sliderEffectsVolume;
	private NumberTextField txtVoicesVolume;
	private JSlider sliderVoicesVolume;
	private NumberTextField txtMaxFps;
	private Checkbox checkboxDisableSound;
	private Checkbox checkboxFullscreen;
	private Checkbox checkLanguageFilter;
	private Checkbox checkEnableTips;
	private Checkbox checkGeneralChat;
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
		
		
		cmbResolution = new JComboBox();
		cmbResolution.setModel(new DefaultComboBoxModel(new String[]{"Super High", "High", "Medium", "Low", "Super Low"}));
		cmbResolution.setBounds(37, 48, 90, 22);
		layeredPane.add(cmbResolution);
		cmbResolution.setSelectedIndex(Game.gameProperties.resolution);
		
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
		cmbWindow.setSelectedIndex(Game.gameProperties.windowSize);
		
		checkboxFullscreen = new Checkbox("Fullscreen");
		checkboxFullscreen.setBounds(37, 141, 127, 25);
		layeredPane.add(checkboxFullscreen);
		checkboxFullscreen.setState(Game.gameProperties.fullscreen);
		
		JLabel lblMaxFps = new JLabel("Max Fps");
		lblMaxFps.setBounds(275, 30, 56, 16);
		layeredPane.add(lblMaxFps);
		
		txtMaxFps = new NumberTextField(Game.gameProperties.maxFps, 1, 3000);
		txtMaxFps.setBounds(275, 48, 59, 22);
		layeredPane.add(txtMaxFps);
		txtMaxFps.setColumns(10);
		txtMaxFps.setText(Integer.toString(Game.gameProperties.maxFps));
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		tabbedPane.addTab("Audio", null, layeredPane_1, null);
		
		sliderMasterVolume = new JSlider();
		sliderMasterVolume.setBounds(29, 48, 257, 23);
		layeredPane_1.add(sliderMasterVolume);
		sliderMasterVolume.setValue(Game.gameProperties.masterVolume);
		sliderMasterVolume.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				txtMasterVolume.setText(Integer.toString(sliderMasterVolume.getValue()));
			}
		});
		
		JLabel lblMasterVolume = new JLabel("Master Volume");
		lblMasterVolume.setBounds(354, 48, 86, 23);
		layeredPane_1.add(lblMasterVolume);
		
		txtMasterVolume = new NumberTextField(sliderMasterVolume,0,100);
		txtMasterVolume.setBounds(297, 48, 49, 22);
		layeredPane_1.add(txtMasterVolume);
		txtMasterVolume.setColumns(10);
		txtMasterVolume.setText(Integer.toString(Game.gameProperties.masterVolume));
		
		
		sliderMusicVolume = new JSlider();
		sliderMusicVolume.setBounds(29, 88, 257, 23);
		layeredPane_1.add(sliderMusicVolume);
		sliderMusicVolume.setValue(Game.gameProperties.musicVolume);
		sliderMusicVolume.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				txtMusicVolume.setText(Integer.toString(sliderMusicVolume.getValue()));
			}
		});
		
		JLabel lblMusicVolume = new JLabel("Music Volume");
		lblMusicVolume.setBounds(354, 88, 86, 23);
		layeredPane_1.add(lblMusicVolume);
		
		txtMusicVolume = new NumberTextField(sliderMusicVolume,0,100);
		txtMusicVolume.setBounds(297, 88, 49, 22);
		layeredPane_1.add(txtMusicVolume);
		txtMusicVolume.setColumns(10);
		txtMusicVolume.setText(Integer.toString(Game.gameProperties.musicVolume));
		
		sliderEffectsVolume = new JSlider();
		sliderEffectsVolume.setBounds(29, 128, 257, 23);
		layeredPane_1.add(sliderEffectsVolume);
		sliderEffectsVolume.setValue(Game.gameProperties.effectsVolume);
		sliderEffectsVolume.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				txtEffectsVolume.setText(Integer.toString(sliderEffectsVolume.getValue()));
			}
		});
		
		JLabel lblEffectsVolume = new JLabel("Effects Volume");
		lblEffectsVolume.setBounds(354, 128, 86, 23);
		layeredPane_1.add(lblEffectsVolume);
		
		txtEffectsVolume = new NumberTextField(sliderEffectsVolume,0,100);
		txtEffectsVolume.setBounds(297, 128, 49, 22);
		layeredPane_1.add(txtEffectsVolume);
		txtEffectsVolume.setColumns(10);
		txtEffectsVolume.setText(Integer.toString(Game.gameProperties.effectsVolume));
		sliderVoicesVolume = new JSlider();
		sliderVoicesVolume.setBounds(29, 168, 257, 23);
		layeredPane_1.add(sliderVoicesVolume);
		sliderVoicesVolume.setValue(Game.gameProperties.voicesVolume);
		sliderVoicesVolume.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				txtVoicesVolume.setText(Integer.toString(sliderVoicesVolume.getValue()));
			}
		});
		
		JLabel lblVoicesVolume = new JLabel("Voices Volume");
		lblVoicesVolume.setBounds(354, 168, 86, 23);
		layeredPane_1.add(lblVoicesVolume);
		
		txtVoicesVolume = new NumberTextField(sliderVoicesVolume,0,100);
		txtVoicesVolume.setBounds(297, 168, 49, 22);
		layeredPane_1.add(txtVoicesVolume);
		txtVoicesVolume.setColumns(10);
		txtVoicesVolume.setText(Integer.toString(Game.gameProperties.voicesVolume));
		
		checkboxDisableSound = new Checkbox("Disable Sound");
		checkboxDisableSound.setBounds(34, 207, 108, 24);
		layeredPane_1.add(checkboxDisableSound);
		checkboxDisableSound.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				checkSound();		
			}
		});
		
		checkboxDisableSound.setState(Game.gameProperties.disableSound);
		checkSound();
		
		JLayeredPane layeredPane_2 = new JLayeredPane();
		tabbedPane.addTab("Gameplay", null, layeredPane_2, null);
		
		checkLanguageFilter = new Checkbox("Enable Language Filter");
		checkLanguageFilter.setBounds(26, 24, 159, 25);
		layeredPane_2.add(checkLanguageFilter);
		
		checkEnableTips = new Checkbox("Enable Tips");
		checkEnableTips.setBounds(26, 63, 113, 25);
		layeredPane_2.add(checkEnableTips);
		
		checkGeneralChat = new Checkbox("Enable General Chat");
		checkGeneralChat.setBounds(26, 104, 145, 25);
		layeredPane_2.add(checkGeneralChat);
		
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
		Game.gameProperties.windowSize = cmbWindow.getSelectedIndex();
		Game.gameProperties.resolution = cmbResolution.getSelectedIndex();
		Game.gameProperties.fullscreen = checkboxFullscreen.getState();
		Game.gameProperties.maxFps = Integer.parseInt(txtMaxFps.getText());
		//Audio
		Game.gameProperties.masterVolume = sliderMasterVolume.getValue();
		Game.gameProperties.musicVolume = sliderMusicVolume.getValue();
		Game.gameProperties.effectsVolume = sliderEffectsVolume.getValue();
		Game.gameProperties.voicesVolume = sliderVoicesVolume.getValue();
		Game.gameProperties.disableSound = checkboxDisableSound.getState();
		//Gameplay
		Game.gameProperties.languageFilter = checkLanguageFilter.getState();
		Game.gameProperties.includeTips = checkEnableTips.getState();
		Game.gameProperties.generalChat = checkGeneralChat.getState();
		
		Game.gameProperties.writeProperties();
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
			txtVoicesVolume.setEnabled(false);
			sliderVoicesVolume.setEnabled(false);
		}
		else{
			txtMasterVolume.setEnabled(true);
			sliderMasterVolume.setEnabled(true);
			txtMusicVolume.setEnabled(true);
			sliderMusicVolume.setEnabled(true);
			txtEffectsVolume.setEnabled(true);
			sliderEffectsVolume.setEnabled(true);
			txtVoicesVolume.setEnabled(true);
			sliderVoicesVolume.setEnabled(true);
		}
	}
}
