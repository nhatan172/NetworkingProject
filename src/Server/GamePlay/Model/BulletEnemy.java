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
public class BulletEnemy {
    private int x, y, speedY;
    private boolean visible = true;
    private Rectangle r;
    private Gameplay g;
    private ArrayList<Plane> players ;
    
    public BulletEnemy(int startX, int startY, Gameplay gl){
        x = startX;
        y = startY;
        speedY = 4;
        visible = true;
        g = gl;
        players = g.getPlayer();
        r = new Rectangle(0,0,0,0);
    }
    
    public void update(){
        y += speedY;
        if(y>800)
            visible = false;
        r.setBounds(x+4,y+16,25,60);
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
        for(int i = 0; i< players.size();i++){
            Plane player = players.get(i);
            if(!player.isStatusDestroy()){
            if(r.intersects(player.getR())){
                player.setStatusDestroy(true);
                this.setVisible(false);
            
            }
        }
        }
    }
    

    
}
