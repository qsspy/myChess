package dev.game.ImageManipulation;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Animation {
	
	public static float OPACITY = 1.0f;
	public static float SPEED = 0.015f;
	
	public static float LOWEST_OPACITY = 0.4f;
	public static float HIGHEST_OPACITY = 1.0f;
	
	private static boolean increasing = false;
	private static boolean decreasing = true;
	
	public static void drawFading(BufferedImage texture,int x, int y, int width, int height, Graphics2D g2D)
	{
		if(OPACITY >= HIGHEST_OPACITY)
		{
			decreasing = true;
			increasing = false;
		}
		else if(OPACITY < LOWEST_OPACITY)
		{
			decreasing = false;
			increasing = true;
		}
		
		if(increasing)
		{
			OPACITY+=SPEED;
		}
		if(decreasing)
		{
			OPACITY-=SPEED;
		}
		
		g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, OPACITY));
		g2D.drawImage(texture,x,y,width,height,null);
		g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		
		
		
	}

}
