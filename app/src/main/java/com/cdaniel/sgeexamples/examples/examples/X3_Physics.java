package com.cdaniel.sgeexamples.examples.examples;

import com.cdaniel.sgeexamples.examples.manager.Setup_Textures;
import com.cdaniel.simplegameengine.engine.SGE;
import com.cdaniel.simplegameengine.plugins.director.directors_movement.DIR_MoveTo;
import com.cdaniel.simplegameengine.plugins.physics.collisionDetection.CollisionBound_Rectangle;
import com.cdaniel.simplegameengine.plugins.physics.constants.ColliderType;
import com.cdaniel.simplegameengine.plugins.physics.physicalProperties.PHYSPROP_Gravity;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVector;

import java.util.ArrayList;

/**
 * Created by christopher.daniel on 5/23/16.
 */
public class X3_Physics extends AbstractXample {

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Vars
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private Integer floor;
    private Integer ceiling;
    private Integer wallLeft;
    private Integer wallFar;
    private Integer wallRight;
    private Integer wallNear;

    private Integer shape1;
    private Integer shape2;
    private ArrayList<Integer> moreShapes;

    private final float leftX  = -18f;
    private final float rightX = 18f;
    private final float nearZ  = 31f;
    private final float farZ   = -31f;

    private final float height = 20f;


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * On Frame
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public void onFrame() {


        //Set up a Simple Environment
        if (SGE.properties().totalFrames() == 1) {
            SGE.director().queueDirector(DIR_MoveTo.builder().toX(0f).toZ(30f).toY(16).duration(5).build());
        }
        if(SGE.properties().totalFrames() == 2){

            floor = SGE.construct().infrastructure().floor().leftX(leftX).rightX(rightX).nearZ(nearZ).farZ(farZ).y(0).textureId(Setup_Textures.texture_metalPanel).build();
            wallLeft = SGE.construct().infrastructure().wall().startX(leftX).endX(leftX).startZ(nearZ).endZ(farZ).y(0f).height(height).textureId(Setup_Textures.texture_metalPanel).build();
            wallFar = SGE.construct().infrastructure().wall().startX(leftX).endX(rightX).startZ(farZ).endZ(farZ).y(0f).height(height).textureId(Setup_Textures.texture_metalPanel).build();
            wallRight = SGE.construct().infrastructure().wall().startX(rightX).endX(rightX).startZ(nearZ).endZ(farZ).y(0f).height(height).textureId(Setup_Textures.texture_metalPanel).build();
            wallNear = SGE.construct().infrastructure().wall().startX(leftX).endX(rightX).startZ(nearZ).endZ(nearZ).y(0f).height(height).textureId(Setup_Textures.texture_metalPanel).build();
            ceiling = SGE.construct().infrastructure().floor().leftX(leftX).rightX(rightX).nearZ(nearZ).farZ(farZ).y(height).textureId(Setup_Textures.texture_birchwood).build();
        }

        if (SGE.properties().totalFrames() == 10) {
            shape1 = SGE.construct().shapes().sphere().y(5.5f).textureId(Setup_Textures.texture1).build();
            shape2 = SGE.construct().shapes().sphere().size(3f).x(4f).y(9f).z(-6f).textureId(Setup_Textures.texture2).build();
        }

        //Enable Physics on the 2 Shapes
        if(SGE.properties().totalFrames() == 170) {
            SGE.physics().activator().contentId(shape1).mass(1000).elasticity(.9f).colliderType(ColliderType.Initiator)
                    .initialVelocity(new SimpleVector(0f, 0f, 0f, -3f, 0f, -3f)).activate();
            SGE.physics().activator().contentId(shape2).mass(1000).elasticity(.9f).colliderType(ColliderType.Initiator)
                    .initialVelocity(new SimpleVector(0f, 0f, 0f, 2f, 0f, 0f)).activate();
        }
        //Enable Physics on the Infrastructure
        if(SGE.properties().totalFrames() == 171) {
            SGE.physics().activator().contentId(floor).mass(999999f).elasticity(.1f).colliderType(ColliderType.Listener)
                    .collisionNormal(new SimpleVector(0f, 0f, 0f, 0f, 1f, 0f))
                    .collisionBoundary(new CollisionBound_Rectangle()).activate();
            SGE.physics().activator().contentId(wallLeft).mass(999999f).elasticity(.1f).colliderType(ColliderType.Listener)
                    .collisionNormal(new SimpleVector(0f, 0f, 0f, -1f, 0f, 0f))
                    .collisionBoundary(new CollisionBound_Rectangle()).activate();
            SGE.physics().activator().contentId(wallFar).mass(999999f).elasticity(.1f).colliderType(ColliderType.Listener)
                    .collisionNormal(new SimpleVector(0f, 0f, 0f, 0f, 0f, 1f))
                    .collisionBoundary(new CollisionBound_Rectangle()).activate();
            SGE.physics().activator().contentId(wallRight).mass(999999f).elasticity(.1f).colliderType(ColliderType.Listener)
                    .collisionNormal(new SimpleVector(0f, 0f, 0f, 1f, 0f, 0f))
                    .collisionBoundary(new CollisionBound_Rectangle()).activate();
            SGE.physics().activator().contentId(wallNear).mass(999999f).elasticity(.1f).colliderType(ColliderType.Listener)
                    .collisionNormal(new SimpleVector(0f, 0f, 0f, 0f, 0f, -1f))
                    .collisionBoundary(new CollisionBound_Rectangle()).activate();
            SGE.physics().activator().contentId(ceiling).mass(999999f).elasticity(.1f).colliderType(ColliderType.Listener)
                    .collisionNormal(new SimpleVector(0f, 0f, 0f, 0f, -1f, 0f))
                    .collisionBoundary(new CollisionBound_Rectangle()).activate();
        }

        //Apply Physical Properties ... Start them up
        if(SGE.properties().totalFrames()==180){
            SGE.physics().applyPhysicalProperty(shape1, new PHYSPROP_Gravity());
            SGE.physics().applyPhysicalProperty(shape2, new PHYSPROP_Gravity());
        }

        //More More More
        if(SGE.properties().totalFrames()==480) {

            moreShapes = new ArrayList<>();

            int numberExtraShapes = 20;

            for (int n = 0; n < numberExtraShapes; n++) {
                int s = SGE.construct().shapes().sphere().y(15f).textureId(Setup_Textures.texture3).build();
                moreShapes.add(s);

                float negPosX = -1;
                float negPosZ = -1;
                if(n%3==1){
                    negPosX = 1;
                }
                if(n%3==2){
                    negPosX = 1;
                    negPosZ = 1;
                }

                float speedX = negPosX * (float) Math.random() * 8f;
                float speedY = (float) Math.random() * 8f;
                float speedZ = negPosZ * (float) Math.random() * 8f;
                float elas   = (float) Math.random() * 1.05f;

                SGE.physics().activator().contentId(s).mass(1000).elasticity(elas).colliderType(ColliderType.Initiator)
                        .initialVelocity(new SimpleVector(0f, 0f, 0f, speedX, speedY, speedZ)).activate();

                SGE.physics().applyPhysicalProperty(s, new PHYSPROP_Gravity());
            }
        }
    }
}
