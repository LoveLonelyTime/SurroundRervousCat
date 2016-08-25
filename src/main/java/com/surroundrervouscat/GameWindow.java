package com.surroundrervouscat;

import java.awt.Insets;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 游戏窗口
 * 
 * @author 爱寂寞的时光
 *
 */
@SuppressWarnings("serial")
public class GameWindow extends JFrame implements MouseListener {

	public static final int WIDTH = 360;
	public static final int HEIGHT = 640;

	private JPanel panel = new GamePanel();

	/**
	 * 运行游戏
	 */
	public void run() {
		// 加载图像
		MediaTracker mediaTracker = new MediaTracker(this);
		mediaTracker.addImage(ResourcesLoader.INSTRUCTION_IMAGE, 1);
		mediaTracker.addImage(ResourcesLoader.WELCOME_IMAGE, 2);
		mediaTracker.addImage(ResourcesLoader.START_BUTTON_IMAGE, 3);
		mediaTracker.addImage(ResourcesLoader.BACKGROUND_IMAGE, 4);
		try {
			mediaTracker.waitForAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 加载窗口
		this.setTitle("围住神经猫");
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().add(panel);
		this.getContentPane().addMouseListener(this);
		this.setVisible(true);
		GameSceneManager.chooseInstructionGameScene();
		while (this.isVisible()) {
			this.getContentPane().repaint();
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void addNotify() {
		super.addNotify();
		Insets insets = this.getInsets();
		this.setSize(WIDTH + insets.left + insets.right, HEIGHT + insets.bottom + insets.top);
		this.setLocationRelativeTo(null);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (GameSceneManager.getGameScene() == GameSceneManager.WELCOME_GAME_SCENE) {
			if (Utils.isPointInRectangle(new Point(e.getX(), e.getY()),
					new Rectangle(WelcomeGameScene.SATRT_BUTTON_X, WelcomeGameScene.SATRT_BUTTON_Y,
							WelcomeGameScene.SATRT_BUTTON_WIDTH, WelcomeGameScene.SATRT_BUTTON_HEIGHT))) {
				GameSceneManager.choosePlayGameScene();
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (GameSceneManager.getGameScene() == GameSceneManager.WELCOME_GAME_SCENE) {
			if (Utils.isPointInRectangle(new Point(e.getX(), e.getY()),
					new Rectangle(WelcomeGameScene.SATRT_BUTTON_X, WelcomeGameScene.SATRT_BUTTON_Y,
							WelcomeGameScene.SATRT_BUTTON_WIDTH, WelcomeGameScene.SATRT_BUTTON_HEIGHT))) {
				WelcomeGameScene.down = true;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (GameSceneManager.getGameScene() == GameSceneManager.WELCOME_GAME_SCENE && WelcomeGameScene.down == true) {
			WelcomeGameScene.down = false;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
