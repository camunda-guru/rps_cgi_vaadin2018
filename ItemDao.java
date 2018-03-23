package com.example.dao;

import java.util.Hashtable;

public class ItemDao {
	
	
	public static Hashtable<Integer,String> getItemList()
	{
		
		Hashtable<Integer,String> itemList=new Hashtable<Integer,String>();
		itemList.put(31456, "Laptop");
		itemList.put(31467, "Mobile");
		itemList.put(31498, "Sensor");
		itemList.put(31478, "Drive");
		itemList.put(31455, "Hub");
		return itemList;
		
		
		
	}

}
