package dev.game.figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import dev.game.GP;
import dev.game.Handler;
import dev.game.ImageManipulation.Animation;
import dev.game.ImageManipulation.Assets;

public abstract class Figure {
	
	protected final int id;
	protected BufferedImage texture;
	protected int x;
	protected int y;
	protected String color;
	protected Color colorClass;
	protected ArrayList<Rectangle> possibleMoves;
	protected ArrayList<Rectangle> possibleAttacks;
	protected ArrayList<Rectangle> potentialMoves;
	protected ArrayList<Rectangle> castlingFields;
	protected ArrayList<Figure> figureList;
	protected Handler handler;
	protected int tick = 1;
	protected boolean isClicked = false;
	protected boolean moveAllowX = false;
	protected boolean moveAllowY = false;
	protected boolean hasStarted = false;
	protected boolean lock = false;
	protected boolean virtualWhiteCheck = false;
	protected boolean virtualBlackCheck = false;
	protected boolean real;
	protected Rectangle bounds;
	protected Point hoveringMemory;
	protected int speed = 8;
	public static final int width = GP.DISPLAY_WIDTH/GP.TILES_PER_ROW;
	public static final int height = GP.DISPLAY_HEIGHT/GP.TILES_PER_COLUMN;
	
	public Figure(Handler handler,int x,int y, String color, int id, boolean real)
	{
		possibleMoves = new ArrayList<Rectangle>();
		possibleAttacks = new ArrayList<Rectangle>();
		potentialMoves = new ArrayList<Rectangle>();
		castlingFields = new ArrayList<Rectangle>();
		this.real = real;
		this.id = id;
		this.x = x;
		this.y = y;
		this.color = color;
		if(color == "white") this.colorClass = Color.WHITE;
		else this.colorClass = Color.BLACK;
		this.handler = handler;
		bounds = new Rectangle(x,y,width,height);
		if(real)figureList = handler.getFigureManager().getFigures();
		else 	figureList = handler.getFigureManager().getVirtualFigures();
	}
	
	public abstract void update();
	
	public void makeStepX(int xDestination)
	{
		if(x < xDestination)
		{
			for(int i =0;i<speed;i++)
			{
				if(x == xDestination)
				{
					//moveAllowX = false;
					break;
				}
				x++;
			}
			
		}
		else if(x > xDestination)
		{
			for(int i =0;i<speed;i++)
			{
				if(x == xDestination)
				{
					//moveAllowX = false;
					break;
				}
				x--;
			}
		}
		else
		{
			//moveAllowX = false;
		}
	}
	
	public void makeStepY(int yDestination)
	{
		if(y < yDestination)
		{
			for(int i =0;i<speed;i++)
			{
				if(y == yDestination)
				{
					//moveAllowY = false;
					break;
				}
				y++;
			}
		}
		else if(y > yDestination)
		{
			for(int i =0;i<speed;i++)
			{
				if(y == yDestination)
				{
					//moveAllowY = false;
					break;
				}
				y--;
			}
		}
		//else moveAllowY = false;
	}
		
	
	public void renderFigure(Graphics g)
	{
		g.drawImage(texture,x,y,width,height,null);
	}
	
	public void renderFields(Graphics g)
	{		
		if(isClicked)
		{
			
			if(mouseHovering(new Rectangle(x,y,width,height)))
				Animation.drawFading(Assets.pinkField,x,y, width, height, (Graphics2D)g);
			else
				g.drawImage(Assets.pinkField,x,y, width, height,null);
			
			
			for(Rectangle d : possibleMoves)
			{
				if(mouseHovering(d))
					Animation.drawFading(Assets.greenField,(int)d.getX(), (int)d.getY(), width, height, (Graphics2D)g);
				else
					g.drawImage(Assets.greenField,(int)d.getX(), (int)d.getY(), width, height,null);
			}
			
			for(Rectangle d : possibleAttacks)
			{
				if(mouseHovering(d))
					Animation.drawFading(Assets.yellowField,(int)d.getX(), (int)d.getY(), width, height, (Graphics2D)g);
				else
					g.drawImage(Assets.yellowField,(int)d.getX(), (int)d.getY(), width, height,null);
			}
			
			for(Rectangle d : castlingFields)
			{
				if(mouseHovering(d))
					Animation.drawFading(Assets.cyanField,(int)d.getX(), (int)d.getY(), width, height, (Graphics2D)g);
				else
					g.drawImage(Assets.cyanField,(int)d.getX(), (int)d.getY(), width, height,null);
			}
			
			
			
			
		}
	}
	
	public abstract void onClick();
	
	public boolean isOcuppied(int x, int y)
	{
		for(Figure f : figureList)
		{
			if(f.getX() == x && f.getY() == y)
			{
				//checkForAttackMove(f);
				//checkForPotentialMove(f);
				return true;
			}
		}
		return false;
	}
	
	protected void checkForAttackMove(Figure f)
	{
		if(f.color != color)
		{
			possibleAttacks.add(new Rectangle(f.getX(),f.getY(),width,height));
		}
	}
	
	protected void checkForPotentialMove(Figure f)
	{
		if(f.color == color)
		{
			potentialMoves.add(new Rectangle(f.getX(),f.getY(),width,height));
		}
	}
	
	protected boolean isMoveForbidden(int xPos, int yPos)
	{
		if(real)
		{
			int xOrigin = x;
			int yOrigin = y;
			
			handler.getFigureManager().updateVirtualFigurePosition(id, xPos, yPos);
			handler.getFigureManager().setVirtualTick(1);
			handler.getFigureManager().updateVirtualFigures();
			handler.getFigureManager().updateVirtualKings();
			if(isThereVirtualCheck(xPos,yPos))
			{
				handler.getFigureManager().updateVirtualFigurePosition(id, xOrigin, yOrigin);
				handler.getFigureManager().setVirtualTick(1);
				handler.getFigureManager().updateVirtualFigures();
				handler.getFigureManager().updateVirtualKings();
				return true;
			}
			else
			{
				handler.getFigureManager().updateVirtualFigurePosition(id, xOrigin, yOrigin);
				handler.getFigureManager().setVirtualTick(1);
				handler.getFigureManager().updateVirtualFigures();
				handler.getFigureManager().updateVirtualKings();
				return false;
			}
		}
		else return false;
	}
	
	protected boolean isAttackForbidden(int xPos, int yPos)
	{
		Figure memory = handler.getFigureManager().findVirtualFigureByLocation(xPos, yPos);
		if(real && memory != null)
		{
			int xOrigin = x;
			int yOrigin = y;
			
			
			handler.getFigureManager().removeVirtualFigure(memory.getId());
			handler.getFigureManager().updateVirtualFigurePosition(id, xPos, yPos);
			handler.getFigureManager().setVirtualTick(1);
			handler.getFigureManager().updateVirtualFigures();
			handler.getFigureManager().updateVirtualKings();
			if(isThereVirtualCheck(xPos,yPos))
			{
				
				handler.getFigureManager().updateVirtualFigurePosition(id, xOrigin, yOrigin);
				handler.getFigureManager().getVirtualFigures().add(memory);
				handler.getFigureManager().setVirtualTick(1);
				handler.getFigureManager().updateVirtualFigures();
				handler.getFigureManager().updateVirtualKings();
				return true;
			}
			else
			{
				handler.getFigureManager().updateVirtualFigurePosition(id, xOrigin, yOrigin);
				handler.getFigureManager().getVirtualFigures().add(memory);
				handler.getFigureManager().setVirtualTick(1);
				handler.getFigureManager().updateVirtualFigures();
				handler.getFigureManager().updateVirtualKings();
				return false;
			}
		}
		else return false;
	}
	
	protected boolean isThereVirtualCheck(int xPos,int yPos)
	{
		for(Figure f : handler.getFigureManager().getVirtualFigures())
		{
			if(f.getColor() == color && f.getClass().getSimpleName().contentEquals("KingFigure"))
			{
	
				if(handler.getFigureManager().fieldEndangered(f.getX(), f.getY(), color, handler.getFigureManager().getVirtualFigures()))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	protected boolean mouseHovering(Rectangle r)
	{
		if(r.contains(handler.getGame().getMouseManager().getX(),handler.getGame().getMouseManager().getY()))
			return true;
		return false;
	}
	//Getters Setters

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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public ArrayList<Rectangle> getPossibleMoves() {
		return possibleMoves;
	}

	public void setPossibleMoves(ArrayList<Rectangle> possibleMoves) {
		this.possibleMoves = possibleMoves;
	}

	public boolean isClicked() {
		return isClicked;
	}

	public void setClicked(boolean isClicked) {
		this.isClicked = isClicked;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public boolean isMoveAllowX() {
		return moveAllowX;
	}

	public void setMoveAllowX(boolean moveAllowX) {
		this.moveAllowX = moveAllowX;
	}

	public boolean isMoveAllowY() {
		return moveAllowY;
	}

	public void setMoveAllowY(boolean moveAllowY) {
		this.moveAllowY = moveAllowY;
	}

	public int getTick() {
		return tick;
	}

	public void setTick(int tick) {
		this.tick = tick;
	}

	public boolean isHasStarted() {
		return hasStarted;
	}

	public void setHasStarted(boolean hasStarted) {
		this.hasStarted = hasStarted;
	}

	public ArrayList<Rectangle> getPossibleAttacks() {
		return possibleAttacks;
	}

	public void setPossibleAttacks(ArrayList<Rectangle> possibleAttacks) {
		this.possibleAttacks = possibleAttacks;
	}

	public ArrayList<Rectangle> getPotentialMoves() {
		return potentialMoves;
	}

	public void setPotentialMoves(ArrayList<Rectangle> potentialMoves) {
		this.potentialMoves = potentialMoves;
	}

	public int getId() {
		return id;
	}

	public ArrayList<Rectangle> getCastlingFields() {
		return castlingFields;
	}

	public void setCastlingFields(ArrayList<Rectangle> castlingFields) {
		this.castlingFields = castlingFields;
	}

	public boolean isLock() {
		return lock;
	}

	public void setLock(boolean lock) {
		this.lock = lock;
	}

	public ArrayList<Figure> getFigureList() {
		return figureList;
	}

	public void setFigureList(ArrayList<Figure> figureList) {
		this.figureList = figureList;
	}

	public Color getColorClass() {
		return colorClass;
	}

	public void setColorClass(Color colorClass) {
		this.colorClass = colorClass;
	}
	
	
	
	
	
	

}
