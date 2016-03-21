package com.tofvesson.api;

public class Coordinate implements ICoordinate{
	private int x;
	private int y;
	
	public Coordinate(int x, int y){setX(x); setY(y);}
	public Coordinate(){this(0, 0);}
	
	@Override
	public int getX() {return x;}
	@Override
	public int getY() {return y;}
	@Override
	public void setX(int x) {this.x=x;}
	@Override
	public void setY(int y) {this.y=y;}

}
