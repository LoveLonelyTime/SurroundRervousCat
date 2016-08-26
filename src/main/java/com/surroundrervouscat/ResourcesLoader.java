package com.surroundrervouscat;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * 资源图像管理器
 * 
 * @author 爱寂寞的时光
 *
 */
public class ResourcesLoader {
	public static final Image ABOUT_BUTTON_IMAGE = ResourcesLoader.loadImage("images/about_button.png");
	public static final Image AGAIN_BUTTON_IMAGE = ResourcesLoader.loadImage("images/again_button.png");
	public static final Image BACKGROUND_IMAGE = ResourcesLoader.loadImage("images/background.png");
	public static final Image CAGE_IMAGE = ResourcesLoader.loadImage("images/cage.png");
	public static final Image CIRCLE_IMAGE = ResourcesLoader.loadImage("images/circle.png");
	public static final Image DIALOG_FAIL_IMAGE = ResourcesLoader.loadImage("images/dialog_fail.png");
	public static final Image DIALOG_SUCCESSFUL_IMAGE = ResourcesLoader.loadImage("images/dialog_successful.png");
	public static final Image INSTRUCTION_IMAGE = ResourcesLoader.loadImage("images/instruction.png");
	public static final Image SHARE_BUTTON_IMAGE = ResourcesLoader.loadImage("images/share_button.png");
	public static final Image START_BUTTON_IMAGE = ResourcesLoader.loadImage("images/start_button.png");
	public static final Image WELCOME_IMAGE = ResourcesLoader.loadImage("images/welcome.png");

	public static final Image CAT_NORMAL_1_IMAGE = ResourcesLoader.loadImage("animation/normal_1.png");
	public static final Image CAT_NORMAL_2_IMAGE = ResourcesLoader.loadImage("animation/normal_2.png");
	public static final Image CAT_NORMAL_3_IMAGE = ResourcesLoader.loadImage("animation/normal_3.png");

	/**
	 * 加载图像
	 * 
	 * @param name
	 *            相对文件名
	 * @return 图像
	 */
	public static Image loadImage(String name) {
		URL url = ResourcesLoader.class.getClassLoader().getResource(name);
		if (url != null) {
			try {
				return ImageIO.read(url);
			} catch (IOException e) {
				throw new RuntimeException("资源" + name + "读取错误", e);
			}
		} else {
			throw new RuntimeException("资源" + name + "不存在");
		}
	}
}
