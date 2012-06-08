package de.rolandkoerner.quotefmapi;

public interface IWebClient {

	final static int WAIT_BETWEEN_REQUESTS = 1000;

	public abstract String getContent(String url);

}