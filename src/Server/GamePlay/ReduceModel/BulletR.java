/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.GamePlay.ReduceModel;

import java.io.Serializable;

/**
 *
 * @author nhata
 */
public class BulletR   implements Serializable {
    private int centerX =0;
    private int centerY =600;

        
    public BulletR(int x,int y){
        this.centerX = x;
        this.centerY = y;

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
    

}
