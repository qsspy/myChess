package dev.game.tiles;

import java.awt.Graphics;

import dev.game.GP;
import dev.game.ImageManipulation.Assets;
import dev.game.figures.FigureManager;

public class Board {
	
	private Tile[] tiles;
	
	public void render(Graphics g)
	{
		int p = 1;
		for(int i=0;i<GP.TILES_PER_COLUMN;i++)
		{
			tiles[0].setY(i*tiles[0].getHeight());
			tiles[1].setY(i*tiles[1].getHeight());
			for(int j=0;j<GP.TILES_PER_ROW;j++)
			{
				if(p%2==0)
				{
					tiles[0].setX(j*tiles[0].getWidth());
					tiles[0].render(g);
				}
				else
				{
					tiles[1].setX(j*tiles[1].getWidth());
					tiles[1].render(g);
				}
				p++;
			}
			p++;
		}
		
	}
	
	public void update()
	{
		tiles[0].update();
		tiles[1].update();
	}
	
	public Board(Tile tile1, Tile tile2)
	{
		tiles = new Tile[2];
		tiles[0] = tile1;
		tiles[1] = tile2;
	}

}
