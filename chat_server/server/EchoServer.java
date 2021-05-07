package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(4999);
        System.out.println("Server started..");
        ConnectionPool pool = new ConnectionPool();
        while(true) {
            Socket socket = serverSocket.accept();
            System.out.println("Connection established");
            ServerSocketHandler handler = new ServerSocketHandler(socket, pool);
            pool.addHandler(handler);
            new Thread(handler).start();
        }
    }
}
