package com.cdaniel.simplegametools.objectextractor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by christopher.daniel on 8/13/16.
 */
public class ObjectExtract {

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Variables
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private List<Float> vertices;
    private List<Float> normals;
    private List<Float> textures;

    private List<Integer> drawVerticeOrder;
    private List<Integer> drawNormalOrder;
    private List<Integer> drawTextureOrder;


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Construction
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public ObjectExtract(){

        vertices = new ArrayList<>();
        normals  = new ArrayList<>();
        textures = new ArrayList<>();

        drawVerticeOrder = new ArrayList<>();
        drawNormalOrder  = new ArrayList<>();
        drawTextureOrder = new ArrayList<>();
    }

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Add Data
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void addVertex(float x, float y, float z){
        vertices.add(x);
        vertices.add(y);
        vertices.add(z);
    }
    public void addNormal(float x, float y, float z){
        normals.add(x);
        normals.add(y);
        normals.add(z);
    }
    public void addTexture(float x, float y, float z){
        textures.add(x);
        textures.add(y);
        textures.add(z);
    }
    public void addDrawVerticeOrder(int a, int b, int c){
        drawVerticeOrder.add(a);
        drawVerticeOrder.add(b);
        drawVerticeOrder.add(c);
    }
    public void addDrawNormalOrder(int a, int b, int c){
        drawNormalOrder.add(a);
        drawNormalOrder.add(b);
        drawNormalOrder.add(c);
    }
    public void addDrawTextureOrder(int a, int b, int c){
        drawTextureOrder.add(a);
        drawTextureOrder.add(b);
        drawTextureOrder.add(c);
    }

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Getters / Setteres
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public List<Float> getVertices() {
        return vertices;
    }

    public void setVertices(List<Float> vertices) {
        this.vertices = vertices;
    }

    public List<Float> getNormals() {
        return normals;
    }

    public void setNormals(List<Float> normals) {
        this.normals = normals;
    }

    public List<Float> getTextures() {
        return textures;
    }

    public void setTextures(List<Float> textures) {
        this.textures = textures;
    }

    public List<Integer> getDrawVerticeOrder() {
        return drawVerticeOrder;
    }

    public void setDrawVerticeOrder(List<Integer> drawVerticeOrder) {this.drawVerticeOrder = drawVerticeOrder;}

    public List<Integer> getDrawNormalOrder() {return drawNormalOrder;}

    public void setDrawNormalOrder(List<Integer> drawNormalOrder) {this.drawNormalOrder = drawNormalOrder;}

    public List<Integer> getDrawTextureOrder() {return drawTextureOrder;}

    public void setDrawTextureOrder(List<Integer> drawTextureOrder) {this.drawTextureOrder = drawTextureOrder;}

}
