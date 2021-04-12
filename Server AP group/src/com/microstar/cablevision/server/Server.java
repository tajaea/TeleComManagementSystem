package com.microstar.cablevision.server;

import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


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
					System.out.println(connectionSocket.getPort());
						ClientHandler serverhandle = new ClientHandler(this,connectionSocket);
						clientList.add(serverhandle);
						serverhandle.start();
				}
			}
			catch(EOFException ex) {
				System.out.println("Client has Ended Conection With Server");
			}
			catch(IOException io) {
				io.printStackTrace();
			}
		}

	}

