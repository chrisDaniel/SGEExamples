package com.cdaniel.simplegameengine.plugins.director.directors_strategy;

import com.cdaniel.simplegameengine.engine.SGE;
import com.cdaniel.simplegameengine.engine.SGEContentWrapper;
import com.cdaniel.simplegameengine.plugins.director.CameraDirector;
import com.cdaniel.simplegameengine.utils.calculations.Calc_VectorMath;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVector;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVertex;
import com.cdaniel.simplegameengine.utils.transformers.Transform_Move;

/**
 * Created by christopher.daniel on 5/21/16.
 */
public class DIR_FollowBehind implements CameraDirector {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Construct and Variables
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private SGEContentWrapper contentToFollow;
    private Float offsetX;
    private Float offsetY;
    private Float offsetZ;
    private Float trailDistance;
    
    private Transform_Move t;

    DIR_FollowBehind(Integer contentId, Float offsetX, Float offsetY, Float offsetZ, Float trailDistance) {
        
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.offsetZ = offsetZ;
        this.trailDistance = trailDistance;

        if(contentId != null) {
            contentToFollow = SGE.contents().get(contentId);
        }

        t = Transform_Move.builder().build();
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Action
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private SimpleVector v = new SimpleVector();

    @Override
    public boolean executeDirection(){

        if(contentToFollow == null){
            return false;
        }

        v.transform(SGE.camera().getLookingAt(), new SimpleVertex(contentToFollow.getCenter().getX()+3, contentToFollow.getCenter().getY()+4, contentToFollow.getCenter().getZ()+1));
        v.transform(Calc_VectorMath.increaseVectorSize(v, trailDistance));

        t.updateValues(v.getEndVertex());
        SGE.camera().applyPositionTransform(t);

        return true;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Builder
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static DIR_FollowBehindBuilder builder() {
        return new DIR_FollowBehindBuilder();
    }
    public static class DIR_FollowBehindBuilder {

        private Integer contentIdToFollow;
        private Float offsetX = 0f;
        private Float offsetY = 3f;
        private Float offsetZ = 0f;
        private Float trailDistance = 2f;


        public DIR_FollowBehind.DIR_FollowBehindBuilder contentIdToFollow(int contentIdToFollow) {
            this.contentIdToFollow = contentIdToFollow;
            return this;
        }
        public DIR_FollowBehind.DIR_FollowBehindBuilder offsetX(float offsetX) {
            this.offsetX = offsetX;
            return this;
        }
        public DIR_FollowBehind.DIR_FollowBehindBuilder offsetY(float offsetY) {
            this.offsetY = offsetY;
            return this;
        }
        public DIR_FollowBehind.DIR_FollowBehindBuilder offsetZ(float offsetZ) {
            this.offsetZ = offsetZ;
            return this;
        }
        public DIR_FollowBehind.DIR_FollowBehindBuilder trailDistance(float trailDistance) {
            this.trailDistance = trailDistance;
            return this;
        }
        public DIR_FollowBehind build() {
            return new DIR_FollowBehind(contentIdToFollow, offsetX, offsetY, offsetZ, trailDistance);
        }
    }
}
