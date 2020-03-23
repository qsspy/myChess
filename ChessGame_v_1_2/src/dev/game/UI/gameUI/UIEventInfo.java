package dev.game.UI.gameUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import dev.game.Handler;
import dev.game.util.ColouredString;

public class UIEventInfo extends UIInfoRectangle{

	public UIEventInfo(Handler handler, int x, int y, int width, int height, BufferedImage texture, ArrayList<ColouredString> messages) {
		super(handler, x, y, width, height, texture, messages);
		// TODO Auto-generated constructor stub
	}
	
	public void update()
	{
		
	}
	
	public void render(Graphics2D g2d) {
		
		int yOffset = 45;
		
		g2d.setFont(new Font("Franklin Gothic Demi",Font.PLAIN,15));
		g2d.drawImage(texture,x,y,width,height,null);
		
		for(int i=0;i<messages.size();i++)
		{
			if(yOffset > height - 45)break;
			updateFont(i, g2d);
			g2d.drawString(messages.get(i).getString(), x+25, y+yOffset);
			yOffset+=25;
		}
	}
	
	private void updateFont(int i,Graphics2D g2d)
	{
		g2d.setColor(messages.get(i).getColor());
	}
	
	
	

}
