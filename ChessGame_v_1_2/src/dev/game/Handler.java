package dev.game;

import dev.game.UI.gameUI.UIManager;
import dev.game.figures.FigureManager;
import dev.game.states.GameState;
import dev.game.states.MenuState;

public class Handler {
	
	private Game game;
	private GameState gameState;
	private MenuState menuState;
	private FigureManager figureManager;
	private UIManager uiManager;
	
	public Handler()
	{
		
	}

	public Game getGame() {
		return game;
	}

	

	public void setGame(Game game) {
		this.game = game;
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public FigureManager getFigureManager() {
		return figureManager;
	}

	public void setFigureManager(FigureManager figureManager) {
		this.figureManager = figureManager;
	}

	public UIManager getUiManager() {
		return uiManager;
	}

	public void setUiManager(UIManager uiManager) {
		this.uiManager = uiManager;
	}

	public MenuState getMenuState() {
		return menuState;
	}

	public void setMenuState(MenuState menuState) {
		this.menuState = menuState;
	}
	
	
	
	

	
	
	

	
	
	
	
	

}
