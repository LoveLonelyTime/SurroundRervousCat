package com.surroundrervouscat;

import java.awt.Insets;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.surroundrervouscat.astar.Astart;
import com.surroundrervouscat.astar.MapInfo;
import com.surroundrervouscat.astar.Node;

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
				((PlayGameScene) GameSceneManager.PLAY_GAME_SCENE).newGame();
			}
		} else if (GameSceneManager.getGameScene() == GameSceneManager.PLAY_GAME_SCENE) {
			int y = (e.getY() - PlayGameScene.START_Y) / PlayGameScene.DIAMETER + 1;
			int x = 0;
			if (y == 1 || y == 3 || y == 5 || y == 7 || y == 9) {
				x = (e.getX() - PlayGameScene.LEFT_START_X) / (PlayGameScene.DIAMETER + PlayGameScene.X_MARGIN);
			} else {
				x = (e.getX() - PlayGameScene.RIGHT_START_X) / (PlayGameScene.DIAMETER + PlayGameScene.X_MARGIN);
			}
			if (x >= 0 && y >= 1 && x < 9 && y < 10) {
				if (PlayGameScene.map[x][y - 1] == PlayGameScene.SATTUS.EMPTY) {
					// 有效操作
					PlayGameScene.map[x][y - 1] = PlayGameScene.SATTUS.FULL;
					MapInfo[] mapInfos = new MapInfo[32];
					List<List<Point>> paths = new ArrayList<List<Point>>();
					int xi = 0;
					int yi = 0;
					for (int i = 0; i < mapInfos.length; i++) {
						mapInfos[i] = new MapInfo(PlayGameScene.map, 9, 9,
								new Node(PlayGameScene.catX, PlayGameScene.catY), new Node(xi, yi));
						System.out.println(new Point(xi, yi));
						if (xi == 0 || xi == 8) {
							if (yi != 8) {
								yi++;
							} else {
								xi++;
								yi = 0;
							}
						} else {
							if (yi == 0) {
								yi = 8;
							} else {
								yi = 0;
								xi++;
							}
						}
					}
					for (int i = 0; i < mapInfos.length; i++) {
						Astart astart = new Astart();
						astart.start(mapInfos[i]);
						if (astart.getPath().size() != 0) {
							paths.add(astart.getPath());
						}
					}
					if (paths.size() != 0) {
						int minPathStep = paths.get(0).size();
						List<Point> minPath = paths.get(0);
						for (List<Point> list : paths) {
							if (list.size() < minPathStep) {
								minPathStep = list.size();
								minPath = list;
							}
						}
						if (minPath.size() == 1) {
							// 输
							JOptionPane.showMessageDialog(this, "你让他跑了，精神病院长又疯了一次", "失败", JOptionPane.PLAIN_MESSAGE);
							((PlayGameScene) GameSceneManager.PLAY_GAME_SCENE).newGame();
						} else {
							PlayGameScene.map[PlayGameScene.catX][PlayGameScene.catY] = PlayGameScene.SATTUS.EMPTY;
							PlayGameScene.catX = minPath.get(minPath.size() - 2).x;
							PlayGameScene.catY = minPath.get(minPath.size() - 2).y;
							PlayGameScene.map[PlayGameScene.catX][PlayGameScene.catY] = PlayGameScene.SATTUS.CAT;
						}
					} else {
						// 赢
						JOptionPane.showMessageDialog(this, "逮住了神经猫，超过全国1%的玩家", "成功", JOptionPane.PLAIN_MESSAGE);
						((PlayGameScene) GameSceneManager.PLAY_GAME_SCENE).newGame();
					}
				}
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
