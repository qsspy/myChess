package dev.game.ImageManipulation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	
	public static BufferedImage loadImage(String path)
	{
		try {
			return ImageIO.read(ImageLoader.class.getResource(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
	public static BufferedImage cutOut(BufferedImage image, int x, int y, int width, int height)
	{
		return image.getSubimage(x, y, width, height);
	}

}
