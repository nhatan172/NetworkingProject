/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.ConnectToServer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author nhata
 */
public class ClientThreadWriter {
    private Socket s;
    private OutputStream outStream;
    private PrintWriter printToStream;

    public ClientThreadWriter(Socket sk) throws IOException{
        s = sk;
        outStream = s.getOutputStream();
        printToStream = new PrintWriter(outStream, true);
    } 
    
    public void sendToServer(String query) {
        printToStream.println(query);
    }
}
