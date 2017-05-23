/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.GamePlay.ReduceModel;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author nhata
 */
public class EnemyR  implements Serializable  {
    private int centerX =0;
    private int centerY =0;
    private boolean statusDestroy = false;
    private ArrayList<BulletR> bullets;
    private boolean visible = true;
        
    public EnemyR(int x,int y,  boolean status,boolean v){
        this.centerX = x;
        this.centerY = y;
        this.statusDestroy = status;
        bullets= new ArrayList<BulletR>();
        this.visible = v;
    }
    public void addBullet(BulletR b){
        bullets.add(b);
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public void setStatusDestroy(boolean statusDestroy) {
        this.statusDestroy = statusDestroy;
    }

    public void setBullets(ArrayList<BulletR> bullets) {
        this.bullets = bullets;
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public boolean isStatusDestroy() {
        return statusDestroy;
    }

    public ArrayList<BulletR> getBullets() {
        return bullets;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    
}
