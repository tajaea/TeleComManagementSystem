package com.microstar.cablevision.controller;

import com.microstar.cablevision.views.ChatView;

import microStarCableVision.Client;

public class ChatController {
	
	ChatView chatView;
	Client clientObj;
	
	public ChatController(ChatView view) {
		setWindow(view);
	}
	
	private void setWindow(ChatView view) {
		chatView = view;
	}
	
	public void setClient(Client client) {
		clientObj = client;
	}
}
