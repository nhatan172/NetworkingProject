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
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author nhata
 */
public class Plane {
    final int MOVESPEED = 5;
    final int GROUND = 600;
    private int ID;
    private int centerX = 0;
    private int centerY = GROUND;
    private boolean movingLeft =  false;
    private boolean movingRight =  false;
    private boolean movingUp =  false;
    private boolean movingDown =  false;
    private Rectangle r = new Rectangle(0,0,0,0);
    private int speedX = 0;
    private int speedY = 0;
    private boolean statusDestroy = false;
    private boolean visible = true;
    private Gameplay g;
    
    private ArrayList<Bullet> bullets;
   
    public Plane(int ID,Gameplay gl) {
        this.ID = ID;
        g = gl;
        bullets  = new ArrayList<Bullet>();
    }
    
    public void update() {
        //Moves Character or Scrolls Background
        if (this.isStatusDestroy())
            return;
        if(speedX != 0)
            centerX += speedX;
        
        if(speedY != 0)
            centerY += speedY;
        
        if(centerX>352)
            centerX = 352;
        
        if(centerX<0)
            centerX = 0;
        
        if(centerY>600)
            centerY = 600;
        
        if(centerY<0)
            centerY = 0;
        
        r.setBounds(centerX+7,centerY+10,110,100);
        
    }
    
    public void moveRight(){
        speedX = MOVESPEED;
    }
    
    public void moveLeft(){
        speedX = -MOVESPEED;
    }
    
    public void moveUp(){
        speedY = -MOVESPEED;
    }
    
    public void moveDown(){
        speedY = MOVESPEED;
    }
    
    public void stopRight(){
        setMovingRight(false);
        stop();
    }
    public void stopLeft(){
        setMovingLeft(false);
        stop();
    }
    public void stopUp(){
        setMovingUp(false);
        stop();
    }
    public void stopDown(){
        setMovingDown(false);
        stop();
    }
    private void stop(){
        if(isMovingRight()== false && isMovingLeft()== false){
            speedX = 0;
        }
        if(isMovingRight() == false && isMovingLeft() == true){
            moveLeft();
        }
        if(isMovingRight()==true && isMovingLeft() == false){
            moveRight();
        }
        if(isMovingUp()== false && isMovingDown()== false){
            speedY = 0;
        }
        if(isMovingUp() == false && isMovingDown() == true){
            moveDown();
        }
        if(isMovingUp()==true && isMovingDown() == false){
            moveUp();
        }
        
    }

    public boolean isMovingUp() {
        return movingUp;
    }

    public void setMovingUp(boolean movingUp) {
        this.movingUp = movingUp;
    }

    public boolean isMovingDown() {
        return movingDown;
    }

    public void setMovingDown(boolean movingDown) {
        this.movingDown = movingDown;
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

  

    public boolean isMovingLeft() {
        return movingLeft;
    }

    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    public boolean isMovingRight() {
        return movingRight;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }


    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return speedY;
    }


    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    
    public void shoot() {
        Bullet b = new Bullet(centerX + 50, centerY - 25,g);
        bullets.add(b);
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

    

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
}
