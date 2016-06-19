package com.cdaniel.simplegameengine.engine;

import android.opengl.GLU;

import com.cdaniel.simplegameengine.core.SimpleGameEngine;
import com.cdaniel.simplegameengine.core.Transform;
import com.cdaniel.simplegameengine.core.Vertex;
import com.cdaniel.simplegameengine.utils.constructs.LockableVertex;
import com.cdaniel.simplegameengine.utils.converters.Conv_PropertyValues;
import com.cdaniel.simplegameengine.utils.transformers.TransformGlobalSpace;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by christopher.daniel on 5/7/16.
 */
public class SGECamera implements SGEPipeline {


    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //Properties
    //
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    //********************
    //The Camera Position
    //********************
    private LockableVertex position = new LockableVertex(3f, 7f, 21f, true);
    private LockableVertex lookAt   = new LockableVertex(0f, 1.5f, 0f, true);
    private LockableVertex headTilt = new LockableVertex(0f, 1f, 0f, true);


    //********************
    //The Lens
    //********************
    private boolean dirtyLens;

    private int viewPortX      = 0;
    private int viewPortY      = 0;
    private int viewPortWidth  = 0;
    private int viewPortHeight = 0;

    private float frustLeft   = -1f;
    private float frustRight  = 1f;
    private float frustBottom = -1f;
    private float frustTop    = 1f;
    private float frustNear   = .9f;
    private float frustFar    = 115f;

    private float screenWidth  = 0f;
    private float screenHeight = 0f;
    private float screenRatio  = 0f;


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Constructor
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    SGECamera() {
        dirtyLens = true;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Exposed Transformation Methods
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/


    //********************
    //The Position
    //********************
    public void applyPositionTransform(TransformGlobalSpace transform) {

        this.position.unlock();
        transform.transform(this.position);
        this.position.lock();
    }

    public void applyLookAtTransform(TransformGlobalSpace transform) {

        this.lookAt.unlock();
        transform.transform(this.lookAt);
        this.lookAt.lock();
    }

    public void applyTiltTransform(Transform transform) {

        this.headTilt.unlock();
        transform.transform(this.headTilt);
        this.headTilt.lock();
    }

    //********************
    //The Lens
    //********************
    public void changeDepthFocus(float near, float far) {

        if (near >= far || near < 0) {
            return;
        }
        frustNear = near;
        frustFar = far;
    }

    public void changeHorizontalFocus(float left, float right) {

        if (frustInSanity(left) || frustInSanity(right) || left >= right) {
            return;
        }

        dirtyLens = true;
        frustLeft = left;
        frustRight = right;
    }

    public void changeVerticalFocus(float bottom, float top) {

        if (frustInSanity(bottom) || frustInSanity(top) || bottom >= top) {
            return;
        }

        dirtyLens = true;
        frustBottom = bottom;
        frustTop = top;
    }

    private boolean frustInSanity(float value) {
        if (value < -1 || value > 1) {
            return true;
        }
        return false;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Pipeline
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    void beforeDraw(GL10 gl) {

        //Part 1...
        //The Lens:  Viewport, Frustrum, Pixel Changes, etc.
        if (SGE.properties().isPropertyDirty(SimpleGameEngine.SGE_PIXEL_HEIGHT) ||
                SGE.properties().isPropertyDirty(SimpleGameEngine.SGE_PIXEL_WIDTH)) {

            dirtyLens = true;
        }

        if (dirtyLens) {
            handleDirtyLens(gl);
        }

        //Part 2...
        //The Look at Matrix ... the camera position and direction
        handleCameraPosition(gl);
    }

    void afterDraw(GL10 gl) {

        dirtyLens = false;
    }


    //******************************************************
    //The Camera Position
    //******************************************************
    private void handleCameraPosition(GL10 gl) {

        camerPosition_setLookAt(gl);
    }
    private void camerPosition_setLookAt(GL10 gl) {
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        GLU.gluLookAt(gl, position.getX(), position.getY(), position.getZ(),
                lookAt.getX(), lookAt.getY(), lookAt.getZ(),
                headTilt.getX(), headTilt.getY(), headTilt.getZ());
    }

    //******************************************************
    //The Lens
    //******************************************************
    private void handleDirtyLens(GL10 gl) {

        dirtyLens_calculations();
        dirtyLens_viewPort(gl);
        dirtyLens_frustrum(gl);
    }

    private void dirtyLens_viewPort(GL10 gl) {

        gl.glViewport(viewPortX, viewPortY, viewPortWidth, viewPortHeight);
    }

    private void dirtyLens_frustrum(GL10 gl) {

        // Make adjustments for screen ratio
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glFrustumf(frustLeft, frustRight, frustBottom, frustTop, frustNear, frustFar);
    }

    private void dirtyLens_calculations() {

        Object wValue = SGE.properties().read(SimpleGameEngine.SGE_PIXEL_WIDTH);
        this.screenWidth = Conv_PropertyValues.extract_Float(wValue);

        Object hValue = SGE.properties().read(SimpleGameEngine.SGE_PIXEL_HEIGHT);
        this.screenHeight = Conv_PropertyValues.extract_Float(hValue);

        if (this.screenWidth < this.screenHeight) {
            this.screenRatio = this.screenWidth / this.screenHeight;
        } else {
            this.screenRatio = this.screenHeight / this.screenWidth;
        }

        calcViewPort();
        calcFrustValues();
    }

    private void calcViewPort() {
        this.viewPortHeight = (int) this.screenHeight;
        this.viewPortWidth = (int) this.screenWidth;
        this.viewPortX = 0;
        this.viewPortY = 0;
    }

    private void calcFrustValues() {

        if (this.screenWidth > this.screenHeight) {
            this.frustBottom = -1 * screenRatio;
            this.frustTop = screenRatio;
        } else {
            this.frustLeft = -1 * screenRatio;
            this.frustRight = screenRatio;
        }
    }


    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //Get Value
    //
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public Vertex getEyePosition() {
        return this.position;
    }

    public Vertex getLookingAt() {
        return this.lookAt;
    }

    public float getFrustLeft() {
        return this.frustLeft;
    }

    public float getFrustRight() {
        return this.frustRight;
    }

    public float getFrustTop() {
        return this.frustTop;
    }

    public float getFrustBottom() {
        return this.frustBottom;
    }

    public float getFrustNear() {
        return this.frustNear;
    }

    public float getFrustFar() {
        return this.frustFar;
    }


    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //To String
    //
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public String toString(){

        String s = "Position: " + this.getEyePosition().toString() + "\n" +
                   "Looking At: " + this.lookAt.toString() + "\n" +
                   "Head Tilt: " + this.headTilt.toString() + "\n" +
                   "Frustrum: " + "Near/Far: " + this.getFrustNear() + " / " + this.getFrustFar() + "\n" +
                   "               Left/Right: " + this.getFrustLeft() + " / " + this.getFrustRight() + "\n" +
                   "               Top/Bottom: " + this.getFrustTop() + " / "  + this.getFrustBottom() + "\n";

        return s;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Pipeline Contract
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private boolean contentFreezeInPlace = false;

    @Override
    public void contentFreeze(boolean isFrozen){
        contentFreezeInPlace = isFrozen;
    }
    @Override
    public void pause(boolean isPaused){
    }
    @Override
    public void deleteEverything(){
    }
}
