package frio.http;

import java.util.HashMap;

public class HTTPRequest {
	private final String path;

	private final HashMap<String, String> parameters;

	private final HashMap<String, String> headers;

	public HTTPRequest(String path, HashMap<String, String> parameters, HashMap<String, String> headers) {
		this.headers = headers;
		this.path = path;
		this.parameters = parameters;
	}

	public String getPath() {
		return path;
	}

	public HashMap<String, String> getParameters() {
		return parameters;
	}

	public HashMap<String, String> getHeaders() {
		return headers;
	}
}
