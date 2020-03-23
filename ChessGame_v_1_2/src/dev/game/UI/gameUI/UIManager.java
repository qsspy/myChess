package dev.game.UI.gameUI;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import dev.game.GP;
import dev.game.Handler;
import dev.game.ImageManipulation.Assets;
import dev.game.util.ColouredString;

public class UIManager {

	private UIInfoRectangle turnInfo;
	private UIInfoRectangle eventInfo;
	private UIInfoRectangle moveInfo;
	
	private Handler handler;
	private int tick = 1;
	
	public UIManager(Handler handler)
	{
		this.handler = handler;
		handler.setUiManager(this);
		
		init();
	}
	
	private void init()
	{
		ArrayList<ColouredString> turnMessages = new ArrayList<ColouredString>();
		turnMessages.add(new ColouredString("White Turn"));
		turnMessages.add(new ColouredString("Black Turn"));
		turnInfo = new UITurnInfo(handler,GP.DISPLAY_WIDTH,0,GP.UI_SPACE,100,Assets.woodenFrame,turnMessages);
		
		ArrayList<ColouredString> events = new ArrayList<ColouredString>();
		eventInfo = new UIEventInfo(handler,GP.DISPLAY_WIDTH,100,GP.UI_SPACE,GP.DISPLAY_HEIGHT-200,Assets.woodenFrame,events);
		
		ArrayList<ColouredString> moveCount = new ArrayList<ColouredString>();
		moveCount.add(new ColouredString("Total Moves : ",Color.WHITE));
		moveCount.add(new ColouredString("Moves : ",Color.YELLOW));
		moveCount.add(new ColouredString("Black has won in : ",Color.BLACK));
		moveCount.add(new ColouredString("White has won in : ",Color.WHITE));
		moveInfo = new UIMoveTracker(handler,GP.DISPLAY_WIDTH,GP.DISPLAY_HEIGHT-100,GP.UI_SPACE,100,Assets.woodenFrame,moveCount);
	}
	
	public void update()
	{
		turnInfo.update();
		eventInfo.update();
		moveInfo.update();
		
	}
	
	public void render(Graphics2D g2d)
	{
		turnInfo.render(g2d);
		eventInfo.render(g2d);
		moveInfo.render(g2d);
	}
	
	public void reset()
	{
		init();
	}

	public int getTick() {
		return tick;
	}

	public void setTick(int tick) {
		this.tick = tick;
	}

	public UIInfoRectangle getEventInfo() {
		return eventInfo;
	}

	public void setEventInfo(UIInfoRectangle eventInfo) {
		this.eventInfo = eventInfo;
	}
	
	
}
