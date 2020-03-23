package dev.game.states;

import java.awt.Graphics;

public abstract class State {
	
	private static State stateActive = null;
	
	public abstract void render(Graphics g);
	
	public abstract void update();

	
	
	//GETTERS SETTERS 
	
	public static State getStateActive() {
		return stateActive;
	}

	public static void setStateActive(State stateActive) {
		State.stateActive = stateActive;
	}
	
	

}
