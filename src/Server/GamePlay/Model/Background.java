/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.GamePlay.Model;

import java.io.Serializable;

/**
 *
 * @author nhata
 */
public class Background implements Serializable {
     private int bgX, bgY, speedY;
    
    public Background(int x, int y, int z){
        this.bgX = x;
        this.bgY = y;
        this.speedY = z;
    }
    
    public void update(){
        bgY += speedY;
        if(bgY >= 800){
            bgY -= 1600;
        }
    }

    public int getBgX() {
        return bgX;
    }

    public void setBgX(int bgX) {
        this.bgX = bgX;
    }

    public int getBgY() {
        return bgY;
    }

    public void setBgY(int bgY) {
        this.bgY = bgY;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }
    
}
