package com.erez.thymeleaf.vehiclemodelsshope.exception;


public class ErrorResponse {
	
	
	
	public ErrorResponse(String message) {
		
		this.message = message;
	}

    public ErrorResponse() {
		
		
	}

	private String message;

    public String getMessage() {
      return message;
   }

   public void setMessage(String message) {
     this.message = message;
   }

  

}
