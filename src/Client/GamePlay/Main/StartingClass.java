/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.GamePlay.Main;

import Client.ConnectToServer.Client;
import Client.ConnectToServer.ClientThreadListen;
import Client.ConnectToServer.ClientThreadWriter;
import Server.GamePlay.Model.Background;
import Server.GamePlay.Model.Plane;
import Server.GamePlay.Model.Bullet;
import Server.GamePlay.Model.BulletEnemy;
import Server.GamePlay.Model.Enemy;
import Server.GamePlay.ReduceModel.BulletR;
import Server.GamePlay.ReduceModel.EnemyR;
import Server.GamePlay.ReduceModel.PlaneR;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author nhata
 */
public class StartingClass extends Applet implements Runnable, KeyListener {

    private ArrayList<PlaneR> planes ;
    private Image image, bot, botBullet,white ;
    private Image background = new ImageIcon(getClass().getResource("/Client/GamePlay/Images/background.png")).getImage();
    private Graphics second;
    private Background    bg1 = new Background(0, 0,1);
    private Background    bg2 = new Background(0, -800,1);
    private boolean shot  = false;
    private ArrayList<EnemyR> enemies ;
    private ClientThreadWriter cw;
    private HashMap<Integer, Image> playersImage = new HashMap<>() ;
    private HashMap<Integer, Image> playersBullet = new HashMap<>() ;
    private ArrayList<DestroyAnimation> destroyList = new ArrayList<DestroyAnimation>();
    private int kill = 0;
    private Client client;
    
    @Override
    public void destroy() {
        super.destroy(); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public void stop() {
        super.stop(); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public void start() {
        Thread t = new Thread(this);
        t.start();
                addKeyListener(this);

    }

    @Override
    public void init() {
        setSize(480, 800);
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        planes = new ArrayList<>();
        enemies = new ArrayList<>();
        
      
        
        playersImage.put(1,new ImageIcon(getClass().getResource("/Client/GamePlay/Images/player1.png")).getImage());
        playersImage.put(2,new ImageIcon(getClass().getResource("/Client/GamePlay/Images/player2.png")).getImage());
        playersImage.put(3,new ImageIcon(getClass().getResource("/Client/GamePlay/Images/player3.png")).getImage());
        playersImage.put(4,new ImageIcon(getClass().getResource("/Client/GamePlay/Images/player4.png")).getImage());
        playersBullet.put(1,new ImageIcon(getClass().getResource("/Client/GamePlay/Images/bullet1.png")).getImage());
        playersBullet.put(2,new ImageIcon(getClass().getResource("/Client/GamePlay/Images/bullet2.png")).getImage());
        playersBullet.put(3,new ImageIcon(getClass().getResource("/Client/GamePlay/Images/bullet3.png")).getImage());
        playersBullet.put(4,new ImageIcon(getClass().getResource("/Client/GamePlay/Images/bullet4.png")).getImage());

        white = new ImageIcon(getClass().getResource("/Client/GamePlay/Images/white.png")).getImage();
        bot = new ImageIcon(getClass().getResource("/Client/GamePlay/Images/enemy.png")).getImage();
        botBullet =new ImageIcon(getClass().getResource("/Client/GamePlay/Images/bulletE.png")).getImage();
    }

    @Override
    public void run() {
        

    }
    public void run1() {
        bg1.update();
        bg2.update();
        
        repaint();
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP :
                cw.sendToServer("up");  
                break;
            case KeyEvent.VK_DOWN :
                cw.sendToServer("down");
                break;
            case KeyEvent.VK_LEFT :
                cw.sendToServer("left");       
                break;
            case KeyEvent.VK_RIGHT :
               cw.sendToServer("right");       
                break;
            case KeyEvent.VK_SPACE :
                if(shot == false){
                    cw.sendToServer("shoot");
                    shot = true;
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP :
                cw.sendToServer("stopup");   
                break;
            case KeyEvent.VK_DOWN :
                cw.sendToServer("stopdown"); 
                break;
            case KeyEvent.VK_LEFT :
                cw.sendToServer("stopleft");
                break;
            case KeyEvent.VK_RIGHT :
                cw.sendToServer("stopright");
                break;
            case KeyEvent.VK_SPACE :
                shot = false;
                break;
        }
    }

    @Override
    public void update(Graphics g) {
        if(image == null){
            image = createImage(this.getWidth(),this.getHeight());
            second = image.getGraphics();
        }
     
        second.setColor(getBackground());
        second.fillRect(0,  0, getWidth(), getHeight());
        second.setColor(getForeground());
        paint(second);
        
        g.drawImage(image,0,0,this);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(background, bg1.getBgX(), bg1.getBgY(), this);
        g.drawImage(background, bg2.getBgX(), bg2.getBgY(), this); 
        g.drawImage(white, 0, 0, this);
        g.drawString(Integer.toString(kill)+"/30",7,20);
        
        
        for(int i =0;i<planes.size();i++){
            PlaneR plane = planes.get(i);
        if(!plane.isStatusDestroy()){
        g.drawImage(playersImage.get(plane.getID()), plane.getCenterX(), plane.getCenterY(), this);
         ArrayList<BulletR> bullets = plane.getBullets();
         for(int j = 0; j< bullets.size();j++){
                BulletR b = bullets.get(j);
                g.drawImage(playersBullet.get(plane.getID()),b.getCenterX(),b.getCenterY(),this);
            }
        }
        else if(plane.isVisible()){
            destroyList.add(new DestroyAnimation(plane.getCenterX(), plane.getCenterY()));
        }
        }
       
         
         for(int i = 0; i< enemies.size();i++){
                EnemyR e = enemies.get(i);
                if(!e.isStatusDestroy()){
                    g.drawImage(bot,e.getCenterX(),e.getCenterY(),this);
                    ArrayList<BulletR> bes = e.getBullets();
                    for(int j = 0; j< bes.size();j++){
                    BulletR be = bes.get(j);
                    g.drawImage(botBullet,be.getCenterX(),be.getCenterY(),this);
                    }
                }
                else if(e.isVisible()){
                    destroyList.add(new DestroyAnimation(e.getCenterX(), e.getCenterY()));
                }
         }
         for(int i = 0; i<destroyList.size();i++){
             DestroyAnimation d = destroyList.get(i);
             d.update();
             g.drawImage(d.getImage(), d.getX(), d.getY(), this);
             if(d.getStatus() == 6)
                 destroyList.remove(d);
         }
    }

    public void setPlanes(ArrayList<PlaneR> planes) throws IOException {
        this.planes = planes;
        if(checkGameOver())
            client.end(0);
    }

    public void setBg1(Background bg1) {
        this.bg1 = bg1;
    }

    public void setBg2(Background bg2) {
        this.bg2 = bg2;
    }

    public void setEnemies(ArrayList<EnemyR> enemies) {
        this.enemies = enemies;
    }

    public void setCw(ClientThreadWriter cw) {
        this.cw = cw;
    }

    public int getKill() {
        return kill;
    }

    public void setKill(int kill) throws IOException {
        this.kill = kill;
        if(kill>=30)
            client.end(1);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
       
    }
    public boolean checkGameOver(){
        for(int i=0; i<planes.size();i++){
            PlaneR p = planes.get(i);
            if(p.isVisible())
                return false;
        }
        return true;
    }
    

    }
    

