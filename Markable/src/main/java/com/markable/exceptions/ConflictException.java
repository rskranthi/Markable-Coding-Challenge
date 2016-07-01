package com.markable.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


//Exception Class to handle 409 Conflict
@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException{

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return super.getMessage();
	}

}
