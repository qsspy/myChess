package dev.game.figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import dev.game.Handler;
import dev.game.ImageManipulation.Assets;

public class KingFigure extends Figure{
	
	
	public KingFigure(Handler handler,int x, int y, String color,int id, boolean real) {
		super(handler, x, y, color,id, real);
		
		
		if(color == "white")texture = Assets.whiteFigures[5];
		else 				texture = Assets.blackFigures[5];
		
	}
	
	public void update() {
		
		if(tick == 1)
		{
			possibleMoves.clear();
			possibleAttacks.clear();
			potentialMoves.clear();
			castlingFields.clear();
			
			checkForLongCastling();
			checkForShortCastling();
			
			updateMoves();
			updatePotentialMoves();
			updateAttacks();
			
			
			tick = 0;
		}
		
	}
	
	public void renderFigure(Graphics g)
	{
		if(!isClicked)
		{
			if(color == "white" && handler.getGameState().isWhiteCheck())
			{
				g.drawImage(Assets.redField,x,y,width,height,null);
			}
			if(color == "black" && handler.getGameState().isBlackCheck())
			{
				g.drawImage(Assets.redField,x,y,width,height,null);
			}
		}
		
		g.drawImage(texture,x,y,width,height,null);
	}
	
	private void checkForLongCastling()
	{
		if(!hasStarted && handler.getFigureManager().findFigure(x - 4*width, y)!= null) 
			if(!handler.getFigureManager().findFigure(x - 4*width, y).isHasStarted())//both figures hasnt started yet
				if(!isRowFragmentOcuppied(x-3*width,x-width,y))					//space beetween king and rook is empty
					if(!fastCheckForCheck())					//there is no check
						if(!isRowFragmentEndangered(x-3*width,x-width,y))		//the fields which king passes cannot be endangered
							castlingFields.add(new Rectangle(x-2*width,y,width,height));
	}
	
	private void checkForShortCastling()
	{
		if(!hasStarted && handler.getFigureManager().findFigure(x + 3*width, y)!= null) 
			if(!handler.getFigureManager().findFigure(x + 3*width, y).isHasStarted())//both figures hasnt started yet
				if(!isRowFragmentOcuppied(x+width,x+2*width,y))					//space beetween king and rook is empty
					if(!fastCheckForCheck())					//there is no check
						if(!isRowFragmentEndangered(x+width,x+2*width,y))		//the fields which king passes cannot be endangered
							castlingFields.add(new Rectangle(x+2*width,y,width,height));
	}
	
	
	private boolean isOcuppiedLogic(int x, int y)
	{
		for(Figure f : figureList)
		{
			if(f.getX() == x && f.getY() == y)
			{
				return true;
			}
		}
		return false;
	}
	
	private boolean isRowFragmentOcuppied(int xStart,int xEnd, int yRow )
	{
		for(int i=xStart;i<=xEnd;i+=width)
		{
			if(isOcuppiedLogic(i,yRow))return true;
		}
		return false;
	}
	
	private boolean isRowFragmentEndangered(int xStart,int xEnd, int yRow )
	{
		for(int i=xStart;i<=xEnd;i+=width)
		{
			if(handler.getFigureManager().fieldEndangered(i, yRow, color,handler.getFigureManager().getFigures())) return true;
		}
		return false;
	}
	
	private boolean fastCheckForCheck()
	{
		if(color == "white")
		{
			if(handler.getGameState().isWhiteCheck())
				return true;
			else return false;
		}
		else
		{
			if(handler.getGameState().isBlackCheck())
				return true;
			else return false;
		}
	}
	
	private void updateMoves()
	{
		for(int i = x-Figure.width;i<=x + Figure.width;i+=Figure.width)
		{
			for(int j = y - Figure.height;j<=y + Figure.height;j+=Figure.height)
			{
				if(i < 0 || j < 0 || i>= Figure.width*8 || j>= Figure.height*8 || (i ==x && j == y)) continue;
				else
				{
					if(!isOcuppied(i,j) && !isMoveForbidden(i,j))
						possibleMoves.add(new Rectangle(i,j,width,height));
				}
			}
		}
	}
	
	private void updatePotentialMoves()
	{
		for(int i = x-Figure.width;i<=x + Figure.width;i+=Figure.width)
		{
			for(int j = y - Figure.height;j<=y + Figure.height;j+=Figure.height)
			{
				if(i < 0 || j < 0 || i>= Figure.width*8 || j>= Figure.height*8 || (i ==x && j == y)) continue;
				else
				{
					if(isOcuppied(i,j))
						potentialMoves.add(new Rectangle(i,j,width,height));
				}
			}
		}
	}
	
	private void updateAttacks()
	{
		for(int i = x-Figure.width;i<=x + Figure.width;i+=Figure.width)
		{
			for(int j = y - Figure.height;j<=y + Figure.height;j+=Figure.height)
			{
				if(i < 0 || j < 0 || i>= Figure.width*8 || j>= Figure.height*8 || (i ==x && j == y)) continue;
				else
				{
					if(isOcuppied(i,j) && !isAttackForbidden(i,j))
						if(handler.getFigureManager().findVirtualFigureByLocation(i, j).getColor() != color)
							possibleAttacks.add(new Rectangle(i,j,width,height));
						
				}
			}
		}
	}

	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		
	}

	
	
	

}
