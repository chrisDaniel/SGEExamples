package com.cdaniel.simplegameengine.plugins.construction.infrastructure.builders;

import com.cdaniel.simplegameengine.core.Color;
import com.cdaniel.simplegameengine.core.Vertex;
import com.cdaniel.simplegameengine.engine.SGE;

import java.util.ArrayList;

/**
 * Created by christopher.daniel on 5/22/16.
 */
public class BuilderWallSequence{

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Variables / Constructs
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    BuilderWallSequence(){}

    private ArrayList<Vertex> wallVertices = new ArrayList<>();

    private float thickness;
    private float height;

    private Color color;
    private Integer textureId;

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Builder Properties
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public BuilderWallSequence pushSequence(ArrayList<Vertex> wallVerticeList){
        if(wallVerticeList.size()%2 == 1){
            return this;
        }
        this.wallVertices.addAll(wallVerticeList);
        return this;
    }
    public BuilderWallSequence pushWall(Vertex wallStart, Vertex wallEnd){
        this.wallVertices.add(wallStart);
        this.wallVertices.add(wallEnd);
        return this;
    }
    public BuilderWallSequence height(float height){
        this.height = height;
        return this;
    }
    public BuilderWallSequence thickness(float thickness){
        this.thickness = thickness;
        return this;
    }
    public BuilderWallSequence color(Color c){
        this.color = c;
        return this;
    }
    public BuilderWallSequence textureId(int textureId){
        this.textureId = textureId;
        return this;
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Build
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public ArrayList<Integer> build() {

        ArrayList<Integer> toReturn = new ArrayList<>();

        int counter = 0;
        while (counter < this.wallVertices.size()) {

            Vertex vStart = this.wallVertices.get(counter);
            counter++;
            Vertex vEnd = this.wallVertices.get(counter);
            counter++;

            int thisOne = SGE.construct().infrastructure().wall().startX(vStart.getX()).startZ(vStart.getZ()).endX(vEnd.getX()).endZ(vEnd.getZ())
                    .height(height).thickness(thickness).textureId(textureId).color(color).y(vStart.getY()).build();

            toReturn.add(thisOne);
        }
        return toReturn;
    }
}
