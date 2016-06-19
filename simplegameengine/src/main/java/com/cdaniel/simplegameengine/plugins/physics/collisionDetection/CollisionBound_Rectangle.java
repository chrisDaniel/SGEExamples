package com.cdaniel.simplegameengine.plugins.physics.collisionDetection;

import com.cdaniel.simplegameengine.plugins.physics.core.PhysicsWrapper;

/**
 * Created by christopher.daniel on 5/23/16.
 */
public class CollisionBound_Rectangle implements CollisionBoundary {


    public boolean detectCollision(PhysicsWrapper i, PhysicsWrapper l) {

        // check 2
        // check the y boundary
        float i_XMax = i.getContent().get_maxX();
        float i_XMin = i.getContent().get_minX();
        float i_YMax = i.getContent().get_maxY();
        float i_YMin = i.getContent().get_minY();
        float i_ZMax = i.getContent().get_maxZ();
        float i_ZMin = i.getContent().get_minZ();

        float l_XMax = l.getContent().get_maxX();
        float l_XMin = l.getContent().get_minX();
        float l_YMax = l.getContent().get_maxY();
        float l_YMin = l.getContent().get_minY();
        float l_ZMax = l.getContent().get_maxZ();
        float l_ZMin = l.getContent().get_minZ();

        boolean passX = testXYZ(i_XMin, i_XMax, l_XMin, l_XMax);
        boolean passY = testXYZ(i_YMin, i_YMax, l_YMin, l_YMax);
        boolean passZ = testXYZ(i_ZMin, i_ZMax, l_ZMin, l_ZMax);

        if(passX && passY && passZ){
            return true;
        }
        return false;
    }  
    private boolean testXYZ(float iMin, float iMax, float lMin, float lMax){
        
        if ((iMax < lMax) && (iMax > lMin)) {
            return true;
        }
        if ((iMin < lMax) && (iMin > lMin)) {
            return true;
        }
        if ((lMax < iMax) && (lMax > iMin)) {
            return true;
        }
        if ((lMin < iMax) && (lMin > iMin)) {
            return true;
        }

        return false;
    }
}
