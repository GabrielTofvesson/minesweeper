package com.tofvesson.api;

public interface IMap {
	public abstract void generate();
	public abstract IGridPosition[] getMap();
	public abstract ICoordinate getSize();
	public abstract IGridPosition getPosition(ICoordinate coord);
	public abstract IGridPosition getPosition(int x, int y);
	public abstract IGridPosition[][] getSubMap(ICoordinate coord1, ICoordinate coord2);
	public abstract IGridPosition[][] getSubMap(int x1, int y1, int x2, int y2);
	public default boolean reveal(ICoordinate coord){return reveal(coord.getX(), coord.getY());}
	public abstract boolean reveal(int x, int y);
}
