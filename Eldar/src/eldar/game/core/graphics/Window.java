package eldar.game.core.graphics;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Window extends Canvas{
	private int scale;
	private Dimension windowSize, canvasSize;
	private boolean resizable;
	private String caption;
	private BufferedImage icon;
	
	private JFrame frame;
	
	public Window(int width, int height, int scale, boolean resizable, String caption, BufferedImage icon){
		this.canvasSize = new Dimension(width, height);
		this.scale = scale;
		this.resizable = resizable;
		this.caption = caption;
		this.icon = icon;
		this.windowSize = new Dimension(width*scale, height*scale);
		//JFrame stuff
		this.frame = new JFrame(caption);
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
	public Dimension getCanvasSize(){
		return canvasSize;
	}
	public boolean isResizable(){
		return resizable;
	}
	public String getCaption(){
		return caption;
	}
	public int getScale(){
		return scale;
	}
	public BufferedImage getIcon(){
		return icon;
	}
	public JFrame getFrame(){
		return frame;
	}
}
