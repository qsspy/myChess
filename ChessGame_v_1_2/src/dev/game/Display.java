package dev.game;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display { //Wyswietlanie okna
	
	private JFrame frame;
	private Canvas canvas;
	
	public Display(String name, int height, int width)
	{
		frame = new JFrame();
		canvas = new Canvas();
		setWindowParams(name,width,height);
	}
	
	private void setWindowParams(String name, int height, int width)
	{
		frame.setTitle(name);
		frame.setSize(width, height);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		canvas.setPreferredSize(new Dimension(width,height));
		canvas.setMaximumSize(new Dimension(width,height));
		canvas.setMinimumSize(new Dimension(width,height));
		canvas.setFocusable(false);
		
		frame.add(canvas);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}
	
	//GETTERS SETTERS
	
	

}
