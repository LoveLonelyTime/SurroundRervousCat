package com.surroundrervouscat.astar;

import java.awt.Point;

public class Node implements Comparable<Node> {
	public Point point; // 坐标
	public Node parent; // 父结点
	public int G; // G：是个准确的值，是起点到当前结点的代价
	public int H; // H：是个估值，当前结点到目的结点的估计代价

	public Node(int x, int y) {
		this.point = new Point(x, y);
	}

	public Node(Point point, Node parent, int g, int h) {
		this.point = point;
		this.parent = parent;
		G = g;
		H = h;
	}

	@Override
	public int compareTo(Node o) {
		if (o == null)
			return -1;
		if (G + H > o.G + o.H)
			return 1;
		else if (G + H < o.G + o.H)
			return -1;
		return 0;
	}

}
