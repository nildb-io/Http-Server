import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class httpServerV1 {
    public static void main(String[] args) throws IOException {
        //open a socket at port 8080 and listen for incoming connections
            ServerSocket server = new ServerSocket(8080);
            System.out.println("Server started on port 8080...");
            System.out.println("Waiting for browser connection...");


        //wait until browser/client connects
        Socket socket = server.accept();
        System.out.println("Client connected successfully !!");

        //Get incoming data sent by client to my server
        InputStream input = socket.getInputStream();
        //Read raw data from client line by line by converting binary to txt format
        BufferedReader in = new BufferedReader(new InputStreamReader(input));
        String line; //for reading line by line
        System.out.println("=========http REQUEST=========");
        while(!(line = in.readLine()).isEmpty()){  //if line is not empty continue fetching and printing and if empty stop -> exits while loop
            System.out.println(line); //print lines
        }
    // =======SENDING RESPONSE========
        //GET OUTGOING STREAM TO BROWSER
        OutputStream out = socket.getOutputStream();
         // Build a raw http response
        String response =
                "HTTP/1.1 200 OK\r\n" +
                        "Content-Type: text/html\r\n\r\n" +
                        "<h6>Narendra modi is gay</h6>";
        //convert string -> bytes
        byte[] responseBytes = response.getBytes();

        //send bytes through socket to client
        out.write(responseBytes);
        //force send remaining buffering bytes
        out.flush();
        System.out.println("Response sent Successfully");

        //======CLOSE CONNECTION====
        socket.close();
        server.close();
        System.out.println("Job Done :) Server closed.");
    }
}
