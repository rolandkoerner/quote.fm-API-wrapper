package de.rolandkoerner.quotefmapi;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import de.rolandkoerner.quotefmapi.exceptions.WebClientException;

public class ApacheWebClient implements IWebClient {
	private HttpClient httpClient = null;

	public ApacheWebClient() {
		httpClient = new DefaultHttpClient();
	}

	public ApacheWebClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	/* (non-Javadoc)
	 * @see de.rolandkoerner.quotefmapi.IWebClient#getContent(java.lang.String)
	 */
	public  String getContent(String url)  {
		
		System.out.println("[Web request] " + url);

		HttpGet httpget = new HttpGet(url);
		HttpResponse response;
		String result = null;
		
		try {
			response = httpClient.execute(httpget);
	
		HttpEntity entity = response.getEntity();

		InputStream inputStream = entity.getContent();

		final char[] buffer = new char[0x10000];
		StringBuilder out = new StringBuilder();
		Reader in = new InputStreamReader(inputStream, entity
				.getContentEncoding().getValue());
		int read;
		do {
			read = in.read(buffer, 0, buffer.length);
			if (read > 0) {
				out.append(buffer, 0, read);
			}
		} while (read >= 0);
		 result = out.toString();
		
		} 
		catch (HttpResponseException e) {
			
		  } catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
	}
}