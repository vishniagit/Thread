import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Server {
    ArrayList<Client> clients = new ArrayList<>();
    ServerSocket server;

    Server () throws IOException {
        server = new ServerSocket(1234);
    }
    void sendAll(String message){
        for (Client client: clients){
            client.receive(message );
        }
    }
    public void run() throws IOException {
        while (true) {
            System.out.println("Waiting...");

            try {
                Socket socket = server.accept();
                System.out.println("Client connected!  :");
                clients.add(new Client(socket,this));


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
            new Server().run();

        }
}