package com.surroundrervouscat;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

public class PlayGameScene implements GameScene {

	public static SATTUS[][] MAP = new SATTUS[9][9];

	@Override
	public void paint(Graphics2D graphics2d, ImageObserver imageObserver) {
		graphics2d.drawImage(ResourcesLoader.BACKGROUND_IMAGE, 0, 0, GameWindow.WIDTH, GameWindow.HEIGHT,
				imageObserver);
	}

	public static enum SATTUS {
		CAT, EMPTY, FULL;
	}

}
