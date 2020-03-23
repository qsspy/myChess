package dev.game.states;

import java.awt.Graphics;
import java.awt.Graphics2D;

import dev.game.GP;
import dev.game.Handler;
import dev.game.ImageManipulation.Assets;
import dev.game.UI.gameUI.UIManager;
import dev.game.figures.FigureManager;
import dev.game.tiles.Board;
import dev.game.tiles.Tile;

public class GameState extends State{
	
	private boolean whiteCheck = false;
	private boolean blackCheck = false;
	private boolean whiteMate = false;
	private boolean blackMate = false;
	private boolean whiteTurn;
	private boolean blackTurn;
	private int howManyMoves;
	private Board board;
	private FigureManager figureManager;
	private Handler handler;
	private UIManager uiManager;
	
	public GameState(Handler handler)
	{
		this.handler = handler;
		handler.setGameState(this);
		
		init();
	}

	@Override
	public void render(Graphics g) {
		board.render(g);
		figureManager.render(g);
		uiManager.render((Graphics2D)g);
		g.drawImage(Assets.yellowShadow,0,0,GP.DISPLAY_WIDTH+GP.UI_SPACE,GP.DISPLAY_HEIGHT,null);
		
	}

	@Override
	public void update() {
		board.update();
		handler.getGame().getMouseManager().updateHoveringMemory();
		figureManager.update();
		uiManager.update();
		
	}

	public FigureManager getFigureManager() {
		return figureManager;
	}
	
	private void init()
	{
		board = new Board(new Tile(Assets.tiles[0]),new Tile(Assets.tiles[1]));
		figureManager = new FigureManager(handler);
		uiManager = new UIManager(handler);
		
		double randNum = Math.random();
		if(randNum < 0.5f)
		{
			handler.getFigureManager().whiteLock();
			blackTurn = true;
			whiteTurn = false;
		}
		else
		{
			handler.getFigureManager().blackLock();
			blackTurn = false;
			whiteTurn = true;
		}
	}
	
	public void reset()
	{
		figureManager.reset();
		uiManager.reset();
		howManyMoves = 0;
		whiteMate = false;
		blackMate = false;
		
		double randNum = Math.random();
		if(randNum < 0.5f)
		{
			handler.getFigureManager().whiteLock();
			blackTurn = true;
			whiteTurn = false;
		}
		else
		{
			handler.getFigureManager().blackLock();
			blackTurn = false;
			whiteTurn = true;
		}
	}
	
	public void movesIncrement()
	{
		howManyMoves++;
	}

	public boolean isWhiteCheck() {
		return whiteCheck;
	}

	public void setWhiteCheck(boolean whiteCheck) {
		this.whiteCheck = whiteCheck;
	}

	public boolean isBlackCheck() {
		return blackCheck;
	}

	public void setBlackCheck(boolean blackCheck) {
		this.blackCheck = blackCheck;
	}

	public boolean isWhiteMate() {
		return whiteMate;
	}

	public void setWhiteMate(boolean whiteMate) {
		this.whiteMate = whiteMate;
	}

	public boolean isBlackMate() {
		return blackMate;
	}

	public void setBlackMate(boolean blackMate) {
		this.blackMate = blackMate;
	}

	public boolean isWhiteTurn() {
		return whiteTurn;
	}

	public void setWhiteTurn(boolean whiteTurn) {
		this.whiteTurn = whiteTurn;
	}

	public boolean isBlackTurn() {
		return blackTurn;
	}

	public void setBlackTurn(boolean blackTurn) {
		this.blackTurn = blackTurn;
	}

	public int getHowManyMoves() {
		return howManyMoves;
	}

	public void setHowManyMoves(int howManyMoves) {
		this.howManyMoves = howManyMoves;
	}
	
	
	
	

}
