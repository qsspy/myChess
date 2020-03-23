package dev.game.figures;

import java.awt.Rectangle;
import java.util.ArrayList;

import dev.game.Handler;
import dev.game.ImageManipulation.Assets;

public class PawnFigure extends Figure{
	
	private FigureManager figureManager;

	public PawnFigure(Handler handler,int x, int y, String color, int id, boolean real) {
		super(handler, x, y, color, id, real);
		
		if(color == "white")texture = Assets.whiteFigures[0];
		else 				texture = Assets.blackFigures[0];
	}

	@Override
	public void update() {
		
		if(tick == 1)
		{
			
			
			possibleMoves.clear();
			possibleAttacks.clear();
			potentialMoves.clear();
			
			updateMoves();
			updatePotentialMoves(color);
			updateAttacks();
			
			
			tick = 0;
		}
	}
	
	
	
	private void updateMoves()
	{
		if(color == "black")
		{
			if(!isOcuppied(x,y+Figure.height) && y+Figure.height<Figure.height*8 && !isMoveForbidden(x,y+Figure.height))
			{
				possibleMoves.add(new Rectangle(x,y+Figure.height,height,width));
			}
			if(!hasStarted && !possibleMoves.isEmpty() && !isMoveForbidden(x,y+2*Figure.height))
			{
				possibleMoves.add(new Rectangle(x,y+2*Figure.height,height,width));
			}
		}
		else
		{
			if(!isOcuppied(x,y-Figure.height) && y-Figure.height>=0 && !isMoveForbidden(x,y-Figure.height))
			{
				possibleMoves.add(new Rectangle(x,y-Figure.height,height,width));
			}
			if(!hasStarted && !possibleMoves.isEmpty() && !isMoveForbidden(x,y-2*Figure.height))
			{
				possibleMoves.add(new Rectangle(x,y-2*Figure.height,height,width));
			}
		}
	}
	
	private void updatePotentialMoves(String color)
	{
		if(color == "black")
		{
			if(x - width >= 0 && y + height < height*8)
			{
				potentialMoves.add(new Rectangle(x - width,y + height,width,height));
			}
			if(x + width < width*8 && y + height < height*8)
			{
				potentialMoves.add(new Rectangle(x + width,y + height,width,height));
			}
		}
		else
		{
			if(x - width >= 0 && y - height >= 0)
			{
				potentialMoves.add(new Rectangle(x - width,y - height,width,height));
			}
			if(x + width < width*8 && y - height >= 0)
			{
				potentialMoves.add(new Rectangle(x + width,y - height,width,height));
			}
		}
	}
	
	private void updateAttacks()
	{
		if(color == "black")
		{
			if(x - width >= 0 && y + height < height*8)
			{
				if(isOcuppied(x-width,y + height) && !isAttackForbidden(x - width,y + height))
				{
					if(handler.getFigureManager().findVirtualFigureByLocation(x-width, y+height).getColor() != color)
						possibleAttacks.add(new Rectangle(x-width,y+height,width,height));
				}
			}
			if(x + width < width*8  && y + height < height*8)
			{
				if(isOcuppied(x+width,y + height) && !isAttackForbidden(x + width,y + height))
				{
					if(handler.getFigureManager().findVirtualFigureByLocation(x+width, y+height).getColor() != color)
						possibleAttacks.add(new Rectangle(x+width,y+height,width,height));
				}
			}
		}
		else
		{
			if(x - width >= 0 && y - height >= 0)
			{
				if(isOcuppied(x-width,y - height) && !isAttackForbidden(x - width,y - height))
				{
					if(handler.getFigureManager().findVirtualFigureByLocation(x-width, y-height).getColor() != color)
						possibleAttacks.add(new Rectangle(x-width,y-height,width,height));
				}
			}
			if(x + width < width*8 && y - height >= 0)
			{
				if(isOcuppied(x+width,y - height) && !isAttackForbidden(x + width,y - height))
				{
					if(handler.getFigureManager().findVirtualFigureByLocation(x+width, y-height).getColor() != color)
						possibleAttacks.add(new Rectangle(x+width,y-height,width,height));
				}
			}
		}
	}

	@Override
	public void onClick() {
		
		isClicked = true;
		
		
		
	}
	
	

}
