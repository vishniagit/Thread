import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

class Client implements Runnable {
    Socket socket;
    Scanner in;
    PrintStream out;
    Server server;
    Calendar calendar = new GregorianCalendar();
    Date date = calendar.getTime();
    public Client(Socket socket, Server server){

        this.socket = socket;
        this.server = server;
        new Thread(this).start();


    }
    void receive(String message){
        out.println(message);
    }
    public void run() {
        try {

            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();


            in = new Scanner(is);
            out = new PrintStream(os);

            // читаем из сети и пишем в сеть
            out.println("Welcome to chat! " + date);
            String input = in.nextLine();
            while (!input.equals("bye")) {
                server.sendAll(input);
                input = in.nextLine();
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}