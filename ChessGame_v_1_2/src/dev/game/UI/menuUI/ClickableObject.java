package dev.game.UI.menuUI;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.game.Handler;

public class ClickableObject {
	
	private int x;
	private int y;
	private int width;
	private int height;
	private Rectangle bounds;
	private BufferedImage[] textures;
	private boolean hovering = false;
	private Handler handler;
	private ClickListener cl;
	
	public ClickableObject(Handler handler,BufferedImage[] textures,int x, int y, int width, int height,ClickListener cl)
	{
		this.handler = handler;
		this.textures = textures;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.cl = cl;
		this.bounds = new Rectangle(x,y,width,height);
	}
	
	public void render(Graphics g)
	{
		if(hovering)g.drawImage(textures[0],x,y,width,height,null);
		else g.drawImage(textures[1],x,y,width,height,null);
	}
	
	public void update()
	{
		setHoveringIfPossible();
		doOnClickActionIfPossible();
	}
	
	private void setHoveringIfPossible()
	{
		if(bounds.contains(handler.getGame().getMouseManager().getX(),handler.getGame().getMouseManager().getY()))
		{
			hovering = true;
		}
		else hovering = false;
	}
	
	private void doOnClickActionIfPossible()
	{
		if(hovering && handler.getGame().getMouseManager().isLeftPressedMenu())
		{
			handler.getGame().getMouseManager().setLeftPressedMenu(false);
			onClick();
		}
	}
	
	public void onClick()
	{
		cl.onClick();
	}

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

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public boolean isHovering() {
		return hovering;
	}

	public void setHovering(boolean hovering) {
		this.hovering = hovering;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}
	
	//GETTERS SETTERS 
	
	
	
	
	

}
