package com.surroundrervouscat;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

/**
 * 游戏场景
 * 
 * @author 爱寂寞的时光
 *
 */
public interface GameScene {
	/**
	 * 绘制
	 * 
	 * @param graphics2d
	 *            画笔
	 * @param imageObserver
	 *            监听器
	 */
	public void paint(Graphics2D graphics2d, ImageObserver imageObserver);
}
