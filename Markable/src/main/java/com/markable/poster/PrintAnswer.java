package com.markable.poster;

import java.net.URI;

import org.springframework.http.HttpStatus;


//Class to return the Result JSON
public class PrintAnswer {

	URI Uri;
	HttpStatus status;
	
	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	public URI getUri() {
		return Uri;
	}
	public void setUri(URI uri) {
		Uri = uri;
	}
	public PrintAnswer(int answer,URI Uri,HttpStatus status)
	{
		this.status=status;
		this.Uri=Uri;
		this.answer = answer;
		
	}
int answer;
public int getAnswer() {
	return answer;
}
public void setAnswer(int answer) {
	this.answer = answer;
}


	}
	
	

