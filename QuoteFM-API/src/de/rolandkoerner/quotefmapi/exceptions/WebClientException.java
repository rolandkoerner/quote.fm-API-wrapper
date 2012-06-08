package de.rolandkoerner.quotefmapi.exceptions;


public class WebClientException extends RuntimeException {

	private static final long serialVersionUID = -7669367232459915744L;

	public WebClientException(Exception e) {
		super(e);
	}

}
