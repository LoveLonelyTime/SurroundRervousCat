package com.surroundrervouscat;

import java.awt.Image;

public class Animation {
	private Image[] images;
	private int delay;
	private int array_index;
	private int delay_index;

	public Animation() {
	}

	public Animation(Image[] images, int delay) {
		this.images = images;
		this.delay = delay;
	}

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
