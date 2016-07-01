package com.markable.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.markable.domain.Message;
import com.markable.exceptions.ConflictException;
import com.markable.poster.PrintAnswer;
import com.markable.services.MessageService;
import com.markable.services.MessageServiceImpl;


//import poster.PrintAnswer;


@Controller
@RequestMapping("/markable")
public class MessageController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass()); // logging using slf4j
	
	
	
	@Autowired
	private MessageService messageService;
	
	private int count=0;
	
	
	
	@RequestMapping(value="/messages",method=RequestMethod.POST)
	public @ResponseBody PrintAnswer postMessage(@RequestBody Message input) throws JsonProcessingException, URISyntaxException{
		
		MessageServiceImpl messageServiceImpl = new MessageServiceImpl();
		
		// flag to maintain the operation status
		boolean flag=messageServiceImpl.addMissionId(input.getMissionId()); 
		
		if(flag)
		{
			
			System.out.println(input.getMissionId());
			
					
			//saving message to Database
			messageService.saveMessage(input);
			
			
			
			URI uri = new URI("http://localhost:8080/markable/messages");
			
			HttpStatus Status = HttpStatus.CREATED;
			
			
			
			
			
			PrintAnswer answer=new PrintAnswer(messageServiceImpl.sumOfProperDivisors(input.getSeed()),uri,Status);
				
			

			//Printing JSON
			ObjectMapper mapper = new ObjectMapper();
			logger.info("\n"+mapper.writerWithDefaultPrettyPrinter().writeValueAsString(answer));
			
			return answer;
			
			
			
			
		}
		
		else
		{
			//handling duplicate missionIds using Exception
			System.out.println("false");
			throw new ConflictException();
		}
		
		
	}
	
	

	//Exception Handler to Handle 409 conflict
	@ExceptionHandler(ConflictException.class)
	@ResponseBody
	void handleBadRequests(ConflictException c,HttpServletResponse response) throws IOException {
		
		
	   response.sendError(HttpStatus.CONFLICT.value(), "Please try again with a unique 'missionId'");
	    
		
	}
	
	 
	//Extra methods 
	
	@RequestMapping("/messages/all")
	public HashSet<Integer> getAllMessages()
	{
		return messageService.getAllMessages();
	}
	
	@RequestMapping("/messages/{id}")
	public int getMissionId(@PathVariable("id") String id)
	{
		int inputId = Integer.parseInt(id);
		
		return messageService.getMissionId(inputId);
	}
	
	//post method calling the /messages via MessageService
	@Scheduled
	(fixedDelay=3000)
	public void doPostEveryThreeSeconds() throws IOException
	{
		messageService.doPost();
	}

}
