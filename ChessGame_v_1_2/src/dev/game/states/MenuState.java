package dev.game.states;

import java.awt.Graphics;

import dev.game.Handler;
import dev.game.ImageManipulation.Assets;
import dev.game.UI.menuUI.COManager;
import dev.game.UI.menuUI.MenuBackground;

public class MenuState extends State{
	
	private Handler handler;
	private MenuBackground mb;
	private COManager co;

	
	public MenuState(Handler handler)
	{
		this.handler = handler;
		handler.setMenuState(this);
		mb = new MenuBackground(Assets.menuBackground);
		co = new COManager(handler);
	}

	@Override
	public void render(Graphics g) 
	{
		mb.render(g);
		co.render(g);
		
	}

	@Override
	public void update() 
	{
		mb.update();
		co.update();
		
	}
	
	

}
