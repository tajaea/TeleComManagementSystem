package com.microstar.cablevision.server;

import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.microstar.cablevision.security.Security;


public class Server extends Thread implements Runnable{
		private final int serverPort;
		private ServerSocket serverSocket;
		private Socket connectionSocket;
		private ArrayList<ClientHandler> clientList = new ArrayList<>();

		public Server(int serverPort) {
			this.serverPort = serverPort;
		}
		
		public ArrayList<ClientHandler> getClientList(){
			return clientList;
		}
		
		@Override
		public void run() {
			try {
				serverSocket = new ServerSocket(serverPort);
				while(true) {
					connectionSocket = serverSocket.accept();
						ClientHandler serverhandle = new ClientHandler(this,connectionSocket);
						clientList.add(serverhandle);
						serverhandle.start();
				}
			}
			catch(EOFException ex) {
				System.out.println("An error occurred in our database connection. Please try again later");	
				Security.logger.error("A EOF Exception was caught in the run method in the Server class");
			}
			catch(IOException io) {
				System.out.println("An error occurred in our server. Please try again later");	
				Security.logger.error("An Input/Output Exception was caught in the run method in the Server class");
			}
		}

	}

