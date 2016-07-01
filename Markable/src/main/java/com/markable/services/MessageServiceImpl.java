package com.markable.services;


import java.io.IOException;
import java.util.HashSet;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.markable.domain.Message;
import com.markable.repositories.MessageRepository;

@Service
public class MessageServiceImpl implements MessageService {
    private MessageRepository messageRepository;

    private MessageService messageService;
    int count=0;
    
    @Autowired
    public void setProductRepository(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }
 

    @Override
    public Iterable<Message> listAllMessages() {
        return messageRepository.findAll();
    }

   

    @Override
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

	
	public HashSet<Integer> getAll() {
		return missionIds;
	}

	
	
	@Override
	public boolean addMissionId(int missionId) {
		return missionIds.add(missionId);
	}

	@Override
	public int getMissionId(int val) {
		// TODO Auto-generated method stub
		
		if(missionIds.contains(val))return val;
		else return -1;
	}

	@Override
	public HashSet<Integer> getAllMessages(){
		return missionIds;
		
	}

	
	//Methods to calculate solution to Euler 21
	private int sumOfPairs(int seed) {
        int answer = 0;
        for (int i = 1; i < seed; i++) {
            int a = sumOfProperDivisors(i);
            int b = sumOfProperDivisors(a);
            if (a != b && i == b) {
                answer += a + b;
            }
        }
        return answer / 2; //Eliminating counting pairs two times
    }

	@Override
    public int sumOfProperDivisors(int n) {
        int sum = 1;
        for (int i = 2; i < Math.sqrt(n); i++) {
            if (n % i == 0) {
                sum += i + n / i;
                if (i * i == n) sum -= i; //perfect squares eliminated
            }
        }
        return sum;
    }
	

	
	//This method is called every 3 seconds
	@Override
	public void doPost() throws IOException
	{
		
		Message m=new Message();
		m.setMissionId(count);
		count=count+1;
		int seed = 1000+(int)(Math.random()*(20000-1000) +1);
		m.setSeed(seed);
		
		RestTemplate restTemplate = new RestTemplate();
		
		String uri = new String("http://localhost:8080/markable/messages");
		try{
		restTemplate.postForObject(uri,m,Message.class); // performing post at the specified url
				
		
		}
		
		
		 catch (HttpClientErrorException e)
	        {
			 
			 System.out.println(e.getResponseBodyAsString());
			 
	           
	        }
	        catch(Exception e)
	        {
	        	
	        	System.out.println(e.getMessage());
	        }
		
		
	}
	    	
   
	    }


	

