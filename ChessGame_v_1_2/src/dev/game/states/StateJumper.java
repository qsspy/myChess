package dev.game.states;

import dev.game.Handler;

public class StateJumper {
	
	private State gameState;
	private State menuState;
	private Handler handler;
	
	public StateJumper(Handler handler)
	{
		this.handler = handler;
	}
	
	public void update()
	{
		if(State.getStateActive() == gameState)
		{
			if(handler.getGame().getKeyManager().isEscPressed())
			{
				State.setStateActive(menuState);
				handler.getGame().getKeyManager().setEscPressed(false);
			}
		}
		else if(State.getStateActive() == menuState)
		{
			if(handler.getGame().getKeyManager().isEscPressed())
			{
				State.setStateActive(gameState);
				handler.getGame().getKeyManager().setEscPressed(false);
			}
		}
	}

	public State getGameState() {
		return gameState;
	}

	public void setGameState(State gameState) {
		this.gameState = gameState;
	}

	public State getMenuState() {
		return menuState;
	}

	public void setMenuState(State menuState) {
		this.menuState = menuState;
	}

	
}
