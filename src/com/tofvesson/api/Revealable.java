package com.tofvesson.api;

public class Revealable implements IRevealable{
	
	IGridPosition[] expandList;
	IGridPosition[] NExpandList;
	
	public Revealable(){
		expandList=new IGridPosition[0];
		NExpandList=new IGridPosition[0];
	}

	@Override
	public IGridPosition[] getExpandList() {
		return expandList;
	}

	@Override
	public IGridPosition[] getNExpandList() {
		return NExpandList;
	}

	@Override
	public void addToExpandList(IGridPosition[] i) {
		IGridPosition[] tmp = expandList;
		expandList = new IGridPosition[expandList.length+i.length];
		int i1=0;
		for(IGridPosition i2 : tmp)
			expandList[i1++]=i2;
		for(IGridPosition i2 : i)
			expandList[i1++]=i2;
	}

	@Override
	public void addToNExpandList(IGridPosition[] i) {
		IGridPosition[] tmp = NExpandList;
		NExpandList = new IGridPosition[NExpandList.length+i.length];
		int i1=0;
		for(IGridPosition i2 : tmp)
			NExpandList[i1++]=i2;
		for(IGridPosition i2 : i)
			NExpandList[i1++]=i2;
	}

}
