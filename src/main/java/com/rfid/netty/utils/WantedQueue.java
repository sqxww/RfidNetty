package com.rfid.netty.utils;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import com.rfid.netty.domain.Label;

public class WantedQueue {
	private final Set<Label> queue = new LinkedHashSet<>();
	private static final WantedQueue instance = new WantedQueue();
	
	private WantedQueue(){}
	
	public static WantedQueue getInstance(){
		return instance;
	}
	
	public synchronized Label[] remove(){
		Iterator<Label> it = queue.iterator();
		Label[] res = new Label[queue.size()];
		int i = 0;
		while(it.hasNext()){
			res[i++] = it.next();
		}
		queue.clear();
		return res;
	}
	
	public synchronized void put(Label label){
		queue.add(label);
	}
	
	public boolean isEmpty(){
		return queue.isEmpty();
	}
}
