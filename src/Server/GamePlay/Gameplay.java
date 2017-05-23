/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.GamePlay;

import Server.GamePlay.Model.Background;
import Server.GamePlay.Model.Bullet;
import Server.GamePlay.Model.BulletEnemy;
import Server.GamePlay.Model.Enemy;
import Server.GamePlay.Model.Plane;
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;

/**
 *
 * @author nhata
 */
public class Gameplay extends Applet implements Runnable{

    private ArrayList<Plane> player =  new ArrayList<Plane>();

    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();;
    
    private int level = 1;
        
    private int kill = 0;

    public void run1() {
 
            for(int i=0;i<player.size();i++){
                Plane plane = player.get(i);
                if(!plane.isStatusDestroy()){
                    plane.update();
                    ArrayList<Bullet> bullets = plane.getBullets();
                    for(int j = 0; j< bullets.size();j++){
                        Bullet b = bullets.get(j);
                        if(b.isVisible())
                            b.update();
                        else{
                            bullets.remove(j);
                        }
                    }
                }
            }
 
            
            if(enemies.size()>0){
            for(int i = 0; i< enemies.size();i++){
                Enemy e = enemies.get(i);
                if(e.isVisible()){
                    e.update();
                    ArrayList<BulletEnemy> bes = e.getBulletEnemies();
                    if(bes.size()>0){
                    for(int j = 0; j < bes.size();j++){
                        BulletEnemy be = bes.get(j);
                        System.out.println(bes.size());
                        System.out.println(j);
                        if(be.isVisible())
                            be.update();
                        else{
                            bes.remove(be);
                        }
                    }
                    }
                }
                else{
                    enemies.remove(e);
                }
              
            }
         }
            if(enemies.size()>0){
                if(enemies.get(enemies.size()-1).getCenterY()>400)
                    enemies.add(new Enemy(level,this));
            }
            else
                 enemies.add(new Enemy(level,this));        
        
    }

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
    
        enemies.add(new Enemy(level,this));

    }
    @Override
    public void init(){

    }

  
    public void addNewPlayer(int i){
        player.add(new Plane(i,this));
    }

    public ArrayList<Plane> getPlayer() {
        return player;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setPlayer(ArrayList<Plane> player) {
        this.player = player;
    }



    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public  void setLevel(int level) {
        this.level = level;
    }
    
    public void action(int id, int idAction){
        Plane plane = player.get(id-1);
        switch(idAction){
            case 1 :
               plane.moveUp();
                plane.setMovingUp(true);
                System.out.println("Recived");
                break;
            case 2 :
                plane.moveDown();
                plane.setMovingDown(true);
                System.out.println("Recived");
                break;    
            case 3 :
                plane.moveLeft();
                plane.setMovingLeft(true);
                System.out.println("Recived");
                break;   
            case 4 :
                plane.moveRight();
                plane.setMovingRight(true);
                System.out.println("Recived");
                break;
            case 5 :
                plane.shoot();
                System.out.println("Recived");
                break;

            case 6 :
                plane.stopUp();
                System.out.println("Recived");
                break;
            case 7 :
                plane.stopDown();
                System.out.println("Recived");
                break;
            case 8 :
                plane.stopLeft();
                System.out.println("Recived");
                break;
            case 9 :
                plane.stopRight();
                System.out.println("Recived");
                break;

        }
    }

    @Override
    public void run() {
    }

    public int getKill() {
        return kill;
    }

    public void setKill(int kill) {
        this.kill = kill;
    }
    
}
