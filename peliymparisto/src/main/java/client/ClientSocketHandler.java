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
    /**
     * Boolean to check if chat connection can be closed
     * Currently always false, meaning chat connection will persist as long as the program is running
     */
    private volatile boolean exit = false;

    public ClientSocketHandler(Socket socket, EchoClient client) throws IOException{
        this.socket = socket;
        this.client = client;
        outToServer = new ObjectOutputStream(socket.getOutputStream());
        infromServer = new ObjectInputStream(socket.getInputStream());
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
    
}
