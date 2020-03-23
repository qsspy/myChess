package dev.game.figures;

import java.awt.Dimension;
import java.awt.Rectangle;

import dev.game.Handler;
import dev.game.ImageManipulation.Assets;

public class BishopFigure extends Figure{

	public BishopFigure(Handler handler,int x, int y, String color, int id, boolean real) {
		super(handler,x, y, color, id, real);
		
		if(color == "white")texture = Assets.whiteFigures[2];
		else 				texture = Assets.blackFigures[2];
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
		int i,j;
		
		i= x -Figure.width;
		j= y -Figure.height;
		while(i >=0 && j>=0)
		{
			if(!isOcuppied(i,j) && !isMoveForbidden(i,j))
			{
				possibleMoves.add(new Rectangle(i,j,width,height));
			}
			else if(isOcuppied(i,j))break;
			
			i-=Figure.width;
			j-=Figure.height;
		}
		
		i= x -Figure.width;
		j= y +Figure.height;
		while(i >=0 && j<Figure.height*8)
		{
			if(!isOcuppied(i,j) && !isMoveForbidden(i,j))
			{
				possibleMoves.add(new Rectangle(i,j,width,height));
			}
			else if(isOcuppied(i,j))break;
			
			i-=Figure.width;
			j+=Figure.height;
		}
		
		i= x +Figure.width;
		j= y +Figure.height;
		while(i<Figure.width*8 && j<Figure.height*8)
		{
			if(!isOcuppied(i,j) && !isMoveForbidden(i,j))
			{
				possibleMoves.add(new Rectangle(i,j,width,height));
			}
			else if(isOcuppied(i,j))break;
			
			i+=Figure.width;
			j+=Figure.height;
		}
		
		i= x +Figure.width;
		j= y -Figure.height;
		while(i<Figure.width*8 && j>=0)
		{
			if(!isOcuppied(i,j) && !isMoveForbidden(i,j))
			{
				possibleMoves.add(new Rectangle(i,j,width,height));
			}
			else if(isOcuppied(i,j))break;
			
			i+=Figure.width;
			j-=Figure.height;
		}
	}
	
	private void updatePotentialMoves()
	{
		int i,j;
		
		i= x -Figure.width;
		j= y -Figure.height;
		while(i >=0 && j>=0)
		{
			if(isOcuppied(i,j))
			{
				potentialMoves.add(new Rectangle(i,j,width,height));
				break;
			}

			
			i-=Figure.width;
			j-=Figure.height;
		}
		
		i= x -Figure.width;
		j= y +Figure.height;
		while(i >=0 && j<Figure.height*8)
		{
			if(isOcuppied(i,j))
			{
				potentialMoves.add(new Rectangle(i,j,width,height));
				break;
			}
			
			i-=Figure.width;
			j+=Figure.height;
		}
		
		i= x +Figure.width;
		j= y +Figure.height;
		while(i<Figure.width*8 && j<Figure.height*8)
		{
			if(isOcuppied(i,j))
			{
				potentialMoves.add(new Rectangle(i,j,width,height));
				break;
			}
			
			i+=Figure.width;
			j+=Figure.height;
		}
		
		i= x +Figure.width;
		j= y -Figure.height;
		while(i<Figure.width*8 && j>=0)
		{
			if(isOcuppied(i,j))
			{
				potentialMoves.add(new Rectangle(i,j,width,height));
				break;
			}
			
			i+=Figure.width;
			j-=Figure.height;
		}
	}
	
	private void updateAttacks()
	{
		int i,j;
		
		i= x -Figure.width;
		j= y -Figure.height;
		while(i >=0 && j>=0)
		{
			if(isOcuppied(i,j) && !isAttackForbidden(i,j))
			{
				if(handler.getFigureManager().findVirtualFigureByLocation(i, j).getColor() == color)break;
				possibleAttacks.add(new Rectangle(i,j,width,height));
				break;
			}
			else if(isOcuppied(i,j))break;
			
			i-=Figure.width;
			j-=Figure.height;
		}
		
		i= x -Figure.width;
		j= y +Figure.height;
		while(i >=0 && j<Figure.height*8)
		{
			if(isOcuppied(i,j) && !isAttackForbidden(i,j))
			{
				if(handler.getFigureManager().findVirtualFigureByLocation(i, j).getColor() == color)break;
				possibleAttacks.add(new Rectangle(i,j,width,height));
				break;
			}
			else if(isOcuppied(i,j))break;
			
			
			i-=Figure.width;
			j+=Figure.height;
		}
		
		i= x +Figure.width;
		j= y +Figure.height;
		while(i<Figure.width*8 && j<Figure.height*8)
		{
			if(isOcuppied(i,j) && !isAttackForbidden(i,j))
			{
				if(handler.getFigureManager().findVirtualFigureByLocation(i, j).getColor() == color)break;
				possibleAttacks.add(new Rectangle(i,j,width,height));
				break;
			}
			else if(isOcuppied(i,j))break;
			
			
			i+=Figure.width;
			j+=Figure.height;
		}
		
		i= x +Figure.width;
		j= y -Figure.height;
		while(i<Figure.width*8 && j>=0)
		{
			if(isOcuppied(i,j) && !isAttackForbidden(i,j))
			{
				if(handler.getFigureManager().findVirtualFigureByLocation(i, j).getColor() == color)break;
				possibleAttacks.add(new Rectangle(i,j,width,height));
				break;
			}
			else if(isOcuppied(i,j))break;
			
			
			i+=Figure.width;
			j-=Figure.height;
		}
	}

	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		
	}


	
	

	

}
