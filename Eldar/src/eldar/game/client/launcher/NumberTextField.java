package eldar.game.client.launcher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JSlider;
import javax.swing.JTextField;

public class NumberTextField extends JTextField{
	int minNum,maxNum;
	JSlider slider;
	public NumberTextField(JSlider jSlider,int min, int max){
		super();
		this.slider = jSlider;
		this.minNum = min;
		this.maxNum = max;
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = -1;
				try{
				 n = Integer.parseInt(getText());
				if(n > maxNum){
					slider.setValue(maxNum);
					setText(Integer.toString(maxNum));
				}
				else if(n < minNum){
					slider.setValue(minNum);
					setText(Integer.toString(minNum));
				}
				else
					slider.setValue(Integer.parseInt(getText()));
				}catch(NumberFormatException ex){
					setText(Integer.toString(slider.getValue()));
				}
			}
		});
		this.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				int n = -1;
				try{
				 n = Integer.parseInt(getText());
				if(n > 100){
					slider.setValue(100);
					setText(Integer.toString(100));
				}
				else if(n < 0){
					slider.setValue(0);
					setText(Integer.toString(0));
				}
				else
					slider.setValue(Integer.parseInt(getText()));
				}catch(NumberFormatException ex){
					setText(Integer.toString(slider.getValue()));
				}
				
			}
			public void focusGained(FocusEvent e) {
			}
		});
	}
}
