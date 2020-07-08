package frio;

import java.util.Scanner;

import frio.http.HTTPServer;

public class Main {
	public static void main(String[] args) {
		HTTPServer.start();

		Scanner sc = new Scanner(System.in);
		while (true) {
			String in = sc.nextLine();

			if (in.equalsIgnoreCase("stop")) {
				break;
			}
		}

		sc.close();

		HTTPServer.stop();
	}
}
