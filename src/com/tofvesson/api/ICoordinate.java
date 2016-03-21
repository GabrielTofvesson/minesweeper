package com.tofvesson.api;

public interface ICoordinate {
	public abstract int getX();
	public abstract int getY();
	public abstract void setX(int x);
	public abstract void setY(int y);
	public default void setCoordinate(ICoordinate c){
		setX(c.getX());
		setY(c.getY());
	}
	public default void setCoordinate(int x, int y){
		setX(x);
		setY(y);
	}
}
