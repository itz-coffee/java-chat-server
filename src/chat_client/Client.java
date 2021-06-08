package chat_client;

import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private PrintStream out;
    private InputStream in;
    private Socket socket;

    public Client() {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter IP Address To Connect To: ");
        String IP = scan.nextLine();
        System.out.print("Enter Port: ");
        int port = Integer.valueOf(scan.nextLine());

        try {
            socket = new Socket(IP, port);
            //socket=new Socket("localhost",2020);
            out = new PrintStream(socket.getOutputStream());
            if (socket.isConnected())
                System.out.println("Connection established to " + socket.getInetAddress().getHostAddress());
            String str = "";
            while (!str.equals("EXIT")) {
                System.out.print("You: ");
                str = scan.nextLine();
                out.println(str);
            }
            socket.close();
            out.close();
            System.out.println("Connection ended");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client();
    }
}