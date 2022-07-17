package com.danceEngine.transition;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.danceEngine.game.Game;

public class FadeToBlackTransition extends Transition {
	private long timer = -1;
	private long time;
	private long delay;
	private boolean waiting = true;

	

	public FadeToBlackTransition(long delay, long time) {
		super();
		this.time = time;
		this.delay = delay;
	}

	@Override
	public void render(Graphics2D g2d, BufferedImage lastImg) {
		if(timer < 0) {
			timer = System.currentTimeMillis();
		}
		
		AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
		g2d.setComposite(alcom);
		g2d.drawImage(lastImg, 0, 0, null);
		
		if(waiting && System.currentTimeMillis() - timer > delay) {
			timer = System.currentTimeMillis();
			waiting = false;
		}
		
		if(!waiting) {
			
			alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Math.min((float)(System.currentTimeMillis() - timer) / time, 1.0f));
			g2d.setComposite(alcom);
			g2d.setColor(Color.black);
			g2d.fillRect(0, 0, Game.width, Game.height);
		}
		
	
	}

	@Override
	public boolean isComplete() {
		return (float)(System.currentTimeMillis() - timer) / (time + delay) >= 1;
	}

	

}
