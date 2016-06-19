package com.cdaniel.simplegameengine.utils.assemblers;

import com.cdaniel.simplegameengine.core.Vertex;
import com.cdaniel.simplegameengine.utils.calculations.Calc_VertexMath;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVertex;

import java.util.List;

/**
 * Created by christopher.daniel on 5/4/16.
 */
public class VertexCollection {

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Variables
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private float[] holder;
    private int holderMaxPosition;
    private int holderCursorPosition;
    private int holderIncrementStep = 1000;

    private float maxX;
    private float maxY;
    private float maxZ;
    private float minX;
    private float minY;
    private float minZ;
    private float maxAll;

    public VCiterator iterator;
    private VCiterator privateiterator;

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Constructors
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public VertexCollection(){

        iterator = new VCiterator(this);
        privateiterator = new VCiterator(this);
        this.initializeCollection();
    }
    public VertexCollection(List<Vertex> vertices){

        iterator = new VCiterator(this);
        privateiterator = new VCiterator(this);
        this.initializeCollection();

        for(Vertex v : vertices){
            this.push(v);
        }
    }
    private void initializeCollection(){

        holderMaxPosition  = 100;
        holderCursorPosition = 0;

        holder = new float[holderMaxPosition];

        this.resetMaxMins();
    }

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Clone
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public VertexCollection copy(){

        VertexCollection copy = new VertexCollection();

        copy.holderMaxPosition = holderMaxPosition;
        copy.holderCursorPosition = holderCursorPosition;
        copy.holderIncrementStep = holderIncrementStep;
        copy.holder = new float[holderMaxPosition];

        copy.holder = this.holder.clone();

        copy.maxX = maxX;
        copy.maxY = maxY;
        copy.maxZ = maxZ;
        copy.minX = minX;
        copy.minY = minY;
        copy.minZ = minZ;
        copy.maxAll = maxAll;

        return copy;
    }
    public void copyValuesFrom(VertexCollection fromVc, float offsetX, float offsetY, float offsetZ){

        if(this.holderMaxPosition < fromVc.holderMaxPosition){
            this.holder = new float[holderMaxPosition];
        }

        int i = -1;
        float offset;
        for(float f : fromVc.holder){

            i++;

            if(i%3==0)     {offset = offsetX;}
            else if(i%3==1){offset = offsetY;}
            else           {offset = offsetZ;}

            this.holder[i] = (f + offset);
        }

        this.holderMaxPosition = fromVc.holderMaxPosition;
        this.holderCursorPosition = fromVc.holderCursorPosition;
        this.holderIncrementStep = fromVc.holderIncrementStep;

        this.calculateAllMaxMins();
    }

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Mutators
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void push(Vertex v){

        this.addToHolder(v.getX(), v.getY(), v.getZ());
    }
    public void push(float x, float y, float z){

        this.addToHolder(x, y, z);
    }
    public void pop(){

        if(calc_isEmpty()){
            return;
        }
        this.removeLastVertexFromHolder();
    }
    public void clear(){

        this.initializeCollection();
    }

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * At Vertex Mutators
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    void transformVertex(int vertexNumber, float x, float y, float z){

        int atX = this.calc_indexX(vertexNumber);
        this.holder[atX] = x;

        int atY = this.calc_indexY(vertexNumber);
        this.holder[atY] = y;

        int atZ = this.calc_indexZ(vertexNumber);
        this.holder[atZ] = z;
    }
    void transformVertexX(int vertexNumber, float x){

        int atX = this.calc_indexX(vertexNumber);
        this.holder[atX] = x;
    }
    void transformVertexY(int vertexNumber, float y){

        int atY = this.calc_indexY(vertexNumber);
        this.holder[atY] = y;
    }
    void transformVertexZ(int vertexNumber, float z){

        int atZ = this.calc_indexZ(vertexNumber);
        this.holder[atZ] = z;
    }

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Private Aggregators / Calculators
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void expandHolder(){

        this.holderMaxPosition += holderIncrementStep;
        float[] newHolder = new float[holderMaxPosition];

        int counter = 0;
        for(float f : this.holder){
            newHolder[counter] = f;
            counter++;
        }

        this.holder = newHolder;
    }
    private void addToHolder(float x, float y, float z){

        if(this.holderCursorPosition +3 >= this.holderMaxPosition){
            expandHolder();
        }

        this.holder[holderCursorPosition] = x;
        holderCursorPosition++;
        this.holder[holderCursorPosition] = y;
        holderCursorPosition++;
        this.holder[holderCursorPosition] = z;
        holderCursorPosition++;

        this.updateMaxMins(x, y, z);
    }
    private void removeLastVertexFromHolder(){
        
        if(this.holderCursorPosition > 2){
            holderCursorPosition -=3;
            this.calculateAllMaxMins();
        }
    }
    private void resetMaxMins(){
        this.maxX  = -9999999f;
        this.maxY  = -9999999f;
        this.maxZ  = -9999999f;
        this.minX  =  9999999f;
        this.minY  =  9999999f;
        this.minZ  =  9999999f;

        this.maxAll= -9999999f;
    }
    private void updateMaxMins(float x, float y, float z){
        
        if(calc_isEmpty()){
            resetMaxMins();
            return;
        }

        if(x > this.maxX){
            this.maxX = x;
        }
        if(x < this.minX){
            this.minX = x;
        }
        if(y > this.maxY){
            this.maxY = y;
        }
        if(y < this.minY){
            this.minY = y;
        }
        if(z > this.maxZ){
            this.maxZ = z;
        }
        if(z < this.minZ){
            this.minZ = z;
        }

        float distV = Calc_VertexMath.distanceFromOrigin(x,y,z);
        if(distV > this.maxAll){
            this.maxAll = distV;
        }
    }
    private void calculateAllMaxMins(){

        resetMaxMins();

        privateiterator.reset();
        while(privateiterator.next()){
            updateMaxMins(privateiterator.x, privateiterator.y, privateiterator.z);
        }
    }

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Calculators
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    boolean calc_isEmpty(){

        return this.holderCursorPosition <= 0;
    }
    int calc_vertexCount(){

        if(this.holderCursorPosition <= 0){
            return 0;
        }

        double by3  = Math.floor( (double) (this.holderCursorPosition + 1) / 3.0);
        int vertexNum = (int) by3;
        return vertexNum;
    }
    int calc_indexX(int vertexNumber){

        int coordIndex = vertexNumber*3;
        return coordIndex;
    }
    int calc_indexY(int vertexNumber){

        int coordIndex = vertexNumber*3 + 1;
        return coordIndex;
    }
    int calc_indexZ(int vertexNumber){

        int coordIndex = vertexNumber*3 + 2;
        return coordIndex;
    }


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Accessors
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public int getVertexCount(){return this.calc_vertexCount();}

    public float getCoordinate_maxX(){
        return this.maxX;
    }
    public float getCoordinate_maxY(){
        return this.maxY;
    }
    public float getCoordinate_maxZ(){
        return this.maxZ;
    }
    public float getCoordinate_minX(){
        return this.minX;
    }
    public float getCoordinate_minY(){
        return this.minY;
    }
    public float getCoordinate_minZ(){
        return this.minZ;
    }
    public float getCoordinate_globalMax() {return this.maxAll; }


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Raw Accessors
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    float getCoordinate(int holderPosition){
        return this.holder[holderPosition];
    }
    public float[] getCoordinateArray(){

        if(calc_isEmpty()){
            float[] empty = new float[0];
            return empty;
        }

        float[] coords = new float[holderCursorPosition];

        int counter = 0;
        privateiterator.reset();
        while(privateiterator.next()){
            coords[counter++] = privateiterator.x;
            coords[counter++] = privateiterator.y;
            coords[counter++] = privateiterator.z;
        }
        return coords;
    }
    public float[] getCoordinateArray(boolean inclX, boolean inclY, boolean inclZ){

        if(calc_isEmpty()){
            float[] empty = new float[0];
            return empty;
        }

        int nbrT = 0;
        if(inclX){nbrT++;}
        if(inclY){nbrT++;}
        if(inclZ){nbrT++;}
        float[] coords = new float[calc_vertexCount() * nbrT];

        int counter = 0;
        privateiterator.reset();
        while(privateiterator.next()) {
            if(inclX){coords[counter++] = privateiterator.x;}
            if(inclY){coords[counter++] = privateiterator.y;}
            if(inclZ){coords[counter++] = privateiterator.z;}
        }

        return coords;
    }

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Iterator
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public class VCiterator {

        /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
         * Vars / Constructors
         * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        int position;
        int last;
        VertexCollection linkRef;

        VCiterator(VertexCollection linkRef){
            this.linkRef = linkRef;
            this.reset();
        }

        /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
         * Traverse Manager
         * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        public void reset(){
            position = -1;
            synch();
        }
        void synch(){

            if(linkRef.getVertexCount()==0){
                this.last = 0;
            }
            else {
                this.last = linkRef.getVertexCount() - 1;
            }
        }
        public boolean next(){

            position++;
            if(position > last) {
                return false;
            }
            updateXyz();
            return true;
        }
        public boolean previous(){

            if(position <= 0){
                return false;
            }
            position--;
            updateXyz();
            return true;
        }


        /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        * Current Vertex Operations
        * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        public float x;
        public float y;
        public float z;

        private void updateXyz(){
            x = linkRef.getCoordinate(linkRef.calc_indexX(position));
            y = linkRef.getCoordinate(linkRef.calc_indexY(position));
            z = linkRef.getCoordinate(linkRef.calc_indexZ(position));
        }
        public void transform(float x, float y, float z){
            linkRef.transformVertex(position, x, y, z);
        }
        public void transformX(float x){
            linkRef.transformVertexX(position, x);
        }
        public void transformY(float y){
            linkRef.transformVertexY(position, y);
        }
        public void transformZ(float z){
            linkRef.transformVertexZ(position, z);
        }


        public SimpleVertex copyVertex(){

            x = linkRef.getCoordinate(linkRef.calc_indexX(position));
            y = linkRef.getCoordinate(linkRef.calc_indexY(position));
            z = linkRef.getCoordinate(linkRef.calc_indexZ(position));

            return new SimpleVertex(x, y, z);
        }
    }
}
