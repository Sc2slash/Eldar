package eldar.game.client.core.graphics;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Window extends Canvas{
	private float scale;
	private Dimension windowSize;
	private boolean resizable;
	private String caption;
	private BufferedImage icon;
	private boolean fullscreen;
	private JFrame frame;
	
	public Window(int width, int height,boolean resizable, boolean fullscreen, String caption, BufferedImage icon){
		this.resizable = resizable;
		this.fullscreen = fullscreen;
		this.caption = caption;
		this.icon = icon;
		this.windowSize = new Dimension(width,height);
		//JFrame stuff
		this.frame = new JFrame(caption);
		if(fullscreen){
			frame.setUndecorated(true);
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		}
		this.frame.setIconImage(icon);
		this.frame.setResizable(resizable);
		this.setPreferredSize(windowSize);
		this.setMaximumSize(windowSize);
		this.setMinimumSize(windowSize);
		this.frame.add(this);
		this.frame.pack();
		this.frame.setLocationRelativeTo(null);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setFocusable(true);
	}
	public void launchWindow(){
		this.frame.setVisible(true);
		this.requestFocus();
	}
	//Set
	public void setCaption(String caption){
		this.caption = caption;
		this.frame.setTitle(caption);
	}
	public void setIcon(BufferedImage icon){
		this.icon = icon;
		this.frame.setIconImage(icon);
	}
	//Get
	public boolean isResizable(){
		return resizable;
	}
	public String getCaption(){
		return caption;
	}
	public BufferedImage getIcon(){
		return icon;
	}
	public JFrame getFrame(){
		return frame;
	}
}
