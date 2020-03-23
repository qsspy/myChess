package dev.game.figures;

import java.awt.Dimension;
import java.awt.Rectangle;

import dev.game.Handler;
import dev.game.ImageManipulation.Assets;

public class KnightFigure extends Figure{

	public KnightFigure(Handler handler, int x, int y, String color, int id, boolean real) {
		super(handler, x, y, color, id, real);
		
		if(color == "white")texture = Assets.whiteFigures[3];
		else 				texture = Assets.blackFigures[3];
	}

	public void update() {
		
		if(tick == 1)
		{
			possibleMoves.clear();
			possibleAttacks.clear();
			potentialMoves.clear();
			
			updateMoves();
			updatePotentialMoves();
			updateAttacks();
			
			
			
			tick = 0;
		}
	}
	
	private void updateMoves()
	{
		Rectangle[] d = new Rectangle[8]; 
		
		d[0] = new Rectangle(x-2*Figure.width, y-Figure.height,width,height);
		d[1] = new Rectangle(x-2*Figure.width, y+Figure.height,width,height);
		d[2] = new Rectangle(x+2*Figure.width, y-Figure.height,width,height);
		d[3] = new Rectangle(x+2*Figure.width, y+Figure.height,width,height);
		d[4] = new Rectangle(x-Figure.width, y-2*Figure.height,width,height);
		d[5] = new Rectangle(x+Figure.width, y-2*Figure.height,width,height);
		d[6] = new Rectangle(x-Figure.width, y+2*Figure.height,width,height);
		d[7] = new Rectangle(x+Figure.width, y+2*Figure.height,width,height);
		
		
		for(Rectangle di : d)
		{
			if(di.getX() >= 0 && di.getY() >= 0 && di.getX() < Figure.width*8 && di.getY() < Figure.height*8)
			{
				if(!isOcuppied((int)di.getX(),(int)di.getY()) && !isMoveForbidden((int)di.getX(),(int)di.getY()))
				{
					possibleMoves.add(di);
				}
			}
		}
	}
	
	private void updatePotentialMoves()
	{
		Rectangle[] d = new Rectangle[8]; 
		
		d[0] = new Rectangle(x-2*Figure.width, y-Figure.height,width,height);
		d[1] = new Rectangle(x-2*Figure.width, y+Figure.height,width,height);
		d[2] = new Rectangle(x+2*Figure.width, y-Figure.height,width,height);
		d[3] = new Rectangle(x+2*Figure.width, y+Figure.height,width,height);
		d[4] = new Rectangle(x-Figure.width, y-2*Figure.height,width,height);
		d[5] = new Rectangle(x+Figure.width, y-2*Figure.height,width,height);
		d[6] = new Rectangle(x-Figure.width, y+2*Figure.height,width,height);
		d[7] = new Rectangle(x+Figure.width, y+2*Figure.height,width,height);
		
		
		for(Rectangle di : d)
		{
			if(di.getX() >= 0 && di.getY() >= 0 && di.getX() < Figure.width*8 && di.getY() < Figure.height*8)
			{
				if(isOcuppied((int)di.getX(),(int)di.getY()))
				{
					potentialMoves.add(di);
				}
			}
		}
	}
	
	private void updateAttacks()
	{
		Rectangle[] d = new Rectangle[8]; 
		
		d[0] = new Rectangle(x-2*Figure.width, y-Figure.height,width,height);
		d[1] = new Rectangle(x-2*Figure.width, y+Figure.height,width,height);
		d[2] = new Rectangle(x+2*Figure.width, y-Figure.height,width,height);
		d[3] = new Rectangle(x+2*Figure.width, y+Figure.height,width,height);
		d[4] = new Rectangle(x-Figure.width, y-2*Figure.height,width,height);
		d[5] = new Rectangle(x+Figure.width, y-2*Figure.height,width,height);
		d[6] = new Rectangle(x-Figure.width, y+2*Figure.height,width,height);
		d[7] = new Rectangle(x+Figure.width, y+2*Figure.height,width,height);
		
		
		for(Rectangle di : d)
		{
			if(di.getX() >= 0 && di.getY() >= 0 && di.getX() < Figure.width*8 && di.getY() < Figure.height*8)
			{
				if(isOcuppied((int)di.getX(),(int)di.getY()) && !isAttackForbidden((int)di.getX(),(int)di.getY()))
					if(handler.getFigureManager().findVirtualFigureByLocation((int)di.getX(), (int)di.getY()).getColor() != color)
						possibleAttacks.add(di);
					
			}
		}
	}
	
	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		
	}

}
