package com.surroundrervouscat;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	private BufferedImage buffer = new BufferedImage(GameWindow.WIDTH, GameWindow.HEIGHT, BufferedImage.TYPE_INT_ARGB);

	@Override
	public void paint(Graphics g) {
		GameScene gameScene = GameSceneManager.getGameScene();
		if (gameScene != null) {
			gameScene.paint((Graphics2D) g, this);
		} else {
			g.fillRect(0, 0, buffer.getWidth(), buffer.getHeight());
		}
	}

	@Override
	public void update(Graphics g) {
		Graphics graphics = buffer.getGraphics();
		graphics.clearRect(0, 0, buffer.getWidth(), buffer.getHeight());
		paint(graphics);
		g.drawImage(buffer, 0, 0, this);
		graphics.dispose();
	}
}
