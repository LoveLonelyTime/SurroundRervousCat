package com.surroundrervouscat;

import java.awt.Point;
import java.awt.Rectangle;

public class Utils {
	public static boolean isPointInRectangle(Point point, Rectangle rectangle) {
		return point.x >= rectangle.x && point.x <= rectangle.x + rectangle.width && point.y >= rectangle.y
				&& point.y <= rectangle.y + rectangle.height;
	}

	public static boolean isPointInCircle(Point point, int circleX, int circleY, int radius) {
		return (point.x >= circleX - radius || point.x <= circleX + radius)
				&& (point.y >= circleY - radius || point.y <= circleY + radius);
	}
}
