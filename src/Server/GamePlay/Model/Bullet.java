/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.GamePlay.Model;

import Client.GamePlay.Main.StartingClass;
import Server.GamePlay.Gameplay;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author nhata
 */
public class Bullet {
    private int x, y, speedY;
    private boolean visible;
    private Rectangle r;
    private Gameplay g;
    private ArrayList<Enemy> enemies;
    
    public Bullet(int startX, int startY, Gameplay g){
        x = startX;
        y = startY;
        speedY = -3;
        visible = true;
        r = new Rectangle(0,0,0,0);
        this.g  = g;
        enemies = g.getEnemies();
    }
    
    public void update(){
        y += speedY;
        if(y<0)
            visible = false;
        r.setBounds(x+5,y+5,30,30);
        checkCollision();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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

    private void checkCollision() {
        for(int i=0;i<enemies.size();i++){
            Enemy e = enemies.get(i);
            if(e.isVisible() && !e.isStatusDestroy()){
            if(r.intersects(e.getR())){
                e.setStatusDestroy(true);
                this.setVisible(false);
                g.setKill(g.getKill()+1);
            }
        }
            }
    }

    
}
