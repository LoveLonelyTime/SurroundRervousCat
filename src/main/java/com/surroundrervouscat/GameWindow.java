package com.surroundrervouscat;

import javax.swing.JFrame;

/**
 * 游戏窗口
 * 
 * @author 爱寂寞的时光
 *
 */
@SuppressWarnings("serial")
public class GameWindow extends JFrame {
	/**
	 * 运行游戏
	 */
	public void run() {
		this.setTitle("围住神经猫");
		this.setResizable(false);
		// 这句话是忽略系统通知的重绘信息，因为我们自己会定时重绘
		this.setIgnoreRepaint(true);
		// 这句话的居中窗口
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
