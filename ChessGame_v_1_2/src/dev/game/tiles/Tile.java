package dev.game.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.game.GP;

public class Tile {
	
	private int x=0;
	private int y=0;
	private BufferedImage texture ;
	private final int width = GP.DISPLAY_WIDTH/GP.TILES_PER_ROW;
	private final int height = GP.DISPLAY_HEIGHT/GP.TILES_PER_COLUMN;
	
	public void update()
	{
		
	}
	
	public void render(Graphics g)
	{
		g.drawImage(texture, x, y, width, height, null);
	}
	
	public Tile(BufferedImage texture)
	{
		this.texture = texture;
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

	public int getHeight() {
		return height;
	}
	
	

}
