package com.vigil.tony;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class MaxConnected {
	private int[][] grid;
	private boolean[][] visited;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//light blue = 1
		//dark blue = 2
		//red = 3
		int[][] input = {{1, 1, 2, 3} ,{1, 2, 3, 2 } ,{ 3, 2, 2, 2 } };
		
		MaxConnected mc = new MaxConnected(input);
		System.out.println("max connected is: "+mc.getMaxConnected());
		
	}
	
	public MaxConnected(int[][] input) {
		grid = input;
		visited = new boolean [grid.length] [grid[0].length];
	}
	
	public int getMaxConnected() {
		int max=0;
		for(int row=0;row<grid.length;row++) {
			for(int col=0;col<grid[0].length;col++) {
				resetVisited();
				int currentValue = getConnectedCount(new Point(row,col), grid[row][col] );
				if(currentValue>max) {
					max=currentValue;
				}
			}
		}
		return max;
	}
	
	private Point[] getValidNeighboors(Point p) {
		//max of 4 points to return
		List<Point> points = new ArrayList<Point>();
		//above, to the left, to the right, below
		Point above, left, right, below;
		//above (x, y-1)
		above = new Point(p.x, p.y-1);
		if(isPointValid(above) ) {
			points.add(above); 
		}
		
		//left (x-1, y)
		left = new Point(p.x-1, p.y);
		if(isPointValid(left) ) {
			points.add(left);
		}
		
		//right (x+1, y)
		right = new Point(p.x+1, p.y);
		if(isPointValid(right) ) {
			points.add(right); 
		}
		
		//below (x, y+1)
		below = new Point(p.x, p.y+1);
		if(isPointValid(below) ) {
			points.add(below);
		}
		
		Point[] returnPoints = new Point[points.size()];
		
		return points.toArray(returnPoints);
	}
	
	private boolean isPointValid(Point p) {
		if (p.x>-1 && p.y>-1 && p.x<grid.length && p.y<grid[0].length && !visited[p.x][p.y] ) {
			visited[p.x][p.y] = true;
			return true;
		} else {
			return false;
		}
			
	}
	
	private void resetVisited() {
		for(int row=0;row<visited.length;row++) {
			for(int col=0;col<visited[0].length;col++) {
				visited[row][col] = false;
			}
		}
	}
	
	private int getConnectedCount(Point p, int value) {
		//get the neighboors to check
		Point[] neighboors = getValidNeighboors(p);
		if(neighboors.length==0) {
			//base case - return 1 if it is the same color, 0 if it is a different color
			if(grid[p.x][p.y] == value) {
				return 1;
			} else {
				return 0;
			}
		} else {
			//recursively call for each valid neighboor 
			int count = 0;
			for(Point each:neighboors) {
				count+=getConnectedCount(each, value);
			}
			return count;
		}
	}

}
