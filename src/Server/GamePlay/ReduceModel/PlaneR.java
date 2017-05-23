/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.GamePlay.ReduceModel;

import Server.GamePlay.Model.Bullet;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author nhata
 */
public class PlaneR  implements Serializable  {
    private int centerX =0;
    private int centerY =600;
    private int ID;
    private boolean statusDestroy = false;
    private ArrayList<BulletR> bullets;
    private boolean visible = true;
        
    public PlaneR(int x,int y, int id, boolean status,boolean v){
        this.centerX = x;
        this.centerY = y;
        this.ID = id;
        this.statusDestroy = status;
        bullets= new ArrayList<BulletR>();
        this.visible = v;
    }
    public void addBullet(BulletR b){
        bullets.add(b);
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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public boolean isStatusDestroy() {
        return statusDestroy;
    }

    public void setStatusDestroy(boolean statusDestroy) {
        this.statusDestroy = statusDestroy;
    }

    public ArrayList<BulletR> getBullets() {
        return bullets;
    }

    public void setBullets(ArrayList<BulletR> bullets) {
        this.bullets = bullets;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
}
