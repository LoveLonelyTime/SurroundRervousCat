package com.surroundrervouscat;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	private BufferedImage buffer = new BufferedImage(GameWindow.WIDTH, GameWindow.HEIGHT, BufferedImage.TYPE_INT_ARGB);

	@Override
	public void paint(Graphics g) {
		GameScene gameScene = GameSceneManager.getGameScene();
		if (gameScene != null) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			gameScene.paint(g2, this);
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
