package sockets.filetransfer.files;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    public static final int PORT = 5000;

    public Server() {
        try {
            serverSocket = new ServerSocket(PORT);
            acceptConnections();
            System.out.println("Server started on port " + PORT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void acceptConnections() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                if (clientSocket.isConnected()){
                    new Thread( ()->{
                        ClientConnection client = new ClientConnection(clientSocket);
                        client.sendFile();
                    }).start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
