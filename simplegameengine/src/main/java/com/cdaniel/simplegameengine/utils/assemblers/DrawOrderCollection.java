package com.cdaniel.simplegameengine.utils.assemblers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by christopher.daniel on 5/4/16.
 */
public class DrawOrderCollection {

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Variables
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private List<Integer> drawOrder;

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Constructors
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public DrawOrderCollection(){

        this.drawOrder = new ArrayList<>();
    }
    public DrawOrderCollection(int start, int end){

        this.drawOrder = new ArrayList<>();

        for(int i = start; i<=end; i++ ){
            this.push(i);
        }
    }
    public DrawOrderCollection(List<Integer> startingList){

        this.drawOrder = new ArrayList<>();

        for(int s : startingList){

            this.push(s);
        }
    }

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Clone
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public DrawOrderCollection copy(){

        DrawOrderCollection copy = new DrawOrderCollection(this.drawOrder);
        return copy;
    }

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Mutators
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void push(int s){

        this.drawOrder.add(s);
    }
    public void push_onePlusPrior(){

        Integer lastOne = this.getLastOneIn();

        if(lastOne == null){
            push(0);
        }
        else{
            push(lastOne + 1);
        }
    }
    public void pop(){

        if(isEmpty()){
            return;
        }
        this.drawOrder.remove(drawOrder.size()-1);
    }
    public void clear(){
        this.drawOrder = new ArrayList<>();
    }


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Accessors
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public boolean isEmpty(){

        return this.drawOrder.size() <= 0;
    }
    public List<Integer> getDrawOrder(){

        return drawOrder;
    }
    public int getDrawOrderCount(){

        return this.drawOrder.size();
    }
    public Integer getLastOneIn(){
        if(isEmpty()){
            return null;
        }
        int lastIndex = this.drawOrder.size()-1;
        int lastOne = this.drawOrder.get(lastIndex);
        return lastOne;
    }
    public Integer getMaxVertexIn(){

        if(isEmpty()){
            return null;
        }
        int max = 0;
        for(Integer i : this.drawOrder){
            if(i > max){
                max = i;
            }
        }
        return max;
    }
}
