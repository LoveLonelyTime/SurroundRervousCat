package com.surroundrervouscat;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

public class InstructionGameScene implements GameScene {

	private boolean load;

	private Thread delay;

	public InstructionGameScene() {
		delay = new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				GameSceneManager.chooseWelcomeGameScene();
			}
		};
	}

	@Override
	public void paint(Graphics2D graphics2d, ImageObserver imageObserver) {
		graphics2d.drawImage(ResourcesLoader.INSTRUCTION_IMAGE, 0, 0, GameWindow.WIDTH, GameWindow.HEIGHT,
				imageObserver);
		if (!load) {
			load = true;
			delay.start();
		}
	}

}
