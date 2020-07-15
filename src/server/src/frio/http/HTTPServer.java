package frio.http;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLServerSocketFactory;

public class HTTPServer {
	private static final int PORT = 58279;

	private static ServerSocket serverSocket = null;

	public static void start() {
		new Thread() {
			@Override
			public void run() {
				if (serverSocket != null) {
					throw new RuntimeException("The server can't be started twice");
				}

				try {
					ServerSocketFactory ssf = SSLServerSocketFactory.getDefault();
					serverSocket = ssf.createServerSocket(PORT);

					System.out.println("server started");
				} catch (IOException e) {
					e.printStackTrace();
				}

				try {
					while (true) {
						Socket client = serverSocket.accept();

						handleRequest(client);
					}
				} catch (IOException e) {
					// Ignore
				}
			}
		}.start();
	}

	public static void stop() {
		if (serverSocket == null) {
			throw new RuntimeException("The server isn't started, you can't stop it");
		} else {
			try {
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static synchronized void handleRequest(final Socket connect) {
		new Thread() {
			@Override
			public void run() {
				BufferedReader in = null;
				PrintWriter out = null;
				BufferedOutputStream dataOut = null;

				try {
					in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
					out = new PrintWriter(connect.getOutputStream());
					dataOut = new BufferedOutputStream(connect.getOutputStream());

					String request = in.readLine();
					String[] requestParts = request.split(" ");

					String method = requestParts[0];
					String URI = requestParts[1];

					String[] URIParts = URI.split("\\?", 2);

					String path = URIParts[0].substring(1); // Removes the first "/"

					HashMap<String, String> parameters = new HashMap<>();

					if (URIParts.length == 2) {
						String query = URIParts[1];

						String[] queryParts = query.split("&");

						for (int i = 0; i < queryParts.length; i++) {
							String[] parameterParts = queryParts[i].split("=", 2);

							parameters.put(parameterParts[0], parameterParts[1]);
						}
					}

					String version = requestParts[2];

					HashMap<String, String> headers = new HashMap<>();

					while (true) {
						String line = in.readLine();

						if (line == null || line.isEmpty()) {
							break;
						}

						String[] headerParts = line.split(": ", 2);

						if (headerParts.length == 2) {
							headers.put(headerParts[0], headerParts[1]);
						}
					}

					HTTPRequest httpRequest = new HTTPRequest(path, parameters, headers);

					HTTPResponse response;

					if (method.equalsIgnoreCase("get")) {
						response = WebResources.getResource(httpRequest);
					} else if (method.equalsIgnoreCase("post")) {
						response = WebResources.postResource(httpRequest);
					} else {
						response = new HTTPResponse();

						response.setCode(501); // Not implemented;
					}

					out.println(response.getCodeString());
					for (String header : response.getHeaders()) {
						out.println(header);
					}
					out.println();
					out.flush();

					if (response.getCode() == 200) {
						if (response.getData() == null) {
							throw new RuntimeException("Data shouldn't be null in response with code 200");
						}
						dataOut.write(response.getData());
						dataOut.flush();
					}
				} catch (SSLHandshakeException e) {
					// Ignore
				} catch (IOException e) {
					e.printStackTrace();
				}

				try {
					connect.close();
				} catch (Exception e) {
					e.printStackTrace();
					System.err.println("Error closing stream");
				}
			}
		}.start();
	}
}
