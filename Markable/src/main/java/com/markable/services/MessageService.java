package com.markable.services;


import java.io.IOException;
import java.util.HashSet;

import com.markable.domain.*;


public interface MessageService {
	
	//static hashset for fast access to missionIds
	// To disallow duplicate missionIds 
	static HashSet<Integer> missionIds = new HashSet<Integer>(); 
	
	
	
	
	public HashSet<Integer> getAllMessages();
	
    Iterable<Message> listAllMessages();

    

    //Save Message to Database
    Message saveMessage(Message message);

	int getMissionId(int val);

	int sumOfProperDivisors(int n);

	boolean addMissionId(int missionId);

	void doPost() throws IOException;
}
