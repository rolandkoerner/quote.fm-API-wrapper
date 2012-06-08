package de.rolandkoerner.quotefmapi.exceptions;

import org.json.JSONException;

public class DAOException extends RuntimeException {

	public DAOException(JSONException e, String result) {
		super(e);
		System.err.println(result);
	}

}
