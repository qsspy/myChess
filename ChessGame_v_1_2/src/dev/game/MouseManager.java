package dev.game;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import dev.game.ImageManipulation.Animation;
import dev.game.figures.Figure;
import dev.game.states.State;

public class MouseManager implements MouseListener,MouseMotionListener{
	
	private int x,y;
	private boolean leftPressedGame;
	private boolean leftPressedMenu;
	private boolean rightPressedGame;
	private boolean rightPressedMenu;
	private Point hoveringMemory;
	private Handler handler;
	
	public MouseManager(Handler handler)
	{
		this.handler = handler;
		hoveringMemory = new Point(0,0);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		if(e.getButton() == MouseEvent.BUTTON1)
		{
			if(State.getStateActive() == handler.getGameState())leftPressedGame = true;
			else if(State.getStateActive() == handler.getMenuState())leftPressedMenu = true;
		}
		
		if(e.getButton() == MouseEvent.BUTTON3)
		{
			if(State.getStateActive() == handler.getGameState())rightPressedGame = true;
			else if(State.getStateActive() == handler.getMenuState())rightPressedMenu = true;
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void updateHoveringMemory()
	{
		if((int)x/Figure.width != hoveringMemory.getX() || (int)y/Figure.height != hoveringMemory.getY())
		{
			Animation.OPACITY = 1.0f;
			hoveringMemory.setLocation(x/Figure.width, y/Figure.height);
		}
	}

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

	public boolean isLeftPressedGame() {
		return leftPressedGame;
	}

	public void setLeftPressedGame(boolean leftPressedGame) {
		this.leftPressedGame = leftPressedGame;
	}

	public boolean isRightPressedGame() {
		return rightPressedGame;
	}

	public void setRightPressedGame(boolean rightPressedGame) {
		this.rightPressedGame = rightPressedGame;
	}

	public boolean isLeftPressedMenu() {
		return leftPressedMenu;
	}

	public void setLeftPressedMenu(boolean leftPressedMenu) {
		this.leftPressedMenu = leftPressedMenu;
	}

	public boolean isRightPressedMenu() {
		return rightPressedMenu;
	}

	public void setRightPressedMenu(boolean rightPressedMenu) {
		this.rightPressedMenu = rightPressedMenu;
	}
	
	

}
