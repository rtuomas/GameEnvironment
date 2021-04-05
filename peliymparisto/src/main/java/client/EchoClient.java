package client;

import java.io.IOException;
import java.net.Socket;

import controller.Controller;

public class EchoClient {
	private Controller controller;
	private Thread thread;

    public EchoClient(Controller controller) {
		this.controller = controller;
	}

	public ClientSocketHandler start() throws IOException {

        Socket socket = new Socket("localhost", 4998);
       
        ClientSocketHandler handler = new ClientSocketHandler(socket, this);
        thread = new Thread(handler);
        thread.setDaemon(true);
        thread.start();
        return handler;
    }

    public void messageReceived(String message) {
        //System.out.println("Input >   " + message);
    	controller.displayMessage(message);
    }
    
}
