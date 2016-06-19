package com.cdaniel.simplegameengine.plugins.construction.simpleshapes.main;

import com.cdaniel.simplegameengine.core.Color;
import com.cdaniel.simplegameengine.core.SimpleGameEngine;
import com.cdaniel.simplegameengine.engine.SGE;
import com.cdaniel.simplegameengine.plugins.construction.texturizer.Texturizer;
import com.cdaniel.simplegameengine.plugins.construction.texturizer.Texturizer_simple;
import com.cdaniel.simplegameengine.utils.transformers.Transform_Move;
import com.cdaniel.simplegameengine.utils.transformers.Transform_Scale;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by christopher.daniel on 5/22/16.
 */
public abstract class AbstractShapeBuilder<T extends AbstractShapeBuilder> {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Common Builder Fields and The Build Method
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    protected Color color;
    protected Texturizer texturizer = Texturizer_simple.noTexture;
    protected float mat_diffuse[];
    protected float mat_ambient[];
    protected float mat_specular[];
    protected float mat_emmissive[];
    protected Float mat_shininess;

    public T color(Color c){
        this.color = c;
        return returnYourself();
    }
    public T textureId(int textureId){
        Texturizer texturizer = new Texturizer_simple(textureId);
        this.texturizer = texturizer;
        return returnYourself();
    }
    public T texturizer(Texturizer texturizer){
        this.texturizer = texturizer;
        return returnYourself();
    }
    public T materialDiffuse(float[] diffuse){
        this.mat_diffuse = diffuse;
        return returnYourself();
    }
    public T materialAmbient(float[] ambient){
        this.mat_ambient = ambient;
        return returnYourself();
    }
    public T materialSpecular(float[] specular){
        this.mat_specular = specular;
        return returnYourself();
    }
    public T materialEmmissive(float[] emmissive){
        this.mat_emmissive = emmissive;
        return returnYourself();
    }
    public T materialShine(float shininess){
        this.mat_shininess = shininess;
        return returnYourself();
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Abstract Requirements
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    protected abstract T returnYourself();
    public abstract int build();


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Properties
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    protected Map<String, Object> properties = new HashMap<>();

    protected void startNewProperties(){

        properties = new HashMap<>();

        properties.put(SimpleGameEngine.CONTENT_STATE, SimpleGameEngine.CONTENT_STATE_Active);
        properties.put(SimpleGameEngine.CONTENT_VIZ, SimpleGameEngine.CONTENT_VIZ_Yes);
    }
    protected void addNewProperty(String property, Object propertyValue){

        properties.put(property, propertyValue);
    }
    protected void applyTextureColorProperty(){

        if(this.texturizer==null || this.texturizer.getTextureId() < 0) {
            addNewProperty(SimpleGameEngine.CONTENT_COLOR, color);
        }
        else{
            addNewProperty(SimpleGameEngine.CONTENT_TEXTURE_ID, texturizer.getTextureId());
        }
    }


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Frequent Transforms
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    protected void sizeNewContent(int contentId, float size) {

        Transform_Scale tsize = Transform_Scale.builder().scale(size).build();
        SGE.contents().get(contentId).applyTransform(tsize);
    }
    protected void moveNewContent(int contentId, Float x, Float y, Float z){

        Transform_Move tmove = Transform_Move.builder().toX(x).toY(y).toZ(z).build();
        SGE.contents().get(contentId).applyTransform(tmove);
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Content Help
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    protected void applyContentProperties(int contentId){

        //Content's Material Properties
        //Type 1 .... If not supplied but Color is then use color
        if(this.mat_diffuse != null){
            SGE.contents().get(contentId).setMaterialDiffuse(this.mat_diffuse);
        }
        else if(this.color != null){
            SGE.contents().get(contentId).setMaterialDiffuse(this.color.getColor());
        }

        if(this.mat_ambient != null){
            SGE.contents().get(contentId).setMaterialAmbient(this.mat_ambient);
        }
        else if(this.color != null){
            SGE.contents().get(contentId).setMaterialAmbient(this.color.getColor());
        }

        //Content's Material Properties
        //Type 2 .... Only Set Content if Value is Supplied
        if(this.mat_emmissive != null){
            SGE.contents().get(contentId).setMaterialEmmissive(this.mat_emmissive);
        }
        if(this.mat_specular != null){
            SGE.contents().get(contentId).setMaterialSpecular(this.mat_specular);
        }
        if(this.mat_shininess != null){
            SGE.contents().get(contentId).setMaterialShininess(this.mat_shininess);
        }
    }
}
