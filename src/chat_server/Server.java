package chat_server;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket listener = new ServerSocket(2020);
            System.out.println("IP Address: " + InetAddress.getLocalHost().getHostAddress() + "\nPort: 2020\nServer running...");
            while (true) {
                new Thread(new ClientHandler(listener.accept())).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {
    private InputStream in;
    private Scanner scan;
    private final Socket socket;
    private final String CLIENT_IP;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        CLIENT_IP = this.socket.getRemoteSocketAddress().toString();
    }

    public void run() {
        try {
            System.out.println("Connection established to " + CLIENT_IP);
            in = socket.getInputStream();
            scan = new Scanner(in);
            String str = scan.nextLine();
            while (!str.equals("EXIT")) {
                System.out.println(CLIENT_IP + ": " + str);
                str = scan.nextLine();
            }
            socket.close();
            in.close();
            System.out.println("Connection ended with " + CLIENT_IP);
        } catch (NoSuchElementException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}