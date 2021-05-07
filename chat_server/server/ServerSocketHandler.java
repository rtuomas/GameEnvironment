package server;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import transferobjects.Message;

public class ServerSocketHandler implements Runnable {
    private ObjectInputStream inFromClient;
    private ObjectOutputStream outToClient;
    private Socket socket;
    private ConnectionPool pool;
    public ServerSocketHandler(Socket socket, ConnectionPool pool) {
        this.socket = socket;
        this.pool = pool;
        try {
            inFromClient = new ObjectInputStream(socket.getInputStream());
            outToClient = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        System.out.println("Thread started");
        try {
            while (true) {
                Message readFromClient = (Message) inFromClient.readObject();
                System.out.println("Received: " + readFromClient.getMessage());
                if(readFromClient.getMessage().equalsIgnoreCase("exit")) {
                    pool.removeHandler(this);
                    break;
                }
                pool.broadcastMessage(readFromClient.getMessage());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void sendMessage(String msg) {
        try {
            outToClient.writeObject(new Message(msg));
        } catch (IOException e) {
        }
    }
}
