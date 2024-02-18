package com.myblog.Exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandlerException {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(Exception exception,WebRequest webRequest){
		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(),
				exception.getMessage(),
				webRequest.getDescription(false),
			  "POST_NOT_FOUND"
				);
		return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		
		Map<String, String> resp=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName = ((FieldError)error).getField();
			String message = error.getDefaultMessage();
			resp.put(fieldName, message); 
			
		});
		
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
	}

}
