package com.tofvesson.api;

public class Callback implements ICallback{
	Runnable r;
	int id;
	boolean trig=false;
	public Callback(Runnable r){this.r=(r!=null)?r:new Runnable(){@Override public void run(){}};}
	public Callback(){this(null);}
	@Override
	public void setCallback(int id){
		this.id=id;
		trig=true;
		r.run();
	}
	@Override
	public int getCallback() {
		return id;
	}
	@Override
	public boolean hasCallback() {
		return trig;
	}
	
}
