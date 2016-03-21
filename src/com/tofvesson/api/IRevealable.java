package com.tofvesson.api;

public interface IRevealable {
	public abstract IGridPosition[] getExpandList();
	public abstract IGridPosition[] getNExpandList();
	public abstract void addToExpandList(IGridPosition[] i);
	public abstract void addToNExpandList(IGridPosition[] i);
	public default void expand(ICoordinate[] ignore){
		IRevealable[] r = new IRevealable[getExpandList().length];
		ICoordinate[] tmp = new ICoordinate[ignore.length+getExpandList().length];
		for(int i=0; i<ignore.length; ++i)
			tmp[i]=ignore[i];
		int i=ignore.length;
		for(IGridPosition i1 : getExpandList())
			tmp[i++]=i1.getPos();
		i=0;
		for(IGridPosition i1 : getExpandList())
			r[i++]=i1.reveal(tmp);
		for(IRevealable r1 : r){
			addToExpandList(r1.getExpandList());
			addToNExpandList(r1.getNExpandList());
		}
	}
}
