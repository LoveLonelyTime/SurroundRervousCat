package com.surroundrervouscat;

import java.awt.Image;

/**
 * 逐帧动画
 * 
 * @author 爱寂寞的时光
 *
 */
public class Animation {
	private Image[] images;
	private int delay;
	private int array_index;
	private int delay_index;

	/**
	 * 创建逐帧动画
	 * 
	 * @param images
	 *            多个关键帧
	 * @param delay
	 *            延迟，具体取决于FPS
	 */
	public Animation(Image[] images, int delay) {
		this.images = images;
		this.delay = delay;
	}

	/**
	 * 取图像下一帧
	 * 
	 * @return 图像下一帧
	 */
	public Image next() {
		if (delay_index == delay) {
			if (array_index == images.length - 1) {
				array_index = 0;
			} else {
				array_index++;
			}
			delay_index = 0;
		} else {
			delay_index++;
		}
		return images[array_index];
	}

}
