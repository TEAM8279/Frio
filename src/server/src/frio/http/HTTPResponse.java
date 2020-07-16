package frio.http;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class HTTPResponse {
	private ArrayList<String> headers = new ArrayList<>();
	private int code = 0;
	private byte[] data = null;

	public HTTPResponse() {

	}

	public void setData(String data) {
		this.data = data.getBytes(StandardCharsets.UTF_8);
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public void addHeader(String name, String value) {
		headers.add(name + ": " + value);
	}

	public void setCode(int code) {
		this.code = code;
	}

	public ArrayList<String> getHeaders() {
		return headers;
	}

	public byte[] getData() {
		return data;
	}

	public int getCode() {
		return code;
	}

	public String getCodeString() {
		switch (code) {
		case 200:
			return "HTTP/1.1 200 OK";
		case 404:
			return "HTTP/1.1 404 Not Found";
		case 500:
			return "HTTP/1.1 501 Not Implemented";
		}

		throw new RuntimeException("Unsupported code : " + code);
	}
}
