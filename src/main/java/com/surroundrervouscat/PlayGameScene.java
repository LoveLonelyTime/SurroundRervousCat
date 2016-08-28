package com.surroundrervouscat;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;

public class PlayGameScene implements GameScene {

	public static SATTUS[][] map = new SATTUS[9][9];
	public static int catX = 4;
	public static int catY = 4;

	public static final int DIAMETER = 33;
	public static final int LEFT_START_X = 15;
	public static final int RIGHT_START_X = 15;

	public static final int START_Y = 240;

	public static final int X_MARGIN = 3;

	public static final Color GRAY = new Color(181, 181, 181);
	public static final Color ORANGE = new Color(255, 131, 93);

	public static final Animation CAT_ANIMATION = new Animation(
			new Image[] { ResourcesLoader.CAT_NORMAL_1_IMAGE, ResourcesLoader.CAT_NORMAL_2_IMAGE,
					ResourcesLoader.CAT_NORMAL_3_IMAGE, ResourcesLoader.CAT_NORMAL_2_IMAGE },
			10);

	public PlayGameScene() {
		clear();
	}

	public void clear() {
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				map[x][y] = SATTUS.EMPTY;
			}
		}
		map[4][4] = SATTUS.CAT;
		catX = 4;
		catY = 4;
	}

	public void newGame() {
		clear();
		for (int i = 0; i < (((int) (Math.random() * 40D)) + 20); i++) {
			int x = (int) (Math.random() * 8D);
			int y = (int) (Math.random() * 8D);
			if (PlayGameScene.map[x][y] == PlayGameScene.SATTUS.EMPTY) {
				PlayGameScene.map[x][y] = PlayGameScene.SATTUS.FULL;
			}
		}

	}

	@Override
	public void paint(Graphics2D graphics2d, ImageObserver imageObserver) {
		graphics2d.drawImage(ResourcesLoader.BACKGROUND_IMAGE, 0, 0, GameWindow.WIDTH, GameWindow.HEIGHT,
				imageObserver);
		// 迭代map
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				SATTUS status = map[x][y];
				Color color = null;
				if (status == SATTUS.EMPTY) {
					color = GRAY;
				} else if (status == SATTUS.FULL) {
					color = ORANGE;
				} else if (status == SATTUS.CAT) {
					color = GRAY;
				}
				if (y == 0 || y == 2 || y == 4 || y == 6 || y == 8) {
					// 向左突出
					int circleX = LEFT_START_X + x * DIAMETER + x * X_MARGIN;
					int circleY = START_Y + y * DIAMETER + y;
					graphics2d.setColor(color);
					graphics2d.fillOval(circleX, circleY, DIAMETER, DIAMETER);
				} else {
					// 向右突出
					int circleX = RIGHT_START_X + x * DIAMETER + x * X_MARGIN;
					int circleY = START_Y + y * DIAMETER + y;
					graphics2d.setColor(color);
					graphics2d.fillOval(circleX, circleY, DIAMETER, DIAMETER);
				}
			}
		}
		if (catY == 0 || catY == 2 || catY == 4 || catY == 6 || catY == 8) {
			int x = (LEFT_START_X + catX * DIAMETER + catX * X_MARGIN) - 8;
			int y = (START_Y + catY * DIAMETER + catY) - 70;
			graphics2d.drawImage(CAT_ANIMATION.next(), x, y, imageObserver);
		} else {
			int x = (RIGHT_START_X + catX * DIAMETER + catX * X_MARGIN) - 8;
			int y = (START_Y + catY * DIAMETER + catY) - 70;
			graphics2d.drawImage(CAT_ANIMATION.next(), x, y, imageObserver);
		}
	}

	public static enum SATTUS {
		CAT, EMPTY, FULL;
	}

}
