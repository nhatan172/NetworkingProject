/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.ConnectToServer;

import Client.GamePlay.Main.StartingClass;
import Server.GamePlay.Model.Background;
import Server.GamePlay.ReduceModel.EnemyR;
import Server.GamePlay.ReduceModel.Ob;
import Server.GamePlay.ReduceModel.PlaneR;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nhata
 */
public class ClientThreadListen implements Runnable {
    private Socket s;
    private InputStream inStream;
    private ObjectInputStream readFromStream;
    private Scanner in;
    private StartingClass main;
    public ClientThreadListen(Socket socket,StartingClass main) throws IOException{
        s = socket;
        inStream = s.getInputStream();
        readFromStream = new ObjectInputStream(inStream);
        this.main = main;
    }
    
    
    @Override
    public void run() {
        while(true){
            try {
                String msg = null;
                Ob object;
                
                Object o = readFromStream.readObject();
                            ArrayList<PlaneR> p = (ArrayList<PlaneR>)o;
                            main.setPlanes(p);
                                                        System.out.println(p.size());

                            System.out.println("Recive Planes");
                                                   o = readFromStream.readObject();

                            ArrayList<EnemyR> e = (ArrayList<EnemyR>)o;
                            System.out.println(e.size());
                            main.setEnemies(e);
                            System.out.println("Recive enemy");
                           int kill = readFromStream.readInt();
                            main.setKill(kill);
                    } catch (IOException ex) {
                Logger.getLogger(ClientThreadListen.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientThreadListen.class.getName()).log(Level.SEVERE, null, ex);
            }
            main.run1();
            try {
                Thread.sleep(17);
            } catch (InterruptedException ex) {
                Logger.getLogger(ClientThreadListen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
