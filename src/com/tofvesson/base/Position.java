package com.tofvesson.base;

import com.tofvesson.api.Coordinate;
import com.tofvesson.api.ICoordinate;
import com.tofvesson.api.IGridPosition;
import com.tofvesson.api.IMap;
import com.tofvesson.api.IRevealable;
import com.tofvesson.api.Revealable;
import com.tofvesson.api.Type;

public class Position implements IGridPosition {
	
	Type type=Type.Unknown;
	ICoordinate pos=new Coordinate();
	final IMap map;
	int adjacentMines;
	
	public Position(ICoordinate pos, IMap map){this.pos=pos; this.map=map;}
	public Position(int x, int y, IMap map){this.pos=new Coordinate(x, y); this.map=map;}
	
	@Override
	public Type getType() {return this.type;}

	@Override
	public void overrideType(Type type) {this.type=type;}

	@Override
	public Type flag() {
		if(type==Type.Flagged)
			type=Type.Unknown;
		else if(type==Type.MineFlagged)
			type=Type.Mine;
		else if(type==Type.Unknown)
			type=Type.Flagged;
		else if(type==Type.Mine)
			type=Type.MineFlagged;
		return type;
	}

	@Override
	public IRevealable reveal(ICoordinate[] ignore) {
		if(type==Type.Mine||type==Type.MineFlagged)
			return null;
		Revealable r = new Revealable();
		r.addToExpandList(getAdjacentOfValue(0));
		for(int i=1; i<9; ++i)
			r.addToNExpandList(getAdjacentOfValue(i));
		return r;
	}

	@Override
	public IGridPosition setPos(int x, int y) {
		pos = new Coordinate(x, y);
		return this;
	}
	@Override
	public ICoordinate getPos() {return pos;}
	@Override
	public IMap getMap() {return map;}
	@Override
	public int getValue() {
		return adjacentMines;
	}
	@Override
	public void calculateAdjacentMines() {
		adjacentMines=getAdjacentOfType(Type.Mine).length+getAdjacentOfType(Type.MineFlagged).length;
	}

}
