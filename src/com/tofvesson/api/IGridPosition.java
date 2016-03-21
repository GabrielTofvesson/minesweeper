package com.tofvesson.api;

import java.util.ArrayList;
import java.util.List;

public interface IGridPosition {
	public abstract Type getType();
	public default IGridPosition[] getAdjacentOfType(Type type){
		List<IGridPosition> i = new ArrayList<>(); 
		int[] posMin=getPosMin();
		int[] posMax=getPosMax();
		for(int x=posMin[0]; x<posMax[0]; ++x)
			for(int y=posMin[1]; y<posMax[1]; ++y)
				if((x!=getPos().getX() && y!=getPos().getY()) && getMap().getPosition(x, y).getType()==type)
					i.add(getMap().getPosition(x, y));
				else if((x!=getPos().getX() && y!=getPos().getY())) --y;
		return (IGridPosition[]) i.toArray();
	}
	public default IGridPosition[] getAdjacentOfValue(int val){
		List<IGridPosition> i = new ArrayList<>(); 
		int[] posMin=getPosMin();
		int[] posMax=getPosMax();
		for(int x=posMin[0]; x<posMax[0]; ++x)
			for(int y=posMin[1]; y<posMax[1]; ++y)
				if((x!=getPos().getX() && y!=getPos().getY()) && getMap().getPosition(x, y).getValue()==val)
					i.add(getMap().getPosition(x, y));
				else if((x!=getPos().getX() && y!=getPos().getY())) --y;
		return (IGridPosition[]) i.toArray();
	}
	public abstract void calculateAdjacentMines();
	public abstract void overrideType(Type type);
	public abstract Type flag();
	public abstract IGridPosition setPos(int x, int y);
	public abstract IRevealable reveal(ICoordinate[] ignore);
	public abstract ICoordinate getPos();
	public abstract IMap getMap();
	public default int[] getPosMax(){
		return new int[]{
				(getPos().getX()!=getMap().getSize().getX())?getPos().getX()+1:getPos().getX(), 
				(getPos().getY()!=getMap().getSize().getY())?getPos().getY()+1:getPos().getY()
				};
	}
	public default int[] getPosMin(){
		return new int[]{
				(getPos().getX()!=0)?getPos().getX()-1:0, 
				(getPos().getY()!=0)?getPos().getY()-1:0
		};
	}
	public abstract int getValue();
}
