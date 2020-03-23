package dev.game.UI.gameUI;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import dev.game.Handler;
import dev.game.util.ColouredString;

public class UIInfoRectangle{

	protected int x,y;
	protected int width,height;
	protected BufferedImage texture;
	protected Handler handler;
	protected ArrayList<ColouredString> messages;
	protected ColouredString activeString;
	
	
	
	public UIInfoRectangle(Handler handler,int x,int y,int width,int height, 
							BufferedImage texture, ArrayList<ColouredString> messages)
	{
		this.handler = handler;
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.texture = texture;
		this.messages = messages;
	}
	
	public void update()
	{
		
	}
	
	public void render(Graphics2D g2D)
	{
		g2D.drawImage(texture,x,y,width,height,null);
	}
	
	public void pushEvent(ColouredString event)
	{
		messages.add(0, event);
	}
	
	//GETTERS SETTERS

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}

	public ArrayList<ColouredString> getMessages() {
		return messages;
	}

	public void setMessages(ArrayList<ColouredString> messages) {
		this.messages = messages;
	}

	public ColouredString getActiveString() {
		return activeString;
	}

	public void setActiveString(ColouredString activeString) {
		this.activeString = activeString;
	}
	
	
	
	

}
