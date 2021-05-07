package server; 

import java.util.ArrayList; 
import java.util.List;

public class ConnectionPool {
    private List<ServerSocketHandler> connections = new ArrayList<>();
    public void broadcastMessage(String msg) {
        for (ServerSocketHandler handler : connections) {
            handler.sendMessage(msg);
        }
    }
    public void addHandler(ServerSocketHandler handler) {
        connections.add(handler);
    }
    public void removeHandler(ServerSocketHandler handler) {
        connections.remove(handler);
    }
}
