package com.surroundrervouscat.astar;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import com.surroundrervouscat.PlayGameScene;

public class Astart {
	public final static PlayGameScene.SATTUS BAR = PlayGameScene.SATTUS.FULL; // 障碍值
	public final static PlayGameScene.SATTUS PATH = PlayGameScene.SATTUS.EMPTY; // 路径
	public final static int DIRECT_VALUE = 10; // 横竖移动代价
	public final static int OBLIQUE_VALUE = 14; // 斜移动代价

	Queue<Node> openList = new PriorityQueue<Node>(); // 优先队列(升序)
	List<Node> closeList = new ArrayList<Node>();

	private List<Point> path = new ArrayList<Point>();

	/**
	 * 判断结点是否是最终结点
	 */
	private boolean isEndNode(Point end, Point point) {
		return point != null && end.equals(point);
	}

	/**
	 * 判断结点能否放入Open列表
	 */
	private boolean canAddNodeToOpen(MapInfo mapInfo, Point point) {
		// 是否在地图中
		if (point.x < 0 || point.x >= mapInfo.width || point.y < 0 || point.y >= mapInfo.hight) {
			return false;
		}
		// 判断是否是不可通过的结点
		if (mapInfo.maps[point.x][point.y] == BAR) {
			return false;
		}
		// 判断结点是否存在close表
		if (isCoordInClose(point)) {
			return false;
		}
		return true;
	}

	/**
	 * 判断坐标是否在close表中
	 */
	private boolean isCoordInClose(Point point) {
		if (closeList.isEmpty()) {
			return false;
		}
		for (Node node : closeList) {
			if (node.point.x == point.x && node.point.y == point.y) {
				return true;
			}
		}
		return false;
	}

	private int calcH(Point end, Point point) {
		return Math.abs(end.x - point.x) + Math.abs(end.y - point.y);
	}

	private Node findNodeInOpen(Point point) {
		if (point == null || openList.isEmpty()) {
			return null;
		}
		for (Node node : openList) {
			if (node.point.equals(point)) {
				return node;
			}
		}
		return null;
	}

	/**
	 * 添加所有邻结点到open表
	 */
	private void addNeighborNodeInOpen(MapInfo mapInfo, Node current) {
		int x = current.point.x;
		int y = current.point.y;
		// 左
		addNeighborNodeInOpen(mapInfo, current, new Point(x - 1, y), DIRECT_VALUE);
		// 上
		addNeighborNodeInOpen(mapInfo, current, new Point(x, y - 1), DIRECT_VALUE);
		// 右
		addNeighborNodeInOpen(mapInfo, current, new Point(x + 1, y), DIRECT_VALUE);
		// 下
		addNeighborNodeInOpen(mapInfo, current, new Point(x, y + 1), DIRECT_VALUE);
		// 左上
		addNeighborNodeInOpen(mapInfo, current, new Point(x - 1, y - 1), OBLIQUE_VALUE);
		// 右上
		addNeighborNodeInOpen(mapInfo, current, new Point(x + 1, y - 1), OBLIQUE_VALUE);
		// 右下
		addNeighborNodeInOpen(mapInfo, current, new Point(x + 1, y + 1), OBLIQUE_VALUE);
		// 左下
		addNeighborNodeInOpen(mapInfo, current, new Point(x - 1, y + 1), OBLIQUE_VALUE);
	}

	/**
	 * 添加一个邻结点到open表
	 */
	private void addNeighborNodeInOpen(MapInfo mapInfo, Node current, Point point, int value) {
		if (canAddNodeToOpen(mapInfo, point)) {
			Node end = mapInfo.end;
			int G = current.G + value; // 计算邻结点的G值
			Node child = findNodeInOpen(point);
			if (child == null) {
				int H = calcH(end.point, point); // 计算H值
				if (isEndNode(end.point, point)) {
					child = end;
					child.parent = current;
					child.G = G;
					child.H = H;
				} else {
					child = new Node(point, current, G, H);
				}
				openList.add(child);
			} else if (child.G > G) {
				child.G = G;
				child.parent = current;
				// 重新调整堆
				openList.add(child);
			}
		}
	}

	private void drawPath(PlayGameScene.SATTUS[][] maps, Node end) {
		if (end == null || maps == null) {
			return;
		}
		while (end != null) {
			Point c = end.point;
			path.add(new Point(c.x, c.y));
			end = end.parent;
		}
	}

	public void start(MapInfo mapInfo) {
		if (mapInfo == null) {
			return;
		}
		// clean
		openList.clear();
		closeList.clear();
		// 开始搜索
		openList.add(mapInfo.start);
		moveNodes(mapInfo);
	}

	/**
	 * 移动当前结点
	 */
	private void moveNodes(MapInfo mapInfo) {
		while (!openList.isEmpty()) {
			if (isCoordInClose(mapInfo.end.point)) {
				drawPath(mapInfo.maps, mapInfo.end);
				break;
			}
			Node current = openList.poll();
			closeList.add(current);
			addNeighborNodeInOpen(mapInfo, current);
		}
	}

	public List<Point> getPath() {
		return path;
	}

}
