/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.GamePlay.Main;

import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author nhata
 */
public class DestroyAnimation {
    int status = 0;
    Image image;
    private ArrayList<Image> destroy = new ArrayList<Image>();

    private int x;
    private int y;
    public DestroyAnimation(int x, int y){
        this.x = x;
        this.y = y;
        
        destroy.add(new ImageIcon(getClass().getResource("/Client/GamePlay/Images/d1.png")).getImage());
        destroy.add(new ImageIcon(getClass().getResource("/Client/GamePlay/Images/d2.png")).getImage());
        destroy.add(new ImageIcon(getClass().getResource("/Client/GamePlay/Images/d3.png")).getImage());
        destroy.add(new ImageIcon(getClass().getResource("/Client/GamePlay/Images/d4.png")).getImage());
        destroy.add(new ImageIcon(getClass().getResource("/Client/GamePlay/Images/d5.png")).getImage());
        destroy.add(new ImageIcon(getClass().getResource("/Client/GamePlay/Images/d6.png")).getImage());
        destroy.add(new ImageIcon(getClass().getResource("/Client/GamePlay/Images/d7.png")).getImage());
    }
    public void update(){
        this.image = destroy.get(status);
        status++;
        if(status>6)
            status = 6;
    }
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    
    
}
