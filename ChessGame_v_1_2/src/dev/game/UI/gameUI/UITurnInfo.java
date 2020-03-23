package dev.game.UI.gameUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import dev.game.Handler;
import dev.game.util.ColouredString;

public class UITurnInfo extends UIInfoRectangle{

	public UITurnInfo(Handler handler, int x, int y, int width, int height, BufferedImage texture, ArrayList<ColouredString> messages) {
		super(handler, x, y, width, height, texture, messages);
		
	}

	@Override
	public void update() {
		
		
	}

	@Override
	public void render(Graphics2D g2d) {
		
		g2d.setFont(new Font("Franklin Gothic Demi",Font.PLAIN,30));
		g2d.drawImage(texture,x,y,width,height,null);
		
		updateFont(g2d);
		g2d.drawString(activeString.getString(), x+25, y+ height/2 + 10);
	}
	
	private void updateFont(Graphics2D g2d)
	{
		if(handler.getGameState().isWhiteTurn())
		{
			g2d.setColor(new Color(255,255,255));
			activeString = messages.get(0);
		}
		else
		{
			g2d.setColor(new Color(0,0,0));
			activeString = messages.get(1);
		}
	}

}
