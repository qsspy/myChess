package dev.game.figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import dev.game.Handler;
import dev.game.MouseManager;
import dev.game.util.ColouredString;


public class FigureManager {
	
	private ArrayList<Figure> figures;
	private ArrayList<Figure> virtualFigures;
	private Figure figureToAdd, virtualFigureToAdd;
	private ArrayList<Figure> figuresToRemove;
	private Handler handler;
	private MouseManager mouseManager;
	private Figure activeFigure,secondActive;
	private Figure targetFigure;
	private int xDestination = -1, yDestination = -1;
	private int secondXDestination =-1, secondYDestination=-1;
	private int mouseLock = 1;
	private int currentId;
	private int renderTrigger = 0;
	private int checkInfoTick = 1;
	private int mateInfoTick = 1;
	
	
	public FigureManager(Handler handler)
	{
		figures = new ArrayList<Figure>();
		figuresToRemove = new ArrayList<Figure>();
		virtualFigures = new ArrayList<Figure>();
		
		
		this.handler = handler;
		handler.setFigureManager(this);
		init();
		virtualInit();
		mouseManager = handler.getGame().getMouseManager();
	}
	
	private void init()
	{
		figures.add(new RookFigure(handler,0*Figure.width,0*Figure.height,"black",1,true));
		figures.add(new BishopFigure(handler,2*Figure.width,0*Figure.height,"black",2,true));
		figures.add(new KnightFigure(handler,1*Figure.width,0*Figure.height,"black",3,true));
		figures.add(new KingFigure(handler,4*Figure.width,0*Figure.height,"black",5,true));
		figures.add(new QueenFigure(handler,3*Figure.width,0*Figure.height,"black",4,true));
		figures.add(new KnightFigure(handler,6*Figure.width,0*Figure.height,"black",6,true));
		figures.add(new BishopFigure(handler,5*Figure.width,0*Figure.height,"black",7,true));
		figures.add(new RookFigure(handler,7*Figure.width,0*Figure.height,"black",8,true));
		
		for(int i = 9;i<9+8;i++)
		{
			figures.add(new PawnFigure(handler,(i-9)*Figure.width,1*Figure.height,"black",i,true));
			
		}
		
		for(int i = 17;i<17+8;i++)
		{
			figures.add(new PawnFigure(handler,(i-17)*Figure.width,6*Figure.height,"white",i,true));
		}
		
		figures.add(new RookFigure(handler,0*Figure.width,7*Figure.height,"white",25,true));
		figures.add(new BishopFigure(handler,2*Figure.width,7*Figure.height,"white",26,true));
		figures.add(new KnightFigure(handler,1*Figure.width,7*Figure.height,"white",27,true));
		figures.add(new KingFigure(handler,4*Figure.width,7*Figure.height,"white",28,true));
		figures.add(new QueenFigure(handler,3*Figure.width,7*Figure.height,"white",29,true));
		figures.add(new KnightFigure(handler,6*Figure.width,7*Figure.height,"white",30,true));
		figures.add(new BishopFigure(handler,5*Figure.width,7*Figure.height,"white",31,true));
		figures.add(new RookFigure(handler,7*Figure.width,7*Figure.height,"white",32,true));
		
		currentId = 33;
	}
	
	private void virtualInit()
	{
		virtualFigures.add(new RookFigure(handler,0*Figure.width,0*Figure.height,"black",1,false));
		virtualFigures.add(new BishopFigure(handler,2*Figure.width,0*Figure.height,"black",2,false));
		virtualFigures.add(new KnightFigure(handler,1*Figure.width,0*Figure.height,"black",3,false));
		virtualFigures.add(new KingFigure(handler,4*Figure.width,0*Figure.height,"black",5,false));
		virtualFigures.add(new QueenFigure(handler,3*Figure.width,0*Figure.height,"black",4,false));
		virtualFigures.add(new KnightFigure(handler,6*Figure.width,0*Figure.height,"black",6,false));
		virtualFigures.add(new BishopFigure(handler,5*Figure.width,0*Figure.height,"black",7,false));
		virtualFigures.add(new RookFigure(handler,7*Figure.width,0*Figure.height,"black",8,false));
		
		for(int i = 9;i<9+8;i++)
		{
			virtualFigures.add(new PawnFigure(handler,(i-9)*Figure.width,1*Figure.height,"black",i,false));
			
		}
		
		for(int i = 17;i<17+8;i++)
		{
			virtualFigures.add(new PawnFigure(handler,(i-17)*Figure.width,6*Figure.height,"white",i,false));
		}
		
		virtualFigures.add(new RookFigure(handler,0*Figure.width,7*Figure.height,"white",25,false));
		virtualFigures.add(new BishopFigure(handler,2*Figure.width,7*Figure.height,"white",26,false));
		virtualFigures.add(new KnightFigure(handler,1*Figure.width,7*Figure.height,"white",27,false));
		virtualFigures.add(new KingFigure(handler,4*Figure.width,7*Figure.height,"white",28,false));
		virtualFigures.add(new QueenFigure(handler,3*Figure.width,7*Figure.height,"white",29,false));
		virtualFigures.add(new KnightFigure(handler,6*Figure.width,7*Figure.height,"white",30,false));
		virtualFigures.add(new BishopFigure(handler,5*Figure.width,7*Figure.height,"white",31,false));
		virtualFigures.add(new RookFigure(handler,7*Figure.width,7*Figure.height,"white",32,false));
	}
	
	
	public void removeFigure(int id)
	{
		for(int i = 0;i<figures.size();i++)
		{
			if(figures.get(i).getId() == id)
			{
				figures.remove(i);
				removeVirtualFigure(id);
			}
		}
	}
	
	public void update()
	{	
		for(Figure f : figures)
		{
			
			if(f.getClass().getSimpleName().contentEquals("PawnFigure") )
			{
				pawnToQueenIfPossible(f);
			}
				
		}
		
		updateFigureList();
		
		updateNonKings();
		
		updateChecks(figures);
		
		if(checkInfoTick ==1)sendCheckInfo();
		
		updateKings();
		
		checkForMate();
		
		if(mateInfoTick == 1)sendMateInfo();

		performActionIfPossible();
		
		printInfo();
		
		if(activeFigure != null)
		{
			moveFigureIfPossible(activeFigure,xDestination,yDestination);
			finishMoveIfPossible();
		}
		
		if(secondActive!= null)
		{
			moveFigureIfPossible(secondActive,secondXDestination,secondYDestination);
			finishSecondMoveIfPossible();
		}
	}
	
	public void render(Graphics g)
	{		
		if(renderTrigger == 0)
		{
			for(Figure f : figures)
			{
				f.renderFields(g);
			}
			
			for(Figure f : figures)
			{
				f.renderFigure(g);
			}
			
			if(activeFigure!=null)
				activeFigure.renderFigure(g);
		}
		else
		{			
			for(Figure f : virtualFigures)
			{
				f.renderFields(g);
			}
			
			g.setColor(Color.MAGENTA);
			
			for(int i=0;i<Figure.width*8;i+=Figure.width)
				for(int j=0;j<Figure.height*8;j+=Figure.height)
					g.fillRect(i,j, Figure.width/4, Figure.height/4);
			
			for(Figure f : virtualFigures)
			{
				f.renderFigure(g);
			}
		}
	}

	public ArrayList<Figure> getFigures() {
		return figures;
	}
	
	private void setNewActive(Figure f)
	{
		if(f != null && !f.isLock())
		{
			if(activeFigure == null)
			{
				activeFigure = f;
				f.setClicked(true);
				mouseManager.setLeftPressedGame(false);
			}
			else
			{
				if(f.equals(activeFigure))
				{
					activeFigure.setClicked(false);
					activeFigure = null;
					mouseManager.setLeftPressedGame(false);
				}
				else
				{
					activeFigure.setClicked(false);
					activeFigure = f;
					f.setClicked(true);
					mouseManager.setLeftPressedGame(false);
				}
			}
		}
		else
		{
			if(activeFigure == null)
			{
				mouseManager.setLeftPressedGame(false);
			}
			else
			{
				activeFigure.setClicked(false);
				activeFigure = null;
				mouseManager.setLeftPressedGame(false);
				
			}
		}
	}
	
	public Figure findFigure(int xFigure,int yFigure)
	{
		for(Figure f : figures)
		{
			if(f.bounds.contains(xFigure,yFigure))
			{
				return f;
			}
		}
		return null;
	}
	
	private boolean isMoveChoosen()
	{
		for(Rectangle r : activeFigure.getPossibleMoves())
		{
			if(r.contains(mouseManager.getX(),mouseManager.getY()))
			{
				xDestination = (int)r.getX();
				yDestination = (int)r.getY();
				System.out.println("Destination : " + xDestination + " " + yDestination);
				System.out.println("Position : " + activeFigure.getX() + " " + activeFigure.getY());
				return true;
			}
		}
		return false;
	}
	
	private void moveFigureIfPossible(Figure f, int xDest, int yDest)
	{
		if(f.isMoveAllowX()) f.makeStepX(xDest);
		if(f.isMoveAllowY()) f.makeStepY(yDest);
	}
	
	private void finishMoveIfPossible()
	{
		if(xDestination == activeFigure.getX() && yDestination == activeFigure.getY())
		{
			activeFigure.getBounds().setLocation(xDestination,yDestination);
			if(activeFigure.isHasStarted() == false) activeFigure.setHasStarted(true);
			if(targetFigure != null)
			{
				removeFigure(targetFigure.getId());
				targetFigure = null;
			}
			
			switchTurn();
			
			sendMoveInfo(activeFigure,xDestination,yDestination);
			handler.getGameState().movesIncrement();
			checkInfoTick = 1;
			mateInfoTick = 1;
			
			activeFigure.setMoveAllowX(false);
			activeFigure.setMoveAllowY(false);
			updateVirtualFigurePosition(activeFigure.getId(),xDestination,yDestination);
			xDestination = -1;
			yDestination = -1;
			
			
			activeFigure = null;
			mouseLock++;
			
			if(mouseLock == 1)
			{
				for(Figure f : virtualFigures)
				{
					f.setTick(1);
				}
				
				for(Figure f : figures)
				{
					f.setTick(1);
				}
			}
			
		}
	}
	
	private boolean isAttackChoosen()
	{
		for(Rectangle r : activeFigure.getPossibleAttacks())
		{
			if(r.contains(mouseManager.getX(),mouseManager.getY()))
			{
				xDestination = (int)r.getX();
				yDestination = (int)r.getY();
				targetFigure = findFigure(xDestination,yDestination);
				System.out.println("Destination : " + xDestination + " " + yDestination);
				System.out.println("Position : " + activeFigure.getX() + " " + activeFigure.getY());
				return true;
			}
		}
		return false;
	}
	
	private void pawnToQueenIfPossible(Figure f)
	{
		if(f.getColor() == "white")
		{
			if(f.getY() == 0) QueenForPawn(f);
		}
		else
		{
			if(f.getY() == Figure.height*7) QueenForPawn(f);
		}
	}
	
	public void QueenForPawn(Figure f)
	{
		
		figureToAdd = new QueenFigure(handler,f.getX(),f.getY(),f.getColor(),currentId,true);
		figureToAdd.setLock(true);
		virtualFigureToAdd = new QueenFigure(handler,f.getX(),f.getY(),f.getColor(),currentId,false);
		currentId++;
		figuresToRemove.add(f);
		System.out.println("Pawn changed!");
	}
	
	public void updateFigureList()
	{
		
		if(figureToAdd != null)figures.add(figureToAdd);
		if(virtualFigureToAdd != null)virtualFigures.add(virtualFigureToAdd);
		
		figureToAdd = null;
		virtualFigureToAdd = null;
		
		for(Figure f : figuresToRemove)
		{
			removeFigure(f.getId());
		}
		
		figuresToRemove.clear();
	}
	
	public boolean fieldEndangered(int x, int y,String color,ArrayList <Figure> list)
	{
		for(Figure f : list)
		{
			if(f.getColor() != color)
			{
				if(f.getClass().getSimpleName().contentEquals("PawnFigure")) //if figure is pawn check only potential moves
				{
					for(Rectangle r : f.getPotentialMoves())
					{
						if(r.getX() == x && r.getY() == y) return true;
					}
				}
				else
				{
					for(Rectangle r : f.getPotentialMoves())
					{
						if(r.getX() == x && r.getY() == y) return true;
					}
					
					for(Rectangle r : f.getPossibleMoves())
					{
						if(r.getX() == x && r.getY() == y) return true;
					}
					
					for(Rectangle r : f.getPossibleAttacks())
					{
						if(r.getX() == x && r.getY() == y) return true;
					}
				}
			}
		}
		return false;
	}
	
	private boolean isCastlingChoosen()
	{
		for(Rectangle r : activeFigure.getCastlingFields())
		{
			if(r.contains(mouseManager.getX(),mouseManager.getY()))
			{
				if(r.getX()<activeFigure.getX()) //long castling
				{
					xDestination = (int)r.getX();
					yDestination = (int)r.getY();
					secondXDestination = xDestination + Figure.width;
					secondYDestination = yDestination;
					secondActive = findFigure(activeFigure.getX()-4*Figure.width,activeFigure.getY());
				}
				else							//short castling
				{
					xDestination = (int)r.getX();
					yDestination = (int)r.getY();
					secondXDestination = xDestination - Figure.width;
					secondYDestination = yDestination;
					secondActive = findFigure(activeFigure.getX()+3*Figure.width,activeFigure.getY());
				}
				System.out.println("Destination : " + xDestination + " " + yDestination);
				System.out.println("Position : " + activeFigure.getX() + " " + activeFigure.getY());
				return true;
			}
		}
		return false;
	}
	
	private void finishSecondMoveIfPossible()
	{
		if(secondXDestination == secondActive.getX() && secondYDestination == secondActive.getY())
		{
			secondActive.getBounds().setLocation(secondXDestination,secondYDestination);
			if(secondActive.isHasStarted() == false) secondActive.setHasStarted(true);

			secondActive.setMoveAllowX(false);
			secondActive.setMoveAllowY(false);
			updateVirtualFigurePosition(secondActive.getId(),secondXDestination,secondYDestination);
			secondActive = null;
			secondXDestination = -1;
			secondYDestination = -1;
			mouseLock++;
			
			if(mouseLock == 1)
			{
				for(Figure f : virtualFigures)
				{
					f.setTick(1);
				}
				
				for(Figure f : figures)
				{
					f.setTick(1);
				}
			}
		}
	}
	
	public void whiteLock()
	{
		for(Figure f : figures)
		{
			if(f.getColor() == "white") f.setLock(true);
		}
	}
	
	public void blackLock()
	{
		for(Figure f : figures)
		{
			if(f.getColor() == "black") f.setLock(true);
		}
	}
	
	public void whiteUnlock()
	{
		for(Figure f : figures)
		{
			if(f.getColor() == "white") f.setLock(false);
		}
	}
	
	public void blackUnlock()
	{
		for(Figure f : figures)
		{
			if(f.getColor() == "black") f.setLock(false);
		}
	}
	
	public void lockAll()
	{
		whiteLock();
		blackLock();
	}
	
	
	private void switchTurn()
	{
		if(activeFigure.getColor() == "white")
		{
				whiteLock();
				blackUnlock();
				handler.getGameState().setBlackTurn(true);
				handler.getGameState().setWhiteTurn(false);
		}
		else
		{
				whiteUnlock();
				blackLock();
				handler.getGameState().setBlackTurn(false);
				handler.getGameState().setWhiteTurn(true);
		}
		System.out.println("Turn switched!");
		
	}
	
	private void performActionIfPossible()
	{
		if(mouseLock == 1)
		{
			if(mouseManager.isLeftPressedGame())
			{
				if(activeFigure != null)
				{
					if(isMoveChoosen())
					{
						mouseLock--;
						activeFigure.setMoveAllowX(true);
						activeFigure.setMoveAllowY(true);
						activeFigure.setClicked(false);
						mouseManager.setLeftPressedGame(false);
						System.out.println("Peep!");
						
					}
					else if(isAttackChoosen())
					{
						mouseLock--;
						activeFigure.setMoveAllowX(true);
						activeFigure.setMoveAllowY(true);
						activeFigure.setClicked(false);
						mouseManager.setLeftPressedGame(false);
						System.out.println("Peep!");
						
					}
					else if(isCastlingChoosen())
					{
						mouseLock-=2;
						activeFigure.setMoveAllowX(true);
						activeFigure.setMoveAllowY(true);
						activeFigure.setClicked(false);
						secondActive.setMoveAllowX(true);
						secondActive.setMoveAllowY(true);
						mouseManager.setLeftPressedGame(false);
						System.out.println("Peep!");
						
					}
					else setNewActive(findFigure(mouseManager.getX(),mouseManager.getY()));
					
				}
				else setNewActive(findFigure(mouseManager.getX(),mouseManager.getY()));
			}
		}
	}
	
	private void updateChecks(ArrayList<Figure> list)
	{
		for(Figure f : list)
		{
			if(f.getClass().getSimpleName().contentEquals("KingFigure")
					/*&& f.getX()%Figure.width == 0 && f.getY()%Figure.height == 0*/)
			{
				if(fieldEndangered(f.getX(),f.getY(),f.getColor(),list))
				{
					if(f.getColor() == "white")handler.getGameState().setWhiteCheck(true);
					if(f.getColor() == "black")handler.getGameState().setBlackCheck(true);
				}
				else
				{
					if(f.getColor() == "white")handler.getGameState().setWhiteCheck(false);
					if(f.getColor() == "black")handler.getGameState().setBlackCheck(false);
				}
			}
		}
	}
	
	public void checkForMate()
	{
		if(handler.getGameState().isWhiteTurn())
		{
			for(Figure f : figures)
			{
				if(!f.lock)
				{
					if(!f.getPossibleAttacks().isEmpty() || !f.getPossibleMoves().isEmpty())return;
				}
			}
			handler.getGameState().setWhiteMate(true);
			lockAll();
		}
		else
		{
			for(Figure f : figures)
			{
				if(!f.lock)
				{
					if(!f.getPossibleAttacks().isEmpty() || !f.getPossibleMoves().isEmpty())return;
				}
			}
			handler.getGameState().setBlackMate(true);
			lockAll();
		}
	}
	
	public void updateVirtualFigurePosition(int id, int x, int y)
	{
		for(Figure f : virtualFigures)
		{
			if(f.getId() == id)
			{
				f.getBounds().setLocation(x,y);
				f.setX(x);
				f.setY(y);
				break;
			}
		}
	}

	public ArrayList<Figure> getVirtualFigures() {
		return virtualFigures;
	}

	public void setVirtualFigures(ArrayList<Figure> virtualFigures) {
		this.virtualFigures = virtualFigures;
	}
	
	public void updateVirtualFigures()
	{
		for(Figure f : virtualFigures)
		{
			if(!f.getClass().getSimpleName().contentEquals("KingFigure"))f.update();
		}
		
	}
	
	public void updateVirtualKings()
	{
		for(Figure f : virtualFigures)
		{
			if(f.getClass().getSimpleName().contentEquals("KingFigure"))f.update();
		}
	}
	
	
	private void updateRealFigures()
	{
		for(Figure f : figures)
		{
			if(!f.getClass().getSimpleName().contentEquals("KingFigure"))f.update();
		}
	}
		
	
	private void updateRealKings()
	{
		for(Figure f : figures)
		{
			if(f.getClass().getSimpleName().contentEquals("KingFigure"))f.update();
		}
	}
	
	private void updateNonKings()
	{
		updateVirtualFigures();
		updateRealFigures();
	}
	
	private void updateKings()
	{
		updateVirtualKings();
		updateRealKings();
	}
	
	private void printInfo()
	{
		if(mouseManager.isRightPressedGame())
		{
			if(renderTrigger == 0)renderTrigger = 1;
			else renderTrigger = 0;
			
			
			for(int i = 0;i<figures.size();i++)
			{
				System.out.print("Real : " + figures.get(i).getX() + "  " + figures.get(i).getY());
				System.out.println("      Virtual : " + virtualFigures.get(i).getX() + "  " + virtualFigures.get(i).getY());
				if(figures.get(i).getX() != virtualFigures.get(i).getX() || figures.get(i).getY() != virtualFigures.get(i).getY())
				{
					System.out.println("ERROR - POSITION DONT MATCH");
					break;
				}
			}
			
			for(Figure f : virtualFigures)
			{
				if(f.getColor() == "white" && f.getClass().getSimpleName().contentEquals("KingFigure"))
				{
					if(fieldEndangered(f.getX(),f.getY(),"white",virtualFigures))
					{
						System.out.println("\nWHITE KING IN DANGER\n!");
					}
				}
			}
			
			System.out.println(virtualFigures.get(4).getClass().getSimpleName());
			
			for(Rectangle f : virtualFigures.get(4).getPossibleMoves())
			{
				
				System.out.println("X : " +f.getX()/Figure.width+" Y : "+f.getY()/Figure.height);
			}
			
			System.out.println(virtualFigures.get(8).getClass().getSimpleName());
			
			System.out.println(virtualFigures.get(8).getX()/Figure.width+" "+virtualFigures.get(8).getY()/Figure.height);
			
			for(Rectangle f : virtualFigures.get(8).getPossibleMoves())
			{
				
				System.out.println("X : " +f.getX()/Figure.width+" Y : "+f.getY()/Figure.height);
			}

			mouseManager.setRightPressedGame(false);
			
			
		}
	}
	
	public Figure findVirtualFigureById(int id)
	{
		for(Figure f : virtualFigures)
		{
			if(f.getId() == id) return f;
		}
		return null;
	}
	
	public Figure findVirtualFigureByLocation(int xLoc, int yLoc)
	{
		for(Figure f : virtualFigures)
		{
			if(f.getX() == xLoc && f.getY() == yLoc) return f;
		}
		System.out.println("I coudnt find virtual figure to delete");
		return null;
	}
	
	public void setVirtualTick(int num)
	{
		for(Figure f : virtualFigures)
		{
			f.setTick(num);
		}
	}
	
	public void removeVirtualFigure(int id)
	{
		for(int i =0;i<virtualFigures.size();i++)
		{
			if(virtualFigures.get(i).getId() == id)
			{
				virtualFigures.remove(i);
				break;
			}
		}
	}
	
	public String numToChar(int num)
	{
		switch(num)
		{
		case 0 :
			return "A";
		case 1 :
			return "B";
		case 2 :
			return "C";
		case 3 :
			return "D";
		case 4 :
			return "E";
		case 5 :
			return "F";
		case 6 :
			return "G";
		default :
			return "H";
		}
	}
	
	public void sendMoveInfo(Figure f,int x, int y)
	{
		String name = f.getClass().getSimpleName();
		
		handler.getUiManager().getEventInfo().pushEvent
			(new ColouredString(name.replace("Figure", "") + " moved to " + numToChar(x/Figure.width) + " " + y/Figure.height,f.getColorClass()));
	}
	
	public void sendCheckInfo()
	{
		if(handler.getGameState().isWhiteCheck())
		{
			handler.getUiManager().getEventInfo().pushEvent
				(new ColouredString("Check On Whites!",Color.RED));
		}
		else if(handler.getGameState().isBlackCheck())
		{
			handler.getUiManager().getEventInfo().pushEvent
				(new ColouredString("Check On Blacks!",Color.RED));
		}
		checkInfoTick = 0;
	} 
	
	public void sendMateInfo()
	{
		
		if(handler.getGameState().isWhiteMate() || handler.getGameState().isBlackMate())
		{
			handler.getUiManager().getEventInfo().pushEvent
				(new ColouredString("Check-Mate!",Color.YELLOW));
		}
		
		mateInfoTick = 0;
	} 
	
	public void reset()
	{
		figures.clear();
		virtualFigures.clear();
		
		init();
		virtualInit();
	}
	
}
