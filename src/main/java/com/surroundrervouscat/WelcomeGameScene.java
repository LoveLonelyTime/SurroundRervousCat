package com.surroundrervouscat;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

public class WelcomeGameScene implements GameScene {

	public static boolean down;

	public static final int SATRT_BUTTON_X = 105;
	public static final int SATRT_BUTTON_Y = 448;
	public static final int SATRT_BUTTON_WIDTH = 149;
	public static final int SATRT_BUTTON_HEIGHT = 47;

	public static final int DOWN_SATRT_BUTTON_X = 102;
	public static final int DOWN_SATRT_BUTTON_Y = 445;
	public static final int DOWN_SATRT_BUTTON_WIDTH = 155;
	public static final int DOWN_SATRT_BUTTON_HEIGHT = 53;

	@Override
	public void paint(Graphics2D graphics2d, ImageObserver imageObserver) {
		graphics2d.drawImage(ResourcesLoader.WELCOME_IMAGE, 0, 0, GameWindow.WIDTH, GameWindow.HEIGHT, imageObserver);
		if (down) {
			graphics2d.drawImage(ResourcesLoader.START_BUTTON_IMAGE, DOWN_SATRT_BUTTON_X, DOWN_SATRT_BUTTON_Y,
					DOWN_SATRT_BUTTON_WIDTH, DOWN_SATRT_BUTTON_HEIGHT, imageObserver);
		} else {
			graphics2d.drawImage(ResourcesLoader.START_BUTTON_IMAGE, SATRT_BUTTON_X, SATRT_BUTTON_Y, SATRT_BUTTON_WIDTH,
					SATRT_BUTTON_HEIGHT, imageObserver);
		}

	}

}
