package com.rfid.netty.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.rfid.netty.domain.Label;

public class ImgMap {
	
	private static final ImgMap instance = new ImgMap();
	
	private final Map<String, Set<Label>> imgMap = new HashMap<>();
	
	private ImgMap(){}
	
	public static ImgMap getInstance(){
		return instance;
	}
	
	public synchronized boolean contains(String key){
		Set<Label> set = imgMap.get(key);
		if(set != null && set.size() > 0)
			return true;
		return false;
	}
	
	public synchronized void put(String key, Label label){
		Set<Label> set = imgMap.get(key);
		if(set == null)
			set = new HashSet<>();
		set.add(label);
		imgMap.put(key, set);
	}
	
	public synchronized Set<Label> get(String key){
		return imgMap.remove(key);
	}
}
