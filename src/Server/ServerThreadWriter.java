/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Server.GamePlay.Gameplay;
import Server.GamePlay.Model.Background;
import Server.GamePlay.Model.Bullet;
import Server.GamePlay.Model.BulletEnemy;
import Server.GamePlay.Model.Enemy;
import Server.GamePlay.Model.Plane;
import Server.GamePlay.ReduceModel.BulletR;
import Server.GamePlay.ReduceModel.EnemyR;
import Server.GamePlay.ReduceModel.Ob;
import Server.GamePlay.ReduceModel.PlaneR;
import com.sun.jmx.snmp.EnumRowStatus;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nhata
 */
public class ServerThreadWriter implements Runnable {
     private Socket socket;
     private int playerID;
     private Gameplay g;
    public ServerThreadWriter(Socket i,int ID,Gameplay gl) {
        socket = i;
        playerID = ID;
        this.g = gl;
    }
    private ArrayList<Plane> players;
    private ArrayList<Enemy> enes;
    private ArrayList<PlaneR> planes  = new ArrayList<PlaneR>();;
    private ArrayList<EnemyR> enemies = new ArrayList<EnemyR>();;

    
    @Override
    public void run() {
        try {
            OutputStream outStream = socket.getOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(outStream);
            PrintWriter printToStream = new PrintWriter(outStream, true);
            while (true) {
               g.run1();
               players = g.getPlayer();
               planes  = new ArrayList<PlaneR>();;
                enemies = new ArrayList<EnemyR>();;
               for(int i = 0; i<players.size();i++){
                   Plane p = players.get(i);
                    PlaneR pr = new PlaneR(p.getCenterX(), p.getCenterY(), p.getID(),p.isStatusDestroy(),p.isVisible());
                    if(p.isStatusDestroy())
                        p.setVisible(false);
                   for(int j=0;j<p.getBullets().size();j++){
                       Bullet b = p.getBullets().get(j);
                       if(b.isVisible())
                           pr.addBullet(new BulletR(b.getX(),b.getY()));
                   }
                   planes.add(pr);
               }
               enes = g.getEnemies();
               for(int i = 0; i<enes.size();i++){
                   Enemy e = enes.get(i);
                   EnemyR er = new EnemyR(e.getCenterX(), e.getCenterY(), e.isStatusDestroy(),e.isVisible());
                   if(e.isStatusDestroy())
                       e.setVisible(false);
                   for(int j=0;j<e.getBulletEnemies().size();j++){
                       BulletEnemy b = e.getBulletEnemies().get(j);
                       if(b.isVisible())
                           er.addBullet(new BulletR(b.getX(),b.getY()));
                   }
                   enemies.add(er);
               }

               out.writeObject(planes);
               out.writeObject(enemies);
                out.writeInt(g.getKill());
                try {
                    Thread.sleep(17);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ServerThreadWriter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
