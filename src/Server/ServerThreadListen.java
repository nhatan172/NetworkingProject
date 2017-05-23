/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Server.GamePlay.Gameplay;
import com.sun.media.jfxmedia.events.PlayerEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author nhata
 */
public class ServerThreadListen implements Runnable {
     private Socket socket;
     private int playerID;
     
    private InputStream inStream;
    private Scanner in;
    Gameplay g;
    
    public ServerThreadListen(Socket i,int ID, Gameplay gl) throws IOException {
        socket = i;
        playerID = ID;
        inStream = socket.getInputStream();
        in = new Scanner(inStream);
        this.g = gl;
    }

    @Override
    public void run() {
        String msg = null;
        while(true){
            if(in.hasNextLine()){
                msg = in.nextLine();
                System.out.println("|||||||||||||||||||||||||||||||_______"+msg);
                switch(msg){
                    case "up" :
                        g.action(playerID, 1);
                        System.out.println("Recived");
                        break;
                    case "down" :
                        g.action(playerID, 2);
                        System.out.println("Recived");
                        break;    
                    case "left" :
                        g.action(playerID, 3);
                        System.out.println("Recived");
                        break;   
                    case "right" :
                        g.action(playerID, 4);
                        System.out.println("Recived");
                        break;
                    case "shoot" :
                        g.action(playerID, 5);
                        System.out.println("Recived");
                        break;
                        
                    case "stopup" :
                       g.action(playerID, 6);
                        System.out.println("Recived");
                        break;
                    case "stopdown" :
                        g.action(playerID, 7);
                        System.out.println("Recived");
                        break;
                    case "stopleft" :
                        g.action(playerID, 8);
                        System.out.println("Recived");
                        break;
                    case "stopright" :
                        g.action(playerID, 9);
                        System.out.println("Recived");
                        break;
                }
        }
        }
    }
}
