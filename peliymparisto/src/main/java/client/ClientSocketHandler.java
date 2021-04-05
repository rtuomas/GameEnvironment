package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javafx.application.Platform;
import transferobjects.Message;

public class ClientSocketHandler implements Runnable {

    private EchoClient client;
    private Socket socket;
    private ObjectInputStream infromServer;
    private ObjectOutputStream outToServer;
    private volatile boolean exit = false;

    public ClientSocketHandler(Socket socket, EchoClient client) {
        this.socket = socket;
        this.client = client;
        try {
            outToServer = new ObjectOutputStream(socket.getOutputStream());
            infromServer = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (!exit) {
                Message message = (Message) infromServer.readObject();
                client.messageReceived(message.getMessage());
            }
            System.out.println("Connection to chat server closed...");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void sendMessage(String msg) {
        try {
            outToServer.writeObject(new Message(msg));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /*
    public void stopRun() {
    	exit = true;
    	System.out.println("Stopped?");
    }
    */
    
}
