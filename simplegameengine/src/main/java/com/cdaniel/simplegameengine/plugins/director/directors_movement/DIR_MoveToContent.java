package com.cdaniel.simplegameengine.plugins.director.directors_movement;

import com.cdaniel.simplegameengine.engine.SGE;
import com.cdaniel.simplegameengine.engine.SGEContentWrapper;
import com.cdaniel.simplegameengine.plugins.director.AbstractBaseDir;
import com.cdaniel.simplegameengine.plugins.director.CameraDirector;
import com.cdaniel.simplegameengine.utils.calculations.Calc_VectorMath;
import com.cdaniel.simplegameengine.utils.calculations.Calc_VertexMath;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVector;
import com.cdaniel.simplegameengine.utils.transformers.Transform_Move;

/**
 * Created by christopher.daniel on 5/21/16.
 */
public class DIR_MoveToContent extends AbstractBaseDir implements CameraDirector {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Construct and Variables
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private SGEContentWrapper contentToFollow;
    private Transform_Move t;
    private float distanceToTravel;

    DIR_MoveToContent(Integer contentId, Float duration) {

        this.duration = duration;

        if(contentId != null) {
            contentToFollow = SGE.contents().get(contentId);
        }

        t = Transform_Move.builder().build();
    }

    @Override
    protected void initialize_II(){

        this.distanceToTravel = Calc_VertexMath.distanceBetween(SGE.camera().getEyePosition(), contentToFollow.getCenter());
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Action
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    SimpleVector execVector = new SimpleVector();
    protected void executeDirection_II(float executionPercent){

        float newExecVectorMag = distanceToTravel - (distanceToTravel * executionPercent);

        execVector.transform(contentToFollow.getCenter(), SGE.camera().getEyePosition());
        execVector.transform(Calc_VectorMath.resizeVector(execVector, newExecVectorMag));

        t.updateValues(execVector.getEndVertex());
        SGE.camera().applyPositionTransform(t);
    }
    protected void finishUp(){

        t.updateValues(contentToFollow.getCenter());
        SGE.camera().applyPositionTransform(t);
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Builder
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static DIR_MoveToContentBuilder builder() {
        return new DIR_MoveToContentBuilder();
    }
    public static class DIR_MoveToContentBuilder {

        private Integer contentId;
        private Float duration;

        public DIR_MoveToContent.DIR_MoveToContentBuilder contentId(int contentId) {
            this.contentId = contentId;
            return this;
        }
        public DIR_MoveToContent.DIR_MoveToContentBuilder duration(float duration) {
            this.duration = duration;
            return this;
        }
        public DIR_MoveToContent build() {
            return new DIR_MoveToContent(contentId, duration);
        }
    }
}
