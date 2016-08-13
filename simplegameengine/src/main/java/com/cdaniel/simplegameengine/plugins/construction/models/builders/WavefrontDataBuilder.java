package com.cdaniel.simplegameengine.plugins.construction.models.builders;

import com.cdaniel.simplegameengine.core.Color;
import com.cdaniel.simplegameengine.core.SimpleGameEngine;
import com.cdaniel.simplegameengine.engine.SGE;
import com.cdaniel.simplegameengine.plugins.construction.models.drawdata.Data_Cube;
import com.cdaniel.simplegameengine.plugins.construction.models.drawdata.ObjectExtractDrawData;
import com.cdaniel.simplegameengine.plugins.construction.texturizer.Texturizer;
import com.cdaniel.simplegameengine.plugins.construction.texturizer.Texturizer_simple;
import com.cdaniel.simplegameengine.utils.transformers.Transform_Move;
import com.cdaniel.simplegameengine.utils.transformers.Transform_Scale;
import com.cdaniel.simplegametools.objectextractor.ObjectExtract;
import com.cdaniel.simplegametools.objectextractor.ObjectExtractor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by christopher.daniel on 8/13/16.
 */
public class WavefrontDataBuilder {

    String wavefrontData;

    Color color;
    Texturizer texturizer = Texturizer_simple.noTexture;
    float mat_diffuse[];
    float mat_ambient[];
    float mat_specular[];
    float mat_emmissive[];
    Float mat_shininess;
    float size = 1f;
    float x;
    float y;
    float z;

    public WavefrontDataBuilder(String wavefrontData) {
        this.wavefrontData = wavefrontData;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Builder Properties
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public WavefrontDataBuilder x(float x) {
        this.x = x;
        return this;
    }

    public WavefrontDataBuilder y(float y) {
        this.y = y;
        return this;
    }

    public WavefrontDataBuilder z(float z) {
        this.z = z;
        return this;
    }

    public WavefrontDataBuilder size(float size) {
        this.size = size;
        return this;
    }

    public WavefrontDataBuilder color(Color c) {
        this.color = c;
        return this;
    }

    public WavefrontDataBuilder textureId(int textureId) {
        Texturizer texturizer = new Texturizer_simple(textureId);
        this.texturizer = texturizer;
        return this;
    }

    public WavefrontDataBuilder texturizer(Texturizer texturizer) {
        this.texturizer = texturizer;
        return this;
    }

    public WavefrontDataBuilder materialDiffuse(float[] diffuse) {
        this.mat_diffuse = diffuse;
        return this;
    }

    public WavefrontDataBuilder materialAmbient(float[] ambient) {
        this.mat_ambient = ambient;
        return this;
    }

    public WavefrontDataBuilder materialSpecular(float[] specular) {
        this.mat_specular = specular;
        return this;
    }

    public WavefrontDataBuilder materialEmmissive(float[] emmissive) {
        this.mat_emmissive = emmissive;
        return this;
    }

    public WavefrontDataBuilder materialShine(float shininess) {
        this.mat_shininess = shininess;
        return this;
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Build
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public int build() {

        startNewProperties();
        applyTextureColorProperty();

        ObjectExtract extract = extractObjectData(this.wavefrontData);
        ObjectExtractDrawData drawData = new ObjectExtractDrawData(extract);

        int contentId = SGE.contents().add(drawData, properties);
        applyContentProperties(contentId);

        moveNewContent(contentId, x, y, z);
        sizeNewContent(contentId, size);

        return contentId;
    }

    public ObjectExtract extractObjectData(String wavefrontData) {

        ObjectExtractor extractor = new ObjectExtractor(ObjectExtractor.ObjectType.WAVEFRONT);

        try {
            ObjectExtract extract = extractor.extract(wavefrontData);
            return extract;
        } catch (Exception e) {
            //todo - do something
            //do nothing
        }
        return null;
    }

    public int build(ObjectExtract extract) {
        return 1;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Properties
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    protected Map<String, Object> properties = new HashMap<>();

    protected void startNewProperties() {

        properties = new HashMap<>();

        properties.put(SimpleGameEngine.CONTENT_STATE, SimpleGameEngine.CONTENT_STATE_Active);
        properties.put(SimpleGameEngine.CONTENT_VIZ, SimpleGameEngine.CONTENT_VIZ_Yes);
    }

    protected void addNewProperty(String property, Object propertyValue) {

        properties.put(property, propertyValue);
    }

    protected void applyTextureColorProperty() {

        if (this.texturizer == null || this.texturizer.getTextureId() < 0) {
            addNewProperty(SimpleGameEngine.CONTENT_COLOR, color);
        } else {
            addNewProperty(SimpleGameEngine.CONTENT_TEXTURE_ID, texturizer.getTextureId());
        }
    }

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Transforms
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    protected void sizeNewContent(int contentId, float size) {

        Transform_Scale tsize = Transform_Scale.builder().scale(size).build();
        SGE.contents().get(contentId).applyTransform(tsize);
    }

    protected void moveNewContent(int contentId, Float x, Float y, Float z) {

        Transform_Move tmove = Transform_Move.builder().toX(x).toY(y).toZ(z).build();
        SGE.contents().get(contentId).applyTransform(tmove);
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Content Help
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    protected void applyContentProperties(int contentId) {

        //Content's Material Properties
        //Type 1 .... If not supplied but Color is then use color
        if (this.mat_diffuse != null) {
            SGE.contents().get(contentId).setMaterialDiffuse(this.mat_diffuse);
        } else if (this.color != null) {
            SGE.contents().get(contentId).setMaterialDiffuse(this.color.getColor());
        }

        if (this.mat_ambient != null) {
            SGE.contents().get(contentId).setMaterialAmbient(this.mat_ambient);
        } else if (this.color != null) {
            SGE.contents().get(contentId).setMaterialAmbient(this.color.getColor());
        }

        //Content's Material Properties
        //Type 2 .... Only Set Content if Value is Supplied
        if (this.mat_emmissive != null) {
            SGE.contents().get(contentId).setMaterialEmmissive(this.mat_emmissive);
        }
        if (this.mat_specular != null) {
            SGE.contents().get(contentId).setMaterialSpecular(this.mat_specular);
        }
        if (this.mat_shininess != null) {
            SGE.contents().get(contentId).setMaterialShininess(this.mat_shininess);
        }
    }
}
