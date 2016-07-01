package com.markable.bootstrap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.markable.domain.Message;
import com.markable.repositories.MessageRepository;
import com.markable.services.MessageService;

@Component
public class MessageLoader implements ApplicationListener<ContextRefreshedEvent> {

    private MessageRepository messageRepository;

    private Logger log = Logger.getLogger(MessageLoader.class);

    @Autowired
    public void setProductRepository(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

    	    	
    	//Populating Static HashMap on Application Start with the
    	//missionIds to optimize database access requests
         Iterable<Message> p = messageRepository.findAll();
        
               
       for(Message m:p)
       		{
    	   		MessageService.missionIds.add(m.getMissionId());
    	   
       		}
        
               
    	      
    }
    
}
