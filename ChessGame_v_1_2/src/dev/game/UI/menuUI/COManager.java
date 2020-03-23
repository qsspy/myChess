package dev.game.UI.menuUI;

import java.awt.Graphics;
import java.util.ArrayList;

import dev.game.GP;
import dev.game.Handler;
import dev.game.ImageManipulation.Assets;
import dev.game.states.State;

public class COManager {
	
	private ArrayList<ClickableObject> objectList;
	private Handler handler;
	private boolean gameStarted = false; 
	
	public COManager(Handler handler)
	{
		objectList = new ArrayList<ClickableObject>();
		this.handler = handler;
		
		init();
	}
	
	public void init()
	{
		objectList.add(new ClickableObject(handler,Assets.startButton
				,(GP.DISPLAY_WIDTH+GP.UI_SPACE)/2-Assets.startButton[0].getWidth()/2
				,GP.DISPLAY_HEIGHT*1/5
				,Assets.startButton[0].getWidth(),Assets.startButton[0].getHeight()
				,new ClickListener() {

			@Override
			public void onClick() {
				
				if(!gameStarted)
				{
					State.setStateActive(handler.getGameState());
					gameStarted = true;
				}
				else
				{
					handler.getGameState().reset();
					State.setStateActive(handler.getGameState());
				}

				
			}}));
		
		objectList.add(new ClickableObject(handler,Assets.continueButton
				,(GP.DISPLAY_WIDTH+GP.UI_SPACE)/2-Assets.continueButton[0].getWidth()/2
				,GP.DISPLAY_HEIGHT*2/5
				,Assets.continueButton[0].getWidth(),Assets.continueButton[0].getHeight()
				,new ClickListener() {

			@Override
			public void onClick() {
				
				if(gameStarted)
				{
					State.setStateActive(handler.getGameState());
				}
				

				
			}}));
		
		objectList.add(new ClickableObject(handler,Assets.quitButton
				,(GP.DISPLAY_WIDTH+GP.UI_SPACE)/2-Assets.quitButton[0].getWidth()/2
				,GP.DISPLAY_HEIGHT*3/5
				,Assets.quitButton[0].getWidth(),Assets.quitButton[0].getHeight()
				,new ClickListener() {

			@Override
			public void onClick() {
				
				System.exit(0);
				
			}}));
	}
	
	public void render(Graphics g)
	{
		for(ClickableObject co : objectList)
		{
			co.render(g);
		}
	}
	
	public void update()
	{
		for(ClickableObject co : objectList)
		{
			co.update();
		}
	}

}
