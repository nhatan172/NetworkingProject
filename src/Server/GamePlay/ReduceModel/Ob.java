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
public class Ob implements Serializable {
    private Object object;
    private int i = 0;
    
    public Ob(Object o,int j){
        this.object = o;
        this.i = j;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
    
    
}
