package SingleThreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{

    public void run()
    {
        try
        {
            int port = 8010;
            ServerSocket socket  = new ServerSocket(port);
            socket.setSoTimeout(10000);

            while(true)
            {
                System.out.println("server is listening on port " + port);
                Socket accpetConn = socket.accept();
            
                System.out.println("conn accpeted from client :" + accpetConn.getRemoteSocketAddress());
                PrintWriter toClient = new PrintWriter(accpetConn.getOutputStream());
                var fromClient = new BufferedReader(new InputStreamReader(accpetConn.getInputStream()));
                toClient.println("Hello from server, you are requesting from port: "+accpetConn.getRemoteSocketAddress());

                String clientMsg = fromClient.readLine();
                System.out.println("from client: "+clientMsg);
                
                toClient.close();
                fromClient.close();
                accpetConn.close();
            }
            
        }
        catch(IOException ex)
        {
            System.out.println("Ex: "+ ex.getMessage());
        }
        

    }

    public static void main(String[] args) {
    
        Server s = new Server();
        s.run();
    }
}

