package com.tofvesson.base;

import com.tofvesson.api.ICoordinate;
import com.tofvesson.api.IGridPosition;
import com.tofvesson.api.IMap;

public class Map implements IMap{
	
	ICoordinate size;
	double genRatio;
	IGridPosition[][] map;
	
	public Map(ICoordinate size){}

	@Override
	public void generate() {
		
	}

	@Override
	public IGridPosition[] getMap() {
		return null;
	}

	@Override
	public ICoordinate getSize() {
		return null;
	}

	@Override
	public IGridPosition getPosition(ICoordinate coord) {
		return null;
	}

	@Override
	public IGridPosition getPosition(int x, int y) {
		return null;
	}

	@Override
	public IGridPosition[][] getSubMap(ICoordinate coord1, ICoordinate coord2) {
		return null;
	}

	@Override
	public IGridPosition[][] getSubMap(int x1, int y1, int x2, int y2) {
		
		return null;
	}

	@Override
	public boolean reveal(int x, int y) {
		
		return false;
	}

}
