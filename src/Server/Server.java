/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Server.GamePlay.Gameplay;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nhata
 */
public class Server {
    public static void main(String[] args) {
         try {
            ServerSocket s = new ServerSocket(9000);
            int i = 1;
            Gameplay g = null;
            while (i<5) {
                Socket incoming = s.accept();
                if(i == 1){
                    g = new Gameplay();
                    g.addNewPlayer(i);
                    g.start();
                    g.run();
                }
                else g.addNewPlayer(i);
                Runnable r = new ServerThreadWriter(incoming,i,g);
                Thread t = new Thread(r);
                t.start();
                Runnable r1 = new ServerThreadListen(incoming,i,g);
                Thread t1 = new Thread(r1);
                t1.start();
                i++;
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
