package dev.game.UI.menuUI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.game.GP;

public class MenuBackground {
	
	private BufferedImage texture;
	
	public MenuBackground(BufferedImage texture)
	{
		this.texture = texture;
	}
	
	public void update()
	{
		
	}
	
	public void render(Graphics g)
	{
		g.drawImage(texture,0,0,GP.DISPLAY_WIDTH + GP.UI_SPACE,GP.DISPLAY_HEIGHT,null);
	}
	
	

}
