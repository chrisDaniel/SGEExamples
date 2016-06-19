package com.cdaniel.simplegameengine.plugins.construction.infrastructure.builders;

/**
 * Created by christopher.daniel on 6/5/16.
 */
public class InfrastructureBuilder {


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Floor / Ceiling / Wall Constructors
     *
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public BuilderFloor floor(){
        return new BuilderFloor();
    }
    public BuilderCeiling ceiling() {return new BuilderCeiling();}
    public BuilderWall wall(){
        return new BuilderWall();
    }
    public BuilderWallSequence wall_sequence() {return new BuilderWallSequence();}


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Column Constructors
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public BuilderColumnRectangular column_square(){return new BuilderColumnRectangular();}
    public BuilderPlatformWithColumn column_platform(){return new BuilderPlatformWithColumn();}


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Tube Constructors
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public BuilderRightAngleTube tube_rightAngle(){return new BuilderRightAngleTube();}
    public BuilderTube tube(){return new BuilderTube();}
}
