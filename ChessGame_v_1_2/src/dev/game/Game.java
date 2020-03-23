package dev.game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import dev.game.ImageManipulation.Assets;
import dev.game.states.GameState;
import dev.game.states.MenuState;
import dev.game.states.State;
import dev.game.states.StateJumper;

public class Game implements Runnable{
	
	//Display,Multithread,Graphics rendering setup
	
	private Display d;
	private String name;
	private Graphics g;
	private boolean isRunning = true;
	private Thread t;
	private BufferStrategy bs;
	private BufferedImage test;
	
	//Handler
	
	private Handler handler;
	
	//States
	
	private State gameState;
	private State menuState;
	private StateJumper stateJumper;
	
	//MouseManager
	
	private MouseManager mouseManager;
	
	//KeyManager
	
	private KeyManager keyManager;

	public Game(String name)
	{
		this.name = name;
		handler = new Handler();
		handler.setGame(this);
		this.mouseManager = new MouseManager(handler);
		this.keyManager = new KeyManager();
		
		
		
	}
	
	
	public void init()
	{
		d = new Display(name,GP.DISPLAY_WIDTH+GP.UI_SPACE,GP.DISPLAY_HEIGHT);
		d.getFrame().addMouseListener(mouseManager);
		d.getFrame().addMouseMotionListener(mouseManager);
		d.getCanvas().addMouseListener(mouseManager);
		d.getCanvas().addMouseMotionListener(mouseManager);
		d.getFrame().addKeyListener(keyManager);
		
		Assets.loadAssets();
		
		
		
		menuState = new MenuState(handler);
		gameState = new GameState(handler);
		stateJumper = new StateJumper(handler);
		stateJumper.setGameState(gameState);
		stateJumper.setMenuState(menuState);
		
		
		State.setStateActive(menuState);
	}
	
	public void update()
	{
		stateJumper.update();
		State.getStateActive().update();
	}
	
	public void render(Graphics g)
	{
		bs = d.getCanvas().getBufferStrategy();
		if(bs == null)
		{
			d.getCanvas().createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		
		g.clearRect(0, 0, GP.DISPLAY_WIDTH+GP.UI_SPACE, GP.DISPLAY_HEIGHT); //clearing
		
		//Start Drawing
		
		State.getStateActive().render(g);
		
		
		//Stop Drawing
		
		bs.show();
		g.dispose();
	}
	
	@Override
	public void run() 
	{
		init();
		
		int fps = 60;
		long now=0;
		long lastTime=System.nanoTime();
		int ticks = 0;
		double timePerTick = 1000000000/fps;
		double delta = 0;
		long timer = 0;
		
		while(isRunning)
		{
			now = System.nanoTime();
			delta+=(now - lastTime)/timePerTick;
			timer+=now - lastTime;
			lastTime = now;
			
			if(delta >= 1)
			{
				update();
				render(g);
				ticks++;
				delta--;
			}
			
			if(timer >= 1000000000)
			{
				System.out.println("FPS : " + ticks);
				timer-=1000000000;
				ticks = 0;
			}
			
		}
		
		stop();
		
	}
	
	public synchronized void start()
	{
		t = new Thread(this);
		t.start();
		isRunning = true;
	}
	
	public synchronized void stop()
	{
		if(isRunning)
		{
			try {
				t.join();
				isRunning = false;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}

	public MouseManager getMouseManager() {
		return mouseManager;
	}
	
	public void setMouseManager(MouseManager mouseManager) {
		this.mouseManager = mouseManager;
	}


	public KeyManager getKeyManager() {
		return keyManager;
	}


	public void setKeyManager(KeyManager keyManager) {
		this.keyManager = keyManager;
	}
	
	
	

}
