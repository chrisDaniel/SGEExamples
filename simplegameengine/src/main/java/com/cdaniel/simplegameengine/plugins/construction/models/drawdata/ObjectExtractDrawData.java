package com.cdaniel.simplegameengine.plugins.construction.models.drawdata;

import com.cdaniel.simplegameengine.core.DrawableData;
import com.cdaniel.simplegameengine.plugins.construction.texturizer.Texturizer;
import com.cdaniel.simplegameengine.utils.assemblers.DrawOrderCollection;
import com.cdaniel.simplegameengine.utils.assemblers.VertexCollection;
import com.cdaniel.simplegametools.objectextractor.ObjectExtract;

import java.util.HashMap;
import java.util.Map;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by christopher.daniel on 8/13/16.
 */
public class ObjectExtractDrawData implements DrawableData{

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Draw Paramaters
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    protected ObjectExtract extract;
    protected VertexCollection vertices;
    protected DrawOrderCollection drawOrder;
    protected VertexCollection textureCollection;
    protected VertexCollection normalCollection;


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Constructor
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public ObjectExtractDrawData(ObjectExtract extract){
        setExtractData(extract);
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Set and Parse the Extract Data
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void setExtractData(ObjectExtract extract) {
        this.extract = extract;
        processExtractData();
    }

    private void processExtractData_V() {

        this.vertices = new VertexCollection();

        int v = 0;
        while (v < extract.getVertices().size()) {

            vertices.push(extract.getVertices().get(v),
                    extract.getVertices().get(v + 1),
                    extract.getVertices().get(v + 2));

            v = v + 3;
        }
    }
    private void processExtractData_T() {

        this.textureCollection = new VertexCollection();

        int t = 0;
        while (t < extract.getTextures().size()) {

            textureCollection.push(extract.getTextures().get(t),
                    extract.getTextures().get(t + 1), 0);

            t = t + 2;
        }
    }

    //Extract Normals is a bit more complex
    // - We have a set of normals in the extract object (3 values per normal)
    // - We have a drawNormalOrder which defines what order to use the normals
    //
    private void processExtractData_N() {

        this.normalCollection = new VertexCollection();

        Map<Integer, float[]> definedNormals = new HashMap<>();

        int n = 0;int count = 1;
        while(n < extract.getNormals().size()){

            float thisNormValues[] = {extract.getNormals().get(n),
                    extract.getNormals().get(n+1),
                    extract.getNormals().get(n+2)};

            definedNormals.put(count, thisNormValues);

            n = n+3;
            count++;
        }

        for(Integer nId : extract.getDrawNormalOrder()){
            float[] pushThese = definedNormals.get(nId);
            this.normalCollection.push(pushThese[0], pushThese[1], pushThese[2]);
        }
    }
    private void processExtractData_D() {

        this.drawOrder = new DrawOrderCollection();

        //todo - not safe to assume that the draw order collection is always 1 based
        //       which we then convert to 0 based
        for(Integer d : extract.getDrawVerticeOrder()){
            this.drawOrder.push(d-1);
        }
    }

    private void processExtractData() {

        this.vertices = new VertexCollection();
        this.normalCollection = new VertexCollection();
        this.textureCollection = new VertexCollection();
        this.drawOrder = new DrawOrderCollection();

        //Prepare
        //Definition Maps of Vertices and Normals
        Map<Integer, float[]> definedVertices = processExtractData_getVertexMap();
        Map<Integer, float[]> definedNormals  = processExtractData_getNormalMap();
        Map<Integer, float[]> definedTextures = processExtractData_getTextureMap();

        //Apply...
        //Add the vertices and build the draw order
        for(Integer vId : extract.getDrawVerticeOrder()){
            float[] pushThese = definedVertices.get(vId);
            this.vertices.push(pushThese[0], pushThese[1], pushThese[2]);
            this.drawOrder.push_onePlusPrior();
        }

        //Apply...
        //Add the normals
        for(Integer nId : extract.getDrawNormalOrder()){
            float[] pushThese = definedNormals.get(nId);
            this.normalCollection.push(pushThese[0], pushThese[1], pushThese[2]);
        }

        //Apply...
        //Add the textures
        for(Integer tId : extract.getDrawNormalOrder()){
            float[] pushThese = definedNormals.get(tId);
            this.textureCollection.push(pushThese[0], pushThese[1], pushThese[2]);
        }
    }

    private Map<Integer, float[]> processExtractData_getVertexMap() {

        Map<Integer, float[]> definedVertices = new HashMap<>();

        int v = 0;int count = 1;
        while(v < extract.getVertices().size()){

            float thisVertValues[] = {extract.getVertices().get(v),
                    extract.getVertices().get(v+1),
                    extract.getVertices().get(v+2)};

            definedVertices.put(count, thisVertValues);

            v = v+3;
            count++;
        }

        return definedVertices;
    }
    private Map<Integer, float[]> processExtractData_getNormalMap() {

        Map<Integer, float[]> definedNormals = new HashMap<>();

        int n = 0; int count = 1;
        while(n < extract.getNormals().size()){

            float thisNormValues[] = {extract.getNormals().get(n),
                    extract.getNormals().get(n+1),
                    extract.getNormals().get(n+2)};

            definedNormals.put(count, thisNormValues);

            n = n+3;
            count++;
        }
        return definedNormals;
    }
    private Map<Integer, float[]> processExtractData_getTextureMap() {

        Map<Integer, float[]> definedTextures = new HashMap<>();

        int t = 0;int count = 1;
        while(t < extract.getTextures().size()){

            float thisValues[] = {extract.getTextures().get(t),
                    extract.getTextures().get(t+1),
                    extract.getTextures().get(t+2)};

            definedTextures.put(count, thisValues);

            t = t+3;
            count++;
        }
        return definedTextures;
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Interface Methods for Draw Data
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public VertexCollection getVertexCollection() {
        return this.vertices.copy();
    }

    @Override
    public DrawOrderCollection getDrawOrder() {
        return this.drawOrder.copy();
    }

    @Override
    public VertexCollection getTextureCollection(){
        return this.textureCollection;
    }

    @Override
    public VertexCollection getNormalCollection(){
        return this.normalCollection;
    }

    @Override
    public int getGlDrawShape(){
        return GL10.GL_TRIANGLES;
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Clone
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public ObjectExtractDrawData copy(){

        ObjectExtractDrawData drawData = new ObjectExtractDrawData(extract);

        if(this.vertices != null) {
            drawData.vertices = this.vertices.copy();
        }
        if(this.drawOrder != null) {
            drawData.drawOrder = this.drawOrder.copy();
        }
        if(this.textureCollection != null){
            drawData.textureCollection = this.textureCollection.copy();
        }
        if(this.normalCollection != null){
            drawData.normalCollection = this.normalCollection.copy();
        }

        return drawData;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Texturizable
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void applyTexturizer(Texturizer t){
        t.texturize(this.getTextureCollection());
    }
}
