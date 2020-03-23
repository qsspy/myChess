package dev.game.figures;

import java.awt.Rectangle;

import dev.game.Handler;
import dev.game.ImageManipulation.Assets;

public class RookFigure extends Figure{
	

	public RookFigure(Handler handler,int x, int y, String color, int id, boolean real) {
		super(handler, x, y, color, id, real);
		
		if(color == "white")texture = Assets.whiteFigures[1];
		else 				texture = Assets.blackFigures[1];
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
		for(int i = x+Figure.width;i<Figure.width*8;i+=Figure.width)
		{
			if(!isOcuppied(i,y) && !isMoveForbidden(i,y))
			{
				possibleMoves.add(new Rectangle(i,y,width,height));
			}
			else if(isOcuppied(i,y))break;
		}
		
		for(int i = x-Figure.width;i>=0;i-=Figure.width)
		{
			if(!isOcuppied(i,y) && !isMoveForbidden(i,y))
			{
				possibleMoves.add(new Rectangle(i,y,width,height));
			}
			else if(isOcuppied(i,y))break;
		}
		
		for(int i = y+Figure.height;i<Figure.height*8;i+=Figure.height)
		{
			if(!isOcuppied(x,i) && !isMoveForbidden(x,i))
			{
				possibleMoves.add(new Rectangle(x,i,width,height));
			}
			else if(isOcuppied(x,i))break;
		}
		
		for(int i = y-Figure.height;i>=0;i-=Figure.height)
		{
			if(!isOcuppied(x,i) && !isMoveForbidden(x,i))
			{
				possibleMoves.add(new Rectangle(x,i,width,height));
			}
			else if(isOcuppied(x,i))break;
		}
	}
	
	private void updatePotentialMoves()
	{
		for(int i = x+Figure.width;i<Figure.width*8;i+=Figure.width)
		{
			if(isOcuppied(i,y))
			{
				potentialMoves.add(new Rectangle(i,y,width,height));
				break;
			}
			
		}
		
		for(int i = x-Figure.width;i>=0;i-=Figure.width)
		{
			if(isOcuppied(i,y))
			{
				potentialMoves.add(new Rectangle(i,y,width,height));
				break;
			}
			
		}
		
		for(int i = y+Figure.height;i<Figure.height*8;i+=Figure.height)
		{
			if(isOcuppied(x,i))
			{
				potentialMoves.add(new Rectangle(x,i,width,height));
				break;
			}
			
		}
		
		for(int i = y-Figure.height;i>=0;i-=Figure.height)
		{
			if(isOcuppied(x,i))
			{
				potentialMoves.add(new Rectangle(x,i,width,height));
				break;
			}
			
		}
	}
	
	private void updateAttacks()
	{
		for(int i = x+Figure.width;i<Figure.width*8;i+=Figure.width)
		{
			if(isOcuppied(i,y) && !isAttackForbidden(i,y))
			{
				if(handler.getFigureManager().findVirtualFigureByLocation(i, y).getColor() == color)break;
				possibleAttacks.add(new Rectangle(i,y,width,height));
				break;
			}
			else if(isOcuppied(i,y))break;
		}
		
		for(int i = x-Figure.width;i>=0;i-=Figure.width)
		{
			if(isOcuppied(i,y) && !isAttackForbidden(i,y))
			{
				if(handler.getFigureManager().findVirtualFigureByLocation(i, y).getColor() == color)break;
				possibleAttacks.add(new Rectangle(i,y,width,height));
				break;
			}
			else if(isOcuppied(i,y))break;
		}
		
		for(int i = y+Figure.height;i<Figure.height*8;i+=Figure.height)
		{
			if(isOcuppied(x,i) && !isAttackForbidden(x,i))
			{
				if(handler.getFigureManager().findVirtualFigureByLocation(x, i).getColor() == color)break;
				possibleAttacks.add(new Rectangle(x,i,width,height));
				break;
			}
			else if(isOcuppied(x,i))break;
		}
		
		for(int i = y-Figure.height;i>=0;i-=Figure.height)
		{
			if(isOcuppied(x,i) && !isAttackForbidden(x,i))
			{
				if(handler.getFigureManager().findVirtualFigureByLocation(x, i).getColor() == color)break;
				possibleAttacks.add(new Rectangle(x,i,width,height));
				break;
			}
			else if(isOcuppied(x,i))break;
		}
	}

	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		
	}

}
