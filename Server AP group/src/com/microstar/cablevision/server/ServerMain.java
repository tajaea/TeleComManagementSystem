package com.microstar.cablevision.server;

public class ServerMain {

	public static void main(String[] args) {
		int PORT = 8888;
		Server server = new Server(PORT);
		server.start();
	}

}
