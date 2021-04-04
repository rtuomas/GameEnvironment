package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import controller.Controller;

public class EchoClient {
	private Controller controller;

    public EchoClient(Controller controller) {
		this.controller = controller;
	}

	public ClientSocketHandler start() throws IOException {

        Socket socket = new Socket("localhost", 4998);
       
        ClientSocketHandler handler = new ClientSocketHandler(socket, this);
        Thread thread = new Thread(handler);
        thread.setDaemon(true);
        thread.start();
        return handler;
    }

    public void messageReceived(String message) {
        //System.out.println("Input >   " + message);
    	controller.displayMessage(message);
    }
}
