package com.cdaniel.simplegameengine.engine;

import com.cdaniel.simplegameengine.core.AbstractHasProperties;
import com.cdaniel.simplegameengine.core.Color;
import com.cdaniel.simplegameengine.core.DrawableData;
import com.cdaniel.simplegameengine.core.HasProperties;
import com.cdaniel.simplegameengine.core.SimpleGameEngine;
import com.cdaniel.simplegameengine.core.Vertex;
import com.cdaniel.simplegameengine.utils.assemblers.DrawOrderCollection;
import com.cdaniel.simplegameengine.utils.assemblers.VertexCollection;
import com.cdaniel.simplegameengine.utils.constructs.LockableVertex;
import com.cdaniel.simplegameengine.utils.constructs.SimpleColor;
import com.cdaniel.simplegameengine.utils.converters.Conv_PropertyValues;
import com.cdaniel.simplegameengine.utils.converters.Conv_ToBuffer;
import com.cdaniel.simplegameengine.utils.transformers.TransformGlobalSpace;
import com.cdaniel.simplegameengine.utils.transformers.TransformLocalSpace;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.Map;

/**
 * Created by christopher.daniel on 5/5/16.
 */
public class SGEContentWrapper extends AbstractHasProperties implements HasProperties{

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Variables
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

    /*~~~~~~~~~~~~~~~~~~~~~~~*/
    /* Wrapper Definition    */
    /*~~~~~~~~~~~~~~~~~~~~~~~*/
    private int contentId;
    private String name = "No Name";
    private String contentGroup = SimpleGameEngine.CONTENT_GROUPING_Default;
    private DrawableData drawableData;
    private boolean dirty;


    /*~~~~~~~~~~~~~~~~~~~~~~~*/
    /* Center                */
    /*~~~~~~~~~~~~~~~~~~~~~~~*/
    private LockableVertex center = new LockableVertex(true);


    /*~~~~~~~~~~~~~~~~~~~~~~~*/
    /* Drawable Vertex Data  */
    /*~~~~~~~~~~~~~~~~~~~~~~~*/
    private VertexCollection    vertexCollection_localSpace;
    private VertexCollection    vertexCollection_globalSpace;

    private DrawOrderCollection drawCollection;
    private VertexCollection    textureCollection;
    private VertexCollection    normalCollection;

    private FloatBuffer vertexBuffer;
    private FloatBuffer textureBuffer;
    private FloatBuffer normalBuffer;
    private ShortBuffer drawBuffer;
    private FloatBuffer colorBuffer;

    
    /*~~~~~~~~~~~~~~~~~~~~~~~*/
    /* Draw Options            */
    /*~~~~~~~~~~~~~~~~~~~~~~~*/
    private boolean visible = true;
    
    private SimpleColor color = new SimpleColor(1f, 1f, 1f, 1f);
    private int textureId = -1;

    private float mat_diffuse[]   = {.9f, .9f, .9f, 1f};
    private float mat_ambient[]   = {.2f, .2f, .2f, 1f};

    private float mat_emmissive[] = {0f, 0f, 0f, 0f};

    private float mat_specular[]  = {.5f, .5f, .5f, 1f};
    private float mat_shininess   = .25f;

    private FloatBuffer material_diffuseBuffer = Conv_ToBuffer.getFloatBuffer(mat_diffuse);
    private FloatBuffer material_ambBuffer = Conv_ToBuffer.getFloatBuffer(mat_ambient);
    private FloatBuffer material_specBuffer = Conv_ToBuffer.getFloatBuffer(mat_specular);
    private FloatBuffer material_emmissiveBuffer = Conv_ToBuffer.getFloatBuffer(mat_emmissive);

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Constructor
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    SGEContentWrapper(int contentId, DrawableData drawableData, Map<String, Object> props){

        this.contentId = contentId;
        this.drawableData = drawableData;
        bindVertexCollections();
        this.dirty = true;

        this.applyProperties(props);
    }
    private void bindVertexCollections(){

        this.vertexCollection_localSpace   = drawableData.getVertexCollection();
        this.vertexCollection_globalSpace  = this.vertexCollection_localSpace.copy();

        this.drawCollection    = drawableData.getDrawOrder();
        this.textureCollection = drawableData.getTextureCollection();
        this.normalCollection  = drawableData.getNormalCollection();
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Content Property Changes
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public void beforePropertyApply(String propertyName, Object propertyValue){

        try {
            if (propertyName.equals(SimpleGameEngine.CONTENT_NAME)) {
                String name = Conv_PropertyValues.extract_String(propertyValue);
                this.name = name;
            }

            if (propertyName.equals(SimpleGameEngine.CONTENT_GROUPING)) {
                String grouping = Conv_PropertyValues.extract_String(propertyValue);

                if (grouping == null || grouping.length() == 0) {
                    this.contentGroup = SimpleGameEngine.CONTENT_GROUPING_Default;
                } else {
                    this.contentGroup = grouping;
                }
            }

            if (propertyName.equals(SimpleGameEngine.CONTENT_TEXTURE_ID)) {
                int texId = Conv_PropertyValues.extract_Integer(propertyValue);
                this.textureId = texId;
            }

            if (propertyName.equals(SimpleGameEngine.CONTENT_COLOR)) {
                Color c = (Color) propertyValue;
                this.setColor(new SimpleColor(c.getColor()));
            }
        }
        catch (Exception e){
                //oh well what the hell
        }
    }



    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Wrapper Data
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public int getContentId(){

        return this.contentId;
    }
    public String getContentGroup(){

        return this.contentGroup;
    }
    public Vertex getCenter(){

        return this.center;
    }
    public VertexCollection getCurrentVertexCollection(){

        VertexCollection lastPublicVertexCollection = new VertexCollection();
        lastPublicVertexCollection.copyValuesFrom(vertexCollection_globalSpace, center.getX(), center.getY(), center.getZ());
        return lastPublicVertexCollection;
    }
    public float getCurrentVertexCollection_maxCoordinate(){

        return vertexCollection_globalSpace.getCoordinate_globalMax();
    }
    public float get_maxX(){
        return vertexCollection_globalSpace.getCoordinate_maxX();
    }
    public float get_minX(){
        return vertexCollection_globalSpace.getCoordinate_minX();
    }
    public float get_maxY(){
        return vertexCollection_globalSpace.getCoordinate_maxY();
    }
    public float get_minY(){
        return vertexCollection_globalSpace.getCoordinate_minY();
    }
    public float get_maxZ(){
        return vertexCollection_globalSpace.getCoordinate_maxZ();
    }
    public float get_minZ(){
        return vertexCollection_globalSpace.getCoordinate_minZ();
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Draw Collections
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    FloatBuffer getVertexBuffer(){return this.vertexBuffer;}
    ShortBuffer getDrawBuffer(){return this.drawBuffer;}
    FloatBuffer getTextureBuffer(){return this.textureBuffer;}
    FloatBuffer getNormalBuffer(){return this.normalBuffer;}
    FloatBuffer getColorBuffer(){return this.colorBuffer; }

    int getDrawSize(){return this.drawCollection.getDrawOrderCount();}
    int getGlDrawShape(){return this.drawableData.getGlDrawShape();}

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Exposed Transformation Methods
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void applyTransform(TransformGlobalSpace t){

        this.dirty = true;

        center.unlock();
        t.transform(this.center);
        center.lock();
    }
    public void applyTransform(TransformLocalSpace t){

        this.dirty = true;
        t.transform(this.vertexCollection_localSpace);
        t.transform(this.normalCollection);
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Exposed Visualization Properties
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~
     * Draw Info Properties
     *~~~~~~~~~~~~~~~~~~~~~~~~~*/
    void setVisibility(boolean visible){
        this.visible = visible;
    }
    boolean isVisible(){
        return this.visible;
    }


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~
    * Material Properties
    *~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void setColor(Color color){
        this.color = new SimpleColor(color);
        update_colorBuffer();
    }
    public Color getColor(){
        return color;
    }

    public void setTextureId(int textureId){
        this.textureId = textureId;
    }
    public int getTextureId(){return textureId; }


    public void setMaterialDiffuse(float[] diffuse){
        this.mat_diffuse = diffuse;
        this.updateMaterialBuffers();
    }
    public FloatBuffer getMaterialDiffuse(){
        return  this.material_diffuseBuffer;
    }
    public void setMaterialAmbient(float[] ambient){
        this.mat_ambient = ambient;
        this.updateMaterialBuffers();
    }
    public FloatBuffer getMaterialAmbient(){
        return this.material_ambBuffer;
    }
    public void setMaterialEmmissive(float[] emmissive){
        this.mat_emmissive = emmissive;
        this.updateMaterialBuffers();
    }
    public FloatBuffer getMaterialEmmissive(){
        return this.material_emmissiveBuffer;
    }
    public void setMaterialSpecular(float[] specular){
        this.mat_specular = specular;
        this.updateMaterialBuffers();
    }
    public FloatBuffer getMaterialSpecular(){
        return this.material_specBuffer;
    }

    public void setMaterialShininess(float shininess){
        this.mat_shininess = shininess;
    }
    public float getMatShininess(){
        return this.mat_shininess;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Pipeline
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    void beforeDraw(){

        if(dirty){
            handleDirty();
            update_buffers();
        }
    }

    void afterDraw(){

        dirty = false;
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Calculations
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void handleDirty(){

        this.vertexCollection_globalSpace.copyValuesFrom(vertexCollection_localSpace, center.getX(), center.getY(), center.getZ());
    }
    private void update_buffers() {

        this.vertexBuffer = Conv_ToBuffer.bufferFromVertexCollection(this.vertexCollection_globalSpace);

        if (drawBuffer == null) {
            this.drawBuffer = Conv_ToBuffer.bufferFromDrawOrderCollection(this.drawCollection);
        }
        if (drawableData.getTextureCollection().getVertexCount() > 0){ // && textureBuffer == null) {
            float[] textureCoordinates = this.textureCollection.getCoordinateArray(true, true, false);
            this.textureBuffer = Conv_ToBuffer.getFloatBuffer(textureCoordinates);
        }
        if (normalCollection != null && normalBuffer == null) {
            this.normalBuffer = Conv_ToBuffer.bufferFromVertexCollection(this.normalCollection);
        }
    }
    private void update_colorBuffer(){

        int colorArraySize = vertexCollection_globalSpace.getVertexCount() * 4;
        float[] colorArray = new float[colorArraySize];

        int i = 0;
        while(i<vertexCollection_globalSpace.getVertexCount()){
            colorArray[i] = this.color.getRed();   i++;
            colorArray[i] = this.color.getGreen(); i++;
            colorArray[i] = this.color.getBlue();  i++;
            colorArray[i] = this.color.getAlpha(); i++;
        }
        this.colorBuffer = Conv_ToBuffer.getFloatBuffer(colorArray);
    }
    private void updateMaterialBuffers() {
        material_diffuseBuffer = Conv_ToBuffer.getFloatBuffer(mat_diffuse);
        material_ambBuffer = Conv_ToBuffer.getFloatBuffer(mat_ambient);
        material_specBuffer = Conv_ToBuffer.getFloatBuffer(mat_specular);
        material_emmissiveBuffer = Conv_ToBuffer.getFloatBuffer(mat_emmissive);
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * To String
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public String toString(){
        return drawableData.getClass().getSimpleName() + " at " + this.center.toString();
    }
}
