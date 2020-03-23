package dev.game.ImageManipulation;

import java.awt.image.BufferedImage;

import dev.game.GP;

public class Assets {
	
	
	public static BufferedImage[] tiles;
	public static BufferedImage[] blackFigures;
	public static BufferedImage[] whiteFigures;
	public static BufferedImage redField,greenField,yellowField,cyanField,pinkField;
	public static BufferedImage woodenFrame;
	public static BufferedImage yellowShadow;
	public static BufferedImage menuBackground;
	public static BufferedImage[] startButton;
	public static BufferedImage[] continueButton;
	public static BufferedImage[] quitButton;

	public static void loadAssets()
	{
		BufferedImage tileTexture = ImageLoader.loadImage("/textures/tiles.png");
		
		tiles = new BufferedImage[2];
		
		tiles[0] = ImageLoader.cutOut(tileTexture, 0, 0, GP.TTL, GP.TTL);
		tiles[1] = ImageLoader.cutOut(tileTexture, GP.TTL, 0, GP.TTL, GP.TTL);
		
		redField = ImageLoader.loadImage("/textures/redField.png");
		greenField = ImageLoader.loadImage("/textures/greenField.png");
		yellowField = ImageLoader.loadImage("/textures/yellowField.png");
		cyanField = ImageLoader.loadImage("/textures/castlingField.png");
		pinkField = ImageLoader.loadImage("/textures/pinkField.png");
		
		BufferedImage figureSheet = ImageLoader.loadImage("/textures/chessFigures.png");
		
		blackFigures = new BufferedImage[6];
		whiteFigures = new BufferedImage[6];
		
		whiteFigures[0] = ImageLoader.cutOut(figureSheet,0*GP.FTL,0*GP.FTL,GP.FTL,GP.FTL);
		whiteFigures[1] = ImageLoader.cutOut(figureSheet,1*GP.FTL,0*GP.FTL,GP.FTL,GP.FTL);
		whiteFigures[2] = ImageLoader.cutOut(figureSheet,2*GP.FTL,0*GP.FTL,GP.FTL,GP.FTL);
		whiteFigures[3] = ImageLoader.cutOut(figureSheet,3*GP.FTL,0*GP.FTL,GP.FTL,GP.FTL);
		whiteFigures[4] = ImageLoader.cutOut(figureSheet,0*GP.FTL,1*GP.FTL,GP.FTL,GP.FTL);
		whiteFigures[5] = ImageLoader.cutOut(figureSheet,1*GP.FTL,1*GP.FTL,GP.FTL,GP.FTL);
		
		blackFigures[0] = ImageLoader.cutOut(figureSheet,0*GP.FTL,2*GP.FTL,GP.FTL,GP.FTL);
		blackFigures[1] = ImageLoader.cutOut(figureSheet,1*GP.FTL,2*GP.FTL,GP.FTL,GP.FTL);
		blackFigures[2] = ImageLoader.cutOut(figureSheet,2*GP.FTL,2*GP.FTL,GP.FTL,GP.FTL);
		blackFigures[3] = ImageLoader.cutOut(figureSheet,3*GP.FTL,2*GP.FTL,GP.FTL,GP.FTL);
		blackFigures[4] = ImageLoader.cutOut(figureSheet,2*GP.FTL,1*GP.FTL,GP.FTL,GP.FTL);
		blackFigures[5] = ImageLoader.cutOut(figureSheet,3*GP.FTL,1*GP.FTL,GP.FTL,GP.FTL);
		
		woodenFrame = ImageLoader.loadImage("/textures/woodenFrame.png");
		yellowShadow = ImageLoader.loadImage("/textures/yellowShadow.png");
		
		menuBackground = ImageLoader.loadImage("/textures/menuBackground.png");
		
		startButton = new BufferedImage[2];
		startButton[0] = ImageLoader.loadImage("/textures/startGameButton1.png");
		startButton[1] = ImageLoader.loadImage("/textures/startGameButton2.png");
		
		continueButton = new BufferedImage[2];
		continueButton[0] = ImageLoader.loadImage("/textures/continueGameButton1.png");
		continueButton[1] = ImageLoader.loadImage("/textures/continueGameButton2.png");
		
		quitButton = new BufferedImage[2];
		quitButton[0] = ImageLoader.loadImage("/textures/quitGameButton1.png");
		quitButton[1] = ImageLoader.loadImage("/textures/quitGameButton2.png");
		
		
		
		
	}
	
	public Assets()
	{
		
	}
	
}
