/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.GamePlay.Model;

import Client.GamePlay.Main.StartingClass;
import Server.GamePlay.Gameplay;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author nhata
 */
public class Enemy {
    private int centerX, centerY,speedX, speedY;
    private boolean visible = true;
    private Rectangle r = new Rectangle(0,0,0,0);
    private boolean statusDestroy; 
    private ArrayList<BulletEnemy> bulletEnemies;
    private int time=0;
    private ArrayList<Plane> players ;
    private Gameplay g;
    
    public Enemy(int level, Gameplay gl){
        Random rand = new Random();
        centerX = rand.nextInt(370) + (-10);
        if (centerX < 100) 
            centerX += 100;
        speedX = 0;
        visible = true;
        centerY = 0;
        speedY = level*2;
        g = gl;
        players = g.getPlayer();
        bulletEnemies = new ArrayList<BulletEnemy>();
    }
    
    public void update() {
        if (this.isStatusDestroy())
            return;
        centerY += speedY;
        if(centerY >800)
            visible = false;
        r.setBounds(centerX+25,centerY+10,90,70);
        if(time!=100){
            time++;
        }
        else{
            time = 0;
            attack();
        }
        checkCollision();
    }

    public void attack() {
        BulletEnemy b = new BulletEnemy(centerX + 50, centerY+20,g);
        bulletEnemies.add(b);
    }

  

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Rectangle getR() {
        return r;
    }

    public boolean isStatusDestroy() {
        return statusDestroy;
    }

    public void setStatusDestroy(boolean statusDestroy) {
        this.statusDestroy = statusDestroy;
    }


    public ArrayList<BulletEnemy> getBulletEnemies() {
        return bulletEnemies;
    }

        private void checkCollision() {
            if (this.isStatusDestroy())
                return;
            for(int i = 0; i <players.size();i++){
                Plane player = players.get(i);
            if(!player.isStatusDestroy()){
            if(r.intersects(player.getR())){
                player.setStatusDestroy(true);
                this.setStatusDestroy(true);
            }
        }
        }}
    
    
}
