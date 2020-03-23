package dev.game.UI.gameUI;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import dev.game.Handler;
import dev.game.util.ColouredString;

public class UIMoveTracker extends UIInfoRectangle{

	public UIMoveTracker(Handler handler, int x, int y, int width, int height, BufferedImage texture,
			ArrayList<ColouredString> messages) {
		super(handler, x, y, width, height, texture, messages);
		
		
		
	}
	
	public void update()
	{
	}
	
	public void render(Graphics2D g2d) {
		
		
		g2d.setFont(new Font("Franklin Gothic Demi",Font.PLAIN,20));
		g2d.drawImage(texture,x,y,width,height,null);
		
		
		
		if(!handler.getGameState().isBlackMate() && !handler.getGameState().isWhiteMate())
		{
			updateFont(0,g2d);
			g2d.drawString(messages.get(0).getString() + handler.getGameState().getHowManyMoves()/2, x+25, y+ height/2 + 10);
		}
		else if(handler.getGameState().isBlackMate())
		{
			updateFont(3,g2d);
			g2d.drawString(messages.get(3).getString(), x+20, y+ height/2-10);
			updateFont(1,g2d);
			g2d.drawString(messages.get(1).getString() + handler.getGameState().getHowManyMoves()/2, x+50, y+ height/2+20);
		}
		else if(handler.getGameState().isWhiteMate())
		{
			updateFont(2,g2d);
			g2d.drawString(messages.get(2).getString(), x+20, y+ height/2-10);
			updateFont(1,g2d);
			g2d.drawString(messages.get(1).getString() + handler.getGameState().getHowManyMoves()/2, x+50, y+ height/2+20);
		}
	
	}
	
	private void updateFont(int i,Graphics2D g2d)
	{
		g2d.setColor(messages.get(i).getColor());
	}

}
