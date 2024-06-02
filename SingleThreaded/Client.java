
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    
    public void run()
    {
        try {
            
            int port = 8010;
            InetAddress address = InetAddress.getByName("localhost");
            Socket socket = new Socket(address,port);

            //send message to server
            PrintWriter toServer = new PrintWriter(socket.getOutputStream(),true);
            toServer.println("hello from the client");

            //received response from server
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("response from server: "+fromServer.readLine());
    
            fromServer.close();
            toServer.close();
            socket.close();

        } catch (Exception e) {
            System.out.println("Excep from client: "+e.getMessage());
        }
    }

    public static void main(String[] args) {
        Client c = new Client();
        c.run();
    }
}
